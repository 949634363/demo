import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * LogAnalysis
 * 日志分析
 *
 * @author LiaoCaiYun
 * @date 2020/7/24
 */
public class LogAnalysis {

    private final static String PATH = "C:\\Users\\user\\Desktop\\log\\";

    private final static String FILE_NAME = "service-2020-07-22.log";

    public static void main(String[] args) {
        // 获取文件行数
        File logFile = new File(PATH, FILE_NAME);
        try {
            long line = Files.lines(Paths.get(PATH + FILE_NAME)).count();
            LineNumberReader lnr = new LineNumberReader(new FileReader(logFile));
            String lineStr;
            String from;
            Map<String, Integer> temp = new HashMap<>();
            int error = 0;
            while ((lineStr = lnr.readLine()) != null) {
                // 获取from字符串
                try {
                    from = lineStr.substring(lineStr.indexOf("\"from\":\"") + 8, lineStr.indexOf("\",\"target\""));
                    from = from.startsWith("hx") ? "hx" : from;
                    Integer count = temp.get(from);
                    count = count == null ? 1 : count + 1;
                    temp.put(from, count);
                } catch (StringIndexOutOfBoundsException e) {
                    error++;
                }
            }
            System.out.println("总行数：" + line);
            int sum = 0;
            for (String key : temp.keySet()) {
                System.out.println(key + ":" + temp.get(key));
                sum += temp.get(key);
            }
            System.out.println(sum);
            System.out.println("错误行数：" + error);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
