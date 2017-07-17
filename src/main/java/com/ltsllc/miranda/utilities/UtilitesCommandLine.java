package com.ltsllc.miranda.utilities;

import com.ltsllc.common.commadline.CommandException;
import com.ltsllc.common.commadline.CommandLine;

/**
 * Created by clarkhobbie on 7/8/17.
 */
public class UtilitesCommandLine extends CommandLine {
    public enum UtilitiesOptions {
        Unknown (-1),

        CreateUser(1 + Switches.LAST.getIndex()),
        CreateCertificateAuthority(2 + Switches.LAST.getIndex()),
        CreateNode(3 + Switches.LAST.getIndex()),
        CreateSubscription(4 + Switches.LAST.getIndex()),
        Help(5 + Switches.LAST.getIndex()),
        KeystoreFilename(6 + Switches.LAST.getIndex()),
        KeystorePassword(7 + Switches.LAST.getIndex()),
        Username(8 + Switches.LAST.getIndex()),
        UserDescription(9 + Switches.LAST.getIndex()),
        UserPublicKeyFile(10 + Switches.LAST.getIndex()),
        UsersFile(11 + Switches.LAST.getIndex()),
        CountryCode(12 + Switches.LAST.getIndex()),
        State(13 + Switches.LAST.getIndex()),
        City(14 + Switches.LAST.getIndex()),
        Company(15 + Switches.LAST.getIndex()),
        Division(16 + Switches.LAST.getIndex()),
        Password(17 + Switches.LAST.getIndex());

        private int index;

        UtilitiesOptions(int index) {
            this.index = index;
        }

        public int getIndex() {
            return index;
        }

