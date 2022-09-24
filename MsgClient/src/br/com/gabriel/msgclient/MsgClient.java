package br.com.gabriel.msgclient;

import javax.swing.JOptionPane;

import br.com.gabriel.msgclient.engine.Client;

public class MsgClient {

	public static void main(String[] args) throws Exception {

		String nome = JOptionPane.showInputDialog("DIGITE O SEU NOME");
		String ip = JOptionPane.showInputDialog("DIGITE O IP");
		String porta = JOptionPane.showInputDialog("DIGITE A PORTA");
		
		Client cliente = new Client();
		cliente.connect(ip, Integer.parseInt(porta), nome);
	}
}