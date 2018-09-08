public class Sym {
    /*
    Store value of HashTable as type of the identifier, represented using
    a String ('int', 'double')
    */

    public String SymS;

    public Sym (String type) {
        
        // constructor: initialize the sym to given type
        this.SymS = type;
    }

    public String getType() {
        // Return this Sym's type.
        return SymS;
    }

    public String toString() {
        // Return this Sym's type.
        return SymS;
    }
}