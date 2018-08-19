package org.nix.learn.auto.core.obtain;

import java.util.Map;

/**
 * @author zhangpei
 * @version 1.0
 * @date 2018/8/19
 */
public abstract class AbstractCommandUtils implements Command {

    /**
     * 阅读层级map中的值
     *
     * @param key   完整的key
     * @param split key的分隔符。分层次的key
     * @return 最终目的想要得到的key
     */
    public static Object readMap(Map map, String key, String split) {
        String[] keys = key.split(split);
        return readMap(map, keys, 0);
    }

    /**
     * @param keys  map种的key值
     * @param index 当前扫描的层级
     * @return 指定map中的值
     */
    public static Object readMap(Map map, String[] keys, int index) {
        if (keys.length - 1 <= index) {
            return map.get(keys[index]);
        }
        Object value = map.get(keys[index]);
        if (value != null) {
            if (value instanceof Map) {
                map = (Map) value;
                return readMap(map, keys, ++index);
            }
        }
        return value;
    }

}
