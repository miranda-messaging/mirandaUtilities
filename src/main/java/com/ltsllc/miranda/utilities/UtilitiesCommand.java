package com.ltsllc.miranda.utilities;

import java.io.File;

/**
 * Created by clarkhobbie on 7/8/17.
 */
abstract public class UtilitiesCommand implements Command {
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

    public void checkFile (String filename, String message) {
        File file = new File(filename);
        if (!file.exists()) {
            printErrorAndUsageAndExit(message);
        }
    }
}
