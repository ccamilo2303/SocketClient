package com.poli.main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * @author Camilo Beltrán
 * @since  28/08/2022
 */
public class ClientMain {
	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException{

		boolean exit = false;

		while(exit == false) {
			System.out.println("Seleccione una opción:");
			System.out.println("1 - Cargar cuenta y valor");
			System.out.println("2 - Buscar cuenta");
			Scanner in = new Scanner(System.in);
			int op = in.nextInt();
			String valor = "";
			switch(op) {
			case 1:
				System.out.println("Por favor ingrese una cuenta seguido de 'coma' y el valor, ej: 10011,2000");
				valor = in.next();
				valor = valor.concat("create");
				sendMessage(valor);
				break;
			case 2:
				System.out.println("Por favor ingrese un número de cuenta para buscar");
				valor = in.next();
				valor = valor.concat("search");
				sendMessage(valor);
				break;

			}

		}




	}

	private static void sendMessage(String message) throws IOException, ClassNotFoundException {



		//get the localhost IP address, if server is running on some other IP, you need to use that
		InetAddress host = InetAddress.getLocalHost();
		Socket socket = null;
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;

		//establish socket connection to server
		socket = new Socket(host.getHostName(), 9876);
		//write to socket using ObjectOutputStream
		oos = new ObjectOutputStream(socket.getOutputStream());
		oos.writeObject(message);
		//read the server response message
		ois = new ObjectInputStream(socket.getInputStream());
		String res = (String) ois.readObject();
		System.out.println("Mensaje respuesta server: " + res);
		//close resources
		ois.close();
		oos.close();



	}

}
