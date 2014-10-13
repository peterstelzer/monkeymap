package com.maya.model;

import com.maya.rules.MayaRules;

import java.util.List;

/**
 * Created by pete on 10/6/14.
 */
public class MonkeyMap {

    /**
     * The map's rooms, stored in a two-dimensional array
     */
    private Room[][] rooms = new Room[5][5];

    /**
     * The path to render (path shows the current path being tested)
     */
    private List<Room> path = null;


    public MonkeyMap(){
        rooms[0][0] = new Room(0, 3, 0, 0, 0, this);
        rooms[1][0] = new Room(2, 0, 0, 1, 0, this);
        rooms[2][0] = new Room(0, 1, 0, 2, 0, this);
        rooms[3][0] = new Room(2, 0, 0, 3, 0, this);
        rooms[4][0] = new Room(0, 3, 0, 4, 0, this);

        rooms[0][1] = new Room(2, 0, 0, 0, 1, this);
        rooms[1][1] = new Room(0, 3, 0, 1, 1, this);
        rooms[2][1] = new Room(1, 0, 0, 2, 1, this);
        rooms[3][1] = new Room(0, 3, 0, 3, 1, this);
        rooms[4][1] = new Room(2, 0, 0, 4, 1, this);

        rooms[0][2] = new Room(0, 1, 1, 0, 2, this);
        rooms[1][2] = new Room(1, 0, 0, 1, 2, this);
        rooms[2][2] = new Room(3, 0, 0, 2, 2, this);
        rooms[3][2] = new Room(1, 0, 0, 3, 2, this);
        rooms[4][2] = new Room(0, 1, 1, 4 ,2, this);

        rooms[0][3] = new Room(2, 0, 0, 0, 3, this);
        rooms[1][3] = new Room(0, 1, 0, 1, 3, this);
        rooms[2][3] = new Room(2, 0, 0, 2, 3, this);
        rooms[3][3] = new Room(0, 3, 0, 3, 3, this);
        rooms[4][3] = new Room(2, 0, 0, 4, 3, this);

        rooms[0][4] = new Room(0, 1, 0, 0 ,4, this);
        rooms[1][4] = new Room(1, 0, 0, 1, 4, this);
        rooms[2][4] = new Room(0, 3, 0, 2, 4, this);
        rooms[3][4] = new Room(2, 0, 0, 3, 4, this);
        rooms[4][4] = new Room(0, 1, 0, 4, 4, this);


        rooms[0][0].addConnectingRoom(rooms[1][0]);
        rooms[0][0].addConnectingRoom(rooms[0][1]);
        rooms[0][0].addConnectingRoom(rooms[1][1]);

        rooms[1][0].addConnectingRoom(rooms[1][1]);
        rooms[1][0].addConnectingRoom(rooms[2][0]);

        rooms[2][0].addConnectingRoom(rooms[2][1]);
        rooms[2][0].addConnectingRoom(rooms[1][1]);
        rooms[2][0].addConnectingRoom(rooms[3][1]);
        rooms[2][0].addConnectingRoom(rooms[3][0]);

        rooms[3][0].addConnectingRoom(rooms[3][1]);
        rooms[3][0].addConnectingRoom(rooms[4][0]);

        rooms[4][0].addConnectingRoom(rooms[4][1]);

        rooms[0][1].addConnectingRoom(rooms[1][1]);
        rooms[0][1].addConnectingRoom(rooms[0][2]);

        rooms[1][1].addConnectingRoom(rooms[0][2]);
        rooms[1][1].addConnectingRoom(rooms[1][2]);
        rooms[1][1].addConnectingRoom(rooms[2][2]);
        rooms[1][1].addConnectingRoom(rooms[2][1]);

        rooms[2][1].addConnectingRoom(rooms[3][1]);
        rooms[2][1].addConnectingRoom(rooms[2][2]);

        rooms[3][1].addConnectingRoom(rooms[2][2]);
        rooms[3][1].addConnectingRoom(rooms[3][2]);
        rooms[3][1].addConnectingRoom(rooms[4][2]);
        rooms[3][1].addConnectingRoom(rooms[4][1]);
        rooms[3][1].addConnectingRoom(rooms[4][0]);

        rooms[4][1].addConnectingRoom(rooms[4][2]);

        rooms[0][2].addConnectingRoom(rooms[1][2]);
        rooms[0][2].addConnectingRoom(rooms[1][3]);
        rooms[0][2].addConnectingRoom(rooms[0][3]);

        rooms[1][2].addConnectingRoom(rooms[2][2]);
        rooms[1][2].addConnectingRoom(rooms[1][3]);

        rooms[2][2].addConnectingRoom(rooms[1][3]);
        rooms[2][2].addConnectingRoom(rooms[2][3]);
        rooms[2][2].addConnectingRoom(rooms[3][3]);
        rooms[2][2].addConnectingRoom(rooms[3][2]);

        rooms[3][2].addConnectingRoom(rooms[4][2]);
        rooms[3][2].addConnectingRoom(rooms[3][3]);

        rooms[4][2].addConnectingRoom(rooms[4][3]);
        rooms[4][2].addConnectingRoom(rooms[3][3]);

        rooms[0][3].addConnectingRoom(rooms[0][4]);
        rooms[0][3].addConnectingRoom(rooms[1][3]);

        rooms[1][3].addConnectingRoom(rooms[0][4]);
        rooms[1][3].addConnectingRoom(rooms[1][4]);
        rooms[1][3].addConnectingRoom(rooms[2][4]);
        rooms[1][3].addConnectingRoom(rooms[2][3]);

        rooms[2][3].addConnectingRoom(rooms[2][4]);
        rooms[2][3].addConnectingRoom(rooms[3][3]);

        rooms[3][3].addConnectingRoom(rooms[2][4]);
        rooms[3][3].addConnectingRoom(rooms[3][4]);
        rooms[3][3].addConnectingRoom(rooms[4][4]);
        rooms[3][3].addConnectingRoom(rooms[4][3]);

        rooms[4][3].addConnectingRoom(rooms[4][4]);

        rooms[0][4].addConnectingRoom(rooms[1][4]);

        rooms[1][4].addConnectingRoom(rooms[2][4]);

        rooms[2][4].addConnectingRoom(rooms[3][4]);

        rooms[3][4].addConnectingRoom(rooms[4][4]);

    }

    /**
     * Return a room at a given location
     * @param x
     * @param y
     * @return the Room at the given location
     */
    public Room getRoom(int x, int y){
        if (x < 0 || x >= MayaRules.MAP_WIDTH)  {
            return null;
        }
        if (y < 0 || y >= MayaRules.MAP_LENGTH) {
            return null;
        }
          return rooms[x][y];
    }

    /**
     * Returns the first room with an exit in the map.
     * @return a Room with an exit
     */
    public Room findRoomWithExit(){
        for (int y = 0; y < MayaRules.MAP_LENGTH; y++){
            for (int x= 0; x < MayaRules.MAP_WIDTH; x++){
                if (getRoom(x, y).getExits() > 0){
                    return getRoom(x, y);
                }
            }
        }
        return null;
    }


}
