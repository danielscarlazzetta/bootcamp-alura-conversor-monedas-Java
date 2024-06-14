import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    public static String ingreseCantidad ="Ingrese la cantidad de";

    public static void mostrarMenu() {
        System.out.println("///////////////////////////////////////////////////");
        System.out.println("1) Dolar ================> peso argentino         /");
        System.out.println("2) Peso argentino =======> Dolar                  /");
        System.out.println("3) Dolar ================> Real BRZ               /");
        System.out.println("4) Real BRZ =============> Dolar                  /");
        System.out.println("5) Dolar ================> Peso Colombiano        /");
        System.out.println("6) Peso Colombiano ======> Dolar                  /");
        System.out.println("7) Salir                                          /");
        System.out.println("Por favor elija una opción válida:                /");
        System.out.println("///////////////////////////////////////////////////");
    }

    public static void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                convertir("USD", "ARS");
                break;
            case 2:
                convertir("ARS", "USD");
                break;
            case 3:
                convertir("USD", "BRL");
                break;
            case 4:
                convertir("BRL", "USD");
                break;
            case 5:
                convertir("USD", "COP");
                break;
            case 6:
                convertir("COP", "USD");
                break;
            case 7:
                break;
            default:
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
                System.out.println("Opcion no valida. Por favor, intente de nuevo.      +");
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
                break;
        }
    }

    private static void convertir(String desde, String hacia) {
        try {
            Scanner teclado = new Scanner(System.in);
            double cantidad = 0.0;

            if (desde.equals("USD")) {
                System.out.println(ingreseCantidad + " de Dolares (USD):");
            } else if (desde.equals("ARS")) {
                System.out.println(ingreseCantidad + " de Pesos Argentinos (ARS):");
            } else if (desde.equals("BRL")) {
                System.out.println(ingreseCantidad + " de Reales Brasileños (BRL):");
            } else if (desde.equals("COP")) {
                System.out.println(ingreseCantidad + " de Pesos Colombianos (COP):");
            }

            cantidad = teclado.nextDouble();

            Double rangoDesde = Main.result.conversion_rates().get(desde);
            Double rqngoHacia = Main.result.conversion_rates().get(hacia);

            if (rangoDesde != null && rqngoHacia != null) {
                double conversorCantidad;

                if (desde.equals("USD")) {
                    conversorCantidad = cantidad * rqngoHacia;
                } else if (hacia.equals("USD")) {
                    conversorCantidad = cantidad / rangoDesde;
                } else {
                    conversorCantidad = cantidad / rangoDesde * rqngoHacia;
                }
                System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
                System.out.println(cantidad + " " + desde + " es igual a " + conversorCantidad + " " + hacia + ".               %");
                System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
            } else {
                System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
                System.out.println("No se encontro la tasa de conversión para " + (desde.equals("USD") ? hacia : desde) + ".");
                System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
            }
        } catch (InputMismatchException e) {
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            System.out.println("Error: Ingrese un numero valido para la cantidad.   @");
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        }
    }
}
