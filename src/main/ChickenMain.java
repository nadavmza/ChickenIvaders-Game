package main;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ChickenMain extends JFrame  {
	
	
	public ChickenMain()
	{
		
		//JFrame f1= new JFrame ("DX Chicken By NMZ");
		ChickenGame bg=new ChickenGame();
		add(bg);
		setDefaultCloseOperation(ChickenMain.EXIT_ON_CLOSE);
		setSize(2000,2000);
		setResizable(false);
		setFocusable(false);
		setVisible(true);
		
		
		
	}
	
	
	

	public static void main(String[] args) {
		new ChickenMain();

	}

}
