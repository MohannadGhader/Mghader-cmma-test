package com.path.lib.common.util.barcode;

/**
 * Copyright 2015, Path Solutions Path Solutions retains all ownership rights to
 * this source code </br>
 * 
 * <strong>PathBarCodeProp.java</strong> used to initialize the properties of
 * the PathBarCodeGenerator
 * 
 * @author Khaledhussein
 * 
 */
public class PathBarCodeProp
{
    /**
     * the width of the barcode
     */
    private Integer width;

    /**
     * the height of the barcode
     */
    private Integer height;

    /**
     * Draw empty space around the barcode
     */
    private Boolean quietZone;

    private Double quietZoneVer;
    private Double quietZoneHor;

    /**
     * @return the width
     */
    public Integer getWidth()
    {
	return width;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(Integer width)
    {
	this.width = width;
    }

    /**
     * @return the height
     */
    public Integer getHeight()
    {
	return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(Integer height)
    {
	this.height = height;
    }

    /**
     * @return the quietZone
     */
    public Boolean isQuietZone()
    {
	return quietZone;
    }

    /**
     * @param quietZone the quietZone to set
     */
    public void setQuietZone(Boolean quietZone)
    {
	this.quietZone = quietZone;
    }

    /**
     * @return the quietZoneVer
     */
    public Double getQuietZoneVer()
    {
        return quietZoneVer;
    }

    /**
     * @param quietZoneVer the quietZoneVer to set
     */
    public void setQuietZoneVer(Double quietZoneVer)
    {
        this.quietZoneVer = quietZoneVer;
    }

    /**
     * @return the quietZoneHor
     */
    public Double getQuietZoneHor()
    {
        return quietZoneHor;
    }

    /**
     * @param quietZoneHor the quietZoneHor to set
     */
    public void setQuietZoneHor(Double quietZoneHor)
    {
        this.quietZoneHor = quietZoneHor;
    }
}
