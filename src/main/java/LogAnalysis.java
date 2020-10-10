import com.alibaba.fastjson.JSONObject;
import com.hx.pubnet.bean.vo.huanxin.msg.HuanxinMsgContentTxtVo;
import com.hx.pubnet.bean.vo.huanxin.msg.HuanxinMsgFromType;
import com.hx.pubnet.bean.vo.huanxin.msg.HuanxinMsgVo;
import com.hx.pubnet.bean.vo.huanxin.msg.ext.HuanxinMsgExtGonggaoVo;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * LogAnalysis
 * 日志分析
 *
 * @author LiaoCaiYun
 * @date 2020/7/24
 */
public class LogAnalysis {

    private final static String PATH = "C:\\Users\\user\\Desktop\\";
    private final static String[] FILE_NAMES = {"123.log"};

    public static void main(String[] args) throws IOException {
//        String token = "b92cfc09b7ff43e884ee8594c515719a";
//        String[] parameters = { "vin=LFV2A2150E3160566", "user=18823730909" , "id="};
//        Arrays.sort(parameters);
//        StringBuffer para = new StringBuffer();
//        for (int i = 0; i < parameters.length; i++) {
//            if (i > 0) {
//                para.append("&");
//            }
//            para.append(parameters[i]);
//        }
//        String sign_temp = para.toString();
//        String sign_md5 = DigestUtils.md5Hex(sign_temp + token);
//        System.out.println(sign_md5);
        write();
    }

    public static void write() throws IOException {
        long start = System.currentTimeMillis();
        HuanxinMsgVo huanxinMsgVo = new HuanxinMsgVo();
        huanxinMsgVo.setExt(new HuanxinMsgExtGonggaoVo(String.valueOf(712201905), "129", "二手车头条", "二手车头条"));
        huanxinMsgVo.setFrom(HuanxinMsgFromType.ERSHOUCHEADMIN);
        huanxinMsgVo.setMsg(new HuanxinMsgContentTxtVo("新车源上新！你可能想要的XX XX!"));
        huanxinMsgVo.setTarget(Arrays.asList("hx1592202296100726024"));
        String fileName = "push.log";
        File file = new File(PATH + fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file, true);
        for (int i = 0; i < 10; i++) {
            fileOutputStream.write((huanxinMsgVo.toSendString() + "\n").getBytes());
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    public static void read() throws IOException {
        long start = System.currentTimeMillis();
        String fileName = "push.log";
        File file = new File(PATH + fileName);
        LineNumberReader lnr = new LineNumberReader(new FileReader(file));
        String lineStr;
        while ((lineStr = lnr.readLine()) != null) {
            JSONObject.parseObject(lineStr);
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    public static void test() {

        for (String fileName : FILE_NAMES) {
            System.out.println("===============================");
            System.out.println(fileName);
            // 获取文件行数
            File logFile = new File(PATH, fileName);
            try {
                long line = Files.lines(Paths.get(PATH + fileName)).count();
                System.out.println("文件行数：" + line);
                LineNumberReader lnr = new LineNumberReader(new FileReader(logFile));
                String lineStr;
                HashSet<String> strings = new HashSet<>();
                while ((lineStr = lnr.readLine()) != null) {
                    strings.add(lineStr);
                }
                StringBuilder sb = new StringBuilder();
                System.out.println(strings.size());
                for (String string : strings) {
//                    System.out.println(string);
                    sb.append(string).append(",");
                }
                System.out.println(sb);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
