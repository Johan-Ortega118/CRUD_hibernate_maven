
package com.mycompany.crudhibernatemaven;

import com.mycompany.dao.PersonaJpaController;
import com.mycompany.dao.exceptions.NonexistentEntityException;
import com.mycompany.dominio.Persona;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Principal {
    
    private static Scanner sc = new Scanner(System.in);
    private static Scanner st = new Scanner(System.in);
    private static Persona p;
    private static PersonaJpaController personaDao = new PersonaJpaController();
    private static int opcion, id;
    
    public static void main(String[] args){
        
        System.out.println("==========================================");
        System.out.println("==========================================");
        System.out.println("====        Elija una opción          ====");
        System.out.println("==========================================");
        System.out.println("==========================================");
        System.out.println("=====  1. Mostrar persona por id     =====");
        System.out.println("=====  2. Insertar persona a la BD   =====");
        System.out.println("=====  3. Actualizar un registro     =====");
        System.out.println("=====  4. Eliminar un registro       =====");
        System.out.println("=====  5. Listar personas            =====");
        System.out.println("==========================================");
        System.out.println("==========================================");
        
        opcion = sc.nextInt();
        
        switch(opcion){
            
            case 1:
                //Mostrar una persona por ID
                p = obtenerPersona();
                System.out.println(p);
                break;
                
            case 2:
                //Insertar persona a la BD
                p = getPersona();
                if(personaDao.setPersona(p))
                    System.out.println("Se ha insertado con éxito");
                else
                    System.out.println("Ha fallado la insersión...");

                break;
                
            case 3:
                //Actualizar un registro
                p = obtenerPersona();
                System.out.println(p);
                System.out.println("Ingresar nombre y apellidos nuevos: ");
                p = new Persona(id, st.nextLine(), st.nextLine());
        
                try {
                    personaDao.updatePersona(p);
                } catch (Exception ex) {
                    System.out.println("No se pudo actualizar el registro...");
                }

                break;
                
            case 4:
                //Eliminar un registro
                p = obtenerPersona();
                System.out.println("Persona a eliminar: ");
                System.out.println(p);
        
                try {
                    personaDao.deletePersona(id);
                    System.out.println("Persona eliminada exitosamente...");
                } catch (NonexistentEntityException ex) {
                    System.out.println("No se pudo eliminar el registro...");
                }
        
                break;
                
            case 5:
                //Listar personas
                List<Persona> personas = personaDao.listarPersonas();
                for (Iterator<Persona> iterator = personas.iterator(); iterator.hasNext();) {
                    Persona next = iterator.next();
                    System.out.println(next);
                }
                break;
            
            default:
                System.out.println("Fuera de rango...");
        }
        personaDao.cerrar();
    }
    
    private static Persona getPersona(){
        
        System.out.println("Ingrese id, nombre y apellido: ");
        Persona p = new Persona(sc.nextInt(), st.nextLine(), st.nextLine());
        
        return p;
        
    }

    private static Persona obtenerPersona(){
        
        System.out.println("Ingrese un id: ");
        id = sc.nextInt();
        p = personaDao.findPersona(id);
        return p;
    
    }
    
}
