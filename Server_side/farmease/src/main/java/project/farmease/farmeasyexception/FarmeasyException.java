package project.farmease.farmeasyexception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import lombok.extern.slf4j.Slf4j;

public class FarmeasyException extends RuntimeException {
	
	Logger logger = LogManager.getLogger(FarmeasyException.class);
	
    public FarmeasyException(String exMessage, Exception exception) {
        logger.error(exMessage,exception);
    }

    public FarmeasyException(String exMessage) {
        logger.error(exMessage);
    }
}
