package com.vishwa.lanmessaging;

import java.io.*;

class FilesIntialisation extends Main{
    void Intialise(int index, int indexu)throws Exception {
        try {

            FileInputStream lol = new FileInputStream("//SRIRAM/PROJECTMESSAGING/" + U[index].Username + " to " + U[indexu].Username + ".txt");
        } catch (FileNotFoundException e) {
            FileOutputStream f = new FileOutputStream("//SRIRAM/PROJECTMESSAGING/" + U[index].Username + " to " + U[indexu].Username + ".txt");
            f.close();
        }
    }
    void WriteMsg(int indexu)throws Exception{
        Writer f = new FileWriter("//SRIRAM/PROJECTMESSAGING/" + U[Qwerty.index].Username + " to " + U[indexu].Username +".txt",false);
        Writer x = new FileWriter("//SRIRAM/PROJECTMESSAGING/" + "LOG" + U[Qwerty.index].Username + " to " + U[indexu].Username +".txt",true);
        System.out.print("Enter your message:");
        String mes = in.next();
        mes += in.nextLine();
        mes += "\n";
        String emes = this.EncryptMessages(mes);
        f.write(emes);
        x.write(emes);
        f.close();
        x.close();
    }
    void checkIfOnline(String Username)throws Exception{
        String cio = "" ;
        String s = "online";

        try {
            FileInputStream  nap = new FileInputStream("//SRIRAM/PROJECTMESSAGING/" + Username + "_check_if_online.txt");
            int i;

            while((i=nap.read())!=-1) {
                cio = cio + (char)i;
            }
            nap.close();
            System.out.println(cio);
            if(s.compareTo(cio)==0){
                    System.out.println(Username+"is online");
                }
                else{
                    System.out.println("User is offline but u can send messages to him");
                }

        } catch (FileNotFoundException e) {
            FileOutputStream nap1 = new FileOutputStream("//SRIRAM/PROJECTMESSAGING/" + Username + "_check_if_online.txt");
            nap1.close();
            checkIfOnline(Username);
        }
        System.out.println(cio);

    }
   private String EncryptMessages(String mes){
        char a[] = mes.toCharArray();
        char c[] = new char[mes.length()];
        int b[] = new int[mes.length()];
        for(int i=0;i<mes.length();i++){
            if(a[i] == ' '){
                b[i] = ' ';
            }
            else{
                b[i] = (int)a[i]+3;
                c[i] = (char)b[i];
            }
        }
    String emes = new String(c);
        return emes;
    }
    String  DecryptMessages(String mes){
        char a[] = mes.toCharArray();
        int b[] = new int[mes.length()];
        char c[] = new char[mes.length()];
        for(int i=0;i<mes.length();i++){
            if(a[i] == ' '){
                c[i] = ' ';
            }
            else {
                b[i] = (int) a[i];
                b[i] = b[i] - 3;
                c[i] = (char) b[i];
            }
        }
        String dmes = new String(c);
        return dmes;
    }

}

