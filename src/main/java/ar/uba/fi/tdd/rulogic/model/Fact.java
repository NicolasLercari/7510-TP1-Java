package ar.uba.fi.tdd.rulogic.model;

import org.mockito.cglib.core.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Created by Nicoo on 7/11/2017.
 */
public class Fact extends Operacion {

    public Fact(String nombreFact){
        nombreFact = nombreFact.replaceAll("\\.", "");
        nombreFact = nombreFact.replaceAll("\\s+", "");
        nombreFact = nombreFact.replaceAll("\\)", "");
        this.nombre = nombreFact.substring(0, nombreFact.indexOf("("));
        this.parametros = new ArrayList<String>();
        String[] params = nombreFact.substring(nombreFact.indexOf("(")+1,nombreFact.length()).split(",");
        this.parametros = new ArrayList<String>(Arrays.asList(params));
    }

    public Fact(String nombreFact, ArrayList<String> parametros){
        this.nombre = nombreFact;
        this.parametros = parametros;
    }
    public boolean comparar(Fact factQuery){
        if (this.nombre.equals(factQuery.nombre))// && this.parametros.size() == factQuery.parametros.size()){
        {Iterator<String> it1 = this.parametros.iterator();
            Iterator<String> it2 = factQuery.parametros.iterator();
            while (it1.hasNext() && it2.hasNext()) {
                if (!(it1.next().equals(it2.next()))){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public boolean comparar(Rule factQuery) {
        return factQuery.comparar(this);
    }

    public boolean comparar(Operacion factQuery) {
        return factQuery.comparar(this);
    }

    public ArrayList<Fact> dameLasCondicionesParaEsteQuery(Fact factQuery){
        ArrayList<Fact> condicion =  new ArrayList<Fact>();
        condicion.add(this);
        return condicion;
    }


    public static boolean esFact(String reprFact){
        return reprFact.matches("\\w+\\(\\w+(,\\w+)*\\)");
    }
}
