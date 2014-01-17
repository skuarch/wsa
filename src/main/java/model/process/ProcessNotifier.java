package model.process;

import java.io.IOException;
import java.util.ArrayList;
import model.beans.Notifier;
import model.common.ModelNotifier;
import model.net.ModelSocket;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author skuarch
 */
public class ProcessNotifier extends Process {

    //==========================================================================
    public ProcessNotifier(ModelSocket ms, JSONObject jsono) {
        super(ms, jsono);
    } // end ProcessNotifier  

    //==========================================================================
    public void addNotifier() {

        if (!jsono.has("name") || !jsono.has("host") || !jsono.has("port")) {
            throw new IllegalArgumentException("json is incorrect");
        }

        Notifier notifier = null;

        try {

            notifier = new Notifier();
            notifier.setName(jsono.getString("name"));
            notifier.setHost(jsono.getString("host"));
            notifier.setPort(jsono.getInt("port"));

            ModelNotifier.addNotifier(notifier);
            ms.send("{\"added\":\"true\"}");

        } catch (IOException | IllegalArgumentException | JSONException e) {
            logger.error("addNotifier", e);
            sendError(e.getMessage());
        } finally {
            ms.closeStreams();
        }

    } // end addNotifier

    //==========================================================================
    public void getNotifiers() {

        ArrayList<Notifier> notifiers = null;
        JSONObject jsonResponse = new JSONObject();

        try {

            notifiers = ModelNotifier.getNotifiers();
            jsonResponse.put("notifiers", notifiers);

            ms.send("{\"added\":\"true\"}");

        } catch (IOException | IllegalArgumentException | JSONException e) {
            logger.error("addNotifier", e);
            sendError(e.getMessage());
        } finally {
            ms.closeStreams();
        }

    } // end getNotifiers

    //==========================================================================
    public void deleteNotifier() {

        if (!jsono.has("name")) {
            throw new IllegalArgumentException("json is incorrect");
        }

        String name = null;
        Notifier notifier = null;

        try {

            name = jsono.getString("name");
            notifier = ModelNotifier.getNotifier(name);
            ModelNotifier.removeNotifier(notifier);

        } catch (JSONException e) {
            logger.error("deleteNotifier", e);
            sendError(e.getMessage());
        } finally {
            ms.closeStreams();
        }

    } // end deleteNotifier

    //==========================================================================
    public void getNotifier() {

        if (!jsono.has("name")) {
            throw new IllegalArgumentException("json is incorrect");
        }

        String name = null;
        Notifier notifier = null;
        JSONObject jsonRsponse = null;

        try {

            name = jsono.getString("name");
            notifier = ModelNotifier.getNotifier(name);

            jsonRsponse = new JSONObject();
            jsonRsponse.put("is", notifier.getId());
            jsonRsponse.put("name", notifier.getName());
            jsonRsponse.put("host", notifier.getHost());
            jsonRsponse.put("port", notifier.getPort());

            ms.send(jsonRsponse.toString());

        } catch (IOException | JSONException e) {
            logger.error("deleteNotifier", e);
            sendError(e.getMessage());
        } finally {
            ms.closeStreams();
        }

    } // end getNotifier

}  // end class
