package com.leo.taobao.security;

import lombok.Data;
import lombok.Synchronized;
import org.jetbrains.annotations.Contract;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
@Data
public class AppCredential implements Serializable {

    private static final long serialVersionUID = 865753493245191256L;

    private String userId;
    private String appId;
    private Map<String, String> credentials;
    private String passphrase;
    private long timestamp;

    /**
     *
     */
    @Contract(pure = true)
    public AppCredential() {
        credentials = new HashMap<>();
    }

    /**
     * @param userId
     */
    @Contract(pure = true)
    public AppCredential(String userId) {
        this();
        this.userId = userId;
    }

    /**
     * @return
     */
    public String getPrimaryCredential() {
        String credential = "";
        for (String c : credentials.values()) {
            if (null != c && !c.isEmpty()) {
                credential = c;
                break;
            }
        }
        return credential;
    }

    @Synchronized
    public String getAppId() {
        return this.appId;
    }

    @Synchronized
    public void setAppId(String appId) {
        this.appId = appId;
    }

    @Synchronized
    public String getPassphrase() {
        return this.passphrase;
    }

    @Synchronized
    public void setPassphrase(String passphrase) {
        this.passphrase = passphrase;
    }

    public String renewPassphrase() {
        String passphrase = PasswordUtils.random();
        this.setPassphrase(passphrase);
        return passphrase;
    }
}
