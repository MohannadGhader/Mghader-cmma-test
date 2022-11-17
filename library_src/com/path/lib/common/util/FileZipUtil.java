/**
 * 
 */
package com.path.lib.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import org.apache.commons.io.FileUtils;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.io.ZipInputStream;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.unzip.UnzipUtil;
import net.lingala.zip4j.util.Zip4jConstants;

import com.path.lib.common.exception.BaseException;
import com.path.lib.log.Log;

/**
 * Copyright 2014, Path Solutions Path Solutions retains all ownership rights to
 * this source code
 * 
 * @author: marwanmaddah ZipFileUtil.java used to implement all the methods that
 *          are needed for zip/unzip with password protection management
 */
public final class FileZipUtil
{
    /**
     * Private constructor only to prevent instantiation in the class
     */
    private static final Log log = Log.getInstance();
    public static final String zipExt = ".zip";

    private FileZipUtil()
    {
	log.error("This Class Should not be Instantiated");
    }
   
    /**
     * Method to check whether the Zip is protected or not
     * @param srcZipFile zip file path under repository folder to check
     * @throws Exception
     * @return return true if file is protected
     */
    public static boolean checkIfZipProtected(String srcZipFile) throws Exception
    {
	return checkIfZipProtected(srcZipFile,true);
    }
    /**
     * Method to check whether the Zip is protected or not
     * @param srcZipFile zip file full path or under repository folder to check based on pathRelativeToRepository flag
     * @param pathRelativeToRepository boolean whether provided full path or to use repository 
     * @throws Exception
     * @return return true if file is protected
     */
    public static boolean checkIfZipProtected(String srcZipFile, boolean pathRelativeToRepository) throws Exception
    {
	if(srcZipFile == null)
	{
	    throw new BaseException("No Zip File Specified srcZipFile ");
	}

	String repositoryPath = pathRelativeToRepository ? FileUtil.getFileURLByName("repository").concat(File.separator) : "";
	String srcPath = repositoryPath.concat(srcZipFile);
	String srcZipFolder = srcPath;
	if(srcZipFile.toLowerCase(Locale.ENGLISH).lastIndexOf(zipExt) != (srcZipFile.length() - zipExt.length()))
	{
	    srcZipFolder = srcZipFolder.concat(zipExt);
	}
	try
	{
	    return new ZipFile(srcZipFolder).isEncrypted();
	}
	catch(Exception e)
	{
	    log.error(e, "Error in checking if Zip Folder with path path " + srcZipFolder);
	    throw e;
	}
    }
    /**
     * Method to extract provided file with path that is located under repository folder
     * @param filePathToExtract full path under repository folder
     * @param password protection password
     * @param pathRelativeToRepository boolean whether provided 
     * @return extracted folder path
     * @throws Exception
     */
    public static String extractProtectedZipFolder(String filePathToExtract, String password) throws Exception
    {
	return extractProtectedZipFolder(filePathToExtract, null, password, true);
    }
    /**
     * Method to extract provided file with path that is located either under repository folder or full path
     * @param filePathToExtract full path under repository folder
     * @param password protection password
     * @param pathRelativeToRepository boolean whether provided full path or to use repository 
     * @return extracted folder path
     * @throws Exception
     */
    public static String extractProtectedZipFolder(String filePathToExtract, String password, boolean pathRelativeToRepository) throws Exception
    {
	return extractProtectedZipFolder(filePathToExtract, null, password, pathRelativeToRepository);
    }
    /**
     * used to extract a zipped folder
     * 
     * @author marwanmaddah
     * @date Apr 30, 2014
     * @param filePathToExtract full file path to zip under repository or whole full path
     * @param destFolder destination folder under repository repository or under full path specified depending of pathRelativeToRepository flag
     * @param password pretection password , null if zip is not protected
     * @param pathRelativeToRepository  boolean whether filePathToExtract provided with full path or to use repository relative
     * @return extracted folder path
     */
    public static String extractProtectedZipFolder(String filePathToExtract,String destFolder, String password, boolean pathRelativeToRepository) throws Exception
    {
	return extractProtectedZipFolder(filePathToExtract, destFolder, password, pathRelativeToRepository, null);
    }
    /**
     * used to extract a zipped folder
     * 
     * @author marwanmaddah
     * @date Apr 30, 2014
     * @param filePathToExtract full file path to zip under repository or whole full path
     * @param destFolder destination folder under repository repository or under full path specified depending of pathRelativeToRepository flag
     * @param password pretection password , null if zip is not protected
     * @param pathRelativeToRepository  boolean whether filePathToExtract provided with full path or to use repository relative
     * @param specificFiles  List to specify only specific files to be extracted
     * @return extracted folder path
     */
    public static String extractProtectedZipFolder(String filePathToExtract,String destFolder, String password, boolean pathRelativeToRepository,List<String> specificFiles) throws Exception
    {
	/**
	 * Initiate the ZipFile
	 */
	String repositoryPath = pathRelativeToRepository ? FileUtil.getFileURLByName("repository").concat(File.separator) : "";
	String srcPath = repositoryPath.concat(filePathToExtract);
	String srcZipFolder = srcPath;
	if(filePathToExtract.toLowerCase(Locale.ENGLISH).lastIndexOf(zipExt) != (filePathToExtract.length() - zipExt.length())
	 && filePathToExtract.toLowerCase(Locale.ENGLISH).lastIndexOf(".war") != (filePathToExtract.length() - ".war".length()))
	{
	    srcZipFolder = srcZipFolder.concat(zipExt);
	}

	try
	{
	    ZipFile zipFile = new ZipFile(srcZipFolder);

	    if(zipFile.isEncrypted())
	    {
		if(password == null)
		{
		    throw new BaseException("Zip File is Protected while no password provided");
		}
		/**
		 * If zip file is password protected then set the password
		 */
		zipFile.setPassword(password);
	    }
	    String destinationPath = repositoryPath;
	    if(destFolder == null)
	    {
		destinationPath = srcZipFolder.substring(0, srcZipFolder.toLowerCase(Locale.ENGLISH).lastIndexOf(zipExt));
	    }
	    else
	    {
		destinationPath = destinationPath.concat(destFolder);
	    }

	    /**
	     * Get a list of FileHeader. FileHeader is the header information
	     * for all the files in the ZipFile
	     */
	    List<FileHeader> fileHeaderList = zipFile.getFileHeaders();
	    ZipInputStream is;
	    OutputStream os;
	    /**
	     * Loop through all the fileHeaders
	     */
	    for(int i = 0; i < fileHeaderList.size(); i++)
	    {
		FileHeader fileHeader = fileHeaderList.get(i);
		if(fileHeader != null)
		{
		    // check if the file not is in the list of file to be extracted if the list is not null, otherwise skip extracting
		    if(specificFiles != null
			    && (!specificFiles.contains(fileHeader.getFileName()) && !specificFiles.contains(fileHeader.getFileName().replace("\\", "/"))))
		    {
			continue;
		    }

		    /**
		     * Build the output file
		     */
		    String outFilePath = destinationPath + File.separator + fileHeader.getFileName();
		    File outFile = new PathFileSecure(outFilePath);

		    /**
		     * Checks if the file is a directory
		     */
		    if(fileHeader.isDirectory())
		    {

			boolean res = outFile.mkdirs();
			if(!res)
			{
			    log.warning("Directory " + outFilePath + " creation failed");
			}
			continue;
		    }

		    /**
		     * Check if the directories(including parent directories) in
		     * the output file path exists
		     */
		    File parentDir = outFile.getParentFile();
		    if(!parentDir.exists())
		    {
			boolean res = parentDir.mkdirs();
			if(!res)
			{
			    log.warning("Directory " + outFilePath + "parent Direction creation failed");
			}
		    }

		    /**
		     * Get the InputStream from the ZipFile
		     */
		    is = zipFile.getInputStream(fileHeader);
		    /**
		     * Initialize the output stream
		     */
		    os = new FileOutputStream(outFile);

		    int readLen = -1;
		    byte[] buff = new byte[4096];

		    /**
		     * Loop until End of File and write the contents to the
		     * output stream
		     */
		    while((readLen = is.read(buff)) != -1)
		    {
			os.write(buff, 0, readLen);
		    }

		    os.close();
		    is.close();

		    /**
		     * To restore File attributes (ex: last modified file time,
		     * read only flag, etc) of the extracted file, a utility
		     * class can be used as shown below
		     */
		    UnzipUtil.applyFileAttributes(fileHeader, outFile);

		    log.debug("Done extracting: " + fileHeader.getFileName());
		}
	    }
	    return destinationPath;
	}
	catch(Exception e)
	{
	    log.error(e, "Error in extracting Zip Folder with path path " + srcZipFolder
		    + " pathRelativeToRepository flag=" + pathRelativeToRepository);
	    throw e;
	}
    }
    /**
     * used to extract a zipped protected file located relative to repository
     * @param fileToExtract path relative to the repository of zip file to extract
     * @param password protection password, if null means not protected
     * @throws Exception
     * @return extracted file full path
     */
     public static String extractProtectedZipFile(String fileToExtract, String password) throws Exception
     {
	 return extractProtectedZipFile(fileToExtract, password, true);
     }
   /**
    * used to extract a zipped protected file
    * @param fileToExtract full path or path relative to the repository of zip file to extract base on pathRelativeToRepository flag
    * @param password protection password, if null means not protected
    * @param pathRelativeToRepository  boolean whether provided full path or to use repository
    * @throws Exception
    * @return extracted file full path
    */
    public static String extractProtectedZipFile(String fileToExtract, String password , boolean pathRelativeToRepository) throws Exception
    {
	String repositoryPath = pathRelativeToRepository ? FileUtil.getFileURLByName("repository").concat(
		File.separator) : "";
	String filePath = repositoryPath.concat(fileToExtract);
	if(filePath.toLowerCase(Locale.ENGLISH).lastIndexOf(zipExt) != (filePath.length() - zipExt.length()))
	{
	    filePath = filePath.concat(zipExt);
	}
	try
	{
	    /**
	     * Initiate ZipFile object with the path/name of the zip file.
	     */
	    ZipFile zipFile = new ZipFile(filePath);
	    if(zipFile.isEncrypted())
	    {
		if(password == null)
		{
		    throw new BaseException("Zip File is Protected while no password provided");
		}
		/**
		 * If zip file is password protected then set the password
		 */
		zipFile.setPassword(password);
	    }
	    String destinationPath = filePath.substring(0, filePath.lastIndexOf(zipExt));
	    /**
	     * Extracts all files to the path specified
	     */
	    zipFile.extractAll(destinationPath);
	    
	    return destinationPath;
	}
	catch(Exception e)
	{
	    log.error(e, "Error in extracting Zip File with path filePath " + filePath
		    + " pathRelativeToRepository flag=" + pathRelativeToRepository);
	    throw e;
	}
    }
    /**
     * used to generate a protected zip folder from the srcFolder under the same
     * location of the source folder. In case the password is null the folder
     * will be zipped without protection
     * 
     * @author marwanmaddah
     * @date Apr 30, 2014
     * @param srcFolder under repository location
     * @param password protection password to zip the folder
     * @return full path to generated Zip 
     */
    public static String generateProtectedZipFolder(String srcFolder, String password)throws Exception
    {
	return generateProtectedZipFolder(srcFolder,password, true);
    }
    /**
     * used to generate a protected zip folder from the srcFolder under the same
     * location of the source folder. In case the password is null the folder
     * will be zipped without protection
     * 
     * @author marwanmaddah
     * @date Apr 30, 2014
     * @param srcFolder under repository location or full path based on pathRelativeToRepository
     * @param password protection password to zip the folder
     * @param pathRelativeToRepository  boolean whether srcFolder provided full path or to use repository
     * @return full path to generated Zip 
     */
    public static String generateProtectedZipFolder(String srcFolder, String password, boolean pathRelativeToRepository)
	    throws Exception
    {
	return generateProtectedZipFolder(srcFolder,password,pathRelativeToRepository,true);
    }
    /**
     * used to generate a protected zip folder from the srcFolder under the same
     * location of the source folder. In case the password is null the folder
     * will be zipped without protection
     * 
     * @author marwanmaddah
     * @date Apr 30, 2014
     * @param srcFolder under repository location or full path based on pathRelativeToRepository
     * @param password protection password to zip the folder
     * @param pathRelativeToRepository  boolean whether srcFolder provided full path or to use repository
     * @param includeRootFolder boolean to specify whether to include srcFolder as root folder into zip (true), or to just zip contents of srcFolder(false) 
     * @return full path to generated Zip 
     */
    public static String generateProtectedZipFolder(String srcFolder, String password, boolean pathRelativeToRepository, boolean includeRootFolder)
	    throws Exception
    {
	/**
	 * Initiate ZipFile object with the path/name of the zip file.
	 */
	String repositoryPath = pathRelativeToRepository ? FileUtil.getFileURLByName("repository").concat(
		File.separator) : "";
	String theSourceFldr = srcFolder;
	if(theSourceFldr != null && ("/".equals(theSourceFldr.substring(theSourceFldr.length()-1)) ||
		"\\".equals(theSourceFldr.substring(theSourceFldr.length()-1))))
	{
	    theSourceFldr = theSourceFldr.substring(0,theSourceFldr.length()-1);
	}
	String dest = repositoryPath.concat(theSourceFldr).concat(zipExt);
	/**
	 * Folder to add
	 */
	String folderToZip = repositoryPath.concat(theSourceFldr);
	try
	{
	    ZipFile zipFile = new ZipFile(dest);


	    /**
	     * Initiate Zip Parameters which define various properties such as
	     * compression method, etc.
	     */
	    ZipParameters parameters = new ZipParameters();

	    /**
	     * set compression method to store compression
	     */
	    parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);

	    /**
	     * Set the compression level
	     */
	    parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
	    /**
	     * set whetehr to include root folder as folder into zipped file
	     */
	    parameters.setIncludeRootFolder(includeRootFolder);
	    /**
	     * in case the password passed as null the protection management
	     * will not be handled
	     */
	    if(password != null)
	    {
		/**
		 * Set the encryption flag to true If this is set to false, then
		 * the rest of encryption properties are ignored
		 */
		parameters.setEncryptFiles(true);

		/**
		 * Set the encryption method to AES Zip Encryption
		 */
		parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_AES);

		/**
		 * Set AES Key strength. Key strengths available for AES
		 * encryption are: AES_STRENGTH_128 - For both encryption and
		 * decryption AES_STRENGTH_256 - For both encryption and
		 * decryption
		 */
		parameters.setAesKeyStrength(Zip4jConstants.AES_STRENGTH_256);

		/**
		 * Set password
		 */
		parameters.setPassword(password);
	    }

