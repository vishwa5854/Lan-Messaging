package com.vishwa.lanmessaging;
import java.awt.*;

public class Display {
    void askToLoginOrSignUp(){
        Frame frame = new Frame();
        frame.setVisible(true);
        frame.setSize(500,500);
        frame.setBackground(Color.DARK_GRAY);
        Button b1 , b2 ;
        TextField t1,t2;
        Label l1,l2;
        l1 = new Label("UserName ->");
        l1.setBounds(200,50,100,50);
        frame.add(l1);
        t1 = new TextField();
        t1.setBounds(310,50,100,50);
        frame.add(t1);
        l2 = new Label("PassWord ->");
        l2.setBounds(200,150,100,50);
        frame.add(l2);
        t2 = new TextField();
        t2.setBounds(310,150,100,50);
        frame.add(t2);
        b1 = new Button("SIGN IN");
        b1.setBounds(250,250,100,70);
        frame.add(b1);
        b2 = new Button("SIGN UP");
        b2.setBounds(350,250,100,70);
        frame.add(b2);
    }
}
