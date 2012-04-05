package project5;

import project2.*;

public class QueryResult {
	Database db;
	public Relation results;
	
	public QueryResult(Database db, Query query) {
		this.db = db;
		Relation r = db.relations.get(query.name).rename(query).select(query).project(query);
		r.query = query;
		results = r;
	}
	
	public QueryResult(Database db, SimplePredicate p) {
		this.db = db;
		Relation r = db.relations.get(p.name).rename(p).select(p).project(p);
		r.query = p;
		results = r;
	}
	
	public String toString() {
		return results.toString();
	}
}
