package com.smartphone.lizandro.gymlr;

import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.util.Log;
import android.view.View;
import android.widget.*;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import com.smartphone.lizandro.gymlr.adapters.MiSimpleAdapter;
import com.smartphone.lizandro.gymlr.mvp.model.Partido;
import com.smartphone.lizandro.gymlr.mvp.presenter.DashboardPresenter;
import com.smartphone.lizandro.gymlr.mvp.view.IDashboardView;
import com.smartphone.lizandro.gymlr.mvp.view.InjectedActivity;
import com.squareup.picasso.Picasso;




public class GymActivity extends InjectedActivity implements IDashboardView {

    private List<HashMap<String, String>> datos = new ArrayList<HashMap<String, String>>();
    private SimpleAdapter adapter;
    private ProgressDialog pd;
    private int refreshHeader = 0;
    private int id= 1;//Iniciamos con las ligas next.
    private int names= 2;//Iniciamos con las ligas Esp.
    private int id_default= 87;//Iniciamos con las ligas Esp.

    @Inject
    DashboardPresenter presenter ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gym);
        final Button miboton = (Button) findViewById(R.id.button);

                miboton.setOnClickListener( new Button.OnClickListener(){
                    public void onClick(View arg0) {
                        String name[] = new String[] {"Spain >>","Spain >>","Spain >>", "Italy >>","Italy >>","Italy >>","Portugal >>","France >>","China >>", "Germany >>", "Internatinal >>"};
                        int ids[] = new int[] {87,139, 140,147, 141, 86, 61, 110, 120, 54, 76};

                        if(id>=ids.length) {
                            id = 0;
                        }

                        if(names>=name.length) {
                            names = 0;
                        }

                        //Cambiamos el texto del boton

                        load_Partidos(ids[id]);
                        miboton.setText("Get next league " +name[names] );
                        ++id;
                        ++names;

                    }

                });
        presenter.setView(this);
        adapter = new MiSimpleAdapter(
                this,
                datos,
                R.layout.row,
                new String[] {"local", "imagen_local", "visitante", "imagen_visitante", "goles_visitante", "goles_local"},
                new int[] {R.id.local, R.id.imagen_local, R.id.visitante, R.id.imagen_visitante, R.id.goles_visitante, R.id.goles_local});

        ((ListView)findViewById(R.id.mi_lista_partido)).setAdapter(adapter);
        load_Partidos(id_default);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_gym, menu);
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


    @Override
    public void load_Partidos(int id) {
        refreshHeader = 0;
        datos.clear();
        pd =  ProgressDialog.show(this, "Hackeando servidores de la NASA...","Conectando a Curiosity .", true);
        presenter.loadPartidos(id);
    }


    @Override
    public void refreshUI(com.smartphone.lizandro.gymlr.mvp.model.Partido partido) {
        if (partido != null) {
            HashMap<String, String> hm = partido.toHashMap(); //new HashMap<String, String>();
            datos.add(hm);

            refreshlHeader(partido);
        } else {
            refreshHeader = 0;
            Toast.makeText(this, "Error conexion a los datos, somos rastreados por la NASA", Toast.LENGTH_SHORT).show();
        }
        adapter.notifyDataSetChanged();
        pd.dismiss();

    }

    private void refreshlHeader(Partido partido) {
        if (refreshHeader == 0) {
            Picasso.with(this)
                    .load("http://medias.marcadores.com/logos/icons/league-64/" + partido.getLigaCode() + ".png")
                    .into((ImageView) findViewById(R.id.ligaCode));
            ((TextView) findViewById(R.id.ligaCode_name)).setText(partido.getLigaCode_name());


            StringBuffer country = new StringBuffer(partido.getCountry());
            if (country.indexOf("-") != -1) {
                // TODO: poner 1a letra despues de _
                // en mayusculas

            }
            country.setCharAt(0, country.substring(0, 1).toUpperCase().charAt(0));
            Log.e("...", country.toString());

            ((TextView) findViewById(R.id.country_name)).setText(country.toString().replaceAll("-", " "));
            Picasso.with(this)
                    .load("http://country-flags.net/medium/" + country + ".png")
                            //.load("http://img.freeflagicons.com/thumb/football_icon/"+partido.getCountry()+"/"+partido.getCountry()+"_640.png")
                    .into(((ImageView) findViewById(R.id.country)));
            refreshHeader = 1;
        }
    }

    public void loadData(View view) {
        this.load_Partidos(id_default);
    }
}
