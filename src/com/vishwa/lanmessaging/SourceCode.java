package com.vishwa.lanmessaging;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.Writer;
import java.util.InputMismatchException;

class SourceCode extends Main {

    void Code() throws Exception {
        int r;
        for (int z = 0; z < 15; z++) {
            System.out.println("\t \t \t" + U[z].Username);
        }
            System.out.print("With whom do u wanna chat:");
            String target = in.next();
            LoginCheck l = new LoginCheck();
            int indexu = l.Search(U, target);
        if (indexu < 15) {
            FilesIntialisation z = new FilesIntialisation();
            z.Intialise(Qwerty.index, indexu);
            z.Intialise(indexu, Qwerty.index);
            try {
                while (true) {
                    System.out.println("\t \t \t VISHWA's MAIL");
                    System.out.println("\t \t1.Compose 2.Inbox 3.Logout 4.Check if Online");
                    System.out.print("choice:");
                    int choice = in.nextInt();
                    switch (choice) {
                        case 1:
                            z.WriteMsg(indexu);
                            break;
                        case 2:
                            char alpha[] = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
                            char tbmsg[] = new char[30];
                            FileInputStream  e = new FileInputStream("//SRIRAM/PROJECTMESSAGING/" + U[indexu].Username + " to " + U[Qwerty.index].Username +".txt");
                            System.out.print(U[indexu].Username + ":");
                            int i=0;
                            while ((r = e.read()) != -1) {
                                tbmsg[i] = (char) r;
                                i++;
                            }
                            String msg = new String(tbmsg);
                            String dmsg = z.DecryptMessages(msg);
                            System.out.print(dmsg);
                            System.out.print("\n");
                            e.close();
                            break;
                        case 3:
                            Writer zx = new FileWriter("//SRIRAM/PROJECTMESSAGING/" + U[Qwerty.index].Username + "_check_if_online.txt");
                            zx.write("offline");
                            zx.close();
                            System.exit(0);
                            break;
                        case 4:
                            System.out.println("checking...");
                            z.checkIfOnline(target);
                            break;
                        default:
                            System.out.println("INVALID CHOICE");
                    }
                }
            }
            catch (FileNotFoundException L) {
                System.out.println("you are not connected to the same LAN");
            } catch (ArrayIndexOutOfBoundsException K) {
                System.out.println("You are not allowed to chat in this chat box");
            }
            catch (InputMismatchException I) {
                System.out.println("Invalid choice");
                this.Code();
            }
        }
        else {
            System.out.println("No such user exists please try again");
            System.exit(0);
        }
    }
}
