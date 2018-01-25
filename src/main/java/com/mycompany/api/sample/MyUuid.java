package com.mycompany.api.sample;

import org.apache.commons.lang3.StringUtils;


public class MyUuid {

    private String uuid;

    public MyUuid(){}
    public MyUuid(String uuid){
        this.uuid = uuid;
    }

    public String getUuid(){
        return StringUtils.defaultIfEmpty(this.uuid, "");
    }

    public void setUuid(String uuid){
        this.uuid = uuid;
    }

    @Override
    public String toString(){
        return String.format("MyUuid[uuid=%s]", StringUtils.defaultIfEmpty(uuid, ""));
    }
}
