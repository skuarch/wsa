package model.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import model.util.IOUtilities;

/**
 * business logic for socket
 * @author skuarch
 */
public class ModelSocket {

    private Socket socket = null;
    private InputStream inputStream = null;
    private ObjectInputStream objectInputStream = null;
    private OutputStream outputStream = null;
    private ObjectOutputStream objectOutputStream = null;

    //==========================================================================
    public ModelSocket(Socket socket) {
        this.socket = socket;
    } // end ModelSocket

    //==========================================================================
    public String receive() throws IOException {

        if (socket == null || socket.isClosed()) {
            throw new NullPointerException("socket is null or close");
        }

        String msg = null;

        try {            
            
            inputStream = socket.getInputStream();
            objectInputStream = new ObjectInputStream(inputStream);
            msg = objectInputStream.readUTF();            

        } catch (IOException e) {
            closeStreams();
            throw e;
        }

        return msg;

    } // end receive

    //==========================================================================
    public void send(String text) throws IOException {

        if (text == null) {
            throw new NullPointerException("text is null");
        }

        if (socket == null || socket.isClosed()) {
            throw new NullPointerException("socket is null or close");
        }

        try {
            
            outputStream = socket.getOutputStream();            
            objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeUTF(text);
            objectOutputStream.flush();
            
        } catch (IOException e) {
            closeStreams();
            throw e;
        }
    } // end send

    //==========================================================================
    public void setKeepAlive(boolean keepAlive) throws SocketException {
        socket.setKeepAlive(keepAlive);
    }

    //==========================================================================
    public void setSoTimeout(int timeout) throws SocketException {
        socket.setSoTimeout(timeout);
    }

    //==========================================================================
    public void closeStreams() {
        IOUtilities.closeInputStream(inputStream);
        IOUtilities.closeInputStream(objectInputStream);
        IOUtilities.closeOutputStream(outputStream);
        IOUtilities.closeOutputStream(objectOutputStream);
        IOUtilities.closeSocket(socket);
        socket = null;
    } // end closeStreams

} // end class
