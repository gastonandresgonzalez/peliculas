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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

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
