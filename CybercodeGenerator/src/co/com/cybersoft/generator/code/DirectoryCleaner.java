package co.com.cybersoft.generator.code;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;

import co.com.cybersoft.generator.code.model.Cybersoft;
import co.com.cybersoft.generator.code.util.CodeUtil;

public class DirectoryCleaner {

	private final Cybersoft cybersoft;
	
	private final List<String> packagedirectories=new ArrayList<String>(Arrays.asList("src/main/java/co/com/cybersoft/core/services",
																			   "src/main/java/co/com/cybersoft/events", 
																			   "src/main/java/co/com/cybersoft/persistence/services",
																			   "src/main/java/co/com/cybersoft/persistence/repository",
																			   "src/main/java/co/com/cybersoft/web/controller",
																			   "src/main/java/co/com/cybersoft/web/domain",
																			   "src/main/webapp/WEB-INF/views/normal/configuration"));
	
	private final List<String> fileDirectories=new ArrayList<String>(Arrays.asList("src/main/java/co/com/cybersoft/core/domain",
																			       "src/main/java/co/com/cybersoft/persistence/domain"));
			
	public DirectoryCleaner(Cybersoft cybersoft){
		this.cybersoft=cybersoft;
	}
	
	public void clean() throws IOException{
		for(String directory:packagedirectories){
			File dir= new File("../"+Cybersoft.projectName+"/"+directory);
			File[] packages = dir.listFiles();
			for (int i = 0; i < packages.length; i++) {
				File pack = packages[i];
				cleanPackage(pack);
			}
		}
		
		for(String directory:fileDirectories){
			File dir = new File("../"+Cybersoft.projectName+"/"+directory);
			File[] files = dir.listFiles();
			for (int i = 0; i < files.length; i++) {
				File file = files[i];
				cleanFile(file);
			}
		}
		
	}
	
	public void initialClean() throws IOException{
		File buildDir = new File (Cybersoft.targetClassPath.split("/")[0]);
		File[] files = buildDir.listFiles();
		for (File file : files) {
			FileUtils.deleteDirectory(file);
		}
	}
	
	private void cleanPackage(File pack) throws IOException{
		if (!CodeUtil.containsTable(cybersoft, pack.getName()) && pack.isDirectory())
			FileUtils.deleteDirectory(pack);
	}
	
	private void cleanFile(File file) throws IOException{
		if (!CodeUtil.isGeneratedFile(cybersoft, file.getName())){
			file.delete();
		}
	}
	
}
