public class Ticket{

	private int id;
	private String type;
	private Rute rute;
	private int price;
	private boolean seating;
	private boolean bike;
	private boolean stroller;
	private static int countid;

	public Ticket(String type, Rute rute, int price, boolean seating, boolean bike, boolean stroller) {
		this.id = countid++;
		this.type = type;
		this.rute = rute;
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

	public Rute getRute() {
		return rute;
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