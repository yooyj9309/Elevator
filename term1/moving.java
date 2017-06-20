package term1;


import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;

public class moving extends Thread{
	static int[] point = { 525, 450, 375, 300, 225, 150, 75, 0};
	
	public moving (JButton ele, int start, int destination, int f, int s) {
		Timer tt = new Timer();
		TimerTask mm = new TimerTask() {
			int currentLocation = point[start];
			int floorVariable = destination - start;
			int fv = 0;
			
		
			public void run() {
				yield();
				System.out.println("fv : "+fv);
				if (Math.abs(fv) == 75) {
					//if(destination == f){
						fv=0;
						this.cancel();
					//}
				}

				if(start==destination  /*destination==f*/){
					fv=0;
					this.cancel();
				}
				
				if (floorVariable >= 0 && fv!=75) {// 올라
					ele.setBounds(3, currentLocation--, 75, 60);
					fv++;
				}

				else if (floorVariable <= 0 && fv!=75) {// 내려
					ele.setBounds(3, currentLocation++, 75, 60);
					fv++;
				}

				yield();
			}
		};
		
		tt.schedule(mm, 0, 13);
	}
}