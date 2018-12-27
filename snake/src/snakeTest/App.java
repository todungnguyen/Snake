package snakeTest;

import snakeGui.Gui;
import snakeModel.Coordinate;
import snakeModel.Game;


public class App {
	public static void main(String[] args) {
		Game game = new Game(50, 50, new Coordinate(20, 30));
		game.getSnake().register(new Gui(game));
		while (game.getSnake().isAlive()) {
			game.step();
			try {
				Thread.sleep(80);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}