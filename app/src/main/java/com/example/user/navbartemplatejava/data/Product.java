package com.example.user.navbartemplatejava.data;

import java.io.Serializable;
import java.util.Date;

public class Product implements Serializable {
    Integer id;
    String product_description;
    Date created_at;
    Date updated_at;
    Date deleted_at;
}
