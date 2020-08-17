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

    private final static String PATH = "C:\\Users\\user\\Desktop\\";

    private final static String FILE_NAME = "123.log";
    private final static String FILE_NAME_NEW = "huanxinid.log";
    private final static String PHONE = "phone.txt";

    public static void main(String[] args) {
        // 获取文件行数
        File logFile = new File(PATH, FILE_NAME);
        try {
            long line = Files.lines(Paths.get(PATH + FILE_NAME)).count();
            LineNumberReader lnr = new LineNumberReader(new FileReader(logFile));
            String lineStr;
            Map<String, Integer> temp = new HashMap<>();
            Map<String, Integer> error = new HashMap<>();
            while ((lineStr = lnr.readLine()) != null) {
                if (!lineStr.contains("TestController") || !lineStr.contains("hx")) {
                    continue;
                }
//                System.out.println(lineStr);
                lineStr = lineStr.substring(lineStr.indexOf("hx"));
                Integer count = temp.get(lineStr);
                temp.put(lineStr, count == null ? 1 : ++count);
                if (count != null) {
                    error.put(lineStr, count);
                }
            }
            System.out.println(temp.get("hx1421157968562686647"));
            System.out.println("总行数：" + line);
            System.out.println("发送总人数：" + temp.size());
            // 重复发送人数
            for (String key : error.keySet()) {
                System.out.println(key + " -------------- " + error.get(key));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
