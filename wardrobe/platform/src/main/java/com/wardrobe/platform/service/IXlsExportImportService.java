package com.wardrobe.platform.service;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/1/22.
 */
public interface IXlsExportImportService {

    Workbook getWorkbook(InputStream fin, String fileName);

    List<String> getExcelTitle(Sheet sheet, int row);

    void xlsExport(List<Map<String, Object>> dataList, Sheet sheet);

    void writeWorkbook(List<Map<String, Object>> dataList, Sheet sheet, int excelTitleIndex, int createStartRowIndex, boolean deleteFirstRow);

    List<Map<String, Object>> readExcelData(Sheet sheet);

}
