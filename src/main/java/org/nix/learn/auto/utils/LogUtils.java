package org.nix.learn.auto.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;

/**
 * @author zhangpei341@pingan.cn.com 2018/8/22 下午2:58
 * @version 1.0
 */
public class LogUtils {

    private static final Logger logger = Logger.getLogger(LogUtils.class);

    /**
     * 专门用于打印日志
     * @param title 日志标题
     * @param value 日志内容
     */
    public static void printLog(final String title, final Object... value){
        try {
            StringBuffer buffer = new StringBuffer();
            buffer.append("\n===================").append(title).append("===================\n");
            buffer.append(JSONObject.toJSONString(value));
            buffer.append("\n===================").append(title).append("===================\n");
            logger.info(buffer);
        }catch (Exception e){
            logger.info("标题为"+title+"的信息打印失败",e);
        }
    }

}
