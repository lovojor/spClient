/**
 * 
 */
package com.oncedev.model;

/**
 * 
 * @author lovojor
 *
 */
public class PairData {
	
	private String code;
	private String value;
	
	/**
	 * Create Object with Lat and Long
	 * 
	 * @param latitude
	 * @param longitud
	 */
	public PairData(String latitude, String longitud) {
		super();
		this.code = latitude;
		this.value = longitud;
	}

	/**
	 * @return the latitude
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param latitude the latitude to set
	 */
	public void setCode(String latitude) {
		this.code = latitude;
	}

	/**
	 * @return the longitud
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param longitud the longitud to set
	 */
	public void setValue(String longitud) {
		this.value = longitud;
	}
}