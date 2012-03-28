//REQUIRED CLASS
package project4;

import java.util.ArrayList;

import project1.*;
import project2.*;

public class Schema {
	private ArrayList<Token> attributes;
	
	public Schema(Scheme scheme) {
		attributes = scheme.attributes;
	}
	
	public void renameAttributes(ArgumentList arguments) {
		// go through arguments, and if it's an identifier, change the corresponding one in attributes
	}
}
