package com.nuebus.excepciones;

/**
 *
 * @author Valeria
 */
public class ErrorException extends RuntimeException  {    
    private static final long serialVersionUID = 1L;     
    private String errorCode;
    private String errorMessage;

    public ErrorException( String errorCode, String errorMessage ) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public ErrorException(String token_expirado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }   
    
}
