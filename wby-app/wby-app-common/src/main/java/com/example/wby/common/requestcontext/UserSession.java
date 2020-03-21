package com.example.wby.common.requestcontext;

import java.util.Locale;


public class UserSession {

    private String userId;
    private String personId;
    private String personName;
    private String personOrgId;
    private String personOrgName;
    private String clientIp;
    private String clientMac;
    private String tgc;
    private Locale language;

    public UserSession() {
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPersonId() {
        return this.personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getClientIp() {
        return this.clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getClientMac() {
        return this.clientMac;
    }

    public void setClientMac(String clientMac) {
        this.clientMac = clientMac;
    }

    public String getTgc() {
        return this.tgc;
    }

    public void setTgc(String tgc) {
        this.tgc = tgc;
    }

    public Locale getLanguage() {
        return this.language;
    }

    public void setLanguage(Locale language) {
        this.language = language;
    }

    public String getPersonName() {
        return this.personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonOrgId() {
        return this.personOrgId;
    }

    public void setPersonOrgId(String personOrgId) {
        this.personOrgId = personOrgId;
    }

    public String getPersonOrgName() {
        return this.personOrgName;
    }

    public void setPersonOrgName(String personOrgName) {
        this.personOrgName = personOrgName;
    }
}
