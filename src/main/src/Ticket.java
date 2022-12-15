public class Ticket{

	private int id;
	private String type;
	private Trip trip;
	private int price;
	private boolean seating;
	private boolean bike;
	private boolean stroller;
	private static int countid;

	public Ticket(String type, Trip trip, int price, boolean seating, boolean bike, boolean stroller) {
		this.id = countid++;
		this.type = type;
		this.trip = trip;
		this.price = price;
		this.seating = seating;
		this.bike = bike;
		this.stroller = stroller;
	}

	public int getId() {
		return id;
	}

	public String getType() {
		return type;
	}

	public Trip getTrip() {
		return trip;
	}

	public int getPrice() {
		return price;
	}

	public boolean isSeating() {
		return seating;
	}

	public boolean isBike() {
		return bike;
	}

	public boolean isStroller() {
		return stroller;
	}




}