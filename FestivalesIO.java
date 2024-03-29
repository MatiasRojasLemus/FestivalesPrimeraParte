import java.time.LocalDate;
import java.util.*;

/**
 * La clase contiene m�odos est�ticos que permiten
 * cargar la agenda de festivales leyendo los datos desde
 * un fichero
 */
public class FestivalesIO {


    public static void cargarFestivales(AgendaFestivales agenda) {
        Scanner sc = null;
        try {
            sc = new Scanner(FestivalesIO.class.getResourceAsStream("/festivales.csv"));
            while (sc.hasNextLine()) {
                String lineaFestival = sc.nextLine();
                Festival festival = parsearLinea(lineaFestival);
                agenda.addFestival(festival);

            }
        } finally {
            if (sc != null) {
                sc.close();
            }
        }

    }

    /**
     * se parsea la l�nea extrayendo sus datos y creando y
     * devolviendo un objeto Festival
     * @param lineaFestival los datos de un festival
     * @return el festival creado
     */
    public static Festival parsearLinea(String lineaFestival) {
        //TODO
        String[] arrayAux = lineaFestival.split(":");
        String nombreFestival = nombreSEYC(arrayAux[0]);
        String lugarEvento = enMayusculas(arrayAux[1]);
        LocalDate fechaInicio = fechaALocalDate(arrayAux[2]);
        int duracion = Integer.parseInt(arrayAux[3]);
        HashSet<Estilo> estilos = conjuntoEstilos(arrayAux);

        return new Festival(nombreFestival,lugarEvento,fechaInicio,duracion,estilos);

    }

    //SEYC = Sin Espacios (a ambos lados) Y Capitalizado
    private static String nombreSEYC(String nombre){
        String[] palabrasNombre = nombre.split(" ");
        StringBuilder resul = new StringBuilder();

        for (String palabraDelNombre: palabrasNombre){
            resul.append(palabraDelNombre.substring(0,1).toUpperCase() + palabraDelNombre.substring(1).toLowerCase() + " ");
        }

        return resul.toString().trim();

    }

    private static String enMayusculas(String lugar){
        return lugar.toUpperCase();
    }

    private static LocalDate fechaALocalDate(String fechaInicio){
        String[] fecha = fechaInicio.split("-");
        Integer[] numerosParaFecha = new Integer[fecha.length];
        int indice = 0;

        for (String numeroStr: fecha){
            numerosParaFecha[indice] = Integer.parseInt(numeroStr);
            indice++;
        }

        int anyo = numerosParaFecha[2];
        int mes = numerosParaFecha[1];
        int dia = numerosParaFecha[0];

        return LocalDate.of(anyo,mes,dia);
    }

    private static HashSet<Estilo> conjuntoEstilos(String[] array){
        HashSet<Estilo> estilos = new HashSet<>();
        int indice = 4;
        while (indice < array.length){
            switch (array[indice].toUpperCase()){
                case "HIPHOP":
                    estilos.add(Estilo.HIPHOP);
                    break;

                case "INDIE":
                    estilos.add(Estilo.INDIE);
                    break;

                case "POP":
                    estilos.add(Estilo.POP);
                    break;

                case "ROCK":
                    estilos.add(Estilo.ROCK);
                    break;

                case "FUSION":
                    estilos.add(Estilo.FUSION);
                    break;

                case "RAP":
                    estilos.add(Estilo.RAP);
                    break;

                case "ELECTRONICA":
                    estilos.add(Estilo.ELECTRONICA);
                    break;

                case "PUNK":
                    estilos.add(Estilo.PUNK);

                case "BLUES":
                    estilos.add(Estilo.BLUES);
            }
            indice++;
        }
        return estilos;
    }
}