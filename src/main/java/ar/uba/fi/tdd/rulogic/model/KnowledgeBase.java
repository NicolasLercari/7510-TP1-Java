package ar.uba.fi.tdd.rulogic.model;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class KnowledgeBase {

	private BaseDeDatos bdd;

	public boolean answer(String query) {
		/*var factsCondicion = this.dameLasCondicionesDeEsteQuery(query);
		return this.checkCondicionesEnBdd(factsCondicion);*/
		//query = query.replaceAll("\\s+", "");
		Fact factQuery = new Fact(query);
		ArrayList<Fact> condiciones = this.bdd.dameLasCondicionesDeEsteQuery(factQuery);
		return this.bdd.chequearCondiciones(condiciones);
	}

	public String parseDB(String fileName){
		String db="";
		try{
			db = new Scanner(new File(fileName)).useDelimiter("\\Z").next();
			this.bdd = new BaseDeDatos(db);
		}
		catch(java.io.FileNotFoundException e){
			this.bdd = new BaseDeDatos("");
		}
		return db;
	}


}
