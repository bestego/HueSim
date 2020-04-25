package nl.bestego.huesim.model;

import java.util.ArrayList;

public class LampLijst {

    ArrayList<Long> list = new ArrayList<>();

    public void add(Long id){
        if (! list.contains(id)){
            list.add(id);
            //list.sort(); //ToDo
        }
    }

    public String get(Long id){
        return String.format(list.toString());
    }

    public boolean contains(Long id){
        return list.contains(id);
    }
}
