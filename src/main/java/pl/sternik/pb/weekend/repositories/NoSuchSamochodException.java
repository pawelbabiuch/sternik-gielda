package pl.sternik.pb.weekend.repositories;

public class NoSuchSamochodException extends Exception {
    private static final long serialVersionUID = -8555511053844242536L;

    public NoSuchSamochodException(String string) {
		super(string);
	}

	public NoSuchSamochodException() {
	}


}
