package org.nix.learn.auto.model.base;

import com.alibaba.fastjson.annotation.JSONField;
import org.apache.log4j.Logger;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * 数据库基础类型，其中包含了系统流水号，创建时间，修改时间
 * @author zhangpei341@pingan.cn.com 2018/8/29 上午11:47
 * @version 1.0
 */
public class BaseModel {

    private static final Logger logger = Logger.getLogger(BaseModel.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "select replace(uuid(), '-', '') as id from dual")
    protected String id;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    protected Date createTime;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    protected Date updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "BaseModel{" +
                "id='" + id + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
