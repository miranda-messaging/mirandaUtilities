package com.ltsllc.miranda.utilities;

import com.ltsllc.common.util.Utils;
import com.ltsllc.miranda.clientinterface.basicclasses.PrivateKey;
import org.bouncycastle.operator.OperatorException;
import org.bouncycastle.pkcs.PKCSException;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;

/**
 * Created by clarkhobbie on 7/8/17.
 */
public class CreateNodeCommand extends UtilitiesCommand {
    private String certificateAuthorityFilename;
    private String certificateAuthorityPassword;

    public String getCertificateAuthorityPassword() {
        return certificateAuthorityPassword;
    }

    public void setCertificateAuthorityPassword(String certificateAuthorityPassword) {
        this.certificateAuthorityPassword = certificateAuthorityPassword;
    }

    public String getCertificateAuthorityFilename() {

        return certificateAuthorityFilename;
    }

    public void setCertificateAuthorityFilename(String certificateAuthorityFilename) {
        this.certificateAuthorityFilename = certificateAuthorityFilename;
    }

    @Override
    public void go() throws CommandException {

    }

    @Override
    public void check() throws CommandException {
        if (null == getCertificateAuthorityFilename())
            throw new CommandException("missing certificate authoritity filename");

        String message = "The certificate authority file, " + getCertificateAuthorityFilename() + ", does not exist";
        checkFile(getCertificateAuthorityFilename(), message);

        if (null == getCertificateAuthorityPassword())
            throw new CommandException("missing certificate authority password");
    }
}
