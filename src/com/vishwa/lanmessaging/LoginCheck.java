package com.vishwa.lanmessaging;

import java.io.FileWriter;
import java.io.Writer;
import java.util.Scanner;

class LoginCheck {
    private Scanner in = new Scanner(System.in);

    int login(User[] users) throws Exception {
        System.out.print("Username:");
        String userName = in.next();
        int index = this.Search(users, userName);

        if (index < 15) {
            System.out.print("password:");
            String pwd = in.next();
            boolean isPasswordValid = verifyPassword(pwd, users, index);
            if (isPasswordValid) {
                Writer w = new FileWriter("//SRIRAM/PROJECTMESSAGING/" + userName + "_check_if_online.txt");
                w.write("online");
                w.flush();
                w.close();
                System.out.println("\t \t \t Welcome Mr." + users[index].Username + "\t \t login successful");
            } else {
                index = -1;
            }
        } else {
            System.out.println("you are not recognised as a user in vishwa's domain");
        }
        return index;

    }

    int Search(User users[], String uname) {
        int i;
        for (i = 0; i < 15; i++) {
            if (uname.compareTo(users[i].Username) == 0) {
                break;
            }
        }
        return i;
    }

    private boolean verifyPassword(String password, User users[], int index) {
        return users[index].Password.compareTo(password) == 0;
    }

}