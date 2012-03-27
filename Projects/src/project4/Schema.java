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
}
