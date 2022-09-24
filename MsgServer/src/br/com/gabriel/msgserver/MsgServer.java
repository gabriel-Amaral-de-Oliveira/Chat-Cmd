package br.com.gabriel.msgserver;

import br.com.gabriel.msgserver.engine.Server;

public class MsgServer {

	public static void main(String[] args) {

		Server srv = new Server();
		srv.start(1111);
		srv.start(2222);
		srv.start(3333);
		srv.start(4444);
		srv.start(5555);
		srv.start(6666);
	}
}