        public static UtilitiesOptions toUtilitiesOptions (Switches aSwitch) throws CommandException {
            UtilitiesOptions option = Unknown;

            if (aSwitch.getIndex() == CreateUser.getIndex())
                option = CreateUser;
            else if (aSwitch.getIndex() == CreateCertificateAuthority.getIndex())
                option = CreateCertificateAuthority;
            else if (aSwitch.getIndex() == CreateNode.getIndex())
                option = CreateNode;
            else if (aSwitch.getIndex() == CreateSubscription.getIndex())
                option = CreateSubscription;
            else if (aSwitch.getIndex() == Help.getIndex())
                option = Help;
            else if (aSwitch.getIndex() == KeystoreFilename.getIndex())
                option = KeystoreFilename;
            else if (aSwitch.getIndex() == KeystorePassword.getIndex())
                option = KeystorePassword;
            else if (aSwitch.getIndex() == Username.getIndex())
                option = Username;
            else if (aSwitch.getIndex() == UserDescription.getIndex())
                option = UserDescription;
            else if (aSwitch.getIndex() == UserPublicKeyFile.getIndex())
                option = UserPublicKeyFile;
            else if (aSwitch.getIndex() == UsersFile.getIndex())
                option = UsersFile;
            else if (aSwitch.getIndex() == CountryCode.getIndex())
                option = CountryCode;
            else if (aSwitch.getIndex() == State.getIndex())
                option = State;
            else if (aSwitch.getIndex() == City.getIndex())
                option = City;
            else if (aSwitch.getIndex() == Company.getIndex())
                option = Company;
            else if (aSwitch.getIndex() == Division.getIndex())
                option = Division;
            else if (aSwitch.getIndex() == Password.getIndex())
                option = Password;
            else {
                throw new CommandException("Unrecognized switch index: " + aSwitch.getIndex());
            }

            return option;
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

    public static final String OPTION_USERS_FILE_SHORT = "-f";
    public static final String OPTION_USERS_FILE_LONG = "--usersFile";

    public static final String OPTION_COUNTRY_CODE_SHORT = "-g";
    public static final String OPTION_COUNTRY_CODE_LONG = "--countryCode";

    public static final String OPTION_STATE_SHORT = "-h";
    public static final String OPTION_STATE_LONG = "--state";

    public static final String OPTION_CITY_SHORT = "-i";
    public static final String OPTION_CITY_LONG = "--city";

    public static final String OPTION_COMPANY_SHORT = "-j";
    public static final String OPTION_COMPANY_LONG = "--company";

    public static final String OPTION_DIVISION_SHORT = "-k";
    public static final String OPTION_DIVISION_LONG = "--division";

    public static final String OPTION_PASSWORD_SHORT = "-l";
    public static final String OPTION_PASSWORD_LONG = "--password";


    public UtilitesCommandLine (String[] argv) {
        super(argv);
    }

    @Override
    public Switches toSwitch(String argument) throws CommandException {
        Switches aSwitch = Switches.PlaceHolder;

        if (argument.equals(OPTION_CREATE_USER_SHORT) || argument.equals(OPTION_CREATE_USER_LONG)) {
            aSwitch.setIndex(UtilitiesOptions.CreateUser.getIndex());
        } else if (argument.equals(OPTION_CREATE_CERTIFICATE_AUTHORITY_SHORT)
                || argument.equals(OPTION_CREATE_CERTIFICATE_AUTHORITY_LONG)) {
            aSwitch.setIndex(UtilitiesOptions.CreateCertificateAuthority.getIndex());
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
        } else if (argument.equals(OPTION_USERS_FILE_SHORT) || argument.equals(OPTION_USERS_FILE_LONG)) {
            aSwitch.setIndex(UtilitiesOptions.UsersFile.getIndex());
        } else if (argument.equals(OPTION_COUNTRY_CODE_SHORT) || argument.equals(OPTION_COUNTRY_CODE_LONG)) {
            aSwitch.setIndex(UtilitiesOptions.CountryCode.getIndex());
        } else if (argument.equals(OPTION_STATE_SHORT) || argument.equals(OPTION_STATE_LONG)) {
            aSwitch.setIndex(UtilitiesOptions.State.getIndex());
        } else if (argument.equals(OPTION_CITY_SHORT) || argument.equals(OPTION_CITY_LONG)) {
            aSwitch.setIndex(UtilitiesOptions.City.getIndex());
        } else if (argument.equals(OPTION_COMPANY_SHORT) || argument.equals(OPTION_COMPANY_LONG)) {
            aSwitch.setIndex(UtilitiesOptions.Company.getIndex());
        } else if (argument.equals(OPTION_DIVISION_SHORT) || argument.equals(OPTION_DIVISION_LONG)) {
            aSwitch.setIndex(UtilitiesOptions.Division.getIndex());
        } else if (argument.equals(OPTION_PASSWORD_SHORT) || argument.equals(OPTION_PASSWORD_LONG)) {
            aSwitch.setIndex(UtilitiesOptions.Password.getIndex());
        } else {
            String command = argument;
            if (argument.startsWith("-"))
                command = argument.substring(1);

            throw new CommandException("unrecognized command: " + command);
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

    @Override
    public void processSwitch(Switches aSwitch) throws CommandException {
        UtilitiesOptions option = UtilitiesOptions.toUtilitiesOptions(aSwitch);

        switch (option) {
            case CreateCertificateAuthority: {
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

            case UsersFile: {
                processUserFile();
                break;
            }

            case City: {
                processCity();
                break;
            }

            case Division: {
                processDivision();
                break;
            }

            case State: {
                processState();
                break;
            }

            case Company: {
                processCompany();
                break;
            }

            case Password: {
                processPassword();
                break;
            }

            case CountryCode: {
                processCountryCode();
                break;
            }
            default: {
                super.processSwitch(aSwitch);
                break;
            }
        }
    }

    public CreateUserCommand getCreateUserCommand () {
        return (CreateUserCommand) getCommand();
    }

    public CreateCertificateAuthorityCommand getCreateCertificateAuthorityCommand () {
        return (CreateCertificateAuthorityCommand) getCommand();
    }

    public void processKeystoreFilename () {
        getCreateUserCommand().setKeystoreFilename(getArgAndAdvanceOrError("missing keystore filename"));
    }

    public void processKeystorePassword () {
        getCreateUserCommand().setKeystorePasswod(getArgAndAdvanceOrError("missing keystore password"));
    }

    public void processUsername () {
        if (getCommand() instanceof CreateUserCommand)
            getCreateUserCommand().setUserName(getArgAndAdvanceOrError("missing username"));
        else if (getCommand() instanceof CreateCertificateAuthorityCommand)
            getCreateCertificateAuthorityCommand().setName(getArgAndAdvanceOrError("missing name"));
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

    public void processCity () {
        getCreateCertificateAuthorityCommand().setCity(getArgAndAdvanceOrError("missing city"));
    }

    public void processDivision () {
        getCreateCertificateAuthorityCommand().setDivision(getArgAndAdvanceOrError("missing division"));
    }

    public void processState () {
        getCreateCertificateAuthorityCommand().setState(getArgAndAdvanceOrError("missing state"));
    }

    public void processCompany () {
        getCreateCertificateAuthorityCommand().setCompany(getArgAndAdvanceOrError("missing company"));
    }

    public void processPassword () {
        getCreateCertificateAuthorityCommand().setPassword(getArgAndAdvanceOrError("missing password"));
    }

    public void processCountryCode () {
        getCreateCertificateAuthorityCommand().setCountryCode(getArgAndAdvanceOrError("missing country code"));
    }
}
