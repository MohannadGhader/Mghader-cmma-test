package com.path.lib.vo;

import java.util.ArrayList;

/**
 * Copyright 2014, Path Solutions
 * Path Solutions retains all ownership rights to this source code 
 * 
 * @author: deniskhaddad
 *
 * GridUpdates.java used to Holds grid added, modified, and deleted records.
 */
public class GridUpdates
{
 private final ArrayList lstAdd, lstModify;
private ArrayList lstDelete, lstAllRec;
/**
 * Default constructor
 *
 */
 public GridUpdates() 
 {
  lstAdd = new ArrayList();
  lstModify = new ArrayList();
  lstDelete = new ArrayList();
  lstAllRec = new ArrayList();
 }
/**
 * Get lstAdd property
 * @return Arraylist
 */
 public ArrayList getLstAdd()
 {
  return lstAdd;
 }
/**
 * Get lstModify property
 * @return ArrayList
 */
 public ArrayList getLstModify()
 {
  return lstModify;
 }
public ArrayList getLstDelete()
{
    return lstDelete;
}
public void setLstDelete(ArrayList lstDelete)
{
    this.lstDelete = lstDelete;
}
/**
 * @return the lstAllRec
 */
public ArrayList getLstAllRec()
{
    return lstAllRec;
}
/**
 * @param lstAllRec the lstAllRec to set
 */
public void setLstAllRec(ArrayList lstAllRec)
{
    this.lstAllRec = lstAllRec;
}
}
