/**
 * @author MarwanMaddah
 */
package com.path.lib.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FilenameUtils;

import com.path.lib.common.exception.BaseException;
import com.path.lib.log.Log;

public final class FileUtil {

	public static final String DEFAULT_FILE_ENCODING = "UTF8";
	private static final Log log = Log.getInstance();

	/**
	 * Private constructor only to prevent instantiation in the class
	 */
	private FileUtil() {
		log.error("This Class Should not be Instantiated");
	}

	public static byte[] generateZipFromFldr(String fldPath) throws Exception {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ZipOutputStream zos = new ZipOutputStream(bos);

		generateRecursiveEntries(fldPath, "", zos);
		zos.finish();
		zos.flush();
		zos.close();

		byte[] ret = bos.toByteArray();
		bos.close();
		return ret;
	}

	private static void generateRecursiveEntries(String fldPath, String zipEntryPath, ZipOutputStream zos)
			throws Exception {
		File zfile = new PathFileSecure(fldPath);
		if (zfile.exists()) {

			File[] files = zfile.listFiles();
			ZipEntry zen = null;
			FileInputStream inpSt = null;
			String zipEntryFullPath;
			byte[] bwRead = null;
			for (int i = 0; i < files.length; ++i) {
				zipEntryFullPath = zipEntryPath + files[i].getName();
				if (zipEntryPath.length() > 0) {
					zipEntryFullPath = zipEntryPath + "/" + files[i].getName();
				}
				if (files[i].isDirectory()) {
					generateRecursiveEntries(files[i].getCanonicalPath(), zipEntryFullPath, zos);
				} else {

					zen = new ZipEntry(zipEntryFullPath);
					zos.putNextEntry(zen);

					inpSt = new FileInputStream(files[i]);
					if (inpSt.available() != 0) {
						bwRead = new byte[inpSt.available()];
						inpSt.read(bwRead);
						zos.write(bwRead);
					}
					inpSt.close();
					zos.closeEntry();
				}
			}
		}
	}

	public static void deleteFolder(String temporaryFldr) throws Exception {
		File f = new PathFileSecure(temporaryFldr);
		if (f.exists()) {
			File[] listFiles = f.listFiles();
			for (int i = 0; i < listFiles.length; i++) {
				if (listFiles[i].isDirectory()) {
					deleteFolder(listFiles[i].getCanonicalPath());
					if (listFiles[i].exists()) {
						boolean res = listFiles[i].delete();
						if (!res) {
							Log.getInstance().warning("File " + listFiles[i] + " Deleteion failed");
						}
					}
				} else {
					boolean res = listFiles[i].delete();
					if (!res) {
						Log.getInstance().warning("File " + listFiles[i].getCanonicalPath() + " Deleteion failed");
					}
				}
			}
			boolean res = f.delete();
			if (!res) {
				Log.getInstance().warning("File " + f.getCanonicalPath() + " Deleteion failed");
			}

		} else {
			Log.getInstance().error("Error in Deleting Folder (does not exist) " + temporaryFldr);
		}
	}

	public static File[] returnDirectories(String folderName) {
		try {
			return returnDirectories(new PathFileSecure(folderName));
		} catch (Exception e) {
			Log.getInstance().error(e, "Error in returnDirectories for " + folderName);
			return null;
		}
	}

	public static File[] returnDirectories(File folderName) {
		// This filter only returns directories
		FileFilter fileFilter = new FileFilter() {
			public boolean accept(File file) {
				return file.isDirectory();
			}
		};
		return folderName.listFiles(fileFilter);

	}

	public static File[] returnFilesOnly(File folderName) {
		// This filter only returns directories
		FileFilter fileFilter = new FileFilter() {
			public boolean accept(File file) {
				return !file.isDirectory();
			}
		};
		return folderName.listFiles(fileFilter);
	}

	/**
	 * check if the provided bytes are in Zip format
	 * 
	 * @param contentBytes
	 * @return true if zip contents
	 */
	public static boolean checkIfZip(byte[] contentBytes) {
		boolean result = false;
		try {

			ZipInputStream zis = new ZipInputStream(new ByteArrayInputStream(contentBytes));
			if (zis.getNextEntry() != null) {
				result = true;
			}
			zis.close();
		} catch (IOException e) {
			Log.getInstance().error(e, "Error checking Zip Contents");
		}
		return result;
	}

