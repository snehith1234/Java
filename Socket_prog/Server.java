package Socket_prog;

import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

 
public class Server
{
	private static int a = 250;
	private static int b = 479;
	private static int moduler = 52;
    private static Socket socket;
    private static int pass_key;
	private static String pass1_key;
	private static String sendText;
    static char[] arr = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
	static int[] arr_task1 = { 57, 49, 41, 33, 25, 17, 9, 1, 58, 50, 42, 34, 26, 18, 10, 2, 39, 51, 43, 35, 27, 19, 11,
			3, 60, 52, 44, 36, 63, 55, 47, 39, 31, 23, 15, 7, 62, 54, 46, 38, 30, 22, 14, 6, 61, 53, 45, 37, 29, 21, 13,
			5, 28, 20, 12, 4 };
	static int[] Left_shifts = { 1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1 };
	static int[] arr_task2 = { 14, 17, 11, 24, 1, 5, 3, 28, 15, 6, 21, 10, 23, 19, 12, 4, 26, 8, 16, 7, 27, 20, 13, 2,
			41, 52, 31, 37, 47, 55, 30, 40, 51, 45, 33, 48, 44, 49, 39, 56, 34, 53, 46, 42, 50, 36, 29, 32 };
	static int[] arr_task3 = { 58, 50, 42, 34, 26, 18, 10, 2, 60, 52, 44, 36, 28, 20, 12, 4, 62, 54, 46, 38, 30, 22, 14,
			6, 64, 56, 48, 40, 32, 24, 16, 8, 57, 49, 41, 33, 25, 17, 9, 1, 59, 51, 43, 35, 27, 19, 11, 3, 61, 53, 45,
			37, 29, 21, 13, 5, 63, 55, 47, 39, 31, 23, 15, 7 };
	static int[] arr_task4 = { 32, 1, 2, 3, 4, 5, 4, 5, 6, 7, 8, 9, 8, 9, 10, 11, 12, 13, 12, 13, 14, 15, 16, 17, 16,
			17, 18, 19, 20, 21, 20, 21, 22, 23, 24, 25, 24, 25, 26, 27, 28, 29, 28, 29, 30, 31, 32, 1 };
	static int[] arr_task5 = { 16, 7, 20, 21, 29, 12, 28, 17, 1, 15, 23, 26, 5, 18, 31, 10, 2, 8, 24, 14, 32, 27, 3, 9,
			19, 13, 30, 6, 22, 11, 4, 25 };
	static int[] arr_task6 = { 40, 8, 48, 16, 56, 24, 64, 32, 39, 7, 47, 15, 55, 23, 63, 31, 38, 6, 46, 14, 54, 22, 62,
			30, 37, 5, 45, 13, 53, 21, 61, 29, 36, 4, 44, 12, 52, 20, 60, 28, 35, 3, 43, 11, 51, 19, 59, 27, 34, 2, 42,
			10, 50, 18, 58, 26, 33, 1, 41, 9, 49, 17, 57, 25 };
	static int[] S_box1 = { 14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7, 0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12,
			11, 9, 5, 3, 8, 4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0, 15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3,
			14, 10, 0, 6, 13 };
	static int[] S_box2 = { 15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10, 3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1,
			10, 6, 9, 11, 5, 0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15, 13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7,
			12, 0, 5, 14, 9 };
	static int[] S_box3 = { 10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8, 13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14,
			12, 11, 15, 1, 13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7, 1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3,
			11, 5, 2, 12 };
	static int[] S_box4 = { 7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15, 13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2,
			12, 1, 10, 14, 9, 10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4, 3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5,
			11, 12, 7, 2, 14 };
	static int[] S_box5 = { 2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9, 14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15,
			10, 3, 9, 8, 6, 4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14, 11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0,
			9, 10, 4, 5, 3 };
	static int[] S_box6 = { 12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11, 10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13,
			14, 0, 11, 3, 8, 9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6, 4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1,
			7, 6, 0, 8, 13 };
	static int[] S_box7 = { 4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1, 13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5,
			12, 2, 15, 8, 6, 1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2, 6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0,
			15, 14, 2, 3, 12 };
	static int[] S_box8 = { 13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 2, 7, 1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6,
			11, 0, 14, 9, 2, 7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8, 2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9,
			0, 3, 5, 6, 11 };
    private static String input_text;
    private static List<Character> char_arr_dec;

