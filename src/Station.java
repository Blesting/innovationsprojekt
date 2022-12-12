public class Station{

	private int id;
	private String name;
	private Location location;
	private static int countid;


	public Station(String name, Location location) {
		this.name = name;
		this.location = location;
		this.id = countid++;
	}

	public String getName() {
		return name;
	}

	public Location getLocation() {
		return location;
	}
}