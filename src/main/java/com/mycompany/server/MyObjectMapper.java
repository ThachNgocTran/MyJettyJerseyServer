package com.mycompany.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

@Provider
public class MyObjectMapper implements ContextResolver<ObjectMapper> {

    ObjectMapper objMapper;

    public MyObjectMapper(){
        objMapper = new ObjectMapper();
        objMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    @Override
    public ObjectMapper getContext(Class<?> aClass) {
        return objMapper;
    }
}
