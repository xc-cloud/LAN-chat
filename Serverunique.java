package test;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import javax.swing.*;

public class Serverunique {
	static JLabel Server_back;
	static JFrame Server_main;
	static JLabel IO_ing;
	static JLabel friends, friends_out;
	static JLabel IP_text;
	static JLabel friends_ing,friends_file;
	static JTextArea friends_text;
	static JTextArea IO_text;
	static JTextArea IP;
	static JScrollPane friends_text_ing;
	static List friends_list, friends_list_out, user_name,friends_text_file;
	static JButton close_Server;
	static JButton start_Server;
	static ArrayList<Client> clients;
	static int exit_int = 0;
	public Serverunique() {
		Server_back = new JLabel(new ImageIcon(Serverunique.class.getResource("image.jpg")));
		Server_main = new JFrame("服务器");
		IO_ing = new JLabel("端口号：");
		friends = new JLabel("该局域内用户：");
		friends_out = new JLabel("该局域内被禁言用户：");
		IP_text = new JLabel("IP地址：");
		friends_ing = new JLabel("聊天记录：");
		friends_file = new JLabel("文件：");
		friends_text = new JTextArea("");
		friends_text_file=new List();
		IO_text = new JTextArea("");
		IP = new JTextArea("");
		friends_text_ing = new JScrollPane(friends_text);
		friends_list = new List();
		friends_list_out = new List();
		close_Server = new JButton("关闭服务器");
		start_Server = new JButton("创建服务器");
		user_name = new List();
		Server_back.setBounds(0, 0, 600, 600);
		Server_main.setSize(600, 530);
		Server_main.setIconImage(Toolkit.getDefaultToolkit().createImage(Serverunique.class.getResource("xc.png")));
		Server_main.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - Server_main.getWidth() / 2,
				Toolkit.getDefaultToolkit().getScreenSize().height / 2 - Server_main.getHeight() / 2);
		IP_text.setBounds(38, 45, 80, 30);
		friends_list.setBounds(38, 375, 180, 100);
		friends_list_out.setBounds(221, 375, 183, 100);
		IP.setBounds(88, 50, 90, 20);
		IO_ing.setBounds(198, 45, 55, 30);
		close_Server.setBounds(Server_main.getWidth() - 160, Server_main.getHeight() - 125, 120, 32);
		IO_text.setBounds(248, 50, 50, 20);
		start_Server.setBounds(310, 45, 100, 30);
		friends.setBounds(38, 340, 120, 30);
		friends_out.setBounds(221, 340, 150, 30);
		friends_ing.setBounds(38, 95, 80, 30);
		friends_file.setBounds(430, 95, 80, 30);
		friends_text_ing.setBounds(38, 130, 366, 200);
		friends_text_file.setBounds(440, 130, 120, 200);
		IO_ing.setForeground(Color.yellow);
		IP_text.setForeground(Color.yellow);
		friends_ing.setForeground(Color.pink);
		friends_file.setForeground(Color.red);
		friends_text.setForeground(Color.magenta);
		start_Server.setForeground(Color.GREEN);
		friends.setForeground(Color.lightGray);
		friends_out.setForeground(Color.lightGray);
		close_Server.setForeground(Color.RED);
		close_Server.setContentAreaFilled(false);
		start_Server.setContentAreaFilled(false);
		IP.setEnabled(false);
		Server_main.setLayout(null);
		Server_main.setResizable(false);
		friends_text.setEditable(false);
		Server_main.add(start_Server);
		Server_main.add(IO_ing);
		Server_main.add(friends);
		Server_main.add(friends_out);
		Server_main.add(IP_text);
		Server_main.add(IO_text);
		Server_main.add(IP);
		Server_main.add(friends_ing);
		Server_main.add(friends_file);
		Server_main.add(friends_text_ing);
		Server_main.add(friends_text_file);
		Server_main.add(close_Server);
		Server_main.add(friends_list);
		Server_main.add(friends_list_out);
		Server_main.add(Server_back);
		Server_main.setVisible(true);
		try {// 获取本机IP地址
			IP.setText(InetAddress.getLocalHost().getHostAddress());
		} catch (UnknownHostException e2) {
			JOptionPane.showMessageDialog(null, "获取本机IP地址失败，请手动输入！");
			IP.requestFocus(true);
			IP.setEditable(true);
		}

