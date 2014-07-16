package co.com.cybersoft.generator.code.model;

import java.util.ArrayList;
import java.util.List;

import co.com.cybersoft.generator.code.util.CodeUtil;

public class Document {

	private String name;
	
	private String spanishName;
	
	private List<Field> header;
	
	private List<Field> body;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpanishName() {
		return spanishName;
	}

	public void setSpanishName(String spanishName) {
		this.spanishName = spanishName;
	}

	public List<Field> getHeader() {
		return header;
	}

	public void setHeader(List<Field> header) {
		this.header = header;
	}

	public List<Field> getBody() {
		return body;
	}

	public void setBody(List<Field> body) {
		this.body = body;
	}
	
	public boolean hasCompoundIndex(){
		for (Field field:header) {
			if (field.getKeyCompound())
				return true;
		}
		return false;
	}
	
	public boolean hasCompoundReference(){
		for (Field field: header) {
			if (field.getCompoundReference())
				return true;
		}
		return false;
	}
	
	public List<Field> getCompositeFields(){
		ArrayList<Field> list = new ArrayList<Field>();
		for (Field field: header) {
			if (field.getCompoundReference())
				list.add(field);
		}
		return list;
	}
	
	public List<Field> getCompoundIndex(Cybertables cybertables){
		ArrayList<Field> compoundIndex = new ArrayList<Field>();
		for (Field field:header) {
			if (field.getKeyCompound()&&!field.getCompoundReference())
				compoundIndex.add(field);
			if (field.getKeyCompound()&&field.getCompoundReference())
				compoundIndex.addAll(CodeUtil.getCompoundKey(cybertables, field.getRefType()));
		}
		return compoundIndex;
	}
	
	
}
