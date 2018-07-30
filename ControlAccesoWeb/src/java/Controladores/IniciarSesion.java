
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
        boolean verificador;
        setPassword(cifrar.cifrarRot13(this.password));
     ResultSet consulta=conectar.Consulta("select *from artesano a where a.correo='"+this.correo+"'"+"&& a.password1='"+this.password+"'");
       try{
   
           while(consulta.next()){
               
                validarConrreo=consulta.getString(4);
                validarContrasenia=consulta.getString(5);
                setValidarContrasenia(cifrar.DesCifrarRot13(validarContrasenia));
                System.out.println("-------------------------------------------------");
                System.out.println("Correo: "+validarConrreo+"\ncontraseña:"+validarContrasenia);
                verificador=PermitirIngreso(validarConrreo, validarContrasenia);
               
                System.out.println("Variable verificador "+verificador);
            }       
        }catch(SQLException e){   
            System.out.println("hay error en el while");
        }  
    }
    public boolean PermitirIngreso(String correop,String passwordp)
    {
        boolean ban;
        if(correop.equals(correo)&&passwordp.equals(password))
        {
        ban=true;
        }else{
        ban=false;
        }
    return ban; 
    }
    
}
