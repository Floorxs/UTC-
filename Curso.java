public class Curso {
    private String clave;
    private String nombre;
    private String docente;
    private int cupoMaximo;
    private int numeroInscritos;

    public Curso(String clave, String nombre, String docente, int cupoMaximo) {
        this.clave = clave;
        this.nombre = nombre;
        this.docente = docente;
        this.cupoMaximo = cupoMaximo;
        this.numeroInscritos = 0;
    }

    public String getClave() { return clave; }
    public void setClave(String clave) { this.clave = clave; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDocente() { return docente; }
    public void setDocente(String docente) { this.docente = docente; }

    public int getCupoMaximo() { return cupoMaximo; }
    public void setCupoMaximo(int cupoMaximo) { this.cupoMaximo = cupoMaximo; }

    public int getNumeroInscritos() { return numeroInscritos; }
    public void setNumeroInscritos(int numeroInscritos) { this.numeroInscritos = numeroInscritos; }

    public boolean inscribirEstudiante() {
        if (numeroInscritos < cupoMaximo) {
            numeroInscritos++;
            return true;
        }
        return false;
    }

    public boolean darDeBajaEstudiante() {
        if (numeroInscritos > 0) {
            numeroInscritos--;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Clave: " + clave + " | Nombre: " + nombre + " | Docente: " + docente + 
               " | Cupo: " + numeroInscritos + "/" + cupoMaximo;
    }
}