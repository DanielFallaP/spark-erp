package co.com.cybersoft.generator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LabelGenerator {

	public static final String viewsAbsolutePath="C:\\Users\\daniel\\git\\co.com.cybersoft\\Cybersoft\\src\\main\\webapp\\WEB-INF\\views";
	
	public static Connection con;
	
	public static PreparedStatement pst;
	
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://cybersoft.c6g4nh1b2apj.us-east-1.rds.amazonaws.com/cybersoft?"
					+ "user=cybersoft&password=petronube");
			pst = con.prepareStatement("insert into cybersoft.dictionary (message) values (?)");
			
			File rootDirectory = new File(viewsAbsolutePath);

			LabelGenerator.insertLabels(rootDirectory);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method inserts all labels contained in all the html pages
	 * in the given view directory into the label database 
	 * for further generation of message resources
	 * @param directory
	 * @throws FileNotFoundException 
	 */
	public static void insertLabels(File directory) throws Exception{
		File[] files = directory.listFiles();
		List<File> filesList = Arrays.asList(files);
		for (File file : filesList) {
			if (!file.isDirectory() && file.getName().endsWith(".html")){
				insertFileLabels(file);
			}
			else{
				insertLabels(file);
			}
		}
	}
	
	public static void insertFileLabels(File htmlFile) throws Exception{
		System.out.println("====================Inserting labels in directory: "+htmlFile.getName());
		LabelGenerator.insertMessages(LabelGenerator.getFileLabels(htmlFile));
	}
	
	public static void insertMessages(List<String> messages){
		for (String message : messages) {
			try {
//				System.out.println(message);
				pst.setString(1, message);
				pst.execute();
			} catch (Exception e) {
				//Just continue with the next message if this one was previously inserted
				continue;
			}
		}
	}
	
	public static List<String> getFileLabels(File htmlFile) throws Exception{
		ArrayList<String> labelList = new ArrayList<String>();
		FileReader fileReader = new FileReader(htmlFile);
		BufferedReader reader = new BufferedReader(fileReader);
		String line = reader.readLine();
		while (line!=null){
			labelList.addAll(getLineLabels(line));
			line=reader.readLine();
		}
		reader.close();
		return labelList;
	}
	
	public static List<String> getLineLabels(String line){
		ArrayList<String> list = new ArrayList<String>();
		while (line.lastIndexOf("#{label.")!=-1){
			String substring = line.substring(line.lastIndexOf("#{label.")+8,line.lastIndexOf("#{label.")+8+LabelGenerator.getLabelLength(line));
			list.add(substring);
			line = line.substring(0,line.lastIndexOf("#{label."));
		}
		return list;
	}
	
	public static int getLabelLength(String line){
		int indexOf = line.lastIndexOf("#{label.")+"#{label.".length()+1;
		int length=1;
		for (; indexOf < line.length(); indexOf++) {
			if (line.charAt(indexOf)!='}'){
				length++;
			}
			else{
				break;
			}
		}
		return length;
	}
}
