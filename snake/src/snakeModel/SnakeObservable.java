package snakeModel;

import java.util.ArrayList;
import java.util.List;

import snakeModel.SnakeEvent.ChangeType;

public class SnakeObservable {

	private final int INITSIZE = 3;

	private List<Coordinate> body;
	private Game game;
	private Direction direction;
	private boolean alive;
	private List<SnakeObserver> observers;
	private Fruit fruit;
	private int counter;
	private boolean invisible;

	public SnakeObservable(Game game, Coordinate start) {
		observers = new ArrayList<>();
		alive = true;
		this.game = game;
		direction = Direction.Right;
		body = new ArrayList<>();
		for (int i = 0; i < INITSIZE; i++) {
			body.add(new Coordinate(start.getX() + i, start.getY()));
		}
		fruit = null;
		counter = 0;
	}

	public void register(SnakeObserver o) {
		observers.add(o);
	}

	public void unregister(SnakeObserver o) {
		observers.remove(o);
	}

	private void notifyObserver(List<SnakeEvent> events) {
		for (SnakeObserver snakeObserver : observers) {
			snakeObserver.notify(events);
		}
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public List<Coordinate> getBody() {
		return new ArrayList<>(body);
	}

	public Fruit getFruit() {
		return fruit;
	}

	public boolean Invisible() {
		return invisible;
	}

	public void setInvisible(boolean invisible) {
		this.invisible = invisible;
	}

	public void creatFruit() {
		Coordinate c = new Coordinate((int) (Math.random() * game.getWidth()), (int) (Math.random() * game.getHeight()));
		while (body.contains(c)) {
			c = new Coordinate((int) (Math.random() * game.getWidth()), (int) (Math.random() * game.getHeight()));
		}
		
		counter++;
		
		if (counter % 60 == 0) {
			fruit = new Fruit(c,new Normal());
		}
		
		if (counter % 240 == 0) {
			fruit = new Fruit(c,new Invisible());
			System.out.println("Invisible fruit");
		}
	}

	public void delFruit() {
		if(fruit != null) {
			fruit.setT(fruit.getT()-1);
			if(fruit.getT() == 0) fruit = null;
		}	
	}

	void move() {

		creatFruit();
		delFruit();

		Coordinate current = body.get(body.size() - 1);
		Coordinate next = new Coordinate(current.getX() + direction.getX(), current.getY() + direction.getY());

		if ((body.contains(next) || game.isOut(next)) && !Invisible())
			alive = false;

		if(game.isOut(next) && Invisible()) {
			if(0 > next.getX())next.setX(next.getX() + game.getWidth());  
			if(next.getX() >= game.getWidth()) next.setX(next.getX() - game.getWidth());
			if(0 > next.getY())next.setY(next.getY() + game.getHeight());
			if(next.getY() >= game.getWidth()) next.setY(next.getY() - game.getHeight());
		}
		
		body.add(next);
		List<SnakeEvent> events = new ArrayList<>();
		events.add(new SnakeEvent(next, ChangeType.ENTER));

		if (fruit == null || !(fruit.getCoord().equals(next))) {
			events.add(new SnakeEvent(body.get(0), ChangeType.LEAVE));
			body.remove(0);
		} else
			eat();

		notifyObserver(events);
	}

	public void eat() {
		fruit.eaten(this);
		fruit = null;
	}

	public boolean isAlive() {
		return alive;
	}

}
