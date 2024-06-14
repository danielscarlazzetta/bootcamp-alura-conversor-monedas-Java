import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static final ConsultaMoneda consulta = new ConsultaMoneda();
    public static final MonedaDTO result = consulta.buscaMoneda("USD");

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);
        int opcion = 0;

        while (opcion != 7) {
            try {
                Menu.mostrarMenu();
                opcion = teclado.nextInt();
                Menu.procesarOpcion(opcion);
            } catch (InputMismatchException e) {
                System.out.println("*****************************************************");
                System.out.println("Error: Ingrese un número válido.                    *");
                System.out.println("*****************************************************");
                teclado.nextLine();
            }
        }

        System.out.println("Saliendo...");
        teclado.close();
    }
}
