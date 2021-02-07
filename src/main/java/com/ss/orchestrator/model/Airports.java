/**
 * 
 */
package com.ss.orchestrator.model;

import java.io.Serializable;

/**
 * @author jordandivina
 *
 */

public class Airports implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    private String airportCode;
    
    private String city;
    
    private String name;
    
    
	@Override
	public String toString() {
		return "Airports [airportCode=" + airportCode + ", city=" + city + ", name=" + name + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((airportCode == null) ? 0 : airportCode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Airports other = (Airports) obj;
		if (airportCode == null) {
			if (other.airportCode != null)
				return false;
		} else if (!airportCode.equals(other.airportCode))
			return false;
		return true;
	}

	public String getAirportCode() {
		return airportCode;
	}
	public void setAirportCode(String airportCode) {
		this.airportCode = airportCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
