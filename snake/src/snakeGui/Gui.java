package snakeGui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JFrame;

import snakeModel.Coordinate;
import snakeModel.Direction;
import snakeModel.Game;
import snakeModel.SnakeEvent;
import snakeModel.SnakeObservable;
import snakeModel.SnakeObserver;

public class Gui implements SnakeObserver {

	private Game game;
	private SnakeObservable snake;
	private static final int cellSize = 10;
	private JFrame frame = new JFrame("Snake");
	private JComponent component = new JComponent() {

		private static final long serialVersionUID = 1L;

		@Override
		protected void paintComponent(Graphics g) {

			g.setColor(Color.BLUE);
			for (Coordinate c : snake.getBody()) {
				g.fillOval(c.getX() * cellSize, c.getY() * cellSize, cellSize, cellSize);
			}

			if (snake.getFruit() != null) {
				g.setColor(Color.RED);
				g.fillOval(snake.getFruit().getCoord().getX() * cellSize, snake.getFruit().getCoord().getY() * cellSize,
						cellSize, cellSize);
			}
		}
	};

	public Gui(Game game) {
		this.game = game;
		this.snake = game.getSnake();
		frame.setContentPane(component);
		frame.setSize(game.getWidth() * cellSize, game.getHeight() * cellSize);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					snake.setDirection(Direction.Left);
					break;
				case KeyEvent.VK_RIGHT:
					snake.setDirection(Direction.Right);
					break;
				case KeyEvent.VK_UP:
					snake.setDirection(Direction.Up);
					break;
				case KeyEvent.VK_DOWN:
					snake.setDirection(Direction.Down);
					break;
				default:
					break;
				}

			}
		});
	}

	@Override
	public void notify(List<SnakeEvent> events) {
		for (SnakeEvent event : events)
			System.out.println(event);
		component.repaint();
	}
}
