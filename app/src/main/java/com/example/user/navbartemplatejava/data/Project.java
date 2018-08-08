package com.example.user.navbartemplatejava.data;

import java.io.Serializable;
import java.util.Date;

public class Project implements Serializable {
    Integer id;
    String project_code;
    String project_description;
    Integer is_close;
    Date created_at;
    Date updated_at;
    Date deleted_at;
}
