package Controladores;

import enviomail.javamail.Mail;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Gonzalo
 */
@ManagedBean
@RequestScoped
public class CrearCuentaa {

    String nombre;
    String apellido;
    String cedula;
    String correo;
    String Password;
    String confPassword;
    conexion.Conectar conectar=new conexion.Conectar();
    String validarConrreo;
    String validarContrasenia;

    public CrearCuentaa() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        System.out.println("EL valor de cedula es :" + cedula);
        this.cedula = cedula;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
        
    }

    public String getConfPassword() {
        return confPassword;
    }

    public void setConfPassword(String confPassword) {
        this.confPassword = confPassword;
    }

    public void enviarMail() {
        Mail mail = new Mail();
        mail.sendEmail(this.correo, "Acceso al sistema", "Usuario: " + this.nombre+"\n" + "\nPassword: " + this.Password);

    }
    public void insertarUsuario()
    {
    enviarMail();
    conectar.EjecutarSQL("Insert into artesano(nombre,apellido,correo,password1,confPassword)values('"+this.nombre+"','"+this.apellido+"','"+this.correo+"','"+this.Password+"','"+this.confPassword+"')");
    }
    
}