	// S-box operation
	public static void S_box_outputs(int Count1, int num_out, int row_bit, List<Character> Sbox_arr1) {
		if (Count1 == 0) {
			num_out = (num_out + (row_bit * 16));
			int out_number = S_box1[num_out];
			String S_box_out = Integer.toBinaryString(out_number);
			char[] S_box_Char_out = S_box_out.toCharArray();
			int cnt = (4 - S_box_Char_out.length);
			if (S_box_Char_out.length < 4) {
				while (cnt > 0) {
					Sbox_arr1.add('0');
					cnt = cnt - 1;
				}
				for (int y = 0; y < S_box_Char_out.length; y++) {
					Sbox_arr1.add(S_box_Char_out[y]);
				}
			} else {
				for (int y = 0; y < S_box_Char_out.length; y++) {
					Sbox_arr1.add(S_box_Char_out[y]);
				}
			}
		} else if (Count1 == 6) {
			num_out = (num_out + (row_bit * 16));
			int out_number = S_box2[num_out];
			String S_box_out = Integer.toBinaryString(out_number);
			char[] S_box_Char_out = S_box_out.toCharArray();
			int cnt = (4 - S_box_Char_out.length);
			if (S_box_Char_out.length < 4) {
				while (cnt > 0) {
					Sbox_arr1.add('0');
					cnt = cnt - 1;
				}
				for (int y = 0; y < S_box_Char_out.length; y++) {
					Sbox_arr1.add(S_box_Char_out[y]);
				}
			} else {
				for (int y = 0; y < S_box_Char_out.length; y++) {
					Sbox_arr1.add(S_box_Char_out[y]);
				}
			}
		}
		if (Count1 == 12) {
			num_out = (num_out + (row_bit * 16));
			int out_number = S_box3[num_out];
			String S_box_out = Integer.toBinaryString(out_number);
			char[] S_box_Char_out = S_box_out.toCharArray();
			int cnt = (4 - S_box_Char_out.length);
			if (S_box_Char_out.length < 4) {
				while (cnt > 0) {
					Sbox_arr1.add('0');
					cnt = cnt - 1;
				}
				for (int y = 0; y < S_box_Char_out.length; y++) {
					Sbox_arr1.add(S_box_Char_out[y]);
				}
			} else {
				for (int y = 0; y < S_box_Char_out.length; y++) {
					Sbox_arr1.add(S_box_Char_out[y]);
				}
			}
		}
		if (Count1 == 18) {
			num_out = (num_out + (row_bit * 16));
			int out_number = S_box4[num_out];
			String S_box_out = Integer.toBinaryString(out_number);
			char[] S_box_Char_out = S_box_out.toCharArray();
			int cnt = (4 - S_box_Char_out.length);
			if (S_box_Char_out.length < 4) {
				while (cnt > 0) {
					Sbox_arr1.add('0');
					cnt = cnt - 1;
				}
				for (int y = 0; y < S_box_Char_out.length; y++) {
					Sbox_arr1.add(S_box_Char_out[y]);
				}
			} else {
				for (int y = 0; y < S_box_Char_out.length; y++) {
					Sbox_arr1.add(S_box_Char_out[y]);
				}
			}
		}
		if (Count1 == 24) {
			num_out = (num_out + (row_bit * 16));
			int out_number = S_box5[num_out];
			String S_box_out = Integer.toBinaryString(out_number);
			char[] S_box_Char_out = S_box_out.toCharArray();
			int cnt = (4 - S_box_Char_out.length);
			if (S_box_Char_out.length < 4) {
				while (cnt > 0) {
					Sbox_arr1.add('0');
					cnt = cnt - 1;
				}
				for (int y = 0; y < S_box_Char_out.length; y++) {
					Sbox_arr1.add(S_box_Char_out[y]);
				}
			} else {
				for (int y = 0; y < S_box_Char_out.length; y++) {
					Sbox_arr1.add(S_box_Char_out[y]);
				}
			}
		}
		if (Count1 == 30) {
			num_out = (num_out + (row_bit * 16));
			int out_number = S_box6[num_out];
			String S_box_out = Integer.toBinaryString(out_number);
			char[] S_box_Char_out = S_box_out.toCharArray();
			int cnt = (4 - S_box_Char_out.length);
			if (S_box_Char_out.length < 4) {
				while (cnt > 0) {
					Sbox_arr1.add('0');
					cnt = cnt - 1;
				}
				for (int y = 0; y < S_box_Char_out.length; y++) {
					Sbox_arr1.add(S_box_Char_out[y]);
				}
			} else {
				for (int y = 0; y < S_box_Char_out.length; y++) {
					Sbox_arr1.add(S_box_Char_out[y]);
				}
			}
		}
		if (Count1 == 36) {
			num_out = (num_out + (row_bit * 16));
			int out_number = S_box7[num_out];
			String S_box_out = Integer.toBinaryString(out_number);
			char[] S_box_Char_out = S_box_out.toCharArray();
			int cnt = (4 - S_box_Char_out.length);
			if (S_box_Char_out.length < 4) {
				while (cnt > 0) {
					Sbox_arr1.add('0');
					cnt = cnt - 1;
				}
				for (int y = 0; y < S_box_Char_out.length; y++) {
					Sbox_arr1.add(S_box_Char_out[y]);
				}
			} else {
				for (int y = 0; y < S_box_Char_out.length; y++) {
					Sbox_arr1.add(S_box_Char_out[y]);
				}
			}
		}
		if (Count1 == 42) {
			num_out = (num_out + (row_bit * 16));
			int out_number = S_box8[num_out];
			String S_box_out = Integer.toBinaryString(out_number);
			char[] S_box_Char_out = S_box_out.toCharArray();
			int cnt = (4 - S_box_Char_out.length);
			if (S_box_Char_out.length < 4) {
				while (cnt > 0) {
					Sbox_arr1.add('0');
					cnt = cnt - 1;
				}
				for (int y = 0; y < S_box_Char_out.length; y++) {
					Sbox_arr1.add(S_box_Char_out[y]);
				}
			} else {
				for (int y = 0; y < S_box_Char_out.length; y++) {
					Sbox_arr1.add(S_box_Char_out[y]);
				}
			}
		}
	}

