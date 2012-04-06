//REQUIRED CLASS
package project5;

import java.util.ArrayList;
import java.util.ListIterator;

import project1.*;
import project2.*;

public class Schema implements Iterable<Token> {
	public ArrayList<Token> attributes;
	
	public Schema(Scheme scheme) {
		attributes = scheme.attributes;
	}
	
	public void renameAttributes(ArgumentList arguments) {
		ListIterator<Token> attributesIter = attributes.listIterator();
		for (Argument arg : arguments) {
			attributesIter.next();
			if (arg.getType() == TokenType.ID) {
				attributesIter.set(arg.getToken().clone());
			}
		}
	}
	
	public String toString() {
		String out = "";
		for (Token attr : attributes) { out += attr.getValue() + ", "; }
		return out;
	}

	@Override
	public ListIterator<Token> iterator() {
		return attributes.listIterator();
	}
	
	public int size() {
		return attributes.size();
	}
}
