package model.common;

import java.io.IOException;
import java.net.Socket;
import model.net.ModelSocket;
import model.process.ProcessConfiguration;
import model.process.ProcessConnectivity;
import model.process.ProcessNotifier;
import model.util.JSONUtilities;
import org.apache.log4j.Logger;
import org.json.JSONObject;

/**
 *
 * @author skuarch
 */
public final class RequestDispatcher extends Thread {

    private static final Logger logger = Logger.getLogger(RequestDispatcher.class);
    private Socket socket = null;
    private ModelSocket ms = null;
    private String textReceived = null;

    //==========================================================================
    public RequestDispatcher(Socket socket) {
        this.socket = socket;
    } // end RequestDispatcher    

    //==========================================================================
    @Override
    public void run() {

        if (socket == null || socket.isClosed()) {
            logger.error("socket is null or close");
            return;
        }

        try {

            ms = new ModelSocket(socket);
            textReceived = ms.receive();

            if (textReceived == null || textReceived.length() < 1) {

                ms.closeStreams();
                throw new NullPointerException("data received from client is null or empty");

            } else {

                System.out.println(textReceived);
                attendRequest(textReceived, ms);

            }

        } catch (IOException | NullPointerException ioe) {
            ms.closeStreams();
            logger.error("run", ioe);
        }

    } // end run    
    
    //===========================================================================
    /**
     * distribute the request.
     */
    public void attendRequest(String jsonString, ModelSocket ms) {

        if (jsonString == null || jsonString.length() < 1) {
            throw new IllegalArgumentException("jsonString is null or empty");
        }

        JSONObject jsono = new JSONObject(jsonString);

        if (!jsono.has("request")) {
            throw new RuntimeException("request is null or empty");
        }

        String request = jsono.getString("request");

        if (request == null || request.length() < 1) {
            throw new RuntimeException("request is null or empty");
        }

        try {

            switch (request) {

                //basic configuration ------------------------------------------
                case "connectivity":
                    new ProcessConnectivity(ms, jsono).connectivity();
                    break;
                case "getConfiguration":
                    new ProcessConfiguration(ms, jsono).getConfiguration();
                    break;
                case "saveConfiguration":
                    new ProcessConfiguration(ms, jsono).saveConfiguration();                    
                    break;
                case "addNotifier":
                    new ProcessNotifier(ms, jsono).addNotifier();
                    break;
                case "getNotifiers":
                    new ProcessNotifier(ms, jsono).getNotifiers();
                    break;
                case "getNotifier":
                    new ProcessNotifier(ms, jsono).getNotifier();
                    break;
                case "deleteServerNotifier":
                    new ProcessNotifier(ms, jsono).deleteNotifier();
                    break;
                    
                //--------------------------------------------------------------
                case "createTask":
                    // to do here
                    break;    
                    
                default:
                    responseUnknownResquest();
                    break;

            }
            
        } catch (Exception e) {
            logger.error("attendRequest", e);
        } finally {
            ms.closeStreams();
        }

    } // end attendRequest

    //==========================================================================
    private void responseUnknownResquest() throws IOException {
        ms.send(JSONUtilities.getJSONError("unknown request"));
    } // end responseUnknownResquest

} // end class
