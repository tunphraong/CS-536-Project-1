import java.util.*;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

public class SymTable {

    private List<HashMap<String,Sym>> symTable;

    public SymTable() { 
        // initialize the SymTable's List field to contain a single, empty HashMap.
        HashMap<String,Sym> map = new HashMap<String,Sym>();
        symTable = new LinkedList​<HashMap<String,Sym>>();
        symTable.add(map);
    }


    /**
     * @param name: name of the variable
     * @param sym: type associated with the variable
     * @throws DuplicateSymException  [already contains the given name as a key]
     * @throws EmptySymTableException [If this SymTable's list is empty]
     */
    public void addDec1(String name, Sym sym) throws DuplicateSymException, 
           EmptySymTableException {

        // If this SymTable'slist  is empty, throw an EmptySymTableException. 
        if (symTable.isEmpty()) {
            throw new EmptyStackException();
        }

        // If either name or sym (or both) is null, throw a NullPointerException. 
        else if (name == null || sym == null) {
            throw new NullPointerException();
        }

        HashMap<String,Sym> firstHashMap = symTable.get(0);
        if (firstHashMap.containsKey(name)) {
            throw new DuplicateSymException();
        }
        // Otherwise, add the given name and sym to the first HashMap in the list.
        firstHashMap.put(name, sym);
        symTable.set(0, firstHashMap); // replace old hash map with new
    }

    /**
     *add a new, empty Hashmap to front of the list
     */
    public void addScope() {
        HashMap<String,Sym> newMap = new HashMap<String,Sym>();
        symTable.add(0, newMap);
    }

    /**
     @param name: name to search
     @return the sym that contains name in the first HashMap
    */
    public Sym lookupLocal (String name) throws EmptySymTableException {
        // If this SymTable's list is empty, throw an EmptySymTableException. 
        if (symTable.isEmpty()) {
           throw new EmptySymTableException();
        }   
        
        // Otherwise, if the first HashMap in the list contains name as a key, return the associated Sym; 
        else if (symTable.get(0).containsKey(name)) {
            return symTable.get(0).get(name);
        }
         // otherwise, return null.
        return null;
    }

    /**
     * @param name: name to search in the entire list
     * @return
     */
    public Sym lookupGlobal (String name) throws EmptySymTableException {
        // If this SymTable's list is empty, throw an EmptySymTableException. 
        if (symTable.isEmpty()) {
            throw new EmptySymTableException();
        }

        boolean isExist = false;
        HashMap<String, Sym> closestHashMap = symTable.get(0);
        // System.out.println("first hash map" +  closestHashMap);
        for (HashMap temp : symTable) {
            if (temp.containsKey(name)) {
                isExist = true;
                closestHashMap = temp; // Does this guaratnee the cloest to the front?
                break;
            }
        }
          
        if (isExist == true) {
            return closestHashMap.get(name);
        }
        // otherwise, return null.
        return null;
    }

    /**
     * @throws EmptyStackException if symTable is empty
     */
    public void removeScope() throws EmptySymTableException {
        // If this SymTable's list is empty, throw an EmptySymTableException;
        //  otherwise, remove the HashMap from the front of the list. 
        //  To clarify, throw an exception only if before attempting to remove, 
        //  the list is empty (i.e. there are no HashMaps to remove). 
        if (symTable.isEmpty()) {
            throw new EmptySymTableException();
        }
        else {
            symTable.remove(0);
        }
    }

    /**
     * Print out the list of HashMaps
     */
    public void print(){
        System.out.println("\nSym Table\n");
        //Iterator<HashMap<String, Sym>> itr = symTable.iterator();
        // while(itr.hasNext()){
        //     HashMap<String, Sym> M = itr.next();
        //     System.out.println(M.toString());
        // }
        for (HashMap M: symTable) {
            System.out.println(M.toString());
        }
    } // end print
}


