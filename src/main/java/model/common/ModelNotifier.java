package model.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import model.beans.Notifier;
import model.dao.DAO;

/**
 *
 * @author skuarch
 */
public final class ModelNotifier {

    //==========================================================================
    private ModelNotifier() {
    } // end ModelNotifier

    //==========================================================================
    /**
     * add notifier to db.
     *
     * @param notifier Notifier.
     * @return id of the new notifier.
     */
    public static long addNotifier(Notifier notifier) {

        if (notifier == null) {
            throw new NullPointerException("notifier is null");
        }

        long id = DAO.create(notifier);

        return id;

    } // end addNotifier

    //==========================================================================
    public static Notifier getNotifier(String name) {

        if (name == null || name.length() < 1) {
            throw new NullPointerException("name is null or empty");
        }

        HashMap<String,String> parameter = new HashMap<>();
        parameter.put("name", name);
        Notifier notifier = null;
        List<Notifier> list = DAO.query("getNotifierByName", parameter, new Notifier());

        if (list == null || list.size() < 1) {
            return null;
        } else {
            notifier = list.get(0);
        }

        return notifier;

    }// end getNotifier

    //==========================================================================
    /**
     * remove notifier from db.
     *
     * @param notifier Notifier
     */
    public static void removeNotifier(Notifier notifier) {

        if (notifier == null) {
            throw new NullPointerException("notifier is null");
        }

        DAO.delete(notifier);

    } // end 

    //==========================================================================
    public static void updateNotifier(Notifier notifier) {

        if (notifier == null) {
            throw new NullPointerException("notifier is null");
        }

        DAO.update(notifier);
        
    } // end updateNotifier

    //==========================================================================
    public static ArrayList<Notifier> getNotifiers() {

        ArrayList<Notifier> notifiers = null;
        notifiers = new ArrayList<>(DAO.getList(new Notifier()));
        return notifiers;

    } // end getNotifiers    

} // end class
