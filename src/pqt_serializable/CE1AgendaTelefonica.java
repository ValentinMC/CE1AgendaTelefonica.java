package pqt_serializable;
import java.util.Scanner;
import java.io.*;

public class CE1AgendaTelefonica {
    static Scanner poTeclado = new Scanner(System.in);

    public static void main(String [] args) throws IOException, 
            ClassNotFoundException{
        /*class ClaseOutput extends ObjectOutputStream {
            ClaseOutput(FileOutputStream f) throws IOException{
                super(f);
            }
            protected void writeStreamHeader() throws IOException{}
        }//ClaseOutput
        try{
            fos = new FileOutputStream(f);
            ClaseOutput co = new ClaseOutput(fos);
            CAgenda p1 = new CAgenda("Valentin", "Calin",617914518,"valentincalin.99@gmail.com");
            co.writeObject(p1);
            CAgenda p2= new CAgenda("Sara","Garcia",612589462,"saragarc.mail");
            co.writeObject(p2);
            System.out.println("SE HAN ESCRITO");

        }catch(IOException s){
            System.out.println("No se ha escrito los dos por defecto.");
        }
        co.close();
        fos.close();
        1
        */
        
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

                    CAgenda.mvAlta(pisNombre, pisApellido, piiTlfno, pisEmail);
                    break;
                }
                case 2:{
                    CAgenda.mvBusqueda(miPedirTlfno());
                    break;
                }
                case 3:{

                    break;
                }
                case 4:{
                    break;
                }
                case 5:{
                    CAgenda.mvSalir();
                    wbSeguir = false;
                    break;
                }

            }
        }//while
    }//main

    static String msSacarMenu(){
        String sMenu = "Elija una opción:\n1)Alta\n2)Búsqueda\n3)Modificación"
                + "\n4)Eliminación\n5)Salir";
        return sMenu;
    }//msSacarMenu
    
    static int miPedirTlfno(){
        System.out.println("¿Qúe número?");
        int piRespuesta = poTeclado.nextInt();poTeclado.nextLine();
        return piRespuesta;
    }
}//CE1AgendaTelefonica