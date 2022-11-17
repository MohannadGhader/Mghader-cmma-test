package com.path.lib.common.util;

import java.io.IOException;
import java.math.BigDecimal;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.deser.std.StdDeserializer;

import com.path.bo.common.ConstantsCommon;

public class BigDecimalDeserializer extends StdDeserializer<BigDecimal>
{

    public BigDecimalDeserializer()
    {
	super(BigDecimal.class);
    }

    @Override
    public BigDecimal deserialize(JsonParser jp, DeserializationContext ctxt)
	    throws IOException, JsonProcessingException
    {
	JsonNode node = jp.getCodec().readTree(jp);
	String strValue = node.asText();
	BigDecimal theValue = null;
	if(strValue != null)
	{
	    if("".equals(strValue))
	    {
		theValue = ConstantsCommon.EMPTY_BIGDECIMAL_VALUE;
	    }
	    else
	    {
		theValue = new BigDecimal(strValue);
	    }
	}

	return theValue;
    }
}