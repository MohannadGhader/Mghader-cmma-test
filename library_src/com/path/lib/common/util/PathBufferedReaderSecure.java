package com.path.lib.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

import com.path.lib.common.exception.BaseException;
/**
 * 
 * Copyright 2019, Path Solutions
 * Path Solutions retains all ownership rights to this source code 
 * 
 * @author: DeniskHaddad
 *
 * PathBufferedReaderSecure.java used to Securely readLines from Buffered REader, in order to protect again File DOS attacks
 */
public class PathBufferedReaderSecure extends BufferedReader
{
	private int readerMaxLines;
	private int readerMaxLineLen;
	private int currentLine = 1;
	/**
	 * Initialize the Secure Buffer Reader with max number of Lines and max length of character per line
	 * @param reader Reader Object to read the Lines
	 * @param maxLines Maximum allowed number of Lines
	 * @param maxLineLen Maximum number of Characters per Line
	 * @throws BaseException, Error if any of MAx Line, Max length per line not satisfied
	 */
	public PathBufferedReaderSecure(Reader reader, int maxLines, int maxLineLen) throws BaseException
	{
		super(reader);
		if ((maxLines<=0) || (maxLineLen<=0)) 
		{
		    throw new BaseException("PathBufferedReaderSecure - Security Error:  maxLines and maxLineLen must be greater than 0");
		 }
		
		readerMaxLines = maxLines;
		readerMaxLineLen = maxLineLen;
	}
	/**
	 * Initialize the Secure Buffer Reader with max length of character per line, Where Max number of Lines is 2 000 000
	 * @param reader Reader Object to read the Lines
	 * @param maxLineLen Maximum number of Characters per Line
	 * @throws BaseException Error if any of Max Line > 2000000, Max length per line not satisfied
	 */ 
	public PathBufferedReaderSecure(Reader reader, int maxLineLen) throws BaseException
	{
		super(reader);
		if (maxLineLen<=0) 
		{
		    throw new BaseException("PathBufferedReaderSecure - Security Error: maxLineLen must be greater than 0");
		 }
		
		readerMaxLines = 2000000;
		readerMaxLineLen = maxLineLen;
	}
	
	@Override
	public String readLine() throws IOException 
	{
		//Check readerMaxLines limit
		if (currentLine > readerMaxLines) 
		{
		    throw new IOException("PathBufferedReaderSecure - Security Error: Max Lines read limit has been reached "+readerMaxLines);
		}
		currentLine++;
		
		int currentPos=0;
		char[] data=new char[readerMaxLineLen];
		final int CR = 13;
		final int LF = 10;
		int currentCharVal = super.read();
		
		// Read characters and add them to the data buffer until we hit the end
		// of a line or the end of the file.
		while((currentCharVal != CR) && (currentCharVal != LF) && (currentCharVal >= 0))
		{
		    data[currentPos++] = (char) currentCharVal;
		    // Check readerMaxLineLen limit
		    if(currentPos < readerMaxLineLen)
		    {
			currentCharVal = super.read();
		    }
		    else
		    {
			throw new IOException("PathBufferedReaderSecure - Security Error: Single Line Limit Reached "+readerMaxLineLen+" characters for Line Num "+(currentLine-1));
		    }
		}
		
		// if nothing to read anymore
		if (currentCharVal < 0)
		{
			//End of file
			if (currentPos > 0)
			{
				//Return last line
				return(new String(data,0,currentPos));
			}
			else
			{
				return null;
			}
		}
		else
		{	
			//Remove newline characters from the buffer
			if(currentCharVal==CR) 
			{
				//Check for LF and remove from buffer
				super.mark(1);
				if (super.read() != LF)
				{
				    super.reset();
				}
			} 
			else if(currentCharVal!=LF)
			{
				//readerMaxLineLen has been hit, but we still need to remove newline characters.
				super.mark(1);
				int nextCharVal = super.read();
				if (nextCharVal==CR) 
				{
					super.mark(1);
					if (super.read() != LF)
					{
						super.reset();	
					}
				} 
				else if (nextCharVal!=LF) 
				{
					super.reset();
				}
			}
			return(new String(data,0,currentPos));
		}
	}
}
