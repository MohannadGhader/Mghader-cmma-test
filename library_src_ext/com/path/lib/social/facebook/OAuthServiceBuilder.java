package com.path.lib.social.facebook;

import java.io.OutputStream;


public class OAuthServiceBuilder 
{
    private String apiKey;
    private String apiSecret;
    private String callback;
    private OutputStream stream;
    public String getApiKey()
    {
        return apiKey;
    }
    public void setApiKey(String apiKey)
    {
        this.apiKey = apiKey;
    }
    public String getApiSecret()
    {
        return apiSecret;
    }
    public void setApiSecret(String apiSecret)
    {
        this.apiSecret = apiSecret;
    }
    public String getCallback()
    {
        return callback;
    }
    public void setCallback(String callback)
    {
        this.callback = callback;
    }
    public OutputStream getStream()
    {
        return stream;
    }
    public void setStream(OutputStream stream)
    {
        this.stream = stream;
    }

    /*public OAuthServiceBuilder(Class<? extends Api> apiClass, String apiKey, String apiSecret, String callback,
	    OutputStream stream)
    {
	try
	{
	    this.api = apiClass.newInstance();
	    this.apiKey(apiKey);
	    this.apiSecret(apiSecret);
	    this.callback(callback);
	    this.signatureType(SignatureType.Header);
	    if(null != stream)
	    {
		this.debugStream(stream);
	    }
	}
	catch(Exception e)
	{
	    throw new OAuthException("Error while creating the Api object", e);
	}
    }

    public Api getApi()
    {
	return api;
    }

    public void setApi(Api api)
    {
	this.api = api;
    }

    public String getApiKey()
    {
	return apiKey;
    }

    public void setApiKey(String apiKey)
    {
	this.apiKey = apiKey;
    }

    public String getApiSecret()
    {
	return apiSecret;
    }

    public void setApiSecret(String apiSecret)
    {
	this.apiSecret = apiSecret;
    }

    public String getCallback()
    {
	return callback;
    }

    public void setCallback(String callback)
    {
	this.callback = callback;
    }

    public OutputStream getStream()
    {
	return stream;
    }

    public void setStream(OutputStream stream)
    {
	this.stream = stream;
    }*/
}
