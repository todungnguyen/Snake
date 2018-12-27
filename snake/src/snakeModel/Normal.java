package snakeModel;

public class Normal implements State {
	public void eaten(SnakeObservable snake) {
	}
	
	public void setTime(Fruit fruit) {
		fruit.setT(20);
	}
}
