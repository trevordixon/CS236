//REQUIRED CLASS
package project4;

import java.util.ArrayList;
import java.util.List;
import project1.*;
import project2.*;

public class Tuple implements Comparable<Tuple> {
	List<Token> parameters = new ArrayList<Token>(); 
	
	public Tuple(Fact fact) {
		parameters = fact.facts;
	}
	
	@Override
	public int compareTo(Tuple t) {
		// TODO Auto-generated method stub
		return 0;
	}
}
