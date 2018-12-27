package snakeModel;

import java.util.List;

public interface SnakeObserver {
	public void notify(List<SnakeEvent> events);
}
