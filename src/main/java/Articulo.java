import java.lang.Double;

class Articulo {
    private String nombre;
    private Double precio;
    public Articulo(){
    	nombre = "";
        precio = 0.0;
    }
    public void setNombre(String nombre){
      this.nombre = nombre;
    }
    public String getNombre(){
      return nombre;
    }
    public void setPrecio(Double precio){
        this.precio=precio;
    }
    public Double getPrecio(){
        return precio;
    }
}
