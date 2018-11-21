package ort.edu.org.pelisapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import java.util.List;
import adapter.ListadoPeliculasAdapter;
import database.DatabaseHelper;
import model.DetallePeliculas;

public class ListadoPeliculasActivity extends Activity {
    private ListView listPelis;
    private ListadoPeliculasAdapter adapter;
    private List<DetallePeliculas> mPeliculaDetalle;
    private DatabaseHelper mDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle );
        listPelis = findViewById(R.id.listViewPeliculasDetalle );

        mDBHelper = new DatabaseHelper(this);
        mDBHelper.getReadableDatabase();

        final Integer datoPeli = getIntent().getIntExtra("id",-1);

        mPeliculaDetalle=mDBHelper.getListadoPeliculas(datoPeli);
        adapter = new ListadoPeliculasAdapter(this , mPeliculaDetalle );
        listPelis.setAdapter(adapter);

    }

}
