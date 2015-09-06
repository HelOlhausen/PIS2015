package com.pis2015.greendaodusaexample;

import java.util.Date;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.pis2015.greendaodusaexamplegen.ChoferDao;
import com.pis2015.greendaodusaexamplegen.Chofer;
import com.pis2015.greendaodusaexamplegen.RepartoDao;
import com.pis2015.greendaodusaexamplegen.Reparto;
import com.pis2015.greendaodusaexamplegen.DaoMaster;
import com.pis2015.greendaodusaexamplegen.DaoMaster.DevOpenHelper;
import com.pis2015.greendaodusaexamplegen.DaoSession;


public class RepartoActivity extends AppCompatActivity {

    private SQLiteDatabase db;

    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private ChoferDao choferDao;
    private RepartoDao repartoDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reparto);

        DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "dusa-db", null);
        db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
        repartoDao = daoSession.getRepartoDao();
        choferDao = daoSession.getChoferDao();
    }

    public void onListarRepartosClick(View view) {
        darDeAltaChoferesConRepartos();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_reparto, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void darDeAltaChoferesConRepartos()
    {
        Chofer chofer = new Chofer();
        chofer.setNombre("Gonza");
        choferDao.insert(chofer);
        Log.i("DusaGreenDaoExample", "Inserted new chofer, ID: " + chofer.getId() + "Nombre:" + chofer.getNombre());

        Reparto reparto710 = new Reparto();
        reparto710.setCodigo("710");
        reparto710.setIdChofer(chofer.getId());
        reparto710.setDate(new Date());
        repartoDao.insert(reparto710);
        Log.i("DusaGreenDaoExample", "Inserted new reparto, ID: " + reparto710.getId());

        Reparto reparto720 = new Reparto();
        reparto720.setCodigo("720");
        reparto720.setIdChofer(chofer.getId());
        reparto720.setDate(new Date());
        repartoDao.insert(reparto720);
        Log.i("DusaGreenDaoExample", "Inserted new reparto, ID: " + reparto720.getId());

        Chofer chofer2 = new Chofer();
        chofer2.setNombre("Mato");
        choferDao.insert(chofer2);
        Log.i("DusaGreenDaoExample", "Inserted new chofer, ID: " + chofer2.getId() + "Nombre:" + chofer2.getNombre());

        Reparto reparto820 = new Reparto();
        reparto820.setCodigo("820");
        reparto820.setIdChofer(chofer2.getId());
        reparto820.setDate(new Date());
        repartoDao.insert(reparto820);
        Log.i("DusaGreenDaoExample", "Inserted new reparto, ID: " + reparto820.getId());

        List<Reparto> repartosDeChofer = chofer.getRepartos();
        Log.i("DusaGreenDaoExample", "Los repartos del chofer, ID: " + chofer.getNombre() + " son:");
        for(Reparto reparto: repartosDeChofer){
            Log.i("Reparto:", reparto.getCodigo());
        }

        List<Reparto> repartosDeChofer2 = chofer2.getRepartos();
        Log.i("DusaGreenDaoExample", "Los repartos del chofer, ID: " + chofer2.getNombre() + " son:");
        for(Reparto reparto: repartosDeChofer2){
            Log.i("Reparto:", reparto.getCodigo());
        }
    }
}
