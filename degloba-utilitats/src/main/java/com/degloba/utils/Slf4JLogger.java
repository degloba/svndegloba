package com.degloba.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * SLF4J logger. Such written to replace the bare SLF4J the API is to:
 * (1)Making the client code does not have to do the log level detection, to simplify the code.
 * (2)Several overloaded method (no parameters, one, two arguments, the array) SLF4J Logging into one (with a variable-length parameters to achieve).
 *
 */
public class Slf4JLogger implements com.degloba.utils.Logger {
	private Logger logger;
	
	Slf4JLogger(Logger logger) {
		this.logger = logger;
	}

	public static Slf4JLogger getLogger(Class<?> clazz) {
		Logger logger = LoggerFactory.getLogger(clazz);
		return new Slf4JLogger(logger);
	}

	public static Slf4JLogger getLogger(String name) {
		Logger logger = LoggerFactory.getLogger(name);
		return new Slf4JLogger(logger);
	}
	
	public void debug(String msg, Object... args) {
		if (logger.isDebugEnabled()) {
			logger.debug(msg, args);
		}
	}
	
	public void debug(String msg, Throwable t) {
		if (logger.isDebugEnabled()) {
			logger.debug(msg, t);
		}
	}
	
	public void info(String msg, Object... args) {
		if (logger.isInfoEnabled()) {
			logger.info(msg, args);
		}
	}
	
	public void info(String msg, Throwable t) {
		if (logger.isInfoEnabled()) {
			logger.info(msg, t);
		}
	}
	
	public void trace(String format, Object... args) {
		if (logger.isTraceEnabled()) {
			logger.trace(format, args);
		}
	}
	
	public void trace(String msg, Throwable t) {
		if (logger.isTraceEnabled()) {
			logger.trace(msg, t);
		}
	}
	
	public void warn(String format, Object... args) {
		if (logger.isWarnEnabled()) {
			logger.warn(format, args);
		}
	}
	
	public void warn(String msg, Throwable t) {
		if (logger.isWarnEnabled()) {
			logger.warn(msg, t);
		}
	}
	
	public void error(String format, Object... args) {
		if (logger.isErrorEnabled()) {
			logger.error(format, args);
		}
	}
	
	public void error(String msg, Throwable t) {
		if (logger.isErrorEnabled()) {
			logger.error(msg, t);
		}
	}
	
}
