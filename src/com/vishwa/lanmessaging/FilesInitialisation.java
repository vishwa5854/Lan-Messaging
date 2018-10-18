package com.vishwa.lanmessaging;

import java.io.*;

class FilesInitialisation {

    void createAChatFileIfNotExists(UserData userData, int index, int targetIndex) throws Exception {
        File file = new File("//SRIRAM/PROJECTMESSAGING/" + userData.users[index].Username + " to " + userData.users[targetIndex].Username + ".txt");
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    void writeMessage(UserData userData, int userIndex, int targetIndex, String message) throws Exception {
        Writer f = new FileWriter("//SRIRAM/PROJECTMESSAGING/" + userData.users[userIndex].Username + " to " + userData.users[targetIndex].Username + ".txt", false);
        Writer logFileWriter = new FileWriter("//SRIRAM/PROJECTMESSAGING/" + "LOG" + userData.users[userIndex].Username + " to " + userData.users[targetIndex].Username + ".txt", true);

        String encryptedMessage = encryptOrDecryptMessage(message, true);
        f.write(encryptedMessage);
        logFileWriter.write(encryptedMessage);
        f.close();
        logFileWriter.close();
    }

    void checkIfOnline(String Username) throws Exception {
        String cio = "";
        String onlineMessage = "online";

        String targetUsersStatusFilePath = "//SRIRAM/PROJECTMESSAGING/" + Username + "_check_if_online.txt";
        try {
            FileInputStream fileInputStream = new FileInputStream(targetUsersStatusFilePath);
            int i;

            while ((i = fileInputStream.read()) != -1) {
                cio = cio + (char) i;
            }
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            File nap1 = new File(targetUsersStatusFilePath);
            nap1.createNewFile();
        }

        if (onlineMessage.compareTo(cio) == 0) {
            System.out.println(Username + "is online");
        } else {
            System.out.println("User is offline but u can send messages to him");
        }

        System.out.println(cio);
    }

    String encryptOrDecryptMessage(String message, boolean encryption) {
        char a[] = message.toCharArray();
        for (int i = 0; i < message.length(); i++) {
            if (a[i] != ' ') {
                if (encryption) {
                    a[i] = (char) (a[i] + 3);
                }
                else {
                    a[i] = (char) (a[i] - 3);
                }
            }
        }
        return new String(a);
    }
}

