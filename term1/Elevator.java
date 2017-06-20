package term1;

import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.TimerTask;
import java.util.Timer;

import javax.swing.*;

public class Elevator extends JFrame {
	//inner button name
	String[] view = { "1", "2", "3", "4", "5", "6", "7", "8", "◀▶", "▶◀" };
	//images for JButtons
	ImageIcon upImg = new ImageIcon("up.png");
	ImageIcon downImg = new ImageIcon("down.png");
	ImageIcon upCheckedImg = new ImageIcon("up_p.png");
	ImageIcon downCheckedImg = new ImageIcon("down_p.png");
	ImageIcon topNormal = new ImageIcon("top.png");
	ImageIcon topRoll = new ImageIcon("top_n.png");
	ImageIcon topPressed = new ImageIcon("top_p.png");
	ImageIcon bottomNormal = new ImageIcon("bottom.png");
	ImageIcon bottomRoll = new ImageIcon("bottom_n.png");
	ImageIcon bottomPressed = new ImageIcon("bottom_p.png");
	ImageIcon closedoor = new ImageIcon("door1.png");
	ImageIcon opendoor = new ImageIcon("door2.png");
	ImageIcon addNormal1 = new ImageIcon("pathdoor1.png");
	ImageIcon addPressed1 = new ImageIcon("pathdoor1_p.png");
	ImageIcon addNormal2 = new ImageIcon("pathdoor2.png");
	ImageIcon addPressed2 = new ImageIcon("pathdoor2_p.png");
	ImageIcon enterNormal= new ImageIcon("patheledoor.png");
	ImageIcon enterPressed = new ImageIcon("patheledoor_p.png");
	
	JPanel centralPanel = new JPanel() {
		Image Back = Toolkit.getDefaultToolkit().getImage("back.PNG");

		public void paintComponent(Graphics g) {
			g.drawImage(Back, 0, 0, 1200, 750, this);
		}
	};//main panel

	JPanel innerButtonPanel1 = new JPanel() {
		Image insideBack = Toolkit.getDefaultToolkit().getImage(
				"insideBack.PNG");

		public void paintComponent(Graphics g) {
			g.drawImage(insideBack, 0, 0, 92, 180, this);
		}
	};//ele1 inner button panel
	
	JPanel innerButtonPanel2 = new JPanel() {
		Image insideBack = Toolkit.getDefaultToolkit().getImage(
				"insideBack.PNG");

		public void paintComponent(Graphics g) {
			g.drawImage(insideBack, 0, 0, 92, 180, this);
		}
	};//ele2 inner button panel
	
	JPanel innerButtonPanel3 = new JPanel() {
		Image insideBack = Toolkit.getDefaultToolkit().getImage(
				"insideBack.PNG");

		public void paintComponent(Graphics g) {
			g.drawImage(insideBack, 0, 0, 92, 180, this);
		}
	};//ele3 inner button panel
	
	JPanel elePanel1 = new JPanel() {// 1번째 Elevator panel
		public void paintComponent(Graphics g) {
			g.drawImage(Toolkit.getDefaultToolkit().getImage("EBack.png"), 0,
					0, 77, 600, this);
		}
	};//panel for show moving elevator1
	
	JPanel elePanel2 = new JPanel() {// 2번째 Elevator panel
		public void paintComponent(Graphics g) {
			g.drawImage(Toolkit.getDefaultToolkit().getImage("EBack.png"), 0,
					0, 77, 600, this);
		}
	};//panel for show moving elevator2
	
	JPanel elePanel3 = new JPanel() {// 3번째 Elevator panel
		public void paintComponent(Graphics g) {
			g.drawImage(Toolkit.getDefaultToolkit().getImage("EBack.png"), 0,
					0, 77, 600, this);
		}
	};//panel for show moving elevator3

	JPanel northPanel1 = new JPanel(){
		public void paintComponent(Graphics g) {
			g.drawImage(Toolkit.getDefaultToolkit().getImage("north.png"), 0,
					0, 77, 33, this);
		}
	};//panel for showing location of elevator1
	
	JPanel northPanel2 = new JPanel(){
		public void paintComponent(Graphics g) {
			g.drawImage(Toolkit.getDefaultToolkit().getImage("north.png"), 0,
					0, 77, 33, this);
		}
	};//panel for showing location of elevator2
	
	JPanel northPanel3 = new JPanel(){
		public void paintComponent(Graphics g) {
			g.drawImage(Toolkit.getDefaultToolkit().getImage("north.png"), 0,
					0, 77, 33, this);
		}
	};//panel for showing location of elevator3
	
	JPanel[] floorPanel = new JPanel[8];//panel for outer buttons

	ImageIcon[] buttonNormal = new ImageIcon[10];
	ImageIcon[] buttonRoll = new ImageIcon[10];
	ImageIcon[] buttonPressed = new ImageIcon[10];
	
	JButton no1 = new JButton();//elevator1
	JButton no2 = new JButton();//elevator2
	JButton no3 = new JButton();//elevator3
	JButton[] top = new JButton[8];// outer top buttons
	JButton[] bottom = new JButton[8];// outer bottom buttons
	JButton[] updown = new JButton[6];// show elevator is moving up or down
	JButton[] ele1 = new JButton[10];// ele1 inner buttons
	JButton[] ele2 = new JButton[10];// ele2 inner buttons
	JButton[] ele3 = new JButton[10];// ele3 inner buttons 
	JButton[] add = new JButton[8];// buttons for produce minions
	JButton[] enterdoor = new JButton[8];// buttons for open and close doors in front of elevator
	JButton[] cp = new JButton[3];//show locaion of each elevators 
	
	boolean[] allTop = new boolean[8];
	boolean[] allBottom = new boolean[8];
	boolean[] innerButton1 = new boolean[10];
	boolean[] innerButton2 = new boolean[10];
	boolean[] innerButton3 = new boolean[10];
	boolean[] no_1Top = new boolean[8];
	boolean[] no_2Top = new boolean[8];
	boolean[] no_3Top = new boolean[8];
	boolean[] no_1Bottom = new boolean[8];
	boolean[] no_2Bottom = new boolean[8];
	boolean[] no_3Bottom = new boolean[8];
	JButton[] Wbutton = new JButton[3];
	
	private static ArrayList<Minion> minion = new ArrayList<Minion>();//array list about produced minions
	static int elein1 = 0, elein2 = 0, elein3 = 0;//index for minions who are in each elevator
	static int countMinion1 = 0;//count minions in ele1
	static int countMinion2 = 0;//count minions in ele2
	static int countMinion3 = 0;//count minions in ele3
	static int countM = -1;//values how many total minions are in each elevator
	
	String tmp1 = new String();//text for cp
	String tmp2 = new String();//text for cp
	String tmp3 = new String();//text for cp

