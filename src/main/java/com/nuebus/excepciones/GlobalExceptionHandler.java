package com.nuebus.excepciones;

/**
 *
 * @author Valeria
 */
import io.jsonwebtoken.ExpiredJwtException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.nuebus.utilidades.IAuthenticationFacade;
import com.nuebus.utilidades.Utilities;

@ControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private IAuthenticationFacade authenticationFacade;

    public static int LONGITUD_MAX_MENSAJE = 50;

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IOException.class)
    public ResponseEntity<  ExceptionResponse> handleIOException(HttpServletRequest request, IOException ex) {

        String mensaje = "Json mal formado ";

        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode("JsonParseException");
        response.setErrorMessage(mensaje + ex.getMessage());
        // Al log va completo el log 
        loguearError(response, request);

        response.setErrorMessage(mensaje + getMensajeLimitado(ex.getMessage()));

        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.BAD_REQUEST);
    }
    
    
    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ExceptionHandler(ErrorException.class)
    public ResponseEntity< ExceptionResponse> handleErrorException( HttpServletRequest request,
                                                                     ErrorException ex ) {

        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode( ex.getErrorCode() );
        response.setErrorMessage( ex.getErrorMessage() );        
        loguearError(response, request);
        
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    } 
    

    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(io.jsonwebtoken.ExpiredJwtException.class)
    public ResponseEntity<  ExceptionResponse> handleTowenExpirado(HttpServletRequest request, ExpiredJwtException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode("Token Expirado");
        response.setErrorMessage(ex.getMessage());
        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.UNAUTHORIZED);
    }

    private String getMensajeLimitado(String mensaje) {
        String retorno = "";
        if (mensaje != null) {
            if (mensaje.length() > LONGITUD_MAX_MENSAJE) {
                retorno = mensaje.substring(0, LONGITUD_MAX_MENSAJE);
            } else {
                retorno = mensaje;
            }
        }
        return retorno;
    }

    @ExceptionHandler(org.springframework.web.HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<  ExceptionResponse> handleMetodoNoPermitido(HttpServletRequest request, HttpRequestMethodNotSupportedException ex) {

        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode("Metodo No permitido");
        response.setErrorMessage(ex.getMessage());

        loguearError(response, request);

        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.METHOD_NOT_ALLOWED);

    }

    @ExceptionHandler(ValidacionExcepcion.class)
    public ResponseEntity< Map<String, Set<String>>> handleValidacionExcepcion(ValidacionExcepcion ex) {
        return new ResponseEntity<>(ex.getErrors(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ValidacionAtributoException.class)
    public ResponseEntity<  List<AtributoException>> handleValidacionExcepcion(ValidacionAtributoException ex) {
        return new ResponseEntity<>(ex.excepciones, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ValidacionGenerica.class)
    public ResponseEntity<  Object> handleValidacionGenericaExcepcion(ValidacionGenerica ex) {
        logger.error("Validacion Generica handler executed");
        return new ResponseEntity<>(ex.objeto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(org.springframework.dao.InvalidDataAccessApiUsageException.class)
    public void handleHibernateHibernateException(InvalidDataAccessApiUsageException ex) {
        logger.error("[could not resolve property: undefined of]");
        logger.error(ex.getMessage());
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public void handlerNumberFormatHandler(NumberFormatException ex) {
        logger.error("NumberFormatException!!!" + ex.getMessage());
    }

    //Es preferible saber la traza del null
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(java.lang.NullPointerException.class)
    public ResponseEntity<ExceptionResponse> handleNullPointerException(HttpServletRequest request, java.lang.NullPointerException ex) {

        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode("Referencia Nula");
        response.setErrorMessage(ex.getMessage());

        loguearError(response, request);

        ex.printStackTrace();

        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
    public ResponseEntity< Object> handleInvalidInput(org.springframework.web.bind.MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        Map<String, Set<String>> errors = Utilities.parsearBinding(result);
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleResourceNotFound(ResourceNotFoundException ex, HttpServletRequest request) {
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode("No encontrado");
        response.setErrorMessage(ex.getMessage());
        loguearError(response, request);
        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ExceptionHandler(org.springframework.dao.DataAccessException.class)
    public ResponseEntity<ExceptionResponse> handleDataAccess(HttpServletRequest request, org.springframework.dao.DataAccessException ex) {

        String mensaje = "";
        if (ex.getCause() != null) {

            if (ex.getCause() instanceof org.hibernate.exception.GenericJDBCException) {
                if (((org.hibernate.exception.GenericJDBCException) ex.getCause()).getSQLException() != null) {
                    SQLException sql = ((org.hibernate.exception.GenericJDBCException) ex.getCause()).getSQLException();
                    mensaje = sql.getMessage();
                }
            } else if (ex.getCause() instanceof org.springframework.dao.DataIntegrityViolationException) {
                mensaje = ((org.springframework.dao.DataIntegrityViolationException) ex.getCause()).getMessage();
            } else if (ex.getCause() instanceof ConstraintViolationException) {
                if (((ConstraintViolationException) ex.getCause()).getConstraintName() != null) {
                    mensaje = "Regla de integridad Constraint" + ((ConstraintViolationException) ex.getCause()).getConstraintName();
                }
            } else if (ex.getCause() instanceof org.hibernate.exception.DataException) {
                mensaje = ((org.hibernate.exception.DataException) ex.getCause()).getSQLException().getMessage();
            } else if (ex.getCause() instanceof org.hibernate.HibernateException) {
                mensaje = "Regla de integridad " + ((org.hibernate.HibernateException) ex.getCause()).getMessage();
            } else {
                logger.error("DataAccessException cause !!!" + ex.getCause().getClass().getCanonicalName());
                ex.printStackTrace();
            }

        } else {
            logger.error("DataAccessException sin cause !!!" + ex.getCause().getClass().getCanonicalName());
            mensaje = "Gral " + ex.getMessage();
            ex.printStackTrace();
        }

        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode("Sql Erroneo");
        response.setErrorMessage(mensaje);

        loguearError(response, request);

        //ex.printStackTrace();

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);

    }

    private String getUserName() {

        if (authenticationFacade.getAuthentication() != null) {
            return (String) authenticationFacade.getAuthentication().getPrincipal();
        }
        return "Desconocido";
    }

    private void loguearError(ExceptionResponse response, HttpServletRequest request) {
        logger.error("Usr[" + getUserName() + "] - " + request.getRequestURI() + " - " + request.getMethod() + " - "
                + response.getErrorCode() + " - " + response.getErrorMessage());
    }

}
