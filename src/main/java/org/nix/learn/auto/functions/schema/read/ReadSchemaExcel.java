package org.nix.learn.auto.functions.schema.read;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.nix.learn.auto.functions.presentation.Presentation;
import org.nix.learn.auto.functions.presentation.PresentationContent;
import org.nix.learn.auto.model.SchemaModel;
import org.nix.learn.auto.utils.LogUtils;
import org.nix.learn.auto.utils.excelutil.ExcelProcessor;
import org.nix.learn.auto.utils.excelutil.XRow;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zhangpei341@pingan.cn.com 2018/9/3 上午10:39
 * @version 1.0
 */
public class ReadSchemaExcel extends ExcelProcessor {

    private static final Logger logger = Logger.getLogger(ReadSchemaExcel.class);

    /**
     * 执行报告
     */
    private Presentation presentation;

    /**
     * 执行结果
     */
    private Boolean ok;

    private Set<SchemaModel> models;

    /**
     * 无报告
     * @param fileName
     * @throws Exception
     */
    public ReadSchemaExcel(String fileName) throws Exception {
        super(fileName);
    }

    public ReadSchemaExcel(String fileName, Presentation presentation) throws Exception {
        super(fileName);
        this.presentation = presentation;
    }

    @Override
    public void processRow(XRow row) {
        int cellSize = row.getCellsSize();
        if (row.getRowIndex() == 1){
            return;
        }
        if (cellSize!=8){
            processRowResult(true);
            return;
        }
        SchemaModel model = new SchemaModel();
        model.setPath(getValue(row,0));
        model.setName(getValue(row,1));
        model.setUse("1".equals(row.getCell(2).getValue()));
        model.setTpl(getValue(row,3));
        model.setRemarks(getValue(row,4));
        model.setUseVersion(getValue(row,5));
        model.setMaxUseVersion(getValue(row,6));
        model.setEg(getValue(row,7));
        if (models == null){
            models = new HashSet<>();
        }
        models.add(model);

    }

    private String getValue(XRow row,int index){
        return "-".equals(row.getCell(index).getValue())?"":row.getCell(index).getValue();
    }

    @Override
    public void processRowResult(boolean isOK) {
        this.ok = isOK;
    }

    public Boolean getOk() {
        return ok;
    }

    public Set<SchemaModel> getModels() {
        return models;
    }

    public static void main(String[] args) {
        String path = "/Users/mac/IdeaProjects/auto_git/src/main/file/example/submit.xlsx";
        try {
            ReadSchemaExcel excel = new ReadSchemaExcel(path);
            excel.processByRow(1);
            System.out.println(JSON.toJSON(excel.getModels()));
            LogUtils.printLog("models",excel.getModels(),excel.getOk());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("ebd1b6e0afe811e8957f9825f24eae7e".length());
    }
}
