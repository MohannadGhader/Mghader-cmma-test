package com.path.lib.common.base.ibatis3;

import org.springframework.core.io.Resource;
/**
 * DENISK_LATEST_VERS_UPDATED
 * @author deniskhaddad
 *
 */
public class SqlSessionFactProps
{
    private Resource[] configLocations;
    
    private Resource configLocation;

    private Resource[] mapperLocations;
    
    private boolean failFast;

    private String oraMappers;
    private String sybaseMappers;
    private String mapEnc;
    
    public void setConfigLocation(Resource configLocation)
    {
	this.configLocation = configLocation;
    }
    public Resource getConfigLocation()
    {
	return configLocation;
    }
    
    public Resource[] getConfigLocations()
    {
        return configLocations;
    }
    public void setConfigLocations(Resource... configLocations)
    {
        this.configLocations = configLocations;
    }
    public Resource[] getMapperLocations()
    {
        return mapperLocations;
    }
    public void setMapperLocations(Resource... mapperLocations)
    {
        this.mapperLocations = mapperLocations;
    }
    public boolean isFailFast()
    {
        return failFast;
    }
    public void setFailFast(boolean failFast)
    {
        this.failFast = failFast;
    }
    public String getOraMappers()
    {
        return oraMappers;
    }
    public void setOraMappers(String oraMappers)
    {
        this.oraMappers = oraMappers;
    }
    public String getSybaseMappers()
    {
        return sybaseMappers;
    }
    public void setSybaseMappers(String sybaseMappers)
    {
        this.sybaseMappers = sybaseMappers;
    }
    public String getMapEnc()
    {
        return mapEnc;
    }
    public void setMapEnc(String mapEnc)
    {
        this.mapEnc = mapEnc;
    }
}
