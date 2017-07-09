package com.ltsllc.miranda.utilities;

import com.ltsllc.common.commadline.CommandLine;

/**
 * Created by clarkhobbie on 7/8/17.
 */
public class UtilitesCommandLine extends CommandLine {
    public enum UtilitiesOptions {
        Unknown (-1),

        CreateUser(1 + Switches.LAST.getIndex()),
        CreateCetificateAuthority(2 + Switches.LAST.getIndex()),
        CreateNode(3 + Switches.LAST.getIndex()),
        CreateSubscription(4 + Switches.LAST.getIndex()),
        Help(5 + Switches.LAST.getIndex()),
        KeystoreFilename(6 + Switches.LAST.getIndex()),
        KeystorePassword(7 + Switches.LAST.getIndex()),
        Username(8 + Switches.LAST.getIndex()),
        UserDescription(9 + Switches.LAST.getIndex()),
        UserPublicKeyFile(10 + Switches.LAST.getIndex()),
        UserFile(11 + Switches.LAST.getIndex());

        private int index;

        UtilitiesOptions(int index) {
            this.index = index;
        }

        public int getIndex() {
            return index;
        }
    }

    public static final String USAGE = "utilities <command>\n"
            + "    where <command> is one of -u,a,n,s or -h";

    public static final String OPTION_CREATE_USER_SHORT = "-u";
    public static final String OPTION_CREATE_USER_LONG = "--createUser";

    public static final String OPTION_CREATE_CERTIFICATE_AUTHORITY_SHORT = "-a";
    public static final String OPTION_CREATE_CERTIFICATE_AUTHORITY_LONG = "--createCetificateAuthority";

    public static final String OPTION_CREATE_NODE_SHORT = "-n";
    public static final String OPTION_CREATE_NODE_LONG = "--createNode";

    public static final String OPTION_CREATE_SUBSCRIPTION_SHORT = "-s";
    public static final String OPTION_CREATE_SUBSCRIPTION_LONG = "--createSubscription";

    public static final String OPTION_HELP_SHORT = "-h";
    public static final String OPTION_HELP_LONG = "--help";

    public static final String OPTION_KEYSTORE_FILE_SHORT = "-k";
    public static final String OPTION_KEYSTORE_FILE_LONG = "--keystore";

    public static final String OPTION_KEYSTORE_PASSWORD_SHORT = "-p";
    public static final String OPTION_KEYSTORE_PASSWORD_LONG = "--keystorePassword";

    public static final String OPTION_USERNAME_SHORT = "-c";
    public static final String OPTION_USERNAME_LONG = "--username";

    public static final String OPTION_USER_DESCRIPTION_SHORT = "-d";
    public static final String OPTION_USER_DESCRIPTION_LONG = "--userDescription";

    public static final String OPTION_USER_PUBLIC_KEY_FILE_SHORT = "-b";
    public static final String OPTION_USER_PUBLIC_KEY_FILE_LONG = "-userPublicKey";

    public static final String OPTION_USER_FILE_SHORT = "-f";
    public static final String OPTION_USER_FILE_LONG = "--userFile";

    public UtilitesCommandLine (String[] argv) {
        super(argv);
    }

    @Override
    public Switches toSwitch(String argument) {
        Switches aSwitch = Switches.PlaceHolder;

        if (argument.equals(OPTION_CREATE_USER_SHORT) || argument.equals(OPTION_CREATE_USER_LONG)) {
            aSwitch.setIndex(UtilitiesOptions.CreateUser.getIndex());
        } else if (argument.equals(OPTION_CREATE_CERTIFICATE_AUTHORITY_SHORT)
                || argument.equals(OPTION_CREATE_CERTIFICATE_AUTHORITY_LONG)) {
            aSwitch.setIndex(UtilitiesOptions.CreateCetificateAuthority.getIndex());
        } else if (argument.equals(OPTION_CREATE_NODE_SHORT) || argument.equals(OPTION_CREATE_NODE_LONG)) {
            aSwitch.setIndex(UtilitiesOptions.CreateNode.getIndex());
        } else if (argument.equals(OPTION_CREATE_SUBSCRIPTION_SHORT) || argument.equals(OPTION_CREATE_SUBSCRIPTION_LONG)) {
            aSwitch.setIndex(UtilitiesOptions.CreateSubscription.getIndex());
        } else if (argument.equals(OPTION_HELP_SHORT) || argument.equals(OPTION_HELP_LONG)) {
            aSwitch.setIndex(UtilitiesOptions.Help.getIndex());
        } else if (argument.equals(OPTION_KEYSTORE_FILE_SHORT) || argument.equals(OPTION_KEYSTORE_FILE_LONG)) {
            aSwitch.setIndex(UtilitiesOptions.KeystoreFilename.getIndex());
        } else if (argument.equals(OPTION_KEYSTORE_PASSWORD_SHORT) || argument.equals(OPTION_KEYSTORE_PASSWORD_LONG)) {
            aSwitch.setIndex(UtilitiesOptions.KeystorePassword.getIndex());
        } else if (argument.equals(OPTION_USERNAME_SHORT) || argument.equals(OPTION_USERNAME_LONG)) {
            aSwitch.setIndex(UtilitiesOptions.Username.getIndex());
        } else if (argument.equals(OPTION_USER_DESCRIPTION_SHORT) || argument.equals(OPTION_USER_DESCRIPTION_LONG)) {
            aSwitch.setIndex(UtilitiesOptions.UserDescription.getIndex());
        } else if (argument.equals(OPTION_USER_PUBLIC_KEY_FILE_SHORT) || argument.equals(OPTION_USER_PUBLIC_KEY_FILE_LONG)) {
            aSwitch.setIndex(UtilitiesOptions.UserPublicKeyFile.getIndex());
        } else if (argument.equals(OPTION_USER_FILE_SHORT) || argument.equals(OPTION_USER_FILE_LONG)) {
            aSwitch.setIndex(UtilitiesOptions.UserFile.getIndex());
        } else {
            String command = argument;
            if (argument.startsWith("-"))
                command = argument.substring(1);

            printErrorAndUsageAndExit("unknown command: " + command);
        }

        return aSwitch;
    }

