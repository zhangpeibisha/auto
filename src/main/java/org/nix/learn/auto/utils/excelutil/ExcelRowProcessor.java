package org.nix.learn.auto.utils.excelutil;

import java.io.IOException;

/**
 * 接口，Excel行级处理器
 *
 * @author zhangchaofeng
 * @version 1.0
 * @date Sep 27, 2011
 */
public interface ExcelRowProcessor {
    void processByRow() throws Exception;

    void processByRow(int sheetIndex) throws Exception;

    void processRow(XRow row);

    void stop() throws IOException;
}
