package co.com.cybersoft.maintenance.tables.core.services.responsibleCenter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import javax.persistence.Query;
import co.com.cybersoft.man.services.excel.*;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import co.com.cybersoft.util.EmbeddedField;
import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.maintenance.tables.core.services.responsibleCenter.ResponsibleCenterService;
import co.com.cybersoft.maintenance.tables.events.responsibleCenter.ResponsibleCenterPageEvent;
import co.com.cybersoft.maintenance.tables.events.responsibleCenter.RequestResponsibleCenterPageEvent;
import co.com.cybersoft.maintenance.tables.web.domain.responsibleCenter.ResponsibleCenterFilterInfo;
import co.com.cybersoft.util.CyberUtils;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class ResponsibleCenterReportingServiceImpl implements ResponsibleCenterReportingService {
	@Autowired
	private LocalContainerEntityManagerFactoryBean emFactory;

	@Autowired
	private ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource;
	
	@Autowired
	private ResponsibleCenterService responsibleCenter;
	
	public ExcelReportResponse toExcel(String className, String detailsClassName, Locale locale, ResponsibleCenterFilterInfo filter) throws Exception {
		Class<?> forName = Class.forName(className);
		Class<?> forNameDetails = Class.forName(detailsClassName);
		
		PageRequest pageRequest = new PageRequest(0, 100, Direction.DESC,"id");
		RequestResponsibleCenterPageEvent pageEvent=new RequestResponsibleCenterPageEvent(pageRequest,filter);			

		ResponsibleCenterPageEvent requestResponsibleCenterFilter = responsibleCenter.requestResponsibleCenterFilter(pageEvent);
		
		return generateExcel(requestResponsibleCenterFilter.getAllList(), forName, forNameDetails, locale);
	}	
	

	private String toLowerCamelCase(String name){
		Character character= name.charAt(0);
		return character.toString().toLowerCase()+name.substring(1);
	}
	
	private String toUpperCamelCase(String name){
		Character character= name.charAt(0);
		return character.toString().toUpperCase()+name.substring(1);
	}
	
	private void generateHeaderRow(List<Field> headRow, List<Object> values, Sheet sheet, int headerRowNumber, Map<String, CellStyle> styles, Class<?> _class, Locale locale) {
		Row headerRow = sheet.createRow(headerRowNumber);
		sheet.setColumnWidth(1, 30*256);
		int i=0;
		int j=0;
		for (Field field : headRow) {
			String fieldName=toLowerCamelCase(_class.getSimpleName())+toUpperCamelCase(field.getName());
			String label=reloadableResourceBundleMessageSource.getMessage(CyberUtils.messageResourcePrefix+fieldName,null,toLowerCamelCase(_class.getSimpleName())+toUpperCamelCase(field.getName()),locale);
			Object value=values.get(j);
			Cell labelCell = headerRow.createCell(i);
			labelCell.setCellStyle(styles.get("header"));
			labelCell.setCellValue(label);
			i++;
			Cell valueCell = headerRow.createCell(i);
			if (value!=null){
				if (value instanceof String){
					valueCell.setCellValue((String) value);
					valueCell.setCellStyle(styles.get("cell"));
				}
				else if (value instanceof Double ){
					valueCell.setCellValue((Double) value);
					valueCell.setCellStyle(styles.get("cell"));
				}
				else if (value instanceof Integer){
					Integer number=(Integer) value;
					valueCell.setCellValue(number.doubleValue());
					valueCell.setCellStyle(styles.get("cell"));
					
				}
				else if (value instanceof Long){
					Long number=(Long) value;
					valueCell.setCellValue(number.doubleValue());
					valueCell.setCellStyle(styles.get("cell"));
				}
				else if (value instanceof Boolean){
					Boolean bool=(Boolean) value;
					valueCell.setCellValue(bool.toString());
					valueCell.setCellStyle(styles.get("cell"));
				}
				else if (value instanceof Date){
					valueCell.setCellValue(((Date) value));
					valueCell.setCellStyle(styles.get("dateCell"));
				}
			}
			else{
				valueCell.setCellValue("");
			}
			i++;
			j++;
		}
	}

	private ExcelReportResponse generateExcel(List<?> cursor, Class<?> _class, Class<?> detailsClass, Locale locale) throws Exception{
		
		
		//Generation of document and title
		Workbook wb=new HSSFWorkbook();
		Sheet sheet = wb.createSheet(reloadableResourceBundleMessageSource.getMessage(CyberUtils.messageResourcePrefix+toLowerCamelCase(_class.getSimpleName())+"Info",null,toLowerCamelCase(_class.getSimpleName()),locale));
		PrintSetup printSetup = sheet.getPrintSetup();
		printSetup.setLandscape(true);
		sheet.setHorizontallyCenter(true);
		
		Map<String, CellStyle> styles = createStyles(wb);
		
		Row titleRow = sheet.createRow(0);
		titleRow.setHeightInPoints(45);
		Cell titleCell = titleRow.createCell(0);
		titleCell.setCellValue(reloadableResourceBundleMessageSource.getMessage(CyberUtils.messageResourcePrefix+toLowerCamelCase(_class.getSimpleName())+"Info",null,toLowerCamelCase(_class.getSimpleName()),locale));
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
			if (!field.getName().equals(CyberUtils.tableIdField)){
				headerCell = headerRow.createCell(j);
				String fieldName="";
				
				//Special labels for audit fields
				if (field.getName().equals(CyberUtils.defaultCreatingUser))
					fieldName=CyberUtils.defaultCreatingUser;
				else if(field.getName().equals(CyberUtils.defaultCreationDateName))
					fieldName=CyberUtils.defaultCreationDateName;
				else if (field.getName().equals(CyberUtils.defaultModificationDateName))
					fieldName=CyberUtils.defaultModificationDateName;
				else if (field.getName().equals(CyberUtils.defaultModifyingUser))
					fieldName="userOfModification";
				else
					fieldName=toLowerCamelCase(_class.getSimpleName())+toUpperCamelCase(field.getName());
				headerCell.setCellValue(reloadableResourceBundleMessageSource.getMessage(CyberUtils.messageResourcePrefix+fieldName,null,toLowerCamelCase(_class.getSimpleName())+toUpperCamelCase(fields[i].getName()),locale));
				headerCell.setCellStyle(styles.get("header"));
				j++;
			}
		}
		
		int rownum=2;
		for (Object next : cursor) {
			sheet.setColumnWidth(rownum, 15*256);
			Row row = sheet.createRow(rownum);
			Object details = detailsClass.newInstance();
			Method toDetailsMethod=detailsClass.getDeclaredMethod("to"+toUpperCamelCase(_class.getSimpleName())+"Details", _class, EmbeddedField[].class);
			details=toDetailsMethod.invoke(details, next, new EmbeddedField[0]);
			int k=0;
			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				if (!field.getName().equals(CyberUtils.tableIdField)){
					Cell cell = row.createCell(k);
					Method declaredMethod = detailsClass.getDeclaredMethod("get"+toUpperCamelCase(field.getName()));
					Object object=declaredMethod.invoke(details);
					if (object!=null){
						if (object instanceof String){
							cell.setCellValue((String) object);
							cell.setCellStyle(styles.get("cell"));
						}
						else if (object instanceof Double ){
							cell.setCellValue((Double) object);
							cell.setCellStyle(styles.get("cell"));
						}
						else if (object instanceof Integer){
							Integer number=(Integer) object;
							cell.setCellValue(number.doubleValue());
							cell.setCellStyle(styles.get("cell"));
							
						}
						else if (object instanceof Long){
							Long number=(Long) object;
							cell.setCellValue(number.doubleValue());
							cell.setCellStyle(styles.get("cell"));
						}
						else if (object instanceof Boolean){
							Boolean bool=(Boolean) object;
							cell.setCellValue(bool.toString());
							cell.setCellStyle(styles.get("cell"));
						}
						else if (object instanceof Date){
							cell.setCellValue(((Date) object));
							cell.setCellStyle(styles.get("dateCell"));
						}
						else{
							cell.setCellValue("Reference");
							cell.setCellStyle(styles.get("cell"));
						}
					}
					else{
						cell.setCellValue("");
					}
					k++;
				}
			}
			rownum++;
		}
		
		//Write the file
		UUID uuid = UUID.randomUUID();
		String fileName=_class.getSimpleName()+"-"+uuid.toString()+".xls";
		URL url = getClass().getClassLoader().getResource("db.json");
		File directory=new File(new File(url.getPath()).getParentFile().getParentFile().getParentFile().getPath()+CyberUtils.excelFilePath);
		
		File excel = new File(directory,fileName);
		FileOutputStream outputStream = new FileOutputStream(excel);
		wb.write(outputStream);
		outputStream.close();
		
		ExcelReportResponse response = new ExcelReportResponse();
		response.setUrl(CyberUtils.excelURLPath+"/"+fileName);
		
		return response;
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
        
        DataFormat df = wb.createDataFormat();
        
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
        style.setDataFormat(df.getFormat("dd/mm/yyyy"));
        styles.put("dateCell", style);


        
        return styles;
	}

}