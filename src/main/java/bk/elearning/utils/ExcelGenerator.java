package bk.elearning.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
 
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import bk.elearning.entity.dto.ExamResultDTO;
 
 
public class ExcelGenerator {
  
  public static ByteArrayInputStream resultToExcel(List<ExamResultDTO> examResultDTOs) throws IOException {
    String[] COLUMNs = {"STT", "Mã Sinh Viên", "Họ Và Tên", "Kết Quả"};
    try(
        Workbook workbook = new XSSFWorkbook();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
    ){
      CreationHelper createHelper = workbook.getCreationHelper();
   
      Sheet sheet = workbook.createSheet("Customers");
   
      Font headerFont = workbook.createFont();
      headerFont.setBold(true);
      headerFont.setColor(IndexedColors.BLUE.getIndex());
   
      CellStyle headerCellStyle = workbook.createCellStyle();
      headerCellStyle.setFont(headerFont);
   
      // Row for Header
      Row headerRow = sheet.createRow(0);
   
      // Header
      for (int col = 0; col < COLUMNs.length; col++) {
        Cell cell = headerRow.createCell(col);
        cell.setCellValue(COLUMNs[col]);
        cell.setCellStyle(headerCellStyle);
      }
   
      // CellStyle for Age
      CellStyle ageCellStyle = workbook.createCellStyle();
      ageCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("#"));
   
      int rowIdx = 1;
      int index=1;
      for (ExamResultDTO result : examResultDTOs) {
        Row row = sheet.createRow(rowIdx++);
   
        row.createCell(0).setCellValue(index);
        row.createCell(1).setCellValue(result.getStudentCode());
        row.createCell(2).setCellValue(result.getStudentName());
        row.createCell(3).setCellValue(String.format("%.02f", result.getStudentGrade()));
        index++;
        
      }
   
      workbook.write(out);
      return new ByteArrayInputStream(out.toByteArray());
    }
  }
}