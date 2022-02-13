package com.belovaoa.config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:config/credential.properties"})
public interface UserCredential extends Config {

    @DefaultValue("testfordemowebshop@mail.ru")
    String email();

    @DefaultValue("6210test")
    String password();

    @DefaultValue("Nop.customer=ee1baf75-daee-428b-ad29-a2b20005ba7b")
    String cookie();
}
