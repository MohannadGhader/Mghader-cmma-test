package com.path.lib.common.util.barcode;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

import javax.imageio.ImageIO;

import org.krysalis.barcode4j.impl.codabar.CodabarBean;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.impl.code39.Code39Bean;
import org.krysalis.barcode4j.impl.datamatrix.DataMatrixBean;
import org.krysalis.barcode4j.impl.datamatrix.SymbolShapeHint;
import org.krysalis.barcode4j.impl.fourstate.RoyalMailCBCBean;
import org.krysalis.barcode4j.impl.fourstate.USPSIntelligentMailBean;
import org.krysalis.barcode4j.impl.int2of5.ITF14Bean;
import org.krysalis.barcode4j.impl.int2of5.Interleaved2Of5Bean;
import org.krysalis.barcode4j.impl.pdf417.PDF417Bean;
import org.krysalis.barcode4j.impl.postnet.POSTNETBean;
import org.krysalis.barcode4j.impl.qr.QRCodeBean;
import org.krysalis.barcode4j.impl.upcean.EAN13Bean;
import org.krysalis.barcode4j.impl.upcean.EAN8Bean;
import org.krysalis.barcode4j.impl.upcean.UPCABean;
import org.krysalis.barcode4j.impl.upcean.UPCEBean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.output.bitmap.BitmapEncoder;
import org.krysalis.barcode4j.output.bitmap.BitmapEncoderRegistry;
import org.krysalis.barcode4j.tools.UnitConv;

import com.path.lib.log.Log;

/**
 * Copyright 2015, Path Solutions Path Solutions retains all ownership rights to
 * this source code </br>
 * 
 * <strong>PathBarCodeGenerator.java</strong> used to generate barcode
 * 
 * @author Khaledhussein
 * 
 */
public final class PathBarCodeGenerator
{

    private static final Log log = Log.getInstance();

    /**
     * Prevent instantiation
     */
    private PathBarCodeGenerator()
    {
	log.warning("This class is utility and cannot be instantiated");
    }

    /**
     * Generate2 BarCode of type Code128 <br>
     * <strong>Valid characters:</strong> The full 7 bit ASCII (US-ASCII)
     * character set is supported.
     * 
     * @param barCode
     * @param prop
     * @return barcode image in byte array
     * @throws Exception
     */
    public static byte[] generateBarCode128(String barCode, PathBarCodeProp prop) throws Exception
    {
	log.debug("Generating BarCode of type Code128 for: " + barCode);

	Code128Bean bean = new Code128Bean();

	int dpi = 160;
	boolean quietZone = false;

	if(prop != null)
	{
	    dpi = prop.getWidth() == null ? dpi : prop.getWidth().intValue();

	    if(prop.isQuietZone() != null)
	    {
		quietZone = prop.isQuietZone().booleanValue();
		if(quietZone)
		{
		    if(prop.getQuietZoneHor() != null)
		    {
			bean.setQuietZone(prop.getQuietZoneHor().doubleValue());
		    }
		    if(prop.getQuietZoneVer() != null)
		    {
			bean.setVerticalQuietZone(prop.getQuietZoneVer().doubleValue());
		    }
		}
	    }

	    // if null no need to set the height
	    if(prop.getHeight() != null)
	    {
		bean.setBarHeight(UnitConv.in2mm(1.0f / prop.getHeight().intValue()));
	    }
	}

	// Set the width of the module
	// makes the narrow bar width exactly one pixel
	bean.setModuleWidth(UnitConv.in2mm(1.0f / dpi));

	// Set the quietZone
	bean.doQuietZone(quietZone);

	// Create the canvas
	BitmapCanvasProvider canvasProvider = new BitmapCanvasProvider(dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);

	// Generate the barcode
	bean.generateBarcode(canvasProvider, barCode);
	canvasProvider.finish();

	BufferedImage bufImage = canvasProvider.getBufferedImage();
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	ImageIO.write(bufImage, "png", baos);
	byte[] imageBytes = baos.toByteArray();

	return imageBytes;
    }

