package adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import model.DetallePeliculas;
import ort.edu.org.pelisapp.R;

public class ListPeliculasAdapter extends BaseAdapter {

    private Context mContext;
    private List<DetallePeliculas> mPeliculaList;

    public ListPeliculasAdapter(Context mContext, List<DetallePeliculas> mPeliculaList){
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
    public View getView(int position, View convertView, ViewGroup parent) {

        View v= View.inflate(mContext, R.layout.item_peliculas , null );
        TextView tv_pelicula_id = v.findViewById(R.id.tv_pelicula_id  );
        TextView tv_pelicula_titulo = v.findViewById(R.id.tv_pelicula_titulo  );
        TextView tv_pelicula_anio = v.findViewById(R.id.tv_pelicula_anio  );
        TextView tv_pelicula_url = v.findViewById(R.id.tv_pelicula_url  );
        TextView tv_pelicula_descripcion = v.findViewById(R.id.tv_pelicula_descripcion  );
        TextView tv_pelicula_duracion = v.findViewById(R.id.tv_pelicula_duracion  );
        TextView tv_pelicula_rating = v.findViewById(R.id.tv_pelicula_rating  );
        TextView tv_pelicula_director = v.findViewById(R.id.tv_pelicula_director  );
        TextView tv_pelicula_genero = v.findViewById(R.id.tv_pelicula_genero  );

        tv_pelicula_id.setText(String.valueOf(mPeliculaList.get(position).getId ()));
        tv_pelicula_titulo.setText(String.valueOf(mPeliculaList.get(position).getTitulo()));
        tv_pelicula_anio.setText(String.valueOf(mPeliculaList.get(position).getAnio ()));
        tv_pelicula_url.setText(String.valueOf(mPeliculaList.get(position).getUrl()));
        tv_pelicula_descripcion.setText(String.valueOf(mPeliculaList.get(position).getDescripcion()));
        tv_pelicula_duracion.setText(String.valueOf(mPeliculaList.get(position).getDuracion()));
        tv_pelicula_rating.setText(String.valueOf(mPeliculaList.get(position).getRating()));
        tv_pelicula_director.setText(String.valueOf(mPeliculaList.get(position).getDirector()));
        tv_pelicula_genero.setText(String.valueOf(mPeliculaList.get(position).getGenero()));

    return v;

    }
}
