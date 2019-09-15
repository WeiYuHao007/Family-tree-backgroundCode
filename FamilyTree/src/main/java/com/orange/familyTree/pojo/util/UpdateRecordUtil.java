package com.orange.familyTree.pojo.util;

import com.orange.familyTree.entity.mysql.GenealogyUpdateRecord;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UpdateRecordUtil {
    public static String createUpdateRemark(String genealogyName, String adminName, Timestamp time) {
        String remark = genealogyName + " " + "管理员：" + adminName + " 提交于 " + time;
        return remark.toString();
    }

    public static Timestamp getNowTimestamp() {
        // 获得系统时间.
        Date date = new Date();
        // 将时间格式转换成符合Timestamp要求的格式.
        String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        // 把时间转换
        Timestamp time = Timestamp.valueOf(nowTime);
        return time;
    }
}
