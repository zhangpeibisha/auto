package org.nix.learn.auto.model;

import org.apache.log4j.Logger;
import org.nix.learn.auto.model.base.BaseModel;

/**
 * 存储在数据库中的运行文本报告
 * @author zhangpei341@pingan.cn.com 2018/8/29 上午11:34
 * @version 1.0
 */
public class PersentationModel  extends BaseModel {

    private static final Logger logger = Logger.getLogger(PersentationModel.class);

    /**
     * 报告生成唯一编码
     */
    private String persentationId;

    /**
     * 报告的json文本
     */
    private String value;

    public PersentationModel() {
    }

    public PersentationModel(String persentationId, String value) {
        this.persentationId = persentationId;
        this.value = value;
    }

    public String getPersentationId() {
        return persentationId;
    }

    public void setPersentationId(String persentationId) {
        this.persentationId = persentationId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "PersentationModel{" +
                "persentationId='" + persentationId + '\'' +
                ", value='" + value + '\'' +
                ", id='" + id + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
