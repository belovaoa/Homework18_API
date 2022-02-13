package com.belovaoa.config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:config/credential.properties"})
public interface UserCredential extends Config {

    @DefaultValue("testfordemowebshop@mail.ru")
    String email();

    @DefaultValue("6210test")
    String password();
    //String cookie();
}
