public class Nodo {
    Curso curso;
    Nodo siguiente;
    Nodo anterior;

    public Nodo(Curso curso) {
        this.curso = curso;
        this.siguiente = null;
        this.anterior = null;
    }
}