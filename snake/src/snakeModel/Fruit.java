package snakeModel;

public class Fruit {
	private Coordinate coord;
	private State state;
	private int t;
	
	public Coordinate getCoord() {
		return coord;
	}
	
	public void setCoord(Coordinate coord) {
		this.coord = coord;
	}
	
	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public int getT() {
		return t;
	}

	public void setT(int t) {
		this.t = t;
	}

	public Fruit(Coordinate coord, State state) {
		this.coord = coord;
		this.state = state;
		state.setTime(this);	
	}
	
	public void eaten(SnakeObservable snake) {
		state.eaten(snake);
	}
}
