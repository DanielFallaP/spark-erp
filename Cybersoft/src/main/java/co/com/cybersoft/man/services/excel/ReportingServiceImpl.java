package co.com.cybersoft.man.services.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

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
import org.springframework.data.mongodb.core.MongoOperations;

import co.com.cybersoft.util.CyberUtils;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class ReportingServiceImpl implements ReportingService {

	@Autowired
	private ApplicationContext context;
	
	@Autowired
	private MongoOperations mongo;
	
	@Autowired
	private ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource;
	
	@Override
	public String toExcel(String className, Locale locale) throws Exception {
		Class<?> forName = Class.forName(className);
		DBCollection collection = mongo.getCollection(toLowerCamelCase(forName.getSimpleName()));
		return generateExcel(collection.find(), forName, locale);
	}
	
	@Override
	public String docToExcel(String className, String bodyClassName, Locale locale, Long id) throws Exception {
		Class<?> header = Class.forName(className);
		Class<?> body = Class.forName(bodyClassName);
		DBCollection collection = mongo.getCollection(toLowerCamelCase(header.getSimpleName()));
		BasicDBObject basicObject = new BasicDBObject();
		basicObject.append("_numericId", id);
		File docExcel = generateDocExcel(collection.find(basicObject), header, body, locale, id);
		return "redirect:"+CyberUtils.excelURLPath+"/"+docExcel.getName();
	}
	
	@Override
	public File docToExcelFile(String className, String bodyClassName, Locale locale, Long id) throws Exception {
		Class<?> header = Class.forName(className);
		Class<?> body = Class.forName(bodyClassName);
		DBCollection collection = mongo.getCollection(toLowerCamelCase(header.getSimpleName()));
		BasicDBObject basicObject = new BasicDBObject();
		basicObject.append("_numericId", id);
		return generateDocExcel(collection.find(basicObject), header, body, locale, id);
	}

	private String toLowerCamelCase(String name){
		Character character= name.charAt(0);
		return character.toString().toLowerCase()+name.substring(1);
	}
	
	private String toUpperCamelCase(String name){
		Character character= name.charAt(0);
		return character.toString().toUpperCase()+name.substring(1);
	}
	
	private File generateDocExcel(DBCursor cursor, Class<?> _class, Class<?> bodyClass, Locale locale, Long id) throws IOException{
		DBObject dbObject = cursor.next();
		List<BasicDBObject> body = (List<BasicDBObject>) dbObject.get(toLowerCamelCase(_class.getSimpleName())+"BodyEntityList");
		
		//Generation of document and title
		Workbook wb=new HSSFWorkbook();
		Sheet sheet = wb.createSheet(reloadableResourceBundleMessageSource.getMessage(CyberUtils.messageResourcePrefix+toLowerCamelCase(_class.getSimpleName())+"Info",null,toLowerCamelCase(_class.getSimpleName()),locale)+" "+id);
		PrintSetup printSetup = sheet.getPrintSetup();
		printSetup.setLandscape(true);
		sheet.setHorizontallyCenter(true);
		
		Map<String, CellStyle> styles = createStyles(wb);
		Row titleRow = sheet.createRow(0);
		titleRow.setHeightInPoints(45);
		Cell titleCell = titleRow.createCell(0);
		titleCell.setCellValue(reloadableResourceBundleMessageSource.getMessage(CyberUtils.messageResourcePrefix+toLowerCamelCase(_class.getSimpleName())+"Info",null,toLowerCamelCase(_class.getSimpleName()),locale)+" "+id);
		titleCell.setCellStyle(styles.get("title"));
		sheet.setColumnWidth(0, 30*256);
		
		//Generation of column headers
		int headerRowNumber=1;		
		Field[] fields = _class.getDeclaredFields();
		List<Field> headRow = new ArrayList<Field>();
		List<Object> headValues=new ArrayList<>();
		int n=0;
		if (!fields[0].getName().endsWith(CyberUtils.bodyEntityListSuffix) && !fields[0].getName().equals(CyberUtils.docIdField) && !fields[0].getName().equals(CyberUtils.docEnableDeletionField) 
				&& !fields[0].getName().equals(CyberUtils.docNumericId) && !fields[0].getName().equals(CyberUtils.docStringIdField) && !fields[0].getName().equals(CyberUtils.defaultCreatingUser)
				&& !fields[0].getName().equals(CyberUtils.defaultModifyingUser) && !fields[0].getName().equals(CyberUtils.defaultCreationDateName) && !fields[0].getName().equals(CyberUtils.defaultModificationDateName)){
			headRow.add(fields[0]);
			headValues.add(dbObject.get(fields[0].getName()));
			n=1;
		}
		int m=1;
		for (;m<fields.length;m++) {
			if (!fields[m].getName().endsWith(CyberUtils.bodyEntityListSuffix) && !fields[m].getName().equals(CyberUtils.docIdField) && !fields[m].getName().equals(CyberUtils.docEnableDeletionField) 
					&& !fields[m].getName().equals(CyberUtils.docNumericId) && !fields[m].getName().equals(CyberUtils.docStringIdField) && !fields[m].getName().equals(CyberUtils.defaultCreatingUser)
					&& !fields[m].getName().equals(CyberUtils.defaultModifyingUser) && !fields[m].getName().equals(CyberUtils.defaultCreationDateName) && !fields[m].getName().equals(CyberUtils.defaultModificationDateName)){
				if (n%CyberUtils.headerColumnsPerRow!=0){
					headRow.add(fields[m]);
					headValues.add(dbObject.get(fields[m].getName()));
				}
				else{
					generateHeaderRow(headRow, headValues, sheet,headerRowNumber++, styles, _class, locale);
					headRow.clear();
					headValues.clear();
					headRow.add(fields[m]);
					headValues.add(dbObject.get(fields[m].getName()));
				}
				n++;
			}
		}
		if (!headRow.isEmpty())
			generateHeaderRow(headRow, headValues, sheet, headerRowNumber++, styles, _class, locale);
		
		//Generation of body column headers
		headerRowNumber++;
		Row bodyHeaderRow = sheet.createRow(headerRowNumber++);
		sheet.setColumnWidth(1, 30*256);
		bodyHeaderRow.setHeightInPoints(40);
		Cell bodyHeaderCell;
		Field[] bodyFields = bodyClass.getDeclaredFields();
		int j=0;
		for (int i = 0; i < bodyFields.length; i++) {
			Field bodyField = bodyFields[i];
			if (!bodyField.getName().equals(CyberUtils.docIdField)&& !bodyField.getName().equals(CyberUtils.docEnableDeletionField)){
				bodyHeaderCell = bodyHeaderRow.createCell(j);
				String fieldName=toLowerCamelCase(_class.getSimpleName())+toUpperCamelCase(bodyField.getName());
				bodyHeaderCell.setCellValue(reloadableResourceBundleMessageSource.getMessage(CyberUtils.messageResourcePrefix+fieldName,null,toLowerCamelCase(_class.getSimpleName())+toUpperCamelCase(bodyFields[i].getName()),locale));
				bodyHeaderCell.setCellStyle(styles.get("header"));
				j++;
			}
		}
		
		//Generation of body row info
		int rownum=headerRowNumber++;
		for (BasicDBObject bodyRecord: body) {
			
			sheet.setColumnWidth(rownum, 30*256);
			Row row = sheet.createRow(rownum);
			int k=0;
			for (int i = 0; i < bodyFields.length; i++) {
				Field field = bodyFields[i];
				if (!field.getName().equals(CyberUtils.docIdField)&& !field.getName().equals(CyberUtils.docEnableDeletionField)){
					Cell cell = row.createCell(k);
					Object object = bodyRecord.get(toLowerCamelCase(bodyFields[i].getName()));
					if (object!=null){
						if (object instanceof String){
							cell.setCellValue((String) object);
							cell.setCellStyle(styles.get("cell"));
						}
						else if (object instanceof Double ){
							cell.setCellValue((double) object);
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
						else{
							cell.setCellValue(((Date) object));
							cell.setCellStyle(styles.get("dateCell"));
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
		return excel;
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
					valueCell.setCellValue((double) value);
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

	private String generateExcel(DBCursor cursor, Class<?> _class, Locale locale) throws IOException{
		
		
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
		while (cursor.hasNext()) {
			DBObject next = cursor.next();
			sheet.setColumnWidth(rownum, 15*256);
			Row row = sheet.createRow(rownum);
			int k=0;
			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				if (!field.getName().equals(CyberUtils.tableIdField)){
					Cell cell = row.createCell(k);
					Object object = next.get(toLowerCamelCase(fields[i].getName()));
					if (object!=null){
						if (object instanceof String){
							cell.setCellValue((String) object);
							cell.setCellStyle(styles.get("cell"));
						}
						else if (object instanceof Double ){
							cell.setCellValue((double) object);
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
						else{
							cell.setCellValue(((Date) object));
							cell.setCellStyle(styles.get("dateCell"));
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

	@Override
	public void cleanupExcelDirectory() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("==============Directory cleansed");
	}

}
