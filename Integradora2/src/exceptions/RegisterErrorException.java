package exceptions;

public class RegisterErrorException extends Exception {
    public RegisterErrorException() {
        super("No se pudo registrar correctamente, intentelo de nuevo");
    }
}