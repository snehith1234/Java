package Socket_prog;

import java.io.Serializable;

public class Server_messenger implements Serializable {
	public int process_num;
	public String texting;
	public Server_messenger(String texting) {
		this.texting = texting;
	}
	public String toString() {
		return "Texting:"+texting+"\n";
	}
}
