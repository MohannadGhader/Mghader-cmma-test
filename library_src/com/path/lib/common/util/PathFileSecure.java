package com.path.lib.common.util;

import java.io.BufferedReader;
import java.io.File;

import org.apache.commons.io.FileUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import com.path.lib.common.exception.BaseException;
/**
 * 
 * Copyright 2018, Path Solutions
 * Path Solutions retains all ownership rights to this source code 
 * 
 * @author: DeniskHaddad
 *
 * PathFileSecure.java used to Perform Secure File Object initialization and protect against Path Traversal attacks
 */
public class PathFileSecure extends File
{
    private static final long serialVersionUID = 1L;
    /**
     * Call the File Class constructor and additional checking on the Path PAtterns
     * @param thePath
     * @throws Exception
     */
    public PathFileSecure(String thePath) throws Exception
    {
	super(thePath);
	checkCanonicalPath(thePath);
	checkParentAndRelativePath(thePath);
    }
    /**
     * Call the File Class constructor and additional checking on the Path Patterns of Parent and Child directories.
     * @param theDirParent
     * @param theChild
     * @throws Exception
     */
    public PathFileSecure(String theDirParent, String theChild) throws Exception
    {
	super(theDirParent, theChild);
	String thePath = theChild;
	if(theDirParent != null)
	{
	    String theTempParentPath = theDirParent.trim();
	    // add file separator if parent path not ends with it and child pat do not start with it
	    if((!theTempParentPath.endsWith("/") && !theTempParentPath.endsWith("\\"))
		&& (theChild != null && !theChild.startsWith("/") && !theChild.startsWith("\\") ))
	    {
		theTempParentPath = theTempParentPath.concat(File.separator);
	    }
	    thePath = theTempParentPath.concat(theChild);
	}
	checkCanonicalPath(thePath);
	checkParentAndRelativePath(thePath);
    }
    /**
     * Check if Path contain ../ relative PAth
     * @param thePath
     * @throws Exception
     */
    private void checkParentAndRelativePath(String thePath) throws Exception
    {
	if(thePath.contains("../"))
	{
	    String theCleanPath = StringUtils.cleanPath(thePath);
	    if(theCleanPath.contains("../"))
	    {
		throw new BaseException("PathFileSecure Path Not Allowed (" + thePath + ") , Path Contains Parent Access ../");
	    }
	}
	
	if(thePath.contains(":/"))
	{
	    String relativePath = (thePath.charAt(0) == '/' ? thePath.substring(1) : thePath);
	    if(ResourceUtils.isUrl(relativePath) || relativePath.toLowerCase().startsWith("url:"))
	    {
		throw new BaseException("PathFileSecure Path Not Allowed (" + thePath + ") , Path represents URL or has \"url:\" prefix.");

	    }
	}
    }
    /**
     * check if  path not matching with canonical Path
     * @param thePath
     * @throws Exception
     */
    private void checkCanonicalPath(String thePath) throws Exception
    {
	String curPath = thePath;
	String canonPath = this.getCanonicalPath();
	// replace any consecutive double / or \ with single / 
	curPath = curPath.replaceAll("[\\\\/]+", "/");
	canonPath = canonPath.replaceAll("[\\\\/]+", "/");
	//remove last / from both paths if exist
	if(curPath.substring(curPath.length()-1).equals("/"))
	{
	    curPath = curPath.substring(0, curPath.length()-1);
	}
	if(canonPath.substring(canonPath.length()-1).equals("/"))
	{
	    canonPath = canonPath.substring(0, canonPath.length()-1);
	}
	
	// "." means working directory needed for Logs and Others.
	if(!curPath.startsWith("./") && !".".equals(curPath) && !curPath.equalsIgnoreCase(canonPath))
	{
	    throw new BaseException("PathFileSecure Path Not Allowed ("+curPath+") , Did not Match Canonical Path ("+canonPath+")");
	}
    }
    
    /**
     * function used to check on file size before reading the file and returning the byte array.
     * this function should be used to prevent AppDos attacks
     * @param file : the file which we should read it's bytes
     * @param fileSizeLimit : the file size limit in bytes
     * @return
     * @throws Exception
     */
    public static byte[] readFileToByteArray(File file, long fileSizeLimit) throws Exception
    {
	if(file != null)
	{
	    long size = file.length();
	    if(size > fileSizeLimit)
	    { 
		throw new BaseException("PathFileSecure File too large , File size in bytes exceeds the limit " + fileSizeLimit);
	    }
	    return FileUtils.readFileToByteArray(file);
	}
	return null;
    }
   
    /**
     * Do not use this method anymore, it will be removed, user PathBufferedReaderSecure object instead
     * function used to check the length of the read line from the bufferedreader applied on the file. 
     * in case the line length exceeds the limits an exception is thrown.   
     * @param bufferedReader
     * @param lineSizeLimit : the accepted limit of the line defined in bytes
     * @return
     * @throws Exception
     */
    @Deprecated // use PathBufferReaderSecure instead
    public static String readLine(BufferedReader bufferedReader, long lineSizeLimit) throws Exception
    {
	if(bufferedReader != null)
	{
	    String line = bufferedReader.readLine();
	    if(line != null && line.getBytes().length > lineSizeLimit)
	    {
		throw new BaseException("PathFileSecure Line length is too large , File line length in bytes exceeds the limit " + lineSizeLimit);
	    }
	    return line;
	}
	return null;
    }
}
