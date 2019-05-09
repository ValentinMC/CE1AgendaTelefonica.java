package pqt_serializable;
import java.util.Scanner;
import java.io.*;

public class CE1AgendaTelefonica {
    static Scanner poTeclado = new Scanner(System.in);
    static File f = new File("C:\\ficheros\\fichero_serializable.dat");
    static FileOutputStream fos;
    static FileInputStream fis;
    static ObjectOutputStream co;
    static ObjectInputStream ois;

    static class ClaseOutput extends ObjectOutputStream {
        ClaseOutput(FileOutputStream f) throws IOException{
            super(f);
        }
        protected void writeStreamHeader() throws IOException{}
    }//ClaseOutput

    static CAgenda p1;
    public static void main(String [] args) throws IOException, ClassNotFoundException{

        try{
            fos = new FileOutputStream(f, true);
            co = new ClaseOutput(fos);
            p1 = new CAgenda("Valentin", "Calin",617914518,"valentincalin.99@gmail.com");
            co.writeObject(p1);
            p1= new CAgenda("Sara","Garcia",612589462,"saragarc.mail");
            co.writeObject(p1);

        }catch(IOException s){
            System.out.println("No se ha escrito los dos por defecto.");
        }
        co.close();
        fos.close();
        boolean wbSeguir=true;

        while(wbSeguir){
            System.out.println(msSacarMenu());
            int iiOpcion = poTeclado.nextInt();poTeclado.nextLine();

            switch(iiOpcion){
                case 1:{
                    System.out.println("Dime el nombre");
                    String pisNombre = poTeclado.nextLine();
                    System.out.println("Dime el apellido");
                    String pisApellido = poTeclado.nextLine();
                    System.out.println("Dime el nº de tlfno");
                    int piiTlfno = poTeclado.nextInt(); poTeclado.nextLine();
                    System.out.println("Dime tu e-mail");
                    String pisEmail = poTeclado.nextLine();

                    mvAlta(pisNombre, pisApellido, piiTlfno, pisEmail);
                    break;
                }
                case 2:{
                    mvBusqueda(miPedirTlfno());
                    break;
                }
                case 3:{

                    break;
                }
                case 4:{
                    break;
                }
                case 5:{
                    mvSalir();
                    wbSeguir = false;
                    break;
                }

            }
        }//while
    }//main

    static String msSacarMenu(){
        String sMenu = "Elija una opción:\n1)Alta\n2)Búsqueda\n3)Modificación\n4)Eliminación\n5)Salir";
        return sMenu;
    }//msSacarMenu
    static int miPedirTlfno(){
        System.out.println("¿Qúe número?");
        int piRespuesta = poTeclado.nextInt();poTeclado.nextLine();
        return piRespuesta;
    }

    static void mvAlta(String isNombre, String isApellido, int iiTlfno, String isEmail) throws IOException{
        CAgenda p1;
        try{
            fos = new FileOutputStream(f, true);
            co = new ClaseOutput(fos);
            p1 = new CAgenda(isNombre, isApellido, iiTlfno,isEmail);
            co.writeObject(p1);
            co.close();
            fos.close();
        }catch(IOException e){
            System.out.println("No se pudo escribir");
        }

    }//msAlta

    static void mvSalir(){
        CAgenda p1;
        try{
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            try{
                while(true){
                    p1=(CAgenda)ois.readObject();
                    System.out.println(msEscribir(p1));
                }
            }catch(EOFException eof){
                System.out.println("FIN DE FICHERO");
            }
            ois.close();
            fis.close();

        }catch(IOException ex){
            System.out.println("No ha leido al salir");
        }
        catch(ClassNotFoundException cnfe){
            System.out.println("Clase no encontrada");
        }
    }//mvSalir

    static void mvBusqueda(int iiTelfno) throws FileNotFoundException, IOException, ClassNotFoundException{
        try {
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            CAgenda p1;
            try {
                while (true) {
                    p1 =(CAgenda)ois.readObject();
                    if(p1.piTlfno==iiTelfno){
                        System.out.println(msEscribir(p1));
                    }//if
                }//while
            }catch (EOFException sw){
                System.out.println("End of file babe");
            }
        }catch(IOException ess) {
            System.out.println("The searching of the number is not working gurl");
        }//try
    }//mvBusqueda

    static String msEscribir(CAgenda pi1){
        String sEscribir = "PERSONA\n---------\nNombre: "+pi1.psNombre+"\nApellidos: "+pi1.psApellidos
                +"\nNúmero: "+pi1.psNombre+"\nE-Mail: "+pi1.psMail+"\n";
        return sEscribir;
    }//msEscribir
}//CE1AgendaTelefonica

class CAgenda implements Serializable{
    String psNombre;
    String psApellidos;
    int piTlfno;
    String psMail;

    CAgenda(String isNombre, String isApellidos, int iiTlfno, String isMail){
        psNombre = isNombre;
        psApellidos = isApellidos;
        piTlfno = iiTlfno;
        psMail = isMail;
    }//CAgenda()
}//CAgenda



