package com.example.myapplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;


public class MsgController {
    public interface Subscriber {
        void noMsgCome(Object content);
    }
    HashMap<UUID, ArrayList<Subscriber>> tasks = new HashMap<>();
    public void trigger(UUID msgId, Object content){
        ArrayList<Subscriber> ss = tasks.get(msgId);
        if(ss != null){
            for(Subscriber s: ss)
                s.noMsgCome(content);
        }
    }
    public void add(UUID msgId, Subscriber subscriber){
        ArrayList<Subscriber> ss = tasks.get(msgId);
        if(ss == null){
            ss = new ArrayList<>();
            tasks.put(msgId, ss);
        }
        ss.add(subscriber);
    }
    public boolean remove(UUID msgId, Subscriber subscriber){
        ArrayList<Subscriber> ss = tasks.get(msgId);
        if(ss != null){
            return ss.remove(subscriber);
        }
        return false;
    }
}
