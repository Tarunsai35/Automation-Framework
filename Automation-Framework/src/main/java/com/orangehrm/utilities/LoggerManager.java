package com.orangehrm.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.orangehrm.base.BaseClass;

public class LoggerManager {

	// This method returns a logger instance for the provider class
	public static  Logger getlogger(Class<?> clazz) {
		 return LogManager.getLogger(clazz);
	}

}
