public class Route{

	private int id;
	private Tog tog;
	private int carriage;
	private Stop fromLocation;
	private Stop toLocation;
	private Time departure;
	private Time arrival;
	private ArrayList<Stop> stop;
	private static int countid;

	public Route(Tog tog, int carriage, Stop fromLocation, Stop toLocation, Time departure, Time arrival, ArrayList<Stop> stop) {
		this.id = countid;
		this.tog = tog;
		this.carriage = carriage;
		this.fromLocation = fromLocation;
		this.toLocation = toLocation;
		this.departure = departure;
		this.arrival = arrival;
		this.stop = stop;
	}

	public int getId() {
		return id;
	}

	public Tog getTog() {
		return tog;
	}

	public int getCarriage() {
		return carriage;
	}

	public Stop getFromLocation() {
		return fromLocation;
	}

	public Stop getToLocation() {
		return toLocation;
	}

	public Time getDeparture() {
		return departure;
	}

	public Time getArrival() {
		return arrival;
	}

	public ArrayList<Stop> getStop() {
		return stop;
	}


}