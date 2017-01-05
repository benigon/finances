import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.time.LocalDate;
import java.util.stream.Stream;
import java.time.format.DateTimeFormatter;

public class Main {
	private static final String SEPARATOR = ",";
	
	// Get all file names from a specific directory
	private static List<String> readFiles(String directory) {
		
		List<String> files = new ArrayList<String>();
		try(Stream<Path> paths = Files.walk(Paths.get(directory))) {
			paths.forEach(filePath -> {
				if (Files.isRegularFile(filePath)) {
					files.add(filePath.toString());
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return files;
	}

	private static int getPos(String line, String column) {
		String[] datas = line.split(SEPARATOR);
		int index = 0;

		for (String data:datas) {
			if (data.compareTo(column) == 0)
				break;
			else
				index++;
		}
		return index;
	}
	
	// Return a list of movements with the information from the files
	private static List<Movement> fillData(List<String> files, DataField dataField) {
		List<Movement> movements = new ArrayList<Movement>();
		int amountPos, datePos, typePos, conceptPos;
		String line = null;
		String[] data;
		BufferedReader br;
		double amount;
		LocalDate date;
		TypeMovement type;
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
		for (String file:files) {
			try {
				br = new BufferedReader(new FileReader(file));
				line = br.readLine();
				amountPos = getPos(line, dataField.getAmountName());
				datePos = getPos(line, dataField.getDateName());
				typePos = getPos(line, dataField.getTypeName());
				conceptPos = getPos(line, dataField.getConceptName());
				
				while ( (line = br.readLine()) != null ) {
					data = line.split(SEPARATOR);
					amount = Double.parseDouble(data[amountPos]);
					date = LocalDate.parse(data[datePos], formatter);
					type = TypeMovement.fromString(data[typePos]);
					movements.add(new Movement(Math.abs(amount), data[conceptPos], type, date, amount > 0));
				}
				br.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return movements;
	}
	
	public static DataField readProperties() {
		Properties prop = new Properties();
		InputStream input = null;
		DataField dataField = null;

		try {
			input = new FileInputStream("src/config.properties");
			// load a properties file
			prop.load(input);
			String directory = prop.getProperty("directory");
			String amountName = prop.getProperty("amount_column");
			String dateName = prop.getProperty("date_column");
			String conceptName = prop.getProperty("concept_column");
			String typeName = prop.getProperty("category_column");
			dataField = new DataField(directory, amountName, conceptName, typeName, dateName);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return dataField;
	}
	
	public static void main(String[] args) {
		
		
		DataField dataField = readProperties();
		
		List<String> files = readFiles(dataField.getDirectory());
		files.forEach(file -> System.out.println(file));
		List<Movement> movements = fillData(files, dataField);
		
		Measurement measurement = new Measurement(movements);
		System.out.println(measurement.getTotalExpense());
		System.out.println(measurement.getTotalRevenue());

	}

}
