package br.com.gabriel.msgserver.engine;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import br.com.gabriel.msgserver.listener.ListenerMensagem;

public class Server {

	@SuppressWarnings("resource")
	public void start(int porta) {

		new Thread(() -> {
			try {
				ServerSocket socketServidor = new ServerSocket(porta);
				Vector<Socket> listClient = new Vector<>();
				Vector<PrintStream> listMessage = new Vector<>();

				System.out.println("Servidor iniciado na porta: " + porta);

				while (true) {
					Socket cliente = socketServidor.accept();
					listClient.add(cliente);
					listMessage.add(new PrintStream(cliente.getOutputStream()));

					BufferedReader in = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
					ListenerMensagem listener = new ListenerMensagem(in, cliente, listMessage);
					listener.start();

					System.out.println(listClient.size() + " usuario " + cliente.getRemoteSocketAddress()
							+ " conectado na porta: " + porta);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
	}
}
