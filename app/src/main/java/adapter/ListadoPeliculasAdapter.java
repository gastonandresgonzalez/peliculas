package adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import database.DatabaseHelper;
import model.DetallePeliculas;
import ort.edu.org.pelisapp.ListadoPeliculasActivity;
import ort.edu.org.pelisapp.MainActivity;
import ort.edu.org.pelisapp.R;

public class ListadoPeliculasAdapter extends BaseAdapter {

    private Context mContext;
    private List<DetallePeliculas> mPeliculaList;


    public ListadoPeliculasAdapter(Context mContext, List<DetallePeliculas> mPeliculaList){
        this.mContext = mContext;
        this.mPeliculaList = mPeliculaList;
    }

    @Override
    public int getCount() {
        return mPeliculaList.size();
    }

    @Override
    public Object getItem(int position) {
        return mPeliculaList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mPeliculaList.get(position).getId();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        Bitmap mPeliFoto = null;
        URL newurl = null;




        // genero la vista con el adapter
        View v= View.inflate(mContext, R.layout.item_detalle, null );

        TextView tv_pelicula_id = v.findViewById(R.id.tv_pelicula_id  );
        TextView tv_pelicula_titulo = v.findViewById(R.id.tv_pelicula_titulo  );
        TextView tv_pelicula_anio = v.findViewById(R.id.tv_pelicula_anio  );
        ImageView tv_pelicula_url = v.findViewById(R.id.tv_pelicula_url  );
        TextView tv_pelicula_descripcion = v.findViewById(R.id.tv_pelicula_descripcion  );
        TextView tv_pelicula_duracion = v.findViewById(R.id.tv_pelicula_duracion  );
        final RatingBar tv_pelicula_rating = v.findViewById(R.id.ratingBarDetalle );
        TextView tv_pelicula_director = v.findViewById(R.id.tv_pelicula_director  );
        TextView tv_pelicula_genero = v.findViewById(R.id.tv_pelicula_genero  );
        Button buttonRate = v.findViewById(R.id.buttonRate);



        tv_pelicula_rating.setRating(Long.valueOf(mPeliculaList.get(position).getRating()));
        try {
            newurl = new URL(String.valueOf(mPeliculaList.get(position).getUrl ()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }




        try {

            assert newurl != null;

            if (android.os.Build.VERSION.SDK_INT > 9) {
                StrictMode.ThreadPolicy policy = new     StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }

            mPeliFoto =BitmapFactory.decodeStream(newurl.openConnection().getInputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }


        //aca le asigno un valor a los campos del activity
        tv_pelicula_id.setText(String.valueOf(mPeliculaList.get(position).getId ()));
        tv_pelicula_titulo.setText(String.valueOf(mPeliculaList.get(position).getTitulo()));
        tv_pelicula_anio.setText(String.valueOf(mPeliculaList.get(position).getAnio ()));
        tv_pelicula_url.setImageBitmap(mPeliFoto);
        tv_pelicula_descripcion.setText(String.valueOf(mPeliculaList.get(position).getDescripcion()));
        tv_pelicula_duracion.setText(String.valueOf(mPeliculaList.get(position).getDuracion()));
        tv_pelicula_director.setText(String.valueOf(mPeliculaList.get(position).getDirector()));
        tv_pelicula_genero.setText(String.valueOf(mPeliculaList.get(position).getGenero()));

        buttonRate.setOnClickListener ( new View.OnClickListener() {

            @Override
            public void onClick(View v){
                DatabaseHelper mDBHelper=new DatabaseHelper(mContext);
                mDBHelper.getWritableDatabase();

                int valor= (int) tv_pelicula_rating.getRating();

                mDBHelper.setRate( valor ,Integer.valueOf(mPeliculaList.get(position).getId()));
                Intent i = new Intent(mContext, MainActivity.class);
                mContext.startActivity(i);
            }
        });

         return v;

    }

}
