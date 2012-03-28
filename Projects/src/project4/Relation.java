//REQUIRED CLASS
package project4;

import java.util.TreeSet;

import project1.*;
import project2.*;

public class Relation {
	Schema schema;
	TreeSet<Tuple> facts = new TreeSet<Tuple>();
	Scheme scheme;
	
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
	
	public Relation project() {
		return this.clone();
	}
	
	public Relation select() {
		return this.clone();
	}
	
	public Relation rename(ArgumentList arguments) {
		Relation r = this.clone();
		
		schema.renameAttributes(arguments);
		
		return r;
	}
	
	public Relation clone() {
		Relation r = new Relation(scheme.clone());
		for (Tuple fact : facts) {
			r.addFact(fact.clone());
		}
		return r;
	}
}
