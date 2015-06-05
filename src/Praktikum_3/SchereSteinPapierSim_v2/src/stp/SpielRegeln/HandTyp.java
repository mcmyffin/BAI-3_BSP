package stp.SpielRegeln;

/**
 *
 * @author dima
 */
public enum HandTyp {

    SCHERE("Schere"),
    STEIN("Stein"),
    PAPIER("Papier");
    
    private String value;
    
    private HandTyp(String value){
        this.value = value;
    }
    
    public String getValue(){
        return this.value;
    }
}
