package com.path.lib.vo;

import java.util.HashMap;

public class LookupGridColumn
{

    protected String colType;
    protected String accesskey;
    protected String align;
    protected String cssClass;
    protected String cssErrorClass;
    protected String cssErrorStyle;
    protected String cssStyle;
    protected String dataType;
    protected String defval;
    protected String disabled;
    protected String editable = "false";
    protected String editoptions;
    protected String editrules;
    protected String edittype;
    protected String effect;
    protected String effectDuration;
    protected String effectMode;
    protected String effectOptions;
    protected String errorElementId;
    protected String errorText;
    protected String formIds;
    protected String formatoptions;
    protected String formatter;
    protected String formoptions;

    /**
     * @author MarwanMaddah Changed from String to Boolean to solve a problem in
     *         liveSearch Component.
     */
    protected Boolean hidden = false;
    protected String hidedlg;
    protected String href;
    protected String id;
    protected String index;
    protected String indicator;
    protected String javascriptTooltip;
    protected String key;
    protected String label;
    protected String labelSeparator;
    protected String labelposition;
    protected String listenTopics;
    protected String loadingText;
    protected String name;
    protected String onAlwaysTopics;
    protected String onBeforeTopics;
    protected String onBlurTopics;
    protected String onChangeTopics;
    protected String onCompleteTopics;
    protected String onDisableTopics;
    protected String onEffectCompleteTopics;
    protected String onEnableTopics;
    protected String onErrorTopics;
    protected String onFocusTopics;
    protected String onSuccessTopics;
    protected String onblur;
    protected String onchange;
    protected String onclick;
    protected String ondblclick;
    protected String onfocus;
    protected String onkeydown;
    protected String onkeypress;
    protected String onkeyup;
    protected String onmousedown;
    protected String onmousemove;
    protected String onmouseout;
    protected String onmouseover;
    protected String onmouseup;
    protected String onselect;
    protected String openTemplate;
    protected String requestType;
    protected String required;
    protected String requiredposition;
    protected String resizable;
    protected Boolean search = false;
    protected HashMap searchoptions = new HashMap();
    protected Boolean sortable = false;
    protected String surl;
    protected String tabindex;
    protected String targets;
    protected String template;
    protected String templateDir;
    protected String timeout;
    protected String title;
    protected String tooltip;
    protected String tooltipConfig;
    protected String tooltipCssClass;
    protected String tooltipDelay;
    protected String tooltipIconPath;
    protected String value;
    protected String width;
    protected String leadZeros;
    protected String nbFormat;
    protected String formatCol;

    private HashMap<String, Object> columnMap = new HashMap<String, Object>();

    public String getColType()
    {
	return colType;
    }

    public void setColType(String colType)
    {
	this.colType = colType;
	columnMap.put("colType", this.colType);
    }

    public String getAccesskey()
    {
	return accesskey;
    }

    public void setAccesskey(String accesskey)
    {
	this.accesskey = accesskey;
	columnMap.put("accesskey", this.accesskey);
    }

    public String getAlign()
    {
	return align;
    }

    public void setAlign(String align)
    {
	this.align = align;
	columnMap.put("align", this.align);
    }

    public String getCssClass()
    {
	return cssClass;
    }

    public void setCssClass(String cssClass)
    {
	this.cssClass = cssClass; 
	columnMap.put("classes", this.cssClass);
    }

    public String getCssErrorClass()
    {
	return cssErrorClass;
    }

    public void setCssErrorClass(String cssErrorClass)
    {
	this.cssErrorClass = cssErrorClass;
	columnMap.put("cssErrorClass", this.cssErrorClass);
    }

    public String getCssErrorStyle()
    {
	return cssErrorStyle;
    }

    public void setCssErrorStyle(String cssErrorStyle)
    {
	this.cssErrorStyle = cssErrorStyle;
	columnMap.put("cssErrorStyle", this.cssErrorStyle);
    }

    public String getCssStyle()
    {
	return cssStyle;
    }

    public void setCssStyle(String cssStyle)
    {
	this.cssStyle = cssStyle;
	columnMap.put("cssStyle", this.cssStyle);
    }

    public String getDataType()
    {
	return dataType;
    }

    public void setDataType(String dataType)
    {
	this.dataType = dataType;
	columnMap.put("dataType", this.dataType);
    }

    public String getDefval()
    {
	return defval;
    }

