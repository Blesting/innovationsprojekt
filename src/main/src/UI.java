import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicInteger;

public class UI{

	private static int width = 400;
	private static int height = 700;

	public static void buildHomepage(){
		JFrame homePage = new JFrame("homepage");

		//Search trip label
		JLabel searchTripLabel = new JLabel("search trip");
		searchTripLabel.setBounds(20,20,400,100);
		searchTripLabel.setFont(new Font("Serif",Font.PLAIN,60));
		homePage.add(searchTripLabel);

		//fra field
		JTextField fromField = new JTextField();
		JLabel fromLabel = new JLabel("fra:");
		fromLabel.setBounds(20,160, 80,30);
		fromField.setBounds(100,160,100,30);
		homePage.add(fromField);  homePage.add(fromLabel);

		//til field
		JTextField toField = new JTextField();
		JLabel toLabel = new JLabel("til:");
		toLabel.setBounds(20,200, 80,30);
		toField.setBounds(100,200,100,30);
		homePage.add(toField);  homePage.add(toLabel);

		//tid fields
		JTextField fromtimeField = new JTextField();
		JTextField totimeField = new JTextField();
		JLabel timeLabel = new JLabel("tidsinterval:");
		timeLabel.setBounds(20,240, 80,30);
		fromtimeField.setBounds(100,240,100,30);
		totimeField.setBounds(200,240,100,30);
		homePage.add(fromtimeField); homePage.add(totimeField);  homePage.add(timeLabel);


		JLabel wrongInputLabel = new JLabel();
		wrongInputLabel.setBounds(100,320,300,30);
		homePage.add(wrongInputLabel);

		//search button
		JButton searchButton = new JButton("Search");
		searchButton.setBounds(100,280,100,30);
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String fromTime = fromtimeField.getText();
				String toTime = totimeField.getText();

				int[] stationIds = main.getStationIds(fromField.getText(), toField.getText());
				int fromStationId = stationIds[0];
				int toStationId = stationIds[1];

				if(fromStationId == toStationId)
					wrongInputLabel.setText("to and from station cannot be the same");
				else if(fromStationId == 0 || toStationId == 0)
					wrongInputLabel.setText("at least one station doesn't exist");
				else if(fromTime.isBlank() || toTime.isBlank())
					wrongInputLabel.setText("you must enter a time");
				else if(!(isValidTime(fromTime) && isValidTime(toTime))){
					wrongInputLabel.setText("time must be of correct format (HH:MM:SS) and valid");
				}
				else
					wrongInputLabel.setText(":)");

				if(wrongInputLabel.getText().equals(":)")){
					homePage.dispose();
					buildMainPage(fromStationId, toStationId, fromTime, toTime);
				}
			}
		});
		homePage.add(searchButton);


		homePage.dispatchEvent(new WindowEvent(homePage, WindowEvent.WINDOW_CLOSING));
		homePage.setSize(width, height);
		homePage.setLayout(null);
		homePage.setVisible(true);
	}

	private static boolean isValidTime(String input){

		String[] inputArray = input.split(":");
		if(inputArray.length != 3)
			return false;

		String validChars = ":0123456789";
		for(int i = 0; i < input.length(); i++){
			if(!validChars.contains(String.valueOf(input.charAt(i))))
				return false;
		}

		int[] inputIntArray = new int[3];
		for(int i = 0; i < 3; i++){
			inputIntArray[i] = Integer.valueOf(inputArray[i]);
		}

		if(inputIntArray[0] < 0 || inputIntArray[0] > 23 || inputIntArray[1] < 0 || inputIntArray[1] > 59  || inputIntArray[2] < 0 || inputIntArray[2] > 59)
			return false;


		return true;
	}

	public static void buildMainPage(int fromStation, int toStation, String fromTime, String toTime){
		JFrame mainPage = new JFrame("Main page");

		//valid trips label
		JLabel validTripsLabel = new JLabel("Valid trips");
		validTripsLabel.setBounds(20,20,400,100);
		validTripsLabel.setFont(new Font("Serif",Font.PLAIN,60));

		//Valid trips table
		ArrayList<String> validTrips = main.getValidTrips(fromStation, toStation, fromTime, toTime);

		String[] fields = {"departure", "arrival", "Price", "ESB", "Buy ticket"};
		Object[][] data = new Object[(int) validTrips.size()][5];

		//Fills data table with items
		int index = 0;
		for (String trip : validTrips){
			String[] tripArray = trip.split(",");
			String seatBought = "";
			ArrayList<String> seatData = main.getDummyData(tripArray[0]);
			if(seatData.size() == 0){
				seatBought = "No data";
			}
			else{
				double average = 0.0;
				for (String seatDatum : seatData) {
					String[] seatDatumArr = seatDatum.split(",");
					average = average + Double.valueOf(seatDatumArr[3])/Double.valueOf(seatDatumArr[2]);
				}
				average = average / seatData.size() * 100;
				seatBought = String.valueOf(average);
			}
			data[index][0] = tripArray[1];
			data[index][1] = tripArray[2];
			data[index][2] = "$$$";
			data[index][3] = seatBought;
			data[index][4] = "Button";
			index ++;
		}
		JTable table = new JTable(data, fields){
			//No columns should be editable
			public boolean isCellEditable(int row, int column){ return false; }
		};
		JScrollPane scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);

		//back button
		JButton backButton = new JButton("<-");
		backButton.setBounds(10,10,50,30);
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainPage.dispose();
				buildHomepage();
			}
		});
		JPanel top = new JPanel();
		top.add(backButton);
		top.add(validTripsLabel);
		mainPage.add(top, BorderLayout.NORTH);
		mainPage.add(scrollPane, BorderLayout.CENTER);


		mainPage.dispatchEvent(new WindowEvent(mainPage, WindowEvent.WINDOW_CLOSING));
		mainPage.setSize(width, height);
		mainPage.setVisible(true);
	}
}