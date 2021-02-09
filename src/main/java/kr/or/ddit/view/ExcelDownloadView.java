package kr.or.ddit.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.servlet.view.AbstractView;

import kr.or.ddit.user.model.UserVo;

public class ExcelDownloadView extends AbstractView{

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		response.setContentType("application/vnd.ms-excel; charset=UTF-8");
		response.setHeader("Content-Disposition", "Attachement; filename=test.xlsx");
		
		//header : List<String>
		//data : List<UserVo>
		List<String> header = (List<String>) model.get("header");
		List<UserVo> data = (List<UserVo>) model.get("data");
		
		//excel 파일 설정
		XSSFWorkbook book = new XSSFWorkbook();
		Sheet sheet = book.createSheet("users");
		
		sheet.setColumnWidth(0, 4000);
		sheet.setColumnWidth(1, 4000);
		sheet.setColumnWidth(2, 4000);
		
		int rownum = 0;
		int colnum = 0;
		
		Row row = sheet.createRow(rownum++);
		
		for(String h : header) {
			Cell cell = row.createCell(colnum++);
			cell.setCellValue(h);
		}
		
		//data는 나중에
		
		for(UserVo d : data) {
			colnum = 0;
			row = sheet.createRow(rownum++);
			row.createCell(colnum++).setCellValue(d.getUserid());
			row.createCell(colnum++).setCellValue(d.getUsernm());
			row.createCell(colnum++).setCellValue(d.getAlias());
		}
		
		book.write(response.getOutputStream());
		
	}

}
