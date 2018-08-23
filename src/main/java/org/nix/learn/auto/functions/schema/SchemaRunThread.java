package org.nix.learn.auto.functions.schema;

import org.apache.log4j.Logger;

/**
 * @author zhangpei341@pingan.cn.com 2018/8/23 上午9:53
 * @version 1.0
 */
public class SchemaRunThread extends Thread {

    private static final Logger logger = Logger.getLogger(SchemaRunThread.class);


    private SchemaRun schemaRun;

    public SchemaRunThread(SchemaRun schemaRun) {
        this.schemaRun = schemaRun;
    }

    @Override
    public void run() {
        schemaRun.runTask();
    }
}
