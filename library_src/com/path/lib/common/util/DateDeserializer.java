package com.path.lib.common.util;

import java.io.IOException;
import java.util.Date;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.deser.std.StdDeserializer;

import com.path.lib.common.exception.BaseException;

public class DateDeserializer extends StdDeserializer<Date> { 
 
    public DateDeserializer() { 
        super(Date.class); 
    }
 
    @Override
    public Date deserialize(JsonParser jp, DeserializationContext ctxt) 
      throws IOException, JsonProcessingException {
        Date formatedDate= new Date();
        try
        {
            return _parseDate(jp, ctxt);
        }
        catch(Exception ex)
        {
            JsonNode node = jp.getCodec().readTree(jp);
            
            String datePattern = DateUtil.getDatePattern(node.getTextValue());
            if(StringUtil.isNotEmpty(datePattern))
        	{
        	try
		{
		    formatedDate =  DateUtil.parseDate(node.getTextValue(), datePattern);
		}
		catch(BaseException e)
		{
		    return null;
		}
        	}
            else
            {
        	return null;
            }
        }
        return formatedDate;
    }
}