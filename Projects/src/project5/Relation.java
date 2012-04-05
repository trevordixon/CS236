//REQUIRED CLASS
package project5;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.TreeSet;

import project1.*;
import project2.*;

public class Relation {
	Schema schema;
	TreeSet<Tuple> facts = new TreeSet<Tuple>();
	Scheme scheme;
	public Query query;
	
	public Relation(Scheme scheme) {
		this.scheme = scheme;
		schema = new Schema(scheme);
	}
	
	public void addFact(Fact fact) {
		facts.add(new Tuple(fact));
	}
	
	public void addFact(Tuple fact) {
		facts.add(fact);
	}
	
	public Relation project(Query query) {
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
	
	public Relation select(Query query) {
		Relation r = this.clone();
		Iterator<Tuple> tIter = r.facts.iterator();
		while (tIter.hasNext()) {
			Tuple t = tIter.next();
			if (!t.matches(query))
				tIter.remove();
		}
		return r;
	}
	
	public Relation rename(Query query) {
		Relation r = this.clone();
		r.schema.renameAttributes(query.arguments);
		return r;
	}
	
	public Relation clone() {
		Relation r = new Relation(scheme.clone());
		for (Tuple fact : facts) {
			r.addFact(fact.clone());
		}
		return r;
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
