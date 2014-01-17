package application.wsa;

import java.io.IOException;
import model.common.ShutdownHook;
import model.common.StartServer;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * this application is the base for another applications.
 *
 */
public class Main {

    private static final Logger logger = Logger.getLogger(Main.class);

    static {
        PropertyConfigurator.configure("configuration/log4j.properties");
    }

    //==========================================================================
    /**
     * create a instance.
     */
    public Main() {
    } // end Main  

    //==========================================================================
    /**
     * this program doesn't use a arguments in the command line.
     *
     * @param args
     */
    public static void main(String[] args) {

        try {

            Runtime.getRuntime().addShutdownHook(new ShutdownHook());
            logger.info("**** starting program ****");

            //create the serverSocket
            new StartServer().run();

        } catch (IOException ioe) {
            logger.error("main", ioe);
        }

    } // end main

} // end class
