package model.common;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;
import org.json.JSONObject;

/**
 * Business logic for Configuration.
 *
 * @author skuarch
 */
public final class ModelConfiguration {

    //==========================================================================
    /**
     * this class doesn't need a constructor.
     */
    private ModelConfiguration() {
    } // end ModelConfiguration

    //==========================================================================
    /**
     * create a HashMap with all the configuration.
     *
     * @return HashMap<String,Object>
     * @throws IOException
     */
    public static HashMap<String, Object> getHashMapConfiguration() throws IOException {

        CustomProperties customProperties = new CustomProperties();
        HashMap<String, Object> hm = new HashMap<>();

        Enumeration<Object> keys = customProperties.getProperties().keys();
        Enumeration<Object> values = customProperties.getProperties().elements();

        while (keys.hasMoreElements()) {

            hm.put((String) keys.nextElement(), values.nextElement());

        }

        return hm;

    } // end getHashMapConfiguration

    //==========================================================================
    /**
     * create a JSON with all the configuration.
     *
     * @return JSONObject
     * @throws IOException
     */
    public static JSONObject getJSONConfiguration() throws IOException {

        CustomProperties customProperties = new CustomProperties();
        JSONObject jsono = new JSONObject();

        Enumeration<Object> keys = customProperties.getProperties().keys();
        Enumeration<Object> values = customProperties.getProperties().elements();

        while (keys.hasMoreElements()) {

            jsono.accumulate((String) keys.nextElement(), values.nextElement());

        }

        return jsono;

    } // end getHashMapConfiguration

    //==========================================================================
    /**
     * Delete the file and create a new one.
     *
     * @param properties Properties
     * @throws IOException
     */
    public static void saveConfiguration(Properties properties) throws IOException {

        if (properties == null || properties.size() < 1) {
            throw new NullPointerException("properties is null or empty");
        }

        CustomProperties customProperties = new CustomProperties();
        customProperties.storeProperties(properties);

    } // end saveConfiguration

} // end class
