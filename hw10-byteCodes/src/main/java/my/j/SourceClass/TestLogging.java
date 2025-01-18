package my.j.SourceClass;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import my.j.annotation.Log;

public class TestLogging implements TestLoggingInterface {
    private static final Logger logger = LoggerFactory.getLogger(TestLogging.class);
    @Log
    public void calculation(int param){
        logger.info("in body of calculation with param {}...", param);
    }

    public int calculation(int param1, int param2){
        logger.info("in body of calculation with params: {}, {}...", param1, param2);
        return param1*param2;
    }

}
