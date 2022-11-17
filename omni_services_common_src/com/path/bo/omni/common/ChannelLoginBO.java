package com.path.bo.omni.common;

import java.math.BigInteger;
import java.security.PublicKey;

import com.path.ws.omni.vo.common.ChannelLoginRequest;

/**
 * 
 * Copyright 2017, Path Solutions Path Solutions retains all ownership rights to
 * this source code
 * 
 * @author hibaoweidat
 *
 *         ChannelLoginBO.java used to login user by channel
 */
public interface ChannelLoginBO
{
    
    public boolean authenticateUser(ChannelLoginRequest request) throws Exception;

    public boolean storeFrontEndPublicKeyKey(BigInteger frontEndPublicKey ) throws Exception;
    
    
}
