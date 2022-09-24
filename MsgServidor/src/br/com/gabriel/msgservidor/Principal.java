package br.com.gabriel.msgservidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

public class Principal extends Thread {

	protected ServerSocket socketServidor;
	protected Socket socketCliente;
	protected static BufferedReader in;
	protected static PrintWriter out;
	protected final static int porta = 3333;

	@Override
	public void run() {
		try {
			socketServidor = new ServerSocket(porta);
			System.out.println("Servidor iniciado na porta: " + porta);
			socketCliente = socketServidor.accept();
			System.out.println("Usuario " + socketCliente.getLocalAddress() + " Conectado!");
			out = new PrintWriter(socketCliente.getOutputStream());
			in = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
			EscutaThread escuta = new EscutaThread(in);
			escuta.start();
			String entrada;
			while (true) {
				entrada = JOptionPane.showInputDialog("Mensagem para o Cliente");
				if(entrada == null) {
					System.out.println("Conexão Encerrada");
					System.exit(0);
				}
				System.out.println("servidor: " + entrada);
				out.println(entrada);
			}
		} catch (IOException e) {
			System.out.println("erro: " + e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		try {
			Principal server = new Principal();
			server.start();
		} catch (Exception e) {
			System.out.println("Erro no Main: " + e.getMessage());
		}
	}
	
}
