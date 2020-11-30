package com.example.dig4634frogup;

public class ProAnimator extends Thread {

    ProActivity surfaceActivity;
    boolean is_running=false;

    public ProAnimator(ProActivity activity){
        surfaceActivity=activity;
    }

    public void run(){
        is_running=true;

        while(is_running){

            surfaceActivity.draw();

            try {
                sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }

    public void finish(){
        is_running=false;
    }

}