	static int[] cache_s = { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	static int[] cache_d = { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	static int weight1 = 0, weight2 = 0, weight3 = 0, maxweight = 1500;
	// Each Elevator`s weight 
	static int no_1location, no_2location, no_3location, moveupdown1,
			moveupdown2, moveupdown3, check_1=-1, check_2, check_3, statement1,
			statement2, statement3, final1=-1, final2, final3;

	ElevatorNo_1 ev1 = new ElevatorNo_1();
	ElevatorNo_2 ev2 = new ElevatorNo_2();
	ElevatorNo_3 ev3 = new ElevatorNo_3();
	TotalElevator evt = new TotalElevator();

	// basic gui form
	public Elevator() {
		super();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		init();
		setSize(1200, 750);
		setVisible(true);
		evt.start();
		ev1.start();
		ev2.start();
		ev3.start();
	}

	public static void main(String[] args) {
		new Elevator();
	}

	//class for producing minion
	class AddMinion {
		int x, y;

		public AddMinion() {
			x = 0;
			y = 0;
		}

		public AddMinion(int floor) {
			Minion m = new Minion();
			minion.add(m);
			m.setStart(floor);
			m.setDestination(-1);
			m.setX(x);
			m.setY(y);
			m.setLinkedIndex(countM);

			int rand = (int) (Math.random() * 7 - 1);
			m.setImage(rand);
		}

	}

	private void init() {
		//set all panel layout to null
		setLayout(null);
		centralPanel.setLayout(null);
		innerButtonPanel1.setLayout(null);
		innerButtonPanel2.setLayout(null);
		innerButtonPanel3.setLayout(null);
		northPanel1.setLayout(null);
		northPanel2.setLayout(null);
		northPanel3.setLayout(null);
		elePanel1.setLayout(null);
		elePanel2.setLayout(null);
		elePanel3.setLayout(null);

		//put panels on central panel
		add(centralPanel);
		centralPanel.add(innerButtonPanel1);
		centralPanel.add(innerButtonPanel2);
		centralPanel.add(innerButtonPanel3);
		centralPanel.add(northPanel1);
		centralPanel.add(northPanel2);
		centralPanel.add(northPanel3);
		centralPanel.add(elePanel1);
		centralPanel.add(elePanel2);
		centralPanel.add(elePanel3);

		//set panels location using coordinate
		centralPanel.setBounds(0, 0, 1200, 750);
		innerButtonPanel1.setBounds(982, 70, 92, 180);
		innerButtonPanel2.setBounds(982, 280, 92, 180);
		innerButtonPanel3.setBounds(982, 490, 92, 180);
		northPanel1.setBounds(628, 57, 77, 33);
		northPanel2.setBounds(715, 57, 77, 33);
		northPanel3.setBounds(802, 57, 77, 33);
		elePanel1.setBounds(628, 95, 77, 600);
		elePanel2.setBounds(715, 95, 77, 600);
		elePanel3.setBounds(802, 95, 77, 600);

	
		northPanel1.setOpaque(false);
		northPanel2.setOpaque(false);
		northPanel3.setOpaque(false);

				
		// moving elevator1 form
		no1 = new JButton(closedoor);
		no1.setBorderPainted(false);
		no1.setFocusPainted(false);
		no1.setContentAreaFilled(false);
		no1.setVisible(true);
		elePanel1.add(no1);
		no1.setBounds(3, 525, 70, 75);  //좌표

		// moving elevator2 form
		no2 = new JButton(closedoor);
		no2.setBorderPainted(false);
		no2.setFocusPainted(false);
		no2.setContentAreaFilled(false);
		no2.setVisible(true);
		elePanel2.add(no2);
		no2.setBounds(3, 525, 70, 75);

		// moving elevator3 form
		no3 = new JButton(closedoor);
		no3.setBorderPainted(false);
		no3.setFocusPainted(false);
		no3.setContentAreaFilled(false);
		no3.setVisible(true);
		elePanel3.add(no3);
		no3.setBounds(3, 525, 70, 75);

		// set inner buttons coordinate
		for (int i = 0; i < ele1.length; i++) {
			buttonNormal[i] = new ImageIcon("b" + (i + 1) + ".png");
			buttonRoll[i] = new ImageIcon("b" + (i + 1) + "_n.png");
			buttonPressed[i] = new ImageIcon("b" + (i + 1) + "_p.png");

			ele1[i] = new JButton(buttonNormal[i]);
			ele1[i].setRolloverIcon(buttonRoll[i]);
			ele1[i].setPressedIcon(buttonPressed[i]);
			ele1[i].setBorderPainted(false);
			ele1[i].setFocusPainted(false);
			ele1[i].setContentAreaFilled(false);
			ele1[i].setEnabled(false);
			innerButtonPanel1.add(ele1[i]);

			ele2[i] = new JButton(buttonNormal[i]);
			ele2[i].setRolloverIcon(buttonRoll[i]);
			ele2[i].setPressedIcon(buttonPressed[i]);
			ele2[i].setBorderPainted(false);
			ele2[i].setFocusPainted(false);
			ele2[i].setContentAreaFilled(false);
			ele2[i].setEnabled(false);
			innerButtonPanel2.add(ele2[i]);

			ele3[i] = new JButton(buttonNormal[i]);
			ele3[i].setRolloverIcon(buttonRoll[i]);
			ele3[i].setPressedIcon(buttonPressed[i]);
			ele3[i].setBorderPainted(false);
			ele3[i].setFocusPainted(false);
			ele3[i].setContentAreaFilled(false);
			ele3[i].setEnabled(false);
			innerButtonPanel3.add(ele3[i]);

			if (i % 2 == 1) {// even number buttons
				ele1[i].setBounds(48, 137 - (30 * (i + 1) / 2), 30, 30);
				ele2[i].setBounds(48, 137 - (30 * (i + 1) / 2), 30, 30);
				ele3[i].setBounds(48, 137 - (30 * (i + 1) / 2), 30, 30);
				if (i == 9) {
					ele1[i].setBounds(48, 138, 30, 30);
					ele2[i].setBounds(48, 138, 30, 30);
					ele3[i].setBounds(48, 138, 30, 30);
				}
			} else {// odd number buttons
				ele1[i].setBounds(14, 107 - (30 * i / 2), 30, 30);
				ele2[i].setBounds(14, 107 - (30 * i / 2), 30, 30);
				ele3[i].setBounds(14, 107 - (30 * i / 2), 30, 30);
				if (i == 8) {
					ele1[i].setBounds(14, 138, 30, 30);
					ele2[i].setBounds(14, 138, 30, 30);
					ele3[i].setBounds(14, 138, 30, 30);
				}
			}

			ele1[i].addActionListener(new MyAction());
			ele2[i].addActionListener(new MyAction());
			ele3[i].addActionListener(new MyAction());
		}

		//set cp which show location of each elevators
		//wbutton show weight of each elevators
		for (int i = 0; i < 3; i++) {
			cp[i] = new JButton("1 F");
			Wbutton[i]= new JButton("0 KG");
			if (i == 0) {
				northPanel1.add(cp[i]);
				centralPanel.add(Wbutton[i]);
			} else if (i == 1) {
				northPanel2.add(cp[i]);
				centralPanel.add(Wbutton[i]);
			} else if (i == 2) {
				northPanel3.add(cp[i]);
				centralPanel.add(Wbutton[i]);
			}

			cp[i].setBounds(8, 0, 60, 30);
			cp[i].setBorderPainted(false);
			cp[i].setFocusPainted(false);
			cp[i].setContentAreaFilled(false);
			
			Wbutton[i].setBounds(628+85*i, 10, 77, 30);
		}

		//set add button and enterdoor button form
		for (int i = 0; i < 8; i++) {
			if(i==1 || i==6){
				add[i] = new JButton(addNormal2);
				add[i].setPressedIcon(addPressed2);
			}
			else{
				add[i] = new JButton(addNormal1);
				add[i].setPressedIcon(addPressed1);
			}
			add[i].setBorderPainted(false);
			add[i].setFocusPainted(false);
			add[i].setContentAreaFilled(false);
			add[i].setOpaque(false);
			add[i].setBounds(50, 624 - (76 * i), 49, 76);
			centralPanel.add(add[i]);

			enterdoor[i] = new JButton(enterNormal);
			enterdoor[i].setBorderPainted(false);
			enterdoor[i].setFocusPainted(false);
			enterdoor[i].setContentAreaFilled(false);
			enterdoor[i].setOpaque(false);
			enterdoor[i].setBounds(565, 624 - (76 * i), 49, 76);
			centralPanel.add(enterdoor[i]);
			
			add[i].addActionListener(new MyAction());

		}
		// set outer buttons coordinate
		for (int i = 0; i < floorPanel.length; i++) {
			floorPanel[i] = new JPanel() {
				public void paintComponent(Graphics g) {
					Image outsideBack = Toolkit.getDefaultToolkit().getImage(
							"outsideBack.png");
					g.drawImage(outsideBack, 0, 0, 63, 62, this);
				}
			};
			centralPanel.add(floorPanel[i]);

			floorPanel[i].setLayout(null);
			floorPanel[i].setOpaque(false);
			floorPanel[i].setBounds(890, 626 - (i * 77), 63, 62);

			top[i] = new JButton(topNormal);
			top[i].setRolloverIcon(topRoll);
			top[i].setBorderPainted(false);
			top[i].setFocusPainted(false);
			top[i].setOpaque(false);
			top[i].setContentAreaFilled(false);


			bottom[i] = new JButton(bottomNormal);
			bottom[i].setRolloverIcon(bottomRoll);
			bottom[i].setOpaque(false);
			bottom[i].setBorderPainted(false);
			bottom[i].setFocusPainted(false);
			bottom[i].setContentAreaFilled(false);

			floorPanel[i].add(top[i]);
			floorPanel[i].add(bottom[i]);

			top[i].setBounds(7, 21, 49, 14);// set top button coordinate
			bottom[i].setBounds(7, 42, 49, 14);// set bottom buttom coordinate
			top[i].addActionListener(new MyAction());
			bottom[i].addActionListener(new MyAction());
		}

		top[7].setEnabled(false); // set unusable top button of floor 8
		bottom[0].setEnabled(false); // set unusable bottom button of floor 1

		// set updown button coordinate
		for (int i = 0; i < updown.length; i++) {
			if (i % 2 == 0) {
				updown[i] = new JButton(upImg);
				updown[i].setBorderPainted(false);
				updown[i].setFocusPainted(false);
				updown[i].setContentAreaFilled(false);
				updown[i].setBounds(3, 12, 20, 20);
			} else if (i % 2 == 1) {
				updown[i] = new JButton(downImg);
				updown[i].setBorderPainted(false);
				updown[i].setFocusPainted(false);
				updown[i].setContentAreaFilled(false);
				updown[i].setBounds(55, 12, 20, 20);
			}

			if (i < 2) {
				northPanel1.add(updown[i]);
			} else if (2 <= i && i < 4) {
				northPanel2.add(updown[i]);
			} else {
				northPanel3.add(updown[i]);
			}
		}
	}

	class MyAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			for (int i = 0; i < 8; i++) {

				//if press inner buttons of ele1
				if (e.getSource().equals(ele1[i])) {
					
					innerButton1[i] = true;
					ele1[i].setIcon(buttonPressed[i]);
					if (e.getSource().equals(ele1[8]))
					{
						try {
							Thread.sleep(2000);
						} catch (InterruptedException E) {
							E.printStackTrace();
						}
						break;
					}
					for (int k = 0; k < minion.size(); k++) {
						//set minions' destination
						if (minion.get(k).getEleNum() == 1 && minion.get(k).getDestination() == -1 && minion.get(k).getRemove() == 0) {
							minion.get(k).setDestination(i);
							minion.get(k).setY(620 - (75 * minion.get(k).getDestination()));
							countMinion1--;
							break;
						}
					}
					if (countMinion1 == 0) {
						for (int k = 0; k < ele1.length - 2; k++)
							ele1[k].setEnabled(false);
					}
					
				}

				//if press inner buttons of ele2
				if (e.getSource().equals(ele2[i])) {
					innerButton2[i] = true;
					ele2[i].setIcon(buttonPressed[i]);
					
					for (int k = 0; k < minion.size(); k++) {
						//set minions' destination
						if (minion.get(k).getEleNum() == 2 && minion.get(k).getDestination() == -1 && minion.get(k).getRemove() == 0) {
							minion.get(k).setDestination(i);
							minion.get(k).setY(620 - (75 * minion.get(k).getDestination()));
							countMinion2--;
							break;
						}
					}
					if (countMinion2 == 0) {
						for (int k = 0; k < ele2.length - 2; k++)
							ele2[k].setEnabled(false);
					}
				}

				//if press inner buttons of ele3
				if (e.getSource().equals(ele3[i])) {
					innerButton3[i] = true;
					ele3[i].setIcon(buttonPressed[i]);
					
					for (int k = 0; k < minion.size(); k++) {
						//set minions' destination
						if (minion.get(k).getEleNum() == 3 && minion.get(k).getDestination() == -1 && minion.get(k).getRemove() == 0) {
							minion.get(k).setDestination(i);
							minion.get(k).setY(620 - (75 * minion.get(k).getDestination()));
							countMinion3--;
							break;
						}
					}
					if (countMinion3 == 0) {
						for (int k = 0; k < ele3.length - 2; k++)
							ele3[k].setEnabled(false);
					}
				}

				//if press outer top buttons
				if (e.getSource() == top[i]) {
					allTop[i] = true;
					top[i].setIcon(topPressed);
				}
				//if press outer bottom buttons
				if (e.getSource() == bottom[i]) {
					allBottom[i] = true;
					bottom[i].setIcon(bottomPressed);
				}

				//if press add button
				if (e.getSource() == add[i]) {
					//produce 1 minion
					countM++;
					AddMinion addM = new AddMinion(i);

					//for timer time, move minion in front of elevator
					Timer t = new Timer();
					TimerTask m = new TimerTask() {
						int k = 0, num = 0, j = minion.get(countM).getStart(),
								index = minion.get(countM).getLinkedIndex();

						public void run() {
							if (num % 4 == 0 || num % 4 == 1) {
								centralPanel.add(minion.get(index).getButton());
								minion.get(index).getButton().setBounds(50 + k, 620 - (75 * j), 80, 80);
								minion.get(index).getButton().setIcon(minion.get(index).getImage1());
								minion.get(index).setX(50 + k);
								minion.get(index).setY(620 - (75 * j));
								num++;
							} else {
								centralPanel.add(minion.get(index).getButton());
								minion.get(index).getButton().setBounds(50 + k, 620 - (75 * j), 80, 80);
								minion.get(index).getButton().setIcon(minion.get(index).getImage2());
								minion.get(index).setX(50 + k);
								minion.get(index).setY(620 - (75 * j));
								num++;
							}
							k = k + 10;
							if (k == 450)
								this.cancel();
						}
					};

					t.schedule(m, 30, 30);
				}
			}
		}
	}
	//==================================================================================================

