package co.com.cybersoft.generator.resources;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * This program connects to the given database and queries all the messages
 * in the table 'dictionary'. Then, for each language column, it creates a
 * resource file called 'messages_*.properties' in the specified resource path.
 * @author daniel
 *
 */
public class ResourceGenerator {

	public static final String resourcePath="C:\\Users\\daniel\\";
	
	public static void main(String[] args) {

		HashMap<String, Integer> filesMap = new HashMap<String, Integer>();
		filesMap.put("en", 1);
		filesMap.put("es", 2);
//		filesMap.put("pt", 5);
//		filesMap.put("de", 6);
//		filesMap.put("fr", 7);
//		filesMap.put("it", 8);
//		filesMap.put("zh", 9);
//		filesMap.put("ko", 10);
//		filesMap.put("ja", 11);
		
		Connection con=null;
		ResultSet resultSet =null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con=DriverManager.getConnection("jdbc:sqlserver://local.winsoftware.com.co:3030;databaseName=AMMessages"
					+ ";user=AM4G;password=AM2001AM05");
			
			Statement statement = con.createStatement();
			resultSet = statement.executeQuery("select * from dbo.dictionary");
			
			Map<String, BufferedWriter> files = ResourceGenerator.prepareFiles(filesMap);
			
			while (resultSet.next()){
				Set<String> keySet = files.keySet();
				for (String language : keySet) {
					String line;
					if (resultSet.getString(filesMap.get(language))==null)
						line="label."+resultSet.getString(4)+"="+"label."+resultSet.getString(4)+'\n';
					else
						line="label."+resultSet.getString(4)+"="+resultSet.getString(filesMap.get(language))+'\n';
					files.get(language).write(line);
				}
			}
			
			ResourceGenerator.closeFiles(files);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			
				try {
					if (resultSet!=null){
						resultSet.close();
					}
					if (con!=null){
					con.close();
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Finished creating message resources!!!");
			}
		
	}

	/**
	 * Creates a buffered writer for each language
	 * @param filesMap
	 * @return
	 * @throws IOException
	 */
	public static Map<String, BufferedWriter> prepareFiles(Map<String,Integer> filesMap) throws IOException{
		HashMap<String, BufferedWriter> result = new HashMap<String, BufferedWriter>();
		Set<String> set = filesMap.keySet();
		for (String string : set) {
			File file = new File("messages_"+string+".properties");
			if (file.exists())
				file.delete();
			if (!file.exists()){
				file.createNewFile();
			}
			
			FileOutputStream outputStream = new FileOutputStream(file.getAbsoluteFile());
			OutputStreamWriter streamWriter = new OutputStreamWriter(outputStream,"UTF-8");
			BufferedWriter bw = new BufferedWriter(streamWriter);
			result.put(string, bw);
		}
		return result;
	}
	
	/**
	 * Closes all files used in the process
	 * @param filesMap
	 * @throws IOException
	 */
	public static void closeFiles(Map<String,BufferedWriter> filesMap) throws IOException{
		Collection<BufferedWriter> values = filesMap.values();
		for (BufferedWriter bufferedWriter : values) {
			bufferedWriter.close();
		}
	}
}