    public void setDefval(String defval)
    {
	this.defval = defval;
	columnMap.put("defval", this.defval);
    }

    public String getDisabled()
    {
	return disabled;
    }

    public void setDisabled(String disabled)
    {
	this.disabled = disabled;
	columnMap.put("disabled", this.disabled);
    }

    public String getEditable()
    {
	return editable;
    }

    public void setEditable(String editable)
    {
	this.editable = editable;
	columnMap.put("editable", this.editable);
    }

    public String getEditoptions()
    {
	return editoptions;
    }

    public void setEditoptions(String editoptions)
    {
	this.editoptions = editoptions;
	columnMap.put("editoptions", this.editoptions);
    }

    public String getEditrules()
    {
	return editrules;
    }

    public void setEditrules(String editrules)
    {
	this.editrules = editrules;
	columnMap.put("editrules", this.editrules);
    }

    public String getEdittype()
    {
	return edittype;
    }

    public void setEdittype(String edittype)
    {
	this.edittype = edittype;
	columnMap.put("edittype", this.edittype);
    }

    public String getEffect()
    {
	return effect;
    }

    public void setEffect(String effect)
    {
	this.effect = effect;
	columnMap.put("effect", this.effect);
    }

    public String getEffectDuration()
    {
	return effectDuration;
    }

    public void setEffectDuration(String effectDuration)
    {
	this.effectDuration = effectDuration;
	columnMap.put("effectDuration", this.effectDuration);
    }

    public String getEffectMode()
    {
	return effectMode;
    }

    public void setEffectMode(String effectMode)
    {
	this.effectMode = effectMode;
	columnMap.put("effectMode", this.effectMode);
    }

    public String getEffectOptions()
    {
	return effectOptions;
    }

    public void setEffectOptions(String effectOptions)
    {
	this.effectOptions = effectOptions;
	columnMap.put("effectOptions", this.effectOptions);
    }

    public String getErrorElementId()
    {
	return errorElementId;
    }

    public void setErrorElementId(String errorElementId)
    {
	this.errorElementId = errorElementId;
	columnMap.put("errorElementId", this.errorElementId);
    }

    public String getErrorText()
    {
	return errorText;
    }

    public void setErrorText(String errorText)
    {
	this.errorText = errorText;
	columnMap.put("errorText", this.errorText);
    }

    public String getFormIds()
    {
	return formIds;
    }

    public void setFormIds(String formIds)
    {
	this.formIds = formIds;
	columnMap.put("formIds", this.formIds);
    }

    public String getFormatoptions()
    {
	return formatoptions;
    }

    public void setFormatoptions(String formatoptions)
    {
	this.formatoptions = formatoptions;
	columnMap.put("formatoptions", this.formatoptions);
    }

    public String getFormatter()
    {
	return formatter;
    }

    public void setFormatter(String formatter)
    {
	this.formatter = formatter;
	columnMap.put("formatter", this.formatter);
    }

    public String getFormoptions()
    {
	return formoptions;
    }

    public void setFormoptions(String formoptions)
    {
	this.formoptions = formoptions;
	columnMap.put("formoptions", this.formoptions);
    }

    public String getHidedlg()
    {
	return hidedlg;
    }

    public void setHidedlg(String hidedlg)
    {
	this.hidedlg = hidedlg;
	columnMap.put("hidedlg", this.hidedlg);
    }

    public String getHref()
    {
	return href;
    }

    public void setHref(String href)
    {
	this.href = href;
	columnMap.put("href", this.href);
    }

    public String getId()
    {
	return id;
    }

    public void setId(String id)
    {
	this.id = id;
	columnMap.put("id", this.id);
	columnMap.put("jsonmap", this.id);
    }

    public String getIndex()
    {
	return index;
    }

    public void setIndex(String index)
    {
	this.index = index;
	columnMap.put("index", this.index);
    }

    public String getIndicator()
    {
	return indicator;
    }

    public void setIndicator(String indicator)
    {
	this.indicator = indicator;
	columnMap.put("indicator", this.indicator);
    }

    public String getJavascriptTooltip()
    {
	return javascriptTooltip;
    }

    public void setJavascriptTooltip(String javascriptTooltip)
    {
	this.javascriptTooltip = javascriptTooltip;
	columnMap.put("javascriptTooltip", this.javascriptTooltip);
    }

    public String getKey()
    {
	return key;
    }

    public void setKey(String key)
    {
	this.key = key;
	columnMap.put("key", this.key);
    }

