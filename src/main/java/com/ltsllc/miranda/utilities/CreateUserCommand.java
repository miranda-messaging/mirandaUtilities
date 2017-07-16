package com.ltsllc.miranda.utilities;

import com.ltsllc.common.util.Utils;
import com.ltsllc.miranda.clientinterface.basicclasses.PrivateKey;
import com.ltsllc.miranda.clientinterface.basicclasses.PublicKey;
import com.ltsllc.miranda.clientinterface.basicclasses.User;
import com.ltsllc.miranda.user.BootstrapUsersFile;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;

/**
 * Created by clarkhobbie on 7/7/17.
 */
public class CreateUserCommand extends UtilitiesCommand {
    private String userName;
    private User.UserTypes userType = User.UserTypes.Unknown;
    private String userDescription;
    private String keystorePasswod;
    private String keystoreFilename;
    private String userFileFilename;
    private String publicKeyFilename;

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public String getPublicKeyFilename() {
        return publicKeyFilename;
    }

    public void setPublicKeyFilename(String publicKeyFilename) {
        this.publicKeyFilename = publicKeyFilename;
    }

    public String getUserDescription() {
        if (null == userDescription)
            userDescription = "";

        return userDescription;
    }

    public void setUserDescription(String userDescription) {
        this.userDescription = userDescription;
    }

    public String getUserFileFilename() {
        return userFileFilename;
    }

    public void setUserFileFilename(String userFileFilename) {
        this.userFileFilename = userFileFilename;
    }

    public String getKeystoreFilename() {
        return keystoreFilename;
    }

    public void setKeystoreFilename(String keystoreFilename) {
        this.keystoreFilename = keystoreFilename;
    }

    public String getKeystorePasswod() {
        return keystorePasswod;
    }

    public void setKeystorePasswod(String keystorePasswod) {
        this.keystorePasswod = keystorePasswod;
    }

    public User.UserTypes getUserType() {
        return userType;
    }

    public void setUserType(User.UserTypes userType) {
        this.userType = userType;
    }

    public String getUserName() {

        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public KeyPair createKeyPair (String name) throws GeneralSecurityException, IOException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        String pem = Utils.toPem(keyPair.getPublic());
        Utils.writeTextFile(getPublicKeyFilenameFor(name), pem);

        pem = Utils.toPem(keyPair.getPrivate());
        Utils.writeTextFile(getPrivateKeyFilenameFor(name), pem);

        return keyPair;
    }

    public java.security.PublicKey loadPublicKey (String userName) throws IOException {
        String pem = Utils.readTextFile(getPublicKeyFilenameFor(userName));
        return Utils.toPublicKey(pem);
    }

    public java.security.PrivateKey loadPrivateKey (String userName) throws IOException, GeneralSecurityException {
        String pem = Utils.readTextFile(getPrivateKeyFilenameFor(userName));
        return Utils.toPrivateKey(pem);
    }

    public void go () throws CommandException {
        try {
            KeyPair keyPair = null;
            if (needToCreateKeyPair(getUserName()))
                keyPair = createKeyPair(getUserName());
            else {
                keyPair = new KeyPair(loadPublicKey(getUserName()), loadPrivateKey(getUserName()));
            }

            PublicKey publicKey = new PublicKey(keyPair.getPublic());
            User user = createUser(getUserName(), getUserType(), getUserDescription(), publicKey);
            user.check();

            BootstrapUsersFile bootstrapUsersFile = new BootstrapUsersFile(getUserFileFilename(), getKeystoreFilename(),
                    getKeystorePasswod());

            bootstrapUsersFile.read();
            bootstrapUsersFile.create(user);
            bootstrapUsersFile.write();
        } catch (Exception e) {
            throw new CommandException(e);
        }
    }

    public boolean needToCreateKeyPair (String userName) {
        File publicKeyFile = new File(getPublicKeyFilenameFor(userName));
        File privateKeyFile = new File(getPrivateKeyFilenameFor(userName));

        if (!publicKeyFile.exists())
            return true;

        if (!privateKeyFile.exists())
            return true;

        return false;
    }

    public User createUser (String name, User.UserTypes userType, String description, PublicKey publicKey)
            throws GeneralSecurityException, IOException
    {
        User user = new User(name, userType, description, publicKey);
        return user;
    }

    @Override
    public void check() throws CommandException {
        if (null == getDirectory()) {
            throw new CommandException("missing directory");
        }

        if (null == getKeystoreFilename()) {
            throw new CommandException("missing keystore");
        }

        if (null == getUserName()) {
            throw new CommandException("missing user name");
        }

        if (User.UserTypes.Unknown == getUserType()) {
            throw new CommandException("missing user type");
        }

        if (null == getUserFileFilename()) {
            throw new CommandException("missing user file");
        }

        String message = "the directory, " + getDirectory() + ", does not exist or is not a directory";
        checkDirectory(getDirectory(), message);

        message = "the keystore, " + getKeystoreFilename() + ", does not exist";
        checkFile (getKeystoreFilename(), message);

        message = "The users file, " + getUserFileFilename() + ", does not exist";
        checkFile(getUserFileFilename(), message);
    }
}