	//Synchronize three elevators movement
	class TotalElevator extends Thread {
		public void run() {
			while (true) {
				for (int i = 0; i < 8; i++) {
					yield();
					if (allTop[i] &&(weight1>maxweight)) // elevator1`s weight is over limit welght so ignore elevator1 and upbutton is pressed
															
					{
						if (moveupdown2==0 && moveupdown3==0)  // elevator 2 ,3 is stoped 
		            	   {
		            		   if(Math.abs(no_2location - i) < Math.abs(no_3location- i)) // find The nearest location 1,2
		                            no_2Top[i] = true;
		                         else
		                         	no_3Top[i]=true;
		                         allTop[i] = false;
		                         break;
		            	   }
						else if ((moveupdown2 == 1 && statement2 == 1 && no_2location <= i)
								|| (moveupdown3 == 1 && statement3 == 1 && no_3location <= i)) // If any elevator is move to up find upper elevator 
								
						{
							if ((moveupdown2 == 1 && statement2 == 1 && no_2location <= i)) {
								if (no_2location > no_3location)
									no_2Top[i] = true;
								else if (no_3location > no_2location)
									no_3Top[i] = true;
								allTop[i] = false;
								break;
							} if (moveupdown2 == 1 && statement2 == 1
									&& no_2location <= i) {
								no_2Top[i] = true;
								allTop[i] = false;
								break;
							} else if (moveupdown3 == 1 && statement3 == 1
									&& no_3location <= i) {
								no_3Top[i] = true;
								allTop[i] = false;
								break;
							}
						} else if ((moveupdown2 == 0 && statement2 == 0 && no_2location <= i)
								|| (moveupdown3 == 0 && statement3 == 0 && no_3location <= i)) {
							if ((moveupdown2 == 0 && statement2 == 0 && no_2location <= i)
									&& (moveupdown3 == 0 && statement3 == 0 && no_3location <= i)) {
								if (Math.abs(no_2location - i) < Math
										.abs(no_3location - i))
									no_2Top[i] = true;
								else
									no_3Top[i] = true;
								allTop[i] = false;
								break;
							}
							if (moveupdown2 == 0 && statement2 == 0
									&& no_2location <= i) {
								no_2Top[i] = true;
								allTop[i] = false;
								break;
							} else
							{
								no_3Top[i] = true;
								allTop[i] = false;
								break;
							}
						} else if ((moveupdown2 == 0 && statement2 == 0)
								|| (moveupdown3 == 0 && statement3 == 0)) {
							if ((moveupdown2 == 0 && statement2 == 0)
									&& (moveupdown3 == 0 && statement3 == 0)) {
								if (Math.abs(no_2location - i) < Math
										.abs(no_3location - i))
									no_2Top[i] = true;
								else
									no_3Top[i] = true;
								allTop[i] = false;
								break;
							}
							if (moveupdown2 == 0 && statement2 == 0) {
								no_2Top[i] = true;
								allTop[i] = false;
								break;
							} else if (moveupdown3 == 0 && statement3 == 0) {
								no_3Top[i] = true;
								allTop[i] = false;
								break;
							}
						}
					} else if (allBottom[i] &&  (weight1>maxweight) ) // levator1`s weight is over limit welght so ignore elevator1 and down button is pressed
																
					{
						if ((moveupdown2 == -1 && statement2 == 3 && no_2location >= i)
								|| (moveupdown3 == -1 && statement3 == 3 && no_3location >= i)) {
							if ((moveupdown2 == -1 && statement2 == 3 && no_2location >= i)
									&& (moveupdown3 == -1 && statement3 == 3 && no_3location >= i)) {
								if (no_2location > no_3location)
									no_2Bottom[i] = true;
								else
									no_3Bottom[i] = true;
								allBottom[i] = false;
								break;
							} else if (moveupdown2 == -1 && statement2 == 3
									&& no_2location >= i) {
								no_2Bottom[i] = true;
								allBottom[i] = false;
								break;
							} else{
								no_3Bottom[i] = true;
								allBottom[i] = false;
								break;
							}
						} else if ((moveupdown2 == 1 && statement2 == 2 && no_2location <= i)
								|| (moveupdown3 == 1 && statement3 == 2 && no_3location <= i)) {
							if ((moveupdown2 == 1 && statement2 == 2 && no_2location <= i)
									&& (moveupdown3 == 1 && statement3 == 2 && no_3location <= i)) {
								if (no_2location < no_3location)
									no_2Bottom[i] = true;
								else
									no_3Bottom[i] = true;
								allBottom[i] = false;
								break;
							} else if (moveupdown2 == 1 && statement2 == 2
									&& no_2location <= i) {
								no_2Bottom[i] = true;
								allBottom[i] = false;
								break;
							} else{
								no_3Bottom[i] = true;
								allBottom[i] = false;
								break;
							}
						} else if ((moveupdown2 == 0 && statement2 == 0 && no_2location >= i)
								|| (moveupdown3 == 0 && statement3 == 0 && no_3location >= i)) {
							if ((moveupdown2 == 0 && statement2 == 0 && no_2location >= i)
									&& (moveupdown3 == 0 && statement3 == 0 && no_3location >= i)) {
								if (Math.abs(no_2location - i) < Math
										.abs(no_1location - i))
									no_2Bottom[i] = true;
								else
									no_3Bottom[i] = true;
								allBottom[i] = false;
								break;
							}
							if (moveupdown2 == 0 && statement2 == 0
									&& no_2location >= i) {
								no_2Bottom[i] = true;
								allBottom[i] = false;
								break;
							} else if (moveupdown3 == 0 && statement3 == 0
									&& no_3location >= i) {
								no_3Bottom[i] = true;
								allBottom[i] = false;
								break;
							}
						} else if ((moveupdown2 == 0 && statement2 == 0)
								|| (moveupdown3 == 0 && statement3 == 0)) {
							if ((moveupdown2 == 0 && statement2 == 0)
									&& (moveupdown3 == 0 && statement3 == 0)) {
								if (Math.abs(no_2location - i) < Math
										.abs(no_3location - i))
									no_2Bottom[i] = true;
								else
									no_3Bottom[i] = true;
								allBottom[i] = false;
								break;
							} else if (moveupdown2 == 0 && statement2 == 0) {
								no_2Bottom[i] = true;
								allBottom[i] = false;
								break;
							} else if (moveupdown3 == 0 && statement3 == 0) {
								no_3Bottom[i] = true;
								allBottom[i] = false;
								break;
							}
						}
					}

					else if (allTop[i]&& (weight2>maxweight)) // elevator2`s weight is over limit welght so ignore elevator2 and upbutton is pressed
																
					{
						if ((moveupdown1 == 1 && statement1 == 1 && no_1location <= i)
								|| (moveupdown3 == 1 && statement3 == 1 && no_3location <= i)) {
							if ((moveupdown1 == 1 && statement1 == 1 && no_1location <= i)
									&& (moveupdown3 == 1 && statement3 == 1 && no_3location <= i)) {
								if (no_1location > no_3location)
									no_1Top[i] = true;
								else 
									no_3Top[i] = true;
								allTop[i] = false;
								break;
							} else if (moveupdown1 == 1 && statement1 == 1
									&& no_1location <= i) {
								no_1Top[i] = true;
								allTop[i] = false;
								break;
							} else {
								no_3Top[i] = true;
								allTop[i] = false;
								break;
							}
						} else if ((moveupdown1 == 0 && statement1 == 0 && no_1location <= i)
								|| (moveupdown3 == 0 && statement3 == 0 && no_3location <= i)) {
							if ((moveupdown1 == 0 && statement1 == 0 && no_1location <= i)
									&& (moveupdown3 == 0 && statement3 == 0 && no_3location <= i)) {// 1
								if (Math.abs(no_1location - i) < Math
										.abs(no_3location - i))
									no_1Top[i] = true;

								else
									no_3Top[i] = true;
								allTop[i] = false;
								break;
							} else if (moveupdown1 == 0 && statement1 == 0
									&& no_1location <= i) {
								no_1Top[i] = true;
								allTop[i] = false;
							} else {
								no_3Top[i] = true;
								allTop[i] = false;
								break;
							}
						} else if ((moveupdown1 == 0 && statement1 == 0)
								|| (moveupdown3 == 0 && statement3 == 0)) {
							if ((moveupdown1 == 0 && statement1 == 0)
									&& (moveupdown3 == 0 && statement3 == 0)) {
								if (Math.abs(no_1location - i) < Math
										.abs(no_3location - i))
									no_1Top[i] = true;

								else 
									no_3Top[i] = true;
								allTop[i] = false;
								break;
							} else if (moveupdown1 == 0 && statement1 == 0) {
								no_1Top[i] = true;
								allTop[i] = false;
								break;
							}
						} else  {
							no_3Top[i] = true;
							allTop[i] = false;
							break;
						}
					}

					else if (allBottom[i] && (weight2>maxweight))  // elevator2`s weight is over limit welght so ignore elevator2 and downbutton is pressed
					{
						if ((moveupdown1 == -1 && statement1 == 3 && no_1location >= i)
								|| (moveupdown3 == -1 && statement3 == 3 && no_3location >= i)) {
							if ((moveupdown1 == -1 && statement1 == 3 && no_1location >= i)
									&& (moveupdown3 == -1 && statement3 == 3 && no_3location >= i)) {
								if (no_1location > no_3location)
									no_1Bottom[i] = true;
								else 
									no_3Bottom[i] = true;
								allBottom[i] = false;
								break;
							} else if (moveupdown1 == -1 && statement1 == 3
									&& no_1location >= i) {
								no_1Bottom[i] = true;
								allBottom[i] = false;
								break;
							} else  {
								no_3Bottom[i] = true;
								allBottom[i] = false;
								break;
							}
							
						} else if ((moveupdown1 == 1 && statement1 == 2 && no_1location <= i)
								|| (moveupdown3 == 1 && statement3 == 2 && no_3location <= i)) {
							if ((moveupdown1 == 1 && statement1 == 2 && no_1location <= i)
									&& (moveupdown3 == 1 && statement3 == 2 && no_3location <= i)) {
								if (no_1location < no_3location)
									no_1Bottom[i] = true;
								else 
									no_3Bottom[i] = true;
								allBottom[i] = false;
								break;
							} else if (moveupdown1 == 1 && statement1 == 2
									&& no_1location <= i) {
								no_1Bottom[i] = true;
								allBottom[i] = false;
								break;
							} else  {
								no_3Bottom[i] = true;
								allBottom[i] = false;
								break;
							}
						} else if ((moveupdown1 == 0 && statement1 == 0 && no_1location >= i)
								|| (moveupdown3 == 0 && statement3 == 0 && no_3location >= i)) {
							if ((moveupdown1 == 0 && statement1 == 0 && no_1location >= i)
									&& (moveupdown3 == 0 && statement3 == 0 && no_3location >= i)) {
								if (Math.abs(no_1location - i) < Math
										.abs(no_3location - i))
									no_1Bottom[i] = true;
								else 
									no_3Bottom[i] = true;
								allBottom[i] = false;
								break;
							} else if (moveupdown1 == 0 && statement1 == 0
									&& no_1location >= i) {
								no_1Bottom[i] = true;
								allBottom[i] = false;
								break;
							} else  {
								no_3Bottom[i] = true;
								allBottom[i] = false;
								break;
							}
						} else if ((moveupdown1 == 0 && statement1 == 0)
								|| (moveupdown3 == 0 && statement3 == 0)) {
							if ((moveupdown1 == 0 && statement1 == 0)
									&& (moveupdown3 == 0 && statement3 == 0)) {
								if (Math.abs(no_1location - i) < Math
										.abs(no_3location - i))
									no_1Bottom[i] = true;
								else 
									no_3Bottom[i] = true;
								allBottom[i] = false;
								break;
							} else if (moveupdown1 == 0 && statement1 == 0) {
								no_1Bottom[i] = true;
								allBottom[i] = false;
								break;
							} else  {
								no_3Bottom[i] = true;
								allBottom[i] = false;
								break;
							}
						}
					}

					else if (allTop[i] && (weight3>maxweight))  // elevator3`s weight is over limit welght so ignore elevator3 and upbutton is pressed
																
					{
						if ((moveupdown1 == 1 && statement1 == 1 && no_1location <= i)
								|| (moveupdown2 == 1 && statement2 == 1 && no_2location <= i)) {
							if ((moveupdown1 == 1 && statement1 == 1 && no_1location <= i)
									&& (moveupdown2 == 1 && statement2 == 1 && no_2location <= i)) {
								if ( no_1location > no_2location)
									no_1Top[i] = true;
								else 
									no_2Top[i] = true;
								allTop[i] = false;
								break;
							} else if (moveupdown1 == 1 && statement1 == 1
									&& no_1location <= i) {
								no_1Top[i] = true;
								allTop[i] = false;
								break;
							} else  {
								no_2Top[i] = true;
								allTop[i] = false;
								break;
							}
						} else if ((moveupdown1 == 0 && statement1 == 0 && no_1location <= i)
								|| (moveupdown2 == 0 && statement2 == 0 && no_2location <= i)) {
							if ((moveupdown1 == 0 && statement1 == 0 && no_1location <= i)
									&& (moveupdown2 == 0 && statement2 == 0 && no_2location <= i)) {// 1
								if (Math.abs(no_1location - i) <= Math
										.abs(no_2location - i))
									no_1Top[i] = true;
								else 
									no_2Top[i] = true;

								allTop[i] = false;
								break;
							} else if (moveupdown1 == 0 && statement1 == 0
									&& no_1location <= i) {
								no_1Top[i] = true;
								allTop[i] = false;
							} else  {
								no_2Top[i] = true;
								allTop[i] = false;
								break;
							}

						} else if ((moveupdown1 == 0 && statement1 == 0)
								|| (moveupdown2 == 0 && statement2 == 0)) {
							if ((moveupdown1 == 0 && statement1 == 0)
									&& (moveupdown2 == 0 && statement2 == 0)) {
								if (Math.abs(no_1location - i) < Math
										.abs(no_2location - i))
									no_1Top[i] = true;
								else 
									no_2Top[i] = true;

								allTop[i] = false;
								break;
							} else if (moveupdown1 == 0 && statement1 == 0) {
								no_1Top[i] = true;
								allTop[i] = false;
								break;
							} else  {
								no_2Top[i] = true;
								allTop[i] = false;
								break;
							}
						}
					}

					else if (allBottom[i] && (weight3>maxweight))  // elevator3`s weight is over limit welght so ignore elevator3 and downbutton is pressed
					{
						if ((moveupdown1 == -1 && statement1 == 3 && no_1location >= i)
								|| (moveupdown2 == -1 && statement2 == 3 && no_2location >= i)) {
							if ((moveupdown1 == -1 && statement1 == 3 && no_1location >= i)
									&& (moveupdown2 == -1 && statement2 == 3 && no_2location >= i)) {
								if (no_1location > no_2location)
									no_1Bottom[i] = true;
								else
									no_2Bottom[i] = true;

								allBottom[i] = false;
								break;
							} else if (moveupdown1 == -1 && statement1 == 3
									&& no_1location >= i) {
								no_1Bottom[i] = true;
								allBottom[i] = false;
								break;
							} else  {
								no_2Bottom[i] = true;
								allBottom[i] = false;
								break;
							}

						} else if ((moveupdown1 == 1 && statement1 == 2 && no_1location <= i)
								|| (moveupdown2 == 1 && statement2 == 2 && no_2location <= i)) {
							if ((moveupdown1 == 1 && statement1 == 2 && no_1location <= i)
									&& (moveupdown2 == 1 && statement2 == 2 && no_2location <= i)) {
								if (no_1location < no_2location)
									no_1Bottom[i] = true;
								else 
									no_2Bottom[i] = true;

								allBottom[i] = false;
								break;
							} else if (moveupdown1 == 1 && statement1 == 2
									&& no_1location <= i) {
								no_1Bottom[i] = true;
								allBottom[i] = false;
								break;
							} else  {
								no_2Bottom[i] = true;
								allBottom[i] = false;
								break;
							}

						} else if ((moveupdown1 == 0 && statement1 == 0 && no_1location >= i)
								|| (moveupdown2 == 0 && statement2 == 0 && no_2location >= i)) {
							if ((moveupdown1 == 0 && statement1 == 0 && no_1location >= i)
									&& (moveupdown2 == 0 && statement2 == 0 && no_2location >= i)) {
								if (Math.abs(no_1location - i) < Math
										.abs(no_2location - i))
									no_1Bottom[i] = true;
								else 
									no_2Bottom[i] = true;
								allBottom[i] = false;
								break;
							} else if (moveupdown1 == 0 && statement1 == 0
									&& no_1location >= i) {
								no_1Bottom[i] = true;
								allBottom[i] = false;
								break;
							} else  {
								no_2Bottom[i] = true;
								allBottom[i] = false;
								break;
							}

						} else if ((moveupdown1 == 0 && statement1 == 0)
								|| (moveupdown2 == 0 && statement2 == 0)) {
							if ((moveupdown1 == 0 && statement1 == 0)
									&& (moveupdown2 == 0 && statement2 == 0)) {
								if (Math.abs(no_1location - i) < Math
										.abs(no_2location - i))
									no_1Bottom[i] = true;
								else 
									no_2Bottom[i] = true;
								allBottom[i] = false;
								break;
							} else if (moveupdown1 == 0 && statement1 == 0) {
								no_1Bottom[i] = true;
								allBottom[i] = false;
								break;
							} else {
								no_2Bottom[i] = true;
								allBottom[i] = false;
								break;
							}
						}
					} else if (allTop[i] && weight1 > maxweight
							&& weight2 > maxweight)// elevator1,2`s weight is over limit welght so ignore elevator1,2 and upbutton is pressed
					{
						allTop[i] = false;
						no_3Top[i] = true;
					} else if (allTop[i] && weight3 > maxweight
							&& weight1 > maxweight)  // elevator1,3`s weight is over limit welght so ignore elevator1,3 and upbutton is pressed
					{
						allTop[i] = false;
						no_2Top[i] = true;
					} else if (allTop[i] && weight3 > maxweight
							&& weight2 > maxweight) //  // elevator2,3`s weight is over limit welght so ignore elevator2 and upbutton is pressed
					{
						allTop[i] = false;
						no_1Top[i] = true;
					}
					
					else if (allBottom[i] && weight1 > maxweight
							&& weight2 > maxweight)// elevator1,2`s weight is over limit welght so ignore elevator1,2 and downbutton is pressed
					{
						allBottom[i] = false;
						no_3Bottom[i] = true;
					} else if (allBottom[i] && weight3 > maxweight
							&& weight1 > maxweight)  // elevator1,3`s weight is over limit welght so ignore elevator1,3 and downbutton is pressed
					{
						allBottom[i] = false;
						no_2Bottom[i] = true;
					} else if (allBottom[i] && weight3 > maxweight
							&& weight2 > maxweight) //  // elevator2,3`s weight is over limit welght so ignore elevator2 and downbutton is pressed
					{
						allBottom[i] = false;
						no_1Bottom[i] = true;
					}

					else if (allTop[i]) // if pressed up button
					{
						if (moveupdown1 == 0 && moveupdown2 == 0
								&& moveupdown3 == 0) // if all elevator stop find closest elevator's location
						{
							if (no_1location == no_2location
									&& no_1location == no_3location)
								no_1Top[i] = true;
							else if (Math.abs(no_1location - i) <= Math
									.abs(no_2location - i)
									&& Math.abs(no_1location - i) <= Math
											.abs(no_3location - i))
								no_1Top[i] = true;
							else if (Math.abs(no_2location - i) < Math
									.abs(no_1location - i)
									&& Math.abs(no_2location - i) <= Math
											.abs(no_3location - i))
								no_2Top[i] = true;
							else if (Math.abs(no_3location - i) < Math
									.abs(no_2location - i)
									&& Math.abs(no_3location - i) < Math
											.abs(no_1location - i))
								no_3Top[i] = true;
							allTop[i] = false;
							break;
						} else if ((moveupdown1 == 1 && statement1 == 1 && no_1location <= i)
								|| (moveupdown2 == 1 && statement2 == 1 && no_2location <= i)
								|| (moveupdown3 == 1 && statement3 == 1 && no_3location <= i)) 
						
						{
							if ((moveupdown1 == 1 && statement1 == 1 && no_1location <= i)
									&& (moveupdown2 == 1 && statement2 == 1 && no_2location <= i)
									&& (moveupdown3 == 1 && statement3 == 1 && no_3location <= i)) {
								if (no_1location > no_2location
										&& no_1location > no_3location)
									no_1Top[i] = true;
								else if (no_2location > no_3location
										&& no_2location > no_1location)
									no_2Top[i] = true;
								else if (no_3location > no_2location
										&& no_3location > no_1location)
									no_3Top[i] = true;
								allTop[i] = false;
								break;
							} else if (moveupdown1 == 1 && statement1 == 1
									&& no_1location <= i) {
								no_1Top[i] = true;
								allTop[i] = false;
								break;
							} else if (moveupdown2 == 1 && statement2 == 1
									&& no_2location <= i) {
								no_2Top[i] = true;
								allTop[i] = false;
								break;
							} else if (moveupdown3 == 1 && statement3 == 1
									&& no_3location <= i) {
								no_3Top[i] = true;
								allTop[i] = false;
								break;
							}
						} else if ((moveupdown1 == 0 && statement1 == 0 && no_1location <= i)
								|| (moveupdown2 == 0 && statement2 == 0 && no_2location <= i)
								|| (moveupdown3 == 0 && statement3 == 0 && no_3location <= i))
						{ 
							if ((moveupdown1 == 0 && statement1 == 0 && no_1location <= i)
									&& (moveupdown2 == 0 && statement2 == 0 && no_2location <= i)
									&& (moveupdown3 == 0 && statement3 == 0 && no_3location <= i)) {// 1
								if (Math.abs(no_1location - i) < Math
										.abs(no_2location - i)
										&& Math.abs(no_1location - i) < Math
												.abs(no_3location - i))
									no_1Top[i] = true;
								else if (Math.abs(no_2location - i) < Math
										.abs(no_1location - i)
										&& Math.abs(no_2location - i) < Math
												.abs(no_3location - i))
									no_2Top[i] = true;
								else if (Math.abs(no_3location - i) < Math
										.abs(no_2location - i)
										&& Math.abs(no_3location - i) < Math
												.abs(no_1location - i))
									no_3Top[i] = true;
								allTop[i] = false;
								break;
							} else if (moveupdown1 == 0 && statement1 == 0
									&& no_1location <= i) {
								no_1Top[i] = true;
								allTop[i] = false;
								break;
							} else if (moveupdown2 == 0 && statement2 == 0
									&& no_2location <= i) {
								no_2Top[i] = true;
								allTop[i] = false;
								break;
							} else if (moveupdown3 == 0 && statement3 == 0
									&& no_3location <= i) {
								no_3Top[i] = true;
								allTop[i] = false;
								break;
							}
						} else if ((moveupdown1 == 0 && statement1 == 0)
								|| (moveupdown2 == 0 && statement2 == 0)
								|| (moveupdown3 == 0 && statement3 == 0)) {
							if ((moveupdown1 == 0 && statement1 == 0)
									&& (moveupdown2 == 0 && statement2 == 0)
									&& (moveupdown3 == 0 && statement3 == 0)) {
								if (Math.abs(no_1location - i) < Math
										.abs(no_2location - i)
										&& Math.abs(no_1location - i) < Math
												.abs(no_3location - i))
									no_1Top[i] = true;
								else if (Math.abs(no_2location - i) < Math
										.abs(no_1location - i)
										&& Math.abs(no_2location - i) < Math
												.abs(no_3location - i))
									no_2Top[i] = true;
								else if (Math.abs(no_3location - i) < Math
										.abs(no_1location - i)
										&& Math.abs(no_3location - i) < Math
												.abs(no_2location - i))
									no_3Top[i] = true;
								allTop[i] = false;
								break;
							} else if (moveupdown1 == 0 && statement1 == 0) {
								no_1Top[i] = true;
								allTop[i] = false;
								break;
							} else if (moveupdown2 == 0 && statement2 == 0) {
								no_2Top[i] = true;
								allTop[i] = false;
								break;
							} else if (moveupdown3 == 0 && statement3 == 0) {
								no_3Top[i] = true;
								allTop[i] = false;
								break;
							}
						}
						break;
					}

					else if (allBottom[i]) {
						if (moveupdown1 == 0 && moveupdown2 == 0
								&& moveupdown3 == 0) {
							if (no_1location == no_2location
									&& no_1location == no_3location)
								no_1Bottom[i] = true;
							else if (Math.abs(no_1location - i) <= Math
									.abs(no_2location - i)
									&& Math.abs(no_1location - i) <= Math
											.abs(no_3location - i))
								no_1Bottom[i] = true;
							else if (Math.abs(no_2location - i) < Math
									.abs(no_1location - i)
									&& Math.abs(no_2location - i) <= Math
											.abs(no_3location - i))
								no_2Bottom[i] = true;
							else if (Math.abs(no_3location - i) < Math
									.abs(no_2location - i)
									&& Math.abs(no_3location - i) < Math
											.abs(no_1location - i))
								no_3Bottom[i] = true;
							allBottom[i] = false;
							break;
						}
						else if (moveupdown1 == 0 && moveupdown2 == 0) {
							if (Math.abs(no_1location - i) <= Math
									.abs(no_2location - i))
								no_1Bottom[i] = true;
							else
								no_2Bottom[i] = true;
							allBottom[i] = false;
							break;
						} else if (moveupdown1 == 0 && moveupdown3 == 0) {
							if (Math.abs(no_1location - i) <= Math
									.abs(no_3location - i))
								no_1Bottom[i] = true;
							else
								no_3Bottom[i] = true;
							allBottom[i] = false;
							break;
						} else if (moveupdown3 == 0 && moveupdown2 == 0) {
							if (Math.abs(no_3location - i) <= Math
									.abs(no_2location - i))
								no_3Bottom[i] = true;
							else
								no_2Bottom[i] = true;
							allBottom[i] = false;
							break;
						} else if ((moveupdown1 == -1 && statement1 == 3 && no_1location >= i)
								|| (moveupdown2 == -1 && statement2 == 3 && no_2location >= i)
								|| (moveupdown3 == -1 && statement3 == 3 && no_3location >= i)) {
							if ((moveupdown1 == -1 && statement1 == 3 && no_1location >= i)
									&& (moveupdown2 == -1 && statement2 == 3 && no_2location >= i)
									&& (moveupdown3 == -1 && statement3 == 3 && no_3location >= i)) {
								if (no_1location > no_2location
										&& no_1location > no_3location)
									no_1Bottom[i] = true;
								else if (no_2location > no_1location
										&& no_2location > no_3location)
									no_2Bottom[i] = true;
								else if (no_3location > no_1location
										&& no_3location > no_2location)
									no_3Bottom[i] = true;
								allBottom[i] = false;
								break;
							} else if (moveupdown1 == -1 && statement1 == 3
									&& no_1location >= i) {
								no_1Bottom[i] = true;
								allBottom[i] = false;
								break;
							} else if (moveupdown2 == -1 && statement2 == 3
									&& no_2location >= i) {
								no_2Bottom[i] = true;
								allBottom[i] = false;
								break;
							} else if (moveupdown3 == -1 && statement3 == 3
									&& no_3location >= i) {
								no_3Bottom[i] = true;
								allBottom[i] = false;
								break;
							}
							 
						} else if ((moveupdown1 == 1 && statement1 == 2 && no_1location <= i)
								|| (moveupdown2 == 1 && statement2 == 2 && no_2location <= i)
								|| (moveupdown3 == 1 && statement3 == 2 && no_3location <= i)) {
							if ((moveupdown1 == 1 && statement1 == 2 && no_1location <= i)
									&& (moveupdown2 == 1 && statement2 == 2 && no_2location <= i)
									&& (moveupdown3 == 1 && statement3 == 2 && no_3location <= i)) {
								if (no_1location < no_2location
										&& no_1location < no_3location)
									no_1Bottom[i] = true;
								else if (no_2location < no_1location
										&& no_2location < no_3location)
									no_2Bottom[i] = true;
								else if (no_3location < no_1location
										&& no_3location < no_2location)
									no_3Bottom[i] = true;
								allBottom[i] = false;
								break;
							} else if (moveupdown1 == 1 && statement1 == 2
									&& no_1location <= i && moveupdown2 == 1
									&& statement2 == 2 && no_2location <= i) {
								if (Math.abs(no_1location - i) < Math
										.abs(no_2location - i)) {
									no_1Bottom[i] = true;
									allBottom[i] = false;
									break;
								} else {
									no_2Bottom[i] = true;
									allBottom[i] = false;
									break;
								}
							} else if (moveupdown1 == 1 && statement1 == 2
									&& no_1location <= i && moveupdown3 == 1
									&& statement3 == 2 && no_3location <= i) {
								if (Math.abs(no_1location - i) < Math
										.abs(no_3location - i)) {
									no_1Bottom[i] = true;
									allBottom[i] = false;
									break;
								} else {
									no_3Bottom[i] = true;
									allBottom[i] = false;
									break;
								}
							} else if (moveupdown3 == 1 && statement3 == 2
									&& no_3location <= i && moveupdown2 == 1
									&& statement2 == 2 && no_2location <= i) {
								if (Math.abs(no_3location - i) < Math
										.abs(no_2location - i)) {
									no_3Bottom[i] = true;
									allBottom[i] = false;
									break;
								} else {
									no_2Bottom[i] = true;
									allBottom[i] = false;
									break;
								}
							}

							else if (moveupdown1 == 1 && statement1 == 2
									&& no_1location <= i) {
								no_1Bottom[i] = true;
								allBottom[i] = false;
								break;
							} else if (moveupdown2 == 1 && statement2 == 2
									&& no_2location <= i) {
								no_2Bottom[i] = true;
								allBottom[i] = false;
								break;
							} else if (moveupdown3 == 1 && statement3 == 2
									&& no_3location <= i) {
								no_3Bottom[i] = true;
								allBottom[i] = false;
								break;
							}
						} else if ((moveupdown1 == 0 && statement1 == 0 && no_1location >= i)
								|| (moveupdown2 == 0 && statement2 == 0 && no_2location >= i)
								|| (moveupdown3 == 0 && statement3 == 0 && no_3location >= i)) {
							if ((moveupdown1 == 0 && statement1 == 0 && no_1location >= i)
									&& (moveupdown2 == 0 && statement2 == 0 && no_2location >= i)
									&& (moveupdown3 == 0 && statement3 == 0 && no_3location >= i)) {
								if (Math.abs(no_1location - i) < Math
										.abs(no_2location - i)
										&& Math.abs(no_1location - i) < Math
												.abs(no_3location - i))
									no_1Bottom[i] = true;
								else if (Math.abs(no_2location - i) < Math
										.abs(no_1location - i)
										&& Math.abs(no_2location - i) < Math
												.abs(no_3location - i))
									no_2Bottom[i] = true;
								else if (Math.abs(no_3location - i) < Math
										.abs(no_1location - i)
										&& Math.abs(no_3location - i) < Math
												.abs(no_2location - i))
									no_3Bottom[i] = true;
								allBottom[i] = false;
								break;
							} else if (moveupdown1 == 0 && statement1 == 0
									&& no_1location >= i) {
								no_1Bottom[i] = true;
								allBottom[i] = false;
								break;
							} else if (moveupdown2 == 0 && statement2 == 0
									&& no_2location >= i) {
								no_2Bottom[i] = true;
								allBottom[i] = false;
								break;
							} else if (moveupdown3 == 0 && statement3 == 0
									&& no_3location >= i) {
								no_3Bottom[i] = true;
								allBottom[i] = false;
								break;
							}
						} else if ((moveupdown1 == 0 && statement1 == 0)
								|| (moveupdown2 == 0 && statement2 == 0)
								|| (moveupdown3 == 0 && statement3 == 0)) {
							if ((moveupdown1 == 0 && statement1 == 0)
									&& (moveupdown2 == 0 && statement2 == 0)
									&& (moveupdown3 == 0 && statement3 == 0)) {
								if (Math.abs(no_1location - i) < Math
										.abs(no_2location - i)
										&& Math.abs(no_1location - i) < Math
												.abs(no_3location - i))
									no_1Bottom[i] = true;
								else if (Math.abs(no_2location - i) < Math
										.abs(no_3location - i)
										&& Math.abs(no_2location - i) < Math
												.abs(no_1location - i))
									no_2Bottom[i] = true;
								else if (Math.abs(no_3location - i) < Math
										.abs(no_2location - i)
										&& Math.abs(no_3location - i) < Math
												.abs(no_1location - i))
									no_3Bottom[i] = true;
								allBottom[i] = false;
								break;
							} else if (moveupdown1 == 0 && statement1 == 0) {
								no_1Bottom[i] = true;
								allBottom[i] = false;
								break;
							} else if (moveupdown2 == 0 && statement2 == 0) {
								no_2Bottom[i] = true;
								allBottom[i] = false;
								break;
							} else if (moveupdown3 == 0 && statement3 == 0) {
								no_3Bottom[i] = true;
								allBottom[i] = false;
								break;
							}
						}
					}
				}
				yield();
			}
		}
	}