    public String getLabel()
    {
	return label;
    }

    public void setLabel(String label)
    {
	this.label = label;
	columnMap.put("label", this.label);
    }

    public String getLabelSeparator()
    {
	return labelSeparator;
    }

    public void setLabelSeparator(String labelSeparator)
    {
	this.labelSeparator = labelSeparator;
	columnMap.put("labelSeparator", this.labelSeparator);
    }

    public String getLabelposition()
    {
	return labelposition;
    }

    public void setLabelposition(String labelposition)
    {
	this.labelposition = labelposition;
	columnMap.put("labelposition", this.labelposition);
    }

    public String getListenTopics()
    {
	return listenTopics;
    }

    public void setListenTopics(String listenTopics)
    {
	this.listenTopics = listenTopics;
	columnMap.put("listenTopics", this.listenTopics);
    }

    public String getLoadingText()
    {
	return loadingText;
    }

    public void setLoadingText(String loadingText)
    {
	this.loadingText = loadingText;
	columnMap.put("loadingText", this.loadingText);
    }

    public String getName()
    {
	return name;
    }

    public void setName(String name)
    {
	this.name = name;
	columnMap.put("name", this.name);
    }

    public String getOnAlwaysTopics()
    {
	return onAlwaysTopics;
    }

    public void setOnAlwaysTopics(String onAlwaysTopics)
    {
	this.onAlwaysTopics = onAlwaysTopics;
	columnMap.put("onAlwaysTopics", this.onAlwaysTopics);
    }

    public String getOnBeforeTopics()
    {
	return onBeforeTopics;
    }

    public void setOnBeforeTopics(String onBeforeTopics)
    {
	this.onBeforeTopics = onBeforeTopics;
	columnMap.put("onBeforeTopics", this.onBeforeTopics);
    }

    public String getOnBlurTopics()
    {
	return onBlurTopics;
    }

    public void setOnBlurTopics(String onBlurTopics)
    {
	this.onBlurTopics = onBlurTopics;
	columnMap.put("onBlurTopics", this.onBlurTopics);
    }

    public String getOnChangeTopics()
    {
	return onChangeTopics;
    }

    public void setOnChangeTopics(String onChangeTopics)
    {
	this.onChangeTopics = onChangeTopics;
	columnMap.put("onChangeTopics", this.onChangeTopics);
    }

    public String getOnCompleteTopics()
    {
	return onCompleteTopics;
    }

    public void setOnCompleteTopics(String onCompleteTopics)
    {
	this.onCompleteTopics = onCompleteTopics;
	columnMap.put("onCompleteTopics", this.onCompleteTopics);
    }

    public String getOnDisableTopics()
    {
	return onDisableTopics;
    }

    public void setOnDisableTopics(String onDisableTopics)
    {
	this.onDisableTopics = onDisableTopics;
	columnMap.put("onDisableTopics", this.onDisableTopics);
    }

    public String getOnEffectCompleteTopics()
    {
	return onEffectCompleteTopics;
    }

    public void setOnEffectCompleteTopics(String onEffectCompleteTopics)
    {
	this.onEffectCompleteTopics = onEffectCompleteTopics;
	columnMap.put("onEffectCompleteTopics", this.onEffectCompleteTopics);
    }

    public String getOnEnableTopics()
    {
	return onEnableTopics;
    }

    public void setOnEnableTopics(String onEnableTopics)
    {
	this.onEnableTopics = onEnableTopics;
	columnMap.put("onEnableTopics", this.onEnableTopics);
    }

    public String getOnErrorTopics()
    {
	return onErrorTopics;
    }

    public void setOnErrorTopics(String onErrorTopics)
    {
	this.onErrorTopics = onErrorTopics;
	columnMap.put("onErrorTopics", this.onErrorTopics);
    }

    public String getOnFocusTopics()
    {
	return onFocusTopics;
    }

    public void setOnFocusTopics(String onFocusTopics)
    {
	this.onFocusTopics = onFocusTopics;
	columnMap.put("onFocusTopics", this.onFocusTopics);
    }

    public String getOnSuccessTopics()
    {
	return onSuccessTopics;
    }

    public void setOnSuccessTopics(String onSuccessTopics)
    {
	this.onSuccessTopics = onSuccessTopics;
	columnMap.put("onSuccessTopics", this.onSuccessTopics);
    }

    public String getOnblur()
    {
	return onblur;
    }

