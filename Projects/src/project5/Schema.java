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
	
	public Schema(ArrayList<Token> attributes) {
		this.attributes = attributes;
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
	
	public Schema clone() {
		ArrayList<Token> a = new ArrayList<Token>();
		for (Token t : attributes) {
			if (t == null) a.add(null);
			else a.add(t.clone());
		}
		return new Schema(a);
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
	
	public boolean contains(Token t) {
		for (Token col : this) {
			if (t.equals(col)) return true;
		}
		return false;
	}
	
	public int indexOf(Token t) {
		int i = 0;
		for (Token col : this) {
			if (t.equals(col)) return i;
			++i;
		}
		return -1;
	}
}
