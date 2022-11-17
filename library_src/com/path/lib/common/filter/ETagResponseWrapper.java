/**
 * 
 */
package com.path.lib.common.filter;

/**
 * Copyright 2015, Path Solutions
 * Path Solutions retains all ownership rights to this source code 
 * 
 * @author: rabihelkhatib
 *
 * ETagResponseWrapper.java used to extend functionality of HttpServletResponseWrapper get responseStream to a writer.
 */
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class ETagResponseWrapper extends HttpServletResponseWrapper {
	private ServletOutputStream stream;
	private PrintWriter writer;
	private final OutputStream buffer;

	public ETagResponseWrapper(HttpServletResponse response, OutputStream _buffer) {
		super(response);
		this.buffer = _buffer;
	}

	public ServletOutputStream getOutputStream() throws IOException {
		if (stream == null) {
			stream = new ETagResponseStream(buffer);
		}
		return stream;
	}

	public PrintWriter getWriter() throws IOException {
		if (writer == null) {
			writer = new PrintWriter(new OutputStreamWriter(getOutputStream(), "UTF-8"));
		}
		return writer;
	}

	public void flushBuffer() throws IOException {
		// TP 500032 Websphere automatic login Issue
		if (stream != null) {
			stream.flush();
		}
	}
}