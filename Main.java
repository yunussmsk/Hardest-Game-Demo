import javax.swing.*;

public class Main {
	public static void main(String args[]){
		JFrame game = new TheFrame();
		game.pack();
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.setLocationRelativeTo(null);
		game.setResizable(false);
		game.setVisible(true);
	}
}
