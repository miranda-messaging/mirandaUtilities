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
    private Command command;

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public static void main(String[] argv) throws Exception {
        Utilities utilities = new Utilities();
        UtilitesCommandLine utilitesCommandLine = new UtilitesCommandLine(argv);
        utilities.go(utilitesCommandLine);
    }

    public void go(UtilitesCommandLine utilitesCommandLine) throws Exception {
        utilitesCommandLine.parse();
        setCommand(utilitesCommandLine.getCommand());
        getCommand().check();
        getCommand().go();
    }

}
