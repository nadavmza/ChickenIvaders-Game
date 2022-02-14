package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class timeGame implements ActionListener {

	
	
	public Timer t;
	int timeLeft;
	int timeLimit;
	boolean IsOver;
	public timeGame(int timelimit)
	{
		this.timeLimit=timelimit;
		
	   t=new Timer(1000, this);
	   timeLeft=timelimit;
	   
	   IsOver=false;
	   
		
		
	}
	
	
	
	public void  drawTimer(Graphics g)
	{
		Font myfont = new Font("Lucida Calligraphy", Font.BOLD, 20);
		if(IsOver==false)
		{
			t.start();
			g.setFont(myfont);
			g.setColor(Color.GREEN);
			g.drawString("Time =" +timeLeft,1357,600);
		}
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(timeLeft==0)
		{
			
			IsOver=true;
			t.stop();
			
		}
		else
			timeLeft--;
		
	}
	
	
	
	public int getTime()
	{
		return timeLeft;
	}
}