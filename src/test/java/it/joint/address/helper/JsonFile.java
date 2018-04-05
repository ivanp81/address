package it.joint.address.helper;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class JsonFile {
    
	private String json;
	
	public String asString() {
		return this.json;
	}
	
	public static class Builder {
		
		private File file;
		
		public Builder() {

        }
		
		public Builder fromPath(String filePath) throws IOException {
	        file = ResourceUtils.getFile(filePath);
	        return this;
	    }
		
		public JsonFile build() throws IOException {
			
			JsonFile json = new JsonFile();
			json.json = new String(Files.readAllBytes(file.toPath()));
			
			return json;
	    }
	}
}
