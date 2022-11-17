package com.path.dao.omni.common.impl;

import java.math.BigDecimal;
import java.util.List;

import com.path.dao.omni.common.OmniCommonDAO;
import com.path.dao.omni.common.base.OmniBaseDAO;
import com.path.lib.common.exception.BaseException;
import com.path.lib.common.exception.DAOException;
import com.path.lib.common.util.DAOHelper;
import com.path.lib.common.util.StringUtil;
import com.path.vo.omni.common.OmniCommonCO;
import com.path.vo.omni.common.base.OmniBaseSC;

/**
 * 
 * Copyright 2017, Path Solutions Path Solutions retains all ownership rights to
 * this source code
 * 
 * @author: ChadiAssaf
 *
 *          OmniCommonDAOImpl.java used to
 */
public class OmniCommonDAOImpl extends OmniBaseDAO implements OmniCommonDAO
{
    /**
     * return return Sequence Next Value
     */
    public int returnNextValOfSequence(OmniBaseSC omniBaseSC) throws BaseException
    {
	return ((Integer) getSqlMap().queryForObject("omniCommonMapper.returnNextValOfSequence", omniBaseSC))
		.intValue();
    }

    /**
     * return Sequence of Original Record
     */
    public int returnRelatedSequence(OmniBaseSC omniBaseSC) throws BaseException
    {
	Integer relSeq = (Integer) getSqlMap().queryForObject("omniCommonMapper.returnRelatedSequence", omniBaseSC);
	return relSeq == null ? 0 : relSeq.intValue();
    }



    /**
     * update Ref Code of Table even if it's null
     */
    public int updateRefCode(OmniBaseSC omniBaseSC) throws BaseException
    {
	return getSqlMap().update("omniCommonMapper.updateRefCode", omniBaseSC);
    }

    /**
     * returnAppChannels
     */
    public List<OmniCommonCO> returnAppChannels(OmniBaseSC omniBaseSC) throws BaseException
    {
	return getSqlMap().queryForList("omniCommonMapper.returnAppChannels", omniBaseSC);
    }


    


    @Override
    public int returnApplicationListCount(OmniBaseSC omniBaseSC) throws BaseException
    {
	DAOHelper.fixGridMaps(omniBaseSC, getSqlMap(), "omniCommonMapper.resApplicationMap");
	return ((Integer) getSqlMap().queryForObject("omniCommonMapper.returnApplicationListCount", omniBaseSC))
		.intValue();
    }

    @Override
    public List returnApplicationList(OmniBaseSC omniBaseSC) throws BaseException
    {
	if(StringUtil.nullToEmpty(omniBaseSC.getSidx()).isEmpty())
	{
	    omniBaseSC.setSidx("APP_ID");
	    omniBaseSC.setSord("ASC");
	}
	DAOHelper.fixGridMaps(omniBaseSC, getSqlMap(), "omniCommonMapper.resApplicationMap");
	if(omniBaseSC.getNbRec() == -1)
	{
	    return getSqlMap().queryForList("omniCommonMapper.returnApplicationList", omniBaseSC);
	}
	else
	{
	    return getSqlMap().queryForList("omniCommonMapper.returnApplicationList", omniBaseSC,
		    omniBaseSC.getRecToskip(), omniBaseSC.getNbRec());
	}
    }

    /**
     * @author KhaledAlTimany
     * @param returnAllowedByChannelByApp
     * @throws DAOException
     * @return BigDecimal
     */
    public BigDecimal returnAllowedByChannelByApp(OmniBaseSC omniBaseSc) throws DAOException
    {
	return (BigDecimal) getSqlMap().queryForObject("omniCommonMapper.returnAllowedByChannelByApp", omniBaseSc);
    }

    /**
     * @author gilbertandary
     * @throws DAOException
     * 
     */

    public Object insertIntoDB(Object vo) throws DAOException
    {

	return getSqlMap().queryForObject("omniCommonMapper.insertIntoDB", vo);
    }

    @Override
    public Object saveService(Object request, Object baseObject) throws Exception
    {
	// TODO Auto-generated method stub
	return null;
    }


}
