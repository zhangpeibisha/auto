package org.nix.learn.auto.functions.presentation;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.nix.learn.auto.utils.LogUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhangpei341@pingan.cn.com 2018/9/3 下午2:29
 * @version 1.0
 */
public class TaskPresentation extends AbstractPresentationContent implements Presentation<Double> {

    private static final Logger logger = Logger.getLogger(TaskPresentation.class);

    private transient PresentationStatus<Double> status;

    private transient Presentation father;

    /**
     * 头节点时使用，且不需要回调状态时
     */
    public TaskPresentation() {
    }

    /**
     * 头节点使用，需要回调状态
     *
     * @param status 状态回调器
     */
    public TaskPresentation(PresentationStatus<Double> status) {
        this.status = status;
    }

    /**
     * 使用默认的回调状态类，头节点使用
     *
     * @param taskNumber 任务数量
     */
    public TaskPresentation(Long taskNumber) {
        if (taskNumber != null) {
            status = new AtomicLongPresentationStatus(taskNumber);
        }
    }

    /**
     * 子节点使用,不对外开放
     *
     * @param father 上级节点
     */
    private TaskPresentation(Presentation father, Long taskNumber) {
        this(taskNumber);
        this.father = father;
    }

    /**
     * 动态记录状态
     *
     * @param sonKey
     * @return
     */
    @Override
    public PresentationContent addNext(String sonKey) {
        return addNext(sonKey, null);
    }

    /**
     * 动态记录状态
     *
     * @param sonKey    添加儿子的键值
     * @param runNumber 子任务有多少个
     * @return
     */
    @Override
    public Presentation addNext(String sonKey, Long runNumber) {
        if (sons == null) {
            sons = new ConcurrentHashMap<>();
        }
        Presentation presentation = new TaskPresentation(this, runNumber);
        sons.put(sonKey, presentation);
        return presentation;
    }

    @Override
    public void setSuccess() {
        if (status != null) {
            status.setSuccess();
        }

        Double result = runSchedule();
        if (result != null && result == 1) {
            // 当状态为1时，表示任务完成，将传给上级成功完成标记
            father.setSuccess();
        }
    }

    @Override
    public void setFail() {
        if (status != null) {
            status.setFail();
        }

        Double result = runSchedule();
        if (result != null && result == 1) {
            // 当状态为1时，表示任务完成，将传给上级成功完成标记
            father.setSuccess();
        }
    }

    @Override
    public Double runSchedule() {
        if (status != null) {
            return status.runSchedule();
        }
        return null;
    }

    @Override
    public Map<String, Object> getInfo() {
        info.put("runSchedule", JSON.toJSON(status));
        return super.getInfo();
    }

    public static void main(String[] args) {
        TaskPresentation taskPresentation = new TaskPresentation(20L);
        taskPresentation.putCurr("computer", "iMac");

        PresentationContent son1 = taskPresentation.addNext("1", 1L);
        son1.putCurr("vivo", "vivo手机");

        PresentationContent son1_1 = son1.addNext("1");
        son1_1.putCurr("活钱宝Url", "yqb**************");

        Presentation son2 = taskPresentation.addNext("2", 1L);
        son2.setFail();
        son2.putCurr("付账", "give*********");

        LogUtils.printLog("test", taskPresentation);
    }
}
