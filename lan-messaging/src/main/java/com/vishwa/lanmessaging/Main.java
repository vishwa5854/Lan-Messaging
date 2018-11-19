package com.vishwa.lanmessaging;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    private UserData userData = new UserData();
    private HashMap<String, User> users = userData.getUsers();

    private Main() throws IOException {
    }

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        System.out.println("1.Sign In      2.SignUp");
        System.out.print("Enter your choice:");
        Scanner in = new Scanner(System.in);
        int choice = in.nextInt();
        if(choice ==1) {
            Messenger messenger = new Messenger();

            String loggedUserName = messenger.getUserName(main.users.keySet());
            if (loggedUserName == null) {
                return;
            }
            messenger.login(main.users.get(loggedUserName));
            String friendName = messenger.searchFriend(main.users.keySet(), loggedUserName);
            if (friendName == null) return;
            messenger.chat(loggedUserName, friendName);
        }
        else main.signUp();
    }
    private void signUp() throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.print("Choose a UserName:");
        String UserName = in.next();
        UserName += in.nextLine();
        if(users.containsKey(UserName)){
            System.out.println("UserName already taken try another");
            signUp();
        }
        System.out.print("Password:");
        String Password = in.next();
        users.put(UserName,new User(UserName,Password));
        userData.writeUserDataIntoFile();
        FileHelper fileHelper = new FileHelper();
        fileHelper.createNewStatusFileAfter(UserName);
        String ar[] = {};
        main(ar);
    }


}

