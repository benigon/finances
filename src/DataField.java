public class DataField {
	private String directory;
	private String amountName;
	private String conceptName;
	private String typeName;
	private String dateName;
	
	public DataField(String directory, String amountName, String conceptName, String typeName, String dateName) {
		this.directory = directory;
		this.amountName = amountName;
		this.conceptName = conceptName;
		this.typeName = typeName;
		this.dateName = dateName;
	}

	public String getDirectory() {
		return directory;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}

	public String getAmountName() {
		return amountName;
	}

	public void setAmountName(String amountName) {
		this.amountName = amountName;
	}

	public String getConceptName() {
		return conceptName;
	}

	public void setConceptName(String conceptName) {
		this.conceptName = conceptName;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getDateName() {
		return dateName;
	}

	public void setDateName(String dateName) {
		this.dateName = dateName;
	}

}
