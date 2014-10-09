package com.maya.rules;

import com.maya.model.Room;
import com.maya.view.Renderer;
import com.maya.view.SwingRenderer;
import com.maya.model.MonkeyMap;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by pete on 10/6/14.
 */
public class MayaRules {

    private static MonkeyMap map = new MonkeyMap();
    public static int MAP_WIDTH = 5;
    public static int MAP_LENGTH = 5;


    public static void main(String[] args){
        // pick an entrance
        // as you land on each room,
        //      if there is one or more bananas, pick up the bananas, and update the amount of bananas you're carrying
        //      or, if there are monkeys and you have bananas to give, give them each a banana, and subtract the amount of bananas from your banana count
        //           if you don't have enough bananas, you don't have a solution to the problem!   Back up and look for a different path.
        //      then go a connected room on a path that is leftmost and not yet visited, to a room that has not yet been visited
        //           if you have already visited that room, you can't go there, Try another connected room.
        //      if there are no unvisited connected rooms, you don't have a solution to the problem!  Back up and look for a different path.
        // if you reach a room with an exit, and you have visited all rooms and you have only one banana left, then you win!
        // if you reach a room with an exit, and you have not visited all rooms, you have not found a solution! Back up and look for a different path.

        // draw the map
        Renderer renderer = new SwingRenderer();
        renderer.render(map);

        // pick the first room that we see with an exit
        LinkedList<Room> visitedRooms = new LinkedList<Room>();
        int bananaCount = 0;

        Room room = map.findRoomWithExit();
        if (visitedRooms.contains(room)){
            // we were already here! Back up and try again.

        }
        visitedRooms.add(room);
        if (room.getBananas() > 0)  {
            bananaCount = bananaCount + room.getBananas();
        }
        else if (room.getMonkeys() > 0){
            bananaCount = bananaCount - room.getMonkeys();
        }
        if (bananaCount < 0){
            // this isn't a winning path; the monkeys beat you up
            visitedRooms.removeLast();
        }
    }
}
