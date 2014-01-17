package model.common;

import model.util.PropertyUtilities;

/**
 * wrapper of PropertyUtilities.
 *
 * @author skuarch
 */
public class CustomProperties extends PropertyUtilities {

    private static final String FILE_PROPERTIES = "configuration/configuration.properties";

    //==========================================================================
    /**
     * send to the super class the path of the file.
     */
    public CustomProperties() {
        super(FILE_PROPERTIES);
    } // end CustomProperties

} // end class
