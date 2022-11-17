package com.path.dao.omni.common;

import java.math.BigDecimal;
import java.util.List;

import com.path.lib.common.exception.BaseException;
import com.path.lib.common.exception.DAOException;
import com.path.vo.omni.common.OmniCommonCO;
import com.path.vo.omni.common.base.OmniBaseSC;

/**
 * 
 * Copyright 2017, Path Solutions Path Solutions retains all ownership rights to
 * this source code
 * 
 * @author: ChadiAssaf
 *
 *          OmniCommonDAO.java used to
 */
public interface OmniCommonDAO
{

    /**
     * Method that returns Next value of sequence of a table
     * 
     * @param omniBaseSC
     * @return
     * @throws BaseException
     */
    public int returnNextValOfSequence(OmniBaseSC omniBaseSC) throws BaseException;

    /**
     * return Sequence of Original Record
     * 
     * @param omniBaseSC
     * @return
     * @throws BaseException
     */
    public int returnRelatedSequence(OmniBaseSC omniBaseSC) throws BaseException;
    

    /**
     * update Ref Code of Table even if it's null
     * 
     * @param omniBaseSC
     * @return
     * @throws BaseException
     */
    public int updateRefCode(OmniBaseSC omniBaseSC) throws BaseException;

    /**
     * update Ref Code of Table even if it's null
     * 
     * @param omniBaseSC
     * @return
     * @throws BaseException
     */
    public List<OmniCommonCO> returnAppChannels(OmniBaseSC omniBaseSC) throws BaseException;




    /**
     * Added by Neena Method that returnsApplication list Count
     * 
     * @param criteria
     * @return int number of records
     * @throws DAOException
     */
    public int returnApplicationListCount(OmniBaseSC omniBaseSC) throws BaseException;

    /**
     * Method that returns Application list
     * 
     * @param criteria
     * @return OmniCommonCO list of records
     * @throws DAOException
     */
    public List returnApplicationList(OmniBaseSC omniBaseSC) throws BaseException;


    /**
     * @author gilbertandary
     * 
     *         Method that logs service into designated table
     * 
     * @param servicePropertiesSC
     * @return
     * @throws DAOException
     * @throws Exception
     * @throws BaseException
     */
    public Object saveService(Object request, Object baseObject) throws Exception;


    /**
     * @author KhaledAlTimany
     * @param returnAllowedByChannelByApp
     * @throws DAOException
     * @return BigDecimal
     */
    public BigDecimal returnAllowedByChannelByApp(OmniBaseSC omniBaseSc) throws DAOException;

    public Object insertIntoDB(Object vo) throws DAOException;
    
}
