package snakeModel;

public class SnakeEvent {

	public static enum ChangeType {
		ENTER, LEAVE
	}

	private Coordinate coordinate;
	private ChangeType changeType;

	public SnakeEvent(Coordinate coordinate, ChangeType changeType) {
		this.coordinate = coordinate;
		this.changeType = changeType;
	}

	public ChangeType getChangeType() {
		return changeType;
	}

	public Coordinate getCoordinate() {
		return coordinate;
	}

	@Override
	public String toString() {
		String s;
		if (changeType == ChangeType.ENTER)
			s = "Enters";
		else
			s = "Leaves";
		return s + " (" + coordinate.getX() + ", " + coordinate.getY() + ")";
	}

}
