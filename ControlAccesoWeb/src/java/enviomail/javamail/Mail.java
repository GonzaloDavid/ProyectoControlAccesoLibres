
package enviomail.javamail;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;


public class Mail {
   
     private final String validate = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";//los caractees que permite un mail
    private final String from = "aplicacioneslibresepn@gmail.com";//es el correo del cual encviamos
    private final String pwd = "password1478";//es la clave del correo
    private final Properties properties = new Properties();//
    private Session session;

    public boolean validarEmail(String email) {
        Pattern patron = Pattern.compile(validate);

        Matcher match = patron.matcher(email);
        return match.matches();
    }

    private void init() {
        //adicionamos la propiedades del servidor  gmail 
        properties.put("mail.transport.protocol", "smtps");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.auth", "true");//para que ingeese con tu usuario y contarseña 
        //Esto añadi 
        properties.put("mail.smtp.starttls.enable", "false");
        properties.put("mail.smtp.ssl.enable", "false");
        properties.put("mail.smtp.socketFactory.fallback", "true");
    
        session = Session.getDefaultInstance(properties);
        session.setDebug(true);
    }

    public void sendEmail(String destinatario, String asunto, String mensaje) {
        init();
 //creando los campos del mensaje
        try {
            MimeMessage mimemessage = new MimeMessage(session);
            mimemessage.setFrom(new InternetAddress(from));
            mimemessage.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
            mimemessage.setSubject(asunto);
            mimemessage.setContent(mensaje, "text/html");

            Transport t = session.getTransport();//conecta y envia el mensahje
            t.connect("smtp.gmail.com", 465, from, pwd);
            t.sendMessage(mimemessage, mimemessage.getRecipients(Message.RecipientType.TO));
            t.close();
        } catch (MessagingException ex) {
            System.out.println(ex);
        }
    }
    /*
    public static void main(String[] args) {
       
         Mail mail=new Mail();
        
        mail.sendEmail("davidgonzalomejia@hotmail.com","Acceso al sistema", "Usuario: "+"Gonzalo david"+"\nPassword: "+"Password");
    
       
    }
    */

}
