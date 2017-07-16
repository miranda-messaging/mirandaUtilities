package com.ltsllc.miranda.utilities;

import java.io.File;

/**
 * Created by clarkhobbie on 7/8/17.
 */
abstract public class UtilitiesCommand implements Command {
    protected String directory;

    public String getUsage () {
        return
                "utilities <command> [<options>]\n"
                + "    where <command> is one of -u,a,s,n or h\n"
                + "    where <options> are one or more of -k,p,c,d or n";
    }

    public void printErrorAndUsageAndExit (String message) {
        System.err.println(message);
        System.err.println(getUsage());
    }

    public void checkFile (String filename, String message) throws CommandException{
        File file = new File(filename);
        if (!file.exists()) {
            throw new CommandException(message);
        }
    }

    public void checkDirectory (String directory, String message) throws CommandException {
        File file = new File(directory);
        if (!file.isDirectory())
            throw new CommandException(message);
    }

    public String getDirectory() {
        return directory;
    }

    public String getPublicKeyFilenameFor (String name) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getDirectory());
        stringBuilder.append("/");
        stringBuilder.append(name);
        stringBuilder.append(".public.pem");

        return stringBuilder.toString();
    }

    public String getPrivateKeyFilenameFor (String name) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getDirectory());
        stringBuilder.append("/");
        stringBuilder.append(name);
        stringBuilder.append(".private.pem");

        return stringBuilder.toString();
    }
}
