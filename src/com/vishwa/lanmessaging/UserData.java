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
    private int countNumberOfLinesInFiles() throws IOException {
        FileReader fileReader = new FileReader("//SRIRAM/PROJECTMESSAGING/UserData.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        int i=0;
        while(bufferedReader.readLine()!=null){
            i++;
        }
       return i;
    }
    private void initialise() throws IOException {
        _users = new HashMap<>();
        FileReader fileReader = new FileReader("//SRIRAM/PROJECTMESSAGING/UserData.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        for(int k=0;k<countNumberOfLinesInFiles();k++) {
            String line = bufferedReader.readLine();
            String userData[] = line.split(",");
            _users.put(userData[0],new User(userData[0],userData[1]));
        }
    }

    HashMap<String, User> getUsers() { return _users; }

    void modifyUserData(String loggedInUsername) throws IOException {
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
                renameFile(loggedInUsername,newUsername);
                _users.put(newUsername,new User(newUsername,currentUser.getPassword()));
                writeUserDataIntoFile();
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
            }
            else{
                System.out.println("You are not recognised as " + currentUser +"please login again and try later");
                System.exit(0);
            }
        }
    }

    private void renameFile(String loggedInUsername , String newUsername) {
        File file= new File("//SRIRAM/PROJECTMESSAGING/" + loggedInUsername + "_check_if_online.txt");
        File newFile = new File("//SRIRAM/PROJECTMESSAGING/" + newUsername + "_check_if_online.txt");
        if(file.renameTo(newFile)){
            System.out.println("Username succesfullychanged");
        }
    }

    void writeUserDataIntoFile() throws IOException {
        FileWriter fileWriter = new FileWriter("//SRIRAM/PROJECTMESSAGING/UserData.txt");
        BufferedWriter writer = new BufferedWriter(fileWriter);
        for(User key : _users.values()){
            writer.write(key.getUserName() + "," + key.getPassword());
            writer.newLine();
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

    String getPassword() { return _password; }

    String getUserName() {
        return _userName;
    }


}
