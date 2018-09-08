import java.util.*;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

// What operations will be done on SymTable ?
// 

public class SymTable {
    /*
    - Represent a symbol table: a data structure that stores the identifiers
    declared in the program being compiled and information about each 
    identifier (its type, where it will be stored at runtime)

    - Implemened as list of HashMaps
    - each HashMap will store the identifiers declared in one scope in the program
    - HashMap key will be String and value will be Syms
    */

    // so we have 2 options in here
    // either using ArrayList or Linked List
    // So in my note I wrote using Linked List so it's probably right
    // so why do we use Linked List of hash table in here?
    private List<HashMap<String,Sym>> symTable;

    public SymTable() { 
        // initialize the SymTable's List field to contain a single, empty HashMap.
        symTable = new LinkedList​<HashMap<String,Sym>>();
    }
    public addDec1(String name, Sym sym) throws DuplicateSymException, 
           EmptySymTableException {

        // If this SymTable's list is empty, throw an EmptySymTableException. 
        if (symTable.isEmpty()) {
            throw EmptyStackException;
        }

        // If either name or sym (or both) is null, throw a NullPointerException. 
        if (name == null || sym == null) {
            throw NullPointerException;
        }

        // If the first HashMap in the list already contains the given name as a key, 
        // throw a DuplicateSymException.
        firstHashMap = symTable.get(0);
        if (firstHashMap.containsKey(name)) {
            throw DuplicateSymException;
        }
        // Otherwise, add the given name and sym to the first HashMap in the list.
        firstHashMap.put(name, sym);
    }

    public addScope() {
        // add a new, empty Hashmap to front of the list
        HashMap<String,Sym> newMap = new HashMap<String,Sym>();
        symTable.add(0, newMap);
    }

    public Sym lookupLocal (String name) {
        // If this SymTable's list is empty, throw an EmptySymTableException. 
        if (symTable.isEmpty()) {
            throw EmptyStackException;
        }
        // Otherwise, if the first HashMap in the list contains name as a key, return the associated Sym; 
        else if (symTable.get(0).containsKey(name)) {
            return symTable.get(0).get(name);
        }
         // otherwise, return null.
        else {
            return null;
        }

    public Sym lokupGlobal (String name) {
        // If this SymTable's list is empty, throw an EmptySymTableException. 
        if (symTable.isEmpty()) {
            throw EmptyStackException;
        }

        isExist = false;
        closestHashMap = null;
        // If any HashMap in the list contains name as a key, 
        // return the first associated Sym (i.e., the one from the 
        //HashMap that is closest to the front of the list); 
        for (HashMap temp : symTable) {
            if (temp.containsKey(name)) {
                isExist = true;
                closestHashMap = temp; // Does this guaratnee the cloest to the front?
            }
        }
          
        if (symTable.isExist()) {
            return closestHashMap.get(name);
        }
        // otherwise, return null.
        else return null;
        
    }

    public void removeScope() throws EmptyStackException {
        // If this SymTable's list is empty, throw an EmptySymTableException;
        //  otherwise, remove the HashMap from the front of the list. 
        //  To clarify, throw an exception only if before attempting to remove, 
        //  the list is empty (i.e. there are no HashMaps to remove). 
        if (symTable.isEmpty()) throw EmptySymTableException;
        else {
            symTable.remove(0);
        }
    }

    public void print() {
        // This method is for debugging. First, print “\nSym Table\n”. 
        // Then, for each HashMap M in the list, print M.toString() 
        //followed by a newline. Finally, print one more newline. 
        // All output should go to System.out.
        System.out.println("\nSym Table\n");
        for (HashMap M: symTable) {
            System.out.println(M.toString());
        }
    }
}


