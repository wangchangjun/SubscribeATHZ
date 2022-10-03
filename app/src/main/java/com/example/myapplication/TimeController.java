package com.example.myapplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;


public class TimeController {

    private static class Timely{
        ArrayList<Subscriber> tasks;
        Timer timer;
        public Timely(Subscriber subscriber, int ms){
            this.tasks = new ArrayList<> ();
            tasks.add(subscriber);
            this.timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    for(Subscriber s: tasks)
                        s.noMsgCome();
                }
            }, ms, ms);
        }
        void add(Subscriber subscriber){
            tasks.add(subscriber);
        }
        boolean remove(Subscriber subscriber){
            if( !tasks.remove(subscriber))
                return false;
            if(tasks.size() == 0)
                timer.cancel();

            return true;
        }
    }
    HashMap<Integer, Timely> scheduler = new HashMap<>(32);
    public void add(int ms, Subscriber subscriber){
        Timely ss = scheduler.get(ms);
        if( ss == null) {
            ss = new Timely(subscriber, ms);
            scheduler.put(ms, ss);
        }
        else{
            ss.add(subscriber);
        }
    }
    public boolean remove(int ms, Subscriber subscriber){
        Timely ss = scheduler.get(ms);
        if(ss != null){
            if( ss.remove(subscriber) ){
                if( ss.tasks.size() == 0){
                    scheduler.remove(ms);
                }
            }
        }
        return  false;
    }

}