    private Command command;

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    @Override
    public String getUsageString() {
        return USAGE;
    }

    public UtilitiesOptions toOption (Switches aSwitch) {
        UtilitiesOptions option = UtilitiesOptions.Unknown;

        if (aSwitch.getIndex() == UtilitiesOptions.CreateUser.getIndex())
            option = UtilitiesOptions.CreateUser;
        else if (aSwitch.getIndex() == UtilitiesOptions.CreateCetificateAuthority.getIndex())
            option = UtilitiesOptions.CreateCetificateAuthority;
        else if (aSwitch.getIndex() == UtilitiesOptions.CreateNode.getIndex())
            option = UtilitiesOptions.CreateNode;
        else if (aSwitch.getIndex() == UtilitiesOptions.CreateSubscription.getIndex())
            option = UtilitiesOptions.CreateSubscription;
        else if (aSwitch.getIndex() == UtilitiesOptions.Username.getIndex())
            option = UtilitiesOptions.Username;
        else if (aSwitch.getIndex() == UtilitiesOptions.UserDescription.getIndex())
            option = UtilitiesOptions.UserDescription;
        else if (aSwitch.getIndex() == UtilitiesOptions.UserPublicKeyFile.getIndex())
            option = UtilitiesOptions.UserPublicKeyFile;
        else if (aSwitch.getIndex() == UtilitiesOptions.UserPublicKeyFile.getIndex())
            option = UtilitiesOptions.UserFile;
        else {
            printErrorAndUsageAndExit("unkrecognized option: " + aSwitch);
        }

        return option;
    }

    @Override
    public void processSwitch(Switches aSwitch) {
        UtilitiesOptions option = toOption(aSwitch);

        switch (option) {
            case CreateCetificateAuthority: {
                CreateCertificateCommand createCertifcateCommand = new CreateCertificateCommand();
                setCommand(createCertifcateCommand);
                break;
            }

            case CreateSubscription: {
                CreateUserCommand createUserCommand = new CreateUserCommand();
                setCommand(createUserCommand);
                break;
            }

            case CreateNode: {
                CreateNodeCommand createNodeCommand = new CreateNodeCommand();
                setCommand(createNodeCommand);
                break;
            }

            case CreateUser: {
                CreateUserCommand createUserCommand = new CreateUserCommand();
                setCommand(createUserCommand);
                break;
            }

            case Help: {
                HelpCommand helpCommand = new HelpCommand();
                setCommand(helpCommand);
                break;
            }

            case KeystoreFilename: {
                processKeystoreFilename();
                break;
            }

            case KeystorePassword: {
                processKeystorePassword();
                break;
            }

            case Username: {
                processUsername();
                break;
            }

            case UserDescription: {
                processUserDescription();
                break;
            }

            case UserPublicKeyFile: {
                processUserPublicKeyFile();
                break;
            }

            case UserFile: {
                processUserFile();
                break;
            }

            default: {
                printErrorAndUsageAndExit("unrecognized option: " + option);
            }
        }
    }

    public CreateUserCommand getCreateUserCommand () {
        return (CreateUserCommand) getCommand();
    }

    public void processKeystoreFilename () {
        getCreateUserCommand().setKeystoreFilename(getArgAndAdvanceOrError("missing keystore filename"));
    }

    public void processKeystorePassword () {
        getCreateUserCommand().setKeystorePasswod(getArgAndAdvanceOrError("missing keystore password"));
    }

    public void processUsername () {
        getCreateUserCommand().setUserName(getArgAndAdvanceOrError("missing username"));
    }

    public void processUserPublicKeyFile () {
        getCreateUserCommand().setPublicKeyFilename(getArgAndAdvanceOrError("missing public key file"));
    }

    public void processUserDescription () {
        getCreateUserCommand().setUserFileFilename(getArgAndAdvanceOrError("missing user description"));
    }

    public void processUserFile () {
        getCreateUserCommand().setUserFileFilename(getArgAndAdvanceOrError("missing user file"));
    }
}
