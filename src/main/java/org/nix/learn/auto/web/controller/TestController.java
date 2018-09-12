package org.nix.learn.auto.web.controller;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.nix.learn.auto.model.SchemaModel;
import org.nix.learn.auto.utils.CryptoUtils;
import org.nix.learn.auto.utils.DateUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author zhangpei341@pingan.cn.com 2018/9/12 下午1:59
 * @version 1.0
 */
@RestController
@RequestMapping("/test")
public class TestController {

    private static final Logger logger = Logger.getLogger(TestController.class);

    private List<SchemaModel> models;

    {
        models = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            SchemaModel huoqianbao = new SchemaModel();
            huoqianbao.setId(String.valueOf(i));
            huoqianbao.setUse(true);
            huoqianbao.setUseVersion("3.0.0");
            huoqianbao.setName("活钱宝");
            huoqianbao.setPath(JSON.toJSONString(CryptoUtils.encodeMD5(DateUtils.getCurrentDateTime() + Math.random()*10000)));
            huoqianbao.setRemarks("测试数据");
            models.add(huoqianbao);
        }
    }

    @GetMapping("/getSchemaJson")
    public Object getSchemaJson(@RequestParam("page")Integer page,@RequestParam("limit")Integer limit) {
        System.out.println(page+" "+limit);
        int start = (page-1)*limit;
        int end = start+limit;
        return new Result(models.size(), JSON.toJSON(models.subList(start,end)));
    }

    class Result {
        private Integer code = 0;
        private String msg = "获取成功";
        private Integer count;
        private Object data;

        public Result(Integer count, Object data) {
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


}
