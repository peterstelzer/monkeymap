package com.maya.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pete on 10/6/14.
 */
public class Room {
    /**
     * Count of monkeys in the room.
     */
    private int monkeys = 0;

    /**
     * Count of bananas in the room.
     */
    private int bananas = 0;


    /**
     * Count of exits in the room.
     */
    private int exits = 0;

    private int xLocation;

    private int yLocation;


    /**
     * Rooms have a reference to the map of rooms, so they can find out about rooms around them. This is important for rendering.
     */
    private MonkeyMap mapOfRooms;


    public int getyLocation() {
        return yLocation;
    }

    public void setyLocation(int yLocation) {
        this.yLocation = yLocation;
    }

    /**
     * The list of connected rooms.
     */
    List<Room> connections = new ArrayList<Room>();



    public Room(int monkeys, int bananas, int exits, int xLocation, int yLocation, MonkeyMap mapOfRooms){
        setMonkeys(monkeys);
        setBananas(bananas);
        setExits(exits);
        setxLocation(xLocation);
        setyLocation(yLocation);
        setMapOfRooms(mapOfRooms);
    }


    public int getMonkeys() {
        return monkeys;
    }

    public void setMonkeys(int monkeys) {
        this.monkeys = monkeys;
    }

    public int getBananas() {
        return bananas;
    }

    public void setBananas(int bananas) {
        this.bananas = bananas;
    }

    public int getExits() {
        return exits;
    }

    public void setExits(int exits) {

        this.exits = exits;
    }

    public int getxLocation() {
        return xLocation;
    }

    public void setxLocation(int xLocation) {
        this.xLocation = xLocation;
    }

    public MonkeyMap getMapOfRooms() {
        return mapOfRooms;
    }

    private void setMapOfRooms(MonkeyMap mapOfRooms) {
        this.mapOfRooms = mapOfRooms;
    }


    public void addConnectingRoom(Room connectedRoom){
        connections.add(connectedRoom);
        connectedRoom.addOneSidedConnectingRoom(this);
    }


    /**
     * A method intended not to be public, but prevents infinite loops in the addConnectingRoom method, when a reciprocal connection is added to the connecting room
     * @param room
     */
    public void addOneSidedConnectingRoom(Room room){
        connections.add(room);
    }

    public boolean isRoomConnected(Room testRoom){
        for (Room room : connections) {
            if (room.equals(testRoom)){
                return true;
            }
        }
        return false;
    }


    @Override
    public boolean equals(Object object) {
        if (object instanceof Room && ((Room) object).getxLocation() == this.getxLocation() && ((Room) object).getyLocation() == this.getyLocation()) {
            return true;
        } else {
            return false;
        }
    }

}
