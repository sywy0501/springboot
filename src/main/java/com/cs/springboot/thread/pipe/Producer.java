package com.cs.springboot.thread.pipe;

import java.io.PipedOutputStream;

/**
 * @author: cs
 * @date: 2019/08/23 18:01
 * @desc:
 */
class Producer extends Thread{
    private PipedOutputStream pos;

    public Producer(PipedOutputStream pos){
        this.pos = pos;
    }

    public void run(){
        int i = 0;
        try {
            while (true){
                this.sleep(1000);
                pos.write(i);
                i++;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}


