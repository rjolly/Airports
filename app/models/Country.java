package models;

import java.io.Serializable;
import com.opencsv.bean.CsvBindByName;

public class Country implements Serializable {
	@CsvBindByName private int id;
	@CsvBindByName private String code;
	@CsvBindByName private String name;
	@CsvBindByName private String continent;
	@CsvBindByName private String wikipedia_link;
	@CsvBindByName private String keywords;
	
	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(final String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getContinent() {
		return continent;
	}

	public void setContinent(final String continent) {
		this.continent = continent;
	}

	public String getWikipedia_link() {
		return wikipedia_link;
	}

	public void setWikipedia_link(final String wikipedia_link) {
		this.wikipedia_link = wikipedia_link;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(final String keywords) {
		this.keywords = keywords;
	}

	@Override
	public String toString() {
		return name;
	}
}
