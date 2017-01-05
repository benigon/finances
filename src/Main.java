import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.util.stream.Stream;
import java.time.format.DateTimeFormatter;

public class Main {

	private static final String DIR = "/home/bgonzalez/csv";
	private static final String SEPARATOR = ",";
	private static final int AMOUNT_POS = 3;
	private static final int CONCEPT_COLUMN = 5;
	private static final int TYPE_COLUMN = 4;
	private static final int DATE_COLUMN = 1;
	
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

	// Return a list of movements with the information from the files
	private static List<Movement> fillData(List<String> files) {
		List<Movement> movements = new ArrayList<Movement>();
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
				br.readLine(); // skip the first line

				while ( (line = br.readLine()) != null ) {
					data = line.split(SEPARATOR);
					amount = Double.parseDouble(data[AMOUNT_POS]);
					date = LocalDate.parse(data[DATE_COLUMN], formatter);
					type = TypeMovement.valueOf(data[TYPE_COLUMN]);

					movements.add(new Movement(Math.abs(amount), data[CONCEPT_COLUMN], type, date, amount > 0));
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
	
	public static void main(String[] args) {
		List<String> files = readFiles(DIR);
		List<Movement> movements = fillData(files);
		
		Measurement measurement = new Measurement(movements);
		System.out.println(measurement.getTotalExpense());
		System.out.println(measurement.getTotalRevenue());

	}

}
