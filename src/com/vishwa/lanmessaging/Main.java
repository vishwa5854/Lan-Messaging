package com.vishwa.lanmessaging;

public class Main {
    public static void main(String[] args) throws Exception {
        UserData userData = new UserData();

        LoginCheck loginCheck = new LoginCheck();
        int userIndex = loginCheck.login(userData.users);

        if (userIndex == -1) {
            System.out.print("Invalid Username or password");
        } else {
            SourceCode s = new SourceCode();
            s.code(userData, userIndex);
        }
    }
}

