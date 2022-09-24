package br.com.gabriel.msgserver.listener;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Vector;

public class ListenerMensagem extends Thread {

	BufferedReader in;
	Vector<PrintStream> listMessage;
	Socket socket;

	public ListenerMensagem(BufferedReader in, Socket socket, Vector<PrintStream> listMessage) {
		this.in = in;
		this.socket = socket;
		this.listMessage = listMessage;
	}

	@Override
	public void run() {
		String entrada;
		try {
			while ((entrada = in.readLine()) != null) {
				System.out.println(entrada);
				distribuiMessage(entrada);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void distribuiMessage(String msg) {
		for (PrintStream cliente : listMessage) {
			cliente.println(msg);
		}
	}
}
