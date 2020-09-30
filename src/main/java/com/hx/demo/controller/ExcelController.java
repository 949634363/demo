package com.hx.demo.controller;

import com.hx.demo.util.DorisDB;
import com.hx.demo.util.ExcelUtil;
import com.hx.demo.bean.vo.PushMessageStatisticsVo;
import com.hx.demo.bean.vo.PushMessageUserVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ExcelController
 *
 * @author LiaoCaiYun
 * @date 2020/7/17
 */
@RestController
@RequestMapping("/excel")
@Slf4j
public class ExcelController {

    @RequestMapping("/export")
    public void export(HttpServletResponse response) throws IOException {
        String sql = "SELECT push_message.title, push_message.content, push_message.type, " +
                "IFNULL(push_message.send_count, 0) AS sendCount, push_message.create_time AS createTime, " +
                "IFNULL(push_message_user.count, 0) AS count FROM push_message LEFT JOIN (SELECT id, count(1) count " +
                "FROM push_message_user GROUP BY id) push_message_user ON push_message.id = push_message_user.id " +
                "WHERE push_message.create_time >= \"2020-07-13 00:00:00\" ORDER BY create_time DESC";
        DorisDB dorisDB = new DorisDB();
        ResultSet resultSet = dorisDB.selectSql(sql);
        List<PushMessageStatisticsVo> pushMessageStatisticsVos = new ArrayList<>();
        try {
            while (resultSet.next()) {
                PushMessageStatisticsVo pushMessageStatisticsVo = new PushMessageStatisticsVo();
                pushMessageStatisticsVo.setContent(resultSet.getString("content"));
                pushMessageStatisticsVo.setTitle(resultSet.getString("title"));
                pushMessageStatisticsVo.setType(resultSet.getString("type").replace("\r", ""));
                pushMessageStatisticsVo.setSendCount(resultSet.getInt("sendCount"));
                pushMessageStatisticsVo.setCount(resultSet.getInt("count"));
                pushMessageStatisticsVo.setCreateTime(resultSet.getTimestamp("createTime"));
                pushMessageStatisticsVos.add(pushMessageStatisticsVo);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        HSSFWorkbook pushMessage = ExcelUtil.getInstance().toExcel(pushMessageStatisticsVos, PushMessageStatisticsVo.class, "PushMessage");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=test.xls");
        response.flushBuffer();
        pushMessage.write(response.getOutputStream());
    }

    @RequestMapping("/export/user")
    public void exportUser(HttpServletResponse response) throws IOException {
        String sql = "SELECT mobile, DATE_FORMAT(time, '%Y-%m-%d %H:00:00') as time FROM push_message_user WHERE time < " +
                "'2020-07-29 00:00:00' AND time > '2020-07-28 00:00:00' AND mobile IN(SELECT mobile FROM push_message_user WHERE time < " +
                "'2020-07-29 00:00:00' AND time > '2020-07-28 00:00:00' GROUP BY mobile LIMIT 0, 5000) ORDER BY mobile ASC, time ASC";
        DorisDB dorisDB = new DorisDB();
        ResultSet resultSet = dorisDB.selectSql(sql);
        Map<String, List<Integer>> statistics = new HashMap<>(5000);
        try {
            while (resultSet.next()) {
                String mobile = resultSet.getString("mobile");
                Date date = resultSet.getTimestamp("time");
                List<Integer> dates = statistics.get(mobile);
                if (dates == null) {
                    dates = new ArrayList<>();
                }
                String hh = DateFormatUtils.format(date, "HH");
                dates.add(Integer.valueOf(hh.startsWith("0") ? hh.replace("0", "") : hh));
                statistics.put(mobile, dates);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        // 创建一个workbook对应一个excel文件
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 在workbook中创建一个sheet对应excel中的sheet
        HSSFSheet sheet = workbook.createSheet("test");
        HSSFSheet sheet1 = workbook.createSheet("test1");
        HSSFRow head = sheet.createRow(0);
        head.createCell(0).setCellValue("");
        head.createCell(1).setCellValue("8点");
        head.createCell(2).setCellValue("9点");
        head.createCell(3).setCellValue("10点");
        head.createCell(4).setCellValue("11点");
        head.createCell(5).setCellValue("12点");
        head.createCell(6).setCellValue("13点");
        head.createCell(7).setCellValue("14点");
        head.createCell(8).setCellValue("15点");
        head.createCell(9).setCellValue("16点");
        int row = 1;
        Map<Integer, Integer> total = new HashMap<>();
        for (String mobile : statistics.keySet()) {

            HSSFRow hssfRow = sheet1.createRow(row++);
            hssfRow.createCell(0).setCellValue(mobile);
            List<Integer> dates = statistics.get(mobile);
            for (int i = 1; i <= 9; i++) {
                if (dates.contains(i + 7)) {
                    hssfRow.createCell(i).setCellValue("√");
                    Integer integer = total.get(i + 7);
                    if (integer == null) {
                        integer = 0;
                    }
                    total.put(i + 7, ++integer);
                } else {
                    hssfRow.createCell(i).setCellValue("");
                }
            }
        }
        HSSFRow end = sheet.createRow(0);
        for (Integer integer : total.keySet()) {
            end.createCell(integer - 7).setCellValue(total.get(integer));
        }

        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=test.xls");
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }
}
