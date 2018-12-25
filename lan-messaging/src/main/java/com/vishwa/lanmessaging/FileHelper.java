package com.vishwa.lanmessaging;
import java.io.*;

class FileHelper {
    private static String _directoryPath = "//predator/MessagingFiles/";
    private static String _fileNamePattern = _directoryPath + "%s to %s.txt";
   // private static String _logFileNamePattern = _directoryPath + "%s to %s.txt";
    private static String _statusFileNamePattern = _directoryPath + "%s_check_if_online.txt";

    static void createAChatFileIfNotExists(String user1, String user2) throws IOException {
        File file = new File(String.format(_fileNamePattern, user1, user2));
        try {
            file.createNewFile();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to create a file to store messages from " + user1 + "to " + user2+ "\n" + "as you are not connected to the same LAN");
        }
    }

    static void writeMessage(String mySelf, String friend, String message){
        try {
            Writer  f = new FileWriter(String.format(_fileNamePattern, mySelf, friend), true);
           // Writer writer = new FileWriter(String.format(_fileNamePattern,mySelf,friend + "status count"),false);
            String encryptedMessage = encryptOrDecryptMessage(message, true);
            f.write(encryptedMessage);
            f.close();
        } catch (IOException e) {
            System.out.println("Unable to write messages from " + mySelf + "to " + friend);
            System.out.println("You are not connected to the same LAN");
        }

    }

    static void checkIfOnline(String friend){
        String stringBuilder = "";
        String onlineMessage = "online";
        String friendStatusFilePath = String.format(_statusFileNamePattern, friend);
        System.out.println("checking...");
        try {
            FileReader fileReader = new FileReader(friendStatusFilePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            stringBuilder = bufferedReader.readLine();
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            File nap1 = new File(friendStatusFilePath);
            try {
                nap1.createNewFile();
            } catch (IOException e1) {
                System.out.println("Unable to read friend (" + friend + ") online status");
            }
        } catch (IOException e) {
            System.out.println("Unable to read friend (" + friend + ") online status");
        }
        catch (Exception e){
            System.out.println("lol");
        }

        if (onlineMessage.compareTo(stringBuilder) == 0) {
            System.out.println(friend + "is online");
        } else {
            System.out.println("User is offline but u can send messages to him");
        }
    }

    static void viewInbox(String loggedUserName, String friend) throws IOException {
        try {
            FileReader fileReader = new FileReader(String.format(_fileNamePattern, friend, loggedUserName));
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String message = bufferedReader.readLine();

            String decryptedMessage = encryptOrDecryptMessage(message, false);

            System.out.println(friend + ":" + decryptedMessage);

            fileReader.close();
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("There are no messages in inbox from " + friend);
            createAChatFileIfNotExists(loggedUserName, friend);
        }
    }

    static void setStatusToOnline(String userName, boolean online) {
        String statusMessage = online ? "online" : "offline";
        try {
            Writer w = new FileWriter(String.format(_statusFileNamePattern, userName));
            w.write(statusMessage);
            w.flush();
            w.close();
        } catch (IOException e) {
            System.out.println("Failed to change your status to online");
        }
    }

    private static String encryptOrDecryptMessage(String message, boolean encryption) {
        char a[] = message.toCharArray();
        for (int i = 0; i < message.length(); i++) {
            if (a[i] != ' ') {
                if (encryption) {
                    a[i] = (char) (a[i] + 3);
                } else {
                    a[i] = (char) (a[i] - 3);
                }
            }
        }
        return new String(a);
    }
    void createNewStatusFileAfter(String userName) throws IOException {
        Writer writer = new FileWriter("//predator/MessagingFiles/"+ userName +"_check_if_online.txt");
        writer.write("offline");
        writer.close();

    }

    void setSecurityQuestion(String securityQuestion,String userName) throws IOException {

        Writer  writer  = new FileWriter("//predator/MessagingFiles/" + userName + "securityQuestionFile.txt");
        try {
            writer.write(securityQuestion);
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Couldn't write data into security question file");
        }
        writer.close();
    }
}