package model.beans;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author skuarch
 */
@Entity
@Table(name = "notifier")
@NamedQueries({
    @NamedQuery(name = "notifiers", query = "from Notifier"),
    @NamedQuery(name = "getNotifierByName", query = "from Notifier n where n.name = :name")
})public class Notifier implements Serializable {

    @Id
    @Column(name = "notifier_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "notifier_name", nullable = false)
    private String name;

    @Column(name = "notifier_host", nullable = false)
    private String host;

    @Column(name = "notifier_port", nullable = false)
    private int port;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public String toString() {        
        return "name= " + name + " id=" + id + " host=" + host + " port=" + port;        
    }

} // end class
