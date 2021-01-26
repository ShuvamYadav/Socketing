import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

//import jdk.internal.org.jline.utils.InputStreamReader;

public class Receiver implements ActionListener{
	static ServerSocket ss;
	static Socket s;
	static java.io.InputStreamReader is;
	static BufferedReader br;
	static JTextArea area = new JTextArea();
	static JButton button = new JButton("Send");
	static JTextField text;
	static PrintStream os;
	public static void main(String args[]) {
		String message;
		JFrame frame = new JFrame();
	    text = new JTextField();
		JLabel label = new JLabel();
		InetAddress IP = null;
		try {
			IP = InetAddress.getLocalHost();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		frame.setTitle("Receiver");
		label.setLayout(null);
		label.setBounds(10, 10, 250, 20);
		label.setText("IP of this system is: " + IP.getHostAddress());
		label.setFocusable(false);
		text.setBounds(10, 220, 170, 25);
		area.add(text);
		frame.add(label);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		frame.setLayout(null);
		area.setBounds(100, 35, 250, 250);
		area.setFont(new Font("Calibri", Font.PLAIN, 20));
		area.setEditable(false);
		button.setBounds(180, 220, 60, 25);
		button.setMargin(new Insets(2, 2, 2, 2));
		button.setFocusable(false);
		button.addActionListener(new Receiver());
		area.add(button);
		frame.add(area);
		frame.setVisible(true);
		try {
			ss = new ServerSocket(6000);
			while (true) {
				s = ss.accept();
				os = new PrintStream(s.getOutputStream());
				is = new InputStreamReader(s.getInputStream());
				br = new BufferedReader(is);
				message = br.readLine();
				if (message.equalsIgnoreCase("Bye")) {
					ss.close();
					s.close();
					System.exit(0);
				}
				area.append("\n" + message);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==button) {
			os.print(text.getText());
			os.close();
		}
			
	}

}
