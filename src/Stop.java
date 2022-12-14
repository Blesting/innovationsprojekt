public class Stop{

	private int id;
	private Station station;
	private String arrival;
	private String departure;
	private int track;
	private static int countid;

	public Stop(Station station, String arrival, String departure, int track) {
		this.id = countid++;
		this.station = station;
		this.arrival = arrival;
		this.departure = departure;
		this.track = track;
	}

	public int getId() {
		return id;
	}

	public Station getStation() {
		return station;
	}

	public String getArrival() {
		return arrival;
	}

	public String getDeparture() {
		return departure;
	}

	public int getTrack() {
		return track;
	}




}