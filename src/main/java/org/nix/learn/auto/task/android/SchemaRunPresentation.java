package org.nix.learn.auto.task.android;

import org.nix.learn.auto.task.android.Schema;

/**
 * 生成报告文件
 * @author zhangpei341@pingan.cn.com 2018/8/21 下午1:59
 * @version 1.0
 */
public class SchemaRunPresentation {

    /**
     * 运行的schema
     */
    private Schema schema;

    /**
     * 截图保存地址
     */
    private String screenshotPath;

    /**
     * 运行结果
     */
    private Boolean runResult;

    /**
     * 备注信息
     */
    private String remarks;

    /**
     * 创建时未知结果
     * @param schema 该scheme的运行结果
     */
    public SchemaRunPresentation(Schema schema) {
        this.schema = schema;
    }

    /**
     * 运行失败的结果
     * @param schema
     * @param runResult
     */
    public SchemaRunPresentation(Schema schema, Boolean runResult) {
        this.schema = schema;
        this.runResult = runResult;
    }

    /**
     * 运行成功后的信息
     * @param schema
     * @param screenshotPath
     * @param runResult
     */
    public SchemaRunPresentation(Schema schema, String screenshotPath, Boolean runResult) {
        this.schema = schema;
        this.screenshotPath = screenshotPath;
        this.runResult = runResult;
    }

    public Schema getSchema() {
        return schema;
    }

    public void setSchema(Schema schema) {
        this.schema = schema;
    }

    public String getScreenshotPath() {
        return screenshotPath;
    }

    public void setScreenshotPath(String screenshotPath) {
        this.screenshotPath = screenshotPath;
    }

    public Boolean getRunResult() {
        return runResult;
    }

    public void setRunResult(Boolean runResult) {
        this.runResult = runResult;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "SchemaRunPresentation{" +
                "schema=" + schema +
                ", screenshotPath='" + screenshotPath + '\'' +
                ", runResult=" + runResult +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
