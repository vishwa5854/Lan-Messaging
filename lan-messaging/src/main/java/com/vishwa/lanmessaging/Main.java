package com.vishwa.lanmessaging;
import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    private UserData userData = new UserData();
    private HashMap<String, User> users = userData.getUsers();
    Scanner in = new Scanner(System.in);

    private Main() throws IOException {
    }

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        System.out.println("1.Sign In      2.SignUp     3.Exit     4.ForgotPassword");
        System.out.print("Enter your choice:");
        int choice = main.in.nextInt();
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
        else if(choice == 2){ main.signUp(); }
        else if(choice ==3){ System.exit(0); }
        else if(choice == 4){ main.forgotPassword();}
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
        String[] ar = {};
        System.out.print("For security please set a security question:");
        String securityQuestion = this.in.next();
        securityQuestion += in.nextLine();
        System.out.print("Answer:");
        String Answer = in.next();
        Answer += in.nextLine();
        securityQuestion += "," + Answer;
        fileHelper.setSecurityQuestion(securityQuestion,UserName);
        main(ar);
    }
    private void forgotPassword() throws IOException {
        System.out.print("Enter your Username:");
        String Username = in.next();
        System.out.println("To verify its you please fill the answer to the security question");
        FileReader fileReader = new FileReader("//predator/MessagingFiles/" + Username + "securityQuestionFile.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = bufferedReader.readLine();
        String[] qanda = line.split(",");
        System.out.println(qanda[0]);
        System.out.print("Answer:");
        String answer = in.next();
        if((answer.compareTo(qanda[1])) == 0){
            System.out.println("We could conform that its you" + "\n" +"Choose new password");
            System.out.print("NewPassword:");
            String NewPassword = in.next();
            users.remove(Username);
            users.put(Username,new User(Username,NewPassword));
            userData.writeUserDataIntoFile();
        }
        bufferedReader.close();
        fileReader.close();
        System.out.println("Please sign-in again");
        String[] lol = new String[1];
        try {
            main(lol);
        } catch (Exception e) {
            System.out.println("Unexpected Error Please restart the  application");
        }
    }


}

