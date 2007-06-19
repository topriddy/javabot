package javabot.dao.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;

import javabot.operations.BotOperation;

/**
 * Created Jun 17, 2007
 *
 * @author <a href="mailto:jlee@antwerkz.com">Justin Lee</a>
 */
@Entity
@Table(name="configuration")
public class Config implements Serializable {
    private Long id;
    private String server;
    private Integer port;
    private String prefixes;
    private String nick;
    private String password;

    private List<BotOperation> operations;
    private List<Channel> channels;

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long configId) {
        id = configId;
    }

    public List<Channel> getChannels() {
        return channels;
    }

    public void setChannels(List<Channel> list) {
        channels = list;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String botName) {
        nick = botName;
    }

    public List<BotOperation> getOperations() {
        return operations;
    }

    public void setOperations(List<BotOperation> list) {
        operations = list;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String value) {
        password = value;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer portNum) {
        port = portNum;
    }

    public String getPrefixes() {
        return prefixes;
    }

    public void setPrefixes(String nicks) {
        prefixes = nicks;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String ircServer) {
        server = ircServer;
    }
}
