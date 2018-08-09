package com.example.user.navbartemplatejava.data;

import java.io.Serializable;

public class IncompatibilityCategory implements Serializable{
    private Integer id;
    private String description;

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
