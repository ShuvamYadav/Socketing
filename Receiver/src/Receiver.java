import java.awt.BorderLayout;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

//import jdk.internal.org.jline.utils.InputStreamReader;

public class Receiver {
	static ServerSocket ss;
	static Socket s;
	static java.io.InputStreamReader is;
	static BufferedReader br;

	public static void main(String args[]) {
		JFrame frame = new JFrame();
		// JPanel panel=new JPanel();
		JLabel label = new JLabel();
		JTextArea area = new JTextArea();
		String message;
		frame.setTitle("Receiver");
		label.setLayout(new BorderLayout());
		label.setBounds(100, 10, 100, 20);
		label.setText("Your phone says:");
		label.setFocusable(false);
		frame.add(label);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		frame.setLayout(null);
		area.setBounds(100, 35, 250, 250);
		area.setFont(new Font("TimesNewRoman", Font.BOLD, 15));
		area.setEditable(false);
		frame.add(area);
		frame.setVisible(true);
		try {
			ss = new ServerSocket(6000);
			while (true) {
				s = ss.accept();
				is = new InputStreamReader(s.getInputStream());
				br = new BufferedReader(is);
				message = br.readLine();

				if (message.equalsIgnoreCase("Bye")) {
					ss.close();
					s.close();
					System.exit(0);
				}
				if (area.getText() == "")
					area.setText(message);
				else {
					area.append("\n" + message);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
