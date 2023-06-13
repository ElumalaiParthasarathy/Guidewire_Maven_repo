package testCases;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LogTest {
	private static final Logger logger = Logger.getLogger(LogTest.class);

	public static void main(String[] args) {
		
		PropertyConfigurator.configure("log4j2.properties");

        // Log some messages
        logger.debug("Debug message");
        logger.info("Info message");
        logger.warn("Warning message");
        logger.error("Error message");
	}
}	
