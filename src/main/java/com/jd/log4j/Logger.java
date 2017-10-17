/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jd.log4j;

import com.jd.log4j.spi.LoggerFactory;


/**
 * @author 张明明  braveheart1115@163.com
 * Logger用于对日志记录行为的抽象，提供记录不同级别日志的接口. 这是在log4j包的中心类。除了配置，大多数日志记录操作都是通过这个类进行的
 */
public class Logger extends Category {

	/**
	 * The fully qualified name(全限定名) of the Logger class. See also the getFQCN method.
	 */
	private static final String FQCN = Logger.class.getName();


	protected Logger(String name) {
		super(name);
	}

	/**
	 * Retrieve a logger named according to the value of the
	 * <code>name</code> parameter. If the named logger already exists,
	 * then the existing instance will be returned. Otherwise, a new
	 * instance is created.
	 * <p>
	 * <p>By default, loggers do not have a set level but inherit it
	 * from their neareast ancestor with a set level. This is one of the
	 * central features of log4j.
	 *
	 * @param name The name of the logger to retrieve.
	 */
	public static Logger getLogger(String name) {
		return LogManager.getLogger(name);
	}

	/**
	 * Shorthand for <code>getLogger(clazz.getName())</code>.
	 *
	 * @param clazz The name of <code>clazz</code> will be used as the
	 *              name of the logger to retrieve.  See {@link #getLogger(String)}
	 *              for more detailed information.
	 */
	public static Logger getLogger(Class clazz) {
		return LogManager.getLogger(clazz.getName());
	}


	/**
	 * Return the root logger for the current logger repository.
	 * <p>
	 * The {@link #getName Logger.getName()} method for the root logger always returns
	 * string value: "root". However, calling
	 * <code>Logger.getLogger("root")</code> does not retrieve the root
	 * logger but a logger just under root named "root".
	 * <p>
	 * In other words, calling this method is the only way to retrieve the
	 * root logger.
	 */
	public static Logger getRootLogger() {
		return LogManager.getRootLogger();
	}

	/**
	 * Like {@link #getLogger(String)} except that the type of logger
	 * instantiated depends on the type returned by the {@link
	 * LoggerFactory#makeNewLoggerInstance} method of the
	 * <code>factory</code> parameter.
	 * <p>
	 * <p>This method is intended to be used by sub-classes.
	 *
	 * @param name    The name of the logger to retrieve.
	 * @param factory A {@link LoggerFactory} implementation that will
	 *                actually create a new Instance.
	 * @since 0.8.5
	 */
	public static Logger getLogger(String name, LoggerFactory factory) {
		return LogManager.getLogger(name, factory);
	}

	/**
	 * Log a message object with the {@link Level#TRACE TRACE} level.
	 *
	 * @param message the message object to log.
	 * @see #debug(Object) for an explanation of the logic applied.
	 * @since 1.2.12
	 */
	public void trace(Object message) {
		if (repository.isDisabled(Level.TRACE_INT)) {
			return;
		}

		if (Level.TRACE.isGreaterOrEqual(this.getEffectiveLevel())) {
			forcedLog(FQCN, Level.TRACE, message, null);
		}
	}

	/**
	 * Log a message object with the <code>TRACE</code> level including the
	 * stack trace of the {@link Throwable}<code>t</code> passed as parameter.
	 * <p>
	 * <p>
	 * See {@link #debug(Object)} form for more detailed information.
	 * </p>
	 *
	 * @param message the message object to log.
	 * @param t       the exception to log, including its stack trace.
	 * @since 1.2.12
	 */
	public void trace(Object message, Throwable t) {
		if (repository.isDisabled(Level.TRACE_INT)) {
			return;
		}

		if (Level.TRACE.isGreaterOrEqual(this.getEffectiveLevel())) {
			forcedLog(FQCN, Level.TRACE, message, t);
		}
	}

	/**
	 * Check whether this category is enabled for the TRACE  Level.
	 *
	 * @return boolean - <code>true</code> if this category is enabled for level
	 * TRACE, <code>false</code> otherwise.
	 * @since 1.2.12
	 */
	public boolean isTraceEnabled() {
		if (repository.isDisabled(Level.TRACE_INT)) {
			return false;
		}
		return Level.TRACE.isGreaterOrEqual(this.getEffectiveLevel());
	}

}
