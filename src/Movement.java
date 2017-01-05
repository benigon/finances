import java.time.LocalDate;

public class Movement {

	private double amount;
	private String concept;
	private TypeMovement type;
	private LocalDate date;
	private boolean isRevenue;

	public Movement(double amount, String concept, TypeMovement type, LocalDate date, boolean isRevenue) {
		super();
		this.amount = amount;
		this.concept = concept;
		this.type = type;
		this.date = date;
		this.isRevenue = isRevenue;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getConcept() {
		return concept;
	}

	public void setConcept(String concept) {
		this.concept = concept;
	}

	public TypeMovement getType() {
		return type;
	}

	public void setType(TypeMovement type) {
		this.type = type;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public boolean isRevenue() {
		return isRevenue;
	}

	public void setRevenue(boolean isRevenue) {
		this.isRevenue = isRevenue;
	}

}
