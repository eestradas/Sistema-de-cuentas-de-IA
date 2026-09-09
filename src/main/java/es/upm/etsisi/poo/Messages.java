package es.upm.etsisi.poo;

public class Messages {
    private Messages(){}

    public static final String HELP =
            "Comandos disponibles:\n" +
                    "  user add DNI nombre password [numeroCuenta]\n" +
                    "  call send DNI \"texto\"\n" +
                    "  agent add DNI numeroAgente\n" +
                    "  agent send DNI numeroAgente \"texto 1#texto 2#texto 3\"\n" +
                    "  bill create DNI CALL|AGENT|MIXED\n" +
                    "  exit";

    public static final String WELCOME =
            "Sistema de cuentas de IA. Escribe 'help' para ver los comandos disponibles, 'exit' para salir.";

    public static final String PROMPT = "> ";

    public static final String EXIT =
            "Saliendo del sistema.";

    public static final String FILE_PROCESSED =
            "Fichero procesado.";

    public static final String NOT_PREMIUM =
            "Error: esta operación solo está disponible para usuarios premium.";

    public static final String NO_PENDING_ITEMS =
            "Error: no hay elementos pendientes de facturación.";

    public static final String COMMAND_UNKNOWN =
            "Error: comando desconocido";

    public static final String COMMAND_MALFORMED =
            "Error: comando mal formado.";




    public static String runningFile(String filePath) {
        return "Ejecutando comandos desde: " + filePath;
    }

    public static String errorReadingInput(String message) {
        return "Error leyendo la entrada: " + message;
    }

    public static String errorReadingFile(String filePath, String message) {
        return "Error leyendo el fichero '" + filePath + "': " + message;
    }

    public static String userNotFound(String dni) {
        return "Error: no se ha encontrado un usuario con dni " + dni + ".";
    }

    public static String userAlreadyExists(String dni){
        return "Error: ya existe un usuario con DNI " + dni + ".";
    }

    public static String invalidDni(String dni){
        return "Error: el DNI " + dni + " no es válido.";
    }

    public static String invalidAccount(){
        return "Error: el número de cuenta no es válido. Debe tener exactamente 22 dígitos numéricos.";
    }

    public static String agentAlreadyExists(int agentId) {
        return "Error: ya existe un agente con número " + agentId + ".";
    }

    public static String agentNotFound(int agentId) {
        return "Error: no se ha encontrado un agente con número " + agentId + ".";
    }

    public static String wordLimitExceeded(int needed, int available) {
        return "Operación rechazada: se necesitan " + needed + " palabras pero solo quedan "
                + available + " disponibles.";
    }

    public static String callResponse(String response) {
        return "Respuesta: " + response;
    }

    public static String callResponseWithCost(String response, double cost) {
        return String.format("Respuesta: %s%n(Coste generado: %.2f€ pendiente de facturación.)", response, cost);
    }

    public static String agentResponseWithCost(String response, double cost) {
        return String.format("%s%n(Coste generado: %.2f€ pendiente de facturación.)", response, cost);
    }

    public static String billCreated(String type, double total) {
        return String.format("Factura creada [tipo=%s, total=%.2f€].", type, total);
    }

    public static String userCreated(String nombre, String dni) {
        return "Usuario creado correctamente: " + nombre + " (" + dni + ").";
    }

    public static String agentCreated(int agentId, String dni) {
        return "Agente " + agentId + " creado correctamente para el usuario " + dni + ".";
    }

    public static String unknownBillType(String type) {
        return "Error: tipo de factura desconocido: " + type + ". Usa CALL, AGENT o MIXED.";
    }

    public static String agentIdNotNumber(String token) {
        return "Error: el número de agente debe ser un entero, se recibió: " + token + ".";
    }

}
