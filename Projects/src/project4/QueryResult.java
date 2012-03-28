package project4;

import java.util.ArrayList;

import project1.*;
import project2.*;

public class QueryResult {
	Database db;
	ArrayList<Relation> results = new ArrayList<Relation>();
	
	public QueryResult(Database db, Query query) {
		this.db = db;
		Relation r = db.relations.get(query.name);
		r = r.rename(query.arguments);
		
		for (Argument arg : query.arguments) {
			if (arg.getType() == TokenType.ID) {
				r = r.select();
			} else if (arg.getType() == TokenType.STRING) {
				
			} else {
				System.out.println("Uh oh! Big problem.");
			}
		}
	}
	
	public String toString() {
		return "q\n";
	}
}
