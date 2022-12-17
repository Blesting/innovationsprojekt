import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class main{

	public static void main(String[] arc){
		UI.buildHomepage();
		ArrayList<Station> stationer = new ArrayList();
		ArrayList<Trip> trips = new ArrayList<>();

		try {
			HashMap<Integer, String[]> tripTemp = new HashMap();
			HashMap<Integer, ArrayList<String>> timesTemp = new HashMap();

			// Loading products
			String fileString = Files.readString(Path.of("../data/RejseplanenStoppesteder.txt"));
			fileString
				.lines()
				.skip(1)
				.map(line -> line.split(";"))
				.filter(lineArray -> lineArray[0].length() == 7)
				//.peek(System.out::println)
				.forEach(lineArray -> {
					Location location = new Location(Integer.valueOf(lineArray[2]), Integer.valueOf(lineArray[3]));
					Station station = new Station(Integer.valueOf(lineArray[0]), lineArray[1], location);
					stationer.add(station);
				});

			fileString = Files.readString(Path.of("../data/trips.txt"));
			fileString
				.lines()
				.skip(1)
				.map(line -> line.split(","))
				//.peek(System.out::println)
				.forEach(lineArray -> tripTemp.put(Integer.valueOf(lineArray[2]), lineArray));

			fileString = Files.readString(Path.of("../data/stop_times.txt"));
			fileString
				.lines()
				.skip(1)
				.map(line -> line.split(","))
				//.peek(System.out::println)
				.forEach(lineArray -> {
					Integer tripId = Integer.valueOf(lineArray[0]);
					if(!timesTemp.containsKey(tripId)){
						ArrayList<String> time = new ArrayList();
						time.add(lineArray[1]);
						time.add(lineArray[2]);
						timesTemp.put(tripId, time);
					}
					else{
						timesTemp.get(tripId).add(lineArray[1]);
						timesTemp.get(tripId).add(lineArray[2]);
					}
				});

			timesTemp.forEach((key, value) -> {
				if(tripTemp.containsKey(key)){
					String[] trip = tripTemp.get(key);
					String departure = value.get(1);
					String arrival = value.get(value.size()-2);
					trips.add(new Trip(trip[0], key, trip[3], trip[4], departure, arrival));
				}
			});

			//timesTemp.forEach((key,value) -> System.out.println(key));

			//System.out.println(stationer.size());


		} catch (Exception e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		//trips.forEach(trip -> System.out.println(trip.getTripHeadsign()));
		//ApiInteg.getTrips(8600512, 8600626, "23/12/2022", "17:55");
		//System.out.println(trips.size());

	}


}