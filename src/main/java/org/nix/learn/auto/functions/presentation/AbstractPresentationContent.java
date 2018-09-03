package org.nix.learn.auto.functions.presentation;

import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @see PresentationContent 接口实现业务实际需要的报告通知类
 *
 * @author zhangpei341@pingan.cn.com 2018/8/23 下午3:02
 * @version 1.0
 */
public abstract class AbstractPresentationContent implements PresentationContent {

    private static final Logger logger = Logger.getLogger(AbstractPresentationContent.class);

    protected Map<String,Object> info = new HashMap<>();

    /**
     * 存入当前层级信息的子信息
     */
    protected transient Map<String,Object> sons;

    /**
     * 向当前层级报告存入key-value信息保存
     * @param key key
     * @param value 需要保存的值
     */
    @Override
    public void putCurr(String key, Object value) {
        info.put(key,value);
    }

    /**
     * 只将map信息打印出来，并向map中添加其子信息
     * @return 一个以map存储的报告
     */
    public Map<String, Object> getInfo() {
        info.put("sons",sons);
        return info;
    }
}
