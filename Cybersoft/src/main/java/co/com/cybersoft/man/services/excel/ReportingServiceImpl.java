package co.com.cybersoft.man.services.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

import co.com.cybersoft.util.CyberUtils;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class ReportingServiceImpl implements ReportingService {

	@Autowired
	private MongoOperations mongo;
	
	@Override
	public String toExcel(String className) throws Exception {
		Class<?> forName = Class.forName(className);
		DBCollection collection = mongo.getCollection(toLowerCamelCase(forName.getSimpleName()));
		return generateExcel(collection.find(), forName);
	}
	
	private String toLowerCamelCase(String name){
		Character character= name.charAt(0);
		return character.toString().toLowerCase()+name.substring(1);
	}
	
	private String generateExcel(DBCursor cursor, Class<?> _class) throws IOException{
		//Generation of document and title
		Workbook wb=new HSSFWorkbook();
		Sheet sheet = wb.createSheet(_class.getSimpleName());
		PrintSetup printSetup = sheet.getPrintSetup();
		printSetup.setLandscape(true);
		sheet.setHorizontallyCenter(true);
		
		Map<String, CellStyle> styles = createStyles(wb);
		
		Row titleRow = sheet.createRow(0);
		titleRow.setHeightInPoints(45);
		Cell titleCell = titleRow.createCell(0);
		titleCell.setCellValue(_class.getSimpleName());
		titleCell.setCellStyle(styles.get("title"));
		sheet.setColumnWidth(0, 30*256);
		
		//Generation of column headers
		Row headerRow = sheet.createRow(1);
		sheet.setColumnWidth(1, 10*256);
		headerRow.setHeightInPoints(40);
		Cell headerCell;
		Field[] fields = _class.getDeclaredFields();
		int j=0;
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			if (!field.getName().equals(CyberUtils.idField)){
				headerCell = headerRow.createCell(j);
				headerCell.setCellValue(fields[i].getName());
				headerCell.setCellStyle(styles.get("header"));
				j++;
			}
		}
		
		int rownum=2;
		while (cursor.hasNext()) {
			DBObject next = cursor.next();
			sheet.setColumnWidth(rownum, 15*256);
			Row row = sheet.createRow(rownum);
			int k=0;
			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				if (!field.getName().equals(CyberUtils.idField)){
					Cell cell = row.createCell(k);
					Object object = next.get(toLowerCamelCase(fields[i].getName()));
					if (object!=null){
						if (object instanceof String)
							cell.setCellValue((String) object);
						else if (object instanceof Double ){
							cell.setCellValue((double) object);
						}
						else if (object instanceof Integer){
							Integer number=(Integer) object;
							cell.setCellValue(number.doubleValue());
							
						}
						else if (object instanceof Long){
							Long number=(Long) object;
							cell.setCellValue(number.doubleValue());
						}
						else if (object instanceof Boolean){
							Boolean bool=(Boolean) object;
							cell.setCellValue(bool.toString());
						}
						else
							cell.setCellValue((Date) object);
					}
					else{
						cell.setCellValue("");
					}
//					cell.setCellStyle(styles.get("cell"));
					k++;
				}
			}
			rownum++;
		}
		
		//Write the file
		UUID uuid = UUID.randomUUID();
		String fileName=_class.getSimpleName()+"-"+uuid.toString()+".xls";
		File directory = new File(CyberUtils.excelFilePath);
		File excel = new File(directory,fileName);
		FileOutputStream outputStream = new FileOutputStream(excel);
		wb.write(outputStream);
		outputStream.close();
		
		return "redirect:"+CyberUtils.excelURLPath+"/"+fileName;
	}
	
	
	
	private Map<String, CellStyle> createStyles(Workbook wb){
		Map<String, CellStyle> styles = new HashMap<String, CellStyle>();
        CellStyle style;
        Font titleFont = wb.createFont();
        titleFont.setFontHeightInPoints((short)18);
        titleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        style = wb.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        style.setFont(titleFont);
        styles.put("title", style);

        Font monthFont = wb.createFont();
        monthFont.setFontHeightInPoints((short)11);
        monthFont.setColor(IndexedColors.WHITE.getIndex());
        style = wb.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setFont(monthFont);
        style.setWrapText(true);
        styles.put("header", style);

        style = wb.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setWrapText(true);
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderTop(CellStyle.BORDER_THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        styles.put("cell", style);

        return styles;
	}
}
