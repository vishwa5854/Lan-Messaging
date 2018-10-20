package com.vishwa.lanmessaging;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

class SourceCode {

    private final int OPTION_COMPOSE = 1;
    private final int OPTION_VIEW_INBOX = 2;
    private final int OPTION_LOGOUT = 3;
    private final int OPTION_CHECK_IF_ONLINE = 4;
    private final int OPTION_MODIFY_PASSWORD = 5;
    void code(UserData userData, int userIndex) throws Exception {
        printUsers(userData);
        try {
            System.out.print("With whom do u wanna chat:");

            Scanner in = new Scanner(System.in);
            String target = in.next();

            int targetIndex = userData.searchAndGetUserIndex(target);
            if (targetIndex < 15) {
                FilesInitialisation z = new FilesInitialisation();
                z.createAChatFileIfNotExists(userData, userIndex, targetIndex);
                z.createAChatFileIfNotExists(userData, targetIndex, userIndex);
                while (true) {
                    System.out.println("\t \t \t VISHWA's MAIL");
                    System.out.println("\t \t1.Compose 2.Inbox 3.Logout 4.Check if Online 5.Change password or username");
                    System.out.print("choice:");
                    int choice = in.nextInt();
                    switch (choice) {
                        case OPTION_COMPOSE:
                            System.out.print("Enter your message:");
                            String message = in.next();
                            message += in.nextLine();
                            message += "\n";
                            z.writeMessage(userData, userIndex, targetIndex, message);
                            break;
                        case OPTION_VIEW_INBOX:
                            viewInbox(userData, userIndex, targetIndex);
                            break;
                        case OPTION_LOGOUT:
                            Writer zx = new FileWriter("//SRIRAM/PROJECTMESSAGING/" + userData.users[userIndex].Username + "_check_if_online.txt");
                            zx.write("offline");
                            zx.close();
                            System.exit(0);
                            break;
                        case OPTION_CHECK_IF_ONLINE:
                            System.out.println("checking...");
                            z.checkIfOnline(target);
                            break;
                        case OPTION_MODIFY_PASSWORD:
                            userData.modifyUserData(userIndex);
                        default:
                            System.out.println("INVALID CHOICE");
                    }
                }
            } else {
                System.out.println("No such user exists please try again");
            }
        }
        catch (FileNotFoundException l) {
            System.out.println("you are not connected to the same LAN");
        } catch (ArrayIndexOutOfBoundsException K) {
            System.out.println("You are not allowed to chat in this chat box");
        } catch (InputMismatchException I) {
            System.out.println("Invalid choice");
            this.code(userData, userIndex);
        }
    }

    private void viewInbox(UserData userData, int currentUserIndex, int targetIndex) throws IOException {
        FileReader fileReader = new FileReader("//SRIRAM/PROJECTMESSAGING/" + userData.users[targetIndex].Username + " to " + userData.users[currentUserIndex].Username + ".txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String message = bufferedReader.readLine();

        FilesInitialisation z = new FilesInitialisation();
        String decryptedMessage = z.encryptOrDecryptMessage(message, false);

        System.out.println(userData.users[currentUserIndex].Username + ":" + decryptedMessage);

        fileReader.close();
        bufferedReader.close();
    }

    private void printUsers(UserData userData) {
        for (int z = 0; z < 15; z++) {
            System.out.println("\t \t \t" + userData.users[z].Username);
        }
    }
}
