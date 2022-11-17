package com.path.lib.common.util;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig.Feature;
import org.codehaus.jackson.map.annotate.JsonFilter;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.codehaus.jackson.map.module.SimpleModule;
import org.codehaus.jackson.map.ser.FilterProvider;
import org.codehaus.jackson.map.ser.impl.SimpleBeanPropertyFilter;
import org.codehaus.jackson.map.ser.impl.SimpleFilterProvider;

@JsonFilter("OMNI_Properties_Filter")  
class PropertyFilterMixIn {}  

/**
 * @author RichardZourob
 *
 */
public class PathObjectMapper extends ObjectMapper
{

    private static String[] ignorableFieldNames = { "isOracle", "isSQLServer", "isSybase", "emptyBigDecimalValue", 
	    					    "traceAppName", "traceUserId", "traceProgRef", "deviceIp" , "userAgent",
	    					    "PASSWORD", "PWD_HASHING_ALGORITHM", "PIN_PASSWORD", "PIN_HASHING_ALGORITHM"};  
    
    /**
     * 
     */
    public PathObjectMapper()
    {
	super();
	setPropertyNamingStrategy(PathPropertyNamingStrategy.KEEP_AS_IS);
	configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
	configure(JsonParser.Feature.ALLOW_NUMERIC_LEADING_ZEROS, true);
	getSerializationConfig().withDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SZ"));
	getSerializationConfig().withSerializationInclusion(Inclusion.NON_NULL);
	getSerializationConfig().withSerializationInclusion(Inclusion.NON_EMPTY);
	
	Version vers = new Version(0, 0, 0, null);
	SimpleModule module = new SimpleModule("dateModule", vers);
	module.addDeserializer(Date.class, new DateDeserializer());
	module.addDeserializer(BigDecimal.class, new BigDecimalDeserializer());
	registerModule(module);
	
	//nabil feghali : add below OMNI_Properties_Filter to remove the attributes that should not be returned in response
	
	setSerializationInclusion(Inclusion.NON_NULL);
	setSerializationInclusion(Inclusion.NON_EMPTY);
	configure(Feature.WRITE_EMPTY_JSON_ARRAYS, false);
	configure(Feature.WRITE_NULL_MAP_VALUES, false);
	// needed to ignore the fields defined in ignorableFieldNames
	getSerializationConfig().addMixInAnnotations(Object.class, PropertyFilterMixIn.class);
	FilterProvider filters = new SimpleFilterProvider().addFilter("OMNI_Properties_Filter",
		SimpleBeanPropertyFilter.serializeAllExcept(ignorableFieldNames));
	getSerializationConfig().withFilters(filters);
	setFilters(filters);
  	
    }

    @Override
    public String writeValueAsString(Object value) throws IOException, JsonGenerationException, JsonMappingException
    {
	
	// TODO Auto-generated method stub
	String output= super.writeValueAsString(value);
	//nabil feghali : null values are removed by using setSerializationInclusion(Inclusion.NON_NULL);
	//output = output.replaceAll("\"([^\" ]+)\":null,", "");//remove null
	//output= output.replaceAll(",\"([^\" ]+)\":null", "");
	output= output.replaceAll("\"([^\" ]+)\":\\{\\},", "");//remove empty object
	output= output.replaceAll(",\"([^\" ]+)\":\\{\\}", "");
	output= output.replaceAll("\"([^\" ]+)\":\\[\\],", "");//remove empty array
	output= output.replaceAll(",\"([^\" ]+)\":\\[\\]", "");
	output= output.replaceAll("\"([^\" ]+)\":\\{\\},", "");//remove empty object after removing the arrays
	output= output.replaceAll(",\"([^\" ]+)\":\\{\\}", "");
	
	//nabil feghali : below is commented because these attributes are removed from OMNI_Properties_Filter
	/*output= output.replaceAll("(\"isOracle\":[a-zA-Z0-9\\- :]*),", "");
	output= output.replaceAll(",(\"isOracle\":[a-zA-Z0-9\\- :]*)", "");
	output= output.replaceAll("(\"emptyBigDecimalValue\":[a-zA-Z0-9\\- :]*),", "");
	output= output.replaceAll(",(\"emptyBigDecimalValue\":[a-zA-Z0-9\\- :]*)", "");
	output= output.replaceAll("(\"isSQLServer\":[a-zA-Z0-9\\- :]*),", "");
	output= output.replaceAll(",(\"isSQLServer\":[a-zA-Z0-9\\- :]*)", "");
	output= output.replaceAll("(\"isSybase\":[a-zA-Z0-9\\- :]*),", "");
	output= output.replaceAll(",(\"isSybase\":[a-zA-Z0-9\\- :]*)", "");*/
	
	return output;
    }
    
   
}

