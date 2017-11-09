package ar.uba.fi.tdd.rulogic.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by Nicoo on 7/11/2017.
 */
public class Rule extends Operacion{

    private ArrayList<Fact> condiciones;

    public Rule(String nombreRule){
        nombreRule = nombreRule.replaceAll("\\.", "");
        this.nombre = nombreRule.substring(0, nombreRule.indexOf("("));
        this.parametros = new ArrayList<String>();
        String[] params = nombreRule.substring(nombreRule.indexOf("(")+1,nombreRule.indexOf(")")).split(",");
        this.parametros = new ArrayList<String>(Arrays.asList(params));

        String[] condiciones = nombreRule.split(":-")[1].split("\\),");
        this.condiciones = new ArrayList<Fact>();
        for (String nombreFact : condiciones){
            this.condiciones.add(new Fact(nombreFact));
        }
    }

    public static boolean esRule(String rule){
        return rule.matches("\\w+\\(\\w+(,\\w+)*\\):-(\\w+\\(\\w+(,\\w+)*\\),)*\\w+\\(\\w+(,\\w+)*\\)");
    }

    public boolean comparar(Operacion factQuery) {
        return factQuery.comparar(this);
    }

    public boolean comparar(Fact factQuery) {
        return ((this.nombre.equals(factQuery.nombre))&&(this.parametros.size() == factQuery.parametros.size()));
    }

    public boolean comparar(Rule factQuery) {
        return ((this.nombre.equals(factQuery.nombre))&&(this.parametros.size() == factQuery.parametros.size()));
    }

    public ArrayList<Fact> dameLasCondicionesParaEsteQuery(Fact factQuery) {
        ArrayList<Fact> condicion =  new ArrayList<Fact>();
        //condicion.add(this);
        HashMap<String, String> mapeoParametros = new HashMap<String, String>();
        int indice = 0;
        for (String unParametro : this.parametros ){
            mapeoParametros.put(unParametro, factQuery.parametros.get(indice));
            indice++;
        }
        for (Fact unFact : this.condiciones ){
                ArrayList<String> nuevosParametros = new ArrayList<String>();
                for (String unParametro : unFact.parametros){
                    nuevosParametros.add(mapeoParametros.get(unParametro));
                }
                condicion.add(new Fact(unFact.nombre, nuevosParametros));
        }
        return condicion;
    }
}
