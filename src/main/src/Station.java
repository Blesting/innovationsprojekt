public class Station{

	private int id;
	private String name;
	private Location location;


	public Station(int id, String name, Location location) {
		this.name = name;
		this.location = location;
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Location getLocation() {
		return location;
	}
}