	public static String getFileURLByName(String name) {
		Log.getInstance().trace("Entering into getFileURLByName in FileUtil with repository name:" + name);
		String thePath = "";
		try {

			String pathProp = System.getProperty("path." + name);
			if (pathProp == null) {
				if (System.getProperties().getProperty("catalina.home") == null) {
					// try to lookup repository from server such as
					// Websphere,...
					try {
						thePath = (String) (new javax.naming.InitialContext()).lookup(name);
						Log.getInstance().trace("FileUtil JNDI " + name + " lookup succeded return value " + thePath);
					} catch (Exception e) {
						Log.getInstance().info("[FileUtil] Repository not exists in the initial context");
						thePath = null;
					}
				} else {
					// Tomcat Server
					try {
						thePath = (String) (new javax.naming.InitialContext()).lookup("java:comp/env/" + name);
					} catch (Exception e) {
						Log.getInstance().info("[FileUtil]: no recource for repository in the context.xml");
						thePath = null;
					}
				}
				/**
				 * [MarwanMaddah]: In case thePath is null, will check is exists in the
				 * pathRemoting.properties file
				 */
				if (StringUtil.nullToEmpty(thePath).isEmpty()) {

					thePath = PathPropertyUtil.returnPathPropertyFromFile("PathRemoting", "path.server.repository");
					if (StringUtil.nullToEmpty(thePath).isEmpty()) {
						thePath = (new File(".")).getAbsolutePath();
						if (thePath.substring(thePath.length() - 2).equals(File.separator + ".")) {
							thePath = thePath.substring(0, thePath.length() - 2);
						}
						thePath = thePath.concat(File.separator).concat(name);
						Log.getInstance().trace("FileUtil JNDI " + name + " lookup Failed return value " + thePath);
					}
				}
			} else {
				Log.getInstance().trace("getFileURLByName detected an path.repository value:" + pathProp);
				thePath = pathProp;
			}
		} catch (Exception ex) {
			Log.getInstance().error(ex, "[FileUtil] Error caught while calling method getFileURLByName(" + name + ")");
			return System.getProperties().getProperty("path.server.repository");
		}
		Log.getInstance().debug("The Returned Path is : " + thePath);
		return thePath;
	}

	/**
	 * Method for returning Bytes Array Encoding
	 * 
	 * @param theBytes
	 * @return
	 */
	public static String returnByteEncoding(byte[] theBytes) throws BaseException {
		String encoding = null;
		try {
			encoding = UnicodeUtil.returnByteEncoding(theBytes);
		} catch (Exception e) {
			log.error("Error in reading Byte Array Encoding method returnByteEncoding in FielUtil");
			throw new BaseException("Error in reading Byte Array Encoding method returnByteEncoding in FielUtil", e);
		}
		return encoding;
	}

	/**
	 * Method for converting bytes to provided encoding possible encoding are:
	 * UTF-8, UTF-16BE, UTF-16LE,UTF-32BE,UTF-32LE
	 * 
	 * @param theBytes          bytes to Convert
	 * @param encodingToConvert needed encoding
	 * @return
	 * @throws BaseException
	 */
	public static byte[] convertByteToEncoding(byte[] theBytes, String encodingToConvert) throws BaseException {
		byte[] result;
		try {
			result = UnicodeUtil.convert(theBytes, encodingToConvert);
		} catch (Exception e) {
			log.error("Error in convering Byte Array to Encoding" + encodingToConvert
					+ " method convertByteToEncoding in FielUtil");
			throw new BaseException("Error in convering Byte Array to Encoding" + encodingToConvert
					+ " method convertByteToEncoding in FielUtil", e);
		}
		return result;
	}

	/**
	 * Saves a file (uploaded file) to a repository
	 * 
	 * @param fileContent
	 * @param repository
	 * @param fileName
	 */
	public static void saveToRepository(byte[] fileContent, String repository, String fileName) {
		String path = getFileURLByName(repository) + fileName;
		try {
			FileOutputStream fOut = new FileOutputStream(path);
			fOut.write(fileContent);
			fOut.close();
		} catch (Exception e) {
			Log.getInstance().error(e, "Error in function : saveToRepository, class: FileUtil");
		}
	}

	public static void makeDirectories(String dirPath) throws Exception {
		File fl = new PathFileSecure(dirPath);
		if (!fl.exists()) {
			boolean res = fl.mkdirs();
			if (!res) {
				Log.getInstance().warning("Directory " + dirPath + " creation failed");
			}
		}
	}

	/**
	 * 
	 * Used for writing file content with File Default Encoding (UTF8)
	 * 
	 * @param filePath - uri path of the file
	 * @param content  - content of the file in String format
	 * @throws Exception
	 */
	public static void writeFile(String filePath, String content) throws Exception {
		writeFile(filePath, content, DEFAULT_FILE_ENCODING);
	}

	/**
	 * 
	 * Used for write content to specific File with Encoding
	 * 
	 * @param filePath - uri path of the file
	 * @param content  - content of the file in String format
	 * @param encoding - charset to encode the string
	 * @throws Exception
	 */
	public static void writeFile(String filePath, String content, String encoding) throws Exception {
		writeFile(filePath, content, encoding, false);
	}

	/**
	 * Write file into directory with override option and using the default charset
	 * 
	 * @param filePath - uri path of the file
	 * @param content  - content of the file in String format
	 * @param override - specifiy if the content should override the old data in
	 *                 case the file was existed
	 * @throws Exception
	 */
	public static void writeFile(String filePath, String content, boolean override) throws Exception {
		writeFile(filePath, content, DEFAULT_FILE_ENCODING, override);
	}

