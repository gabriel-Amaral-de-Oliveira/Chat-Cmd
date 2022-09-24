package br.com.gabriel.msgservidor;

import java.io.BufferedReader;

public class EscutaThread extends Thread {

	BufferedReader in;

	public EscutaThread(BufferedReader in) {
		this.in = in;
	}

	@Override
	public void run() {
		String entrada;
		try {
			while ((entrada = in.readLine()) != null) {
				System.out.println("recebi: " + entrada);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
