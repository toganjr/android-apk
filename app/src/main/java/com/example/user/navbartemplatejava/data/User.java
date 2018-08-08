package com.example.user.navbartemplatejava.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable{
    Integer id;
    String name;
    String nip;
    String email;
    String photo;
    Integer jabatan_id;
    Integer divisi_id;
    Date created_at;
    Date updated_at;
}
