package com.path.lib.social.facebook;


public class OAuthServiceProvider<T> {

	private OAuthServiceConfig config;

	public OAuthServiceConfig getConfig()
	{
	    return config;
	}

	public void setConfig(OAuthServiceConfig config)
	{
	    this.config = config;
	}
	
	
	/*private Api api;

	public OAuthServiceProvider() {
	}

	public OAuthServiceProvider(OAuthServiceConfig<? extends Api> config) {
		this.config = config;
		OAuthServiceBuilder oAuthServiceBuilder = new OAuthServiceBuilder(config.getApiClass(), config.getApiKey(), config.getApiSecret(), config.getCallback(),null);
		this.api = oAuthServiceBuilder.getApi();
	}

	public Api getApi() {
		return api;
	}

	public void setApi(Api api) {
		this.api = api;
	}

	public OAuthService returnOAuthService() {
		return api.createService(new OAuthConfig(config.getApiKey(), config.getApiSecret(), config.getCallback(), SignatureType.Header, null, null));
	}*/
}