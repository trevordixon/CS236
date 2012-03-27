//REQUIRED CLASS
package project4;

import java.util.TreeSet;

import project1.*;
import project2.*;

public class Relation {
	Schema schema;
	TreeSet<Tuple> facts;
	
	public Relation(Scheme scheme) {
		schema = new Schema(scheme);
	}
	
	public void addFact(Fact fact) {
		facts.add(new Tuple(fact));
	}
}
