package com.vishwa.lanmessaging;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

class UserData {
    private Scanner in = new Scanner(System.in);
    private HashMap<String, User> _users;

    UserData() throws IOException {
        initialise();
    }

    private void initialise() throws IOException {
        _users = new HashMap<>();
        FileReader fileReader = new FileReader("//SRIRAM/PROJECTMESSAGING/UserData.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        for(int k=0;k<15;k++) {
            String line = bufferedReader.readLine();
            String userData[] = line.split(",");
            _users.put(userData[0],new User(userData[0],userData[1]));
        }
    }
    private void printUserData(){
        for(String key : _users.keySet()){
            System.out.println(key);
        }
    }



    HashMap<String, User> getUsers() {
        return _users;
    }


    void modifyUserData(String loggedInUsername)throws Exception{
        FileWriter fileWriter = new FileWriter("//SRIRAM/PROJECTMESSAGING/UserData.txt");
        BufferedWriter writer = new BufferedWriter(fileWriter);
        System.out.println("What do u want to chane??");
        System.out.println("1.Username \t \t 2.Password");
        int choice = in.nextInt();
        User currentUser = _users.get(loggedInUsername);
        Messenger messenger = new Messenger();
        if(choice == 1){
            System.out.print("To verify its you please enter your");
            boolean isLogin = messenger.login(currentUser);
            if(isLogin){
                System.out.print("Enter your new username:");
                String newUsername = in.next();
                _users.remove(loggedInUsername);
                _users.put(newUsername,new User(newUsername,currentUser.getPassword()));
                for(User key : _users.values()){
                    writer.write(key.getUserName() + "," + key.getPassword());
                    writer.newLine();
                }
            }
            else{
                System.out.println("You are not recognised as " + currentUser +"please login again and try later");
                System.exit(0);
            }
        }
        else{
            System.out.print("To verify its you please enter your");
            boolean isLogin = messenger.login(currentUser);
            if(isLogin){
                System.out.print("Enter your new password:");
                String newPassword = in.next();
                _users.remove(loggedInUsername);
                _users.put(loggedInUsername,new User(loggedInUsername,newPassword));
                for(User key : _users.values()){
                    writer.write(key.getUserName() + "," + key.getPassword());
                    writer.newLine();
                }
            }
            else{
                System.out.println("You are not recognised as " + currentUser +"please login again and try later");
                System.exit(0);
            }
        }
        writer.close();
    }
}

class User {
    private String _userName;
    private String _password;

    User(String userName, String pwd) {
        _userName = userName;
        _password = pwd;
    }

    String getPassword() {
        return _password;
    }

    String getUserName() {
        return _userName;
    }


}
