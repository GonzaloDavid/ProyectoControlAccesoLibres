
package Controladores;

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
public class IniciarSesion {

    String correo;
    String password;
    String validarContrasenia;
    String validarConrreo;
    String notificar;
     boolean verificador;
    conexion.Conectar conectar=new conexion.Conectar();
    Cifrado.CifradoDatos cifrar=new Cifrado.CifradoDatos();
    public IniciarSesion() {
    }

    public String getNotificar() {
        return notificar;
    }

    public String getValidarContrasenia() {
        return validarContrasenia;
    }

    public boolean isVerificador() {
        return verificador;
    }

    public void setVerificador(boolean verificador) {
        this.verificador = verificador;
    }

    public void setValidarContrasenia(String validarContrasenia) {
        this.validarContrasenia = validarContrasenia;
    }

    public String getValidarConrreo() {
        return validarConrreo;
    }

    public void setValidarConrreo(String validarConrreo) {
        this.validarConrreo = validarConrreo;
    }

    public void setNotificar(String notificar) {
        this.notificar = notificar;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public void comprobarCuenta()
    {
       
     setPassword(cifrar.cifrarRot(this.password));
     ResultSet consulta=conectar.Consulta("select *from artesano a where a.correo='"+this.correo+"'"+"&& a.password1='"+this.password+"'");
       try{
   
           while(consulta.next()){
               
                validarConrreo=consulta.getString(4);
                validarContrasenia=consulta.getString(5);
                verificador=PermitirIngreso(validarConrreo, validarContrasenia);
                System.out.println("Correo: "+validarConrreo+"\ncontraseña:"+validarContrasenia);
                System.out.println("Variable verificador"+verificador);
            }       
        }catch(SQLException e){   
            System.out.println("hay error en el while");
        }  
       llamarNotificacion();
    }
    public boolean PermitirIngreso(String correop,String passwordp)
    {
        boolean ban;
        if(correop.equals(this.correo)&&passwordp.equals(this.password))
        {
        ban=true;
        }else{
        ban=false;
        }
    return ban; 
    }
    public void llamarNotificacion()
    {
    if(isVerificador()==false)
    {
        setNotificar("USUARIO NO REGISTRADO");
    }else{
    setNotificar("BIENVENIDO");
    }
            
    
    }
    
}