	// S-box rows and colomns selector
	public static int S_box_block_num(List<Character> right_hex_bit_arr1, int Count1) {
		List<Character> temp = new ArrayList<Character>();
		temp.add(right_hex_bit_arr1.get(Count1 + 1));
		temp.add(right_hex_bit_arr1.get(Count1 + 2));
		temp.add(right_hex_bit_arr1.get(Count1 + 3));
		temp.add(right_hex_bit_arr1.get(Count1 + 4));
		int num_out = 0;
		for (int x = 0; x < temp.size(); x++) {
			if (x == 0) {
				int num_S = Character.getNumericValue(temp.get(x)) * 8;
				num_out = num_out + num_S;
			}
			if (x == 1) {
				int num_S = Character.getNumericValue(temp.get(x)) * 4;
				num_out = num_out + num_S;
			}
			if (x == 2) {
				int num_S = Character.getNumericValue(temp.get(x)) * 2;
				num_out = num_out + num_S;
			}
			if (x == 3) {
				int num_S = Character.getNumericValue(temp.get(x)) * 1;
				num_out = num_out + num_S;
			}
		}
		return num_out;
	}

	// XOR operation
	public static void XOR_operation(List<Character> hexa, List<Character> right_hexa, List<Character> right_hexa1) {
		for (int j = 0; j < hexa.size(); j++) {
			for (int l = 0; l < right_hexa.size(); l++) {
				if (j == l) {
					if (hexa.get(j) == '0' && right_hexa.get(l) == '0') {
						char intit = '0';
						right_hexa1.add(intit);
					}
					if (hexa.get(j) == '1' && right_hexa.get(l) == '0') {
						char intit = '1';
						right_hexa1.add(intit);
					}
					if (hexa.get(j) == '0' && right_hexa.get(l) == '1') {
						char intit = '1';
						right_hexa1.add(intit);
					}
					if (hexa.get(j) == '1' && right_hexa.get(l) == '1') {
						char intit = '0';
						right_hexa1.add(intit);
					}
				}
			}
		}
	}

