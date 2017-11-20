package models;

import java.io.Serializable;
import com.opencsv.bean.CsvBindByName;

public class Runway implements Serializable {
	@CsvBindByName private int id;
	@CsvBindByName private int airport_ref;
	@CsvBindByName private String airport_ident;
	@CsvBindByName private int length_ft;
	@CsvBindByName private int width_ft;
	@CsvBindByName private String surface;
	@CsvBindByName private boolean lighted;
	@CsvBindByName private boolean closed;
	@CsvBindByName private String le_ident;
	@CsvBindByName private float le_latitude_deg;
	@CsvBindByName private float le_longitude_deg;
	@CsvBindByName private int le_elevation_ft;
	@CsvBindByName private float le_heading_degT;
	@CsvBindByName private int le_displaced_threshold_ft;
	@CsvBindByName private String he_ident;
	@CsvBindByName private float he_latitude_deg;
	@CsvBindByName private float he_longitude_deg;
	@CsvBindByName private int he_elevation_ft;
	@CsvBindByName private float he_heading_degT;
	@CsvBindByName private int he_displaced_threshold_ft;
	@CsvBindByName private int empty;

	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public int getAirport_ref() {
		return airport_ref;
	}

	public void setAirport_ref(final int airport_ref) {
		this.airport_ref = airport_ref;
	}

	public String getAirport_ident() {
		return airport_ident;
	}

	public void setAirport_ident(final String airport_ident) {
		this.airport_ident = airport_ident;
	}

	public int getLength_ft() {
		return length_ft;
	}

	public void setLength_ft(final int length_ft) {
		this.length_ft = length_ft;
	}

	public int getWidth_ft() {
		return width_ft;
	}

	public void setWidth_ft(final int width_ft) {
		this.width_ft = width_ft;
	}

	public String getSurface() {
		return surface;
	}

	public void setSurface(final String surface) {
		this.surface = surface;
	}

	public boolean getLighted() {
		return lighted;
	}

	public void setLighted(final boolean lighted) {
		this.lighted = lighted;
	}

	public boolean getClosed() {
		return closed;
	}

	public void setClosed(final boolean closed) {
		this.closed = closed;
	}

	public String getLe_ident() {
		return le_ident;
	}

	public void setLe_ident(final String le_ident) {
		this.le_ident = le_ident;
	}

	public float getLe_latitude_deg() {
		return le_latitude_deg;
	}

	public void setLe_latitude_deg(final float le_latitude_deg) {
		this.le_latitude_deg = le_latitude_deg;
	}

	public float getLe_longitude_deg() {
		return le_longitude_deg;
	}

	public void setLe_longitude_deg(final float le_longitude_deg) {
		this.le_longitude_deg = le_longitude_deg;
	}

	public int getLe_elevation_ft() {
		return le_elevation_ft;
	}

	public void setLe_elevation_ft(final int le_elevation_ft) {
		this.le_elevation_ft = le_elevation_ft;
	}

	public float getLe_heading_degT() {
		return le_heading_degT;
	}

	public void setLe_heading_degT(final float le_heading_degT) {
		this.le_heading_degT = le_heading_degT;
	}

	public int getLe_displaced_threshold_ft() {
		return le_displaced_threshold_ft;
	}

	public void setLe_displaced_threshold_ft(final int le_displaced_threshold_ft) {
		this.le_displaced_threshold_ft = le_displaced_threshold_ft;
	}

	public String getHe_ident() {
		return he_ident;
	}

	public void setHe_ident(final String he_ident) {
		this.he_ident = he_ident;
	}

	public float getHe_latitude_deg() {
		return he_latitude_deg;
	}

	public void setHe_latitude_deg(final float he_latitude_deg) {
		this.he_latitude_deg = he_latitude_deg;
	}

	public float getHe_longitude_deg() {
		return he_longitude_deg;
	}

	public void setHe_longitude_deg(final float he_longitude_deg) {
		this.he_longitude_deg = he_longitude_deg;
	}

	public int getHe_elevation_ft() {
		return he_elevation_ft;
	}

	public void setHe_elevation_ft(final int he_elevation_ft) {
		this.he_elevation_ft = he_elevation_ft;
	}

	public float getHe_heading_degT() {
		return he_heading_degT;
	}

	public void setHe_heading_degT(final float he_heading_degT) {
		this.he_heading_degT = he_heading_degT;
	}

	public int getHe_displaced_threshold_ft() {
		return he_displaced_threshold_ft;
	}

	public void setHe_displaced_threshold_ft(final int he_displaced_threshold_ft) {
		this.he_displaced_threshold_ft = he_displaced_threshold_ft;
	}

	public int getEmpty() {
		return empty;
	}

	public void setEmpty(final int empty) {
		this.empty = empty;
	}

	@Override
	public String toString() {
		return le_ident;
	}
}
