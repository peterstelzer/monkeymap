package com.maya.rules;

import com.maya.model.Room;
import com.maya.util.TreeNode;
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
        //LinkedList<Room> visitedRooms = new LinkedList<Room>();

        int bananaCount = 0;

        // Pass 1: build a tree of possible paths from an exit to a point where all rooms have been visited or the path is blocked off

        Room room = map.findRoomWithExit();
        TreeNode<Room> tree = new TreeNode<Room>(room);
        traverseToNextRoom(tree, map, 0);
        System.out.println("We found a total of " + tree.numberOfLeafNodes() + " that result in a positive number of monkeys");
        List<TreeNode<Room>> solutions = countPathsWithLeafNodesAtExit(tree);


        // we now have a tree of all paths through the maze; now let's check each of them for the following conditions:
        //  1. At no point in the path do we have a banana count < 0
        //  2. We end up at an exit
        TreeNode<Room> rootRoom = tree;


/*
        while (room.getExits() == 0 || !weVisitedAllRooms(visitedRooms) || bananaCount != 1) {
            if (!visitedRooms.contains(room)){
                if (room.getBananas() > 0)  {
                    bananaCount = bananaCount + room.getBananas();
                }
                else if (room.getMonkeys() > 0){
                    bananaCount = bananaCount - room.getMonkeys();
                }
                if (bananaCount >= 0){
                    // we still have bananas! Let's continue.
                    // pick a room to go to
                    visitedRooms.add(room);
                    room = room.getAnUnvisitedConnectedRoom();
                }
                else {    // we don't have a surplus of bananas!  Let's back up.   Also, let's remove the last room, so we can revisit it, and let's back out our banana count
                    if (room.getBananas() > 0)  {
                        bananaCount = bananaCount - room.getBananas();
                    }
                    else if (room.getMonkeys() > 0){
                        bananaCount = bananaCount + room.getMonkeys();
                    }
                    room = visitedRooms.getLast();
                    visitedRooms.removeLast();
                    // now we have to back out the previous room's banana changes
                    if (room.getBananas() > 0)  {
                        bananaCount = bananaCount - room.getBananas();
                    }
                    else if (room.getMonkeys() > 0){
                        bananaCount = bananaCount + room.getMonkeys();
                    }

                }
            }
            else { // we visited this room already!  Let's back out of this.
                room = visitedRooms.getLast();
                visitedRooms.removeLast();
                // back out the changes from the current room, since they'll be applied again at the next go-round
                if (room.getBananas() > 0)  {
                    bananaCount = bananaCount - room.getBananas();
                }
                else if (room.getMonkeys() > 0){
                    bananaCount = bananaCount + room.getMonkeys();
                }

            }
        }
*/
   }

    private static boolean weVisitedAllRooms(LinkedList<Room> visitedRooms){
        return (visitedRooms.size() == 2);
    }

    /**
     * Recursively traverse a path through the rooms, returning false if there are no next rooms to visit, either because we are trapped, or because all the rooms have been visited
     * @param tree
     * @param map the map of the rooms
     *
     */
    private static void traverseToNextRoom(TreeNode<Room> tree, MonkeyMap map, int bananaCount){
        // build up a tree representation of all the paths through the map, adding rooms to the current path only if that room has not yet been added, and stopping if all rooms have already
        // been visited.
        // Future grounds for removal of this branch: if the branch doesn't end in an exit, and maybe if the banana count at any point is < 0, but this will be a post-processing step for now
        bananaCount = bananaCount + tree.getData().getBananas() - tree.getData().getMonkeys();
        for (Room connectedRoom : tree.getData().getConnections()){
            TreeNode<Room> child = new TreeNode<Room>(connectedRoom);
            if ((bananaCount + child.getData().getBananas() - child.getData().getMonkeys() >= 0) && !allRoomsVisited(tree) && tree.addChildNode(child)){
                // haven't visited this room yet, let's go there
                traverseToNextRoom(child, map, bananaCount);
            }
        }
    }

    /**
     * Determines if all the rooms have been visited by comparing the length of the current branch to the total number of rooms.
     * @param room
     * @return whether this branch represents all the rooms being visited.
     */
    private static boolean allRoomsVisited(TreeNode<Room> room){
        return room.getDistanceToRoot() == MAP_WIDTH * MAP_LENGTH;
    }

    private static List<TreeNode<Room>> countPathsWithLeafNodesAtExit(TreeNode<Room> tree){
        List<TreeNode<Room>> solutions = tree.findPathsWithCertainEndpoint(new Room(0, 0, 0, 4, 2, null));
        return solutions;
    }

}
