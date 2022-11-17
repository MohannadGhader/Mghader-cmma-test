package com.path.ws.omni.vo.common;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_DEFAULT) 
public class OmniCommonResponse extends ResponseBaseObject
{

    private Map<String, String> parameterMap = new HashMap<String, String>();
    
    /**
     * @author mohamadhassan
     * @return
     * itemValue and description attributes are used for Select input component in all screens (ps:input component)
     */
    private String itemValue;
    private String description;
    private HashMap<String, Object> commonResponseList = new HashMap<String, Object>();

    public Map<String, String> getParameterMap()
    {
        return parameterMap;
    }

    public void setParameterMap(Map<String, String> parameterMap)
    {
        this.parameterMap = parameterMap;
    }

    public String getItemValue()
    {
	return itemValue;
    }

    public void setItemValue(String itemValue)
    {
	this.itemValue = itemValue;
    }

    public String getDescription()
    {
	return description;
    }

    public void setDescription(String description)
    {
	this.description = description;
    }

	public HashMap<String, Object> getCommonResponseList() {
		return commonResponseList;
	}

	public void setCommonResponseList(HashMap<String, Object> commonResponseList) {
		this.commonResponseList = commonResponseList;
	}
    
}