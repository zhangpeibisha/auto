package org.nix.learn.auto.dao.mybatis.base.expansion;


import org.apache.ibatis.mapping.MappedStatement;
import org.nix.learn.auto.utils.LogUtils;
import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.mapperhelper.EntityHelper;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.MapperTemplate;
import tk.mybatis.mapper.mapperhelper.SqlHelper;

import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

/**
 * 自己扩展通用接口
 *
 * @author zhangpei341@pingan.cn.com 2018/9/5 下午1:21
 * @version 1.0
 */
public class MySpecialProvider extends MapperTemplate {

    public MySpecialProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }

    /**
     * 自定义批量加入sql
     *
     * @return 自动生成的sql
     */
    public String insertListValue(MappedStatement ms) {
        Class<?> entityClass = this.getEntityClass(ms);
        StringBuilder sql = new StringBuilder();
        sql.append(SqlHelper.insertIntoTable(entityClass, this.tableName(entityClass)));
        sql.append(SqlHelper.insertColumns(entityClass, false, false, false));
        sql.append(" VALUES ");
        sql.append("<foreach collection=\"list\" item=\"record\" separator=\",\" >");
        sql.append("<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">");
        Set<EntityColumn> columnList = EntityHelper.getColumns(entityClass);
        Iterator var5 = columnList.iterator();
        while (var5.hasNext()) {
            EntityColumn column = (EntityColumn)var5.next();
            if (!column.isId() && column.isInsertable()) {
                sql.append(column.getColumnHolder("record")).append(",");
                LogUtils.printLog("exampl",column.getColumnHolder("record"));
            }else if (column.isId() && column.isInsertable()){
                sql.append("(SELECT REPLACE(UUID(), '-', '') AS id)").append(",");
            }
        }

        sql.append("</trim>");
        sql.append("</foreach>");
        LogUtils.printLog("sql", sql);
        return sql.toString();
    }

}
