package com.belovaoa.config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:config/credential.properties"})
public interface UserCredential extends Config {

    String email();
    String password();
    //String cookie();
}
