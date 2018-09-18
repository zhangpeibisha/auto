package org.nix.learn.auto;


import java.util.Iterator;
import java.util.Map;

import javax.sound.midi.Synthesizer;

import com.alibaba.fastjson.JSON;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author zhangpei341@pingan.cn.com 2018/9/17 下午6:13
 * @version 1.0
 */
public class Main {


    /**
     * 遍历json格式数据
     *
     * @param json
     * @return
     */
    public static Object traveseJson(Object json) throws JSONException {

        if (json == null) {
            return null;
        }
        if (json instanceof JSONObject) {//json 是一个map
            //创建一个json对象
            JSONObject jsonObj = new JSONObject();
            //将json转换为JsonObject对象
            JSONObject jsonStr = (JSONObject) json;
            //迭代器迭代 map集合所有的keys
            Iterator it = jsonStr.keys();
            while (it.hasNext()) {
                //获取map的key
                String key = (String) it.next();
                //得到value的值
                Object value = jsonStr.get(key);
                System.out.println("key= " + key + " value= " +value);
                //递归遍历
                jsonObj.put(key, traveseJson(value));

            }
            return jsonObj;

        } else if (json instanceof JSONArray) {// if  json 是 数组
            JSONArray jsonAry = new JSONArray();
            JSONArray jsonStr = (JSONArray) json;
            //获取Array 的长度
            int length = jsonStr.length();
            for (int i = 0; i < length; i++) {
                jsonAry.put(traveseJson(jsonStr.get(i)));
                System.out.println("key= " + i + "  " + jsonStr.get(i));
            }

            return jsonAry;

        } else {//其他类型

            return json;
        }


    }


