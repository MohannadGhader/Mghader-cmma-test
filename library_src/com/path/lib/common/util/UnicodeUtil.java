package com.path.lib.common.util;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PushbackInputStream;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Arrays;

import org.mozilla.universalchardet.UniversalDetector;

public abstract class UnicodeUtil
{

    public static String returnByteEncoding(byte[] bytes) throws Exception
    {
//	UnicodeInputStream uis = new UnicodeInputStream(new ByteArrayInputStream(bytes), "ASCII");
//	String encoding = uis.getEncoding();
//	uis.close();
	byte[] buf = new byte[4096];
	ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
	UniversalDetector detector = new UniversalDetector(null);
	int nread;
	while((nread = bis.read(buf)) > 0 && !detector.isDone())
	{
	    detector.handleData(buf, 0, nread);
	}
	detector.dataEnd();
	String encoding = detector.getDetectedCharset();
	if(encoding == null)
	{
	    encoding = "ASCII";
	}
	detector.reset();
	bis.close();
	return encoding;
    }

    public static byte[] convert(byte[] bytes, String encout) throws Exception
    {
	// Workaround for bug that will not be fixed by SUN
	// http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=4508058
	UnicodeInputStream uis = new UnicodeInputStream(new ByteArrayInputStream(bytes), "ASCII");
	boolean unicodeOutputReqd = (getBOM(encout) == null) ? false : true;
	String enc = uis.getEncoding();
	String BOM = getBOM(enc); // get the BOM of the inputstream

	if(BOM == null)
	{
	    // inputstream looks like ascii...
	    // create a BOM based on the outputstream
	    BOM = getBOM(encout);
	}
	uis.close();

	ByteArrayOutputStream out = new ByteArrayOutputStream();
	ByteArrayInputStream temp = new ByteArrayInputStream(bytes, uis.getBOMOffset(), bytes.length);

	// Denisk check if utf8
	if(BOM != null 
	   && BOM.equals(new String(new byte[] { (byte) 0xEF, (byte) 0xBB, (byte) 0xBF }, enc))
	   // check if last delimiter is same for UTF8
	   && (byte) 0xBF == bytes[bytes.length - 1] 
	   && (byte) 0xBB == bytes[bytes.length - 2]
	   && (byte) 0xEF == bytes[bytes.length - 3])
	{
	    // remove trail and leading utf8 character , considering always
	    // Exists
	    temp = new ByteArrayInputStream(bytes, uis.getBOMOffset(), bytes.length - 2 * (uis.getBOMOffset()));
	}

	// BufferedReader br = new BufferedReader(new InputStreamReader(new
	// ByteArrayInputStream(bytes, uis.getBOMOffset(), bytes.length), enc));
	BufferedReader br = new BufferedReader(new InputStreamReader(temp, enc));
	Writer w = new BufferedWriter(new OutputStreamWriter(out, encout));

	// dont write a BOM for ascii(out) as the OutputStreamWriter
	// will not process it correctly.
	if(BOM != null && unicodeOutputReqd)
	{
	    w.write(BOM);
	}

	char[] buffer = new char[4096];
	int len;
	while((len = br.read(buffer)) != -1)
	{
	    w.write(buffer, 0, len);
	}

	br.close(); // Close the input.
	w.close(); // Flush and close output.
	return out.toByteArray();
    }

    public static String getBOM(String enc) throws UnsupportedEncodingException
    {
	if("UTF8".equals(enc) || "UTF-8".equals(enc))
	{
	    byte[] bom = new byte[3];
	    bom[0] = (byte) 0xEF;
	    bom[1] = (byte) 0xBB;
	    bom[2] = (byte) 0xBF;
	    return new String(bom, enc);
	}
	else if("UTF-16BE".equals(enc))
	{
	    byte[] bom = new byte[2];
	    bom[0] = (byte) 0xFE;
	    bom[1] = (byte) 0xFF;
	    return new String(bom, enc);
	}
	else if("UTF-16LE".equals(enc))
	{
	    byte[] bom = new byte[2];
	    bom[0] = (byte) 0xFF;
	    bom[1] = (byte) 0xFE;
	    return new String(bom, enc);
	}
	else if("UTF-32BE".equals(enc))
	{
	    byte[] bom = new byte[4];
	    bom[0] = (byte) 0x00;
	    bom[1] = (byte) 0x00;
	    bom[2] = (byte) 0xFE;
	    bom[3] = (byte) 0xFF;
	    return new String(bom, enc);
	}
	else if("UTF-32LE".equals(enc))
	{
	    byte[] bom = new byte[4];
	    bom[0] = (byte) 0x00;
	    bom[1] = (byte) 0x00;
	    bom[2] = (byte) 0xFF;
	    bom[3] = (byte) 0xFE;
	    return new String(bom, enc);
	}
	else
	{
	    return null;
	}

    }

