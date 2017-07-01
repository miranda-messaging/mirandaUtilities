package com.ltsllc.miranda.utilities;


import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMDecryptorProvider;
import org.bouncycastle.openssl.PEMEncryptor;
import org.bouncycastle.openssl.PEMWriter;
import org.bouncycastle.openssl.jcajce.JcePEMDecryptorProviderBuilder;
import org.bouncycastle.openssl.jcajce.JcePEMEncryptorBuilder;
import sun.security.pkcs10.PKCS10;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.StringWriter;
import java.security.*;

/**
 * Created by Clark on 6/29/2017.
 */
public class Utilities {
    public static void main(String[] argv) throws Exception {
        Utilities utilities = new Utilities();
        utilities.go();
    }

    public String toPemString(PublicKey publicKey, String password) throws Exception {
        PEMWriter pemWriter = null;
        StringWriter stringWriter = null;
        JcePEMEncryptorBuilder jcePEMEncryptorBuilder = new JcePEMEncryptorBuilder("AES-128-cbc");
        jcePEMEncryptorBuilder.setSecureRandom(new SecureRandom());
        PEMEncryptor encryptor = jcePEMEncryptorBuilder.build(password.toCharArray());
        stringWriter = new StringWriter();
        pemWriter = new PEMWriter(stringWriter);
        pemWriter.writeObject(publicKey, encryptor);
        pemWriter.close();

        return stringWriter.toString();
    }

    public static final String FILE_NAME = "whatever.public.pem";
    public static final String PASSWORD = "whatever";

    public static void writeTextFile (String filename, String contents) throws Exception {
        FileWriter fileWriter = new FileWriter(filename);
        fileWriter.write(contents);
        fileWriter.close();
    }

    public static String readTextFile (String filename) throws Exception {
        FileReader fileReader = new FileReader(filename);
        StringWriter stringWriter = new StringWriter();
        int c = fileReader.read();
        while (c != -1) {
            stringWriter.write(c);
            c = fileReader.read();
        }

        fileReader.close();
        stringWriter.close();
        return stringWriter.toString();
    }

    public void go() throws Exception {
        encrypt();
        decrypt(FILE_NAME);
    }

    public void encrypt () throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        String pemString = toPemString(keyPair.getPublic(), PASSWORD);
        writeTextFile(FILE_NAME, pemString);
    }

    public PublicKey decrypt (String filename) throws Exception {
        String pemString = readTextFile(filename);
        JcePEMDecryptorProviderBuilder jcePEMDecryptorProviderBuilder = new JcePEMDecryptorProviderBuilder();
        jcePEMDecryptorProviderBuilder.setProvider(new BouncyCastleProvider());
        PEMDecryptorProvider pemDecryptorProvider = jcePEMDecryptorProviderBuilder.build(PASSWORD.toCharArray());
        return null;
    }

    public KeyPair createKeyPair () throws GeneralSecurityException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        return keyPairGenerator.generateKeyPair();
    }

    public void createSignatureRequest () throws Exception {
        KeyPair keyPair = createKeyPair();
        CreateCertificateSigningRequest createCertificateSigningRequest = new CreateCertificateSigningRequest();
        createCertificateSigningRequest.setPrivateKey(keyPair.getPrivate());
        createCertificateSigningRequest.setPublicKey(keyPair.getPublic());
        PKCS10 pkcs10 = createCertificateSigningRequest.create();
        pkcs10.print(System.out);
    }
}
