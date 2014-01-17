package model.util;

/**
 *
 * @author skuarch
 */
public class JSONUtilities {

    //==========================================================================
    /**
     * this class doesn't need a constructor.
     */
    private JSONUtilities() {
    } // end JSONUtilities

    //==========================================================================
    public static String getJSONError(String error) {
        if (error == null || error.length() < 1) {
            error = "unexpected error";
        }

        return "{\"error\":\"" + error + "\"}";

    } // end getJSONError

} // end class
