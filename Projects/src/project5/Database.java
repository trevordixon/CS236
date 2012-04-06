//REQUIRED CLASS
package project5;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

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

		boolean changed;
		int passes = 0;
		do {
			++passes;
			changed = false;
			for (Rule rule : d.ruleList) {
				Relation r = relations.get(rule.simplePredicate.name);
				int size = r.size();
				r.processRule(this, rule);
				if (r.size() > size) changed = true;
			}
		} while (changed);
		
		System.out.println("Schemes populated after " + passes + " passes through the Rules.");
		
		for (Query query : d.queryList) {
			queryResults.add(new QueryResult(this, query));
		}
	}

	public String toString() {
		String out = "";
		for (QueryResult qr : queryResults) {
			out += qr.toString();
		}
		out += "Done!\n";
		return out;
	}
}
