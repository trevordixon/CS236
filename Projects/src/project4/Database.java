//REQUIRED CLASS
package project4;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import project1.*;
import project2.*;

public class Database {
	Map<String, Relation> relations = new TreeMap<String, Relation>();
	ArrayList<QueryResult> queryResults = new ArrayList<QueryResult>();
	
	public Database(DatalogProgram d) {
		for (Scheme scheme : d.schemeList) {
			relations.put(scheme.name, new Relation(scheme));
		}
		
		for (Fact fact : d.factList) {
			relations.get(fact.name).addFact(fact);
		}
		
		for (Query query : d.queryList) {
			queryResults.add(new QueryResult(this, query));
		}
	}

	public String toString() {
		String out = "";
		for (QueryResult qr : queryResults) {
			out += qr.toString();
		}
		return out;
	}
}
