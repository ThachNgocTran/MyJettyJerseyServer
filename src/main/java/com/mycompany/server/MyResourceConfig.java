package com.mycompany.server;

import org.glassfish.jersey.server.ResourceConfig;

public class MyResourceConfig extends ResourceConfig {

    public MyResourceConfig(){
        packages("com.mycompany.api");
    }
}
