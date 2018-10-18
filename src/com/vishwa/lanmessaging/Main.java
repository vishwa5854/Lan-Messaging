package com.vishwa.lanmessaging;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws Exception {
        UserData userData = new UserData();

        HashMap<String, User> users = userData.getUsers();

        Messenger messenger = new Messenger();

        String loggedUserName = messenger.getUserName(users.keySet());
        if(loggedUserName == null){
            return;
        }
        boolean isLoginSuccess = messenger.login(users.get(loggedUserName));

        if (!isLoginSuccess) {
            return;
        }
        String friendName = messenger.searchFriend(users.keySet(), loggedUserName);
        if (friendName == null) {
            return;
        }

        messenger.chat(loggedUserName, friendName);
    }
}

