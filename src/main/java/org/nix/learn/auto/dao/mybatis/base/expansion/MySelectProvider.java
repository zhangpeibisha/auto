package org.nix.learn.auto.dao.mybatis.base.expansion;

import org.apache.ibatis.mapping.MappedStatement;
import org.apache.log4j.Logger;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.SqlHelper;
import tk.mybatis.mapper.provider.base.BaseSelectProvider;

/**
 * @author zhangpei341@pingan.cn.com 2018/9/5 下午5:58
 * @version 1.0
 */
public class MySelectProvider extends BaseSelectProvider {

    private static final Logger logger = Logger.getLogger(MySelectProvider.class);

    public MySelectProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }

}
