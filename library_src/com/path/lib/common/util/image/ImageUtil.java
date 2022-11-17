package com.path.lib.common.util.image;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

import com.path.lib.common.exception.BaseException;
import com.path.lib.log.Log;

/**
 * 
 * Copyright 2015, Path Solutions Path Solutions retains all ownership rights to
 * this source code
 * 
 * @author: deniskhaddad
 * 
 *          ImageUtil.java used to perform different operation on Image
 */
public final class ImageUtil
{
    private static final Log log = Log.getInstance();

    /**
     * Private constructor only to prevent instantiation in the class
     */
    private ImageUtil()
    {
	log.error("This Class Should not be Instantiated");
    }
    /**
     * Method used to compress contents of the image
     * @param inputImgBytes byte Array of image contents
     * @return new byte array if compressed size less than input size otherwise input bytes are returned
     * @throws BaseException Exception if error occurred
     */
    public static byte[] compressImageBytes(byte[] inputImgBytes) throws BaseException
    {
	try
	{
	    ByteArrayInputStream bInpStrm =  new ByteArrayInputStream(inputImgBytes);
	    BufferedImage bufferedImage = ImageIO.read(bInpStrm);
	    byte[] newImgCont = compressAndShow(bufferedImage, 0.3f);
	    
	    log.info("Compression of image succeeded OLD size = "+inputImgBytes.length+" New Size ="+newImgCont.length);
	    if(newImgCont.length > inputImgBytes.length)
	    {
		newImgCont = inputImgBytes;
		log.info("New Compressed size returning Size ="+newImgCont.length);
	    }
	    bInpStrm.close();
	    return newImgCont;
	}
	catch(Exception e)
	{
	    log.error(e, "Error in Image compressImageBytes Method ImageUtil");
	    throw new BaseException("Error in Image compressImageBytes Method ImageUtil", e);
	}
    }

    private static byte[] compressAndShow(BufferedImage image, float quality) throws IOException
    {
	// Get a ImageWriter for jpeg format.
	Iterator<ImageWriter> writers = ImageIO.getImageWritersBySuffix("jpeg");
	if(!writers.hasNext())
	{
	    throw new IllegalStateException("No writers found");
	}
	ImageWriter writer = writers.next();
	// Create the ImageWriteParam to compress the image.
	ImageWriteParam param = writer.getDefaultWriteParam();
	param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
	param.setCompressionQuality(quality);
	// The output will be a ByteArrayOutputStream (in memory)
	ByteArrayOutputStream bos = new ByteArrayOutputStream(32768);
	ImageOutputStream ios = ImageIO.createImageOutputStream(bos);
	writer.setOutput(ios);
	writer.write(null, new IIOImage(image, null, null), param);
	ios.flush(); // otherwise the buffer size will be zero!
	ios.close();
	ios = null;
	byte[] toreturn = bos.toByteArray();
	bos.flush();
	bos.close();
	bos = null;
	return toreturn;
    }

}
