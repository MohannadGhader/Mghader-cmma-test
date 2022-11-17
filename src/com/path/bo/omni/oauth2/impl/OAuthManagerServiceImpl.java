/**
 * Copyright 2018, Path Solutions
 * Path Solutions retains all ownership rights to this source code 
 * 
 * @author: gilbertandary
 *
 * OAuthManagerServiceImpl.java
 *
 * 
 */
package com.path.bo.omni.oauth2.impl;

import java.util.List;

import org.apache.cxf.rs.security.oauth2.common.AccessTokenRegistration;
import org.apache.cxf.rs.security.oauth2.common.Client;
import org.apache.cxf.rs.security.oauth2.common.OAuthPermission;
import org.apache.cxf.rs.security.oauth2.common.ServerAccessToken;
import org.apache.cxf.rs.security.oauth2.common.UserSubject;
import org.apache.cxf.rs.security.oauth2.provider.OAuthDataProvider;
import org.apache.cxf.rs.security.oauth2.provider.OAuthServiceException;
import org.apache.cxf.rs.security.oauth2.tokens.refresh.RefreshToken;

import com.path.bo.omni.common.base.OmniBaseBO;

public class OAuthManagerServiceImpl extends OmniBaseBO implements OAuthDataProvider
{

    @Override
    public List<OAuthPermission> convertScopeToPermissions(Client arg0, List<String> arg1)
    {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public ServerAccessToken createAccessToken(AccessTokenRegistration arg0) throws OAuthServiceException
    {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public ServerAccessToken getAccessToken(String arg0) throws OAuthServiceException
    {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public List<ServerAccessToken> getAccessTokens(Client arg0, UserSubject arg1) throws OAuthServiceException
    {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Client getClient(String arg0) throws OAuthServiceException
    {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public ServerAccessToken getPreauthorizedToken(Client arg0, List<String> arg1, UserSubject arg2, String arg3)
	    throws OAuthServiceException
    {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public List<RefreshToken> getRefreshTokens(Client arg0, UserSubject arg1) throws OAuthServiceException
    {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public ServerAccessToken refreshAccessToken(Client arg0, String arg1, List<String> arg2)
	    throws OAuthServiceException
    {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void removeAccessToken(ServerAccessToken arg0) throws OAuthServiceException
    {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void revokeToken(Client arg0, String arg1, String arg2) throws OAuthServiceException
    {
	// TODO Auto-generated method stub
	
    }
	/*protected ConcurrentHashMap<String, Client> registeredClientsMap = new ConcurrentHashMap<String, Client>();

	//protected HashMap<String, ConcurrentHashMap<String, OmniServerAccessToken>> oauthTokens = new HashMap<String, ConcurrentHashMap<String, OmniServerAccessToken>>();


	private long tokenLifeTime = 3600L;

	public void registerClient(Client c)
	{
		if(!registeredClientsMap.contains(c.getClientId()))
		{			
			registeredClientsMap.put(c.getClientId(), c);
		}
	}

	public Client getClient(String clientId) throws OAuthServiceException
	{
		return registeredClientsMap.get(clientId);
	}

	public void registerClient(Client c, AdminParametersSC adminParametersSC)
	{
		try
		{
			List<AdminParametersCO> adminParametersCOs = adminParametersBO.returnParameterValues(adminParametersSC);
			for(AdminParametersCO co : adminParametersCOs)
			{
				if(co.getParameterName().equalsIgnoreCase("clearCacheDuration"))
				{
					tokenLifeTime = Long.parseLong(co.getParameterVal());
				}
			}
		}
		catch(BaseException e)
		{
			e.printStackTrace();
		}
		registerClient(c);
	}

	public OmniServerAccessToken createAccessToken(AccessTokenRegistration reg, OAuth2UserRegistration credentials)
			throws OAuthServiceException
	{

		ServerAccessToken tempToken = new BearerAccessToken(reg.getClient(), tokenLifeTime);

		OmniServerAccessToken token = new OmniServerAccessToken();

		try
		{
			PathPropertyUtil.copyProperties(tempToken, token);
		}
		catch(BaseException e)
		{
			pathlog.error(e, e.getMessage());
		}

		token.setPermittedOpers(credentials.getPermittedOpers());
		
		List<String> scope = reg.getApprovedScope().isEmpty() ? reg.getRequestedScope() : reg.getApprovedScope();
		token.setScopes(convertScopeToPermissions(reg.getClient(), scope));
		token.setGrantType(reg.getGrantType());

		ConcurrentHashMap<String, OmniServerAccessToken> temp = oauthTokens.get(reg.getClient().getClientId());

		if(temp != null)
		{
			temp.put(credentials.getUserName(), token);
		}
		else
		{
			temp = new ConcurrentHashMap<String, OmniServerAccessToken>();
			temp.put(credentials.getUserName(), token);
			oauthTokens.put(reg.getClient().getClientId(), temp);
		}

		return token;
	}

	*//**
	 * return the token by tokenId
	 *//*
	public OmniServerAccessToken getAccessToken(OAuth2UserRegistration oAuth2UserRegistration)
			throws OAuthServiceException
	{
		String clientId = oAuth2UserRegistration.getClientId();
		String userName = oAuth2UserRegistration.getUserName();
		ConcurrentHashMap<String, OmniServerAccessToken> map = oauthTokens.get(clientId);
		if(map != null)
		{
			return map.get(userName);
		}
		return null;
	}

	public void removeAccessToken(OmniServerAccessToken token) throws OAuthServiceException
	{
		if(token != null)
		{
			String clientId = token.getClient().getClientId();
			String userName = token.getClient().getClientId();
			ConcurrentHashMap<String, OmniServerAccessToken> map = oauthTokens.get(clientId);
			if(map != null)
			{
				map.remove(userName);
			}
			oauthTokens.replace(clientId, map);
		}
	}

	public OmniServerAccessToken removeUserSession(OAuth2UserRegistration oAuth2UserRegistration)
	{
		String clientId = oAuth2UserRegistration.getClientId();
		String userName = oAuth2UserRegistration.getUserName();
		ConcurrentHashMap<String, OmniServerAccessToken> map = oauthTokens.get(clientId);
		OmniServerAccessToken token = null;
		if(map != null)
		{
			token = map.remove(userName);
		}
		oauthTokens.replace(clientId, map);
		return token;
	}

	public UserManagerCO validateToken(OAuth2UserRegistration oAuth2UserRegistration)
	{
		OmniServerAccessToken token = getAccessToken(oAuth2UserRegistration);

		UserManagerCO co = new UserManagerCO();

		if(token != null)
		{
			long issuedAt = token.getIssuedAt();
			long expiresIn = token.getExpiresIn();

			boolean isExpired = OAuthUtils.isExpired(issuedAt, expiresIn);
			boolean hasOper = true;
			pathlog.info("Expired:? " + isExpired);

			ArrayList<String> permittedOpers = token.getPermittedOpers();
			BigDecimal currentOperId = oAuth2UserRegistration.getOperId();

			if(permittedOpers != null && currentOperId != null)
			{
				if(!permittedOpers.contains(currentOperId))
				{
					hasOper = false;
				}
				if(currentOperId.equals(new BigDecimal(1475)))
				{
					hasOper = true;
				}
			}
			// && (hasOper)
			co.setValid((!isExpired));

		}
		else
		{
			co.setValid(false);
		}
		// Timestamp stamp = new Timestamp();
		return co;
	}

	public ConcurrentHashMap<String, OmniServerAccessToken> getCompanyOauthTokens(String clientId)
	{
		ConcurrentHashMap<String, OmniServerAccessToken> map = oauthTokens.get(clientId);
		return map;
	}

	public List<OAuthPermission> convertScopeToPermissions(Client client, List<String> scopes)
	{
		List<OAuthPermission> list = new ArrayList<OAuthPermission>();
		for(int i=0;i<scopes.size();i++)
		{			
			OAuthPermission permission = new OAuthPermission(scopes.get(i));
			list.add(permission);
		}
		return list;
	}

	public ConcurrentHashMap<String, Client> getRegisteredClientsMap()
	{
		return registeredClientsMap;
	}

	public void setRegisteredClientsMap(ConcurrentHashMap<String, Client> registeredClientsMap)
	{
		this.registeredClientsMap = registeredClientsMap;
	}

	public long getTokenLifeTime()
	{
		return tokenLifeTime;
	}

	public void setTokenLifeTime(long tokenLifeTime)
	{
		this.tokenLifeTime = tokenLifeTime;
	}

	public HashMap<String, ConcurrentHashMap<String, OmniServerAccessToken>> getOauthTokens()
	{
		return oauthTokens;
	}

	public void setOauthTokens(HashMap<String, ConcurrentHashMap<String, OmniServerAccessToken>> oauthTokens)
	{
		this.oauthTokens = oauthTokens;
	}

	
	 * (non-Javadoc)
	 * 
	 * @see org.apache.cxf.rs.security.oauth2.provider.OAuthDataProvider#
	 * createAccessToken(org.apache.cxf.rs.security.oauth2.common.
	 * AccessTokenRegistration)
	 
	@Override
	public ServerAccessToken createAccessToken(AccessTokenRegistration arg0) throws OAuthServiceException
	{
		// TODO Auto-generated method stub
		return null;
	}

	
	 * (non-Javadoc)
	 * 
	 * @see org.apache.cxf.rs.security.oauth2.provider.OAuthDataProvider#
	 * getAccessToken(java.lang.String)
	 
	@Override
	public ServerAccessToken getAccessToken(String arg0) throws OAuthServiceException
	{
		// TODO Auto-generated method stub
		return null;
	}

	
	 * (non-Javadoc)
	 * 
	 * @see org.apache.cxf.rs.security.oauth2.provider.OAuthDataProvider#
	 * getAccessTokens(org.apache.cxf.rs.security.oauth2.common.Client,
	 * org.apache.cxf.rs.security.oauth2.common.UserSubject)
	 
	@Override
	public List<ServerAccessToken> getAccessTokens(Client arg0, UserSubject arg1) throws OAuthServiceException
	{
		// TODO Auto-generated method stub
		return null;
	}

	
	 * (non-Javadoc)
	 * 
	 * @see org.apache.cxf.rs.security.oauth2.provider.OAuthDataProvider#
	 * getPreauthorizedToken(org.apache.cxf.rs.security.oauth2.common.Client,
	 * java.util.List, org.apache.cxf.rs.security.oauth2.common.UserSubject,
	 * java.lang.String)
	 
	@Override
	public ServerAccessToken getPreauthorizedToken(Client arg0, List<String> arg1, UserSubject arg2, String arg3)
			throws OAuthServiceException
	{
		// TODO Auto-generated method stub
		return null;
	}

	
	 * (non-Javadoc)
	 * 
	 * @see org.apache.cxf.rs.security.oauth2.provider.OAuthDataProvider#
	 * getRefreshTokens(org.apache.cxf.rs.security.oauth2.common.Client,
	 * org.apache.cxf.rs.security.oauth2.common.UserSubject)
	 
	@Override
	public List<RefreshToken> getRefreshTokens(Client arg0, UserSubject arg1) throws OAuthServiceException
	{
		// TODO Auto-generated method stub
		return null;
	}

	
	 * (non-Javadoc)
	 * 
	 * @see org.apache.cxf.rs.security.oauth2.provider.OAuthDataProvider#
	 * refreshAccessToken(org.apache.cxf.rs.security.oauth2.common.Client,
	 * java.lang.String, java.util.List)
	 
	@Override
	public ServerAccessToken refreshAccessToken(Client arg0, String arg1, List<String> arg2)
			throws OAuthServiceException
	{
		// TODO Auto-generated method stub
		return null;
	}

	
	 * (non-Javadoc)
	 * 
	 * @see org.apache.cxf.rs.security.oauth2.provider.OAuthDataProvider#
	 * removeAccessToken(org.apache.cxf.rs.security.oauth2.common.
	 * ServerAccessToken)
	 
	@Override
	public void removeAccessToken(ServerAccessToken arg0) throws OAuthServiceException
	{
		// TODO Auto-generated method stub

	}

	
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.cxf.rs.security.oauth2.provider.OAuthDataProvider#revokeToken(
	 * org.apache.cxf.rs.security.oauth2.common.Client, java.lang.String,
	 * java.lang.String)
	 
	@Override
	public void revokeToken(Client arg0, String arg1, String arg2) throws OAuthServiceException
	{
		// TODO Auto-generated method stub

	}*/

}
