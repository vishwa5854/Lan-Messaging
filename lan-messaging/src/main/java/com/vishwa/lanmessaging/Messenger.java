package com.vishwa.lanmessaging;

import java.io.*;
import java.util.Scanner;
import java.util.Set;

class Messenger {
    private Scanner in = new Scanner(System.in);

    String getUserName(Set<String> usersList) throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.print("Username:");
        String userName = in.next();
        userName += in.nextLine();
        if (!usersList.contains(userName)) {
            System.out.println("you are not recognised as a user in vishwa's domain");
            System.out.println("Try another username ");
            new UserData();
            userName = getUserName(usersList);

        }
        return userName;
    }

    boolean login(User user) throws Exception {

        System.out.print("password:");
        String password = in.next();

        boolean isPasswordValid = verifyPassword(password, user);

        if (!isPasswordValid) {
            System.out.print("Invalid password");
            String[] args = {};
            Main.main(args);
            return false;
        }

        FileHelper.setStatusToOnline(user.getUserName(), true);
        System.out.println("\t \t \t Welcome Mr." + user.getUserName() + "\t \t login successful");
        return true;
    }

    String searchFriend(Set<String> usersList, String currentUserName) {
        for (String userName : usersList) {
            System.out.println("\t \t \t" + userName);
        }
        System.out.print("With whom do u wanna chat:");

        Scanner in = new Scanner(System.in);
        String friendName = in.next();

        if (!usersList.contains(friendName)) {
            System.out.print("User does not exists ");
            System.out.println("Please choose from the listed ones");
            searchFriend(usersList,currentUserName);
        }
        if (currentUserName.compareTo(friendName) == 0) {
            System.out.print("Chatting with yourself is not supported");
            return null;
        }
        return friendName;
    }

    void chat(String loggedInUserName, String friendName) {
        final int OPTION_COMPOSE = 1;
        final int OPTION_VIEW_INBOX = 2;
        final int OPTION_LOGOUT = 3;
        final int OPTION_CHECK_IF_ONLINE = 4;
        final int OPTION_CHANGE_USER_DETAILS = 5;

        try {
            while (true) {
                int choice = getChoice();
                switch (choice) {
                    case OPTION_COMPOSE:
                        sendMessage(loggedInUserName, friendName);
                        break;
                    case OPTION_VIEW_INBOX:
                        FileHelper.viewInbox(loggedInUserName, friendName);
                        break;
                    case OPTION_LOGOUT:
                        FileHelper.setStatusToOnline(loggedInUserName, false);
                        String[] args = {};
                        Main.main(args);
                        break;
                    case OPTION_CHECK_IF_ONLINE:
                        FileHelper.checkIfOnline(friendName);
                        break;
                    case OPTION_CHANGE_USER_DETAILS:
                        UserData userData = new UserData();
                        userData.modifyUserData(loggedInUserName);
                        case '\n':
                            break;
                    default:
                        System.out.println("INVALID CHOICE");
                }
            }
        } catch (Exception ignored) {

        }
    }

    private void sendMessage(String loggedInUserName, String friendName) throws IOException {
        System.out.print("Enter your message:");
        String message = in.next();
        message += in.nextLine();
        message += "\n";
        FileHelper.createAChatFileIfNotExists(loggedInUserName, friendName);
        FileHelper.writeMessage(loggedInUserName, friendName, message);
    }

    private int getChoice() {
        System.out.println("\t \t \t VISHWA's MAIL");
        System.out.println("\t \t1.Compose    2.Inbox     3.Logout    4.Check if Online     5.ModifyYourData");
        System.out.print("choice:");
        return in.nextInt();
    }


    private boolean verifyPassword(String password, User user) {
        return user.getPassword().compareTo(password) == 0;
    }

}
