package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
<<<<<<< HEAD
import android.widget.Toast;
=======
>>>>>>> master

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import model.DetallePeliculas;
import model.ListadoPeliculas;
import model.User;

/**
 * Created by NgocTri on 11/7/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "mydatabase.sqlite";

    public static final String DBLOCATION = "/data/data/ort.edu.org.pelisapp/databases/";
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public DatabaseHelper(Context context) {
        super(context, DBNAME, null, 1);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void openDatabase() {
        String dbPath = DBLOCATION + DBNAME;
        if(mDatabase != null && mDatabase.isOpen()) {
            return ;
        }
        mDatabase = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE
                 );

    }

    public void closeDatabase() {
        if(mDatabase!=null) {
            mDatabase.close();
        }
    }


    public List<DetallePeliculas> getDetallePeliculas() {

        DetallePeliculas DetPelicula = null;
        List<DetallePeliculas> listaPeliculas = new ArrayList<>();
        openDatabase();

        Cursor cursor = mDatabase.rawQuery("select peliculas.idpelicula,peliculas.titulo,peliculas.anio,peliculas.url,peliculas.descripcion,peliculas.duracion,peliculas.rating,directores.nombre_director,generos.genero from peliculas inner join generos on(peliculas.idgenero=generos.idgenero) inner join directores on(peliculas.iddirector=directores.iddirector)", null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

            DetPelicula = new DetallePeliculas(cursor.getInt(0),cursor.getString (1),cursor.getInt(2),cursor.getString(3),cursor.getString(4),cursor.getInt(5),cursor.getInt(6),cursor.getString(7),cursor.getString(8));
            listaPeliculas.add(DetPelicula);
            cursor.moveToNext();

        }
        cursor.close();
        closeDatabase();
        return listaPeliculas;
    }

    public List<ListadoPeliculas> getListadoPeliculas() {

        ListadoPeliculas DetPelicula = null;
        List<ListadoPeliculas> listaPeliculas = new ArrayList<>();
        openDatabase();

        Cursor cursor = mDatabase.rawQuery("select peliculas.idpelicula,peliculas.titulo,peliculas.anio from peliculas inner join generos on(peliculas.idgenero=generos.idgenero) inner join directores on(peliculas.iddirector=directores.iddirector)", null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

            DetPelicula = new ListadoPeliculas(cursor.getInt(0),cursor.getString (1),cursor.getInt(2));
            listaPeliculas.add(DetPelicula);
            cursor.moveToNext();

        }
        cursor.close();
        closeDatabase();
        return listaPeliculas;
    }

    public boolean checkUser(User usuario) throws IndexOutOfBoundsException,NullPointerException{

        int tmpIdUser=0;

        openDatabase();

        Cursor cursor = mDatabase.rawQuery("select idusuario from usuarios where email='"+usuario.getEmail()+"' and password ='"+usuario.getPassword()+"';", null);

        if (null != cursor) {
            cursor.moveToFirst();


                tmpIdUser = cursor.getInt(0);

        }

        cursor.close();
        closeDatabase();

            if( tmpIdUser > 0){
                return true;
            }

        return false;
    }

}
