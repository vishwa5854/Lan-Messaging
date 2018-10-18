package com.vishwa.lanmessaging;
import java.io.FileWriter;
import java.io.Writer;
import java.util.Scanner;

class LoginCheck{
   private Scanner in = new Scanner(System.in);
    Qwerty Login(UP U[])throws Exception{
        System.out.print("Username:");
        String uname = in.next();
        int index = this.Search(U,uname);

        int z=0 ;
        if(index < 15){
            System.out.print("password:");
            String pwd = in.next();
            int v = this.Check(pwd,U,index);
            if(v == 1){
                Writer w = new FileWriter("//SRIRAM/PROJECTMESSAGING/" + uname + "_check_if_online.txt");
                w.write("online");
                w.flush();
                w.close();
                System.out.println("\t \t \t Welcome Mr."+U[index].Username+"\t \t Login Succesful");
                z = 1;
            }
            else{
                z = 0;
            }
        }
        else{
            System.out.println("you are not recognised as a user in vishwa's domain");
        }
        Qwerty qw = new Qwerty();
        qw.z = z;
        Qwerty.index = index;
        return qw;

    }
    int Search(UP U[],String uname){
        int i;
        for(i=0;i<15;i++){
            if(uname.compareTo(U[i].Username) == 0){
                break;
            }

        }
        return i;
    }

    private int Check(String pwd,UP U[],int index){
        if(U[index].Password.compareTo(pwd) == 0){
            return 1;
        }
        else{
            return 0;
        }
    }

}