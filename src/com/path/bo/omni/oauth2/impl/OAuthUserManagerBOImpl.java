/**
 * Copyright 2018, Path Solutions
 * Path Solutions retains all ownership rights to this source code 
 * 
 * @author: gilbertandary
 *
 * OAuthUserManagerBOImpl.java
 *
 * 
 */
package com.path.bo.omni.oauth2.impl;

import com.path.bo.omni.common.base.OmniBaseBO;

public class OAuthUserManagerBOImpl extends OmniBaseBO
{

	/*private OAuthManagerService oAuthManagerService;

	public Client registerClient(OAuth2UserRegistration oAuth2UserRegistration, AdminParametersSC adminParametersSC)
	{
		String clientId = oAuth2UserRegistration.getClientId();
		String clientSecret = oAuth2UserRegistration.getClientSecret();

		Client newClient = new Client(clientId, clientSecret, true, oAuth2UserRegistration.getApplicationName(),
				oAuth2UserRegistration.getApplicationWebUri());
		oAuthManagerService.registerClient(newClient, adminParametersSC);

		return newClient;
	}

	public OmniServerAccessToken registerUserAndReturnToken(OAuth2UserRegistration oAuth2UserRegistration,
			AdminParametersSC adminParametersSC)
	{
		Client newClient = registerClient(oAuth2UserRegistration, adminParametersSC);
		AccessTokenRegistration reg = new AccessTokenRegistration();
		reg.setClient(newClient);
		reg.setGrantType(OmniCommonConstants.GRANT_RESOURCE_OWNER);
		reg.setRequestedScope(oAuth2UserRegistration.getPermittedOpers());
		reg.setApprovedScope(oAuth2UserRegistration.getPermittedOpers());
		OmniServerAccessToken token = oAuthManagerService.createAccessToken(reg,
				oAuth2UserRegistration);
		return token;
	}

	public UserManagerCO validateUser(OAuth2UserRegistration oAuth2UserRegistration)
	{
		return oAuthManagerService.validateToken(oAuth2UserRegistration);
	}

	public OmniServerAccessToken removeUserSession(OAuth2UserRegistration oAuth2UserRegistration)
	{
		return oAuthManagerService.removeUserSession(oAuth2UserRegistration);
	}

	public ConcurrentHashMap<String, OmniServerAccessToken> getTokenMap(String compCode)
	{
		return oAuthManagerService.getCompanyOauthTokens(compCode);
	}

	public HashMap<String, ConcurrentHashMap<String, OmniServerAccessToken>> getAllTokenMap()
	{
		return oAuthManagerService.getOauthTokens();
	}

	public OmniServerAccessToken returnAccessToken(OAuth2UserRegistration oAuth2UserRegistration)
	{
		return oAuthManagerService.getAccessToken(oAuth2UserRegistration);
	}

	public String returnAccessTokenOnly(OAuth2UserRegistration oAuth2UserRegistration)
	{
		if(returnAccessToken(oAuth2UserRegistration) != null)
		{
			return returnAccessToken(oAuth2UserRegistration).getTokenKey();
		}
		else
		{
			return "";
		}
	}

	public void setoAuthManagerService(OAuthManagerService oAuthManagerService)
	{
		this.oAuthManagerService = oAuthManagerService;
	}*/
}
