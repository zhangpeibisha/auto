package org.nix.learn.auto.functions.presentation;

import org.apache.log4j.Logger;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhangpei341@pingan.cn.com 2018/9/18 上午8:51
 * @version 1.0
 */
public class LinkedMapPresentation implements Presentation<Double>{

    private static final Logger logger = Logger.getLogger(LinkedMapPresentation.class);

    /**
     * 记录状态
     */
    private PresentationStatus<Double> status;

    /**
     * 数据域
     */
    private Map<String,Object> data;

    /**
     * 子集
     */
    private Map<String,Presentation> next;

    private Presentation father;

    private Integer currIndex = 0;

    public LinkedMapPresentation(PresentationStatus<Double> presentationStatus) {
        this.status = presentationStatus;
    }

    public LinkedMapPresentation(Long all) {
        status = new AtomicLongPresentationStatus(all);
    }

    public LinkedMapPresentation(Presentation father,Long all) {
        this.father = father;
        status = new AtomicLongPresentationStatus(all);
    }

    public LinkedMapPresentation(PresentationStatus<Double> status, Presentation father) {
        this.status = status;
        this.father = father;
    }

    private Integer nextIndex = 0;

    @Override
    public Presentation addNext(String sonKey, Long runNumber) {
        if (next ==null){
            next = new ConcurrentHashMap<>();
        }
        Presentation presentation = new LinkedMapPresentation(this,runNumber);
        next.put(String.valueOf(nextIndex),presentation);
        nextIndex++;
        return presentation;
    }

    @Override
    public void putCurr(String key, Object value) {
        if (data == null){
            data = new ConcurrentHashMap<>();
        }
        data.put(String.valueOf(currIndex),value);
        currIndex++;
    }

    @Override
    @Deprecated
    public PresentationContent addNext(String sonKey) {
        return null;
    }


    @Override
    public void setSuccess() {
        if (status != null) {
            status.setSuccess();
        }

        if (isOk() && father!=null) {
            // 当状态为1时，表示任务完成，将传给上级成功完成标记
            father.setSuccess();
        }
    }

    @Override
    public void setFail() {
        if (status != null) {
            status.setFail();
        }

        if (isOk()  && father!=null) {
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
    public Boolean isOk() {
        if (runSchedule()!=null){
            return runSchedule() == 1;
        }
        return true;
    }








    public PresentationStatus getStatus() {
        return status;
    }

    public void setStatus(PresentationStatus<Double> status) {
        this.status = status;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public Map<String, Presentation> getNext() {
        return next;
    }

    public void setNext(Map<String, Presentation> next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "LinkedMapPresentation{" +
                "status=" + status +
                ", data=" + data +
                ", next=" + next +
                '}';
    }
}
