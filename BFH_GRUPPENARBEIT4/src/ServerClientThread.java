import java.net.*;
import java.io.*;

class ServerClientThread extends Thread {
	Socket serverClient;
	int clientNo;

	ServerClientThread(Socket inSocket, int counter) {
		serverClient = inSocket;
		clientNo = counter;
	}

	public void run() {
		try {
			DataInputStream inStream = new DataInputStream(serverClient.getInputStream());
			DataOutputStream outStream = new DataOutputStream(serverClient.getOutputStream());
			String clientMessage = "";
			while (!clientMessage.equals("bye")) {
				clientMessage = inStream.readUTF();
				System.out.println("From Client-" + clientNo + ": Message is :" + clientMessage);
				doActionBasedOnMessage(clientMessage, outStream);
				outStream.flush();
			}
			inStream.close();
			outStream.close();
			serverClient.close();
		} catch (Exception ex) {
			System.out.println(ex);
		} finally {
			System.out.println("Client -" + clientNo + " exit!! ");
		}
	}

	private void doActionBasedOnMessage(String clientMessage, DataOutputStream outStream) throws IOException {
		if (clientMessage.matches(".*\\d.*")) {
			doSqaure(clientMessage, outStream);
			return;
		}

		switch (clientMessage) {
		case "ping":
			doPingPong(outStream);
			break;
		}
		return;
	}

	private void doSqaure(String clientMessage, DataOutputStream outStream) throws IOException {
		int squre = Integer.parseInt(clientMessage) * Integer.parseInt(clientMessage);
		String serverMessage = "From Server to Client-" + clientNo + " Square of Number in" + clientMessage + " is "
				+ squre;
		outStream.writeUTF(serverMessage);

	}

	private void doPingPong(DataOutputStream outStream) throws IOException {
		outStream.writeUTF("Pong");

	}
}