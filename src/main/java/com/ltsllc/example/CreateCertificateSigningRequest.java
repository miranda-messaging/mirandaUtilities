package com.ltsllc.example;

import sun.security.pkcs10.PKCS10;
import sun.security.x509.X500Name;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

/**
 * Created by Clark on 6/30/2017.
 */
public class CreateCertificateSigningRequest {
    public static final String UNKNOWN = "unknown";

    private String country = UNKNOWN;
    private String state = UNKNOWN;
    private String city = UNKNOWN;
    private String company = UNKNOWN;
    private String organizationalUnit = UNKNOWN;
    private String commonName = UNKNOWN;
    private PublicKey publicKey;
    private PrivateKey privateKey;

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

    public String getCommonName() {

        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getOrganizationalUnit() {

        return organizationalUnit;
    }

    public void setOrganizationalUnit(String organizationalUnit) {
        this.organizationalUnit = organizationalUnit;
    }

    public String getCompany() {

        return company;
    }

    public void setCompany(String company) {
        this.company = company;
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

    public String getCountry() {

        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public PKCS10 create () throws IOException, GeneralSecurityException {
        String distinguishedName = "c=" + getCountry();
        distinguishedName += ",s=" + getState();
        distinguishedName += ",l=" + getCity();
        distinguishedName += ",o=" + getCompany();
        distinguishedName += ",ou=" + getOrganizationalUnit();
        distinguishedName += ",cn=" + getCommonName();

        X500Name x500Name = new X500Name(distinguishedName);

        String signatureAlgorithmName = "SHA1WithRSA";
        Signature signature = Signature.getInstance(signatureAlgorithmName);
        signature.initSign(getPrivateKey());


        PKCS10 pkcs10 = new PKCS10(getPublicKey());
        pkcs10.encodeAndSign(x500Name,signature);

        return pkcs10;
    }
}
