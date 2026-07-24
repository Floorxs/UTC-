import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ListaEnlazadaSimple listaSimple = new ListaEnlazadaSimple();
        ListaDobleEnlazada listaDoble = new ListaDobleEnlazada();
        ArrayList<String> historial = new ArrayList<>();

        int opcion;
        do {
            System.out.println("\n===== SISTEMA DE GESTIÓN DE CURSOS UTC 2.0 =====");
            System.out.println("1. Agregar curso");
            System.out.println("2. Mostrar cursos");
            System.out.println("3. Buscar curso por clave");
            System.out.println("4. Eliminar curso");
            System.out.println("5. Inscribir estudiante a curso");
            System.out.println("6. Dar de baja estudiante de curso");
            System.out.println("7. Mostrar cursos de inicio a fin");
            System.out.println("8. Mostrar cursos de fin a inicio");
            System.out.println("9. Navegador de cursos (Carrusel)");
            System.out.println("10. Contar cursos usando recursividad");
            System.out.println("11. Buscar curso usando recursividad");
            System.out.println("12. Mostrar historial de acciones");
            System.out.println("13. Salir");
            System.out.print("Seleccione una opción: ");
            
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese clave: ");
                    String clave = scanner.nextLine();
                    if (listaSimple.existeClave(clave)) {
                        System.out.println("Error: Ya existe un curso con esa clave.");
                        break;
                    }
                    System.out.print("Ingrese nombre del curso: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Ingrese docente: ");
                    String docente = scanner.nextLine();
                    System.out.print("Ingrese cupo máximo: ");
                    int cupo = scanner.nextInt();

                    Curso nuevoCurso = new Curso(clave, nombre, docente, cupo);
                    listaSimple.agregar(nuevoCurso);
                    listaDoble.agregarAlFinal(nuevoCurso);
                    historial.add("Agregado curso con clave: " + clave);
                    break;

                case 2:
                    listaSimple.mostrar();
                    break;

                case 3:
                    System.out.print("Ingrese la clave a buscar: ");
                    String bClave = scanner.nextLine();
                    Curso encontrado = listaSimple.buscar(bClave);
                    if (encontrado != null) {
                        System.out.println("Curso encontrado: " + encontrado);
                    } else {
                        System.out.println("Curso no encontrado.");
                    }
                    break;

                case 4:
                    System.out.print("Ingrese la clave del curso a eliminar: ");
                    String eClave = scanner.nextLine();
                    
                    // Se elimina de la lista simple Y de la lista doble
                    boolean elSimple = listaSimple.eliminar(eClave);
                    boolean elDoble = listaDoble.eliminar(eClave);

                    if (elSimple || elDoble) {
                        System.out.println("Curso eliminado correctamente.");
                        historial.add("Eliminado curso con clave: " + eClave);
                    } else {
                        System.out.println("Error: El curso no existe.");
                    }
                    break;

                case 5:
                    System.out.print("Ingrese la clave del curso para inscribir: ");
                    String insClave = scanner.nextLine();
                    Curso cInscribir = listaSimple.buscar(insClave);
                    if (cInscribir != null) {
                        if (cInscribir.inscribirEstudiante()) {
                            System.out.println("Estudiante inscrito con éxito.");
                            historial.add("Inscripción en curso: " + insClave);
                        } else {
                            System.out.println("Error: El curso ya está lleno.");
                        }
                    } else {
                        System.out.println("El curso no existe.");
                    }
                    break;

                case 6:
                    System.out.print("Ingrese la clave del curso para dar de baja: ");
                    String bajaClave = scanner.nextLine();
                    Curso cBaja = listaSimple.buscar(bajaClave);
                    if (cBaja != null) {
                        if (cBaja.darDeBajaEstudiante()) {
                            System.out.println("Estudiante dado de baja con éxito.");
                            historial.add("Baja de estudiante en curso: " + bajaClave);
                        } else {
                            System.out.println("Error: El curso tiene cero inscritos.");
                        }
                    } else {
                        System.out.println("El curso no existe.");
                    }
                    break;

                case 7:
                    listaDoble.mostrarInicioFin();
                    break;

                case 8:
                    listaDoble.mostrarFinInicio();
                    break;

                case 9:
                    listaDoble.iniciarNavegador();
                    int opCarrusel;
                    do {
                        System.out.println("\n--- NAVEGADOR DE CURSOS (CARRUSEL) ---");
                        listaDoble.verCursoActual();
                        System.out.println("1. Avanzar al siguiente curso");
                        System.out.println("2. Regresar al curso anterior");
                        System.out.println("3. Salir del navegador");
                        System.out.print("Elija una opción: ");
                        opCarrusel = scanner.nextInt();
                        if (opCarrusel == 1) listaDoble.avanzarCarrusel();
                        else if (opCarrusel == 2) listaDoble.regresarCarrusel();
                    } while (opCarrusel != 3);
                    break;

                case 10:
                    int total = listaSimple.obtenerTotalCursos();
                    System.out.println("Total de cursos registrados (recursivo): " + total);
                    break;

                case 11:
                    System.out.print("Ingrese clave a buscar de forma recursiva: ");
                    String rClave = scanner.nextLine();
                    Curso resRecurso = listaSimple.buscarPorClaveRecursivo(rClave);
                    if (resRecurso != null) {
                        System.out.println("Encontrado recursivamente: " + resRecurso);
                    } else {
                        System.out.println("Curso no encontrado.");
                    }
                    break;

                case 12:
                    System.out.println("----- HISTORIAL DE ACCIONES -----");
                    if (historial.isEmpty()) {
                        System.out.println("No hay acciones registradas.");
                    } else {
                        for (String h : historial) {
                            System.out.println("- " + h);
                        }
                    }
                    break;

                case 13:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 13);
        scanner.close();
    }
}