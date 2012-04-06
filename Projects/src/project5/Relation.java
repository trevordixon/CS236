//REQUIRED CLASS
package project5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.TreeSet;

import project1.*;
import project2.*;

public class Relation {
	Schema schema;
	TreeSet<Tuple> facts = new TreeSet<Tuple>();
	Scheme scheme;
	public SimplePredicate query;
	
	public Relation(Scheme scheme) {
		this.scheme = scheme;
		schema = new Schema(scheme);
	}
	
	public Relation(Schema schema) {
		this.schema = schema;
	}
	
	public void addFact(Fact fact) {
		facts.add(new Tuple(fact));
	}
	
	public void addFact(Tuple fact) {
		facts.add(fact);
	}
	
	public void processRule(Database db, Rule rule) {
		Relation result = null;
		ArrayList<Relation> _results = new ArrayList<Relation>();

		for (SimplePredicate p : rule.predicateList) {
			_results.add(new QueryResult(db, p).results);
		}
		
		for (Relation r : _results) {
			if (result == null) result = r;
			else result = result.join(r);
		}
		
		result = result.projectAfterJoin(rule.simplePredicate);
		
		for (Tuple t : result.facts) {
			this.addFact(t);
		}
	}
	
	public Relation join(Relation r) {
		Relation result = this.clone().dropTuples();
		
		HashMap<Integer, Integer> matchingCols = new HashMap<Integer, Integer>(); 
		int i = 0;
		for (Token col : r.schema) {
			if (result.schema.contains(col))
				matchingCols.put(i, result.schema.indexOf(col));
			else {
				result.schema.attributes.add(col.clone());
				result.query.arguments.arguments.add(new Argument(col.clone()));
			}
			++i;
		}

		for (Tuple t1 : this.facts) {
			nextfact:
			for (Tuple t2 : r.facts) {
				for (int col2 : matchingCols.keySet()) {
					int col1 = matchingCols.get(col2);
					if (!t1.parameters.get(col1).equals(t2.parameters.get(col2)))
						continue nextfact;
				}
				
				Tuple newTuple = t1.clone();
				i = 0;
				for (Token val : t2.parameters) {
					if (!matchingCols.containsKey(i))
						newTuple.parameters.add(val);
					++i;
				}
				
				result.addFact(newTuple);
			}
		}
		
		return result;
	}
	
	public Relation project(SimplePredicate query) {
		Relation r = this.clone();
		Iterator<Argument> qIter = query.arguments.iterator();
		ListIterator<Token> sIter = r.schema.iterator();
		TreeSet<Argument> vars = new TreeSet<Argument>();
		while (qIter.hasNext()) {
			Argument a = qIter.next();
			Token s = sIter.next();
			if (a.getType() != TokenType.ID)
				sIter.set(null);
			else if (vars.contains(a))
				sIter.set(null);
			else vars.add(a);
		}
		return r;
	}
	
	public Relation projectAfterJoin(SimplePredicate query) {
		Relation r = this.clone();

		ArrayList<Token> cols = new ArrayList<Token>();
		ArrayList<Integer> i = new ArrayList<Integer>();
		for (Argument a : query.arguments.arguments) {
			cols.add(a.getToken());
			i.add(r.schema.indexOf(a.getToken()));
		}
		
		for (Tuple t : r.facts) {
			t.reorderCols(i);
		}

		r.schema = new Schema(cols);
		r.query = query;

		return r;
	}
	
	public Relation select(SimplePredicate query) {
		Relation r = this.clone();
		Iterator<Tuple> tIter = r.facts.iterator();
		while (tIter.hasNext()) {
			Tuple t = tIter.next();
			if (!t.matches(query))
				tIter.remove();
		}
		return r;
	}
	
	public Relation rename(SimplePredicate query) {
		Relation r = this.clone();
		r.schema.renameAttributes(query.arguments);
		return r;
	}
	
	public Relation clone() {
		Relation r = new Relation(schema.clone());
		for (Tuple fact : facts) {
			r.addFact(fact.clone());
		}
		r.query = this.query;
		return r;
	}
	
	public Relation dropTuples() {
		facts = new TreeSet<Tuple>();
		return this;
	}
	
	public String toString() {
		String out = query + "? ";
		out += (facts.size() > 0) ? ("Yes(" + facts.size() + ")\n") : "No\n";
		
		for (Tuple t : facts) {
			String tuples = "  ";
			Iterator<Token> tIter = t.iterator();
			for (Token s : schema) {
				if (s == null) { tIter.next(); continue; }
				if (!tuples.equals("  ")) tuples += ", ";
				Token p = tIter.next();
				tuples += s.getValue() + "=" + p.toString();
			}
			if (tuples.equals("  ")) continue;
			out += tuples + "\n";
		}
		
		return out;
	}
}