    /**
     * Generate2 BarCode of type Code39 <br>
     * <strong>Valid characters:</strong> 0-9, A-Z and the characters: - . $ / +
     * % SPACE <br>
     * The character '*' is only used as start and stop character and may not be
     * used in the message
     * 
     * @param barCode
     * @param prop
     * @return barcode image in byte array
     * @throws Exception
     */
    public static byte[] generateBarCode39(String barCode, PathBarCodeProp prop) throws Exception
    {
	log.debug("Generating BarCode of type Code39 for: " + barCode);

	Code39Bean bean = new Code39Bean();

	int dpi = 160;
	boolean quietZone = false;

	if(prop != null)
	{
	    dpi = prop.getWidth() == null ? dpi : prop.getWidth().intValue();

	    if(prop.isQuietZone() != null)
	    {
		quietZone = prop.isQuietZone().booleanValue();
		if(quietZone)
		{
		    if(prop.getQuietZoneHor() != null)
		    {
			bean.setQuietZone(prop.getQuietZoneHor().doubleValue());
		    }
		    if(prop.getQuietZoneVer() != null)
		    {
			bean.setVerticalQuietZone(prop.getQuietZoneVer().doubleValue());
		    }
		}
	    }

	    // if null no need to set the height
	    if(prop.getHeight() != null)
	    {
		bean.setBarHeight(UnitConv.in2mm(1.0f / prop.getHeight().intValue()));
	    }
	}

	// Set the width of the module
	// makes the narrow bar width exactly one pixel
	bean.setModuleWidth(UnitConv.in2mm(1.0f / dpi));

	// Set the quietZone
	bean.doQuietZone(quietZone);

	// Create the canvas
	BitmapCanvasProvider canvasProvider = new BitmapCanvasProvider(dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);

	// Generate the barcode
	bean.generateBarcode(canvasProvider, barCode);
	canvasProvider.finish();

	BufferedImage bufImage = canvasProvider.getBufferedImage();
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	ImageIO.write(bufImage, "png", baos);
	byte[] imageBytes = baos.toByteArray();

	return imageBytes;
    }

    /**
     * Generate2 BarCode of type Interleaved 2 of 5 <br>
     * <strong>Valid characters:</strong> 0-9 <br>
     * 
     * @param barCode
     * @param prop
     * @return barcode image in byte array
     * @throws Exception
     */
    public static byte[] generateBarCodeInterleaved2Of5(String barCode, PathBarCodeProp prop) throws Exception
    {
	log.debug("Generating BarCode of type Interleaved2Of5 for: " + barCode);

	Interleaved2Of5Bean bean = new Interleaved2Of5Bean();

	int dpi = 160;
	boolean quietZone = false;

	if(prop != null)
	{
	    dpi = prop.getWidth() == null ? dpi : prop.getWidth().intValue();

	    if(prop.isQuietZone() != null)
	    {
		quietZone = prop.isQuietZone().booleanValue();
		if(quietZone)
		{
		    if(prop.getQuietZoneHor() != null)
		    {
			bean.setQuietZone(prop.getQuietZoneHor().doubleValue());
		    }
		    if(prop.getQuietZoneVer() != null)
		    {
			bean.setVerticalQuietZone(prop.getQuietZoneVer().doubleValue());
		    }
		}
	    }

	    // if null no need to set the height
	    if(prop.getHeight() != null)
	    {
		bean.setBarHeight(UnitConv.in2mm(1.0f / prop.getHeight().intValue()));
	    }
	}

	// Set the width of the module
	// makes the narrow bar width exactly one pixel
	bean.setModuleWidth(UnitConv.in2mm(1.0f / dpi));

	// Set the quietZone
	bean.doQuietZone(quietZone);

	// Create the canvas
	BitmapCanvasProvider canvasProvider = new BitmapCanvasProvider(dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);

	// Generate the barcode
	bean.generateBarcode(canvasProvider, barCode);
	canvasProvider.finish();

	BufferedImage bufImage = canvasProvider.getBufferedImage();
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	ImageIO.write(bufImage, "png", baos);
	byte[] imageBytes = baos.toByteArray();

	return imageBytes;
    }

    /**
     * Generate2 BarCode of type CodaBar <br>
     * <strong>Valid characters:</strong> 0-9, the characters: - $ : / . +<br>
     * 
     * @param barCode
     * @param prop
     * @return barcode image in byte array
     * @throws Exception
     */
    public static byte[] generateBarCodeCodaBar(String barCode, PathBarCodeProp prop) throws Exception
    {
	log.debug("Generating BarCode of type Codabar for: " + barCode);

	CodabarBean bean = new CodabarBean();

	int dpi = 160;
	boolean quietZone = false;

	if(prop != null)
	{
	    dpi = prop.getWidth() == null ? dpi : prop.getWidth().intValue();

	    if(prop.isQuietZone() != null)
	    {
		quietZone = prop.isQuietZone().booleanValue();
		if(quietZone)
		{
		    if(prop.getQuietZoneHor() != null)
		    {
			bean.setQuietZone(prop.getQuietZoneHor().doubleValue());
		    }
		    if(prop.getQuietZoneVer() != null)
		    {
			bean.setVerticalQuietZone(prop.getQuietZoneVer().doubleValue());
		    }
		}
	    }

	    // if null no need to set the height
	    if(prop.getHeight() != null)
	    {
		bean.setBarHeight(UnitConv.in2mm(1.0f / prop.getHeight().intValue()));
	    }

	}

	// Set the width of the module
	// makes the narrow bar width exactly one pixel
	bean.setModuleWidth(UnitConv.in2mm(1.0f / dpi));

	// Set the quietZone
	bean.doQuietZone(quietZone);

	// Create the canvas
	BitmapCanvasProvider canvasProvider = new BitmapCanvasProvider(dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);

	// Generate the barcode
	bean.generateBarcode(canvasProvider, barCode);
	canvasProvider.finish();

	BufferedImage bufImage = canvasProvider.getBufferedImage();
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	ImageIO.write(bufImage, "png", baos);
	byte[] imageBytes = baos.toByteArray();

	return imageBytes;
    }

