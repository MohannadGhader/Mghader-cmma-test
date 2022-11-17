package com.path.lib.vo;


public class MenuVO extends BaseVO
{
	private String menuVar;
	private String menuName;
	private boolean leaf;
	private String onLeafClick;
	public String getOnLeafClick()
	{
	    return onLeafClick;
	}
	public void setOnLeafClick(String onLeafClick)
	{
	    this.onLeafClick = onLeafClick;
	}
	public boolean isLeaf()
	{
		return leaf;
	}
	public void setLeaf(boolean leaf)
	{
		this.leaf = leaf;
	}
	public String getMenuVar()
	{
		return menuVar;
	}
	public void setMenuVar(String menuVar)
	{
		this.menuVar = menuVar;
	}
	public String getMenuName()
	{
		return menuName;
	}
	public void setMenuName(String menuName)
	{
		this.menuName = menuName;
	}
	
	
}
