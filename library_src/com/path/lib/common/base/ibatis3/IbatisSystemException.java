package com.path.lib.common.base.ibatis3;

import org.springframework.dao.UncategorizedDataAccessException;

/**
 * iBATIS-specific subclass of UncategorizedDataAccessException, for iBATIS
 * system errors that do not match any concrete
 * <code>org.springframework.dao</code> exceptions.
 * <p>
 * 
 * In iBATIS 3 <code>IbatisException</code> is a <code>RuntimeException</code>,
 * but using this wrapper class to bring everything under a single hierarchy
 * will be easier for client code to handle.
 */
public class IbatisSystemException extends UncategorizedDataAccessException {

    public IbatisSystemException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
