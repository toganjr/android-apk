package com.example.user.navbartemplatejava.data;

import java.io.Serializable;
import java.util.Date;

public class Division implements Serializable {
    Integer id;
    String division_name;
    Integer parent;
    Date created_at;
    Date updated_at;
    Date deleted_at;
    String kadiv_nip;
}
