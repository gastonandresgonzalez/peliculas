package adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import model.DetallePeliculas;
import model.ListadoPeliculas;
import ort.edu.org.pelisapp.R;

public class ListadoPeliculasAdapter extends BaseAdapter {

    private Context mContext;
    private List<ListadoPeliculas> mPeliculaList;

    public ListadoPeliculasAdapter(Context mContext, List<ListadoPeliculas> mPeliculaList){
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

        tv_pelicula_id.setText(String.valueOf(mPeliculaList.get(position).getId ()));
        tv_pelicula_titulo.setText(String.valueOf(mPeliculaList.get(position).getTitulo()));
        tv_pelicula_anio.setText(String.valueOf(mPeliculaList.get(position).getAnio ()));

    return v;

    }
}
