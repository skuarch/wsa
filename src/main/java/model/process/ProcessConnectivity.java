package model.process;

import java.io.IOException;
import model.net.ModelSocket;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author skuarch
 */
public final class ProcessConnectivity extends Process {

    //==========================================================================
    public ProcessConnectivity(ModelSocket ms, JSONObject jsono) {
        super(ms, jsono);
    } // end ProcessConnectivity

    //==========================================================================
    public void connectivity() {

        JSONObject response = null;

        try {

            response = new JSONObject();
            response.accumulate("alive", true);
            ms.send(response.toString());

        } catch (IOException | JSONException e) {
            logger.error("responseConnectivity", e);
            sendError(e.getMessage());
        } finally {
            ms.closeStreams();
        }

    } // end connectivity

} // end class
