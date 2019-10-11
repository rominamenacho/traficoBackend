package com.nuebus.service;

import com.nuebus.model.MailConfig;
import java.io.File;
import java.util.Properties;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;


/**
 *
 * @author Valeria
 */
@Service
public class EmailSenderService {
    
    @Autowired
    private JavaMailSender emailSender;
    
    public void sendSimpleMessage( MailConfig mailConfig, String subject, String text, String destino, String fromCustomizado ){    
         sendSimpleMessage( mailConfig, subject, text,  new String[] { destino }, fromCustomizado );
    }

    public void sendSimpleMessage( MailConfig mailConfig, String subject, String text, String[] destinatarios,String fromCustomizado ){
        
        if( mailConfig != null ){
            ((JavaMailSenderImpl)emailSender).setHost( mailConfig.getSmtpServer() );       
            ((JavaMailSenderImpl)emailSender).setPort( mailConfig.getSmtpPort() );      
            ((JavaMailSenderImpl)emailSender).setUsername( mailConfig.getUser() );            
            ((JavaMailSenderImpl)emailSender).setPassword( mailConfig.getPassword() );                          
            
       }
        
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject( subject );
        message.setText( text );
        message.setTo( destinatarios );
        message.setFrom( fromCustomizado );
        emailSender.send(message);
    }
    
    
    public void sendMessageWithAttachment( String to, String subject, String text, String pathToAttachment) throws MessagingException {
        
        MimeMessage message = emailSender.createMimeMessage();

        MimeMessageHelper helper;         
        helper = new MimeMessageHelper(message, true);
         

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text);

        FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
        helper.addAttachment("Invoice", file);

        emailSender.send(message);
        
    }
    
    /*public void sendMessageWithAttachmentOnTheFligth( MailConfig mailConfig,  String textAdjunto, String textMail,
            String nombreAttachment,  byte[] attachment, String destino  ) throws MessagingException {
        
       

       if( mailConfig != null ){
            ((JavaMailSenderImpl)emailSender).setHost( mailConfig.getHost() );       
            ((JavaMailSenderImpl)emailSender).setPort( mailConfig.getPort() );
            ((JavaMailSenderImpl)emailSender).setUsername( mailConfig.getUsername() );
            ((JavaMailSenderImpl)emailSender).setPassword( mailConfig.getPassword() );               
       }
       
        MimeMessage message = emailSender.createMimeMessage();       
        MimeMessageHelper helper;         
        helper = new MimeMessageHelper(message, true);
        final ByteArrayResource is = new ByteArrayResource(attachment);      

        helper.setTo( destino );
        helper.setSubject(textAdjunto);
        helper.setText( textMail );  
        helper.addAttachment( nombreAttachment , is );       

        emailSender.send(message);        
    } */
   
   /* public void sendMessageWithAttachmentOnTheFligth( MailConfig mailConfig,  String textAdjunto, String textMail,
            String nombreAttachment,  byte[] attachment, String[] destinatarios  ) throws MessagingException {  

       if( mailConfig != null ){
            ((JavaMailSenderImpl)emailSender).setHost( mailConfig.getHost() );       
            ((JavaMailSenderImpl)emailSender).setPort( mailConfig.getPort() );
            ((JavaMailSenderImpl)emailSender).setUsername( mailConfig.getUsername() );
            ((JavaMailSenderImpl)emailSender).setPassword( mailConfig.getPassword() );               
       }
       
        MimeMessage message = emailSender.createMimeMessage();       
        MimeMessageHelper helper;         
        helper = new MimeMessageHelper(message, true);
        final ByteArrayResource is = new ByteArrayResource(attachment);      

        helper.setTo( destinatarios );
        helper.setSubject(textAdjunto);
        helper.setText( textMail );  
        helper.addAttachment( nombreAttachment , is );       

        emailSender.send(message);        
    }*/ 
    
     
    @Bean
    public JavaMailSender getJavaMailSender() {
        
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        //props.put("mail.debug", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        
        props.put("mail.smtp.socketFactory.port", String.valueOf(465));
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");           

        return mailSender;
    }
    
    
    //((BeanDefinitionRegistry) beanFactory).removeBeanDefinition("myBean");
    //((DefaultListableBeanFactory) beanFactory).destroySingleton("myBean");
}
