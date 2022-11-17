package com.path.lib.remote;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

import com.path.lib.common.util.StringUtil;
import com.path.lib.log.Log;

/**
 * 
 * Copyright 2011, Path Solutions Path Solutions retains all ownership rights to
 * this source code
 * 
 * 
 * PathBeanFactoryPostProcessor.java used to update properties of bean
 * definition
 */
public class PathBeanFactoryPostProcessor implements BeanFactoryPostProcessor
{
    private String beanAvailability;
    static Log log = Log.getInstance();

    public String getBeanAvailability()
    {
	return beanAvailability;
    }

    public void setBeanAvailability(String beanAvailability)
    {
	this.beanAvailability = beanAvailability;
    }

    /**
     * This method overrides property element of each bean which will be created
     * by pathBeanFactory remotely and removes the properties for these beans in
     * remoteing case.
     */
    public void postProcessBeanFactory(ConfigurableListableBeanFactory factory) throws BeansException
    {
	// if bean has to be created locally then no need to override property
	if(RemoteConstants.LOCAL_CONF.equals(StringUtil.nullToEmpty(beanAvailability).trim()))
	{
	    return;
	}
	
	//TP 828729 Spring 5.x Upgrade to avoid getting circular reference error on startup  with the beans added to AOP Proxy in applicationContext.xml, like PathBPMInterceptor. this will prevent getting the below exception :
	//org.springframework.beans.factory.BeanCurrentlyInCreationException: Error creating bean with name 'fomBO': Bean with name 'fomBO' has been 
	//injected into other beans [fomIdsBO,nCIfMaintenanceOnChangeBO,fomKycBO,suspiciousBO] in its raw version as part of a circular reference, 
	//but has eventually been wrapped. This means that said other beans do not use the final version of the bean. 
	//This is often the result of over-eager type matching - consider using 'getBeanNamesOfType' with the 'allowEagerInit' flag turned off, for example.
	if(factory instanceof DefaultListableBeanFactory)
	{
	    ((DefaultListableBeanFactory) factory).setAllowRawInjectionDespiteWrapping(true);
	}
	String[] beanNames = factory.getBeanDefinitionNames();
	for(int i = 0; i < beanNames.length; ++i)
	{

	    try
	    {
		AbstractBeanDefinition beanDefinition = (AbstractBeanDefinition) factory
			.getBeanDefinition(beanNames[i]);
		
		if(beanDefinition.getFactoryBeanName() != null)
		{
		    Object factoryBean = factory.getBean(beanDefinition.getFactoryBeanName());

		    if(factoryBean != null)
		    {
			//in case of calling getIntegInstance from the standalone integration application or from the embedded
			//integration application and in case IntegServiceURL is empty so we are in imal_core_integration or in imal_core_services,
			//in this case we need to continue , we don't need to set setPropertyValues to null.
			if("getIntegInstance".equals(beanDefinition.getFactoryMethodName())
				&& factoryBean instanceof PathBeanFactory
				&& StringUtil.nullToEmpty((((PathBeanFactory) factoryBean).getIntegServiceURL()))
					.isEmpty())
			{
			    continue;
			}

			if("com.path.lib.remote.PathBeanFactory".equals(factoryBean.getClass().getName()))
			{
			    beanDefinition.setPropertyValues(null);
			    beanDefinition.setParentName(null);
			    //in case we need to apply lazy init on portal action's beans we need to use setLazyInit(true)
			    beanDefinition.setLazyInit(true);
			    //set init-method and destroy-method to null in case of PathBeanFactory ( in case of portal or integration layer)
			    beanDefinition.setDestroyMethodName(null);
			    beanDefinition.setInitMethodName(null);
			}
		    }
		}
	    }
	    catch(Exception e)
	    {
		log.error(e, "error in postProcessBeanFactory for bean " + beanNames[i]);
	    }
	}

    }
}
