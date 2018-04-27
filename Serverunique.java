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
		Server_main = new JFrame("������");
		IO_ing = new JLabel("�˿ںţ�");
		friends = new JLabel("�þ������û���");
		friends_out = new JLabel("�þ����ڱ������û���");
		IP_text = new JLabel("IP��ַ��");
		friends_ing = new JLabel("�����¼��");
		friends_file = new JLabel("�ļ���");
		friends_text = new JTextArea("");
		friends_text_file=new List();
		IO_text = new JTextArea("");
		IP = new JTextArea("");
		friends_text_ing = new JScrollPane(friends_text);
		friends_list = new List();
		friends_list_out = new List();
		close_Server = new JButton("�رշ�����");
		start_Server = new JButton("����������");
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
		try {// ��ȡ����IP��ַ
			IP.setText(InetAddress.getLocalHost().getHostAddress());
		} catch (UnknownHostException e2) {
			JOptionPane.showMessageDialog(null, "��ȡ����IP��ַʧ�ܣ����ֶ����룡");
			IP.requestFocus(true);
			IP.setEditable(true);
		}

		friends_list.addMouseListener(new MouseAdapter() { // �����û�
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					if (friends_list.getItemCount() != -1) {
						for (int i = clients.size() - 1; i >= 0; i--) {
							clients.get(i).getWriter().println(
									"out@id:message_0" + friends_list.getItem(friends_list.getSelectedIndex()));
							clients.get(i).getWriter().flush();
						}
						Serverunique.friends_text.append("\n�ԡ�"+ friends_list.getItem(friends_list.getSelectedIndex()) + "��ʵʩ�˽��Բ�����");
						friends_list_out.add(friends_list.getItem(friends_list.getSelectedIndex()));
						friends_list.remove(friends_list.getSelectedIndex());
					}
				}
			}
		});

		friends_list_out.addMouseListener(new MouseAdapter() { // �����Ե��û�
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					if (friends_list_out.getItemCount() != -1) {
						for (int i = clients.size() - 1; i >= 0; i--) {
							clients.get(i).getWriter().println(
									"@id:message_0" + friends_list_out.getItem(friends_list_out.getSelectedIndex()));
							clients.get(i).getWriter().flush();
						}
						Serverunique.friends_text.append("\n�ԡ�"+ friends_list_out.getItem(friends_list_out.getSelectedIndex()) + "��ȡ���˽��Բ�����");
						friends_list.add(friends_list_out.getItem(friends_list_out.getSelectedIndex()));
						friends_list_out.remove(friends_list_out.getSelectedIndex());
					}

				}
			}
		});

		close_Server.addActionListener(new ActionListener() {// �ر��������¼�
			public void actionPerformed(ActionEvent e) {
				exit();
			}
		});
		start_Server.addActionListener(new ActionListener() {// �����������¼�
			public void actionPerformed(ActionEvent e) {
				try {
					if (IP.getText().length() != 0) {
						if (IO_text.getText().length() != 0) {
							clients = new ArrayList<Client>();
							new Server(new ServerSocket(Integer.parseInt(IO_text.getText()))).start();
						} else
							JOptionPane.showMessageDialog(null, "������˿ںţ�");
					} else
						JOptionPane.showMessageDialog(null, "������IP��ַ��");
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "����Ķ˿ں��������������룡");
					IO_text.setText(null);
					IO_text.requestFocus(true);
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "�ö˿ں��ѱ�ռ�ã����������룡");
					IO_text.setText(null);
					IO_text.requestFocus(true);
				}
			}
		});
		Server_main.addWindowListener(new WindowAdapter() {// �����ڹرհ�ť�¼�
			public void windowClosing(WindowEvent e) {
				exit();
			}
		});
		friends_text_file.addMouseListener(new MouseAdapter() { // ������ɾ���ļ�
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
						for (int i = Serverunique.clients.size() - 1; i >= 0; i--) {// ���յ�����ϢȺ����ÿ���û�
							Serverunique.clients.get(i).getWriter().println("file@delete:file�������Ƴ����ļ���"+friends_text_file.getItem(friends_text_file.getSelectedIndex()));
							Serverunique.clients.get(i).getWriter().flush();
						}
						Serverunique.friends_text.append("\n�Ƴ����ļ���"+ friends_text_file.getItem(friends_text_file.getSelectedIndex()) );
						File file_name=new File("d:\\"+friends_text_file.getItem(friends_text_file.getSelectedIndex()));
						if(file_name.exists())
						file_name.delete();
						friends_text_file.remove(friends_text_file.getSelectedIndex());
				}
			}
		});
	}

	void exit() {// �˳�/�رհ�ť�¼�
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

// �ͻ��˷����߳�
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
					Serverunique.friends_text.append("\n���յ��ļ���"+ message.replace("file@close:file", "") );
					message_all_temp=true;
					message="file@add:file���������յ��ļ���"+message.substring(15, message.length());
					
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
								Serverunique.friends_text.append("\n��"+ message.replace("delete@id:", "") + "���˳��������������");
								break;
							}
						}
					if (Serverunique.friends_list_out.getItemCount() != 0)
						for (int i = 0; i < Serverunique.friends_list_out.getItemCount(); i++) {
							if (message.contains(Serverunique.friends_list_out.getItem(i)) == true) {
								Serverunique.friends_list_out.remove(i);
								Serverunique.friends_text.append("\n��"+ message.replace("delete@id:", "") + "���˳��������������");
								break;
							}
						}
				} else {
					Serverunique.friends_text.append("\n" + message);
				}
				//BUG�յ��ļ�����Ҳͬʱ�ַ���ȥ��
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
		for (int i = Serverunique.clients.size() - 1; i >= 0; i--) {// ���յ�����ϢȺ����ÿ���û�
			Serverunique.clients.get(i).getWriter().println(message);
			Serverunique.clients.get(i).getWriter().flush();
		}
	}
	public void updateUserList() {
		Serverunique.friends_list.add(info);
		Serverunique.friends_text.append("\n��" + info + "�������˸������ҡ�");
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

// Ϊ�ȴ��ͻ��˽����̡߳�
class Server extends Thread {
	ServerSocket serverSocket;

	public Server(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}

	public void run() {
		Serverunique.exit_int = 1;
		Serverunique.start_Server.setText("�����ɹ���");
		Serverunique.friends_text.setText("�������Ѵ������ˣ��Ͽ�������Ѽ�������ɣ���������IDΪ��" + Serverunique.IO_text.getText());
		Serverunique.Server_main.setTitle("�����ID��" + Serverunique.IO_text.getText());
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
						client.start();// ����Ϊ�ͻ��˷�����߳�
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