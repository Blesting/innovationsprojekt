public class Tog{
	private int id;
	private int pladserPrVogn;
	private static int countid;

	public Tog(int pladserPrVogn) {
		this.id = countid++;
		this.pladserPrVogn = pladserPrVogn;
	}

	public int getId(){
		return id;
	}

	public int getPladserPrVogn(){
		return pladserPrVogn;
	}
}