package Socket_pro_main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Client {
	private static int a = 250;
	private static int b = 479;
	private static int Total_Num = 52;
	private static int randon_k_num;
	private static String inputText;
	private static Socket socket;
	private static String sendingMsg;
	static char[] Alpha = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
			'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
			'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
	private static List<Integer> encryptioned_code;
	private static List<Character> char_encoded;

	/** encryption to word conversion */
	public static String ConvertString(List<Character> X) {
		String word = new String();
		for (int i = 0; i < X.size(); i++) {
			word = word + X.get(i);
		}
		word = word.toString();
		return word;
	}

	/** input encryption */
	public static List<Character> MessageEncryption(char[] Scanned_Alpha) {
		List<Character> out_text = new ArrayList<Character>();
		for (int i = 0; i < Scanned_Alpha.length; i++) {
			for (int j = 0; j < Alpha.length; j++) {
				if (Scanned_Alpha[i] == Alpha[j]) {
					int x = encryptioned_code.get(j);
					char y = Alpha[x];
					out_text.add(y);
				}
			}
		}
		return out_text;
	}

	/** encrypting Alpha data */
	public static List<Integer> encryption(char[] args, int k) {
		List<Integer> myList = new ArrayList<Integer>();
		for (int i = 0; i < args.length; i++) {
			int encryptioned_key = (i + k) % Total_Num;
			myList.add(encryptioned_key);
		}
		return myList;
	}

	/** Generate Shift number */
	public static int randomNumber() {
		Random rand = new Random();
		randon_k_num = rand.nextInt(1000000) + 1;

		int moduler_Num = ((a * randon_k_num) + b) % Total_Num;
		while (moduler_Num == 0) {
			randomNumber();
		}
		return moduler_Num;
	}

	public static void main(String args[]) {
		try {
			String host = "localhost";
			int port = 9090;
			InetAddress address = InetAddress.getByName(host);
			socket = new Socket(address, port);
			Scanner Scan = new Scanner(System.in);
			System.out.println("Enter your word::");

			String Scan_Alpha_String = Scan.nextLine();
			String[] in_put = Scan_Alpha_String.split(" ");
			List<String> OUT_PUT = new ArrayList<String>();
			int number = randomNumber();
			try {
				DataOutputStream bw = new DataOutputStream(socket.getOutputStream());
				bw.writeInt(randon_k_num);
				bw.flush();
			} catch (Exception e) {
				e.printStackTrace();

			}
			encryptioned_code = encryption(Alpha, number);
			for (int i = 0; i < in_put.length; i++) {
				char_encoded = MessageEncryption(in_put[i].toCharArray());
				String sendMsg = ConvertString(char_encoded);
				OUT_PUT.add(sendMsg);
			}
			for (int i = 0; i < OUT_PUT.size(); i++) {
				if (i != 0) {
					sendingMsg = sendingMsg + " " + OUT_PUT.get(i);
				} else {
					sendingMsg = OUT_PUT.get(i);
				}
			}
			DataOutputStream bw1 = new DataOutputStream(socket.getOutputStream());
			bw1.writeUTF(sendingMsg);
			bw1.flush();
			System.out.println("Message to the server : " + sendingMsg);
			int count = 0;
			DataInputStream is2 = new DataInputStream(socket.getInputStream());
			while (is2.available() == 0 && count < 3) {
				count = count + 1;
			}
			inputText = is2.readUTF();
			System.out.println("Message received from the server : " + inputText);
		} catch (EOFException e1) {
			// TODO Auto-generated catch block
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		finally {
			// Closing the socket
			try {
				socket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