    /**
     * 
     * @param enc
     * @return
     * @throws UnsupportedEncodingException
     */
    public static byte[] getBOMBytes(String enc) throws UnsupportedEncodingException
    {
	if("UTF8".equals(enc) || "UTF-8".equals(enc))
	{
	    byte[] bom = new byte[3];
	    bom[0] = (byte) 0xEF;
	    bom[1] = (byte) 0xBB;
	    bom[2] = (byte) 0xBF;
	    return bom;
	}
	else if("UTF-16BE".equals(enc))
	{
	    byte[] bom = new byte[2];
	    bom[0] = (byte) 0xFE;
	    bom[1] = (byte) 0xFF;
	    return bom;
	}
	else if("UTF-16LE".equals(enc))
	{
	    byte[] bom = new byte[2];
	    bom[0] = (byte) 0xFF;
	    bom[1] = (byte) 0xFE;
	    return bom;
	}
	else if("UTF-32BE".equals(enc))
	{
	    byte[] bom = new byte[4];
	    bom[0] = (byte) 0x00;
	    bom[1] = (byte) 0x00;
	    bom[2] = (byte) 0xFE;
	    bom[3] = (byte) 0xFF;
	    return bom;
	}
	else if("UTF-32LE".equals(enc))
	{
	    byte[] bom = new byte[4];
	    bom[0] = (byte) 0x00;
	    bom[1] = (byte) 0x00;
	    bom[2] = (byte) 0xFF;
	    bom[3] = (byte) 0xFE;
	    return bom;
	}
	else
	{
	    return new byte[0];
	}
    }
    
    
    /**
     * addBOMToBytes Adds BOM definition to the Byte Array if needed, and not
     * already available
     * 
     * @param byte[]
     * @param String encoding
     * @return byte[] with BOM
     * @throws UnsupportedEncodingException
     */
    public static byte[] addBOMToBytes(byte[] scriptByte, String enc) throws UnsupportedEncodingException
    {
	byte[] utfBOMBytes = null;
	int utfBOMLen = 0;
	if(enc != null && !enc.isEmpty() && !"ASCII".equals(enc))
	{
	    utfBOMBytes = UnicodeUtil.getBOMBytes(enc);
	    utfBOMLen = utfBOMBytes.length;
	}
	if(utfBOMLen > 0)
	{
	    int scriptByteLen = scriptByte.length;
	    // comparing if the bytes already containing BOM, check only if
	    // bytes bigger than BOM length
	    if(scriptByteLen >= utfBOMLen)
	    {
		boolean bomNotExist = false;
		for(int j = 0; j < utfBOMLen; j++)
		{
		    if(utfBOMBytes[j] != scriptByte[j])
		    {
			bomNotExist = true;
		    }
		}
		if(bomNotExist)
		{
		    byte[] utfMessage = new byte[utfBOMLen + scriptByteLen];
		    //copying BOM bytes to new Array
		    System.arraycopy(utfBOMBytes, 0, utfMessage, 0, utfBOMLen);
		    //copying initial bytes date to new Array at position after the BOM bytes
		    System.arraycopy(scriptByte, 0, utfMessage, utfBOMLen, scriptByteLen);
		    return utfMessage;
		}
	    }
	}
	return scriptByte;
    }
    
