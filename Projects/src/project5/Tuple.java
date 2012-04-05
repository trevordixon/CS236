//REQUIRED CLASS
package project5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import project1.*;
import project2.*;

public class Tuple implements Comparable<Tuple>, Iterable<Token> {
	List<Token> parameters = new ArrayList<Token>();
	Fact fact;
	
	public Tuple(Fact fact) {
		this.fact = fact;
		parameters = fact.facts;
	}
	
	public boolean matches(SimplePredicate query) {
		Iterator<Token> tIter = parameters.iterator();
		Iterator<Argument> qIter = query.arguments.iterator();
		HashMap<String, String> vars = new HashMap<String, String>();
		while(qIter.hasNext()) {
			Argument q = qIter.next();
			Token t = tIter.next();
			if (q.getType() == TokenType.STRING && !q.getValue().equals(t.getValue()))
				return false;
			if (q.getType() == TokenType.ID) {
				String pVal = vars.get(q.getValue());
				if (pVal == null) vars.put(q.getValue(), t.getValue());
				else if (!pVal.equals(t.getValue())) return false;
			}
		}
		return true;
	}
	
	@Override
	public int compareTo(Tuple t) {
		Iterator<Token> thisIter = this.iterator();
		Iterator<Token> tIter = t.iterator();
		while (thisIter.hasNext()) {
			Token thisToken = thisIter.next();
			Token otherToken = tIter.next();
			if (thisToken.getValue().compareTo(otherToken.getValue()) != 0)
				return thisToken.getValue().compareTo(otherToken.getValue());
		}
		return 0;
	}
	
	public Tuple clone() {
		return new Tuple(fact.clone());
	}
	
	public String toString() {
		String out = "(";
		for (Token p : parameters) {
			out += p + ", " ;
		}
		out += ")";
		return out;
	}

	@Override
	public Iterator<Token> iterator() {
		return parameters.listIterator();
	}
}
