package ar.uba.fi.tdd.rulogic.model;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nicoo on 7/11/2017.
 */
public class BaseDeDatos {

    private ArrayList<Operacion> bdd;

    public BaseDeDatos(String bddStr){

        this.bdd = new ArrayList<Operacion>();
        String basedd = bddStr.replaceAll("\\s","");
        String[] listBdd = basedd.split("\\.");
        for (String query : listBdd){
            query = query.replaceAll("\\s+","");
            if(Fact.esFact(query)){
                bdd.add(new Fact(query));
            }
            else if(Rule.esRule(query)){
                bdd.add(new Rule(query));
            } else {
                this.bdd = new ArrayList<Operacion>();
            }
        }
    }

    public ArrayList<Fact> dameLasCondicionesDeEsteQuery(Fact factQuery) {
        ArrayList<Fact> facts = new ArrayList<Fact>();
        for (Operacion unaOperacion : this.bdd) {
            if (unaOperacion.comparar(factQuery)) {
                facts = unaOperacion.dameLasCondicionesParaEsteQuery(factQuery);
            }
        }
        return facts;
    }

    public boolean chequearCondiciones(ArrayList<Fact> condiciones) {
        if (condiciones.size() == 0) return false;
        for (Operacion unaOperacion : condiciones){
            if (! this.estaEnBdd(unaOperacion)){
                return false;
            }
        }
        return true;
    }

    private boolean estaEnBdd(Operacion unaOperacion) {
        for (Operacion operacionEnBdd : this.bdd){
            if(operacionEnBdd.comparar(unaOperacion)){
                return true;
            }
        }
        return false;
    }
}
