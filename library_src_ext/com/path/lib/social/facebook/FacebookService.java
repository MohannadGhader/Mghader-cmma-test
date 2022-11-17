package com.path.lib.social.facebook;

public class FacebookService
{
    private String apikey;
    private String apisecret;
    private String callback;
    private String redeemLink;
    public String getApikey()
    {
        return apikey;
    }
    public void setApikey(String apikey)
    {
        this.apikey = apikey;
    }
    public String getApisecret()
    {
        return apisecret;
    }
    public void setApisecret(String apisecret)
    {
        this.apisecret = apisecret;
    }
    public String getCallback()
    {
        return callback;
    }
    public void setCallback(String callback)
    {
        this.callback = callback;
    }
    public String getRedeemLink()
    {
        return redeemLink;
    }
    public void setRedeemLink(String redeemLink)
    {
        this.redeemLink = redeemLink;
    }
    
    
    /*private ConnectionFactoryRegistry connectionFactoryRegistry;
    private OAuth2Parameters oAuth2Parameters;
    private OAuthServiceProvider<FacebookApi> facebookServiceProvider;

    public String returnAuthorizeUrl() throws BOException
    {
	FacebookConnectionFactory facebookConnectionFactory = (FacebookConnectionFactory) connectionFactoryRegistry.getConnectionFactory(FacebookConstant.FACEBOOK);
	OAuth2Operations oauthOperations = facebookConnectionFactory.getOAuthOperations();
	oAuth2Parameters.setState(FacebookConstant.RECIVED_FROM_FACEBOOK_TOKEN);
	String authorizeUrl = oauthOperations.buildAuthorizeUrl(GrantType.AUTHORIZATION_CODE, oAuth2Parameters);
	return authorizeUrl;
    }

    public FacebookTemplate returnFaceBookTemplate(String code) throws BOException
    {
//	OAuthService oAuthService = facebookServiceProvider.returnOAuthService();
//	Verifier verifier = new Verifier(code);
//	Token accessToken = oAuthService.getAccessToken(Token.empty(), verifier);
	//Token accessToken = returnAccessTokenFromAuthCode(code);
	//FacebookTemplate template = new FacebookTemplate(accessToken.getToken());
	Token accessToken = returnAccessTokenFromAuthCode(code);
	FacebookTemplate template = new FacebookTemplate(accessToken.getToken());
	return template;
    }
    
    public JSONObject returnJsonByQuery(FacebookTemplate template, String query) throws BOException
    {
	if(template == null)
	{
	    FacebookConnectionFactory facebookConnectionFactory = (FacebookConnectionFactory) connectionFactoryRegistry.getConnectionFactory(FacebookConstant.FACEBOOK);
	    OAuth2Operations oauthOperations = facebookConnectionFactory.getOAuthOperations();
	    template = new FacebookTemplate(oauthOperations.authenticateClient().getAccessToken());
	}
	template.setApiVersion(FacebookConstant.FACEBOOK_API_VERSION);
	return template.restOperations().getForObject(template.getBaseGraphApiUrl()+query, JSONObject.class);
    }
    
    public Token returnAccessTokenFromAuthCode(String code) throws BOException
    {
	Api api = facebookServiceProvider.getApi();
	OAuthRequest request = new OAuthRequest(((DefaultApi20) api).getAccessTokenVerb(), ((DefaultApi20) api).getAccessTokenEndpoint());
	request.addQuerystringParameter(OAuthConstants.CLIENT_ID, apikey);
	request.addQuerystringParameter(OAuthConstants.CLIENT_SECRET, apisecret);
	request.addQuerystringParameter(OAuthConstants.CODE, code);
	request.addQuerystringParameter(OAuthConstants.REDIRECT_URI, "https://apps.facebook.com/mohamadbankoc/");
	if(null != oAuth2Parameters.getScope())
	{
	    request.addQuerystringParameter(OAuthConstants.SCOPE, oAuth2Parameters.getScope());
	}
	Response response = request.send();
	JSONObject json = (JSONObject) JSONSerializer.toJSON(response.getBody());  
	Preconditions.checkEmptyString(response.getBody(), "Response body is incorrect. Can't extract a token from an empty string"); 
	if(null != json.get("error"))
	{
	    JSONObject jsonError = (JSONObject)json.get("error");
	    throw new BOException(jsonError.getString("message"));
	}
	else if(null == json.getString(FacebookConstant.ACCESS_TOKEN))
	{
	    throw new BOException("Invalid verification code format.");
	}
	String token = OAuthEncoder.decode(json.getString(FacebookConstant.ACCESS_TOKEN));
	return new Token(token, "", response.getBody());
    }
    
    public String publishLinkRestriction(FacebookCommonCO facebookCO) throws BOException
    {
	Assert.notNull(facebookCO.getFacebookTemplate());
	Assert.notNull(facebookCO.getUserIdToPostTo());
	return facebookCO.getFacebookTemplate().publish(facebookCO.getUserIdToPostTo(), "feed", facebookCO.getUserRestrictedMap());
    }
    
    public String returnFaceBookRedirectUri() throws BOException
    {
	return oAuth2Parameters.getRedirectUri();
    }

    public String returnOAuth2ByParameters(String params) throws BOException
    {
	for(Entry<String, List<String>> entry : oAuth2Parameters.entrySet())
	{
	    if(entry.getKey().equals(params))
	    {
		;
	    }
	    {
		return entry.getValue().get(0); 
	    }
	}
	return "";
    }

    public ConnectionFactoryRegistry getConnectionFactoryRegistry()
    {
        return connectionFactoryRegistry;
    }

    public void setConnectionFactoryRegistry(ConnectionFactoryRegistry connectionFactoryRegistry)
    {
        this.connectionFactoryRegistry = connectionFactoryRegistry;
    }

    public OAuth2Parameters getoAuth2Parameters()
    {
        return oAuth2Parameters;
    }

    public void setoAuth2Parameters(OAuth2Parameters oAuth2Parameters)
    {
        this.oAuth2Parameters = oAuth2Parameters;
    }

    public OAuthServiceProvider<FacebookApi> getFacebookServiceProvider()
    {
        return facebookServiceProvider;
    }

    public void setFacebookServiceProvider(OAuthServiceProvider<FacebookApi> facebookServiceProvider)
    {
        this.facebookServiceProvider = facebookServiceProvider;
    }

    public String getApikey()
    {
        return apikey;
    }

    public void setApikey(String apikey)
    {
        this.apikey = apikey;
    }

    public String getApisecret()
    {
        return apisecret;
    }

    public void setApisecret(String apisecret)
    {
        this.apisecret = apisecret;
    }

    public String getCallback()
    {
        return callback;
    }

    public void setCallback(String callback)
    {
        this.callback = callback;
    }

    public String getRedeemLink()
    {
        return redeemLink;
    }

    public void setRedeemLink(String redeemLink)
    {
        this.redeemLink = redeemLink;
    }*/

}
