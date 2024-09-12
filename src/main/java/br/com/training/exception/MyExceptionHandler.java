package br.com.training.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import java.io.Serializable;


@ControllerAdvice
public class MyExceptionHandler {

	public class UserNotFoundException extends Exception implements Serializable {
	    private static final long serialVersionUID = 1L;

	    public UserNotFoundException(String message) {
	        super(message);
	    }
	}

    public static class InvalidRequestException extends Exception implements Serializable {
    	private static final long serialVersionUID = 1L;
        public InvalidRequestException(String message) {
            super(message);
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        String errorMessage = "Ocorreu um erro interno. Tente novamente mais tarde.";
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
        String errorMessage = "Usuário não encontrado.";
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<Object> handleInvalidRequestException(InvalidRequestException ex, WebRequest request) {
        String errorMessage = "Requisição inválida.";
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
}
