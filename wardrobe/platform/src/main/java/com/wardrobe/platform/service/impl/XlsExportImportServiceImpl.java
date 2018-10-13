package com.wardrobe.platform.service.impl;

import com.wardrobe.common.constant.IPlatformConstant;
import com.wardrobe.common.util.FileUtil;
import com.wardrobe.common.util.StrUtil;
import com.wardrobe.platform.service.IXlsExportImportService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/1/22.
 */
@Service
public class XlsExportImportServiceImpl implements IXlsExportImportService {

    private final static String FORMULA = "Formula_";

    @Override
    public Workbook getWorkbook(InputStream fin, String fileName) {
        Workbook workbook = null;
        String extension = FileUtil.getFileExtension(fileName);
        try {
            if (IPlatformConstant.excelExtension.equals(extension)) {
                workbook = new HSSFWorkbook(fin);
            } else if (IPlatformConstant.excelExtensionX.equals(extension)) {
                workbook = new XSSFWorkbook(fin);
            }
        } catch (IOException e1) {
            throw new RuntimeException(e1);
        }
        return workbook;
    }

    /**
     * 获取excel的title
     *
     * @param sheet
     * @param row
     * @return
     */
    @Override
    public List<String> getExcelTitle(Sheet sheet, int row) {
        Row excelRow = sheet.getRow(row);
        List<String> titles = new ArrayList<String>();
        for (Cell cell : excelRow) {
            if(cell != null) {
                cell.setCellType(Cell.CELL_TYPE_STRING);
                titles.add(cell.getStringCellValue().trim());
            }
        }
        return titles;
    }

    @Override
    public void xlsExport(List<Map<String, Object>> dataList, Sheet sheet) {
        writeWorkbook(dataList, sheet, 0, 2, true);
    }

    @Override
    public void writeWorkbook(List<Map<String, Object>> dataList, Sheet sheet, int excelTitleIndex, int createStartRowIndex, boolean deleteFirstRow) {
        if (dataList != null && dataList.size() > 0) {
            List<String> excelTitle = getExcelTitle(sheet, excelTitleIndex);
            for (int i = 0; i < dataList.size(); i++) {
                Map<String, Object> map = dataList.get(i);
                Row row = sheet.createRow(i + createStartRowIndex);
                for (int z = 0; z < excelTitle.size(); z++) {
                    Cell excelCell = row.createCell(z);
                    excelCell.setCellValue(StrUtil.trimToEmpty(map.get(excelTitle.get(z))));
                }
            }
        }
        if(deleteFirstRow) {
            sheet.shiftRows(1, sheet.getLastRowNum(), -1);
        }
    }

    @Override
    public List<Map<String, Object>> readExcelData(Sheet sheet){
        List<Map<String, Object>> list = new ArrayList<>();
        List<String> excelTitle = this.getExcelTitle(sheet, 0);
        int lastRowNum = sheet.getLastRowNum();
        for(int i = 2; i <= lastRowNum; i++){
            Row row = sheet.getRow(i);
            if(row == null) continue;
            int lastCellNum = row.getLastCellNum();
            Map<String, Object> map = new HashMap<>();
            for(int j = 0; j < lastCellNum; j++){
                Cell cell = row.getCell(j);
                if(cell == null || j >= excelTitle.size()) continue;
                cell.setCellType(Cell.CELL_TYPE_STRING);
                map.put(excelTitle.get(j), cell.getStringCellValue().trim());
            }
            list.add(map);
        }
        return list;
    }

}
