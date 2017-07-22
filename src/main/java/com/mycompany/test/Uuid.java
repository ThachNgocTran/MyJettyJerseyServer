package com.mycompany.test;

import org.apache.commons.lang3.StringUtils;

/**
 * Created on 22/Jul/2017.
 */
public class Uuid {

    private String uuid;

    public Uuid(){}
    public Uuid(String uuid){
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
        return String.format("Uuid[uuid=%s]", StringUtils.defaultIfEmpty(uuid, ""));
    }
}
