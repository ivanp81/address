package it.joint.address.provider;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressResponse implements Serializable{

	private Double latitude;
	private Double longitude;
	private List<String> addresses;

	public AddressResponse() {
		super();
	}
 
	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public List<String> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<String> addresses) {
		this.addresses = addresses;
	}

	public boolean equals(Object o) {

		if (o == null)
			return false;
		if (!(o instanceof AddressResponse))
			return false;

		AddressResponse other = (AddressResponse) o;
		
		if (!this.latitude.equals(other.latitude))
			return false;
		if (!this.longitude.equals(other.longitude))
			return false;

		return true;
	}

	public int hashCode() {
		return latitude.hashCode() * longitude.hashCode();
	}
	
	public String toJsonString() throws Exception {    	
    	return new ObjectMapper().writeValueAsString(this);
    }
	
    public static class Builder {

    	private Double latitude;
    	private Double longitude;
    	private List<String> addresses;

        public Builder() {

        }

        public Builder withLatitude(Double latitude){
            this.latitude = latitude;
            return this; 
        }

        public Builder withLongitude(Double longitude){
            this.longitude = longitude;
            return this; 
        }
        
        public Builder withAddresses(List<String> addresses){
            this.addresses = addresses;
            return this; 
        }

        public AddressResponse build(){

        	AddressResponse addressResponse = new AddressResponse(); 
        	addressResponse.latitude = this.latitude;
        	addressResponse.longitude = this.longitude;
    		addressResponse.addresses = this.addresses;
    		
            return addressResponse;

        }
    }
}