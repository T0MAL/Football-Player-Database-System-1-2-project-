package Server;

import java.util.Scanner;

public class serverExitMethod implements Runnable{
    volatile boolean t;
    serverExitMethod(boolean t){
        this.t=t;
        new Thread(this).start();
    }
    @Override
    public void run() {
        System.out.println("write false to exit the server perfectly");
        Scanner scn=new Scanner(System.in);
        t=scn.nextBoolean();
        System.out.println("gg");
    }
}
