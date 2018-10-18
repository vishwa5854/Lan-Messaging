package com.vishwa.lanmessaging;
import java.util.Scanner;

public class Main extends UserData {
    private Qwerty q = new Qwerty();
    private LoginCheck l = new LoginCheck();
    Scanner in = new Scanner(System.in);
    public static void main(String[] args)throws Exception {
        Main M = new Main();
        UserData f = new UserData();
        M.q = M.l.Login(f.U);
        if(M.q.z == 0 ){
            System.out.print("Invalid Username or password");
        }
        else {

                SourceCode s = new SourceCode();
                s.Code();

        }
    }
}
