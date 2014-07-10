package co.com.cybersoft.generator.code.model;

import java.util.List;

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
	
	
	
}
