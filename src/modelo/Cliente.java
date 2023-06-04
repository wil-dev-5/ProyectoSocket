/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * El cliente usa la clase Socket
 * @author W
 */
public class Cliente {

    private Socket cliente;

    public Cliente() {
        cliente = new Socket();
    }

    public Socket getCliente() {
        return cliente;
    }

    public void setCliente(Socket cliente) {
        this.cliente = cliente;
    }

    public static void main(String[] args) throws IOException {
        Cliente cliente = new Cliente();
        DataInputStream entradaCliente;
        DataOutputStream salidaCliente;
        Scanner in = new Scanner(System.in);

        System.out.println("Cliente en proceso....");
        cliente.setCliente(new Socket("localhost", 4060));
        String palabra = "";
        
        while (!palabra.equalsIgnoreCase("salir")) {
            entradaCliente = new DataInputStream(cliente.getCliente().getInputStream()); //entrada del servidor
            System.out.println(entradaCliente.readUTF());
            salidaCliente = new DataOutputStream(cliente.getCliente().getOutputStream());
            System.out.print("Ingrese la palabra a buscar del diccionario: ");
            palabra = in.nextLine();
            salidaCliente.writeUTF(palabra); // se envia la clave ingresada en el scanner hacia el servidor                                  
        }

    }
}
