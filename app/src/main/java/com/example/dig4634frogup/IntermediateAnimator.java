package com.example.dig4634frogup;

public class IntermediateAnimator extends Thread {

    IntermediateActivity surfaceActivity;
    boolean is_running=false;

    public IntermediateAnimator(IntermediateActivity activity){
        surfaceActivity=activity;
    }

    public void run(){
        is_running=true;

        while(is_running){

            surfaceActivity.draw();

            try {
                sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }

    public void finish(){
        is_running=false;
    }

}
