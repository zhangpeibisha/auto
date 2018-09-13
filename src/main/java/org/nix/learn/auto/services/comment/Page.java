package org.nix.learn.auto.services.comment;

import org.apache.log4j.Logger;
import org.nix.learn.auto.model.ApkInfoModel;
import org.nix.learn.auto.model.SchemaModel;
import org.nix.learn.auto.model.base.BaseModel;
import org.nix.learn.auto.services.ServerException;

import javax.persistence.Table;
import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

/**
 * 分页使用
 * @author zhangpei341@pingan.cn.com 2018/9/13 下午5:22
 * @version 1.0
 */
public class Page {

    private static final Logger logger = Logger.getLogger(Page.class);

    /**
     * 需要查询的数据，全部数据由*代替，默认为*
     */
    private String select = "*";

    /**
     * 需要查询的表
     */
    private String form;

    /**
     * 条件查询
     */
    private String where;

    /**
     * 当前行号
     */
    private Integer currRow;

    /**
     * 需要的数据量
     */
    private Integer limit;

    /**
     * 用来存储class信息
     */
    private transient Set<Class<? extends BaseModel>> formClass;

    /**
     * 完整的配置一个分页参数
     * @param select
     * @param form
     * @param where
     * @param currRow 页码号
     * @param limit
     */
    public Page(String select, Set<Class<? extends BaseModel>> form, String where, Integer currRow, Integer limit) {
        init(select,form,where,currRow,limit);
    }

    public Page() {
    }

    /**
     * 初始化函数
     * @param select
     * @param form
     * @param where
     * @param currRow
     * @param limit
     */
    private void init(String select, Set<Class<? extends BaseModel>> form, String where, Integer currRow, Integer limit){
        this.formClass = form;
        this.currRow = (currRow-1)*limit;
        this.limit = this.currRow+limit;
        this.where = where;
        this.select = select;
    }


    public Page(Integer currRow, Integer limit, Class<? extends BaseModel> form) {
        this.currRow = currRow;
        this.limit = limit;
        setForm(form);
    }

    /**
     * 获取表名
     */
    private void setForm(){
        StringBuilder builder = new StringBuilder();
        for (Class c : formClass) {
            Table table = (Table)c.getAnnotation(Table.class);
            if (table==null){
                throw new ServerException("找不到类"+c.getName()+"对应的数据库表");
            }
            builder.append(table.name()).append(",");
        }
        int last = builder.length();
        builder.delete(last-1,last);
        this.form = builder.toString();
    }

    /**
     * 添加表
     * @param form
     */
    private void setForm(Class<? extends BaseModel> form){
        if (formClass==null){
            formClass = new HashSet<>();
        }
        formClass.add(form);
        setForm();
    }

    public String getForm(){
        setForm();
        return form;
    }

    public static void main(String[] args) {
        Page page = new Page(1,2,SchemaModel.class);
        System.out.println(page.form);
    }

}