    /**
     * Generate2 BarCode of type PostNet <br>
     * <strong>Valid characters:</strong> 0-9<br>
     * 
     * @param barCode
     * @param prop
     * @return barcode image in byte array
     * @throws Exception
     */
    public static byte[] generateBarCodePostNet(String barCode, PathBarCodeProp prop) throws Exception
    {
	log.debug("Generating BarCode of type POSTNET for: " + barCode);

	POSTNETBean bean = new POSTNETBean();

	int dpi = 160;
	boolean quietZone = false;

	if(prop != null)
	{
	    dpi = prop.getWidth() == null ? dpi : prop.getWidth().intValue();

	    if(prop.isQuietZone() != null)
	    {
		quietZone = prop.isQuietZone().booleanValue();
		if(quietZone)
		{
		    if(prop.getQuietZoneHor() != null)
		    {
			bean.setQuietZone(prop.getQuietZoneHor().doubleValue());
		    }
		    if(prop.getQuietZoneVer() != null)
		    {
			bean.setVerticalQuietZone(prop.getQuietZoneVer().doubleValue());
		    }
		}
	    }

	    // if null no need to set the height
	    if(prop.getHeight() != null)
	    {
		bean.setBarHeight(UnitConv.in2mm(1.0f / prop.getHeight().intValue()));
	    }
	}

	// Set the width of the module
	// makes the narrow bar width exactly one pixel
	bean.setModuleWidth(UnitConv.in2mm(1.0f / dpi));

	// Set the quietZone
	bean.doQuietZone(quietZone);

	// Create the canvas
	BitmapCanvasProvider canvasProvider = new BitmapCanvasProvider(dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);

	// Generate the barcode
	bean.generateBarcode(canvasProvider, barCode);
	canvasProvider.finish();

	BufferedImage bufImage = canvasProvider.getBufferedImage();
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	ImageIO.write(bufImage, "png", baos);
	byte[] imageBytes = baos.toByteArray();

	return imageBytes;
    }

    /**
     * Generate2 BarCode of type EAN13 <br>
     * <strong>Valid characters:</strong> 0-9<br>
     * Message length must be 12 characters <br>
     * Supplementals are supported (2 and 5 characters). You can add them by
     * appending a "+" along with the supplemental message to the barcode
     * message. Example: 9783468111242+56789
     * 
     * @param barCode
     * @param prop
     * @return barcode image in byte array
     * @throws Exception
     */
    public static byte[] generateBarCodeEAN13(String barCode, PathBarCodeProp prop) throws Exception
    {
	log.debug("Generating BarCode of type EAN13 for: " + barCode);

	EAN13Bean bean = new EAN13Bean();

	int dpi = 160;
	boolean quietZone = false;

	if(prop != null)
	{
	    dpi = prop.getWidth() == null ?  dpi : prop.getWidth().intValue();

	    if(prop.isQuietZone() != null)
	    {
		quietZone = prop.isQuietZone().booleanValue();
		if(quietZone)
		{
		    if(prop.getQuietZoneHor() != null)
		    {
			bean.setQuietZone(prop.getQuietZoneHor().doubleValue());
		    }
		    if(prop.getQuietZoneVer() != null)
		    {
			bean.setVerticalQuietZone(prop.getQuietZoneVer().doubleValue());
		    }
		}
	    }

	    // if null no need to set the height
	    if(prop.getHeight() != null)
	    {
		bean.setBarHeight(UnitConv.in2mm(1.0f / prop.getHeight().intValue()));
	    }
	}

	// Set the width of the module
	// makes the narrow bar width exactly one pixel
	bean.setModuleWidth(UnitConv.in2mm(1.0f / dpi));

	// Set the quietZone
	bean.doQuietZone(quietZone);

	// Create the canvas
	BitmapCanvasProvider canvasProvider = new BitmapCanvasProvider(dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);

	// Generate the barcode
	bean.generateBarcode(canvasProvider, barCode);
	canvasProvider.finish();

	BufferedImage bufImage = canvasProvider.getBufferedImage();
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	ImageIO.write(bufImage, "png", baos);
	byte[] imageBytes = baos.toByteArray();

	return imageBytes;
    }

