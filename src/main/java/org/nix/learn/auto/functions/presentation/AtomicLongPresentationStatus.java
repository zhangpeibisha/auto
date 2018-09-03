package org.nix.learn.auto.functions.presentation;

import org.apache.log4j.Logger;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author zhangpei341@pingan.cn.com 2018/9/3 下午3:09
 * @version 1.0
 */
public class AtomicLongPresentationStatus implements PresentationStatus<Double>{

    private AtomicLong success = new AtomicLong();

    private AtomicLong fail = new AtomicLong();

    private AtomicLong all;

    /**
     * @param all 任务数量
     */
    public AtomicLongPresentationStatus(long all) {
        this.all = new AtomicLong(all);
    }

    @Override
    public void setFail() {
        if (fail.get()+success.get()<all.get()){
            fail.incrementAndGet();
        }
    }

    @Override
    public void setSuccess() {
        if (success.get()+fail.get()< all.get()){
            success.incrementAndGet();
        }
    }

    @Override
    public Double runSchedule() {
        long result = success.get()+fail.get();
        if (result == 0){
            return 0.0;
        }
        return result*1.0/all.get();
    }

    public AtomicLong getSuccess() {
        return success;
    }

    public AtomicLong getFail() {
        return fail;
    }

    public AtomicLong getAll() {
        return all;
    }

    public Double getRunSchedule(){
        return runSchedule();
    }
}
