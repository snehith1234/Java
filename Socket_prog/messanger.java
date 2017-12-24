package Socket_prog;

import java.io.Serializable;

public class messanger implements Serializable{
	public int process_num;
		public String texting;
	public String key;

	/**
	 * public messanger(String texting, int key) { this.texting = texting; this.key
	 * = key; }
	 */
	public messanger(int process_num, String texting, String key) {
		this.process_num = process_num;
		this.texting = texting;
		this.key = key;
		}
		public String toString() {
		return "process_num:" + "process_num" + "Texting:" + "texting" + "\n" + "Key:" + key;
		}
}
