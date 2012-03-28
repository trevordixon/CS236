package project4;

import java.util.ArrayList;

import project2.*;

public class QueryResult {
	Database db;
	ArrayList<Relation> results = new ArrayList<Relation>();
	
	public QueryResult(Database db, Query query) {
		this.db = db;
		Relation r = db.relations.get(query.name);
		r = r.rename(query).select(query).project(query);
		r.query = query;
		results.add(r);
	}
	
	public String toString() {
		String out = "";
		for (Relation r : results) {
			out += r.toString();
		}
		return out;
	}
}
