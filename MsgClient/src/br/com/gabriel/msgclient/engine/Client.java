package br.com.gabriel.msgclient.engine;

import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JOptionPane;

import br.com.gabriel.msgclient.listener.ServerListener;

public class Client {

	private Socket socketClient;

	public void connect(String ip, int porta, String nome) throws Exception {

		socketClient = new Socket(ip, porta);
		ServerListener listener = new ServerListener(socketClient.getInputStream());
		listener.start();

		System.out.println("Conectado ao Servidor");

		PrintWriter out = new PrintWriter(socketClient.getOutputStream(), true);

		String entrada = "";

		while (entrada != null) {

			entrada = JOptionPane.showInputDialog("Mensagem Para o Servidor!");
			if (socketClient.isClosed()) {
				System.out.println("Servidor Fechado");
				socketClient.close();
				System.exit(0);
			} else if (entrada == null || entrada.equalsIgnoreCase("sair")) {
				out.println(nome + " se desconectou!");
				socketClient.close();
				System.exit(0);
			}
			out.println(nome + ": " + entrada);
		}
	}

}
