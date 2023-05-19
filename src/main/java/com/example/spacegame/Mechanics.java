package com.example.spacegame;

public class Mechanics {
    //For collisions , out of bounds, spawning waves

    public static void moveX(BaseEntity entity, int position, int displacement){
        position = position + displacement;
        entity.setPosX(position);
    }
}
