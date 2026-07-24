public class ListaDobleEnlazada {
    private Nodo cabeza;
    private Nodo cola;
    private Nodo cursor;

    public ListaDobleEnlazada() {
        this.cabeza = null;
        this.cola = null;
        this.cursor = null;
    }

    public boolean estaVacia() {
        return cabeza == null;
    }

    public void agregarAlFinal(Curso curso) {
        Nodo nuevo = new Nodo(curso);
        if (estaVacia()) {
            cabeza = nuevo;
            cola = nuevo;
            cursor = nuevo;
        } else {
            cola.siguiente = nuevo;
            nuevo.anterior = cola;
            cola = nuevo;
        }
    }

    // ===== MÉTODO QUE FALTABA =====
    public boolean eliminar(String clave) {
        if (estaVacia()) return false;

        Nodo actual = cabeza;

        while (actual != null) {
            if (actual.curso.getClave().equalsIgnoreCase(clave)) {
                
                // Si el nodo a eliminar es el cursor del carrusel, lo reubicamos
                if (cursor == actual) {
                    if (actual.siguiente != null) {
                        cursor = actual.siguiente;
                    } else {
                        cursor = actual.anterior;
                    }
                }

                // Caso 1: Es el primer nodo (cabeza)
                if (actual == cabeza) {
                    cabeza = cabeza.siguiente;
                    if (cabeza != null) {
                        cabeza.anterior = null;
                    } else {
                        cola = null; // La lista quedó vacía
                    }
                } 
                // Caso 2: Es el último nodo (cola)
                else if (actual == cola) {
                    cola = cola.anterior;
                    if (cola != null) {
                        cola.siguiente = null;
                    }
                } 
                // Caso 3: Está en medio
                else {
                    actual.anterior.siguiente = actual.siguiente;
                    actual.siguiente.anterior = actual.anterior;
                }

                return true;
            }
            actual = actual.siguiente;
        }

        return false;
    }

    public void mostrarInicioFin() {
        if (estaVacia()) {
            System.out.println("La lista doble está vacía.");
            return;
        }
        Nodo actual = cabeza;
        System.out.println("----- CURSOS (INICIO A FIN) -----");
        while (actual != null) {
            System.out.println(actual.curso);
            actual = actual.siguiente;
        }
    }

    public void mostrarFinInicio() {
        if (estaVacia()) {
            System.out.println("La lista doble está vacía.");
            return;
        }
        Nodo actual = cola;
        System.out.println("----- CURSOS (FIN A INICIO) -----");
        while (actual != null) {
            System.out.println(actual.curso);
            actual = actual.anterior;
        }
    }

    public void iniciarNavegador() {
        cursor = cabeza;
    }

    public void verCursoActual() {
        if (cursor != null) {
            System.out.println(" [CARRUSEL] " + cursor.curso);
        } else {
            System.out.println("No hay cursos en el navegador.");
        }
    }

    public void avanzarCarrusel() {
        if (cursor != null && cursor.siguiente != null) {
            cursor = cursor.siguiente;
            System.out.println("Avanzado al siguiente curso.");
        } else {
            System.out.println("Estás en el último curso.");
        }
    }

    public void regresarCarrusel() {
        if (cursor != null && cursor.anterior != null) {
            cursor = cursor.anterior;
            System.out.println("Regresado al curso anterior.");
        } else {
            System.out.println("Estás en el primer curso.");
        }
    }
}