    public static void main(String[] args) throws JSONException {


        String str =
                "{\"data\":{\"0\":{\"apk\":{\"appActivity\":\"??YQB???\",\"appPackage\":\"????????\",\"createTime\":\"2018-09-17 16:59:11\",\"id\":\"f19dbe24ba5711e8a1f65cf3fcddc180\",\"installPath\":\"??YQB????\",\"runEnvironment\":\"2\",\"updateTime\":\"2018-09-17 16:59:11\",\"version\":\"6.0\"},\"msg\":\"启动执行成功\",\"schemas\":[{\"createTime\":\"2018-09-17 16:58:52\",\"id\":\"e66c9095ba5711e8a1f65cf3fcddc180\",\"name\":\"???\",\"path\":\"yqbnative://app.1qianbao.com/huoqianbao/index\",\"remarks\":\"????\",\"updateTime\":\"2018-09-17 16:58:52\",\"use\":true,\"useVersion\":\"3.0.0\"},{\"createTime\":\"2018-09-17 16:58:52\",\"id\":\"e66c94f4ba5711e8a1f65cf3fcddc180\",\"name\":\"assets\",\"path\":\"yqbnative://app.1qianbao.com/assets/integral\",\"remarks\":\"????\",\"updateTime\":\"2018-09-17 16:58:52\",\"use\":true,\"useVersion\":\"3.0.0\"},{\"createTime\":\"2018-09-17 16:58:52\",\"id\":\"e66c963aba5711e8a1f65cf3fcddc180\",\"name\":\"??\",\"path\":\"yqbnative://app.1qianbao.com/setting/index\",\"remarks\":\"????\",\"updateTime\":\"2018-09-17 16:58:52\",\"use\":true,\"useVersion\":\"3.0.0\"},{\"createTime\":\"2018-09-17 16:58:52\",\"id\":\"e66c970aba5711e8a1f65cf3fcddc180\",\"name\":\"????\",\"path\":\"yqbnative://app.1qianbao.com/lifepay/index\",\"remarks\":\"????\",\"updateTime\":\"2018-09-17 16:58:52\",\"use\":true,\"useVersion\":\"3.0.0\"},{\"createTime\":\"2018-09-17 16:58:52\",\"id\":\"e66c97c3ba5711e8a1f65cf3fcddc180\",\"name\":\"???\",\"path\":\"yqbnative://app.1qianbao.com/xuqibao/index\",\"remarks\":\"????\",\"updateTime\":\"2018-09-17 16:58:52\",\"use\":true,\"useVersion\":\"3.0.0\"},{\"createTime\":\"2018-09-17 16:58:52\",\"id\":\"e66c9877ba5711e8a1f65cf3fcddc180\",\"name\":\"????\",\"path\":\"yqbnative://app.1qianbao.com/msgcenter/index\",\"remarks\":\"????\",\"updateTime\":\"2018-09-17 16:58:52\",\"use\":true,\"useVersion\":\"3.0.0\"},{\"createTime\":\"2018-09-17 16:58:52\",\"id\":\"e66c992cba5711e8a1f65cf3fcddc180\",\"name\":\"?????\",\"path\":\"yqbnative://app.1qianbao.com/transfer/index\",\"remarks\":\"????\",\"updateTime\":\"2018-09-17 16:58:52\",\"use\":true,\"useVersion\":\"3.0.0\"},{\"createTime\":\"2018-09-17 16:58:52\",\"id\":\"e66c99e3ba5711e8a1f65cf3fcddc180\",\"name\":\"????\",\"path\":\"yqbnative://app.1qianbao.com/mobilerecharge/index\",\"remarks\":\"????\",\"updateTime\":\"2018-09-17 16:58:52\",\"use\":true,\"useVersion\":\"3.0.0\"},{\"createTime\":\"2018-09-17 16:58:52\",\"id\":\"e66c9a9cba5711e8a1f65cf3fcddc180\",\"name\":\"???\",\"path\":\"yqbnative://app.1qianbao.com/barcode/index\",\"remarks\":\"????\",\"updateTime\":\"2018-09-17 16:58:52\",\"use\":true,\"useVersion\":\"3.0.0\"},{\"createTime\":\"2018-09-17 16:58:52\",\"id\":\"e66c9ba0ba5711e8a1f65cf3fcddc180\",\"name\":\"????\",\"path\":\"yqbnative://app.1qianbao.com/lovedonate/index\",\"remarks\":\"????\",\"updateTime\":\"2018-09-17 16:58:52\",\"use\":true,\"useVersion\":\"3.0.0\"},{\"createTime\":\"2018-09-17 16:58:52\",\"id\":\"e66c9c50ba5711e8a1f65cf3fcddc180\",\"name\":\"????\",\"path\":\"yqbnative://app.1qianbao.com/rebatemall/index\",\"remarks\":\"????\",\"tpl\":\"{tplId:'dfafafasfa',tplParams:{mallUrl:'',isNativeNav:'',contentCalss:''}}\",\"updateTime\":\"2018-09-17 16:58:52\",\"use\":true,\"useVersion\":\"3.0.0\"},{\"createTime\":\"2018-09-17 16:58:52\",\"id\":\"e66c9d1eba5711e8a1f65cf3fcddc180\",\"name\":\"?????\",\"path\":\"yqbnative://app.1qianbao.com/wanlitong/game\",\"remarks\":\"????\",\"updateTime\":\"2018-09-17 16:58:52\",\"use\":true,\"useVersion\":\"3.1.6\"},{\"createTime\":\"2018-09-17 16:58:52\",\"id\":\"e66c9dd0ba5711e8a1f65cf3fcddc180\",\"name\":\"????\",\"path\":\"yqbnative://app.1qianbao.com/myfinancial/index\",\"remarks\":\"????\",\"updateTime\":\"2018-09-17 16:58:52\",\"use\":true,\"useVersion\":\"3.3.7\"}]}},\"next\":[{\"data\":{\"0\":{\"ip\":\"127.0.0.1\",\"msg\":\"执行成功\"}},\"next\":[{\"data\":{\"0\":{\"appiumPath\":\"http://127.0.0.1:4723/wd/hub\",\"msg\":\"启动执行成功\",\"phone\":{\"androidDeviceReadyTimeout\":60,\"appActivity\":\".MainActivity\",\"appPackage\":\"com.yqb.pingan.yqb\",\"autoLaunch\":true,\"autoWebview\":false,\"autoWebviewTimeout\":2000,\"automationName\":\"Appium\",\"avdLaunchTimeout\":60000,\"avdReadyTimeout\":60000,\"databaseEnabled\":false,\"desired\":{\"appPackage\":\"com.yqb.pingan.yqb\",\"noReset\":true,\"dontStopAppOnReset\":false,\"disableAndroidWatchers\":false,\"deviceName\":\"1267e25a\",\"deviceReadyTimeout\":60,\"fullReset\":false,\"ignoreUnimportantViews\":false,\"newCommandTimeout\":60,\"automationName\":\"Appium\",\"autoWebview\":false,\"unicodeKeyboard\":false,\"platformName\":\"Android\",\"udid\":\"1267e25a\",\"enablePerformanceLogging\":false,\"resetKeyboard\":false,\"useKeystore\":false,\"androidDeviceReadyTimeout\":60,\"orientation\":\"PORTRAIT\",\"autoWebviewTimeout\":2000,\"noSign\":false,\"appActivity\":\".MainActivity\",\"avdLaunchTimeout\":60000,\"autoLaunch\":true,\"avdReadyTimeout\":60000},\"deviceManufacturer\":\"vivo\",\"deviceModel\":\"vivo X9s Plus\",\"deviceName\":\"1267e25a\",\"deviceReadyTimeout\":60,\"deviceScreenSize\":\"1080x1920\",\"deviceUDID\":\"1267e25a\",\"disableAndroidWatchers\":false,\"dontStopAppOnReset\":false,\"enablePerformanceLogging\":false,\"fullReset\":false,\"ignoreUnimportantViews\":false,\"javascriptEnabled\":true,\"locationContextEnabled\":false,\"networkConnectionEnabled\":true,\"newCommandTimeout\":60,\"noReset\":true,\"noSign\":false,\"orientation\":\"PORTRAIT\",\"platform\":\"LINUX\",\"platformName\":\"LINUX\",\"platformVersion\":\"7.1.2\",\"resetKeyboard\":false,\"takesScreenshot\":true,\"udid\":\"1267e25a\",\"unicodeKeyboard\":false,\"useKeystore\":false,\"warnings\":{},\"webStorageEnabled\":false}}},\"next\":[{\"data\":{\"0\":{\"msg\":\"执行成功\",\"runSchema\":{\"createTime\":\"2018-09-17 16:58:52\",\"id\":\"e66c9095ba5711e8a1f65cf3fcddc180\",\"name\":\"???\",\"path\":\"yqbnative://app.1qianbao.com/huoqianbao/index\",\"remarks\":\"????\",\"updateTime\":\"2018-09-17 16:58:52\",\"use\":true,\"useVersion\":\"3.0.0\"},\"savePath\":\"/Users/mac/IdeaProjects/auto_git/src/main/file/test/2018-09-18/screenshot3810059765659258774.png\"}},\"ok\":true,\"status\":{\"all\":1,\"fail\":0,\"ok\":true,\"runSchedule\":1.0,\"success\":1}},{\"data\":{\"0\":{\"msg\":\"执行成功\",\"runSchema\":{\"createTime\":\"2018-09-17 16:58:52\",\"id\":\"e66c94f4ba5711e8a1f65cf3fcddc180\",\"name\":\"assets\",\"path\":\"yqbnative://app.1qianbao.com/assets/integral\",\"remarks\":\"????\",\"updateTime\":\"2018-09-17 16:58:52\",\"use\":true,\"useVersion\":\"3.0.0\"},\"savePath\":\"/Users/mac/IdeaProjects/auto_git/src/main/file/test/2018-09-18/screenshot5195026723506550022.png\"}},\"ok\":true,\"status\":{\"all\":1,\"fail\":0,\"ok\":true,\"runSchedule\":1.0,\"success\":1}},{\"data\":{\"0\":{\"msg\":\"执行成功\",\"runSchema\":{\"createTime\":\"2018-09-17 16:58:52\",\"id\":\"e66c963aba5711e8a1f65cf3fcddc180\",\"name\":\"??\",\"path\":\"yqbnative://app.1qianbao.com/setting/index\",\"remarks\":\"????\",\"updateTime\":\"2018-09-17 16:58:52\",\"use\":true,\"useVersion\":\"3.0.0\"},\"savePath\":\"/Users/mac/IdeaProjects/auto_git/src/main/file/test/2018-09-18/screenshot8868752121253402592.png\"}},\"ok\":true,\"status\":{\"all\":1,\"fail\":0,\"ok\":true,\"runSchedule\":1.0,\"success\":1}},{\"data\":{\"0\":{\"msg\":\"执行成功\",\"runSchema\":{\"createTime\":\"2018-09-17 16:58:52\",\"id\":\"e66c970aba5711e8a1f65cf3fcddc180\",\"name\":\"????\",\"path\":\"yqbnative://app.1qianbao.com/lifepay/index\",\"remarks\":\"????\",\"updateTime\":\"2018-09-17 16:58:52\",\"use\":true,\"useVersion\":\"3.0.0\"},\"savePath\":\"/Users/mac/IdeaProjects/auto_git/src/main/file/test/2018-09-18/screenshot8932009122448246750.png\"}},\"ok\":true,\"status\":{\"all\":1,\"fail\":0,\"ok\":true,\"runSchedule\":1.0,\"success\":1}},{\"data\":{\"0\":{\"msg\":\"执行成功\",\"runSchema\":{\"createTime\":\"2018-09-17 16:58:52\",\"id\":\"e66c97c3ba5711e8a1f65cf3fcddc180\",\"name\":\"???\",\"path\":\"yqbnative://app.1qianbao.com/xuqibao/index\",\"remarks\":\"????\",\"updateTime\":\"2018-09-17 16:58:52\",\"use\":true,\"useVersion\":\"3.0.0\"},\"savePath\":\"/Users/mac/IdeaProjects/auto_git/src/main/file/test/2018-09-18/screenshot1860937048026210841.png\"}},\"ok\":true,\"status\":{\"all\":1,\"fail\":0,\"ok\":true,\"runSchedule\":1.0,\"success\":1}},{\"data\":{\"0\":{\"msg\":\"执行成功\",\"runSchema\":{\"createTime\":\"2018-09-17 16:58:52\",\"id\":\"e66c9877ba5711e8a1f65cf3fcddc180\",\"name\":\"????\",\"path\":\"yqbnative://app.1qianbao.com/msgcenter/index\",\"remarks\":\"????\",\"updateTime\":\"2018-09-17 16:58:52\",\"use\":true,\"useVersion\":\"3.0.0\"},\"savePath\":\"/Users/mac/IdeaProjects/auto_git/src/main/file/test/2018-09-18/screenshot7328970984217409776.png\"}},\"ok\":true,\"status\":{\"all\":1,\"fail\":0,\"ok\":true,\"runSchedule\":1.0,\"success\":1}},{\"data\":{\"0\":{\"msg\":\"执行成功\",\"runSchema\":{\"createTime\":\"2018-09-17 16:58:52\",\"id\":\"e66c992cba5711e8a1f65cf3fcddc180\",\"name\":\"?????\",\"path\":\"yqbnative://app.1qianbao.com/transfer/index\",\"remarks\":\"????\",\"updateTime\":\"2018-09-17 16:58:52\",\"use\":true,\"useVersion\":\"3.0.0\"},\"savePath\":\"/Users/mac/IdeaProjects/auto_git/src/main/file/test/2018-09-18/screenshot2808397875285615312.png\"}},\"ok\":true,\"status\":{\"all\":1,\"fail\":0,\"ok\":true,\"runSchedule\":1.0,\"success\":1}},{\"data\":{\"0\":{\"msg\":\"执行成功\",\"runSchema\":{\"createTime\":\"2018-09-17 16:58:52\",\"id\":\"e66c99e3ba5711e8a1f65cf3fcddc180\",\"name\":\"????\",\"path\":\"yqbnative://app.1qianbao.com/mobilerecharge/index\",\"remarks\":\"????\",\"updateTime\":\"2018-09-17 16:58:52\",\"use\":true,\"useVersion\":\"3.0.0\"},\"savePath\":\"/Users/mac/IdeaProjects/auto_git/src/main/file/test/2018-09-18/screenshot4714991678480408327.png\"}},\"ok\":true,\"status\":{\"all\":1,\"fail\":0,\"ok\":true,\"runSchedule\":1.0,\"success\":1}},{\"data\":{\"0\":{\"msg\":\"执行成功\",\"runSchema\":{\"createTime\":\"2018-09-17 16:58:52\",\"id\":\"e66c9a9cba5711e8a1f65cf3fcddc180\",\"name\":\"???\",\"path\":\"yqbnative://app.1qianbao.com/barcode/index\",\"remarks\":\"????\",\"updateTime\":\"2018-09-17 16:58:52\",\"use\":true,\"useVersion\":\"3.0.0\"},\"savePath\":\"/Users/mac/IdeaProjects/auto_git/src/main/file/test/2018-09-18/screenshot6727824870229820939.png\"}},\"ok\":true,\"status\":{\"all\":1,\"fail\":0,\"ok\":true,\"runSchedule\":1.0,\"success\":1}},{\"data\":{\"0\":{\"msg\":\"执行成功\",\"runSchema\":{\"createTime\":\"2018-09-17 16:58:52\",\"id\":\"e66c9ba0ba5711e8a1f65cf3fcddc180\",\"name\":\"????\",\"path\":\"yqbnative://app.1qianbao.com/lovedonate/index\",\"remarks\":\"????\",\"updateTime\":\"2018-09-17 16:58:52\",\"use\":true,\"useVersion\":\"3.0.0\"},\"savePath\":\"/Users/mac/IdeaProjects/auto_git/src/main/file/test/2018-09-18/screenshot6692391065326265749.png\"}},\"ok\":true,\"status\":{\"all\":1,\"fail\":0,\"ok\":true,\"runSchedule\":1.0,\"success\":1}},{\"data\":{\"0\":{\"msg\":\"执行成功\",\"runSchema\":{\"createTime\":\"2018-09-17 16:58:52\",\"id\":\"e66c9c50ba5711e8a1f65cf3fcddc180\",\"name\":\"????\",\"path\":\"yqbnative://app.1qianbao.com/rebatemall/index\",\"remarks\":\"????\",\"tpl\":\"{tplId:'dfafafasfa',tplParams:{mallUrl:'',isNativeNav:'',contentCalss:''}}\",\"updateTime\":\"2018-09-17 16:58:52\",\"use\":true,\"useVersion\":\"3.0.0\"},\"savePath\":\"/Users/mac/IdeaProjects/auto_git/src/main/file/test/2018-09-18/screenshot5306861128906126016.png\"}},\"ok\":true,\"status\":{\"all\":1,\"fail\":0,\"ok\":true,\"runSchedule\":1.0,\"success\":1}},{\"data\":{\"0\":{\"msg\":\"执行成功\",\"runSchema\":{\"createTime\":\"2018-09-17 16:58:52\",\"id\":\"e66c9d1eba5711e8a1f65cf3fcddc180\",\"name\":\"?????\",\"path\":\"yqbnative://app.1qianbao.com/wanlitong/game\",\"remarks\":\"????\",\"updateTime\":\"2018-09-17 16:58:52\",\"use\":true,\"useVersion\":\"3.1.6\"},\"savePath\":\"/Users/mac/IdeaProjects/auto_git/src/main/file/test/2018-09-18/screenshot4908239985966676810.png\"}},\"ok\":true,\"status\":{\"all\":1,\"fail\":0,\"ok\":true,\"runSchedule\":1.0,\"success\":1}},{\"data\":{\"0\":{\"msg\":\"执行成功\",\"runSchema\":{\"createTime\":\"2018-09-17 16:58:52\",\"id\":\"e66c9dd0ba5711e8a1f65cf3fcddc180\",\"name\":\"????\",\"path\":\"yqbnative://app.1qianbao.com/myfinancial/index\",\"remarks\":\"????\",\"updateTime\":\"2018-09-17 16:58:52\",\"use\":true,\"useVersion\":\"3.3.7\"},\"savePath\":\"/Users/mac/IdeaProjects/auto_git/src/main/file/test/2018-09-18/screenshot2585388494144353984.png\"}},\"ok\":true,\"status\":{\"all\":1,\"fail\":0,\"ok\":true,\"runSchedule\":1.0,\"success\":1}}],\"ok\":true,\"status\":{\"all\":13,\"fail\":0,\"ok\":true,\"runSchedule\":1.0,\"success\":13}}],\"ok\":true,\"status\":{\"all\":1,\"fail\":0,\"ok\":true,\"runSchedule\":1.0,\"success\":1}}],\"ok\":true,\"status\":{\"all\":1,\"fail\":0,\"ok\":true,\"runSchedule\":1.0,\"success\":1}}";

        Map<String,Object> map = JSON.parseObject(str);

        System.out.println(getValue(map,new String[]{"data","0","schemas"},0).getClass().getName());


//        traveseJson(new JSONObject(str));

        System.out.println();
    }


    /**
     * 针对值获取，如果是JsonArrxuyao
     * @param map
     * @param keys
     * @param index
     * @return
     */
    public static Object getValue(Map<String, Object> map, String[] keys, Integer index) {
        int len = keys.length - 1;
        if (index > len) {
            return null;
        }
        if (index == len){
            return map.get(keys[index]);
        }
        Object value = map.get(keys[index]);
        return getValue((Map<String, Object>) value,keys,++index);
    }



}
