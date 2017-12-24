package Socket_prog;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
 
public class Client
{
	private static int a = 250;
	private static int b = 479;
	private static int moduler = 52;
    private static int rand_k;
	private static String sendText;
    private static Socket socket;
	private static String DES_key = "133457799BBCDFF1";
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
	private static List<Integer> en_codes;
	private static List<Character> char_arr_enc;

	/** S-box Operations */
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
		}
		else if (Count1 == 6) {
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

	/** S-block Operations */
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

	/** XOR Operations */
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

	/** Make right data array */
	public static void right_convert_to_fourty_eight(int[] task1, List<Character> hex2, List<Character> right_hex) {
		for (int j = 0; j < task1.length; j++) {
			int num1 = task1[j] - 1;
			char Select1 = hex2.get(num1);
			right_hex.add(Select1);
		}
	}

	/** Make left data array */
	public static void left_or_right_arr(List<Character> hex, int X, int Y, List<Character> hex1) {
		for (int i = X; i < Y; i++) {
			hex1.add(hex.get(i));
		}
	}

	/** Input and key submitter to permutations */
	public static void permutations_Sync(int[] task1, List<Character> hex, List<Character> hex1) {
		for (int i = 0; i < task1.length; i++) {
			for (int j = 0; j < hex.size(); j++) {
				if ((i == j)) {
					char change = hex.get((task1[j] - 1));
					hex1.add(change);
				}
			}
		}
	}

	/** Input data enchancer */
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
	public static String make_string(List<Character> X) {
		String word = new String();
		for (int i = 0; i < X.size(); i ++) {
			word = word + X.get(i);
		}
		word = word.toString();
		return word;
	}
	
	/** Encrypt the message input*/
	public static List<Character> Message_encrypt(char[] Scanned_arr) {
		List<Character> out_text = new ArrayList<Character>();
		for(int i = 0; i < Scanned_arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				if (Scanned_arr[i] == arr[j]) {
					int x = en_codes.get(j);
					char y = arr[x];
					out_text.add(y);
				}
			}
		}
		return out_text;
	}
	/**Encrypt all the letters i.e arr data*/
	public static List<Integer> encrypt(char[] args, int k) {
    	List<Integer> myList = new ArrayList<Integer>();
    	for (int i = 0; i < args.length; i++) {
    		int encrypted_key = (i + k ) % moduler;
    		myList.add(encrypted_key);
    	}
		return myList;
    }
    /**Generate Shift number*/
    public static int random_number() {
		Random rand = new Random();
		rand_k = rand .nextInt(1000000) + 1;
	
        int modular_out = ((a * rand_k)+ b) % moduler;
        while (modular_out == 0) {
        	random_number();
        }
        return modular_out;
    }
    
    
    public static void main(String args[])
    {
        try
        {
        	Server_messenger Smsg;
            String host = "localhost";
            int port = 9090;

            InetAddress address = InetAddress.getByName(host);
            socket = new Socket(address, port);

			List<String> OUT_Line = new ArrayList<String>();
			Scanner Scan_method = new Scanner(System.in);
			System.out.println("Enter Method(Please enter 1: Chiper 2: DES)::");
			int Encryption_method = Scan_method.nextInt();
			switch (Encryption_method) {
			case 1:
				int Scan_Case = 1;
				Scanner Scan1 = new Scanner(System.in);
				System.out.println("Enter Message::");
				String Scan_arr1 = Scan1.nextLine();
				String[] inp = Scan_arr1.split(" ");
				int number = random_number();
				en_codes = encrypt(arr, number);
				for (int i = 0; i < inp.length; i++) {
					char_arr_enc = Message_encrypt(inp[i].toCharArray());
					String sendMessage = make_string(char_arr_enc);
					OUT_Line.add(sendMessage);
				}
				for (int i = 0; i < OUT_Line.size(); i++) {
					if (i != 0) {
						sendText = sendText + " " + OUT_Line.get(i);
					} else {
						sendText = OUT_Line.get(i);
					}
				}
				// Send the message to the server
				ObjectOutputStream bw = new ObjectOutputStream(socket.getOutputStream());
				messanger sendMessage1 = new messanger(Scan_Case, sendText, String.valueOf(rand_k));
				bw.writeObject(sendMessage1);
				bw.flush();
				System.out.println("Message sent to the server : " + sendMessage1.texting);
				// Get the return message from the server
				ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
				Smsg = (Server_messenger) is.readObject();
				String input_text = Smsg.texting;
				System.out.println("Message received from the server : " + input_text);
				break;
			case 2:
				int Scan_Case1 = 2;
				FileReader file = new FileReader("C:\\Users\\venka\\Desktop\\input.txt");
				BufferedReader Bf = new BufferedReader(file);
				String BF1 = Bf.readLine();
				String H_Str = null;
				while (BF1 != null) {
					String[] base = BF1.split(" ");
					for (int k = 0; k < base.length; k++) {
						List<Character> hex_bit_arr = new ArrayList<Character>();
						List<Character> hex_bit_key = new ArrayList<Character>();
						List<Character> hex_bit_key1 = new ArrayList<Character>();
						List<Character> hex_bit_arr2 = new ArrayList<Character>();
						List<Character> hex_bit_arr5 = new ArrayList<Character>();
						List<Character> hex_bit_arr6 = new ArrayList<Character>();
						Bytes_input_arr(base[k], hex_bit_arr);
						Bytes_input_arr(DES_key, hex_bit_key);
						permutations_Sync(arr_task1, hex_bit_key, hex_bit_key1);/** 56-bit key */
						permutations_Sync(arr_task3, hex_bit_arr, hex_bit_arr2);/** 64-bit input Message */
						left_or_right_arr(hex_bit_arr2, 0, 32, hex_bit_arr5);/** Left 32-bits */
						left_or_right_arr(hex_bit_arr2, 32, (hex_bit_arr2.size()), hex_bit_arr6);/** Right 32-bit */
						for (int i = 0; i < Left_shifts.length; i++) {
							List<Character> hex_bit_key5 = new ArrayList<Character>();
							List<Character> right_hex_bit_arr = new ArrayList<Character>();
							right_convert_to_fourty_eight(arr_task4, hex_bit_arr6,
									right_hex_bit_arr);/** 48-bit input from right 32-bit */
							int Count = 0;
							List<Character> hex_bit_key3 = new ArrayList<Character>();
							List<Character> right_hex_bit_arr1 = new ArrayList<Character>();
							List<Character> Sbox_arr1 = new ArrayList<Character>();
							List<Character> hex_bit_key4 = new ArrayList<Character>();
							/**
							 * Everything is same, the only change is here.. Here in the client side the
							 * Shifts happen for 16 iterations from starting of the array to the end total
							 * shift i.e after all the 16 iterations it is calculated as 28
							 */
							while (Count < Left_shifts[i]) {
								char Key_Shift1 = hex_bit_key1.get(0);
								char Key_Shift2 = hex_bit_key1.get(28);
								hex_bit_key1.remove(0);
								hex_bit_key1.add(27, Key_Shift1);
								hex_bit_key1.remove(28);
								hex_bit_key1.add((hex_bit_key1.size()), Key_Shift2);
								Count = Count + 1;
							}
							permutations_Sync(arr_task2, hex_bit_key1, hex_bit_key3);/** Making Keys */
							/** K1 + E(R0) */
							XOR_operation(hex_bit_key3, right_hex_bit_arr, right_hex_bit_arr1);/** 48-bit */
							int Count1 = 0;

							while (Count1 < (right_hex_bit_arr1.size() - 5)) {

								int first_bit = Character.getNumericValue(right_hex_bit_arr1.get(Count1)) * 2;
								int last_bit = Character.getNumericValue(right_hex_bit_arr1.get(Count1 + 5)) * 1;
								int row_bit = first_bit + last_bit;
								int num_out = S_box_block_num(right_hex_bit_arr1, Count1);
								S_box_outputs(Count1, num_out, row_bit, Sbox_arr1);
								Count1 = Count1 + 6;
							}
							
							permutations_Sync(arr_task5, Sbox_arr1, hex_bit_key4);
							XOR_operation(hex_bit_arr5, hex_bit_key4, hex_bit_key5);
							hex_bit_arr5 = hex_bit_arr6;
							hex_bit_arr6 = hex_bit_key5;
						}
						List<Character> hex_TEMP = new ArrayList<Character>();
						hex_TEMP = hex_bit_arr5;
						hex_bit_arr5 = hex_bit_arr6;
						hex_bit_arr6 = hex_TEMP;
						for (int a = 0; a < hex_bit_arr6.size(); a++) {
							hex_bit_arr5.add(hex_bit_arr6.get(a));
						}
						List<Character> Out_put_bits = new ArrayList<Character>();
						permutations_Sync(arr_task6, hex_bit_arr5, Out_put_bits);
						int back_cnt = 0;
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
					BF1 = Bf.readLine();
				}
				/** Sending msg to server */
				ObjectOutputStream bw1 = new ObjectOutputStream(socket.getOutputStream());
				messanger sendMessage2 = new messanger(Scan_Case1, H_Str, DES_key);
				bw1.writeObject(sendMessage2);
				bw1.flush();
				System.out.println("Message sent to the server : " + sendMessage2.texting);
				// Get the return message from the server
				DataInputStream is1 = new DataInputStream(socket.getInputStream());
				String input_text1 = is1.readUTF();
				System.out.println("Message received from the server : " + input_text1);
				break;
			default:
				Encryption_method = 0;
				System.out.println("No case detected, Please enter right input. You are disconnected");
				break;
			}

        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        finally
        {
            //Closing the socket
            try
            {
                socket.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
