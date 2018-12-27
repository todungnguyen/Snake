package snakeModel;

public interface State {
	public void eaten(SnakeObservable snake);
	public void setTime(Fruit fruit);
}
