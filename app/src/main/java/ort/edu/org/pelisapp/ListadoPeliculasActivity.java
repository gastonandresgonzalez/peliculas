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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import adapter.ListadoPeliculasAdapter;
import database.DatabaseHelper;
import model.DetallePeliculas;
import model.User;

public class ListadoPeliculasActivity extends Activity {
    private ListView listPelis;

    private RatingBar mRatingBar;
    private Button buttonCalif;

    private ListadoPeliculasAdapter adapter;
    private List<DetallePeliculas> mPeliculaDetalle;
    private DatabaseHelper mDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main );
        listPelis = findViewById(R.id.listViewPeliculas );
        mRatingBar = findViewById(R.id.ratingBarDetalle );
        buttonCalif=findViewById(R.id.buttonRate);

        mDBHelper = new DatabaseHelper(this);
        mDBHelper.getReadableDatabase();

        final Integer datoPeli = getIntent().getIntExtra("id",-1);

        mPeliculaDetalle=mDBHelper.getListadoPeliculas(datoPeli);
        adapter = new ListadoPeliculasAdapter(this, mPeliculaDetalle );
        listPelis.setAdapter(adapter);

    /*    buttonCalif.setOnClickListener ( new View.OnClickListener() {

            @Override
            public void onClick(View v){
                try {

                    mDBHelper.setRate( mRatingBar.getNumStars() ,datoPeli);
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);

                    startActivity(i);

                } catch (IndexOutOfBoundsException error) {
                    Toast.makeText(ListadoPeliculasActivity.this, "Error al abrir item", Toast.LENGTH_SHORT).show();
                }
            }
        });*/

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

    public static class DownloadImageTask {
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
