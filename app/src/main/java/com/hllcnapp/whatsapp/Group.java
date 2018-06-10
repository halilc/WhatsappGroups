package com.hllcnapp.whatsapp;

public class Group {

    private String groupName;
    private String groupUrl;
    private String type;

    public Group(String name, String url, String type){

        groupName = name;
        groupUrl = url;
        this.type = type;

    }

    public String getGroupName() {
        return groupName;
    }

    public String getGroupUrl() {
        return groupUrl;
    }

    public String getType() {
        return type;
    }
}
