package com.vishwa.lanmessaging;

class UserData{
    UP U[] = new UP[15];
    UserData(){
        for(int i=0;i<15;i++){
            U[i] = new UP();
        }
        U[0] = new UP("ravi","ravi123");
        U[1] = new UP("vishwa","vishwa123");
        U[2] = new UP("shashi","shashi123");
        U[3] = new UP("riteesh","riteesh123");
        U[4] = new UP("teja","teja123");
        U[5] = new UP("vamsi","vamsi123");
        U[6] = new UP("chaitanya","chaitanya123");
        U[7] = new UP("madhu","madhu123");
        U[8] = new UP("phani","phani123");
        U[9] = new UP("sriram","sriram123");
        U[10] = new UP("dheeraj","dheeraj123");
        U[11] = new UP("riyaz","riyaz123");
        U[12] = new UP("sai","sai123");
        U[13] = new UP("gopi","gopi123");
        U[14] = new UP("rakesh","rakesh123");

    }

}
class UP{
    String Username;
    String Password;
    UP(){
    }
    UP(String uname , String pwd){
        Username = uname;
        Password = pwd;
    }
}
class Qwerty{
    int z;
   static int index;
}
