package robots;
import javax.swing.*;
import static dit948.Random.*;

import becker.robots.*;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.BoxLayout;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class PacMan {
	
     JFrame window;//main frame
     JPanel city;
     ACity sweden;
     CpuRobot pacman;
     Thread pac;
     JPanel first;//user Control
     UserRCRobot user;
     Thread usr;
     RobotUIComponents startt;
     final String path="/Users/chatchai/Dropbox/SEM First term/Programming/Obada/src/robots/misc_58.png";//the directory of the prize image p.s: just place the icon anywhere and change the directory to that place
     
     public PacMan(){//constructor 
    	 intilaize();
    	 
     }
     
     /*
      * I set the layout of the main panel to box layout on the Y_AXIS then added the JPanel City which contain the city view and JPanel first which 
      * contain the user control, finally I set up the JMenuBar on the main frame
      */
     public void intilaize(){
    	 window=new JFrame("PacMan");
 
    	 window.getContentPane().setLayout(new BoxLayout(window.getContentPane(),BoxLayout.Y_AXIS));
    	 window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	 
    	 addCity();
    	 addUserControl();
    	 addMenu();
 
    	window.pack();
    	window.setVisible(true);
     }
     
     
     
     /*
      * Quit the program(System.exit(0))
      */
    public void quit(){
    	 System.exit(0);
    	 
     }
	/*
	 * Here I removed everything from the main JPanel and stopped the threads that were already running and added everything again
	 */
    @SuppressWarnings("deprecation")
	public void restart(){
    	window.getContentPane().removeAll();
    	window.setJMenuBar(null);
         pac.stop();
         usr.stop();
         addCity();
   	     addUserControl();
   	     addMenu();

      	window.pack();
      	startt.getMenuBar().getMenu(1).getItem(0).doClick();
    }
    
    /*
     * JMenuBar which contains Menus(Actions,Settings).
     * Actions contains the JMenuItems((Start/stop),Restart,Quit).
     * Settings contains the JRadioButtons(Easy,Medium,Hard) which I added to a GroupButtons so I can have one selected per time
     */
    public void addMenu(){
    JMenuBar mnb=new JMenuBar();
	 window.setJMenuBar(mnb);

	 
	 JMenu settings=new JMenu("Settings");
	 JMenu actions=new JMenu("Actions");
	 mnb.add(actions);
	 mnb.add(settings);
	 JRadioButtonMenuItem easy=new JRadioButtonMenuItem("Easy");
	 easy.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent q) {//the actionListeners in each easy,hard,medium are pretty much the same (change the speed and restart)
			restart();
			CpuRobot.speed=1;//the speed of the CpuRobot(enemy Robot)  1/5 of UserRobot's speed
		}
	});
	 JRadioButtonMenuItem hard=new JRadioButtonMenuItem("Hard");
	hard.addActionListener(new ActionListener() {
		public void  actionPerformed(ActionEvent q) {
			restart();
			CpuRobot.speed=5; // Same as UserRobot speed
		}
	});
	 JRadioButtonMenuItem med=new JRadioButtonMenuItem("Medium");
	 med.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent q) {
			
			CpuRobot.speed=2.5;// 1/2 of UserRobot's speed
		}
	});
	 
	 ButtonGroup btnG=new ButtonGroup();
	 btnG.add(easy);
	 easy.setSelected(true);//Selected by default
	 btnG.add(hard);
	 btnG.add(med);
	 
	 settings.add(easy);
	 settings.add(med);
	 settings.add(hard);
	 
	 JMenuItem quit=new JMenuItem("Quit");
	 quit.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent q) {
		quit();	
		}
	});
	 
	 
	 startt=new  RobotUIComponents(sweden);
	 JMenuItem restart=new JMenuItem("Restart");
	 restart.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent q) {
		restart();
		}
	});
	 
	 actions.add(startt.getMenuBar().getMenu(1).getItem(0));
	 actions.add(restart);
	 actions.add(quit);	
    }
    
    /*
     * initialize the City JPanel,set its layout to boxLayout on the Y_AXIS and initialize the City sweden.
     * added the UserRobot (user),the CpuRobot (pacman) and the Thing Prize to the City sweden and the Threads usr,pac and start them
     * added City sweden to the JPanel City and added the JPanel to the the main JPanel in the JFrame window
     */
    public void addCity(){
    	/*
    	 * Create some random integers and make sure that they're not the same and assign them to 
    	 * the position of  the Robots and the Thing
    	 */
    	int st1,st2,av1,av2,prizest,prizeav;
    	st1=randomInt(11);
    	st2=randomInt(11);
    	av1=randomInt(11);
    	av2=randomInt(11);
    	prizest=randomInt(11);
    	prizeav=randomInt(11);
    	if(st1==st2 && av1==av2){
    		st1=5;
    		st2=3;
    	}else if(prizest==st1 && prizeav==av1 || prizest==st2 &&prizeav==av2)
    		prizest=randomInt(11);//here I hope that I have no more same results :p
    	prizeav=randomInt(11);
    		
    	
    	city=new JPanel();
    	city.setLayout(new BoxLayout(city,BoxLayout.Y_AXIS));
    	City.showFrame(false);
   	 sweden = new ACity(11);
   	 RobotUIComponents view= new RobotUIComponents(sweden,0,0,11,11);
   	 pacman=new CpuRobot(sweden,st1,av1,Direction.NORTH);
   	 user=new UserRCRobot(sweden,st2,av2,Direction.SOUTH);
   	 usr=new Thread(user);
   	 pac=new Thread(pacman);
   	 usr.start();
   	 pac.start();
   	 Thing prize=new Thing(sweden,prizest,prizeav);
   	 prize.setIcon(new ImageIcon(path));//Set the Icon the the Thing Prize
   	 city.add(view.getCityView());
   	 window.add(city);
    }
    
    /*
     * initialize the JPanel first and set its layout to BorderLayout and add the buttons to it
     * the System.out.println("") is just a little delay because of the insane while loop I have inside the run method of the UserRobot
     */
    
    public void addUserControl(){
    	first=new JPanel();
   	 first.setLayout(new BorderLayout());
   	 
   	 JButton movebtn=new JButton("Up");
   	 movebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(user.getDirection()==Direction.NORTH){//if we're facing the same Direction as the button then
					if(user.frontIsClear()){//make sure we're not going through the wall
				user.control='m';//move one step
				System.out.println("");
				user.control='g';//make sure that we move only one step so we changed the Char control to a random char 
					}
				}else {//if we're not facing that Direction then
					user.control='l';//do one turnLeft
					System.out.println("");
					user.control='g';//same as above :D
				}
			}
		});
   	 first.add(movebtn,BorderLayout.NORTH);//set the place of this button in the JPanle First

   	 JButton pickbtn=new JButton("Pick"); 
   	 pickbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {//and so on...
				user.control='p';
				System.out.println("");
				user.control='g';
				
			}
		});
        first.add(pickbtn, BorderLayout.CENTER);
        
        
        JButton turnleftbtn=new JButton("Left");
        turnleftbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(user.getDirection()==Direction.WEST){
					if(user.frontIsClear()){
				user.control='m';
				System.out.println("");
				user.control='g';
					}
				}else {
					user.control='l';
					System.out.println("");
					user.control='g';
				}
			}
		});
        first.add(turnleftbtn, BorderLayout.WEST);
        
        JButton turnrightbtn=new JButton("Right");
        turnrightbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(user.getDirection()==Direction.EAST){
					if(user.frontIsClear()){
				user.control='m';
				System.out.println("");
				user.control='g';
					}
				}else {
					user.control='l';
					System.out.println("");
					user.control='g';
				}
			}
		});
        first.add(turnrightbtn, BorderLayout.EAST);
        
        JButton turnaroundbtn=new JButton("Down");
        turnaroundbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(user.getDirection()==Direction.SOUTH){
					if(user.frontIsClear()){
				user.control='m';
				System.out.println("");
				user.control='g';
					}
				}else {
					user.control='l';
					System.out.println("");
					user.control='g';
				}
			}
		});
        first.add(turnaroundbtn, BorderLayout.SOUTH);
        window.getContentPane().add(first);
    }
    
	
    /*
     * Lunch and check of the winning and losing conditions
     */
    
	public static void main(String[] args) {
		
		PacMan sweden=new PacMan();
		while (true){//checking of winning and losing conditions
			if(sweden.user.hasPrize()==true){//if the userRobot picks up the prize 
				sweden.pacman.breakme=true;//break the enemy robot
				sweden.startt.getMenuBar().getMenu(1).getItem(0).doClick();//stop the game
				int cmd=JOptionPane.showConfirmDialog(null,
                        "You won! \n Restart game?", 
                        "Game Over", JOptionPane.YES_NO_OPTION);//Display the restart message
				if(cmd==0)
					sweden.restart();
				if(cmd==1)
					System.exit(0);
			}else if (sweden.user.getIntersection()==sweden.pacman.getIntersection()){//if the enemy robot touches the user Robot
				sweden.user.breakme=true;//Same thing...etc
				sweden.startt.getMenuBar().getMenu(1).getItem(0).doClick();
				int cmd=JOptionPane.showConfirmDialog(null,
                        "You lose! \n Restart game?", 
                        "Game Over", JOptionPane.YES_NO_OPTION);
				if(cmd==0)
					sweden.restart();
				if(cmd==1)
					System.exit(0);
			}
		}
		
        
        
        	
	}

}
