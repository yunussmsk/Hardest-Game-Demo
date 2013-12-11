import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TheFrame extends JFrame implements ActionListener {

	private int x = 30, y = 235;

	private Object o[] = new Object[8];

	private int direction = 0, a = 0;

	private Timer time;

	JPanel p = new ThePanel();

	private boolean dead = false;

	public TheFrame() {
		super("Zor Oyun");
		addKeyListener(new Listener());
		for (int i = 0; i < o.length; i++)
			o[i] = new Object(150 + i * 50, (i % 2 == 0) ? 30 : 400);

		time = new Timer(0, this);
		add(p);
		time.start();
	}

	private class ThePanel extends JPanel {

		ThePanel() {
			setPreferredSize(new Dimension(670, 460));
			setBackground(new Color(200, 191, 231));
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			checkDead();
			if (dead == true) {
				x = 30;
				y = 235;
				dead = false;
			}
			if(x > 520){
				g.setFont(new Font("",Font.BOLD + Font.ITALIC,40));
				g.setColor(Color.GREEN);
				g.drawString("Victory!!",300,200);
				return;
				}
			paintBackground(g);
			paintTheObject(g);
			paintObjects(g);
		}

		public void paintBackground(Graphics g) {
			g.setColor(Color.BLACK);
			g.fillRect(20, 170, 95, 150);
			g.fillRect(115, 30, 440, 400);
			g.fillRect(555, 170, 100, 150);

			g.setColor(Color.CYAN);
			g.fillRect(120, 35, 430, 390);

			g.setColor(Color.GREEN);
			g.fillRect(25, 175, 95, 140);
			g.fillRect(550, 175, 100, 140);
		}

		public void paintTheObject(Graphics g) {
			g.setColor(Color.BLACK);
			g.fillRect(x, y, 30, 30);
			g.setColor(Color.RED);
			g.fillRect(x + 5, y + 5, 20, 20);
		}

		public void paintObjects(Graphics g) {

			for (int i = 0; i < o.length; i++) {
				g.setColor(Color.BLACK);
				g.fillOval(o[i].getX(), o[i].getY(), 20, 20);
				g.setColor(Color.BLUE);
				g.fillOval(o[i].getX() + 3, o[i].getY() + 3, 14, 14);

			}
		}

		public void checkDead() {
			for (int i = 0; i < o.length; i++) {
				boolean north = false, south = false, east = false, west = false;

				north = (y - o[i].getY() < 20);
				south = (o[i].getY() - y < 30);
				east = (o[i].getX() - x < 30);
				west = (x - o[i].getX() < 20);
				dead = dead | (north & south & east & west);
			}
		}

	}

	private class Listener implements KeyListener {
		@Override
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
				y -= 5;
				if (outOfGame())
					y += 5;
				break;
			case KeyEvent.VK_DOWN:
				y += 5;
				if (outOfGame())
					y -= 5;
				break;
			case KeyEvent.VK_RIGHT:
				x += 5;
				if (outOfGame())
					x -= 5;
				break;
			case KeyEvent.VK_LEFT:
				x -= 5;
				if (outOfGame())
					x += 5;
				break;
			}
			p.repaint();
		}

		@Override
		public void keyReleased(KeyEvent e) {

		}

		@Override
		public void keyTyped(KeyEvent e) {

		}

		public boolean outOfGame() {
			boolean a, b, c, d, e, f, g, h, i, j, k, l;
			a = (x < 25);
			b = (!(y < 290 && y > 170) && (x < 120));
			c = (y <= 30 || y >= 400);
			d = (!(y < 290 && y> 170) && (x > 520));
			e = (x > 620);
			return a || b || c || d || e;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		a++;
		if (a % 5 == 0) {
			if (o[0].getY() == 30)
				direction = 0;
			if (o[0].getY() == 400)
				direction = 1;
			for (int i = 0; i < o.length; i++) {
				if (direction == 0)
					o[i].setY((i % 2 == 0) ? (o[i].getY() + 1)
							: (o[i].getY() - 1));
				if (direction == 1)
					o[i].setY((i % 2 == 0) ? (o[i].getY() - 1)
							: (o[i].getY() + 1));
			}
		}
		p.repaint();
	}
}
