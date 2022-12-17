import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class UI{

	private static int width = 400;
	private static int height = 700;

	public static void buildHomepage(){
		JFrame loginPage = new JFrame("homepage");

		//Login label
		JLabel loginLabel = new JLabel("search trip");
		loginLabel.setBounds(20,50,400,100);
		loginLabel.setFont(new Font("Serif",Font.PLAIN,60));
		loginPage.add(loginLabel);

		//fra field
		JTextField fromField = new JTextField();
		JLabel fromLabel = new JLabel("fra:");
		fromLabel.setBounds(20,160, 80,30);
		fromField.setBounds(100,160,100,30);
		loginPage.add(fromField);  loginPage.add(fromLabel);

		//til field
		JTextField toField = new JTextField();
		JLabel toLabel = new JLabel("til:");
		toLabel.setBounds(20,200, 80,30);
		toField.setBounds(100,200,100,30);
		loginPage.add(toField);  loginPage.add(toLabel);

		//tid fields
		JTextField fromtimeField = new JTextField();
		JTextField totimeField = new JTextField();
		JLabel timeLabel = new JLabel("tidsinterval:");
		timeLabel.setBounds(20,240, 80,30);
		fromtimeField.setBounds(100,240,100,30);
		totimeField.setBounds(200,240,100,30);
		loginPage.add(fromtimeField); loginPage.add(totimeField);  loginPage.add(timeLabel);


		JLabel wrongInputLabel = new JLabel();
		wrongInputLabel.setBounds(100,320,300,30);
		loginPage.add(wrongInputLabel);

		//search button
		JButton searchButton = new JButton("Search");
		searchButton.setBounds(100,280,100,30);
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
		loginPage.add(searchButton);


		loginPage.dispatchEvent(new WindowEvent(loginPage, WindowEvent.WINDOW_CLOSING));
		loginPage.setSize(width, height);
		loginPage.setLayout(null);
		loginPage.setVisible(true);
	}
}