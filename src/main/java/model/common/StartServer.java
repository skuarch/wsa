package model.common;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import org.apache.log4j.Logger;

/**
 * Read some properties in order to start the server.
 *
 * @author skuarch
 */
public final class StartServer {

    private static final Logger logger = Logger.getLogger(StartServer.class);

    //==========================================================================
    /**
     * create a instance.
     */
    public StartServer() {
    } // end StartServer

    //==========================================================================
    /**
     * read the application file to get the port, end call the SocketProccesor
     * to listen the port
     *
     * @throws IOException
     */
    public void run() throws IOException {

        ServerSocket serverSocket = null;
        Socket socket = null; //client
        int timeout = 0;
        CustomProperties customProperties = new CustomProperties();
        int port;

        try {

            //retrieving data from application.properties               
            port = customProperties.getIntPropertie("listen.port");

            timeout = customProperties.getIntPropertie("socket.timeout");
            serverSocket = new ServerSocket(port);
            logger.info("listening on port " + port);

            //here create a socket to each request
            while (true) {

                socket = serverSocket.accept();
                socket.setSoTimeout(timeout);
                socket.setKeepAlive(false);

                //dispatch each client in another thread in order to continue listening
                new Thread(new RequestDispatcher(socket)).start();
            }

        } catch (IOException e) {
            throw e;
        } finally {
            customProperties = null;
        }

    } // end run

} // end class
