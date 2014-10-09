package com.maya.view;

import com.maya.model.Room;
import com.maya.rules.MayaRules;

import javax.swing.*;
import java.awt.*;

/**
 * This class is a Swing component representing a Room. It knows how to render itself.
 * Created by pete on 10/6/14.
 */
public class SwingRoom extends JPanel{
    private Room room;

    public SwingRoom(Room room){
        this.room = room;
    }

    private static int border = 20;
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawRect(border, border, getWidth()-2*border, getHeight()-2*border);
        //g.drawRect(0, 0, getWidth(), getHeight());
        int x = room.getxLocation();
        int y = room.getyLocation();
        // if there is a room above, and it's connected, draw a line leading up
        Room neighborRoom = room.getMapOfRooms().getRoom(x, y-1);
        if (neighborRoom != null && room.isRoomConnected(neighborRoom))  {
            g.drawLine(getWidth()/2, 0, getWidth()/2, border);
        }
        // check the room to the left
        neighborRoom = room.getMapOfRooms().getRoom(x-1, y);
        if (neighborRoom != null && room.isRoomConnected(neighborRoom)){
            g.drawLine(0, getHeight()/2, border, getHeight()/2);
        }
        // check the room to the right
        neighborRoom = room.getMapOfRooms().getRoom(x+1, y);
        if (neighborRoom != null && room.isRoomConnected(neighborRoom)){
           g.drawLine(getWidth() - border, getHeight()/2, getWidth(), getHeight()/2);
        }
        // check the room below
        neighborRoom = room.getMapOfRooms().getRoom(x, y+1);
        if (neighborRoom != null && room.isRoomConnected(neighborRoom)){
            g.drawLine(getWidth()/2, getHeight() - border, getWidth()/2, getHeight());
        }
        // check the room diagonally up and to the left
        neighborRoom = room.getMapOfRooms().getRoom(x-1, y-1);
        if (neighborRoom != null && room.isRoomConnected(neighborRoom)){
            g.drawLine(0, 0, border, border);
        }
        // check the room diagonally down and to the right
        neighborRoom = room.getMapOfRooms().getRoom(x+1, y+1);
        if (neighborRoom != null && room.isRoomConnected(neighborRoom)){
            g.drawLine(getWidth()-border, getHeight()-border, getWidth(), getHeight());
        }
        // check the room diagonally up and to the right
        neighborRoom = room.getMapOfRooms().getRoom(x+1, y-1);
        if (neighborRoom != null && room.isRoomConnected(neighborRoom)){
            g.drawLine(getWidth()-border, border, getWidth(), 0);
        }
        // check the room diagonally down and to the left
        neighborRoom = room.getMapOfRooms().getRoom(x-1, y+1);
        if (neighborRoom != null && room.isRoomConnected(neighborRoom)){
            g.drawLine(border, getHeight() - border, 0, getHeight());
        }

        if (room.getBananas() > 0){
            g.drawString(new Integer(room.getBananas()).toString() + (room.getBananas() == 1 ? " banana" : " bananas"), border*2, getHeight()/3);
        }
        if (room.getMonkeys() > 0){
            g.drawString(new Integer(room.getMonkeys()).toString() + (room.getMonkeys() == 1 ? " monkey" : " monkeys"), border*2, getHeight()/3);
        }

        // if this room has an exit, draw it now
        if (room.getExits() > 0){
            if (room.getxLocation() == 0){
                // it's on the left edge
                g.drawLine(0, getHeight()/2, border, getHeight()/2);
            }
            if (room.getxLocation() == MayaRules.MAP_WIDTH - 1){
                // it's on the right edge
                g.drawLine(getWidth() - border, getHeight()/2, getWidth(), getHeight()/2);
            }
        }
    }
}
