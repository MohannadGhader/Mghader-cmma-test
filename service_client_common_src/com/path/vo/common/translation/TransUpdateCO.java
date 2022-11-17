/**
 * 
 */
package com.path.vo.common.translation;

import java.util.List;

import com.path.lib.vo.BaseVO;

/**
 * Copyright 2013, Path Solutions
 * Path Solutions retains all ownership rights to this source code 
 * 
 * @author: rabihelkhatib
 *
 * TransUpdateCO.java used to store the Label translation updated values
 */
public class TransUpdateCO extends BaseVO
{
    private List<TranslationCO> addSysParamKeyLabelTransVO;	// used to store  label translation to be added
    private List<TranslationCO> modSysParamKeyLabelTransVO;	// used to store  label translation to be modified
    private List<TranslationCO> delSysParamKeyLabelTransVO;	// used to store  label translation to be deleted
    private List<TranslationCO> addSysParamKeyGroupTransVO;	// used to store  Group translation to be added
    private List<TranslationCO> modSysParamKeyGroupTransVO;	// used to store  Group translation to be modified
    private List<TranslationCO> delSysParamKeyGroupTransVO;	// used to store  Group translation to be deleted
    private List<TranslationCO> modSysParamLOVTransVO;		// used to store  LOV translation to be modified
    
    public List<TranslationCO> getAddSysParamKeyLabelTransVO()
    {
        return addSysParamKeyLabelTransVO;
    }
    public void setAddSysParamKeyLabelTransVO(List<TranslationCO> addSysParamKeyLabelTransVO)
    {
        this.addSysParamKeyLabelTransVO = addSysParamKeyLabelTransVO;
    }
    public List<TranslationCO> getModSysParamKeyLabelTransVO()
    {
        return modSysParamKeyLabelTransVO;
    }
    public void setModSysParamKeyLabelTransVO(List<TranslationCO> modSysParamKeyLabelTransVO)
    {
        this.modSysParamKeyLabelTransVO = modSysParamKeyLabelTransVO;
    }
    public List<TranslationCO> getDelSysParamKeyLabelTransVO()
    {
        return delSysParamKeyLabelTransVO;
    }
    public void setDelSysParamKeyLabelTransVO(List<TranslationCO> delSysParamKeyLabelTransVO)
    {
        this.delSysParamKeyLabelTransVO = delSysParamKeyLabelTransVO;
    }
    public List<TranslationCO> getAddSysParamKeyGroupTransVO()
    {
        return addSysParamKeyGroupTransVO;
    }
    public void setAddSysParamKeyGroupTransVO(List<TranslationCO> addSysParamKeyGroupTransVO)
    {
        this.addSysParamKeyGroupTransVO = addSysParamKeyGroupTransVO;
    }
    public List<TranslationCO> getModSysParamKeyGroupTransVO()
    {
        return modSysParamKeyGroupTransVO;
    }
    public void setModSysParamKeyGroupTransVO(List<TranslationCO> modSysParamKeyGroupTransVO)
    {
        this.modSysParamKeyGroupTransVO = modSysParamKeyGroupTransVO;
    }
    public List<TranslationCO> getDelSysParamKeyGroupTransVO()
    {
        return delSysParamKeyGroupTransVO;
    }
    public void setDelSysParamKeyGroupTransVO(List<TranslationCO> delSysParamKeyGroupTransVO)
    {
        this.delSysParamKeyGroupTransVO = delSysParamKeyGroupTransVO;
    }
    public List<TranslationCO> getModSysParamLOVTransVO()
    {
        return modSysParamLOVTransVO;
    }
    public void setModSysParamLOVTransVO(List<TranslationCO> modSysParamLOVTransVO)
    {
        this.modSysParamLOVTransVO = modSysParamLOVTransVO;
    }
}