	    /**
	     * Add folder to the zip file
	     */
	    zipFile.addFolder(folderToZip, parameters);
	    return dest;
	}
	catch(Exception e)
	{
	    log.error(e, "Error in generating Zip from folder with path folder path " + folderToZip
		    + " pathRelativeToRepository flag=" + pathRelativeToRepository);
	    throw e;
	}
    }
    /**
     * used to generate a protected zip file in case the password is null the
     * file will be zipped without protection. the Zip will generated under same location of srcFileName
     * 
     * @author marwanmaddah
     * @date Apr 30, 2014
     * @param srcFileName the full path under repository of the file that have
     * @param zipFileName the name path relative to repository of the file that to be zipped
     * @return full path to zipped file
     */
    public static String generateProtectedZipFile(String srcFileName, String zipFileName, String password)throws Exception
    {
	return generateProtectedZipFile(srcFileName,zipFileName,password, true);
    }
    /**
     * used to generate a protected zip file in case the password is null the
     * file will be zipped without protection.
     * 
     * @author marwanmaddah
     * @date Apr 30, 2014
     * @param srcFileName the full path or relative to repository(based on pathRelativeToRepository flag) of the file that have
     *            to be zipped.
     * @param zipFileName the name of the protected file that will be created
     * @param pathRelativeToRepository  boolean whether srcFolder provided full path or to use repository
     * @return full path to zipped file
     */
    public static String generateProtectedZipFile(String srcFileName, String zipFileName, String password,
	    boolean pathRelativeToRepository) throws Exception
    {
	return generateProtectedZipFileStndrEnc(srcFileName, zipFileName,  password,
		     pathRelativeToRepository,  false);
    }
    /**
     * used to generate a protected zip file in case the password is null the
     * file will be zipped without protection.
     * 
     * @author deniskhaddad
     * @date Apr 30, 2014
     * @param srcFileName the full path or relative to repository(based on pathRelativeToRepository flag) of the file that have
     *            to be zipped.
     * @param zipFileName the name of the protected file that will be created
     * @param pathRelativeToRepository  boolean whether srcFolder provided full path or to use repository
     * @param isStandardProt check is standard protection to be encrypted and not advanced, For PB application decompression the protection should be standard
     * @return full path to zipped file
     */
    public static String generateProtectedZipFileStndrEnc(String srcFileName, String zipFileName, String password,
	    boolean pathRelativeToRepository, boolean isStandardProt) throws Exception
    {
	String repositoryPath = pathRelativeToRepository ? FileUtil.getFileURLByName("repository").concat(
		File.separator) : "";
	/**
	 * Initiate ZipFile object with the path/name of the zip file.
	 */
	String outFilePath = repositoryPath.concat(zipFileName);
	if(outFilePath.toLowerCase(Locale.ENGLISH).lastIndexOf(zipExt) != (outFilePath.length() - zipExt.length()))
	{
	    outFilePath= outFilePath.concat(zipExt);
	}
	String fileToZipPath = repositoryPath.concat(srcFileName);
	try
	{
	    ZipFile outputZipFile = new ZipFile(outFilePath);
	    /**
	     * Build the files to be added
	     */
	    File fileToZip = new PathFileSecure(fileToZipPath);

	    /**
	     * Initiate Zip Parameters which define various properties such as
	     * compression method, etc.
	     */
	    ZipParameters parameters = new ZipParameters();

	    /**
	     * set compression method to store compression
	     */
	    if(isStandardProt)
	    {
		parameters.setCompressionMethod(Zip4jConstants.ENC_METHOD_STANDARD);
		parameters.setCompressionLevel(Zip4jConstants.ENC_METHOD_STANDARD);
	    }
	    else
	    {
		parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
		/**
		 * Set the compression level
		 */
		parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
	    }

	    /**
	     * in case the password passed as null the protection management
	     * will not be handled
	     */
	    if(password != null)
	    {
		/**
		 * set the protection informations
		 */
		parameters.setEncryptFiles(true);
		/**
		 * Set the encryption method to AES Zip Encryption
		 */
		 if(isStandardProt)
		 {
		     parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_STANDARD);
		     parameters.setAesKeyStrength(Zip4jConstants.ENC_METHOD_STANDARD);
		 }
		 else
		 {
		    parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_AES);
		    /**
		     * Set AES Key strength. Key strengths available for AES
		     * encryption are: AES_STRENGTH_128 - For both encryption
		     * and decryption AES_STRENGTH_256 - For both encryption and
		     * decryption
		     */
		    parameters.setAesKeyStrength(Zip4jConstants.AES_STRENGTH_256);
		 }
		
		parameters.setPassword(password);
	    }

	    outputZipFile.createZipFile(fileToZip, parameters);
	    return outFilePath;
	}
	catch(Exception e)
	{
	    log.error(e, "Error in generating Zip file from path  " + fileToZipPath + " pathRelativeToRepository flag="
		    + pathRelativeToRepository);
	    throw e;
	}
    }

    /**
     * used to generate protected zip file that will be contains a list of provided files (srcFileNames).
     * In case the password is null, the zipped file will be created without
     * protection. The generated file will be under in zipFileName path relative to repository location
     * 
     * @author marwanmaddah
     * @date May 2, 2014
     * @param srcFileNames list of Provided files path relative to repository
     * @param zipFileName file name path relative to repository location.
     * @param password protection password.
     * @return full path to the generated zipped file 
     */
    public static String generateProtectedZipListOfFile(String[] srcFileNames, String zipFileName, String password)throws Exception
    {
	return generateProtectedZipListOfFile(srcFileNames, zipFileName, password, true);
    }
    /**
     * used to generate protected zip file that will be contains a list of provided files (srcFileNames).
     * In case the password is null, the zipped file will be created without
     * protection. The generated file will be under in zipFileName full path or relative to repository location based on pathRelativeToRepository flag
     * 
     * @author marwanmaddah
     * @date May 2, 2014
     * @param srcFileNames list of Provided files path relative to repository
     * @param zipFileName zipped file name full path or relative to repository location based on pathRelativeToRepository flag.
     * @param password protection password.
     * @param pathRelativeToRepository  boolean whether srcFolder provided full path or to use repository
     * @return full path to generated zipped file
     */
    public static String generateProtectedZipListOfFile(String[] srcFileNames, String zipFileName, String password,
	    boolean pathRelativeToRepository) throws Exception
    {
	String repositoryPath = pathRelativeToRepository ? FileUtil.getFileURLByName("repository").concat(
		File.separator) : "";
	try
	{
	    String outFilePath = repositoryPath.concat(zipFileName);
	    if(outFilePath.toLowerCase(Locale.ENGLISH).lastIndexOf(zipExt) != (outFilePath.length() - zipExt.length()))
	    {
		outFilePath = outFilePath.concat(zipExt);
	    }
	    /**
	     * Initiate ZipFile object with the path/name of the zip file.
	     */
	    ZipFile outputZipFile = new ZipFile(outFilePath);

	    /**
	     * Build the list of files to be added in the array list Objects of
	     * type File have to be added to the ArrayList
	     */
	    ArrayList<File> filesToAdd = new ArrayList<File>();
	    for(int i = 0; i < srcFileNames.length; i++)
	    {
		String fileToZipPath = repositoryPath.concat(srcFileNames[i]);
		filesToAdd.add(new PathFileSecure(fileToZipPath));
	    }

	    /**
	     * Initiate Zip Parameters which define various properties such as
	     * compression method, etc.
	     */
	    ZipParameters parameters = new ZipParameters();

	    /**
	     * set compression method to store compression
	     */
	    parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);

	    /**
	     * Set the compression level. This value has to be in between 0 to 9
	     */
	    parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);

	    /**
	     * in case the password passed as null the protection management
	     * will not be handled
	     */
	    if(password != null)
	    {
		/**
		 * set the protection informations
		 */
		parameters.setEncryptFiles(true);
		/**
		 * Set the encryption method to AES Zip Encryption
		 */
		parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_AES);
		parameters.setAesKeyStrength(Zip4jConstants.AES_STRENGTH_256);
		parameters.setPassword(password);
	    }

	    outputZipFile.createZipFile(filesToAdd, parameters);
	    return outFilePath;
	}
	catch(Exception e)
	{
	    StringBuffer theFiles =  new StringBuffer();
	    for(String fileName : srcFileNames)
	    {
		theFiles.append(fileName);
	    }
	    log.error(e, "Error in generating Zip file from list of Files  " + theFiles.toString()
		    + " pathRelativeToRepository flag=" + pathRelativeToRepository);
	    throw e;
	}
    }
    
    /**
     * split the zip file over several files when the size exceeds a particular limit
     * @param part_limit_size (long) : minimum of 65536 bytes (64kb)
     * @param parts_prefix_name (String) : the name of the new created parts
     * @param source_files_list ( List(String) ) : the content of files that should
     *            be splitted into parts (should be under same location) (the content should be Base64 Encoded as string)
     * 
     * @output parts_number (int) : number of parts
     * @output generated_parts_list (List(byte[])) : the list of parts generated after splitting, including zipModel and/or added parts 
     * in case they were engendered
     * after splitting at the end we will get multiple (if the new size
     *         is greater than the part_limit_size ) parts will be generated as
     *         below : output.zip, output.z01, output.z02...
     */
    public static HashMap<String, Object> splitZipFile(HashMap<String, Object> hm) throws Exception 
    {
	try
	{
	    // Initiate ZipFile object with the path/name of the zip file.
	    Long maxPartSize = (Long) hm.get("part_limit_size");
	    // if the size is not defined then do nothing
	    String zippingDir = returnXmlRepository();

	    String partName = (String) hm.get("parts_prefix_name");
	    // if no name is defined, set "output" as default name
	    partName = StringUtil.nullEmptyToValue(partName, "output");
	    ZipFile zipFile = new ZipFile(
		    zippingDir.concat(File.separator).concat(partName).concat(zipExt));

	    // Build the list of files to be added in the array list
	    // Objects of type File have to be added to the ArrayList
	    List<String> listOfFiles = (ArrayList<String>) hm.get("source_files_list");
	    if(listOfFiles != null)
	    {
		ArrayList<PathFileSecure> filesToAdd = new ArrayList<PathFileSecure>();

		// Initiate Zip Parameters which define various properties such
		// as compression method, etc.
		ZipParameters parameters = new ZipParameters();

		// set compression method to store compression
		parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
		// Set the compression level. This value has to be in between 0 to 9
		parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);

		    String fileContent = listOfFiles.get(0);
		    String decodedContent = SecurityUtils.decodeB64(fileContent.getBytes(FileUtil.DEFAULT_FILE_ENCODING));

		    StringBuffer fileName = new StringBuffer(zippingDir);
		    fileName.append(File.separator).append(partName).append(".xml");
		    PathFileSecure f = new PathFileSecure(fileName.toString());
		    FileUtil.writeFile(f.getCanonicalPath(), decodedContent);
		    filesToAdd.add(f);

		// Create a split file by setting splitArchive parameter to true
		// and specifying the splitLength. SplitLenth has to be
		// greater than 65536 bytes
		// Please note: If the zip file already exists, then this method throws an exception

		if(NumberUtil.isNumberPositive(maxPartSize))
		{
		    // in case we should split the file
		    long splitLength = maxPartSize * FileUtils.ONE_MB;
		    zipFile.createZipFile(filesToAdd, parameters, true, splitLength);
		}
		else
		{
		    // otherwise generate a standard zip file
		    zipFile.createZipFile(filesToAdd, parameters);
		}

		// delete temporary in the end, no more needed
		f.delete();

		int partsNumber = zipFile.getSplitZipFiles().size();
		hm.put("parts_number", partsNumber);
		List<byte[]> generatedPartsList = new ArrayList<byte[]>();
		for(int partIndex = 0; partIndex < partsNumber; partIndex++)
		{
		    String partLocation = (String) zipFile.getSplitZipFiles().get(partIndex);
		    generatedPartsList.add(FileUtils.readFileToByteArray(new PathFileSecure(partLocation)));
		}
		hm.put("generated_parts_list", generatedPartsList);
	    }
	}
	catch(Exception e)
	{
	    log.error(e,"Error in FileZipUtil.splitZipFile"); 
	    throw e;
	}
	return hm;
    }
    
    
    /**
     * this method generates and creates a unique folder to save temporary files under server repository
     * @return new folder path
     * @throws Exception
     */
    private static String returnXmlRepository() throws Exception
    {
	StringBuffer repositoryLocation =  new StringBuffer( FileUtil.getFileURLByName("repository"));
	repositoryLocation.append(File.separator);
	//append a specific folder for xml zip management
	repositoryLocation.append("xmlZipManagement");
	repositoryLocation.append(File.separator);
	//generate a unique time ID
	String uniqueID = UUID.randomUUID().toString();
	repositoryLocation.append(File.separator);
	repositoryLocation.append(uniqueID);
	PathFileSecure currentFolder = new PathFileSecure(repositoryLocation.toString());
	if(!currentFolder.exists())
	{
	    //use mkdirs to create multiple level folders, most probably xmlZipManagement folder does not exist first time
	    currentFolder.mkdirs();
	}
	currentFolder.deleteOnExit();
	log.info("[returnXmlRepository]: currentFolderPath: " + repositoryLocation);
	return repositoryLocation.toString();
    }
    
    
    /**
     * @description this method merges multiple splitted parts and generates a new combined zip file
     * noting that parts and related zip should have same naming convention, e.g : filezip.zip, filezip.z01, filezip.z02
     * @param file_parts_list : contains the model zip in addition to list of parts (in case it exists)
     * @param output_zip_name : the name of the new generated file
     * 
     * @output generated_zip_file (byte[]) : the new merged zip converted as String base64
     */
    public static HashMap<String, Object> mergeZipParts(HashMap<String, Object> hm) throws Exception
    {
	List<byte[]> filePartsList = (List<byte[]>) hm.get("file_parts_list");
	String outputZipName = (String) hm.get("output_zip_name");
	//if no name is defined, set "output" as default name
	outputZipName = StringUtil.nullEmptyToValue(outputZipName, "output").concat(zipExt);

	try
	{
	    String currentFolderPath = returnXmlRepository();
	    if(filePartsList != null)
	    {
		for(int index = 0 ; index < filePartsList.size() ; index++)
		{
		    byte[] fileContent = filePartsList.get(index);
		    StringBuffer fileName = new StringBuffer(currentFolderPath);
		    fileName.append(File.separator).append("tempFile");
		    
		    //this file is just created and used to differentiate between model zip and parts
		    PathFileSecure tempFile = new PathFileSecure(currentFolderPath + File.separator + "dd.zip");
		    tempFile.deleteOnExit();
		    FileUtils.writeByteArrayToFile(tempFile, fileContent);
		    ZipFile z = new ZipFile(tempFile);
		    if(z.isValidZipFile())
		    {
			fileName.append(zipExt);
		    }
		    else
		    {
			//parts .z01, .z02
			String partIndex = NumberUtil.addLeadingZeros((index + 1 ), 2);
			fileName.append(".z").append(partIndex);
		    }
		    PathFileSecure f = new PathFileSecure(fileName.toString());
		    FileUtil.writeFile(f.getCanonicalPath(), fileContent, true);
		    f.deleteOnExit();
		}
	    }
	    // the new zip name after merging the contents of all related parts
	    ZipFile zipFile = new ZipFile(currentFolderPath.concat(File.separator).concat("tempFile.zip"));
	    if(zipFile.isSplitArchive())
	    {
		String fileName = zipFile.getFile().getParent().concat(File.separator).concat( outputZipName);
		PathFileSecure newOutputZipFileName = new PathFileSecure(fileName);
		zipFile.mergeSplitFiles(newOutputZipFileName);
		byte[] arr = FileUtils.readFileToByteArray(zipFile.getFile());
		hm.put("generated_zip_file", SecurityUtils.encodeB64(arr));
	    }
	}
	catch(Exception e)
	{
	    log.error(e,"Error in FileZipUtil.mergeZipParts");
	    throw e;
	}
	return hm;
    }
}