    /**
     * Generate2 BarCode of type EAN8 <br>
     * <strong>Valid characters:</strong> 0-9<br>
     * Message length must be 7 characters <br>
     * Supplementals are supported (2 and 5 characters). You can add them by
     * appending a "+" along with the supplemental message to the barcode
     * message. Example: 2213933+12
     * 
     * @param barCode
     * @param prop
     * @return barcode image in byte array
     * @throws Exception
     */
    public static byte[] generateBarCodeEAN8(String barCode, PathBarCodeProp prop) throws Exception
    {
	log.debug("Generating BarCode of type EAN8 for: " + barCode);

	EAN8Bean bean = new EAN8Bean();

	int dpi = 160;
	boolean quietZone = false;

	if(prop != null)
	{
	    dpi = prop.getWidth() == null ? dpi : prop.getWidth().intValue();

	    if(prop.isQuietZone() != null)
	    {
		quietZone = prop.isQuietZone().booleanValue();
		if(quietZone)
		{
		    if(prop.getQuietZoneHor() != null)
		    {
			bean.setQuietZone(prop.getQuietZoneHor().doubleValue());
		    }
		    if(prop.getQuietZoneVer() != null)
		    {
			bean.setVerticalQuietZone(prop.getQuietZoneVer().doubleValue());
		    }
		}
	    }

	    // if null no need to set the height
	    if(prop.getHeight() != null)
	    {
		bean.setBarHeight(UnitConv.in2mm(1.0f / prop.getHeight().intValue()));
	    }
	}

	// Set the width of the module
	// makes the narrow bar width exactly one pixel
	bean.setModuleWidth(UnitConv.in2mm(1.0f / dpi));

	// Set the quietZone
	bean.doQuietZone(quietZone);

	// Create the canvas
	BitmapCanvasProvider canvasProvider = new BitmapCanvasProvider(dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);

	// Generate the barcode
	bean.generateBarcode(canvasProvider, barCode);
	canvasProvider.finish();

	BufferedImage bufImage = canvasProvider.getBufferedImage();
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	ImageIO.write(bufImage, "png", baos);
	byte[] imageBytes = baos.toByteArray();

	return imageBytes;
    }

    /**
     * Generate2 BarCode of type UPC-A <br>
     * <strong>Valid characters:</strong> 0-9<br>
     * Message length must be 12 characters <br>
     * Supplementals are supported (2 and 5 characters). You can add them by
     * appending a "+" along with the supplemental message to the barcode
     * message. Example: 9783468111242+56789
     * 
     * @param barCode
     * @param prop
     * @return barcode image in byte array
     * @throws Exception
     */
    public static byte[] generateBarCodeUPCA(String barCode, PathBarCodeProp prop) throws Exception
    {
	log.debug("Generating BarCode of type UPCA for: " + barCode);

	UPCABean bean = new UPCABean();

	int dpi = 160;
	boolean quietZone = false;

	if(prop != null)
	{
	    dpi = prop.getWidth() == null ? dpi : prop.getWidth().intValue();

	    if(prop.isQuietZone() != null)
	    {
		quietZone = prop.isQuietZone().booleanValue();
		if(quietZone)
		{
		    if(prop.getQuietZoneHor() != null)
		    {
			bean.setQuietZone(prop.getQuietZoneHor().doubleValue());
		    }
		    if(prop.getQuietZoneVer() != null)
		    {
			bean.setVerticalQuietZone(prop.getQuietZoneVer().doubleValue());
		    }
		}
	    }

	    // if null no need to set the height
	    if(prop.getHeight() != null)
	    {
		bean.setBarHeight(UnitConv.in2mm(1.0f / prop.getHeight().intValue()));
	    }
	}

	// Set the width of the module
	// makes the narrow bar width exactly one pixel
	bean.setModuleWidth(UnitConv.in2mm(1.0f / dpi));

	// Set the quietZone
	bean.doQuietZone(quietZone);

	// Create the canvas
	BitmapCanvasProvider canvasProvider = new BitmapCanvasProvider(dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);

	// Generate the barcode
	bean.generateBarcode(canvasProvider, barCode);
	canvasProvider.finish();

	BufferedImage bufImage = canvasProvider.getBufferedImage();
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	ImageIO.write(bufImage, "png", baos);
	byte[] imageBytes = baos.toByteArray();

	return imageBytes;
    }

