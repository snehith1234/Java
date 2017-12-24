package Socket_pro_main;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
	private static int a = 250;
	private static int b = 479;
	private static int Total_num = 52;
	private static Socket socket;
	private static int Secret_key;
	private static String sendingMessage;
	static char[] Alpha = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
			'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
			'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
	private static String inputText;
	private static List<Character> char_decoded;

	// Convert the decrypted message to String
	public static String ConvString(List<Character> X) {
		String word = new String();
		for (int i = 0; i < X.size(); i++) {
			word = word + X.get(i);
		}
		word = word.toString();
		return word;
	}

	// Client Message decryptor
	public static List<Character> Message_decryption(char[] Scanned_Alpha, int k) {
		List<Character> out_text = new ArrayList<Character>();
		for (int i = 0; i < Scanned_Alpha.length; i++) {
			for (int j = 0; j < Alpha.length; j++) {
				if (Scanned_Alpha[i] == Alpha[j]) {
					int de_cryted_key = (j - k) % Total_num;
					if (de_cryted_key < 0) {
						de_cryted_key = de_cryted_key + Total_num;
						out_text.add(Alpha[de_cryted_key]);
					} else {
						out_text.add(Alpha[de_cryted_key]);
					}
				}
			}
		}
		return out_text;
	}

	// Calculate the Shift key from the Secret key Shared by Client
	public static int random_number(int k) {
		int ModulerNum = ((a * k) + b) % Total_num;
		return ModulerNum;
	}

	public static void main(String[] args) {
		try {
			int port = 9090;
			ServerSocket serverSocket = new ServerSocket(port);
			System.out.println("Server Started and listening to the port::" + port);

			// Server is running always. This is done using this while(true) loop
			while (true) {
				// Reading the message from the client
				socket = serverSocket.accept();
				DataInputStream is = new DataInputStream(socket.getInputStream());
				Secret_key = is.readInt();
				int number = random_number(Secret_key);
				try {
					DataInputStream is1 = new DataInputStream(socket.getInputStream());
					inputText = is1.readUTF();
					System.out.println("Message received from the client : " + inputText);
					String[] in_put = inputText.split(" ");
					List<String> OUT_PUT = new ArrayList<String>();
					for (int i = 0; i < in_put.length; i++) {
						char[] Scan_Alpha = in_put[i].toCharArray();
						char_decoded = Message_decryption(Scan_Alpha, number);
						String sendingMsg = ConvString(char_decoded);
						OUT_PUT.add(sendingMsg);
					}
					for (int i = 0; i < OUT_PUT.size(); i++) {
						if (i != 0) {
							sendingMessage = sendingMessage + " " + OUT_PUT.get(i);
						} else {
							sendingMessage = OUT_PUT.get(i);
						}
					}
					DataOutputStream bw = new DataOutputStream(socket.getOutputStream());
					bw.writeUTF(sendingMessage);
					bw.flush();
					System.out.println("Message sent from the server : " + sendingMessage);

				} finally {
					try {
						socket.close();
					} catch (Exception e) {
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (Exception e) {
			}
		}
	}
}