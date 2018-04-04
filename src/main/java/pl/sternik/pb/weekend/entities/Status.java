package pl.sternik.pb.weekend.entities;


public enum Status {
    
    NOWA("Nowe"), 
    UZYWANA("Uzywane"),
    USZKODZONA("Uszkodzone");
    
    
    public static final Status[] ALL = { NOWA, UZYWANA, USZKODZONA };
    
    
    private final String name;

    private Status(final String name) {
    	this.name = name;
    }
    
//    public static Status forName(final String name) {
//        if (name == null) {
//            throw new IllegalArgumentException("Nie mozna nula dla Status");
//        }
//        if (name.equalsIgnoreCase("NOWA")) {
//            return NOWA;
//        } else if (name.equalsIgnoreCase("DO_SPRZEDANIA")) {
//            return Status.DO_SPRZEDANIA;
//        }
//        throw new IllegalArgumentException("Nazwa \"" + name + "\" nie pasuje do zadengo Statusu");
//    }
//    
    
    public String getName() {
        return this.name;
    }
}
