package com.nuebus.utilidades;

import java.text.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
   
/**
 * A Class class.
 * <P>
 * @author Oracle JDeveloper Team
 */
public class Utilities {
    public static final String FORMAT_ORACLE_TIMESTAMP = "dd/mm/yyyy hh24:mi:ss";
    public static final String FORMAT_JAVA_TIMESTAMP = "dd/MM/yyyy HH:mm:ss";
    
    public static final String FORMAT_DATETIME = "dd/MM/yyyy HH:mm:ss";
    public static final String FORMAT_DATE = "dd/MM/yyyy";
    public static final String FORMAT_TIME = "HH:mm:ss";
    public static final String FORMAT_DATEHOUR = "dd/MM/yyyy HH:mm";
    public static final String FORMAT_HOUR = "HH:mm";
    public static final String FORMAT_DAY = "EEE";
    public static final String FORMAT_DAYHOUR = "EEE HH:mm";
    public static final String FORMAT_DAYDATEHOUR = "EEE dd/MM/yyyy HH:mm";
    public static final String FORMAT_DAYDATE = "EEE dd/MM/yyyy";
    public static final String FORMAT_SQL_DATETIME = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_SQL_DATE = "yyyy-MM-dd";
    public static final String FORMAT_SQL_TIME = "HH:mm:ss";
    public static final DateFormatSymbols formatSymbols = new DateFormatSymbols(new java.util.Locale("es", "ES"));
    
    public static final String FORMAT_MONEY = "###,##0.00##";
    public static final String FORMAT_PORCENTAJE = "#0.00";
    public static final String FORMAT_BOLETO = "000000000000";
    public static final String FORMAT_BOLETO_8DIGITOS="00000000";
    
    public static final String YES  = "S";
    public static final String NO   = "N";
    
    public static final String TRUE     = "T";
    public static final String FALSE    = "F";
    
    public static final String REX_SOLO_LETRAS    = "[a-zA-Z������������������������]";
    public static final String REX_NOMBRES    = "[a-zA-Z������������������������''\\s]{1,30}";
    public static final String REX_SOLO_NUMEROS    = "[0-9]";
    public static final String REX_EMAIL    = "^[\\w-]+(\\.[\\w-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    
    public static final String ANTERIOR     = "<";
    public static final String POSTERIOR     = ">";
    public static final String IGUAL    = "=";
    
   
    /**
     * Constructor
     */    
    public Utilities() {
        
    }
    
   
    public static int parseInt(String _value){
        int retorno = 0;
        try{
            retorno = Integer.parseInt(_value.trim());
        } catch (Exception e) {
            retorno = 0;
        }
        return retorno;
    }
    
    public static int parseInt(char _value) {
        int retorno = 0;
        try{
            retorno = Integer.parseInt(String.valueOf(_value));
        } catch (Exception e) {
            retorno = 0;
        }
        return retorno;
    }
    
    public static long parseLong(String _value){
        long retorno = 0;
        try{
            retorno = Long.parseLong(_value.trim());
        } catch (Exception e) {
            retorno = 0;
        }
        return retorno;
    }
    
    
    public static double parseDouble(String _value){
        double retorno = 0;
        try{
            retorno = Double.parseDouble(_value.trim());
        } catch (Exception e){
            retorno = 0;
        }
        return retorno;
    }
    
    public static boolean parseBoolean(String _value) {
        boolean retorno = false;
        try {
            retorno = new Boolean(_value.toLowerCase()).booleanValue();
        } catch (Exception e) {
            retorno = false;
        }
        return retorno;
    }
    
    public static float parseFloat(String _value){
        float retorno = 0;
        try{
            retorno = Float.parseFloat(_value.trim());
        } catch (Exception e){
            retorno = 0;
        }
        return retorno;
    }
    
    public static boolean isInteger(String _nro){
        boolean ok=true;
        try{
            Integer nro=Integer.valueOf(_nro);
        }catch(NumberFormatException e){
            ok=false;
        }
        return ok;
    }
    
    public static boolean isDouble(String _nro){
        boolean ok=true;
        try{
            Double nro=Double.valueOf(_nro);
        }catch(NumberFormatException e){
            ok=false;
        }
        return ok;
        
    }
    public static boolean isFloat(String _nro){
        boolean ok=true;
        try{
            Float nro=Float.valueOf(_nro);
        }catch(NumberFormatException e){
            ok=false;
        }
        return ok;
    }
    
    public static boolean isLong(String _nro){
        boolean ok=true;
        try{
            Long nro=Long.valueOf(_nro);
        }catch(NumberFormatException e){
            ok=false;
        }
        return ok;
    }
    
    public static  <E> Map<String, Set<String>>  validarEntity( E objeto ){    
        
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
                
        Map<String, Set<String>> errors = new HashMap<>();
        
        Set<ConstraintViolation<E>> violations = validator.validate(objeto);        
        for (ConstraintViolation<E> violation : violations) {             
            
            java.lang.annotation.Annotation annotation = violation.getConstraintDescriptor().getAnnotation();            
            if( annotation.annotationType() == NotNull.class 
                    || annotation.annotationType() == NotBlank.class ){
                errors.computeIfAbsent( violation.getPropertyPath().toString(), key -> new HashSet<>()).add( "required" );            
            }
            if( annotation.annotationType() == Size.class ){                
                String valor = (String)violation.getInvalidValue();
                
                if( valor.length() < ((Size)annotation).min() ){
                    errors.computeIfAbsent( violation.getPropertyPath().toString(), key -> new HashSet<>()).add( "minlength" );      
                }else{
                    errors.computeIfAbsent( violation.getPropertyPath().toString(), key -> new HashSet<>()).add( "maxlength" );      
                }                    
            }
            
            if( annotation.annotationType() == Digits.class ){
                errors.computeIfAbsent( violation.getPropertyPath().toString(), key -> new HashSet<>()).add( "overDigit" );   
            }              
        }        
        return errors;    
    }    
    
    public static Map<String, Set<String>>  parsearBinding ( BindingResult result ){            
        Map<String, Set<String>> errors = new HashMap<>();                      
        for ( FieldError fieldError: result.getFieldErrors() ) {                    
            errors.computeIfPresent( fieldError.getField(),( key, value ) -> { value.add( fieldError.getDefaultMessage() ); return value; } );
            errors.computeIfAbsent( fieldError.getField(), key -> new HashSet<>()).add( fieldError.getDefaultMessage() );                   
        } 
        return errors;    
    }
    
    public static  <E> Object validarEntityError( E objeto, E error ){    
        
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
                
        Map<String, Set<String>> errors = new HashMap<>();        
        Set<ConstraintViolation<E>> violations = validator.validate(objeto);        
        for (ConstraintViolation<E> violation : violations) {      
            errors.computeIfPresent( violation.getPropertyPath().toString(),( key, value ) -> { value.add( violation.getMessage() ); return value; } );
            errors.computeIfAbsent( violation.getPropertyPath().toString(), key -> new HashSet<>()).add( violation.getMessage() );   
        }             
        
        BeanWrapper objetoWrapper = new BeanWrapperImpl(error);
        
        errors.forEach((k,v)->{                
                objetoWrapper.setPropertyValue( k, v );
        } );      
        
        return error;    
    }
    
    
}