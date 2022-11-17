package com.path.vo.common.mandatorylanguage;

public class MandatoryLanguage
{
   boolean englishMandatory;
   String arabicDescriptionVisible;
   String language;


public String getLanguage()
{
    return language;
}
public void setLanguage(String language)
{
    this.language = language;
}
public String getArabicDescriptionVisible()
{
    return arabicDescriptionVisible;
}
public void setArabicDescriptionVisible(String arabicDescriptionVisible)
{
    this.arabicDescriptionVisible = arabicDescriptionVisible;
}
public boolean isEnglishMandatory()
{
    return englishMandatory;
}
public void setEnglishMandatory(boolean englishMandatory)
{
    this.englishMandatory = englishMandatory;
}

}
