package com.path.ws.omni.vo.common;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.codehaus.jackson.JsonProcessingException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class CustomLocalDateTimeSerializer extends StdSerializer<LocalDateTime>{
	
	 private static DateTimeFormatter formatter = 
		      DateTimeFormatter.ofPattern("yyyy-MM-dd");

		    public CustomLocalDateTimeSerializer() {
		        this(null);
		    }
		 
		    public CustomLocalDateTimeSerializer(Class<LocalDateTime> t) {
		        super(t);
		    }
		    
		    @Override
		    public void serialize(
		      LocalDateTime value,
		      JsonGenerator gen,
		      SerializerProvider arg2)
		      throws IOException, JsonProcessingException {
		 
		        gen.writeString(formatter.format(value));
		    }

}
