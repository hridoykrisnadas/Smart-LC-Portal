package com.hridoykrisna.smartlcportal.util;

public class URLConstrain {
    public static final String VERSION = "/v1";
    public static final String API = "/api";

    private URLConstrain() {
    }

    public static class APPUser{
        public static final String  ROOT = API + VERSION+"/user";
        public static final String registration = "/registration";
    }

    public static class AuthManagement{
        public static final String  ROOT = API + "/auth";
        public static final String  LOGIN = "/login";
        public static final String REGISTRATION = "/registration";
    }
}
