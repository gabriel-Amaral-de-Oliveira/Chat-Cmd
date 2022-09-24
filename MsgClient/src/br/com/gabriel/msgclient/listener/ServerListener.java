package br.com.gabriel.msgclient.listener;

import java.io.InputStream;
import java.util.Scanner;

public class ServerListener extends Thread {

	InputStream in;

	public ServerListener(InputStream in) {
		super();
		this.in = in;
	}

	@Override
	public void run() {
		try (Scanner msgOutros = new Scanner(in)) {
			while (msgOutros.hasNextLine()) {
				System.out.println(msgOutros.nextLine());
			}
		}
	}
}
