public class Trip{

	private String routeId;
	private int tripId;
	private String tripHeadsign;
	private String tripShortName;
	private String departure;
	private String arrival;

	public Trip(String routeId, int tripId, String tripHeadsign, String tripShortName, String departure, String arrival) {
		this.routeId = routeId;
		this.tripId = tripId;
		this.tripHeadsign = tripHeadsign;
		this.tripShortName = tripShortName;
		this.departure = departure;
		this.arrival = arrival;
	}

	public String getRouteId() {
		return routeId;
	}

	public int getTripId() {
		return tripId;
	}

	public String getTripHeadsign() {
		return tripHeadsign;
	}

	public String getTripShortName() {
		return tripShortName;
	}

	public String getDeparture() {
		return departure;
	}

	public String getArrival() {
		return arrival;
	}


}