    /**
     * Generate2 BarCode of type UPC-E <br>
     * <strong>Valid characters:</strong> 0-9<br>
     * Message length must be 7 characters <br>
     * Supplementals are supported (2 and 5 characters). You can add them by
     * appending a "+" along with the supplemental message to the barcode
     * message. Example: 2213933+12
     * 
     * @param barCode
     * @param prop
     * @return barcode image in byte array
     * @throws Exception
     */
    public static byte[] generateBarCodeUPCE(String barCode, PathBarCodeProp prop) throws Exception
    {
	log.debug("Generating BarCode of type UPCE for: " + barCode);

	UPCEBean bean = new UPCEBean();

	int dpi = 160;
	boolean quietZone = false;

	if(prop != null)
	{
	    dpi = prop.getWidth() == null ? dpi : prop.getWidth().intValue();

	    if(prop.isQuietZone() != null)
	    {
		quietZone = prop.isQuietZone().booleanValue();
		if(quietZone)
		{
		    if(prop.getQuietZoneHor() != null)
		    {
			bean.setQuietZone(prop.getQuietZoneHor().doubleValue());
		    }
		    if(prop.getQuietZoneVer() != null)
		    {
			bean.setVerticalQuietZone(prop.getQuietZoneVer().doubleValue());
		    }
		}
	    }

	    // if null no need to set the height
	    if(prop.getHeight() != null)
	    {
		bean.setBarHeight(UnitConv.in2mm(1.0f / prop.getHeight().intValue()));
	    }
	}

	// Set the width of the module
	// makes the narrow bar width exactly one pixel
	bean.setModuleWidth(UnitConv.in2mm(1.0f / dpi));

	// Set the quietZone
	bean.doQuietZone(quietZone);

	// Create the canvas
	BitmapCanvasProvider canvasProvider = new BitmapCanvasProvider(dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);

	// Generate the barcode
	bean.generateBarcode(canvasProvider, barCode);
	canvasProvider.finish();

	BufferedImage bufImage = canvasProvider.getBufferedImage();
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	ImageIO.write(bufImage, "png", baos);
	byte[] imageBytes = baos.toByteArray();

	return imageBytes;
    }

    /**
     * Generate2 BarCode of type ITF-14 <br>
     * <strong>Valid characters:</strong> 0-9<br>
     * Message length must be 13 characters <br>
     * 
     * @param barCode
     * @param prop
     * @return barcode image in byte array
     * @throws Exception
     */
    public static byte[] generateBarCodeITF14(String barCode, PathBarCodeProp prop) throws Exception
    {
	log.debug("Generating BarCode of type ITF14 for: " + barCode);

	ITF14Bean bean = new ITF14Bean();

	int dpi = 160;
	boolean quietZone = true;

	if(prop != null)
	{
	    dpi = prop.getWidth() == null ? dpi : prop.getWidth().intValue();

	    if(prop.getQuietZoneHor() != null)
	    {
		bean.setQuietZone(prop.getQuietZoneHor().doubleValue());
	    }
	    if(prop.getQuietZoneVer() != null)
	    {
		bean.setVerticalQuietZone(prop.getQuietZoneVer().doubleValue());
	    }

	    // if null no need to set the height
	    if(prop.getHeight() != null)
	    {
		bean.setBarHeight(UnitConv.in2mm(1.0f / prop.getHeight().intValue()));
	    }
	}

	// Set the width of the module
	// makes the narrow bar width exactly one pixel
	bean.setModuleWidth(UnitConv.in2mm(1.0f / dpi));

	// Set the quietZone
	bean.doQuietZone(quietZone);

	// Create the canvas
	BitmapCanvasProvider canvasProvider = new BitmapCanvasProvider(dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);

	// Generate the barcode
	bean.generateBarcode(canvasProvider, barCode);
	canvasProvider.finish();

	BufferedImage bufImage = canvasProvider.getBufferedImage();
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	ImageIO.write(bufImage, "png", baos);
	byte[] imageBytes = baos.toByteArray();

	return imageBytes;
    }

    /**
     * Generate2 BarCode of type Royal Mail CBC <br>
     * <strong>Valid characters:</strong> 0-9 and A-Z<br>
     * 
     * @param barCode
     * @param prop
     * @return barcode image in byte array
     * @throws Exception
     */
    public static byte[] generateBarCodeRoyalMailCbc(String barCode, PathBarCodeProp prop) throws Exception
    {
	log.debug("Generating BarCode of type RoyalMailCBC for: " + barCode);

	RoyalMailCBCBean bean = new RoyalMailCBCBean();

	int dpi = 160;
	boolean quietZone = false;

	if(prop != null)
	{
	    dpi = prop.getWidth() == null ? dpi : prop.getWidth().intValue();

	    if(prop.isQuietZone() != null)
	    {
		quietZone = prop.isQuietZone().booleanValue();
		if(quietZone)
		{
		    if(prop.getQuietZoneHor() != null)
		    {
			bean.setQuietZone(prop.getQuietZoneHor().doubleValue());
		    }
		    if(prop.getQuietZoneVer() != null)
		    {
			bean.setVerticalQuietZone(prop.getQuietZoneVer().doubleValue());
		    }
		}
	    }

	    // if null no need to set the height
	    if(prop.getHeight() != null)
	    {
		bean.setBarHeight(UnitConv.in2mm(1.0f / prop.getHeight().intValue()));
	    }
	}

	// Set the width of the module
	// makes the narrow bar width exactly one pixel
	bean.setModuleWidth(UnitConv.in2mm(1.0f / dpi));

	// Set the quietZone
	bean.doQuietZone(quietZone);

	// Create the canvas
	BitmapCanvasProvider canvasProvider = new BitmapCanvasProvider(dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);

	// Generate the barcode
	bean.generateBarcode(canvasProvider, barCode);
	canvasProvider.finish();

	BufferedImage bufImage = canvasProvider.getBufferedImage();
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	ImageIO.write(bufImage, "png", baos);
	byte[] imageBytes = baos.toByteArray();

	return imageBytes;
    }

