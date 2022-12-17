import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class main{

	private static ArrayList<Station> stations = new ArrayList();
	private static ArrayList<Trip> trips = new ArrayList<>();

	public static void main(String[] arc){

		loadData();

		UI.buildHomepage();


		/*
		int[] stationIds = getStationIds("Odense St.", "København H");
		getValidTrips(stationIds[0], stationIds[1], "17:00:00", "20:00:00").forEach(System.out::println);
		*/

		//trips.forEach(trip -> System.out.println(trip.getTripHeadsign()));
		//ApiInteg.getTrips(8600646, 8600798, "22/12/2022", "17:55");
		//System.out.println(trips.size());

	}

	public static ArrayList<Station> getStations() {
		return stations;
	}

	public static ArrayList<Trip> getTrips() {
		return trips;
	}

	public static void loadData(){
		try {
			HashMap<Integer, String[]> tripTemp = new HashMap();
			HashMap<Integer, ArrayList<String>> timesTemp = new HashMap();

			// Loading products
			String fileString = Files.readString(Path.of("src/main/data/RejseplanenStoppesteder.txt"));
			fileString
					.lines()
					.skip(1)
					.map(line -> line.split(";"))
					.filter(lineArray -> lineArray[0].length() == 7)
					//.peek(System.out::println)
					.forEach(lineArray -> {
						Location location = new Location(Integer.valueOf(lineArray[2]), Integer.valueOf(lineArray[3]));
						Station station = new Station(Integer.valueOf(lineArray[0]), lineArray[1].substring(1,lineArray[1].length() - 1), location);
						stations.add(station);
					});

			fileString = Files.readString(Path.of("src/main/data/trips.txt"));
			fileString
					.lines()
					.skip(1)
					.map(line -> line.split(","))
					//.peek(System.out::println)
					.forEach(lineArray -> tripTemp.put(Integer.valueOf(lineArray[2]), lineArray));

			fileString = Files.readString(Path.of("src/main/data/stop_times.txt"));
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

		} catch (Exception e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	public static ArrayList<String> getValidTrips(int fromStationInt, int toStationInt, String fromTime, String toTime){
		ArrayList<String> validTrips = new ArrayList<>();

		try {

			String fromStation = "00000" + fromStationInt;
			String toStation = "00000" + toStationInt;

			String[] fromTimeArray = fromTime.split(":");
			String[] toTimeArray = toTime.split(":");


			HashMap<String, ArrayList<String>> testMap = new HashMap<>();

			String fileString = Files.readString(Path.of("src/main/data/stop_times.txt"));
			fileString
					.lines()
					.skip(1)
					.map(line -> line.split(","))
					.filter(lineArray -> lineArray[3].equals(fromStation) || lineArray[3].equals(toStation))
					//.peek(System.out::println)
					.forEach(lineArray -> {
						if(lineArray[3].equals(fromStation)){
							if(!testMap.containsKey(lineArray[0])){
								ArrayList<String> stop = new ArrayList();
								stop.add(lineArray[3] + "," + lineArray[2]);
								testMap.put(lineArray[0], stop);
							}
							else{
								testMap.get(lineArray[0]).add(lineArray[3] + "," + lineArray[2]);
							}
						}
						else{
							String[] arrivalTimeArray = lineArray[1].split(":");

							Boolean later = false;
							if(Integer.valueOf(arrivalTimeArray[0]) >= Integer.valueOf(fromTimeArray[0])){
								if(Integer.valueOf(arrivalTimeArray[1]) >= Integer.valueOf(fromTimeArray[1])){
									if(Integer.valueOf(arrivalTimeArray[2]) >= Integer.valueOf(fromTimeArray[2])){
										later = true;
									}
								}
							}
							Boolean earlier = false;
							if(Integer.valueOf(arrivalTimeArray[0]) < Integer.valueOf(toTimeArray[0])){
								earlier = true;
							}
							else if(Integer.valueOf(arrivalTimeArray[0]) == Integer.valueOf(toTimeArray[0])){
								if(Integer.valueOf(arrivalTimeArray[1]) < Integer.valueOf(toTimeArray[1])){
									earlier = true;
								}
								else if(Integer.valueOf(arrivalTimeArray[1]) == Integer.valueOf(toTimeArray[1])){
									if(Integer.valueOf(arrivalTimeArray[1]) <= Integer.valueOf(toTimeArray[1])) {
										earlier = true;
									}
								}
							}

							if(later && earlier){
								if(!testMap.containsKey(lineArray[0])){
									ArrayList<String> stop = new ArrayList();
									stop.add(lineArray[3] + "," + lineArray[1]);
									testMap.put(lineArray[0], stop);
								}
								else{
									testMap.get(lineArray[0]).add(lineArray[3] + "," + lineArray[1]);
								}
							}
						}


					});
			/*
			testMap.entrySet().stream()
					.filter(x -> x.getValue().size() == 2 && x.getValue().get(0).equals(fromStation))
					.forEach(x -> {
						System.out.print(x.getKey() + ": ");
						x.getValue().forEach(value -> System.out.print(value + " "));
						System.out.println();
					});
			*/

			testMap.entrySet().stream()
					.filter(x -> x.getValue().size() == 2 && x.getValue().get(0).split(",")[0].equals(fromStation))
					.forEach(x -> validTrips.add(x.getKey() + "," + x.getValue().get(0).split(",")[1] + "," + x.getValue().get(1).split(",")[1]));

		} catch (Exception e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return validTrips;
	}

	public static int[] getStationIds(String fromStationName, String toStationName){
		int[] stationIds = {0,0};

		for (Station station : stations) {
			if(station.getName().equals(fromStationName))
				stationIds[0] = station.getId();
			else if(station.getName().equals(toStationName))
				stationIds[1] = station.getId();
		}


		return stationIds;
	}

	public static ArrayList<String> getDummyData(String tripId){
		ArrayList<String> dummyData = new ArrayList<>();

		try{
			String fileString = Files.readString(Path.of("src/main/data/dummyData.txt"));
			fileString
					.lines()
					.skip(1)
					.map(line -> line.split(","))
					.filter(lineArray -> lineArray[0].equals(tripId))
					//.peek(System.out::println)
					.forEach(lineArray -> {
						dummyData.add(lineArray[0] + "," + lineArray[1] + "," + lineArray[2] + "," + lineArray[3]);
						});
		}catch (Exception e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		return dummyData;
	}


}