package com.ltsllc.miranda.utilities;

import com.ltsllc.common.commadline.CommandException;
import com.ltsllc.common.util.Utils;
import com.ltsllc.miranda.clientinterface.basicclasses.CertificateSigningRequest;
import com.ltsllc.miranda.clientinterface.basicclasses.PrivateKey;
import com.ltsllc.miranda.clientinterface.basicclasses.PublicKey;
import org.bouncycastle.operator.OperatorException;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.cert.X509Certificate;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by miranda on 7/15/2017.
 */
public class CreateCertificateAuthorityCommand extends UtilitiesCommand {
    public static final String UNKNOWN = "Unknown";

    private String countryCode = UNKNOWN;
    private String state = UNKNOWN;
    private String city = UNKNOWN;
    private String zip = UNKNOWN;
    private String company = UNKNOWN;
    private String division = UNKNOWN;
    private String name = UNKNOWN;

    private String password;
    private PublicKey publicKey;
    private PrivateKey privateKey;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(PrivateKey privateKey) {
        this.privateKey = privateKey;
    }

    public PublicKey getPublicKey() {

        return publicKey;
    }

    public void setPublicKey(PublicKey publicKey) {
        this.publicKey = publicKey;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDivision() {

        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getCompany() {

        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getZip() {

        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCity() {

        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {

        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public void check () throws CommandException {
        if (null == getPassword()) {
            throw new CommandException("missing password");
        }
    }

    public void go () throws CommandException {
        try {
            if (needKeys())
                createKeyPair();

            String distinguishedName = createDistinguishedNameString();
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());

            Date now = calendar.getTime();
            calendar.add(Calendar.YEAR, 10);
            Date tenYearsFromNow = calendar.getTime();

            CertificateSigningRequest csr = createCertificateSigningRequest();
            X509Certificate x509Certificate = getPrivateKey().sign(csr, distinguishedName, now, tenYearsFromNow);
            Utils.writeAsPem("ca.certificate.pem", getPassword(), x509Certificate);
        } catch (GeneralSecurityException|IOException|OperatorException e) {
            throw new CommandException(e);
        }
    }

    public boolean needKeys () {
        return null == getPublicKey() || null == getPrivateKey();
    }

    public void createKeyPair () throws GeneralSecurityException, IOException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        PublicKey publicKey = new PublicKey(keyPair.getPublic());
        setPublicKey(publicKey);
        Utils.writeAsPem("ca.public.pem", getPassword(), publicKey.getSecurityPublicKey());

        PrivateKey privateKey = new PrivateKey(keyPair.getPrivate());
        setPrivateKey(privateKey);
        Utils.writeAsPem("ca.private.pem", getPassword(), privateKey.getSecurityPrivateKey());
    }

    public String createDistinguishedNameString () {
        String dn = "c=" + getCountryCode();
        dn += ", st=" + getState();
        dn += ", l=" + getCity();
        dn += ", o=" + getCompany();
        dn += ", ou=" + getDivision();
        dn += ", cn=" + getName();

        return dn;
    }

    public CertificateSigningRequest createCertificateSigningRequest () {
        String distinguishedName = createDistinguishedNameString();
        CertificateSigningRequest csr = new CertificateSigningRequest(getPublicKey(),getPrivateKey(),distinguishedName);
        return csr;
    }
}
