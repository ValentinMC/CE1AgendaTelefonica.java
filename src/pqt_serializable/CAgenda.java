package pqt_serializable;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Scanner;

class CAgenda implements Serializable{
    String psNombre;
    String psApellidos;
    int piTlfno;
    String psMail;
    
    static Scanner poTeclado = new Scanner(System.in);
    static File f = new File("C:\\ficheros\\fichero_serializable.dat");
    static FileOutputStream fos;
    static FileInputStream fis;
    static ObjectOutputStream oos;
    static ObjectInputStream ois;

    static class ClaseOutput extends ObjectOutputStream {
        ClaseOutput(FileOutputStream f) throws IOException{
            super(f);
        }
        protected void writeStreamHeader() throws IOException{}
    }//ClaseOutput

    CAgenda(String isNombre, String isApellidos, int iiTlfno, String isMail){
        psNombre = isNombre;
        psApellidos = isApellidos;
        piTlfno = iiTlfno;
        psMail = isMail;
    }//CAgenda()
    
    static void mvAlta(String isNombre, String isApellido, int iiTlfno, 
            String isEmail) throws IOException{
    
        try{
            File f = new File("C:\\ficheros\\fichero_serializable.dat");
            fos = new FileOutputStream(f, true);
            ClaseOutput co = new ClaseOutput(fos);
            CAgenda p1 = new CAgenda(isNombre, isApellido, iiTlfno,isEmail);
            co.writeObject(p1);

            co.close();
            fos.close();
        }catch(IOException e){
            System.out.println("No se pudo escribir");
        }//try-catch
    }//msAlta
    
    static String msEscribir(CAgenda pi1){
        String sEscribir = "PERSONA\n---------\nNombre: "+pi1.psNombre+
                "\nApellidos: "+pi1.psApellidos
                +"\nNúmero: "+pi1.psNombre+"\nE-Mail: "+pi1.psMail+"\n";
        return sEscribir;
    }//msEscribir
    
    static void mvSalir(){
        try{
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            try{
                while(true){
                    CAgenda p1=(CAgenda)ois.readObject();
                    if (p1 instanceof CAgenda)
                        System.out.println(CAgenda.msEscribir(p1));
                    else System.out.println("Error");
                }
            }catch(EOFException eof){
                System.out.println("FIN DE FICHERO");
                ois.close();
                fis.close();
            }
        }catch(IOException ex){
            System.out.println("No ha leido al salir");
        }
        catch(ClassNotFoundException cnfe){
            System.out.println("Clase no encontrada");
        }//try-catch   
    }//mvSalir
    
    static void mvBusqueda(int iiTelfno) throws FileNotFoundException, 
            IOException, ClassNotFoundException{
        try {
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            try {
                while (true) {
                    CAgenda p1 =(CAgenda)ois.readObject();
                    if(p1.piTlfno==iiTelfno){
                        System.out.println(msEscribir(p1));
                    }//if
                }//while
            }catch (EOFException sw){
                System.out.println("End of file babe");
            }
        }catch(IOException ess) {
            System.out.println("No funciona la busqueda del numero");
        }//try-catch
    }//mvBusqueda
}//CAgenda