	// Extention to 48 bit function
	public static void right_convert_to_fourty_eight(int[] task1, List<Character> hex2, List<Character> right_hex) {
		for (int j = 0; j < task1.length; j++) {
			int num1 = task1[j] - 1;
			char Select1 = hex2.get(num1);
			right_hex.add(Select1);
		}
	}

	// Split the array to half used for sl=plitting right an left array of 32-bits
	// from 64-bit array
	public static void left_or_right_arr(List<Character> hex, int X, int Y, List<Character> hex1) {
		for (int i = X; i < Y; i++) {
			hex1.add(hex.get(i));
		}
	}

	// common permutation function
	public static void permutation_Sync(int[] task1, List<Character> hex, List<Character> hex1) {
		for (int i = 0; i < task1.length; i++) {
			for (int j = 0; j < hex.size(); j++) {
				if ((i == j)) {
					char change = hex.get((task1[j] - 1));
					hex1.add(change);
				}
			}
		}
	}

	// Convert numbers to bytes
	public static void Bytes_input_arr(String c, List<Character> Z) {
		try {
			char[] Scan2 = c.toCharArray();
			for (int j = 0; j < Scan2.length; j++) {
				String Scan3 = Character.toString(Scan2[j]);
				int hex = Integer.parseInt(Scan3, 16);
				int dec1 = Integer.parseInt(Integer.toString(hex));
				String dec2 = Integer.toBinaryString(dec1);
				int Counter = dec2.length();
				while (Counter < 4) {
					char X = '0';
					Z.add(X);
					Counter = Counter + 1;
				}
				for (int l = 0; l < dec2.length(); l++) {
					Z.add(dec2.charAt(l));
				}
			}

		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}
    //Convert the decrypted message to String 
    public static String make_string(List<Character> X) {
		String word = new String();
		for (int i = 0; i < X.size(); i ++) {
			word = word + X.get(i);
		}
		word = word.toString();
		return word;
	}
    //Client Message decryptor
    public static List<Character> Message_decrypt(char[] Scanned_arr,int k) {
		List<Character> out_text = new ArrayList<Character>();
		for(int i = 0; i < Scanned_arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				if (Scanned_arr[i] == arr[j]) {
					int de_cryted_key = (j - k) % moduler;// Formula of decryption on chiper
					/**
					 * j = the index place of the character in in arr array, k is the key generated
					 * from random function below using the shared key from client moduler is the
					 * given constant i.e 52
					 */
					if (de_cryted_key < 0) {
		    			de_cryted_key = de_cryted_key + moduler;
		    			out_text.add(arr[de_cryted_key]);
		    		}
		    		else {
		    			out_text.add(arr[de_cryted_key]);
		    		}
				}
			}
		}
		return out_text;
	}
    //Calculate the Shift key from the Secred key Shared by Client
    public static int random_number(int k) {
		/** number calculated from shared key k */
		int modular_out = ((a * k)+ b) % moduler;
        return modular_out;
    }
    
