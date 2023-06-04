/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

/**
 * Primero se ejecuta el servidor por que necesita establecer un puerto El
 * servidor usa la clase ServerSocket
 *
 * @author W
 */
public class Servidor {

    private ServerSocket servidor;
    private int port;

    public Servidor(int port) throws IOException {
        this.port = port;
        servidor = new ServerSocket(port);
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public ServerSocket getServidor() {
        return servidor;
    }

    public void setServidor(ServerSocket servidor) {
        this.servidor = servidor;
    }

    public static void main(String[] args) throws IOException {

        Servidor servidor = new Servidor(4060);
        Socket socket = servidor.getServidor().accept();
        System.out.println("Servidor ejecutando procesos....");
        System.out.println("Esperando cliente en el puerto: " + servidor.getPort());
        System.out.println("cliente conectado " + socket.getInetAddress());

        HashMap<String, String> diccionario = new HashMap();
        diccionario.put("ETICA", "Relacionado con teoria, es pensar");
        diccionario.put("Moral", "Relacionado con la practica, es actuar");
        diccionario.put("javascript", "Lenguaje de programacion de los navegadores");
        diccionario.put("serenidad", "Mantenerse en calma ante cualquier situacion");
        diccionario.put("filosofia", "Estudia el comportamiento humano");

        DataOutputStream salidaDatos = new DataOutputStream(socket.getOutputStream());
        salidaDatos.writeUTF("Solicitud aceptada");
        // recibir la clave del diccionario y obtener el valor
        DataInputStream entradaDatos = new DataInputStream(socket.getInputStream());

        // el servidor termina el proceso hasta que se ingrese salir desde el cliente
        String claveDiccionario;
        do {
            salidaDatos = new DataOutputStream(socket.getOutputStream());
            claveDiccionario = String.valueOf(entradaDatos.readUTF());
            // Comparar claves ignorando mayúsculas y minúsculas, si no coincide devuelve vacio
            String textoDiccionario="";
            for (String key : diccionario.keySet()) {
                if (key.equalsIgnoreCase(claveDiccionario)) {
                    textoDiccionario = diccionario.get(key);
                    break;
                }
            }
            if (textoDiccionario.equals("")) {
                textoDiccionario = "La clave no existe en el diccionario";
            }
            salidaDatos.writeUTF(textoDiccionario);
        } while (!claveDiccionario.equalsIgnoreCase("salir"));
        
    }

}


