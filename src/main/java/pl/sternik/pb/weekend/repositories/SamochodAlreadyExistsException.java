package pl.sternik.pb.weekend.repositories;

public class SamochodAlreadyExistsException extends Exception {
    private static final long serialVersionUID = -4576295597218170159L;

    public SamochodAlreadyExistsException() {     
    }

    public SamochodAlreadyExistsException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public SamochodAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public SamochodAlreadyExistsException(String message) {
        super(message);
    }

    public SamochodAlreadyExistsException(Throwable cause) {
        super(cause);
    }
    
}
