package laszlo.airports;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.List;


import javax.swing.*;
/**
 * Class that creates the GUI that extracts information about the csv files.
 * Also contains the main method to run the GUI. Class is dependent 
 * on HashMapping, Airports, Runways & Countries classes.
 * 
 * @author laszlo
 *
 */
public class GUI implements ActionListener {

	private String pathAirports = "C:\\Users\\laszlo\\eclipse-workspace\\Airports\\resources\\airports.csv";;
	private String pathRunways ="C:\\Users\\laszlo\\eclipse-workspace\\Airports\\resources\\runways.csv";
	private String pathCountries = "C:\\Users\\laszlo\\eclipse-workspace\\Airports\\resources\\countries.csv";
	private String pathTextFile =	"C:\\Users\\laszlo\\eclipse-workspace\\Airports\\output.txt";
	
	private JFrame frame;
	private JPanel panel;

	private JButton buttonHighestAirports;

	private JLabel labelRunwayPath;
	private JButton setRunwayButton;
	private JLabel labelAirportPath;
	private JButton setAirportButton;
	private JLabel labelCountriesPath;
	private JButton setCountriesButton;
	private JLabel labelOutputPath;
	private JButton setOutputButton;
	private JTextPane outPutField;

	private JButton buttonAirportRunways;
	private JTextField inputFieldCountry;	

	public GUI() {
		frame = new JFrame();
		
		inputFieldCountry = new JTextField("enter countrycode", 10);
		buttonAirportRunways = new JButton("Get airports and runways of a country");
		buttonAirportRunways.addActionListener(this);

		buttonHighestAirports = new JButton("Get countries with the most airports in the world");
		buttonHighestAirports.addActionListener(this);

		labelRunwayPath = new JLabel(pathRunways);
		setRunwayButton = new JButton("Set path runway csv");
		setRunwayButton.addActionListener(this);

		labelAirportPath = new JLabel(pathAirports);
		setAirportButton = new JButton("Set path airport csv");
		setAirportButton.addActionListener(this);
		
		labelCountriesPath = new JLabel(pathCountries);
		setCountriesButton = new JButton("Set path countries csv");
		setCountriesButton.addActionListener(this);
		
		labelOutputPath = new JLabel(pathTextFile);
		setOutputButton = new JButton("Set path output text file");
		setOutputButton.addActionListener(this);
				
		outPutField = new JTextPane();
		
		panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
		panel.setLayout(new GridLayout(0, 1));
		
		panel.add(inputFieldCountry);
		panel.add(buttonAirportRunways);
		panel.add(buttonHighestAirports);
		panel.add(setRunwayButton);
		panel.add(labelRunwayPath);
		panel.add(setAirportButton);
		panel.add(labelAirportPath);
		panel.add(setCountriesButton);
		panel.add(labelCountriesPath);
		panel.add(setOutputButton);
		panel.add(labelOutputPath);
		panel.add(outPutField);

		frame.add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("GUI");
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		GUI gui = new GUI();
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == buttonHighestAirports) {
			HashMap<String, Integer> AirportsCountries = Airports.getAirportsCountries(this.pathAirports, this.pathCountries);		
			List<Entry<String, Integer>> sortedList = HashMapping.sortHashMap(AirportsCountries);
			String[] countryIds = HashMapping.lastKeys(sortedList, 10);
			String[] countryNames = Countries.getCountryNames(countryIds,pathCountries);			
			String names = "";
			for(String name : countryNames) {
				names = names + " " + name;
			}
			outPutField.setText(names);
		}
		if (event.getSource() == buttonAirportRunways) {
			String countryCode = Countries.getCountryCode(this.inputFieldCountry.getText(), this.pathCountries);
			if (countryCode.equals("NOT FOUND")) {
				outPutField.setText("Country not found");
			}else {
				HashMap<String, String> airportIdentifiersNames =  Airports.getAirportIdentifiersNames(countryCode, this.pathAirports);
				HashMap<String, String> airportsRunways = Runways.getAirportsRunways(airportIdentifiersNames, this.pathRunways);
				String text = HashMapping.formatHashMap(airportsRunways);
				try {
				    Files.write(Paths.get(pathTextFile), text.getBytes(), StandardOpenOption.APPEND);
				}catch (IOException exception) {
					outPutField.setText("Something went wrong writing to the text file");
				}
				outPutField.setText("See text file for airports and their runways id's");
			}
		}
		
		if (event.getSource() == setRunwayButton || event.getSource() == setCountriesButton || event.getSource() == setAirportButton || event.getSource() == setOutputButton) {
			JFileChooser fileChooser = new JFileChooser();
			int response = fileChooser.showOpenDialog(null); 

			if (response == JFileChooser.APPROVE_OPTION) {
				File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
				if (event.getSource() == setRunwayButton) {
					this.pathRunways = file.getPath();
					labelRunwayPath.setText(this.pathRunways);
				}
				if (event.getSource() == setCountriesButton) {
					this.pathCountries = file.getPath();
					labelCountriesPath.setText(this.pathCountries);
				}
				if (event.getSource() == setAirportButton) {
					this.pathAirports = file.getPath();
					labelAirportPath.setText(this.pathAirports);
				}
				if (event.getSource() == setOutputButton) {
					this.pathTextFile = file.getPath();
					labelOutputPath.setText(this.pathTextFile);
				}
				
			}
		}
	}
}
