package com.cs.springboot.thread.pipe;

import java.io.IOException;
import java.io.PipedInputStream;

/**
 * @author: cs
 * @date: 2019/08/23 18:00
 * @desc:
 */
class Consumer1 extends Thread{
    private PipedInputStream pis;
    public Consumer1(PipedInputStream pis){
        this.pis = pis;
    }

    public void run(){
        try {
            while (true){
                System.out.println("consumer1: "+pis.read());
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
