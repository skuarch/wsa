package model.common;

import org.apache.log4j.Logger;

/**
 * show a message before the program ends.
 *
 * @author skuarch
 */
public class ShutdownHook extends Thread {

    private static final Logger logger = Logger.getLogger(ShutdownHook.class);

    //==========================================================================
    @Override
    public void run() {
        logger.info("**** program finished ****");
    } // end run

} // end class