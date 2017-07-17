package com.ltsllc.miranda.utilites;

import com.ltsllc.miranda.clientinterface.basicclasses.CertificateSigningRequest;
import com.ltsllc.common.commadline.CommandException;
import com.ltsllc.miranda.utilities.CreateCertificateAuthorityCommand;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by miranda on 7/16/2017.
 */
public class TestCreateCertificateAuthority {
    private CreateCertificateAuthorityCommand command;

    public CreateCertificateAuthorityCommand getCommand() {
        return command;
    }

    public static final String TEST_PASSWORD = "whatever";
    public static final String TEST_COUNTRY_CODE = "US";
    public static final String TEST_STATE = "Colorado";
    public static final String TEST_CITY = "Denver";
    public static final String TEST_COMPANY = "Oracle";
    public static final String TEST_DIVISION = "Development";
    public static final String TEST_NAME = "John Doe";

    @Before
    public void setup () {
        this.command = new CreateCertificateAuthorityCommand();
        getCommand().setCountryCode(TEST_COUNTRY_CODE);
        getCommand().setState(TEST_STATE);
        getCommand().setCity(TEST_CITY);
        getCommand().setCompany(TEST_COMPANY);
        getCommand().setDivision(TEST_DIVISION);
        getCommand().setName(TEST_NAME);
        getCommand().setPassword(TEST_PASSWORD);
    }

    @Test
    public void testGoSuccess () {
        CommandException ce = null;

        try {
            getCommand().go();
        } catch (CommandException e) {
            ce = e;
        }

        assert (null == ce);
    }

    @Test
    public void testGoMissingPassword () {
        getCommand().setPassword(null);

        CommandException ce = null;
        try {
            getCommand().check();
        } catch (CommandException e) {
            ce = e;
        }

        assert (null != ce);
    }

    @Test
    public void testCreateKeyPair () {
        assert (getCommand().getPublicKey() == null);
        assert (getCommand().getPrivateKey() == null);

        Throwable t = null;

        try {
            getCommand().createKeyPair();
        } catch (Exception e) {
            t = e;
        }

        assert (t == null);
        assert (getCommand().getPublicKey() != null);
        assert (getCommand().getPrivateKey() != null);
    }

    public boolean stringContains (String stringToBeSearched, String stringToLookFor) {
        if (stringToBeSearched == null || stringToLookFor == null)
            return stringToLookFor == stringToBeSearched;

        int index = stringToBeSearched.indexOf(stringToLookFor);

        return index != -1;
    }

    @Test
    public void testCreateDistinguishedName () throws Exception {
        String dn = getCommand().createDistinguishedNameString();

        assert (stringContains(dn, TEST_COUNTRY_CODE));
        assert (stringContains(dn, TEST_STATE));
        assert (stringContains(dn, TEST_CITY));
        assert (stringContains(dn, TEST_COMPANY));
        assert (stringContains(dn, TEST_DIVISION));
        assert (stringContains(dn, TEST_NAME));
    }

    @Test
    public void testCreateCertificateSingingRequest () {
        Throwable t = null;

        try {
            getCommand().createKeyPair();
            java.security.PrivateKey privateKey = getCommand().getPrivateKey().getSecurityPrivateKey();
            java.security.PublicKey publicKey = getCommand().getPublicKey().getSecurityPublicKey();
            CertificateSigningRequest csr = getCommand().createCertificateSigningRequest();
            String subject = csr.getSubject();
            assert (csr.getJsPrivateKey() == privateKey);
            assert (csr.getJsPublicKey() == publicKey);
            assert (stringContains(subject, TEST_NAME));
            assert (stringContains(subject, TEST_COUNTRY_CODE));
            assert (stringContains(subject, TEST_STATE));
            assert (stringContains(subject, TEST_CITY));
            assert (stringContains(subject, TEST_COMPANY));
            assert (stringContains(subject, TEST_DIVISION));
        } catch (Exception e) {
            t = e;
        }

        assert (t == null);
    }


}
