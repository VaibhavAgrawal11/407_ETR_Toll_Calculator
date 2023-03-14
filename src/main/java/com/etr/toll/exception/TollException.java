package com.etr.toll.exception;

public class TollException extends Exception {
	private static final long serialVersionUID = 1L;

	public enum TollErrorType {
	    LOCATION_NOT_FOUND("Location not found"),
	    INVALID_LOCATION_IDS("Invalid location IDs"),
	    ROUTE_NOT_FOUND("Route not found"),
		FILE_NOT_FOUND("Invalid file location"),
		INVAID_JSON_MAPPING("Invalid JSON mapping");
		
	    private final String message;

	    TollErrorType(String message) {
	        this.message = message;
	    }

	    public String getMessage() {
	        return message;
	    }
	}
	
	public TollErrorType type;

    public TollException(TollErrorType type, String message) {
        super(message);
        this.type = type;
    }
    
}
