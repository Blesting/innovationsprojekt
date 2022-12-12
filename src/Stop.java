public class Stop{

	private int id;
	private Station station;
	private Time arrival;
	private time departure;
	private int track;
	private static int countid;

	public Stop(Station station, Time arrival, time departure, int track) {
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

	public Time getArrival() {
		return arrival;
	}

	public time getDeparture() {
		return departure;
	}

	public int getTrack() {
		return track;
	}




}