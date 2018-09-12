package org.nix.learn.auto.web.controller;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.nix.learn.auto.model.SchemaModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangpei341@pingan.cn.com 2018/9/12 下午1:59
 * @version 1.0
 */
@RestController
@RequestMapping("/test")
public class TestController {

    private static final Logger logger = Logger.getLogger(TestController.class);

    @GetMapping("/getSchemaJson")
    public Object getSchemaJson(){
        SchemaModel huoqianbao = new SchemaModel();
        huoqianbao.setUse(true);
        huoqianbao.setUseVersion("3.0.0");
        huoqianbao.setName("活钱宝");
        huoqianbao.setPath("yqbnative://app.1qianbao.com/huoqianbao/index");
        huoqianbao.setRemarks("测试数据");
//        modelMapper.insert(huoqianbao);

        SchemaModel assets = new SchemaModel();
        assets.setUse(true);
        assets.setUseVersion("3.0.0");
        assets.setName("assets");
        assets.setPath("yqbnative://app.1qianbao.com/assets/integral");
        assets.setRemarks("测试数据");


        SchemaModel setting = new SchemaModel();
        setting.setUse(true);
        setting.setUseVersion("3.0.0");
        setting.setName("设置");
        setting.setPath("yqbnative://app.1qianbao.com/setting/index");
        setting.setRemarks("测试数据");



        SchemaModel lifepay = new SchemaModel();
        lifepay.setUse(true);
        lifepay.setUseVersion("3.0.0");
        lifepay.setName("生活支付");
        lifepay.setPath("yqbnative://app.1qianbao.com/lifepay/index");
        lifepay.setRemarks("测试数据");


        List<SchemaModel> models = new ArrayList<>();
        models.add(huoqianbao);
        models.add(assets);
        models.add(setting);
        models.add(lifepay);

        System.out.println(JSON.toJSONString(models));
        return new Result(models.size(),JSON.toJSON(models));
    }

    class Result{
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
