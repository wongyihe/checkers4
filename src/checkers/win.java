package checkers;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Label;
import java.awt.List;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.text.html.ListView;

public class win extends Frame {
	Checkers app;
	board b;
	Button btn;
	TextField txt_ip;
	TextField txt_port;
	List list_color;
	TextArea sockettext = new TextArea();
	Label label_ip;
	Label label_port;
	Button btnStart;
	Button btnStop;
	String mcolor = "RED";
	HashMap<String, Integer> mapcolor = new HashMap<String, Integer>();
	int mycolor;

	void init() {
		mapcolor.put("RED", 3);
		mapcolor.put("YELLOW", 2);

		txt_ip = new TextField(10);
		//txt_ip.setText("127.0.0.1");
		txt_ip.setText("192.168.1.197");
		txt_port = new TextField(10);
		txt_port.setText("8765");

		list_color = new List();
		list_color.add("RED");
		list_color.add("YELLOW");
		list_color.select(0);
		list_color.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mcolor = e.getActionCommand();
				mycolor = (int) mapcolor.get(mcolor);
				//System.out.println("mycolor"+mycolor);
				b.myColor = mycolor;
			}
		});
		btnStart = new Button();
		btnStart.setSize(100, 50);
		btnStart.setLabel("start server");
		btnStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					//System.out.println(txt_ip.getText() + "," + Integer.parseInt(txt_port.getText()));
					app.w.b.sc.init(txt_ip.getText(), Integer.parseInt(txt_port.getText()));
					// sc.init(txt_ip.getText(), Integer.parseInt(txt_port.getText()));
				} catch (Exception e1) {
					// sc.init("127.0.0.1", 8765);
					System.out.println("omg" + e1);
				}
				// app.ServerPort = Integer.parseInt(txt_port.getText());
				app.start();
			}
		});

		btnStop = new Button();
		btnStop.setSize(100, 50);
		btnStop.setLabel("stop server");
		btnStop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				app.OutServer = true;
			}
		});

		label_ip = new Label();
		label_ip.setText("ip:");
		label_port = new Label();
		label_port.setText("client port:");

	}

	public win(Checkers app) throws HeadlessException {
		this.app = app;
		init();
		b = new board();
		b.setBounds(0, 0, 1000, 700);
		setLayout(new BorderLayout());
		add(label_ip);
		add(txt_ip);
		add(label_port);
		add(txt_port);
		add(btnStart);
		add(btnStop);
		add(list_color);
		add(b);
		add(sockettext);
	}
}
