package raghavan.query;

public enum QueryType {

	DID("Did"), WHO("Who");

	private String value;

	private QueryType(String value) {
		this.value = value;
	}

	public static QueryType getEnumFromString(String value) {
		for (QueryType s : values()) {
			if (s.value.equals(value))
				return s;
		}
		return null;
	}
}