	/**
	 * Write file into directory with override option
	 * 
	 * @param filePath - uri path of the file
	 * @param content  - content of the file in String format
	 * @param encoding - charset to encode the string
	 * @param override - specifiy if the content should override the old data in
	 *                 case the file was existed
	 * @throws BaseException
	 */
	public static void writeFile(String filePath, String content, String encoding, boolean override) throws Exception {
		byte[] data = content.getBytes(encoding);
		writeFile(filePath, data, override);
	}

	/**
	 * Write file with byte array data with override and encoding options
	 * 
	 * @param filePath - uri path of the file
	 * @param content  - content of the file in byte array format
	 * @param override - specifiy if the content should override the old data in
	 *                 case the file was existed
	 * @throws Exception
	 */
	public static void writeFile(String filePath, byte[] content, boolean override) throws Exception {
		Path path = Paths.get(filePath);

		// We can write files but we can't write directories
		Path parentPath = path.getParent();
		if (Files.notExists(parentPath)) {
			Files.createDirectories(parentPath);
		}

		if (override) {
			Files.write(path, content, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
		} else {
			Files.write(path, content, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
		}
	}

	/**
	 * 
	 * Used for reading file with defined encoding
	 * 
	 * @param filePath
	 * @param encoding
	 * @return
	 * @throws Exception
	 */
	public static String readFile(String filePath, String encoding) throws Exception {
		byte[] cont = readFileBytes(filePath);
		return new String(cont, encoding);
	}

	/**
	 * 
	 * Used for reading file bytes
	 * 
	 * @param filePath
	 * @return byte[] of file
	 * @throws Exception
	 */
	public static byte[] readFileBytes(String filePath) throws Exception {
		return Files.readAllBytes(Paths.get(filePath));
	}

	/**
	 * 
	 * Used for reading file
	 * 
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	public static String readFile(String filePath) throws Exception {
		return readFile(filePath, DEFAULT_FILE_ENCODING);
	}

	public static boolean existFile(String filePath) throws Exception {
		return Files.exists(Paths.get(filePath));
	}

	public static void copyFolder(String srcFldr, String destPath) throws Exception {
		File srcFld = new PathFileSecure(srcFldr);
		File[] lstFiles = srcFld.listFiles();
		File curFile, newFile;
		String newDirOrFile;
		FileChannel srcChannel, destChannel;

		newFile = new PathFileSecure(destPath);
		if (!newFile.exists()) {
			boolean res = newFile.mkdirs();
			if (!res) {
				Log.getInstance().warning("Directory " + newFile + " creation failed");
			}
		}

		for (int i = 0; i < lstFiles.length; i++) {
			curFile = lstFiles[i];

			newDirOrFile = destPath + File.separator + curFile.getName();
			if (curFile.isDirectory()) {
				newFile = new PathFileSecure(newDirOrFile);
				boolean res = newFile.mkdir();
				if (!res) {
					Log.getInstance().warning("Directory " + newFile.getCanonicalPath() + " creation failed");
				}
				copyFolder(curFile.getCanonicalPath(), newDirOrFile);
			} else {
				FileInputStream fis = new FileInputStream(curFile);
				FileOutputStream fos = new FileOutputStream(newDirOrFile);
				srcChannel = fis.getChannel();
				destChannel = fos.getChannel();
				long size = srcChannel.size();
				destChannel.transferFrom(srcChannel, 0, size);

				srcChannel.close();
				destChannel.close();
				fis.close();
				fos.close();
			}
		}
	}

	/**
	 * return all files inside a folder with its nested folder recursively
	 * 
	 * @param theFolder the folder which files to be returned
	 * @return
	 */
	public static ArrayList<File> retunFilesInFolderRec(File theFolder) {
		ArrayList<File> lstFilesRet = new ArrayList<File>();

		if (theFolder != null && theFolder.isDirectory()) {
			// read all files inside the folder
			File[] folderFiles = FileUtil.returnFilesOnly(theFolder);
			lstFilesRet.addAll(Arrays.asList(folderFiles));

			// read folders inside the folder
			File[] folderFldrs = FileUtil.returnDirectories(theFolder);
			for (int i = 0; i < folderFldrs.length; i++) {
				lstFilesRet.addAll(retunFilesInFolderRec(folderFldrs[i]));
			}
		}
		return lstFilesRet;
	}

	/**
	 * checks if a given fileName ends with any of the given extensions
	 * 
	 * @param filename
	 * @param extensions
	 * @return true - if fileName ends with any of the extensions. false -
	 *         otherwise.
	 */
	public static boolean isExtension(String filename, Collection<String> extensions) {
		return FilenameUtils.isExtension(filename, extensions);
	}

	/**
	 * checks if a given fileName ends with the given extension
	 * 
	 * @param filename
	 * @param extension
	 * @return true - if fileName ends with the given extension. false - otherwise.
	 */
	public static boolean isExtension(String filename, String extension) {
		return FilenameUtils.isExtension(filename, extension);
	}

	/**
	 * checks if a given fileName ends with any of the given extensions
	 * 
	 * @param filename
	 * @param extensions
	 * @return true - if fileName ends with any of the extensions. false -
	 *         otherwise.
	 */
	public static boolean isExtension(String filename, String... extensions) {
		return FilenameUtils.isExtension(filename, extensions);
	}

}
