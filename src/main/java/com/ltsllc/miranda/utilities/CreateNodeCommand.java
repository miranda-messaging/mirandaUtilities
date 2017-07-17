package com.ltsllc.miranda.utilities;

import com.ltsllc.common.commadline.CommandException;

/**
 * Created by clarkhobbie on 7/8/17.
 */
public class CreateNodeCommand extends UtilitiesCommand {
    private String caPrivateKeyFilename;
    private String caPrivateKeyPassword;

    public String getCaPrivateKeyPassword() {
        return caPrivateKeyPassword;
    }

    public void setCaPrivateKeyPassword(String caPrivateKeyPassword) {
        this.caPrivateKeyPassword = caPrivateKeyPassword;
    }

    public String getCaPrivateKeyFilename() {

        return caPrivateKeyFilename;
    }

    public void setCaPrivateKeyFilename(String caPrivateKeyFilename) {
        this.caPrivateKeyFilename = caPrivateKeyFilename;
    }

    @Override
    public void go() throws CommandException {

    }

    @Override
    public void check() throws CommandException {
        if (null == getCaPrivateKeyFilename())
            throw new CommandException("missing certificate authority filename");

        String message = "The certificate authority file, " + getCaPrivateKeyFilename() + ", does not exist";
        checkFile(getCaPrivateKeyFilename(), message);

        if (null == getCaPrivateKeyPassword())
            throw new CommandException("missing certificate authority password");
    }
}
