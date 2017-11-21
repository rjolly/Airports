package models;

import java.io.Serializable;
import com.opencsv.bean.CsvBindByName;

public class Airport implements Serializable {
	@CsvBindByName private int id;
	@CsvBindByName private String ident;
	@CsvBindByName private String type;
	@CsvBindByName private String name;
	@CsvBindByName private double latitude_deg;
	@CsvBindByName private double longitude_deg;
	@CsvBindByName private int elevation_ft;
	@CsvBindByName private String continent;
	@CsvBindByName private String iso_country;
	@CsvBindByName private String iso_region;
	@CsvBindByName private String municipality;
	@CsvBindByName private String scheduled_service;
	@CsvBindByName private String gps_code;
	@CsvBindByName private String iata_code;
	@CsvBindByName private String local_code;
	@CsvBindByName private String home_link;
	@CsvBindByName private String wikipedia_link;
	@CsvBindByName private String keywords;
	
	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public String getIdent() {
		return ident;
	}

	public void setIdent(final String ident) {
		this.ident = ident;
	}

	public String getType() {
		return type;
	}

	public void setType(final String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public double getLatitude_deg() {
		return latitude_deg;
	}

	public void setLatitude_deg(final double latitude_deg) {
		this.latitude_deg = latitude_deg;
	}

	public double getLongitude_deg() {
		return longitude_deg;
	}

	public void setLongitude_deg(final double longitude_deg) {
		this.longitude_deg = longitude_deg;
	}

	public int getElevation_ft() {
		return elevation_ft;
	}

	public void setElevation_ft(final int elevation_ft) {
		this.elevation_ft = elevation_ft;
	}

	public String getContinent() {
		return continent;
	}

	public void setContinent(final String continent) {
		this.continent = continent;
	}

	public String getIso_country() {
		return iso_country;
	}

	public void setIso_country(final String iso_country) {
		this.iso_country = iso_country;
	}

	public String getIso_region() {
		return iso_region;
	}

	public void setIso_region(final String iso_region) {
		this.iso_region = iso_region;
	}

	public String getMunicipality() {
		return municipality;
	}

	public void setMunicipality(final String municipality) {
		this.municipality = municipality;
	}

	public String getScheduled_service() {
		return scheduled_service;
	}

	public void setScheduled_service(final String scheduled_service) {
		this.scheduled_service = scheduled_service;
	}

	public String getGps_code() {
		return gps_code;
	}

	public void setGps_code(final String gps_code) {
		this.gps_code = gps_code;
	}

	public String getIata_code() {
		return iata_code;
	}

	public void setIata_code(final String iata_code) {
		this.iata_code = iata_code;
	}

	public String getLocal_code() {
		return local_code;
	}

	public void setLocal_code(final String local_code) {
		this.local_code = local_code;
	}

	public String getHome_link() {
		return home_link;
	}

	public void setHome_link(final String home_link) {
		this.home_link = home_link;
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