    public void setOnblur(String onblur)
    {
	this.onblur = onblur;
	columnMap.put("onblur", this.onblur);
    }

    public String getOnchange()
    {
	return onchange;
    }

    public void setOnchange(String onchange)
    {
	this.onchange = onchange;
	columnMap.put("onchange", this.onchange);
    }

    public String getOnclick()
    {
	return onclick;
    }

    public void setOnclick(String onclick)
    {
	this.onclick = onclick;
	columnMap.put("onclick", this.onclick);
    }

    public String getOndblclick()
    {
	return ondblclick;
    }

    public void setOndblclick(String ondblclick)
    {
	this.ondblclick = ondblclick;
	columnMap.put("ondblclick", this.ondblclick);
    }

    public String getOnfocus()
    {
	return onfocus;
    }

    public void setOnfocus(String onfocus)
    {
	this.onfocus = onfocus;
	columnMap.put("onfocus", this.onfocus);
    }

    public String getOnkeydown()
    {
	return onkeydown;
    }

    public void setOnkeydown(String onkeydown)
    {
	this.onkeydown = onkeydown;
	columnMap.put("onkeydown", this.onkeydown);
    }

    public String getOnkeypress()
    {
	return onkeypress;
    }

    public void setOnkeypress(String onkeypress)
    {
	this.onkeypress = onkeypress;
	columnMap.put("onkeypress", this.onkeypress);
    }

    public String getOnkeyup()
    {
	return onkeyup;
    }

    public void setOnkeyup(String onkeyup)
    {
	this.onkeyup = onkeyup;
	columnMap.put("onkeyup", this.onkeyup);
    }

    public String getOnmousedown()
    {
	return onmousedown;
    }

    public void setOnmousedown(String onmousedown)
    {
	this.onmousedown = onmousedown;
	columnMap.put("onmousedown", this.onmousedown);
    }

    public String getOnmousemove()
    {
	return onmousemove;
    }

    public void setOnmousemove(String onmousemove)
    {
	this.onmousemove = onmousemove;
	columnMap.put("onmousemove", this.onmousemove);
    }

    public String getOnmouseout()
    {
	return onmouseout;
    }

    public void setOnmouseout(String onmouseout)
    {
	this.onmouseout = onmouseout;
	columnMap.put("onmouseout", this.onmouseout);
    }

    public String getOnmouseover()
    {
	return onmouseover;
    }

    public void setOnmouseover(String onmouseover)
    {
	this.onmouseover = onmouseover;
	columnMap.put("onmouseover", this.onmouseover);
    }

    public String getOnmouseup()
    {
	return onmouseup;
    }

    public void setOnmouseup(String onmouseup)
    {
	this.onmouseup = onmouseup;
	columnMap.put("onmouseup", this.onmouseup);
    }

    public String getOnselect()
    {
	return onselect;
    }

    public void setOnselect(String onselect)
    {
	this.onselect = onselect;
	columnMap.put("onselect", this.onselect);
    }

    public String getOpenTemplate()
    {
	return openTemplate;
    }

    public void setOpenTemplate(String openTemplate)
    {
	this.openTemplate = openTemplate;
	columnMap.put("openTemplate", this.openTemplate);
    }

    public String getRequestType()
    {
	return requestType;
    }

    public void setRequestType(String requestType)
    {
	this.requestType = requestType;
	columnMap.put("requestType", this.requestType);
    }

    public String getRequired()
    {
	return required;
    }

    public void setRequired(String required)
    {
	this.required = required;
	columnMap.put("required", this.required);
    }

    public String getRequiredposition()
    {
	return requiredposition;
    }

    public void setRequiredposition(String requiredposition)
    {
	this.requiredposition = requiredposition;
	columnMap.put("requiredposition", this.requiredposition);
    }

    public String getResizable()
    {
	return resizable;
    }

    public void setResizable(String resizable)
    {
	this.resizable = resizable;
	columnMap.put("resizable", this.resizable);
    }

    public Boolean getSearch()
    {
	return search;
    }

    public void setSearch(Boolean search)
    {
	this.search = search;
	columnMap.put("search", this.search);
    }

    public HashMap getSearchoptions()
    {
	return searchoptions;
    }

    public void setSearchoptions(HashMap searchoptions)
    {
	this.searchoptions = searchoptions;
	columnMap.put("searchoptions", this.searchoptions);
    }