    /**
     * removeBOMFromBytes removes BOM definition from the Byte Array if not needed, and 
     * already available
     * 
     * @param byte[]
     * @param String encoding
     * @return byte[] with BOM
     * @throws UnsupportedEncodingException
     */
    public static byte[] removeBOMFromBytes(byte[] scriptByte, String enc) throws UnsupportedEncodingException
    {
	byte[] utfBOMBytes = null;
	int utfBOMLen = 0;
	if(enc != null && !enc.isEmpty() && !"ASCII".equals(enc))
	{
	    utfBOMBytes = UnicodeUtil.getBOMBytes(enc);
	    utfBOMLen = utfBOMBytes.length;
	}
	if(utfBOMLen > 0)
	{
	    int scriptByteLen = scriptByte.length;
	    // comparing if the bytes already containing BOM, check only if
	    // bytes bigger than BOM length
	    if(scriptByteLen >= utfBOMLen)
	    {
		boolean bomExist = true;
		for(int j = 0; j < utfBOMLen; j++)
		{
		    if(utfBOMBytes[j] != scriptByte[j])
		    {
			bomExist = false;
		    }
		}
		//if a BOM exists the remove it from the byte array
		if(bomExist)
		{
		    byte[] utfMessage = new byte[scriptByteLen - utfBOMLen];
		    utfMessage = Arrays.copyOfRange(scriptByte, utfBOMLen, scriptByteLen);
		    return utfMessage;
		}
	    }
	}
	return scriptByte;
    }
    
    public final static class UnicodeInputStream extends InputStream
    {
	private final PushbackInputStream internalIn;

	private boolean isInited;

	private int BOMOffset = -1;

	private final String defaultEnc;

	private String encoding;

	public static final int BOM_SIZE = 4;

	public UnicodeInputStream(InputStream in, String defaultEnc)
	{
	    internalIn = new PushbackInputStream(in, BOM_SIZE);
	    this.defaultEnc = defaultEnc;
	}

	public String getDefaultEncoding()
	{
	    return defaultEnc;
	}

	public String getEncoding()
	{
	    if(!isInited)
	    {
		try
		{
		    init();
		}
		catch(IOException ex)
		{
		    IllegalStateException ise = new IllegalStateException("Init method failed.",ex);
		    ise.initCause(ise);
		    throw ise;
		}
	    }
	    return encoding;
	}

	/**
	 * Read-ahead four bytes and check for BOM marks. Extra bytes are unread
	 * back to the stream, only BOM bytes are skipped.
	 */
	protected void init() throws IOException
	{
	    if(isInited)
	    {
		return;
	    }

	    byte bom[] = new byte[BOM_SIZE];
	    int n, unread;
	    n = internalIn.read(bom, 0, bom.length);

	    if((bom[0] == (byte) 0x00) && (bom[1] == (byte) 0x00) && (bom[2] == (byte) 0xFE) && (bom[3] == (byte) 0xFF))
	    {
		encoding = "UTF-32BE";
		unread = n - 4;
	    }
	    else if((bom[0] == (byte) 0xFF) && (bom[1] == (byte) 0xFE) && (bom[2] == (byte) 0x00)
		    && (bom[3] == (byte) 0x00))
	    {
		encoding = "UTF-32LE";
		unread = n - 4;
	    }
	    else if((bom[0] == (byte) 0xEF) && (bom[1] == (byte) 0xBB) && (bom[2] == (byte) 0xBF))
	    {
		encoding = "UTF-8";
		unread = n - 3;
	    }
	    else if((bom[0] == (byte) 0xFE) && (bom[1] == (byte) 0xFF))
	    {
		encoding = "UTF-16BE";
		unread = n - 2;
	    }
	    else if((bom[0] == (byte) 0xFF) && (bom[1] == (byte) 0xFE))
	    {
		encoding = "UTF-16LE";
		unread = n - 2;
	    }
	    else
	    {
		// Unicode BOM mark not found, unread all bytes
		encoding = defaultEnc;
		unread = n;
	    }
	    BOMOffset = BOM_SIZE - unread;
	    if(unread > 0)
	    {
		internalIn.unread(bom, (n - unread), unread);
	    }

	    isInited = true;
	}

	@Override
	public void close() throws IOException
	{
	    // init();
	    isInited = true;
	    internalIn.close();
	}

	@Override
	public int read() throws IOException
	{
	    // init();
	    isInited = true;
	    return internalIn.read();
	}

	public int getBOMOffset()
	{
	    return BOMOffset;
	}
    }

}