    /**
     * Generate2 BarCode of type USPS Intelligent Mail <br>
     * <strong>Valid characters:</strong> 0-9<br>
     * It must have at least 20 digits. Routing code can be appended with length
     * 0, 5, 9 or 11 only; i.e., the expected length of the barcode is 20, 25,
     * 29 or 31
     * 
     * @param barCode
     * @param prop
     * @return barcode image in byte array
     * @throws Exception
     */
    public static byte[] generateBarCodeUsps4Bc(String barCode, PathBarCodeProp prop) throws Exception
    {
	log.debug("Generating BarCode of type USPSIntelligentMail for: " + barCode);

	USPSIntelligentMailBean bean = new USPSIntelligentMailBean();

	int dpi = 160;
	boolean quietZone = false;

	if(prop != null)
	{
	    dpi = prop.getWidth() == null ? dpi : prop.getWidth().intValue();

	    if(prop.isQuietZone() != null)
	    {
		quietZone = prop.isQuietZone().booleanValue();
		if(quietZone)
		{
		    if(prop.getQuietZoneHor() != null)
		    {
			bean.setQuietZone(prop.getQuietZoneHor().doubleValue());
		    }
		    if(prop.getQuietZoneVer() != null)
		    {
			bean.setVerticalQuietZone(prop.getQuietZoneVer().doubleValue());
		    }
		}
	    }

	    // if null no need to set the height
	    if(prop.getHeight() != null)
	    {
		bean.setBarHeight(UnitConv.in2mm(1.0f / prop.getHeight().intValue()));
	    }
	}

	// Set the width of the module
	// makes the narrow bar width exactly one pixel
	bean.setModuleWidth(UnitConv.in2mm(1.0f / dpi));

	// Set the quietZone
	bean.doQuietZone(quietZone);

	// Create the canvas
	BitmapCanvasProvider canvasProvider = new BitmapCanvasProvider(dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);

	// Generate the barcode
	bean.generateBarcode(canvasProvider, barCode);
	canvasProvider.finish();

	BufferedImage bufImage = canvasProvider.getBufferedImage();
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	ImageIO.write(bufImage, "png", baos);
	byte[] imageBytes = baos.toByteArray();

	return imageBytes;
    }

    /**
     * Generate2 BarCode of type PDF417 <br>
     * <strong>Valid characters:</strong> All printable ASCII characters are
     * valid characters. <br>
     * Byte compaction mode permits all 256 possible 8-bit byte values to be
     * encoded.<br>
     * Using only numeric characters allows for smaller symbol sizes.<br>
     * Currently, no ECI functionality is available. All characters are
     * interpreted in "cp437" (PC437) encoding.
     * 
     * @param barCode
     * @param prop
     * @return barcode image in byte array
     * @throws Exception
     */
    public static byte[] generateBarCodePDF417(String barCode, PathBarCodeProp prop) throws Exception
    {
	log.debug("Generating BarCode of type PDF417 for: " + barCode);

	PDF417Bean bean = new PDF417Bean();

	int dpi = 400;
	boolean quietZone = false;

	if(prop != null)
	{
	    dpi = prop.getWidth() == null ? dpi : prop.getWidth().intValue();

	    if(prop.isQuietZone() != null)
	    {
		quietZone = prop.isQuietZone().booleanValue();
		if(quietZone)
		{
		    if(prop.getQuietZoneHor() != null)
		    {
			bean.setQuietZone(prop.getQuietZoneHor().doubleValue());
		    }
		    if(prop.getQuietZoneVer() != null)
		    {
			bean.setVerticalQuietZone(prop.getQuietZoneVer().doubleValue());
		    }
		}
	    }

	    // if null no need to set the height
	    if(prop.getHeight() != null)
	    {
		bean.setBarHeight(UnitConv.in2mm(1.0f / prop.getHeight().intValue()));
	    }
	}

	// Set the width of the module
	// makes the narrow bar width exactly one pixel
	bean.setModuleWidth(UnitConv.in2mm(1.0f / dpi));

	// Set the quietZone
	bean.doQuietZone(quietZone);

	// Create the canvas
	BitmapCanvasProvider canvasProvider = new BitmapCanvasProvider(dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);

	// Generate the barcode
	bean.generateBarcode(canvasProvider, barCode);
	canvasProvider.finish();

	BufferedImage bufImage = canvasProvider.getBufferedImage();
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	ImageIO.write(bufImage, "png", baos);
	byte[] imageBytes = baos.toByteArray();

	return imageBytes;
    }

