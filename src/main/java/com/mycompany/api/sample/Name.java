package com.mycompany.api.sample;

import org.apache.commons.lang3.StringUtils;

public class Name {

    private String lastName;
    private String firstName;

    public Name(){}
    public Name(String lastName, String firstName){
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public String getLastName(){
        return StringUtils.defaultIfEmpty(lastName, "");
    }

    public String getFirstName(){
        return StringUtils.defaultIfEmpty(firstName, "");
    }

    /*
    These SET methods are important because otherwise, deserialization from json string doesn't work!
     */
    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    @Override
    public String toString(){
        return String.format("Name[lastName=%s; firstName=%s]",
                this.getLastName(), this.getFirstName());
    }
}
