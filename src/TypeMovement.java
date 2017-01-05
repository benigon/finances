public enum TypeMovement {
	FT, CASH, DIRECTDEBIT, PAYMENT, DIRECTDEP, BCC, OTH, OTHER;

	public static TypeMovement fromString(String text) {
	    if (text != null) {
	      for (TypeMovement b : TypeMovement.values()) {
	        if (b.toString() == text) {
	          return b;
	        }
	      }
	    }
	    return TypeMovement.OTHER;
	}
}
