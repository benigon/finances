import java.util.ArrayList;
import java.util.List;

public class Measurement {
	private List<Movement> movements = new ArrayList<Movement>();

	public Measurement(List<Movement> movements) {
		this.movements = movements;
	}
	
	public double getTotalExpense() {
		
		double total = 0;
		for (Movement movement:this.movements) {
			if (!movement.isRevenue()) {
				total += movement.getAmount();
			}
		}
		return total;
	}

	public double getTotalRevenue() {
		
		double total = 0;
		for (Movement movement:this.movements) {
			if (movement.isRevenue()) {
				total += movement.getAmount();
			}
		}
		return total;
	}

}
