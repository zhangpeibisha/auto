package org.nix.learn.auto.model;

import org.nix.learn.auto.model.base.BaseModel;

import javax.persistence.Table;

/**
 * 存储在数据库中的运行文本报告
 * @author zhangpei341@pingan.cn.com 2018/8/29 上午11:34
 * @version 1.0
 */
@Table(name = "presentation_model")
public class PresentationModel extends BaseModel {

    /**
     * 报告生成唯一编码
     */
    private String presentationId;

    /**
     * 报告的json文本
     */
    private String value;

    public PresentationModel() {
    }

    public PresentationModel(String presentationId, String value) {
        this.presentationId = presentationId;
        this.value = value;
    }

    public String getPresentationId() {
        return presentationId;
    }

    public void setPresentationId(String presentationId) {
        this.presentationId = presentationId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "PresentationModel{" +
                "presentationId='" + presentationId + '\'' +
                ", value='" + value + '\'' +
                ", id='" + id + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
