package com.path.vo.common;

import java.util.List;

import com.path.struts2.lib.common.BaseSC;

public class ProcArgSC extends BaseSC
{
    private List<ProcArgCO> argsList;

    public List<ProcArgCO> getArgsList()
    {
	return argsList;
    }

    public void setArgsList(List<ProcArgCO> argsList)
    {
	this.argsList = argsList;
    }

}