		friends_list.addMouseListener(new MouseAdapter() { // 在线用户
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					if (friends_list.getItemCount() != -1) {
						for (int i = clients.size() - 1; i >= 0; i--) {
							clients.get(i).getWriter().println(
									"out@id:message_0" + friends_list.getItem(friends_list.getSelectedIndex()));
							clients.get(i).getWriter().flush();
						}
						Serverunique.friends_text.append("\n对“"+ friends_list.getItem(friends_list.getSelectedIndex()) + "”实施了禁言操作。");
						friends_list_out.add(friends_list.getItem(friends_list.getSelectedIndex()));
						friends_list.remove(friends_list.getSelectedIndex());
					}
				}
			}
		});

		friends_list_out.addMouseListener(new MouseAdapter() { // 被禁言的用户
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					if (friends_list_out.getItemCount() != -1) {
						for (int i = clients.size() - 1; i >= 0; i--) {
							clients.get(i).getWriter().println(
									"@id:message_0" + friends_list_out.getItem(friends_list_out.getSelectedIndex()));
							clients.get(i).getWriter().flush();
						}
						Serverunique.friends_text.append("\n对“"+ friends_list_out.getItem(friends_list_out.getSelectedIndex()) + "”取消了禁言操作。");
						friends_list.add(friends_list_out.getItem(friends_list_out.getSelectedIndex()));
						friends_list_out.remove(friends_list_out.getSelectedIndex());
					}

				}
			}
		});

		close_Server.addActionListener(new ActionListener() {// 关闭所触发事件
			public void actionPerformed(ActionEvent e) {
				exit();
			}
		});
		start_Server.addActionListener(new ActionListener() {// 创建所触发事件
			public void actionPerformed(ActionEvent e) {
				try {
					if (IP.getText().length() != 0) {
						if (IO_text.getText().length() != 0) {
							clients = new ArrayList<Client>();
							new Server(new ServerSocket(Integer.parseInt(IO_text.getText()))).start();
						} else
							JOptionPane.showMessageDialog(null, "请输入端口号！");
					} else
						JOptionPane.showMessageDialog(null, "请输入IP地址！");
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "输入的端口号有误，请重新输入！");
					IO_text.setText(null);
					IO_text.requestFocus(true);
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "该端口号已被占用，请重新输入！");
					IO_text.setText(null);
					IO_text.requestFocus(true);
				}
			}
		});
		Server_main.addWindowListener(new WindowAdapter() {// 主窗口关闭按钮事件
			public void windowClosing(WindowEvent e) {
				exit();
			}
		});
		friends_text_file.addMouseListener(new MouseAdapter() { // 服务器删除文件
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
						for (int i = Serverunique.clients.size() - 1; i >= 0; i--) {// 把收到的消息群发给每个用户
							Serverunique.clients.get(i).getWriter().println("file@delete:file服务器移除了文件："+friends_text_file.getItem(friends_text_file.getSelectedIndex()));
							Serverunique.clients.get(i).getWriter().flush();
						}
						Serverunique.friends_text.append("\n移除了文件："+ friends_text_file.getItem(friends_text_file.getSelectedIndex()) );
						File file_name=new File("d:\\"+friends_text_file.getItem(friends_text_file.getSelectedIndex()));
						if(file_name.exists())
						file_name.delete();
						friends_text_file.remove(friends_text_file.getSelectedIndex());
				}
			}
		});
	}

	void exit() {// 退出/关闭按钮事件
		Server_main.setVisible(false);
		if (exit_int == 1) {
			for (int i = clients.size() - 1; i >= 0; i--) {
				clients.get(i).getWriter().println("delete@id:Server_exit_0");
				clients.get(i).getWriter().flush();
			}
		}
		System.exit(0);
	}

	public static void main(String[] args) {
		new Serverunique();
	}
}

// 客户端服务线程
class Client extends Thread {
	Socket socket;
	BufferedReader reader;
	PrintWriter writer;
	String info;
	FileWriter file_name;
	public PrintWriter getWriter() {
		return writer;
	}

	public Client(Socket socket) {
		this.socket = socket;
		try {
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer = new PrintWriter(socket.getOutputStream());
			info = reader.readLine();
		} catch (IOException e) {
		}
	}