    public String getSurl()
    {
	return surl;
    }

    public void setSurl(String surl)
    {
	this.surl = surl;
	columnMap.put("surl", this.surl);
    }

    public String getTabindex()
    {
	return tabindex;
    }

    public void setTabindex(String tabindex)
    {
	this.tabindex = tabindex;
	columnMap.put("tabindex", this.tabindex);
    }

    public String getTargets()
    {
	return targets;
    }

    public void setTargets(String targets)
    {
	this.targets = targets;
	columnMap.put("targets", this.targets);
    }

    public String getTemplate()
    {
	return template;
    }

    public void setTemplate(String template)
    {
	this.template = template;
	columnMap.put("template", this.template);
    }

    public String getTemplateDir()
    {
	return templateDir;
    }

    public void setTemplateDir(String templateDir)
    {
	this.templateDir = templateDir;
	columnMap.put("templateDir", this.templateDir);
    }

    public String getTimeout()
    {
	return timeout;
    }

    public void setTimeout(String timeout)
    {
	this.timeout = timeout;
	columnMap.put("timeout", this.timeout);
    }

    public String getTitle()
    {
	return title;
    }

    public void setTitle(String title)
    {
	this.title = title;
	columnMap.put("title", this.title);
    }

    public String getTooltip()
    {
	return tooltip;
    }

    public void setTooltip(String tooltip)
    {
	this.tooltip = tooltip;
	columnMap.put("tooltip", this.tooltip);
    }

    public String getTooltipConfig()
    {
	return tooltipConfig;
    }

    public void setTooltipConfig(String tooltipConfig)
    {
	this.tooltipConfig = tooltipConfig;
	columnMap.put("tooltipConfig", this.tooltipConfig);
    }

    public String getTooltipCssClass()
    {
	return tooltipCssClass;
    }

    public void setTooltipCssClass(String tooltipCssClass)
    {
	this.tooltipCssClass = tooltipCssClass;
	columnMap.put("tooltipCssClass", this.tooltipCssClass);
    }

    public String getTooltipDelay()
    {
	return tooltipDelay;
    }

    public void setTooltipDelay(String tooltipDelay)
    {
	this.tooltipDelay = tooltipDelay;
	columnMap.put("tooltipDelay", this.tooltipDelay);
    }

    public String getTooltipIconPath()
    {
	return tooltipIconPath;
    }

    public void setTooltipIconPath(String tooltipIconPath)
    {
	this.tooltipIconPath = tooltipIconPath;
	columnMap.put("tooltipIconPath", this.tooltipIconPath);
    }

    public String getValue()
    {
	return value;
    }

    public void setValue(String value)
    {
	this.value = value;
	columnMap.put("value", this.value);
    }

    public String getWidth()
    {
	return width;
    }

    public void setWidth(String width)
    {
	this.width = width;
	columnMap.put("width", this.width);
    }

    public HashMap<String, Object> getColumnMap()
    {
	return columnMap;
    }

    public void setColumnMap(HashMap<String, Object> columnMap)
    {
	this.columnMap = columnMap;
    }

    /**
     * @return the hidden
     */
    public Boolean getHidden()
    {
	return hidden;
    }

    /**
     * @param hidden the hidden to set
     */
    public void setHidden(Boolean hidden)
    {
	this.hidden = hidden;
	columnMap.put("hidden", this.hidden);
    }

    public Boolean getSortable()
    {
	return sortable;
    }

    public void setSortable(Boolean sortable)
    {
	this.sortable = sortable;
	columnMap.put("sortable", this.sortable);
    }

    /**
     * @return the leadZeros
     */
    public String getLeadZeros()
    {
	return leadZeros;
    }

    /**
     * @param leadZeros the leadZeros to set
     */
    public void setLeadZeros(String leadZeros)
    {
	this.leadZeros = leadZeros;
	columnMap.put("leadZeros", this.leadZeros);
    }

    /**
     * @return the nbFormat
     */
    public String getNbFormat()
    {
	return nbFormat;
    }

    /**
     * @param nbFormat the nbFormat to set
     */
    public void setNbFormat(String nbFormat)
    {
	this.nbFormat = nbFormat;
	columnMap.put("nbFormat", this.nbFormat);
    }

    public String getFormatCol()
    {
        return formatCol;
    }

    public void setFormatCol(String formatCol)
    {
        this.formatCol = formatCol;
        columnMap.put("formatCol", formatCol);
    }

}
