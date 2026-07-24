public class ListaEnlazadaSimple {
    private Nodo cabeza;

    public ListaEnlazadaSimple() {
        this.cabeza = null;
    }

    public boolean estaVacia() {
        return cabeza == null;
    }

    public boolean existeClave(String clave) {
        Nodo actual = cabeza;
        while (actual != null) {
            if (actual.curso.getClave().equalsIgnoreCase(clave)) {
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }

    public void agregar(Curso curso) {
        if (existeClave(curso.getClave())) {
            System.out.println("Error: Ya existe un curso con esa clave.");
            return;
        }
        Nodo nuevo = new Nodo(curso);
        nuevo.siguiente = cabeza;
        cabeza = nuevo;
        System.out.println("Curso agregado exitosamente.");
    }

    public void mostrar() {
        if (estaVacia()) {
            System.out.println("La lista de cursos está vacía.");
            return;
        }
        Nodo actual = cabeza;
        System.out.println("----- LISTA DE CURSOS -----");
        while (actual != null) {
            System.out.println(actual.curso);
            actual = actual.siguiente;
        }
    }

    public Curso buscar(String clave) {
        Nodo actual = cabeza;
        while (actual != null) {
            if (actual.curso.getClave().equalsIgnoreCase(clave)) {
                return actual.curso;
            }
            actual = actual.siguiente;
        }
        return null;
    }

    public boolean eliminar(String clave) {
        if (estaVacia()) return false;

        if (cabeza.curso.getClave().equalsIgnoreCase(clave)) {
            cabeza = cabeza.siguiente;
            return true;
        }

        Nodo actual = cabeza;
        while (actual.siguiente != null) {
            if (actual.siguiente.curso.getClave().equalsIgnoreCase(clave)) {
                actual.siguiente = actual.siguiente.siguiente;
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }

    public int contarRecursivo(Nodo nodo) {
        if (nodo == null) return 0;
        return 1 + contarRecursivo(nodo.siguiente);
    }

    public int obtenerTotalCursos() {
        return contarRecursivo(cabeza);
    }

    public Curso buscarRecursivo(Nodo nodo, String clave) {
        if (nodo == null) return null;
        if (nodo.curso.getClave().equalsIgnoreCase(clave)) return nodo.curso;
        return buscarRecursivo(nodo.siguiente, clave);
    }

    public Curso buscarPorClaveRecursivo(String clave) {
        return buscarRecursivo(cabeza, clave);
    }
}