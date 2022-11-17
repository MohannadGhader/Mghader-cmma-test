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
 * ETagResponseStream.java used to extend functionality of ServletOutputStream to extract byte[] from ServletOutputStream.
 */
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;


public class ETagResponseStream extends ServletOutputStream {
        private boolean closed ;
        private final OutputStream stream;

        public ETagResponseStream(OutputStream theStream) throws IOException {
                super();
                this.stream = theStream;
        }

        public void close() throws IOException {
                if (!closed) {
                        stream.close();
                        closed = true;
                }
        }

        public void flush() throws IOException {
                if (!closed) {
                        stream.flush();
                }
        }

        public void write(int b) throws IOException {
                if (!closed) {
                        stream.write((byte) b);
                }
        }

        public void write(byte b[], int off, int len) throws IOException {
                if (!closed) {
                        stream.write(b, off, len);
                }
        }

        public void write(byte b[]) throws IOException {
                write(b, 0, b.length);
        }

        public boolean closed() {
                return closed;
        }

        public void reset() {
                //noop
        }

	public boolean isReady()
	{
	    return false;
	}

	public void setWriteListener(WriteListener arg0)
	{
	}
}