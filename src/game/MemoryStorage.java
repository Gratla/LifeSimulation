package game;

import datastructures.Vector2D;

import java.util.ArrayList;

public class MemoryStorage {
    private ArrayList<Memory> memories;

    public MemoryStorage(){
        memories = new ArrayList<>();
    }

    public void forgetOne(){
        if(!memories.isEmpty()){
            memories.remove((int)(Math.random() * memories.size()));
        }
    }

    public void forgetAll(){
        memories.clear();
    }

    public Memory getMemory(){
        if(!memories.isEmpty()){
            return memories.get((int)(Math.random() * memories.size()));
        }
        return null;
    }

    public void addMemory(Vector2D position, Creature creature){
        memories.add(new Memory(position, creature));
    }
}
