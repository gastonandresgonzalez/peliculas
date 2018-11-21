package ort.edu.org.pelisapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import adapter.ListPeliculasAdapter;
import database.DatabaseHelper;
import model.DetallePeliculas;

public class MainActivity extends Activity {
    private ListView listPelisTerror;
    private ListView listPelisSuspenso;
    private ListView listPelisDrama;

    private ListPeliculasAdapter adapterTerror;
    private ListPeliculasAdapter adapterSuspenso;
    private ListPeliculasAdapter adapterDrama;

    private List<DetallePeliculas> mPeliculaDetalleTerror;
    private List<DetallePeliculas> mPeliculaDetalleSuspenso;
    private List<DetallePeliculas> mPeliculaDetalleDrama;

    private DatabaseHelper mDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listPelisTerror = findViewById(R.id.listViewPeliculasTerror);
        listPelisSuspenso = findViewById(R.id.listViewPeliculasSuspenso);
        listPelisDrama = findViewById(R.id.listViewPeliculasDrama);

        mDBHelper = new DatabaseHelper(this);

        //Check exists database
        File database = getApplicationContext().getDatabasePath(DatabaseHelper.DBNAME);
        if (false == database.exists()) {
            mDBHelper.getReadableDatabase();
            //Copy db
            if (copyDatabase(this)) {
                Toast.makeText(this, "Copy database succes", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Copy data error", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        mPeliculaDetalleTerror = mDBHelper.getDetallePeliculasTerror();
        mPeliculaDetalleSuspenso = mDBHelper.getDetallePeliculasSuspenso();
        mPeliculaDetalleDrama = mDBHelper.getDetallePeliculasDrama();


        adapterSuspenso = new ListPeliculasAdapter(this, mPeliculaDetalleSuspenso);
        adapterTerror = new ListPeliculasAdapter(this, mPeliculaDetalleTerror);
        adapterDrama = new ListPeliculasAdapter(this, mPeliculaDetalleDrama);


        try {
            listPelisTerror.setAdapter(adapterTerror);
        } catch (NullPointerException error) {
            Toast.makeText(MainActivity.this, "No hay peliculas de Terror cargadas", Toast.LENGTH_SHORT).show();
        }

        try {
            listPelisDrama.setAdapter(adapterDrama);
        } catch (NullPointerException error) {
            Toast.makeText(MainActivity.this, "No hay peliculas de Suspenso cargadas", Toast.LENGTH_SHORT).show();
        }

        try {
            listPelisSuspenso.setAdapter(adapterSuspenso);
        } catch (NullPointerException error) {
            Toast.makeText(MainActivity.this, "No hay peliculas de Suspenso cargadas", Toast.LENGTH_SHORT).show();
        }


        listPelisTerror.setOnItemClickListener(new AdapterView.OnItemClickListener () {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                try {

                    Intent i = new Intent(getApplicationContext(), ListadoPeliculasActivity.class);

                    i.putExtra("id", (int) id);

                    startActivity(i);

                } catch (IndexOutOfBoundsException error) {
                    Toast.makeText(MainActivity.this, "Error al abrir item", Toast.LENGTH_SHORT).show();
                }
            }

        });

        listPelisDrama.setOnItemClickListener(new AdapterView.OnItemClickListener () {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                try {

                    Intent i = new Intent(getApplicationContext(), ListadoPeliculasActivity.class);

                    i.putExtra("id", (int) id);

                    startActivity(i);

                } catch (IndexOutOfBoundsException error) {
                    Toast.makeText(MainActivity.this, "Error al abrir item", Toast.LENGTH_SHORT).show();
                }
            }

        });

        listPelisSuspenso.setOnItemClickListener(new AdapterView.OnItemClickListener () {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                try {

                    Intent i = new Intent(getApplicationContext(), ListadoPeliculasActivity.class);

                    i.putExtra("id", (int) id);

                    startActivity(i);

                } catch (IndexOutOfBoundsException error) {
                    Toast.makeText(MainActivity.this, "Error al abrir item", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

    private boolean copyDatabase(Context context) {
        try {

            InputStream inputStream = context.getAssets().open(DatabaseHelper.DBNAME);
            String outFileName = DatabaseHelper.DBLOCATION + DatabaseHelper.DBNAME;
            OutputStream outputStream = new FileOutputStream(outFileName);
            byte[]buff = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(buff)) > 0) {
                outputStream.write(buff, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            Log.w("MainActivity","DB copied");
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

   public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

}