	//===================================================================================
/*
 * This class sector is controller of elevator 1, 2, 3.
 * Divide 5 state and each case controls each situation. 
 * */
	class ElevatorNo_1 extends Thread {
		ElevatorMovement1 move_1 = new ElevatorMovement1();
		ElevatorDistribution1 move_1_s = new ElevatorDistribution1();
		ElevatorNo_1Moving ev_1 = new ElevatorNo_1Moving();

		public void run() {
			GO: while (true) {
				switch (statement1) {
				case 0: // Not moving Elevator state
					updown[0].setIcon(upImg);
					updown[1].setIcon(downImg);
					yield();
					int max = 0,
					index = 0;
					for (int j = 0; j < 8; j++) {
						if (cache_d[j] > max) {
							max = cache_d[j];
							index = j;
						}
					}
					if (no_1location != index && max > 5) {
						final1 = index;
						if (index > no_1location) {
							try {
								Thread.sleep(3000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							no_1Top[index] = true;
							moveupdown1 = 1;
						} else if (index < no_1location) {
							try {
								Thread.sleep(3000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							no_1Bottom[index] = true;
							moveupdown1 = -1;
						} else
							moveupdown1 = 0;
						move_1_s.move();
					}
					yield();
					break;

				case 1:// Elevator move up and check top button
					updown[0].setIcon(upCheckedImg);
					updown[1].setIcon(downImg);
					for (int i = no_1location; i <= final1; i++, no_1location++) {
						yield();
						 //if the elevator is lower than present location,
						 
						for (int y = 0; y < no_1location; y++) {        
							if (no_1Top[y]) {                           
								allTop[y] = true;
								no_1Top[y] = false;
								break;
							}
						}
						yield();     // the 2nd elevator is moved.
						
						// If elevator is moving to final floor, but check top button,
						for (int y = no_1location; y <= final1; y++) {
							if (no_1Top[y] || innerButton1[y]) { 
								check_1 = y; // check the floor
								break;
							}
						}
						yield();
						move_1_s.move(); 
						ev_1.work(); //Call ElevatorNo_1Moving
						yield();
						move_1_s.move(); 
					}
					no_1location--;  
					break;

				case 2: //Elevator move up and check bottom button.
					updown[0].setIcon(upCheckedImg);
					updown[1].setIcon(downImg);
					check_1 = final1; 
					for (int i = no_1location; i <= final1; i++, no_1location++) {
						yield();
						
						for (int y = no_1location; y < 8; y++) { 
							if (no_1Top[y]) { 
								statement1 = 1; 
								continue GO;
							} else if (no_1Bottom[y] && y < no_1location) {
								allBottom[y] = true;
								no_1Bottom[y] = false;
								break;
							}
						}
						check_1 = final1;
						yield();
						move_1_s.move();
						ev_1.work(); // Call ElevatorNo_1Moving
						yield();
						move_1_s.move();
					}
					no_1location--; 
					break;
				case 3:      //Elevator move down and check up button.
					updown[1].setIcon(downCheckedImg);
					updown[0].setIcon(upImg);
					for (int i = no_1location; i >= final1; i--, no_1location--) {
						yield();
						
						//If the check is higher than present location,
						
						for (int y = 7; y > no_1location; y--) { 
							if (no_1Bottom[y]) { 
								allBottom[y] = true;
								no_1Bottom[y] = false;
								break;
							}
						}
						yield(); //moving 2 Elevator
						for (int y = no_1location; y >= final1; y--) {
							if (no_1Bottom[y] || innerButton1[y]) { 
								check_1 = y; 
								break;
							}
						}
						yield();
						move_1_s.move();
						ev_1.work(); // Call ElevatorNo_1Moving
						yield();
						move_1_s.move();
					}
					no_1location++;
					break;

				case 4:     //Elevator move down and check down button.
					if (no_1location == 0) {
						updown[0].setIcon(upCheckedImg);
						updown[1].setIcon(downImg);
					} else {
						updown[1].setIcon(downCheckedImg);
						updown[0].setIcon(upImg);
					}
					check_1 = final1;
					for (int i = no_1location; i >= final1; i--, no_1location--) {
						yield();
						//If not Bottom button and top button is checked in under the location
						for (int y = no_1location; y > 10; y--) { 
							if (no_1Bottom[y]) { 
								statement1 = 3; 
								continue GO; // Go to bottom button processing
							} else if (no_1Top[y] && y > no_1location) {
								allTop[y] = true;
								no_1Top[y] = false;
								break;
							}
						}
						check_1 = final1;
						yield();
						move_1_s.move();
						ev_1.work(); //Call ElevatorNo_1Moving
						yield();
						move_1_s.move();
					}
					no_1location++;
					break;
				}
				statement1 = 0;
				move_1.move();
				yield();
			}
		}
	}//ElevatorNO_1 finish

	// decide elevator1 to move up or move down or stop
		class ElevatorMovement1 {
			ElevatorDistribution1 move_1_s = new ElevatorDistribution1();

			public void move() {
				for (int i = 0; i < 8; i++) {
					if (no_1Bottom[i] || no_1Top[i] || innerButton1[i]) {
						break;
					} else {
						moveupdown1 = 0;
					}
				}
				for (int i = no_1location; i < 8; i++) {
					if (no_1Top[i] || no_1Bottom[i] || innerButton1[i]) {
						moveupdown1 = 1;
						break;
					}
				}
				for (int i = no_1location; i > -1; i--) {
					if (no_1Top[i] || no_1Bottom[i] || innerButton1[i]) {
						moveupdown1 = -1;
						break;
					}
				}
				move_1_s.move();
			}
		}//ElevatorMovement1 finish
		
		//================================================================================
		class ElevatorDistribution1 {
			public void move() {
				switch (moveupdown1) {
				case 1:
					for (int i = 7; i >= no_1location; i--) {
						if (no_1Top[i] || innerButton1[i]) {
							statement1 = 1;
							final1 = i;
							return; // IF elevator1 move up and pressed inner button statement =1 
						}
						if (no_1Bottom[i]) {
							statement1 = 2;
							final1 = i;
							return; // if elevator move up and pressed down button
						}
					}
					break;

				case -1:// if elevator1 move down floor
					for (int i = 0; i <= no_1location; i++) {
						if (no_1Bottom[i] || innerButton1[i]) {
							statement1 = 3;
							final1 = i;
							return; // if pressed down button and inner button
						}
						if (no_1Top[i]) {
							statement1 = 4;
							final1 = i;
							return; // if pressed up button
						}
					}
					break;
				}
				moveupdown1 = 0;
			}
		}//ElevatorDistribution1 finish
	// ///////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		// action for moving elevator1
					class ElevatorNo_1Moving {
						public void work() {
							
							
							System.out.println("NO1)> current ( " + no_1location + ") destination (" + check_1 + ") :" + statement1);
							System.out.println(final1);
							//showing elevator location in text 
							tmp1 = "" + (no_1location + 1) + " F";
							cp[0].setLabel(tmp1);
							Wbutton[0].setLabel(weight1+" KG");
							if(moveupdown1!=0){
								Timer tt = new Timer();
								TimerTask mm = new TimerTask() {
									int currentLocation = 540 - (75 * no_1location);
									int floorVariable = 75 * (check_1 - no_1location);
									int fv=0;
									public void run() {
										
										if(floorVariable > 0 && fv!=75){
											no1.setBounds(3, currentLocation - fv, 70, 60);
											fv++;
										}
										else if(floorVariable < 0 && fv!=-75){
											no1.setBounds(3, currentLocation - fv, 70, 60);
											fv--;
										}
										else if (Math.abs(fv)==75){
											this.cancel();
										}
									}
								};
								tt.schedule(mm, 0, 13);
							}
							
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							
							if (no_1location == check_1) {
								innerButton1[check_1] = false;
								if (statement1 == 1 || statement1 == 4) {
									no_1Top[check_1] = false;
									top[no_1location].setIcon(buttonNormal[check_3]);
								} else if (statement1 == 2 || statement1 == 3) {
									no_1Bottom[check_1] = false;
									bottom[no_1location].setIcon(buttonNormal[check_3]);
								}

								ele1[check_1].setIcon(buttonNormal[check_1]);
								top[no_1location].setIcon(topNormal);
								bottom[no_1location].setIcon(bottomNormal);
								ele1[8].setIcon(buttonPressed[8]);

								try {
									Thread.sleep(1000);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								
								//open elevator door
								ele1[8].setIcon(buttonNormal[8]);
								no1.setIcon(opendoor);
								enterdoor[no_1location].setIcon(enterPressed);
								ele1[9].setIcon(buttonPressed[9]);
								
								for (int k = 0; k < minion.size(); k++) {
									// come in to elevator
									if (minion.get(k).getStart() == no_1location
											&& minion.get(k).getRemove() == 0 && weight1+minion.get(k).getweight()<maxweight) {
										minion.get(k).getButton().setVisible(false);
										minion.get(k).setEleNum(1);
										weight1=weight1+minion.get(k).getweight();
										System.out.println(weight1);
										Wbutton[0].setLabel(weight1+" KG ");
										cache_s[no_1location]++;

										countMinion1++;
										for (int k2 = 0; k2 < ele1.length; k2++)
											ele1[k2].setEnabled(true);
									}
									// come out of elevator
									if (minion.get(k).getDestination() == no_1location
											&& minion.get(k).getRemove() == 0
											&& minion.get(k).getEleNum() == 1) {
										try {
											Thread.sleep(300);
										} catch (InterruptedException e) {
											e.printStackTrace();
										}
										elein1 = k;
										minion.get(k).getButton().setVisible(true);
										weight1=weight1-minion.get(k).getweight();
										Wbutton[0].setLabel(weight1+" KG ");
										cache_d[no_1location]++;

										//moving minions out of path using timer
										Timer t = new Timer();
										TimerTask m = new TimerTask() {
											int p = 0, num = 0, index = minion.get(elein1).getLinkedIndex();

											public void run() {
												if (num % 4 == 0 || num % 4 == 1) {
													minion.get(index).getButton().setBounds(minion.get(index).getX() - p, minion.get(index).getY(), 80, 80);
													minion.get(index).getButton().setIcon(minion.get(index).getImage1());
													centralPanel.add(minion.get(index).getButton());
													num++;
												} else {
													minion.get(index).getButton().setBounds(minion.get(index).getX() - p, minion.get(index).getY(), 80, 80);
													minion.get(index).getButton().setIcon(minion.get(index).getImage2());
													centralPanel.add(minion.get(index).getButton());
													num++;
												}
												
												p = p + 10;
												
												if (p == 450) {
													if(minion.get(index).getDestination()==1 || minion.get(index).getDestination()==6){
														add[minion.get(index).getDestination()].setIcon(addPressed2);
													}
													else{
														add[minion.get(index).getDestination()].setIcon(addPressed1);
													}
													
													minion.get(index).getButton().setVisible(false);
													
													try {
														Thread.sleep(200);
													} catch (InterruptedException e) {
														e.printStackTrace();
													}

													if(minion.get(index).getDestination()==1 || minion.get(index).getDestination()==6){
														add[minion.get(index).getDestination()].setIcon(addNormal2);
													}
													else{
														add[minion.get(index).getDestination()].setIcon(addNormal1);
													}
													
													minion.get(index).setRemove(1);
													this.cancel();
												}
											}
										};

										t.schedule(m, 30, 30);							
									}
								}
								
								try {
									Thread.sleep(1000);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}

								//close elevator door
								ele1[9].setIcon(buttonNormal[9]);
								top[no_1location].setIcon(topNormal);
								bottom[no_1location].setIcon(bottomNormal);
								no1.setIcon(closedoor);
								enterdoor[no_1location].setIcon(enterNormal);
								ele1[check_1].setIcon(buttonNormal[check_1]);
								
								if (statement1 == 2 || statement1 == 3)
									top[check_1].setIcon(topNormal);
								else
									bottom[check_1].setIcon(bottomNormal);
							}
						}
					}

				//==========================================================================================
	class ElevatorNo_2 extends Thread {
		ElevatorMovement2 move_2 = new ElevatorMovement2();
		ElevatorDistribution2 move_2_s = new ElevatorDistribution2();
		ElevatorNo_2Moving ev_2 = new ElevatorNo_2Moving();

		public void run() {
			GO: while (true) {
				switch (statement2) {
				case 0:    // Not moving Elevator state
					updown[2].setIcon(upImg);
					updown[3].setIcon(downImg);
					yield();
					int max = 0,
					index = 0,
					max1 = 0;
					for (int j = 0; j < 8; j++) {
						if (cache_d[j] > max)
							max = cache_d[j];
					}
					for (int j = 0; j < 8; j++) {
						if (cache_d[j] != max && cache_d[j] > max1) {
							max1 = cache_d[j];
							index = j;
						}
					} //Find Second the Most visted floor 
					if (no_2location != index && max1 > 5) {
						final2 = index;
						if (index > no_2location) {
							try {
								Thread.sleep(3000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							no_2Top[index] = true;
							moveupdown2 = 1;
						} else if (index < no_2location) {
							try {
								Thread.sleep(3000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							no_2Bottom[index] = true;
							moveupdown2 = -1;
						} else
							moveupdown2 = 0;
						move_2_s.move();
						
					}
					break;

				case 1:   // Elevator move up and check top button
					updown[2].setIcon(upCheckedImg);
					updown[3].setIcon(downImg);
					for (int i = no_2location; i <= final2; i++, no_2location++) {
						yield();
						//if the elevator is lower than present location,
						for (int y = 0; y < no_2location; y++) { 
							if (no_2Top[y]) { 
								allTop[y] = true;
								no_2Top[y] = false;
								break;
							}
						}
						yield(); // Another elevator is moved.
						for (int y = no_2location; y <= final2; y++) {
							if (no_2Top[y] || innerButton2[y]) { 
								check_2 = y;
								break;
							}
						}
						yield();
						move_2_s.move();
						ev_2.work(); // Call ElevatorNo_2Moving
						yield();
						move_2_s.move();
					}
					no_2location--; 
					break;

				case 2:   //Elevator move up and check bottom button.
					updown[2].setIcon(upCheckedImg);
					updown[3].setIcon(downImg);
					check_2 = final2;
					for (int i = no_2location; i <= final2; i++, no_2location++) {
						yield();
						for (int y = no_2location; y < 8; y++) { 
							if (no_2Top[y] || innerButton2[y]) { 
								statement2 = 1; 
								continue GO; 
							} else if (no_2Bottom[y] && y < no_2location) {
								allBottom[y] = true;
								no_2Bottom[y] = false;
								break;
							}
						}
						check_2 = final2;
						yield();
						move_2_s.move();
						ev_2.work(); // Call ElevatorNo_2Moving
						yield();
						move_2_s.move();
					}
					no_2location--; 
					break;

				case 3:   //Elevator move down and check up button.
					updown[3].setIcon(downCheckedImg);
					updown[2].setIcon(upImg);
					for (int i = no_2location; i >= final2; i--, no_2location--) {
						yield();
						
						//If the check is higher than present location,
						for (int y = 7; y > no_2location; y--) { 
							if (no_2Bottom[y]) { 
								allBottom[y] = true;
								no_2Bottom[y] = false;
								break;
							}
						}
						yield();    //moving another Elevator
						for (int y = no_2location; y >= final2; y--) {
							if (no_2Bottom[y] || innerButton2[y]) { 
								check_2 = y; 
								break;
							}
						}
						yield();
						move_2_s.move();
						ev_2.work(); // Call ElevatorNo_2Moving
						yield();
						move_2_s.move();
					}
					no_2location++; 
					break;

				case 4:    //Elevator move down and check down button.
					if (no_2location == 0) {
						updown[2].setIcon(upCheckedImg);
						updown[3].setIcon(downImg);
					} else {
						updown[3].setIcon(downCheckedImg);
						updown[2].setIcon(upImg);
					}
					check_2 = final2;
					for (int i = no_2location; i >= final2; i--, no_2location--) {
						yield();
						for (int y = no_2location; y > 8; y--) { 
							if (no_2Bottom[y] || innerButton2[y]) { 
								statement2 = 3; 
								continue GO; 
							} else if (no_2Top[y] && y > no_2location) {
								allTop[y] = true;
								no_2Top[y] = false;
								break;
							}
						}
						check_2 = final2;
						yield();
						move_2_s.move();
						ev_2.work(); // Call ElevatorNo_2Moving
						yield();
						move_2_s.move();
					}
					no_2location++;
					break;
				}
				statement2 = 0;
				move_2.move();
				yield();
			}
		}
	}//ElevatorNO_2 finish
	// decide elevator2 to move up or move down or stop
		class ElevatorMovement2 {
			ElevatorDistribution2 move_2_s = new ElevatorDistribution2();

			public void move() {
				for (int i = 0; i < 8; i++) {
					if (no_2Bottom[i] || no_2Top[i] || innerButton2[i]) {
						break;
					} else {
						moveupdown2 = 0;
					}
				}
				for (int i = no_2location; i < 8; i++) {
					if (no_2Top[i] || no_2Bottom[i] || innerButton2[i]) {
						moveupdown2 = 1;
						break;
					}
				}
				for (int i = no_2location; i > -1; i--) {
					if (no_2Top[i] || no_2Bottom[i] || innerButton2[i]) {
						moveupdown2 = -1;
						break;
					}
				}
				move_2_s.move();
			}
		}
		
		class ElevatorDistribution2 //same as ElevatorDistribution1
		{
			public void move() {
				switch (moveupdown2) {
				case 1:
					for (int i = 7; i >= no_2location; i--) {
						if (no_2Top[i] || innerButton2[i]) {
							statement2 = 1;
							final2 = i;
							return;
						}
						if (no_2Bottom[i]) {
							statement2 = 2;
							final2 = i;
							return;
						}
					}
					break;

				case -1:
					for (int i = 0; i <= no_2location; i++) {
						if (no_2Bottom[i] || innerButton2[i]) {
							statement2 = 3;
							final2 = i;
							return;
						}
						if (no_2Top[i]) {
							statement2 = 4;
							final2 = i;
							return;
						}
					}
					break;
				}
				moveupdown2 = 0;
			} 
		}

		//=========================================================================================
		// action for moving elevator2
					class ElevatorNo_2Moving {
						public void work() {
							moving moveElevator2 = new moving(no2, no_2location, check_2,final2, statement2);
							System.out.println("NO2)> current ( " + no_2location + ") destination (" + check_2 + ") :" + statement2);
								tmp2 = "" + (no_2location + 1) + " F";
							cp[1].setLabel(tmp2);
							Wbutton[1].setLabel(weight2+" KG");
							if(no_2location!=check_2){
								Timer tt = new Timer();
								TimerTask mm = new TimerTask() {
									int currentLocation = 540 - (75 * no_2location);
									int floorVariable = 75 * (check_2 - no_2location);
									int fv=0;
									
									public void run() {
										if(floorVariable > 0 && fv!=75){
											no2.setBounds(3, currentLocation - fv, 70, 60);
											fv++;
										}
										else if(floorVariable < 0 && fv!=-75){
											no2.setBounds(3, currentLocation - fv, 70, 60);
											fv--;
										}
										else if (Math.abs(fv)==75){
											this.cancel();
										}
									}
								};
								tt.schedule(mm, 0, 13);
							}
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							
							if (no_2location == check_2) {
								innerButton2[check_2] = false;
								if (statement2 == 1 || statement2 == 4) {
									no_2Top[check_2] = false;
									top[no_2location].setIcon(buttonNormal[check_2]);
								} else if (statement2 == 2 || statement2 == 3) {
									no_2Bottom[check_2] = false;
									bottom[no_2location].setIcon(buttonNormal[check_2]);
								}
								ele2[check_2].setIcon(buttonNormal[check_2]);
								top[no_2location].setIcon(topNormal);
								bottom[no_2location].setIcon(bottomNormal);
								ele2[8].setIcon(buttonPressed[8]);
								
								try {
									Thread.sleep(1000);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								//open elevator door
								ele2[8].setIcon(buttonNormal[8]);
								no2.setIcon(opendoor);
								enterdoor[no_2location].setIcon(enterPressed);
								ele2[9].setIcon(buttonPressed[9]);
								
								try {
									Thread.sleep(1000);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}

								int k0=0; 
								for (int k = 0; k < minion.size(); k++) {
									// come into elevator
									if (minion.get(k).getStart() == no_2location &&(weight2+minion.get(k).getweight())<maxweight) {
										minion.get(k).getButton().setVisible(false);
										if(k0==0)
										{
											cache_s[no_1location]++;
											k0=1;
										}
										minion.get(k).getButton().setVisible(false);
										weight2=weight2+minion.get(k).getweight();
										Wbutton[1].setLabel(weight2+" KG ");
										minion.get(k).setEleNum(2);
										countMinion2++;
										for (int k2 = 0; k2 < ele2.length; k2++)
											ele2[k2].setEnabled(true);
									}
									else if (minion.get(k).getStart() == no_2location
											&& minion.get(k).getRemove() == 0) 
										break;
									// come out of elevator
									if (minion.get(k).getDestination() == no_2location
											&& minion.get(k).getRemove() == 0
											&& minion.get(k).getEleNum() == 2) {
										try {
											Thread.sleep(300);
										} catch (InterruptedException e) {
											e.printStackTrace();
										}
										if(k0==0)
										{
											cache_d[no_1location]++;
											k0=1;
										}
										elein2 = k;
										minion.get(k).getButton().setVisible(true);
										weight2=weight2-minion.get(k).getweight();
										Timer t = new Timer();
										TimerTask m = new TimerTask() {
											int p = 0, num = 0, index = minion.get(elein2)
													.getLinkedIndex();

											public void run() {
												if (num % 4 == 0 || num % 4 == 1) {
													minion.get(index).getButton().setBounds(minion.get(index).getX() - p,minion.get(index).getY(), 80, 80);
													minion.get(index).getButton().setIcon(minion.get(index).getImage1());
													centralPanel.add(minion.get(index).getButton());
													num++;
												} else {
													minion.get(index).getButton().setBounds(minion.get(index).getX() - p,minion.get(index).getY(), 80, 80);
													minion.get(index).getButton().setIcon(minion.get(index).getImage2());
													centralPanel.add(minion.get(index).getButton());
													num++;
												}
												p = p + 10;
												if (p == 500) {
													minion.get(index).getButton().setVisible(false);
													if(minion.get(index).getDestination()==1 || minion.get(index).getDestination()==6){
														add[minion.get(index).getDestination()].setIcon(addPressed2);
													}
													else{
														add[minion.get(index).getDestination()].setIcon(addPressed1);
													}
													minion.get(index).setRemove(1);
										
													remove(minion.get(index));
													this.cancel();
												}
											}
										};

										t.schedule(m, 30, 30);
									}
								}
								//close elevator door
								try {
									Thread.sleep(1000);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								
								if(no_1location==1 || no_1location==6){
									add[no_1location].setIcon(addNormal2);
								}
								else{
									add[no_1location].setIcon(addNormal1);
								}
								
								ele2[9].setIcon(buttonNormal[9]);
								top[no_2location].setIcon(topNormal);
								bottom[no_2location].setIcon(bottomNormal);
								no2.setIcon(closedoor);
								enterdoor[no_2location].setIcon(enterNormal);
								ele2[check_2].setIcon(buttonNormal[check_2]);
								
								if (statement2 == 2 || statement2 == 3)
									top[check_2].setIcon(topNormal);
								else
									bottom[check_2].setIcon(bottomNormal);
							}
						}
					}
					
				//==========================================================================================
	class ElevatorNo_3 extends Thread {
		ElevatorMovement3 move_3 = new ElevatorMovement3();
		ElevatorDistribution3 move_3_s = new ElevatorDistribution3();
		ElevatorNo_3Moving ev_3 = new ElevatorNo_3Moving();

		public void run() {
			GO: while (true) {
				switch (statement3) {
				case 0:    // Not moving Elevator state
					updown[4].setIcon(upImg);
					updown[5].setIcon(downImg);
					yield();
					break;

				case 1:   // Elevator move up and check top button
					updown[4].setIcon(upCheckedImg);
					updown[5].setIcon(downImg);
					for (int i = no_3location; i <= final3; i++, no_3location++) {
						yield();
						
						//if the elevator is lower than present location,
						for (int y = 0; y < no_3location; y++) { 
							if (no_3Top[y]) { 
								allTop[y] = true;
								no_3Top[y] = false;
								break;
							}
						}
						yield();   // Another elevator is moved.
						// If elevator is moving to final floor, but check top button,
						for (int y = no_3location; y <= final3; y++) {
							if (no_3Top[y] || innerButton3[y]) { 
								check_3 = y;   // check the floor
								break;
							}
						}
						yield();
						move_3_s.move(); 
						ev_3.work();     //Call ElevatorNo_3  Moving
						yield();
						move_3_s.move(); 
					}
					no_3location--; 
					break;

				case 2:      //Elevator move up and check bottom button.
					updown[4].setIcon(upCheckedImg);
					updown[5].setIcon(downImg);
					check_3 = final3; 
					for (int i = no_3location; i <= final3; i++, no_3location++) {
						yield();
						for (int y = no_3location; y < 8; y++) { 
							if (no_3Top[y]) { 
								statement3 = 1; 
								continue GO; 
							} else if (no_3Bottom[y] && y < no_3location) {
								allBottom[y] = true;
								no_3Bottom[y] = false;
								break;
							}
						}
						check_3 = final3;
						yield();
						move_3_s.move();
						ev_3.work(); // Call ElevatorNo_3 Moving
						yield();
						move_3_s.move();
					}
					no_3location--; 
					break;
				case 3:    //Elevator move down and check up button.
					updown[4].setIcon(upImg);
					updown[5].setIcon(downCheckedImg);
					for (int i = no_3location; i >= final3; i--, no_3location--) {
						yield();
						//If the check is higher than present location,
						for (int y = 7; y > no_3location; y--) { 
							if (no_3Bottom[y]) { 
								allBottom[y] = true;
								no_3Bottom[y] = false;
								break;
							}
						}
						yield();   //moving anther Elevator
						for (int y = no_3location; y >= final3; y--) {
							if (no_3Bottom[y] || innerButton3[y]) { 
								check_3 = y; 
								break;
							}
						}
						yield();
						move_3_s.move();
						ev_3.work(); // Call ElevatorNo_3 Moving
						yield();
						move_3_s.move();
					}
					no_3location++; 
					break;

				case 4:    //Elevator move down and check down button.
					if (no_3location == 0) {
						updown[4].setIcon(upCheckedImg);
						updown[5].setIcon(downImg);
					} else {
						updown[5].setIcon(downCheckedImg);
						updown[4].setIcon(upImg);
					}
					check_3 = final3;
					for (int i = no_3location; i >= final3; i--, no_3location--) {
						yield();
						
						//If not Bottom button and top button is checked in under the location
						for (int y = no_3location; y > 10; y--) { 
							if (no_3Bottom[y]) {
								statement3 = 3; 
								continue GO;    // Go to bottom button processing
							} else if (no_3Top[y] && y > no_3location) {
								allTop[y] = true;
								no_3Top[y] = false;
								break;
							}
						}
						check_3 = final3;
						yield();
						move_3_s.move();
						ev_3.work(); // Call ElevatorNo_3 Moving
						yield();
						move_3_s.move();
					}
					no_3location++; 
					break;
				}
				statement3 = 0;
				move_3.move();
				yield();
			}
		}
	}
	// ===========================================================================

	

	
	// decide elevator3 to move up or move down or stop
	class ElevatorMovement3 {
		ElevatorDistribution3 move_3_s = new ElevatorDistribution3();

		public void move() {
			for (int i = 0; i < 8; i++) {
				if (no_3Bottom[i] || no_3Top[i] || innerButton3[i]) {
					break;
				} else {
					moveupdown3 = 0;
				}
			}
			for (int i = no_3location; i < 8; i++) {
				if (no_3Top[i] || no_3Bottom[i] || innerButton3[i]) {
					moveupdown3 = 1;
					break;
				}
			}
			for (int i = no_3location; i > -1; i--) {
				if (no_3Top[i] || no_3Bottom[i] || innerButton3[i]) {
					moveupdown3 = -1;
					break;
				}
			}
			move_3_s.move();
		}
	}

	

	// ===============================================================================
	
	class ElevatorDistribution3 //same as ElevatorDistribution1
	{
		public void move() {
			switch (moveupdown3) {
			case 1:
				for (int i = 7; i >= no_3location; i--) {
					if (no_3Top[i] || innerButton3[i]) {
						statement3 = 1;
						final3 = i;
						return;
					}
					if (no_3Bottom[i]) {
						statement3 = 2;
						final3 = i;
						return;
					}
				}
				break;

			case -1:
				for (int i = 0; i <= no_3location; i++) {
					if (no_3Bottom[i] || innerButton3[i]) {
						statement3 = 3;
						final3 = i;
						return;
					}
					if (no_3Top[i]) {
						statement3 = 4;
						final3 = i;
						return;
					}
				}
				break;
			}
			moveupdown3 = 0;
		} 
	}
	//==========================================================================================
	
		
		// action for moving elevator3
			class ElevatorNo_3Moving {
			public void work() {
				moving moveElevator3 = new moving(no3, no_3location, check_3,final3, statement3);
				System.out.println("NO3)> current ( " + no_3location + ") destination (" + check_3 + ") :" + statement3);
				tmp3 = "" + (no_3location + 1) + " F";
				cp[2].setLabel(tmp3);
				Wbutton[2].setLabel(weight3+" KG");
				if(no_3location!=check_3){
					Timer tt = new Timer();
					TimerTask mm = new TimerTask() {
						int currentLocation = 540 - (75 * no_3location);
						int floorVariable = 75 * (check_3 - no_3location);
						int fv=0;
						
						public void run() {
							if(floorVariable > 0 && fv!=75){
								no3.setBounds(3, currentLocation - fv, 70, 60);
								fv++;
							}
							else if(floorVariable < 0 && fv!=-75){
								no3.setBounds(3, currentLocation - fv, 70, 60);
								fv--;
							}
							else if (Math.abs(fv)==75){
								this.cancel();
							}
						}
					};
					tt.schedule(mm, 0, 13);

				}
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				if (no_3location == check_3) {
					innerButton3[check_3] = false;
					if (statement3 == 1 || statement3 == 4) {
						no_3Top[check_3] = false;
						top[no_3location].setIcon(buttonNormal[check_3]);
					} else if (statement3 == 2 || statement3 == 3) {
						no_3Bottom[check_3] = false;
						bottom[no_3location].setIcon(buttonNormal[check_3]);
					}
					
					ele3[check_3].setIcon(buttonNormal[check_3]);
					top[no_3location].setIcon(topNormal);
					bottom[no_3location].setIcon(bottomNormal);
					ele3[8].setIcon(buttonPressed[8]);
					
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					ele3[8].setIcon(buttonNormal[8]);
					no3.setIcon(opendoor);
					enterdoor[no_3location].setIcon(enterPressed);
					ele3[9].setIcon(buttonPressed[9]);
					int k0=0;
					for (int k = 0; k < minion.size(); k++) {
						// come into elevator
						if (minion.get(k).getStart() == no_3location
								&& minion.get(k).getRemove() == 0  && (weight3+minion.get(k).getweight())<maxweight) 
						{
							if(k0==0)
							{
								cache_s[no_1location]++;
								k0=1;
							}
							minion.get(k).getButton().setVisible(false);
							weight3=weight3+minion.get(k).getweight();
							Wbutton[2].setLabel(weight3+" KG ");
							minion.get(k).setEleNum(3);
							countMinion3++;
							for (int k2 = 0; k2 < ele3.length; k2++)
								ele3[k2].setEnabled(true);
						}
						else if (minion.get(k).getStart() == no_3location && minion.get(k).getRemove() == 0) 
							break;
						// come out of elevator
						if (minion.get(k).getDestination() == no_3location
								&& minion.get(k).getRemove() == 0
								&& minion.get(k).getEleNum() == 3) {
							try {
								Thread.sleep(300);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							if(k0==0)
							{
								cache_d[no_1location]++;
								k0=1;
							}
							elein3 = k;
							minion.get(k).getButton().setVisible(true);
							weight3=weight3-minion.get(k).getweight();
							Wbutton[2].setLabel(weight3+" KG ");
							Timer t = new Timer();
							TimerTask m = new TimerTask() {
								int p = 0, num = 0, index = minion.get(elein3).getLinkedIndex();

								public void run() {
									if (num % 4 == 0 || num % 4 == 1) {
										minion.get(index).getButton().setBounds(minion.get(index).getX() - p, minion.get(index).getY(), 80, 80);
										minion.get(index).getButton().setIcon(minion.get(index).getImage1());
										centralPanel.add(minion.get(index).getButton());
										num++;
									} else {
										minion.get(index).getButton().setBounds(minion.get(index).getX() - p,minion.get(index).getY(),80, 80);
										minion.get(index).getButton().setIcon(minion.get(index).getImage2());
										centralPanel.add(minion.get(index).getButton());
										num++;
									}
									p = p + 10;
									if (p == 445) {
										if(minion.get(index).getDestination()==1 || minion.get(index).getDestination()==6){
											add[minion.get(index).getDestination()].setIcon(addPressed2);
										}
										else{
											add[minion.get(index).getDestination()].setIcon(addPressed1);
										}
										
										minion.get(index).getButton().setVisible(false);

										if(minion.get(index).getDestination()==1 || minion.get(index).getDestination()==6){
											add[minion.get(index).getDestination()].setIcon(addNormal2);
										}
										else{
											add[minion.get(index).getDestination()].setIcon(addNormal1);
										}
										
										minion.get(index).setRemove(1);
										this.cancel();
									}
								}
							};
						
							t.schedule(m, 30, 30);
						}
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					ele3[9].setIcon(buttonNormal[9]);
					top[no_3location].setIcon(topNormal);
					bottom[no_3location].setIcon(bottomNormal);
					no3.setIcon(closedoor);
					enterdoor[no_3location].setIcon(enterNormal);
					ele3[check_3].setIcon(buttonNormal[check_3]);

					if (statement3 == 2 || statement3 == 3)
						top[check_3].setIcon(topNormal);
					else
						bottom[check_3].setIcon(bottomNormal);
				}
			}
		}
	}