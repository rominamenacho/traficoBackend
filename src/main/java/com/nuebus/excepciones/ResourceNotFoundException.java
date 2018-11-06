package com.nuebus.excepciones;

/**
 *
 * @author Valeria
 */
public class ResourceNotFoundException extends RuntimeException {
    
    private Long resourceId;

    public ResourceNotFoundException(Long resourceId, String message) {
        super(message);
        this.resourceId = resourceId;
    }
    
}