    /**
     * Generate2 BarCode of type PDF417 <br>
     * <strong>Valid characters:</strong> All ISO-8859-1 characters are valid
     * message characters. <br>
     * Using only numeric characters allows for smaller symbol sizes. <br>
     * Currently, no ECI functionality is available. Only characters in the
     * "ISO-8859-1" encoding can be used. <br>
     * Currently, the FNC1 and reader programming signal cannot be encoded. <br>
     * Some applications use special ASCII characters like <GS> (group
     * separator) or <RS> (record separator). Just send them as is to Barcode4J.
     * In Java a preamble of such an application ("[)>RS05GS") can be expressed
     * as "[)>\u001E05\u001D". The same encoded as part of an URL (when using
     * the barcode servlet) will be "%5B%29%3E%1E05%1D". <br>
     * Binary data can be supplied through URLs if they are enclosed in "url()".
     * RFC 2397 data URLs can be used to encode inline data. An example to
     * encode the text "~Test~": url(data:;base64,flRlc3R+) or
     * url(data:text/plain;charset=iso-8859-1,%7ETest%7E) (the
     * "charset="iso-8859-1" is important to get characters above the 7bit
     * US-ASCII set correctly!).
     * 
     * @param barCode
     * @param prop
     * @return barcode image in byte array
     * @throws Exception
     */
    public static byte[] generateBarCodeDataMatrix(String barCode, PathBarCodeProp prop) throws Exception
    {
	log.debug("Generating BarCode of type DataMatrix for: " + barCode);

	DataMatrixBean bean = new DataMatrixBean();

	int dpi = 400;
	boolean quietZone = false;

	if(prop != null)
	{
	    dpi = prop.getWidth() == null ? dpi : prop.getWidth().intValue();

	    if(prop.isQuietZone() != null)
	    {
		quietZone = prop.isQuietZone().booleanValue();
		if(quietZone)
		{
		    if(prop.getQuietZoneHor() != null)
		    {
			bean.setQuietZone(prop.getQuietZoneHor().doubleValue());
		    }
		    if(prop.getQuietZoneVer() != null)
		    {
			bean.setVerticalQuietZone(prop.getQuietZoneVer().doubleValue());
		    }
		}
	    }

	    // if null no need to set the height
	    if(prop.getHeight() != null)
	    {
		bean.setBarHeight(UnitConv.in2mm(1.0f / prop.getHeight().intValue()));
	    }
	}

	// Set the width of the module
	// makes the narrow bar width exactly one pixel
	bean.setModuleWidth(UnitConv.in2mm(1.0f / dpi));

	// Set the quietZone
	bean.doQuietZone(quietZone);

	// Create the canvas
	BitmapCanvasProvider canvasProvider = new BitmapCanvasProvider(dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);

	// Generate the barcode
	bean.generateBarcode(canvasProvider, barCode);
	canvasProvider.finish();

	BufferedImage bufImage = canvasProvider.getBufferedImage();
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	ImageIO.write(bufImage, "png", baos);
	byte[] imageBytes = baos.toByteArray();

	return imageBytes;
    }

