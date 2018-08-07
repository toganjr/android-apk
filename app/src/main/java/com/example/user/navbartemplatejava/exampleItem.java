package com.example.user.navbartemplatejava;

public class exampleItem {
    private String mText1;
    private String mText2;

    public exampleItem(String text1,String text2){
        mText1 = text1;
        mText2 = text2;
    }

    public void gonext (String text){
        mText1 = text;
    }

    public void gotonext(String text){
        mText1 = text;
    }

    public String getText1(){
        return mText1;
    }

    public String getText2(){
        return mText2;
    }
}
