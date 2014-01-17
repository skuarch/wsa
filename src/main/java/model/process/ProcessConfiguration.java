package model.process;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import model.common.ModelConfiguration;
import model.net.ModelSocket;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author skuarch
 */
public final class ProcessConfiguration extends Process {

    //==========================================================================
    public ProcessConfiguration(ModelSocket ms, JSONObject jsono) {
        super(ms, jsono);
    } // end ProcessConfiguration   

    //==========================================================================
    public void getConfiguration() {

        HashMap<String, Object> configuration = null;

        try {

            configuration = ModelConfiguration.getHashMapConfiguration();
            ms.send(new JSONObject(configuration).toString());

        } catch (IOException e) {
            logger.error("responseGetConfiguration", e);
            sendError(e.getMessage());
        } finally {
            ms.closeStreams();
        }

    } // end getConfiguration

    //==========================================================================
    public void saveConfiguration() {

        Properties properties = null;
        Iterator iterator = null;
        String key = null;
        String value = null;

        try {

            properties = new Properties();
            iterator = jsono.keys();

            while (iterator.hasNext()) {

                key = (String) iterator.next();
                value = jsono.getString(key);
                
                properties.setProperty(key, (String) value);

            }

            ModelConfiguration.saveConfiguration(properties);
            ms.send("{\"saved\":\"true\"}");

        } catch (JSONException | IOException e) {
            logger.error("responseEditConfiguration", e);
            sendError(e.getMessage());
        } finally {
            ms.closeStreams();
        }

    } // end editConfiguration

} // end class