	public void run() {
		String message = null,file_all="";
		while (true) {
			int file_i=0,file_j=0;
			boolean message_all_temp=true;
			try {
				message = reader.readLine();
				if(message.contains("download@new:") == true){
					if (Serverunique.friends_text_file.getItemCount() != 0)
						for (; file_i < Serverunique.friends_text_file.getItemCount(); file_i++) {
							if (message.contains(Serverunique.friends_text_file.getItem(file_i)) == true) {
								for (; file_j < Serverunique.user_name.getItemCount(); file_j++) {
									if (message.contains(Serverunique.user_name.getItem(file_j)) == true) {
										new File_Thread(file_i,file_j).start();
										break;
									}
								}
							}
						}
					message_all_temp=false;
				}else
				if(message.contains("file@new:name") == true){
					file_name=new FileWriter("d:\\"+message.substring(13, message.length()));	
					message_all_temp=false;
				}else if(message.contains("file@start:file") == true){
					file_name.write(message.substring(15, message.length())+"\r\n");
					System.out.println(file_all);
					message_all_temp=false;
				}else if(message.contains("file@close:file") == true){
					Serverunique.friends_text_file.add(message.substring(15, message.length()));
					file_name.close();
					Serverunique.friends_text.append("\n接收到文件："+ message.replace("file@close:file", "") );
					message_all_temp=true;
					message="file@add:file服务器接收到文件："+message.substring(15, message.length());
					
				}else if (message.contains("delete@id:") == true) {
					for (int t = 0; t < Serverunique.user_name.getItemCount(); t++) {
						if (message.contains(Serverunique.user_name.getItem(t)) == true) {
							Serverunique.clients.remove(t);
							Serverunique.user_name.remove(t);
							break;
						}
					}
					if (Serverunique.friends_list.getItemCount() != 0)
						for (int i = 0; i < Serverunique.friends_list.getItemCount(); i++) {
							if (message.contains(Serverunique.friends_list.getItem(i)) == true) {
								Serverunique.friends_list.remove(i);
								Serverunique.friends_text.append("\n“"+ message.replace("delete@id:", "") + "”退出了聊天服务器。");
								break;
							}
						}
					if (Serverunique.friends_list_out.getItemCount() != 0)
						for (int i = 0; i < Serverunique.friends_list_out.getItemCount(); i++) {
							if (message.contains(Serverunique.friends_list_out.getItem(i)) == true) {
								Serverunique.friends_list_out.remove(i);
								Serverunique.friends_text.append("\n“"+ message.replace("delete@id:", "") + "”退出了聊天服务器。");
								break;
							}
						}
				} else {
					Serverunique.friends_text.append("\n" + message);
				}
				//BUG收到文件内容也同时分发出去了
				if(message_all_temp==true){
					message_all(message);
				}
			} catch (

			IOException e)

			{
			}

		}
	}
	public void message_all(String message){
		for (int i = Serverunique.clients.size() - 1; i >= 0; i--) {// 把收到的消息群发给每个用户
			Serverunique.clients.get(i).getWriter().println(message);
			Serverunique.clients.get(i).getWriter().flush();
		}
	}
	public void updateUserList() {
		Serverunique.friends_list.add(info);
		Serverunique.friends_text.append("\n“" + info + "”加入了该聊天室。");
		if (Serverunique.clients.size() > 0) {
			for (int i = Serverunique.clients.size() - 1; i >= 0; i--) {
				Serverunique.clients.get(i).getWriter().println("@deleteallid");
			}
			for (int i = Serverunique.clients.size() - 1; i >= 0; i--) {
				for (int j = 0; j < Serverunique.friends_list.getItemCount(); j++) {
					Serverunique.clients.get(i).getWriter().println("new@id:" + Serverunique.friends_list.getItem(j));
					Serverunique.clients.get(i).getWriter().flush();
				}
				for (int j = 0; j < Serverunique.friends_list_out.getItemCount(); j++) {
					Serverunique.clients.get(i).getWriter()
							.println("new@_out_id:" + Serverunique.friends_list_out.getItem(j));
					Serverunique.clients.get(i).getWriter().flush();
				}
				Serverunique.clients.get(i).getWriter().println("file@add:file_removeall:");
				Serverunique.clients.get(i).getWriter().flush();
				for (int j = 0; j < Serverunique.friends_text_file.getItemCount(); j++) {
					Serverunique.clients.get(i).getWriter().println("file@add_ls:file" + Serverunique.friends_text_file.getItem(j));
					Serverunique.clients.get(i).getWriter().flush();
				}
				
			}
		}
	}
}

// 为等待客户端接入线程。
class Server extends Thread {
	ServerSocket serverSocket;

	public Server(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}

	public void run() {
		Serverunique.exit_int = 1;
		Serverunique.start_Server.setText("创建成功！");
		Serverunique.friends_text.setText("服务器已创建好了，赶快邀请好友加入聊天吧！本服务器ID为：" + Serverunique.IO_text.getText());
		Serverunique.Server_main.setTitle("服务端ID：" + Serverunique.IO_text.getText());
		Serverunique.start_Server.setEnabled(false);
		while (true) {
			try {
				int j = 1;
				Socket socket = serverSocket.accept();
				Client client = new Client(socket);
				if (client.info != null) {
					for (int i = 0; i < Serverunique.friends_list.getItemCount(); i++) {
						if (client.info.equals(Serverunique.friends_list.getItem(i)) == true) {
							client.writer.println("warning@id_inseparable");
							client.writer.flush();
							j = 0;
						}
					}
					if (j == 1) {
						Serverunique.user_name.add(client.info);
						client.writer.println("warning@");
						client.writer.flush();
						Serverunique.clients.add(client);
						client.updateUserList();
						client.start();// 开启为客户端服务的线程
					}
				}
			} catch (IOException e) {
			}
		}
	}
}
class File_Thread extends Thread{
	String lineTxt=null;
	int user,Client;
	public File_Thread(int user,int Client){
		this.user=user;
		this.Client=Client;
	}
	public void run() {
		try {
			File file_sum=new File("d:\\"+Serverunique.friends_text_file.getItem(user));	
			InputStreamReader read=new InputStreamReader(new FileInputStream(file_sum));
			BufferedReader buff=new BufferedReader(read);
			Serverunique.clients.get(Client).getWriter().println("download@start:name");
			Serverunique.clients.get(Client).getWriter().flush();
				while((lineTxt = buff.readLine())!=null){
					Serverunique.clients.get(Client).getWriter().println("download@go:name"+lineTxt);
					Serverunique.clients.get(Client).getWriter().flush();
			}
				buff.close();
				Serverunique.clients.get(Client).getWriter().println("download@close:name");
				Serverunique.clients.get(Client).getWriter().flush();
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}
	}
}