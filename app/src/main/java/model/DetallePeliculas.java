package model;

public class DetallePeliculas {
    private int id;
    private String titulo;
    private int anio;
    private String url;
    private String descripcion;
    private int duracion;
    private int rating;
    private String director;
    private String genero;

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getTitulo() {return titulo;}
    public int getAnio() {return anio;}
    public String getUrl() {return url;}
    public String getDescripcion() {return descripcion;}
    public int getDuracion() {return duracion;}
    public int getRating() {return rating;}
    public String getDirector() {return director;}
    public String getGenero() {return genero;}

    public DetallePeliculas(int id, String titulo, int anio, String url, String descripcion, int duracion, int rating, String director, String genero) {
        this.id=id;
        this.titulo = titulo;
        this.anio = anio;
        this.url = url;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.rating = rating;
        this.director = director;
        this.genero = genero;
    }
}