	/***************************************************************************************
	 * Program flow starts here
	 *****************************************************************************************************/
    public static void main(String[] args)
    {
        try
        {
        	messanger Msg;
			int port = 9090;// Assigning port
			ServerSocket serverSocket = new ServerSocket(port);// Socket initiated
			System.out.println("Server Started and listening on port-->" + port);
 
            //Server is running always. This is done using this while(true) loop
            while(true)
            {
                socket = serverSocket.accept();
				ObjectInputStream is = new ObjectInputStream(socket.getInputStream());// Reading the message from the
																						// client
				Msg = (messanger) is.readObject();// Message is in object format
				int Prog_case = Msg.process_num;// Reading which case to execute
				// Ciper text executed if Prog_case == 1
				if (Prog_case == 1) {
					pass_key = Integer.parseInt(Msg.key);// Converting the key (shared in string format) to integer
					input_text = Msg.texting;// Encrypted message shared by client to be decrypted by server
					System.out.println("Message received from client is " + Msg.texting);
					int number = random_number(pass_key);// Generate a number from shared key
					String[] inp = input_text.split(" ");// Enhancing the input meassage to the convertable format
					List<String> OUT_Line = new ArrayList<String>();
					for (int i = 0; i < inp.length; i++) {
						char[] Scan_arr = inp[i].toCharArray();
						char_arr_dec = Message_decrypt(Scan_arr, number);// Decrypting the message
						String sendMessage = make_string(char_arr_dec);// The char decrypted are converted to words
						OUT_Line.add(sendMessage);// the words are added to an array
					}
					// Making words to a single sentence
					for (int i = 0; i < OUT_Line.size(); i++) {
						if (i != 0) {
							sendText = sendText + " " + OUT_Line.get(i);
						} else {
							sendText = OUT_Line.get(i);
						}
					}
					try {
						// Send Message to Server
						ObjectOutputStream bw = new ObjectOutputStream(socket.getOutputStream());
						Server_messenger sendMessage1 = new Server_messenger(sendText);
						bw.writeObject(sendMessage1);
						bw.flush();
						bw.close();
						System.out.println("Message sent from the server : " + sendMessage1.texting);
					} finally {
						try {
							socket.close();
						} catch (Exception e) {
						}
					}
                }
				// DES ALGORITH EXECUTED IF SELECTED Prog_case == 2
				else if (Prog_case == 2) {
					pass1_key = Msg.key;// Des key sahred by client
					input_text = Msg.texting;// Encrypted message shared by Client to be decrypted by server
					String H_Str = null;// initiated for the convinence of final output
					System.out.println("Message received from client is " + Msg.texting);
					while (input_text != null) {
						String[] base = input_text.split(" ");// Enhanced the Encrypted message as per the program to
																// decrypt
						for (int k = 0; k < base.length; k++) {
							List<Character> hex_bit_arr = new ArrayList<Character>();
							List<Character> hex_bit_key = new ArrayList<Character>();
							List<Character> hex_bit_key1 = new ArrayList<Character>();
							List<Character> hex_bit_arr2 = new ArrayList<Character>();
							List<Character> hex_bit_arr5 = new ArrayList<Character>();
							List<Character> hex_bit_arr6 = new ArrayList<Character>();
							Bytes_input_arr(base[k], hex_bit_arr);// Each 64bit Encrypted message is converted to bytes
																	// and kept into hex_bit_arr array
							Bytes_input_arr(pass1_key, hex_bit_key);// 64bit key is converted to bytes and kept into
																	// hex_bit_key array
							/** Converting the 64-bit key to 56-bit key, output in hex_bit_key1 */
							permutation_Sync(arr_task1, hex_bit_key, hex_bit_key1);
							/**
							 * Converting the 64-bit encrypted message is permutated with arr_task3, output
							 * is a 64-bit in hex_bit key2
							 */
							permutation_Sync(arr_task3, hex_bit_arr, hex_bit_arr2);
							left_or_right_arr(hex_bit_arr2, 0, 32,
									hex_bit_arr5);/** A 32-bit Left part of the hex_bit_arr2(input message) */
							left_or_right_arr(hex_bit_arr2, 32, (hex_bit_arr2.size()),
									hex_bit_arr6);/** A 32-bit Right part of the hex_bit_arr2(input message) */

							for (int i = 0; i < Left_shifts.length; i++) {
								List<Character> hex_bit_key5 = new ArrayList<Character>();
								List<Character> right_hex_bit_arr = new ArrayList<Character>();
								/**
								 * Right 32-bit of the input message is converted to 48 bit by permutation array
								 * of arr_task4, output at right_hex_bit_arr
								 */
								right_convert_to_fourty_eight(arr_task4, hex_bit_arr6, right_hex_bit_arr);
								List<Character> hex_bit_key3 = new ArrayList<Character>();
								List<Character> right_hex_bit_arr1 = new ArrayList<Character>();
								List<Character> Sbox_arr1 = new ArrayList<Character>();
								List<Character> hex_bit_key4 = new ArrayList<Character>();
								List<Character> hex_bit_dumm_key1 = new ArrayList<Character>();
								// Making a duplicate array of 56bit key for the shift changes
								for (int j = 0; j < hex_bit_key1.size(); j++) {
									hex_bit_dumm_key1.add(j, hex_bit_key1.get(j));
								}
								// left shifts i.e. 16 iteration (whole of 28 shifts) for making 16 key
								// combinations
								/**
								 * Same 16 shifts are done here but in reverse order the fisrt key generated
								 * here is the last key of generated on client side and last key is the first
								 * key on client side All that change is below
								 */
								int Len = Left_shifts.length - i;
								for (int j = 0; j < Len; j++) {
									int Count = 0;
									while (Count < Left_shifts[j]) {
										char Key_Shift1 = hex_bit_dumm_key1.get(0);
										char Key_Shift2 = hex_bit_dumm_key1.get(28);
										hex_bit_dumm_key1.remove(0);
										hex_bit_dumm_key1.add(27, Key_Shift1);
										hex_bit_dumm_key1.remove(28);
										hex_bit_dumm_key1.add((hex_bit_key1.size() - 1), Key_Shift2);
										Count = Count + 1;
										}
									}
								// Each Key combination is subjected to extention to 48 bit key
								permutation_Sync(arr_task2, hex_bit_dumm_key1, hex_bit_key3);
								/** K1 + E(R0) */
								// The 48 bit key and the input right part is XOR
								XOR_operation(hex_bit_key3, right_hex_bit_arr, right_hex_bit_arr1);/** 48-bit */
								int Count1 = 0;
								// The reult is subjected to S-box combination
								while (Count1 < (right_hex_bit_arr1.size() - 5)) {
									int first_bit = Character.getNumericValue(right_hex_bit_arr1.get(Count1)) * 2;
									int last_bit = Character.getNumericValue(right_hex_bit_arr1.get(Count1 + 5)) * 1;
									int row_bit = first_bit + last_bit;
									int num_out = S_box_block_num(right_hex_bit_arr1, Count1);
									S_box_outputs(Count1, num_out, row_bit, Sbox_arr1);
									Count1 = Count1 + 6;
								}
								// Result is made to 32 bit
								permutation_Sync(arr_task5, Sbox_arr1, hex_bit_key4);
								// Now XOR with the key serise and the left side of the input
								XOR_operation(hex_bit_arr5, hex_bit_key4, hex_bit_key5);
								hex_bit_arr5 = hex_bit_arr6;
								hex_bit_arr6 = hex_bit_key5;

							}
							// Assigning the outputs to single array
							List<Character> hex_TEMP = new ArrayList<Character>();
							hex_TEMP = hex_bit_arr5;
							hex_bit_arr5 = hex_bit_arr6;
							hex_bit_arr6 = hex_TEMP;
							for (int a = 0; a < hex_bit_arr6.size(); a++) {
								hex_bit_arr5.add(hex_bit_arr6.get(a));
							}
							List<Character> Out_put_bits = new ArrayList<Character>();
							// last permute to get the output decryption
							permutation_Sync(arr_task6, hex_bit_arr5, Out_put_bits);
							int back_cnt = 0;
							// reading bytes to hexa number
							while (back_cnt < Out_put_bits.size()) {
								int a = Out_put_bits.get(back_cnt) * 8;
								int b = Out_put_bits.get(back_cnt + 1) * 4;
								int c = Out_put_bits.get(back_cnt + 2) * 2;
								int d = Out_put_bits.get(back_cnt + 3) * 1;
								int e = a + b + c + d;
								String Str = Integer.toHexString(e).toUpperCase();
								if (back_cnt == 0) {
									if (H_Str == null) {
										H_Str = "" + Str.substring(2);
									} else {
										H_Str = H_Str + " " + Str.substring(2);
									}
								} else {
									H_Str = H_Str + Str.substring(2);
								}
								back_cnt = back_cnt + 4;
							}
						}
						input_text = null;
					}

					try {
						// Send Message to Server
						DataOutputStream bw1 = new DataOutputStream(socket.getOutputStream());
						bw1.writeUTF(H_Str);
						bw1.flush();
						bw1.close();
						System.out.println("Message sent from the server : " + H_Str);
					} finally {
						try {
							socket.close();
						} catch (Exception e) {
						}
					}
				}
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                socket.close();
            }
            catch(Exception e){}
        }
    }
}