//REQUIRED CLASS
package project4;

import java.util.Map;
import java.util.TreeMap;

import project1.*;
import project2.*;

public class Database {
	Map<String, Relation> relations = new TreeMap<String, Relation>();
	
	public Database(DatalogProgram d) {
		for (Scheme scheme : d.schemeList) {
			relations.put(scheme.name, new Relation(scheme));
		}
		
		for (Fact fact : d.factList) {
			relations.get(fact.name).addFact(fact);
		}
	}

	public String toString() {
		return "Implement me";
	}
}
