/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learnRobot;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



/**
 *
 * @author chatchai
 */
public class SpeedListener implements ActionListener{
    Frames fr;
    int speed;
    
    public SpeedListener(int speed, Frames fr) {
        this.speed = speed;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       fr.changespeed(speed);
    }
}
