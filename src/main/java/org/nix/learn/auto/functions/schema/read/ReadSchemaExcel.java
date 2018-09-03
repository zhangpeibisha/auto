package org.nix.learn.auto.functions.schema.read;

import org.apache.log4j.Logger;
import org.nix.learn.auto.functions.presentation.PresentationContent;
import org.nix.learn.auto.utils.excelutil.ExcelProcessor;
import org.nix.learn.auto.utils.excelutil.XRow;

/**
 * @author zhangpei341@pingan.cn.com 2018/9/3 上午10:39
 * @version 1.0
 */
public class ReadSchemaExcel extends ExcelProcessor {

    private static final Logger logger = Logger.getLogger(ReadSchemaExcel.class);

    /**
     * 执行报告
     */
    private PresentationContent presentation;

    /**
     * 执行结果
     */
    private Boolean ok;

    /**
     * 无报告
     * @param fileName
     * @throws Exception
     */
    public ReadSchemaExcel(String fileName) throws Exception {
        super(fileName);
    }

    public ReadSchemaExcel(String fileName, PresentationContent presentation) throws Exception {
        super(fileName);
        this.presentation = presentation;
    }

    @Override
    public void processRow(XRow row) {
        // 当前行标
        int rowIndex = row.getRowIndex();
        // 当前行有多少列
        int cellSize = row.getCellsSize();

        if (cellSize != 10){
            processRowResult(false);
            return;
        }
        try {


        }catch (Exception e){
            logger.info("");
        }

    }

    @Override
    public void processRowResult(boolean isOK) {
        this.ok = isOK;
    }

    public Boolean getOk() {
        return ok;
    }
}
