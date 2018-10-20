package com.vishwa.lanmessaging;
import java.io.*;
import java.util.Scanner;

class UserData {
    private Scanner in = new Scanner(System.in);
    User users[] = new User[15];
        private FileReader fileReader = new FileReader("//SRIRAM/PROJECTMESSAGING/UserData.txt");
        private BufferedReader bufferedReader = new BufferedReader(fileReader);
    UserData() throws Exception {
        for(int i=0;i<15;i++){
            users[i] = new User();
        }
    }
    void setUsers() throws IOException {
        for(int k=0;k<15;k++) {
            String line = bufferedReader.readLine();
            int commaIndex = commaIdentifier(line);
            for (int i = 0; i < commaIndex; i++) {
                if(i==0){
                    users[k].Username = Character.toString(line.charAt(i));
                }
                else {
                    users[k].Username = users[k].Username + line.charAt(i);
                }
            }
            for (int l = commaIndex + 1; l < line.length(); l++) {
                if(l==commaIndex +1){
                    users[k].Password = Character.toString(line.charAt(l));
                }
                else {
                    users[k].Password = users[k].Password + line.charAt(l);
                }
            }
        }
        printUserData();
    }


    private int commaIdentifier(String line){
        int i;
        for(i=0;i<line.length();i++){
            if(line.charAt(i) == ','){
                break;
            }
        }
        return i;
    }
    private int countFseekValue(int index,boolean uop){
        int seekValue=0 ;
        if(uop) {
            for (int i = 0; i < index; i++) {
                seekValue = seekValue + users[i].Username.length() + users[i].Password.length() + 1;
            }
        }
        else{
            for (int i = 0; i < index; i++) {
                seekValue = seekValue + users[i].Username.length() + users[i].Password.length() + 1;
            }
            seekValue += users[index].Username.length()+1;
        }
        return seekValue;
    }
     void modifyUserData(int index) throws Exception{
        System.out.println("Choice 1 for modifying your Username Choice 2 for modifying password");
        System.out.print("choice:");
        int choice = in.nextInt();
        RandomAccessFile file = new RandomAccessFile("//SRIRAM/PROJECTMESSAGING/UserData.txt","rw");
        if(choice == 1){
            System.out.print("Enter the new Username:");
            users[index].Username = in.next();
            file.seek(countFseekValue(index,true));
            file.writeUTF(new StringBuilder().append("\n").append(users[index].Username).toString());
        }
        else {
            System.out.print("Enter new password:");
            users[index].Password = in.next();
            file.seek(countFseekValue(index,false));
            file.writeUTF(new StringBuilder().append("\n").append(users[index].Password).toString());
        }
    }

    private void printUserData(){
        for(int i=0;i<15;i++){
            System.out.println(users[i].Username);
            System.out.println(users[i].Password);
        }
    }



    int searchAndGetUserIndex(String uname) {
        int i;
        for (i = 0; i < 15; i++) {
            if (uname.compareTo(users[i].Username) == 0) {
                break;
            }
        }
        return i;
    }
}

class User {
    String Username;
    String Password;
    User(){
    }
}
