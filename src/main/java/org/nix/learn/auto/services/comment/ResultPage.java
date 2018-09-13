package org.nix.learn.auto.services.comment;

import org.apache.log4j.Logger;

/**
 * @author zhangpei341@pingan.cn.com 2018/9/13 下午6:22
 * @version 1.0
 */
public class ResultPage {

    private static final Logger logger = Logger.getLogger(ResultPage.class);

    private Integer code = 0;
    private String msg = "获取成功";
    private Integer count;
    private Object data;

    public ResultPage(Integer count, Object data) {
        this.count = count;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", count=" + count +
                ", data=" + data +
                '}';
    }
}