    /**
     * Generates DataMatrix barcode in addition to the array string
     * 
     * @param barCode
     * @param param
     * @param prop
     * @return
     * @throws Exception
     */
    public static byte[] generateBarCodeDataMatrixWithParam(String barCode, String[] param, PathBarCodeProp prop)
	    throws Exception
    {
	log.debug("Generating BarCode of type DataMatrix with Param for: " + barCode);

	DataMatrixBean bean = new DataMatrixBean();

	int dpi = 200;

	if(prop != null)
	{
	    dpi = prop.getWidth() == null ? dpi : prop.getWidth().intValue();

	    // if null no need to set the height
	    if(prop.getHeight() != null)
	    {
		bean.setBarHeight(UnitConv.in2mm(1.0f / prop.getHeight().intValue()));
	    }
	}

	// Set the width of the module
	// makes the bar width exactly 8 pixels
	bean.setModuleWidth(UnitConv.in2mm(8.0f / dpi));

	// Set the quietZone
	bean.doQuietZone(false);
	bean.setShape(SymbolShapeHint.FORCE_RECTANGLE);

	// Create the canvas
	BitmapCanvasProvider canvasProvider = new BitmapCanvasProvider(dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);

	// Generate the barcode
	bean.generateBarcode(canvasProvider, barCode);
	canvasProvider.finish();

	BufferedImage bufImage = canvasProvider.getBufferedImage();

	// Prepare the additional param
	int fontSize = 18; // pixels
	int lineHeight = (int) (fontSize * 1.2);
	Font font = new Font("Calibri", Font.PLAIN, fontSize);
	int width = bufImage.getWidth();
	int height = bufImage.getHeight();

	FontRenderContext frc = new FontRenderContext(new AffineTransform(), false, true);

	for(int i = 0; i < param.length; i++)
	{
	    String line = param[i];
	    Rectangle2D bounds = font.getStringBounds(line, frc);
	    width = (int) Math.ceil(Math.max(width, bounds.getWidth()));
	    height += lineHeight;
	}

	// Add padding
	int padding = 2;
	width += 2 * padding;
	height += 3 * padding;

	// Draw the new image
	BufferedImage bitmap = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);
	Graphics2D g2d = (Graphics2D) bitmap.getGraphics();
	g2d.setBackground(Color.white);
	g2d.setColor(Color.black);
	g2d.clearRect(0, 0, bitmap.getWidth(), bitmap.getHeight());
	g2d.setFont(font);

	// Place the barcode symbol
	AffineTransform symbolPlacement = new AffineTransform();
	symbolPlacement.translate(padding, padding);
	g2d.drawRenderedImage(bufImage, symbolPlacement);

	// Add text lines (or anything else you might want to add)
	int y = padding + bufImage.getHeight() + padding;
	for(int i = 0; i < param.length; i++)
	{
	    String line = param[i];
	    y += lineHeight;
	    g2d.drawString(line, padding, y);
	}
	g2d.dispose();

	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	final BitmapEncoder encoder = BitmapEncoderRegistry.getInstance("image/png");
	encoder.encode(bitmap, baos, "image/png", dpi);
	byte[] imageBytes = baos.toByteArray();

	return imageBytes;
    }

    /**
     * Generate2 BarCode of type Code QR <br>
     * <strong>Valid characters:</strong> All ISO-8859-1 characters are valid
     * message characters. <br>
     * The "encoding" setting can be used to establish a different ECI (Extended
     * Channel Interpretation) code. By default, messages are encoded in
     * "ISO-8859-1". Only a subset of the encodings defined in Java can be used
     * as they have to be mappable to ECI codes. Besides the "ISO-8859-*" series
     * of encodings, "UTF-8" is probably the most useful encoding. Note that ECI
     * codes may not be supported by every barcode scanner. <br>
     * Using only numeric characters allows for smaller symbol sizes. <br>
     * 
     * @param barCode
     * @param prop
     * @return barcode image in byte array
     * @throws Exception
     */
    public static byte[] generateBarCodeCodeQR(String barCode, PathBarCodeProp prop) throws Exception
    {
	log.debug("Generating BarCode of type QRCode for: " + barCode);

	QRCodeBean bean = new QRCodeBean();

	int dpi = 400;
	boolean quietZone = false;

	if(prop != null)
	{
	    dpi = prop.getWidth() == null ? dpi : prop.getWidth().intValue();

	    if(prop.isQuietZone() != null)
	    {
		quietZone = prop.isQuietZone().booleanValue();
		if(quietZone)
		{
		    if(prop.getQuietZoneHor() != null)
		    {
			bean.setQuietZone(prop.getQuietZoneHor().doubleValue());
		    }
		    if(prop.getQuietZoneVer() != null)
		    {
			bean.setVerticalQuietZone(prop.getQuietZoneVer().doubleValue());
		    }
		}
	    }

	    // if null no need to set the height
	    if(prop.getHeight() != null)
	    {
		bean.setBarHeight(UnitConv.in2mm(1.0f / prop.getHeight().intValue()));
	    }
	}

	// Set the width of the module
	// makes the narrow bar width exactly one pixel
	bean.setModuleWidth(UnitConv.in2mm(1.0f / dpi));

	// Set the quietZone
	bean.doQuietZone(quietZone);

	// Create the canvas
	BitmapCanvasProvider canvasProvider = new BitmapCanvasProvider(dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);

	// Generate the barcode
	bean.generateBarcode(canvasProvider, barCode);
	canvasProvider.finish();

	BufferedImage bufImage = canvasProvider.getBufferedImage();
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	ImageIO.write(bufImage, "png", baos);
	byte[] imageBytes = baos.toByteArray();

	return imageBytes;
    }
}
