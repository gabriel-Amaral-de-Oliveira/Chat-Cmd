package br.com.gabriel.msgcliente;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JOptionPane;

public class ClienteMsg extends Thread {

	private Socket socketCliente = new Socket();
	private final String ip = "10.100.0.112";
	private final int port = 3333;

	@Override
	public void run() {
		try {
			System.out.println("conectando ao servidor...");
			Thread.sleep(2000);
			socketCliente = new Socket(ip, port);
			System.out.println("conectado ao Servidor!");
			PrintWriter out = new PrintWriter(socketCliente.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
			EscutaThread escuta = new EscutaThread(in);
			escuta.start();
			String entrada;
			while (true) {
				entrada = JOptionPane.showInputDialog("Mensagem para o servidor");
				if (entrada == null || entrada.equalsIgnoreCase("sair")) {
					System.out.println("Conexão Encerrada");
					System.exit(0);
				}
				out.println(entrada);
				System.out.println("Cliente: " + entrada);
			}
		} catch (Throwable e) {
			System.out.println("Erro ao tentar conectar no servidor: " + e.getMessage());
		}

	}

	public static void main(String[] args) {

		ClienteMsg cliente = new ClienteMsg();
		cliente.start();
	}
}
