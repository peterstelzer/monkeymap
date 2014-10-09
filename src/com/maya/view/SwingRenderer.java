package com.maya.view;

import com.maya.model.MonkeyMap;
import com.maya.model.Room;
import com.maya.rules.MayaRules;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by pete on 10/6/14.
 */
public class SwingRenderer implements Renderer {
    JFrame mainFrame = new JFrame("MonkeyMap");
    JPanel panel = new JPanel();
    public static int frameSize=700;


    public void render(MonkeyMap map) {
        panel.setLayout(new GridLayout(5, 5));
        mainFrame.add(panel);
        mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                mainFrame.setVisible(false);
                // Perform any other operations you might need
                // before exit.
                System.exit(0);
            }
        });
        for (int y = 0; y < MayaRules.MAP_LENGTH; y++){
            for (int x= 0; x < MayaRules.MAP_WIDTH; x++){
                addRoom(map.getRoom(x, y));
            }
        }
        mainFrame.pack();
        mainFrame.setSize(frameSize,frameSize);
        mainFrame.setVisible(true);
    }

    private void addRoom(Room room){
        panel.add(new SwingRoom(room));

    }

}
