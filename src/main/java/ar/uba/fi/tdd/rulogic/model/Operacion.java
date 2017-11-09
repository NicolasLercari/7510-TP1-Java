package ar.uba.fi.tdd.rulogic.model;

import java.util.ArrayList;

/**
 * Created by Nicoo on 7/11/2017.
 */
public abstract class Operacion {

    protected String nombre;
    protected ArrayList<String> parametros;

    public abstract boolean comparar(Operacion factQuery);
    public abstract boolean comparar(Fact factQuery);
    public abstract boolean comparar(Rule factQuery);

    public abstract ArrayList<Fact> dameLasCondicionesParaEsteQuery(Fact factQuery);
}
