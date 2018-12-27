package snakeModel;

public class Invisible implements State {
	public void eaten(SnakeObservable snake) {
		snake.setInvisible(true);
		System.out.println("INVISIBLE TIME");
	}
	
	public void setTime(Fruit fruit) {
		fruit.setT(40);
	}
}
