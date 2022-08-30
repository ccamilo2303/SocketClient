package com.poli.main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

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
			System.out.println("2 - buscar cuenta");
		}
		
		
		
		
		//get the localhost IP address, if server is running on some other IP, you need to use that
		InetAddress host = InetAddress.getLocalHost();
		Socket socket = null;
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		for(int i=0; i<5;i++){
			//establish socket connection to server
			socket = new Socket(host.getHostName(), 9876);
			//write to socket using ObjectOutputStream
			oos = new ObjectOutputStream(socket.getOutputStream());
			System.out.println("Sending request to Socket Server");
			if(i==4)oos.writeObject("exit");
			else oos.writeObject(""+i);
			//read the server response message
			ois = new ObjectInputStream(socket.getInputStream());
			String message = (String) ois.readObject();
			System.out.println("Message: " + message);
			//close resources
			ois.close();
			oos.close();
			Thread.sleep(100);
		}
	}
}
