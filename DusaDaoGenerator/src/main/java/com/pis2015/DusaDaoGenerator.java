package com.pis2015;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.ToMany;

public class DusaDaoGenerator {

    public static void main(String args[]) throws Exception {
        Schema schema = new Schema(2,"com.pis2015.greendaodusaexamplegen");
        addRepartoWithChofer(schema);
        new DaoGenerator().generateAll(schema,"../app/src/main/java/");
    }

    private static void addRepartoWithChofer(Schema schema) {
        Entity reparto = schema.addEntity("Reparto");
        reparto.addIdProperty();
        reparto.addStringProperty("codigo").notNull();
        reparto.addDateProperty("date");

        Entity chofer = schema.addEntity("Chofer");
        chofer.addIdProperty();
        chofer.addStringProperty("nombre").notNull();

        // Relaciones
        // Un Reparto tiene un Chofer : Relacion toOne 1:1

        // Creo una foreign key para luego agregar a la tabla reparto
        Property idChofer = reparto.addLongProperty("idChofer").notNull().getProperty();

        // agrego a reparto la relacion toOne que saca el id de chofer y lo agrega como foreign key
        // en la tabla reparto como choferId
        reparto.addToOne(chofer, idChofer);

        // La linea anterior permite trabajar directamente en un Reparto con su respectivo Chofer
        // con un getChofer/setChofer creados por greenDao

        // Un Chofer tiene muchos Repartos: Relacion toMany 1:N
        // To-many relations are modeled like to-one relations,
        // except that the foreign key is placed in the destination table.
        ToMany repartosDeUnChofer = chofer.addToMany(reparto, idChofer);
        repartosDeUnChofer.setName("repartos");

        // Entonces ahora podemos llamar a chofer.getRepartos()
        // que nos retornara la lista de repartos del chofer
    }
}
