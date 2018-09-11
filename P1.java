import java.util.*;

// import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;
// import com.sun.org.apache.xerces.internal.util.SymbolTable;

public class P1 {
    public static void main(String [] args) {


        // Test Sym class first
        
        String[] test_list = {"int", "String", "boolean", ""};

        for (String temp: test_list) {
            Sym firstSym = new Sym(temp);

            if (firstSym.getType() != temp) {
                System.out.println("getType produces wrong result");
            }

            if (firstSym.toString() != temp) {
                System.out.println("getType produces wrong result");
            }

        }


        // test SymTable class
        // 
        // test exception throw on removeScope
        SymTable table;
        try {
            table = new SymTable();
            table.removeScope();  
            // we have 1 HashMap afer calling constructor
            // so this shouldn't throw an error
            try {
                table.removeScope(); 
                // we don't have any HashMap to remove
                // this should throw EmptySymTableException
            } catch (EmptySymTableException e) {
            } catch (Exception e) {
                System.out.println("Wrong exception thrown");
            }
        } catch (EmptySymTableException e) {
            System.out.println("after calling SymTable constructor");
        } catch (Exception e) {
            System.out.println("thrown after calling SymTable constructor");
        }

        table = new SymTable();

        // test addDec1
        // test adding null to addDec
        try {
           
            table.addDec1(null,null);
        } catch (NullPointerException e) {
            // expected
            // System.out.println("Go here");
        } catch (Exception e) {
            System.out.println("Wrong exception thrown");
        }

        // test adding normal data to addDec
        try {
            Sym firstSym = new Sym("boolean");
            table.addDec1("firstVarible", firstSym);
            Sym secondSym = new Sym("int");
            table.addDec1("secondVariable", secondSym);
        }
        catch (Exception e) {
            System.out.println("Wrong exception thrown");
        }

        // test adding duplicate variable to HashMap
        try {
            Sym secondSym = new Sym("int");
            table.addDec1("firstVarible", secondSym);
        } catch (DuplicateSymException e) {
            // System.out.println("Duplicate");
            // expected
        } catch (Exception e) {
            System.out.println("Wrong exception thrown");
        }
        
        // test adding Scope
        try {
            table.addScope();
        } catch (Exception e) {
            System.out.println("Wrong exception thrown on adding scope");
        }

        // test adding some new data to the new HashMap
        try {
            Sym firstSym = new Sym("float");
            table.addDec1("firstVarible", firstSym);
            Sym secondSym = new Sym("int");
            table.addDec1("secondVariable", secondSym);
             Sym thirdSym = new Sym("double");
            table.addDec1("thirdVariable", thirdSym);
        } catch (Exception e) {
            System.out.println("Wrong exception thrown");
        }



        // test lookupLocal
        SymTable newTable;

        // test lookupLocal and lookupGlobal with emptyTable
        try {
            newTable = new SymTable();
            newTable.removeScope();
            try {
                newTable.lookupLocal("firstVarible");
            } catch (EmptySymTableException e){
                // expected
                // System.out.println("expected isEmpty");
            } catch (Exception e){
             System.out.println("Wrong exception thrown");   
            }

            try {
                newTable.lookupGlobal("firstVarible");   
            } catch (EmptySymTableException e){
                // expected
                // System.out.println("expected isEmpty");
            } catch (Exception e){
             System.out.println("Wrong exception thrown");   
            }

        } catch (EmptySymTableException e) {
            System.out.println("after calling SymTable constructor");
        } catch (Exception e){
            System.out.println("Wrong exception thrown");   
        }

        // test lookupLocal with normal data
        newTable = new SymTable();
        try {

            Sym firstSym = new Sym("boolean");
            newTable.addDec1("firstVarible", firstSym);
            Sym secondSym = new Sym("int");
            newTable.addDec1("secondVariable", secondSym);
            Sym thirdSym = new Sym("double");
            newTable.addDec1("thirdVariable", thirdSym);

            // test look up item IS in the HashMap
            Sym inHashMap = newTable.lookupLocal("firstVarible");
            if (inHashMap != firstSym) {
                System.out.println("lookupLocal produces wrong result");
            }
            // test look up item NOT in the HashMap
            // 
            Sym random = newTable.lookupLocal("randomVariable");
            if (null != random) {
                System.out.println("lookupLocal produces wrong result");
            }

        } catch (Exception e) {
                System.out.println("Wrong exception thrown");   
        }

        // test lookupGlobal
        // table.print();
        // previously, we have a table that looks like
        // {thirdVariable=double, secondVariable=int, firstVarible=float}
        // {secondVariable=int, firstVarible=boolean}
        
        try {
            //Sym temp = table.lookupGlobal("firstVarible");
            Sym lookUp = new Sym("int");
            System.out.println(table.lookupGlobal("secondVariable"));
            System.out.println(lookUp);
            if (table.lookupGlobal("firstVarible") != lookUp) {
                System.out.println("lookupGlobal produces wrong result");
            }
            
            
            // test for non-existent data
            Sym random = table.lookupGlobal("randomVariable");
            if (null != random) {
                System.out.println("lookupGlobal produces wrong result");   
            }

        } catch (Exception e) {
            System.out.println("Wrong exception thrown");   
        }

        // testing PRINT
        //
        // print using one empty HashMap, several Hashmaps and no HashMap
        SymTable test_print = new SymTable();
        try {
            System.out.println("List with 1 empty HashMap");
            test_print.print();
            try {
                test_print.addDec1("firstVarible", new Sym("int"));
                test_print.addDec1("secondVariable", new Sym("boolean"));
                test_print.addDec1("thirdVariable", new Sym("float"));
                test_print.addScope();
                test_print.addDec1("thirdVariable", new Sym("int"));
                test_print.addScope();
                test_print.addDec1("firstVarible", new Sym("double"));
            } catch (Exception e) {
                System.out.println("Wrong exception thrown " + e.toString());    
            }
            System.out.println("List with several HashMaps");
            test_print.print();
            for (int i = 0; i <= 2 ; i++) {
                test_print.removeScope();
            }
            test_print.print();
        } catch (Exception e) {
            System.out.println("Wrong exception thrown " + e.toString());
        }
  
    }
}