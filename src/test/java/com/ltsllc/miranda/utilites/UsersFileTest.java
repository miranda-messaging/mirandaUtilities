package com.ltsllc.miranda.utilites;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.ltsllc.common.util.Utils;
import com.ltsllc.miranda.EncryptedMessage;
import com.ltsllc.miranda.clientinterface.basicclasses.PrivateKey;
import com.ltsllc.miranda.clientinterface.basicclasses.PublicKey;
import com.ltsllc.miranda.clientinterface.basicclasses.User;
import com.ltsllc.miranda.user.JSPublicKeySerializer;

import java.io.IOException;
import java.lang.reflect.Type;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by miranda on 7/11/2017.
 */
public class UsersFileTest {
    private String filename;
    private List<User> users;
    private String keystoreFilename;
    private String keystorePassword;

    public UsersFileTest(String filename, String keystoreFilename, String keystorePassword) {
        this.filename = filename;
        this.keystoreFilename = keystoreFilename;
        this.keystorePassword = keystorePassword;
    }

    public List<User> getUsers() {
        if (null == users)
            users = new ArrayList<User>();

        return users;
    }

    public String getKeystorePassword() {
        return keystorePassword;
    }

    public void setKeystorePassword(String keystorePassword) {
        this.keystorePassword = keystorePassword;
    }

    public String getKeystoreFilename() {
        return keystoreFilename;
    }

    public void setKeystoreFilename(String keystoreFilename) {
        this.keystoreFilename = keystoreFilename;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void load () throws IOException, GeneralSecurityException
    {
        KeyStore keyStore = Utils.loadKeyStore(getKeystoreFilename(), getKeystorePassword());
        java.security.PrivateKey jsPrivateKey = (java.security.PrivateKey) keyStore.getKey("private", getKeystorePassword().toCharArray());
        PrivateKey privateKey = new PrivateKey(jsPrivateKey);
        String cipherTextString = Utils.readTextFile(getFilename());
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(PublicKey.class, new JSPublicKeySerializer());
        Gson gson = builder.create();

        EncryptedMessage encryptedMessage = gson.fromJson(cipherTextString, EncryptedMessage.class);
        byte[] plainText = privateKey.decrypt(encryptedMessage);
        String plainTextString = new String(plainText);

        Type t = new TypeToken<List<User>> () {}.getType();
        List<User> users = gson.fromJson(plainTextString, t);
        setUsers(users);
    }

    public boolean contains (String userName) {
        for (User user : getUsers()) {
            if (user.getName().equals(userName))
                return true;
        }

        return false;
    }
}
