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

    public static void main(String[] args) throws IOException {
        File file = new File("/Users/liaocaiyun/Program/code/hx2car/send-service/src");
        System.out.println(test(file, 0));
    }
    public static long test(File file, long line) throws IOException {
        long temp = 0;
        if (file.isFile()) {
            return Files.lines(Paths.get(file.toURI())).count() + line;
        }
        File[] files = file.listFiles();
        assert files != null;
        if (files.length == 0) {
            return 0;
        }
        for (File file1 : files) {
            temp += test(file1, line);
        }
        return line + temp;
    }
}
