package test;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

public class Clientunique {
	static Socket socket;
	static PrintWriter pw;
	static JButton message, Server_ing, close, setmessagecolor, removemessageall, setfontmax_min, setfontmax_file;
	static List friends_sum, friends_sum_out, friends_file;
	static JTextArea friends_list, f_message;
	static JScrollPane friends_ing, message_ing;
	static JLabel f_message_ing, IO, IP_from, user_name, friends, friends_out, user_out, message_text, message_file,
			new_user, logo, back;
	static JTextArea IO_text, IP, ID;
	static String info, temp_ID = "";
	static JFrame Client_main;
	static InputStreamReader isr;
	static BufferedReader binreader;
	static int exit_int = 0;
	static JFileChooser file;
	static String filename;
	static FileWriter file_name;

	public Clientunique() {
		message = new JButton("发送");
		close = new JButton("退出");
		Client_main = new JFrame("客户端");
		logo = new JLabel(new ImageIcon(Clientunique.class.getResource("Client.png")));
		Client_main.setIconImage(Toolkit.getDefaultToolkit().createImage(Clientunique.class.getResource("xc.png")));
		back = new JLabel(new ImageIcon(Clientunique.class.getResource("image1.jpg")));
		new_user = new JLabel("loading");
		Server_ing = new JButton("连接");
		friends_sum = new List();
		friends_sum_out = new List();
		friends_list = new JTextArea("等待您的发送连接请求......");
		friends_file = new List();
		f_message = new JTextArea();
		friends_ing = new JScrollPane(friends_list);
		message_ing = new JScrollPane(f_message);
		f_message_ing = new JLabel("欲发出消息:");
		setfontmax_file = new JButton("文件传输");
		setfontmax_min = new JButton("字体大小");
		setmessagecolor = new JButton("字体颜色");
		removemessageall = new JButton("清空记录");
		IO = new JLabel("端口号：");
		IP_from = new JLabel("IP地址：");
		user_name = new JLabel("用户名：");
		friends = new JLabel("正在使用的用户：");
		friends_out = new JLabel("已被禁言的用户：");
		user_out = new JLabel("您正被禁言中。。。");
		message_file = new JLabel("文件：");
		message_text = new JLabel("消息记录：");
		IO_text = new JTextArea("");
		IP = new JTextArea("");
		ID = new JTextArea("");
		file = new JFileChooser();
		back.setBounds(0, 0, 584, 700);
		Client_main.setSize(584, 138);
		IP_from.setBounds(28, 45, 80, 30);
		IP.setBounds(78, 50, 90, 20);
		IO.setBounds(188, 45, 55, 30);
		IO_text.setBounds(238, 50, 50, 20);
		user_name.setBounds(305, 45, 56, 30);
		ID.setBounds(356, 50, 70, 20);
		logo.setBounds(10, 0, 100, 100);
		new_user.setBounds(120, 35, 600, 40);
		Server_ing.setBounds(440, 45, 100, 30);
		friends_ing.setBounds(168, 120, 260, 150);
		friends_file.setBounds(430, 120, 112, 150);
		f_message_ing.setBounds(38, 285, 80, 32);
		setfontmax_file.setBounds(175, 280, 90, 28);
		setfontmax_min.setBounds(265, 280, 90, 28);
		setmessagecolor.setBounds(355, 280, 90, 28);
		removemessageall.setBounds(445, 280, 90, 28);
		user_out.setBounds(230, 327, 120, 32);
		message_ing.setBounds(18, 318, 522, 50);
		message.setBounds(120, 400, 80, 32);
		close.setBounds(350, 400, 80, 32);
		friends_sum.setBounds(18, 120, 130, 70);
		friends_sum_out.setBounds(18, 215, 130, 70);
		friends.setBounds(28, 90, 120, 30);
		friends_out.setBounds(28, 188, 120, 30);
		message_text.setBounds(185, 90, 80, 30);
		message_file.setBounds(430, 90, 80, 30);
		new_user.setFont(new Font("", 1, 30));
		close.setForeground(Color.red);
		message.setForeground(Color.white);
		Server_ing.setForeground(Color.black);
		user_name.setForeground(Color.RED);
		IO.setForeground(Color.RED);
		IP_from.setForeground(Color.RED);
		removemessageall.setForeground(Color.RED);
		f_message.setEnabled(false);
		close.setContentAreaFilled(false);
		friends.setVisible(false);
		message_text.setVisible(false);
		message_file.setVisible(false);
		Server_ing.setContentAreaFilled(false);
		new_user.setVisible(false);
		friends_list.setEditable(false);
		message.setEnabled(false);
		message.setContentAreaFilled(false);
		Client_main.setLayout(null);
		Client_main.setResizable(false);
		IP.setEnabled(false);
		user_out.setVisible(false);
		logo.setVisible(false);
		Client_main.add(logo);// 将所有对象加入到主界面
		Client_main.add(user_out);
		Client_main.add(new_user);
		Client_main.add(friends_ing);
		Client_main.add(user_name);
		Client_main.add(ID);
		Client_main.add(setmessagecolor);
		Client_main.add(removemessageall);
		Client_main.add(setfontmax_file);
		Client_main.add(setfontmax_min);
		Client_main.add(message);
		Client_main.add(close);
		Client_main.add(Server_ing);
		Client_main.add(friends_sum);
		Client_main.add(friends_sum_out);
		Client_main.add(f_message_ing);
		Client_main.add(message_ing);
		Client_main.add(IO);
		Client_main.add(IP_from);
		Client_main.add(friends);
		Client_main.add(friends_out);
		Client_main.add(message_text);
		Client_main.add(message_file);
		Client_main.add(friends_file);
		Client_main.add(IO_text);
		Client_main.add(IP);
		Client_main.add(back);
		Client_main.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - Client_main.getWidth() / 2,
				Toolkit.getDefaultToolkit().getScreenSize().height / 2 - Client_main.getHeight() / 2);
		Client_main.setVisible(true);
		try {// 获取本机IP地址
			IP.setText(InetAddress.getLocalHost().getHostAddress());
		} catch (UnknownHostException e2) {
			JOptionPane.showMessageDialog(null, "获取本机IP地址失败，请手动输入！");
			IP.requestFocus(true);
			IP.setEditable(true);
		}
		Server_ing.addActionListener(new ActionListener() {// 连接按钮事件
			public void actionPerformed(ActionEvent e1) {
				if (IP.getText().length() != 0) {
					if (IO_text.getText().length() != 0) {
						if (ID.getText().length() != 0) {
							temp_ID = ID.getText();
							Server_ing.setEnabled(false);
							try {
								socket = new Socket(IP.getText(), Integer.parseInt(IO_text.getText()));
								pw = new PrintWriter(socket.getOutputStream());
								pw.println(temp_ID);
								pw.flush();
								isr = new InputStreamReader(socket.getInputStream());
								binreader = new BufferedReader(isr);
								info = binreader.readLine();
								if (info.contains("warning@id_inseparable") == true) {
									JOptionPane.showMessageDialog(null, "您的用户名已存在，请重新输入！");
									ID.setText(null);
									ID.requestFocus(true);
									Server_ing.setEnabled(true);
								} else if (info.contains("warning@") == true) {
									Server_ing.setText("连接成功！");
									Client_main.setTitle("用户：" + temp_ID + "\t已成功连接到服务器！");
									new_user.setVisible(true);
									new_user.setText(temp_ID + "      IP:" + IP.getText());
									new_user.setForeground(Color.RED);
									IP.setVisible(false);
									IP_from.setVisible(false);
									IO_text.setVisible(false);
									IO.setVisible(false);
									ID.setVisible(false);
									user_name.setVisible(false);
									Server_ing.setVisible(false);
									logo.setVisible(true);
									friends.setVisible(true);
									message_text.setVisible(true);
									message_file.setVisible(true);
									exit_int = 1;
									Client_main.setSize(584, 479);
									Client_main.setLocation(
											Toolkit.getDefaultToolkit().getScreenSize().width / 2
													- Client_main.getWidth() / 2,
											Toolkit.getDefaultToolkit().getScreenSize().height / 2
													- Client_main.getHeight() / 2);
									close.setEnabled(true);
									f_message.setEnabled(true);
									message.setEnabled(true);
									new ClientThread().start();
								}
							} catch (NumberFormatException e) {
								IOException();
							} catch (UnknownHostException e) {
								IPException();
							} catch (IOException e) {
								IOException();
							}
						} else
							JOptionPane.showMessageDialog(null, "请输入用户名！（将用来标识你在该服务器的昵称！）");
					} else
						JOptionPane.showMessageDialog(null, "请输入服务器的端口号！");
				} else
					IPException();
			}
		});
		message.addActionListener(new ActionListener() {// 发送按钮事件
			public void actionPerformed(ActionEvent e) {
				pw.println(temp_ID + "：" + f_message.getText());
				pw.flush();
				f_message.setText(null);
			}
		});
		close.addActionListener(new ActionListener() {// 关闭按钮事件
			public void actionPerformed(ActionEvent e1) {
				exit();
			}
		});
		Client_main.addWindowListener(new WindowAdapter() {// 主窗口关闭事件
			public void windowClosing(WindowEvent e) {
				exit();
			}
		});
		setfontmax_min.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {

				int b;
				try {
					b = Integer.parseInt(JOptionPane.showInputDialog("请输入字体大小："));
					friends_list.setFont(new Font("", 1, b));
					f_message.setFont(new Font("", 1, b));
				} catch (NumberFormatException e) {
				} catch (HeadlessException e) {
				}

			}
		});
		setmessagecolor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
				String color = JOptionPane.showInputDialog("请输入文本颜色（用逗号隔开（取值0-255））：");
				if (color.contains(",") == true) {
					String[] color_str = color.split(",");
					if (color_str.length == 3) {
						int[] color_int = new int[3];
						for (int i = 0; i < 3; i++) {
							color_int[i] = Integer.parseInt(color_str[i]);
						}
						Color s = new Color(color_int[0], color_int[1], color_int[2]);
						friends_list.setForeground(s);
						f_message.setForeground(s);
					}
				} else {
					String[] color_str1 = color.split("，");
					if (color_str1.length == 3) {
						int[] color_int = new int[3];
						for (int i = 0; i < 3; i++) {
							color_int[i] = Integer.parseInt(color_str1[i]);
						}
						Color s = new Color(color_int[0], color_int[1], color_int[2]);
						friends_list.setForeground(s);
						f_message.setForeground(s);
					}
				}
			}
		});
		removemessageall.addActionListener(new ActionListener() {// 关闭按钮事件
			public void actionPerformed(ActionEvent e1) {
				friends_list.setText("记录已清空。");
			}
		});
		setfontmax_file.addActionListener(new ActionListener() {// 发文件按钮事件
			public void actionPerformed(ActionEvent e1) {
				String lineTxt = null;
				try {
					file.setDialogTitle("发送文件（目前只支持文本文件）");
					int bool=file.showOpenDialog(Client_main);// 打开文件
					// 已获取的文件路径
					if (bool == JFileChooser.APPROVE_OPTION) {
					File file_sum = new File(file.getSelectedFile().toString());
					String filename= file.getName(file.getSelectedFile()).substring(file.getName(file.getSelectedFile()).length()-4, file.getName(file.getSelectedFile()).length());
					if(filename.equals(".txt")==true){
					InputStreamReader read = new InputStreamReader(new FileInputStream(file_sum));
					BufferedReader buff = new BufferedReader(read);
					Clientunique.friends_list.append("\n" + "正在上传文件：" + file.getSelectedFile());
					pw.println("file@new:name" + file.getName(file.getSelectedFile()));
					pw.flush();
					while ((lineTxt = buff.readLine()) != null) {
						pw.println("file@start:file" + lineTxt);
						pw.flush();
					}
					pw.println("file@close:file" + file.getName(file.getSelectedFile()));
					pw.flush();
					Clientunique.friends_list.append("\n" + "文件：“" + file.getName(file.getSelectedFile()) + "”上传成功！");
					}
					else
						Clientunique.friends_list.append("\n" + "文件：“" + file.getName(file.getSelectedFile()) + "”上传失败！\n错误原因：该类型文件不支持上传！");
					}
				} catch (IOException e) {
					
				}
			}
		});
		friends_file.addMouseListener(new MouseAdapter() { // 保存文件事件
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					try {
						file.setDialogTitle("保存文件");
						int bool = file.showSaveDialog(Client_main);// 保存文件
						if (bool ==0) {
							filename = file.getSelectedFile().toString();
							pw.println(
									"download@new:" + temp_ID + friends_file.getItem(friends_file.getSelectedIndex()));
							pw.flush();
						}
					} catch (HeadlessException e1) {
						JOptionPane.showMessageDialog(null, "无法保存该文件！！");
					}
				}
			}
		});
	}

	void exit() {// 退出时触发事件
		Client_main.setVisible(false);
		if (exit_int == 1) {
			pw.println("delete@id:" + temp_ID);
			pw.flush();
		}
		System.exit(0);
	}

	void IOException() {// 端口号异常处理。
		JOptionPane.showMessageDialog(null, "该端口号不存在，请重新输入！");
		IO_text.setText(null);
		IO_text.requestFocus(true);
		Server_ing.setEnabled(true);
	}

	void IPException() {// IP异常处理。
		JOptionPane.showMessageDialog(null, "IP地址有误，请重新输入！");
		IP.setText(null);
		IP.requestFocus(true);
		IP.setEditable(true);
		Server_ing.setEnabled(true);
	}

	public static void main(String[] args) {
		new Clientunique();
	}
}

// 启动收消息线程。
class ClientThread extends Thread {
	public void run() {
		try {

			while (true) {
				Clientunique.info = Clientunique.binreader.readLine();
				if (Clientunique.info.length() != 0) {
					if (Clientunique.info.contains("file@add:file_removeall:") == true) {
						Clientunique.friends_file.removeAll();
					} else if (Clientunique.info.contains("file@add_ls:file") == true) {
						Clientunique.friends_file.add(Clientunique.info.replace("file@add_ls:file", ""));
					} else if (Clientunique.info.contains("download@start:name") == true) {
						File file_sum = new File(Clientunique.filename);
						file_sum.createNewFile();
						Clientunique.file_name = new FileWriter(Clientunique.filename, true);

					} else if (Clientunique.info.contains("download@go:name") == true) {
						Clientunique.file_name.write(Clientunique.info.substring(16, Clientunique.info.length())+"\r\n");
					} else if (Clientunique.info.contains("download@close:name") == true) {
						Clientunique.file_name.close();
						Clientunique.friends_list.append("\n" + "文件下载成功！保存路径：" + Clientunique.filename);
					} else if (Clientunique.info.contains("file@delete:file服务器移除了文件：") == true) {
						for (int i = 0; i <= Clientunique.friends_file.getItemCount(); i++) {
							if (Clientunique.info.replace("file@delete:file服务器移除了文件：", "")
									.contains(Clientunique.friends_file.getItem(i)) == true) {
								Clientunique.friends_file.remove(i);
								break;
							}
						}
						Clientunique.friends_list.append("\n" + Clientunique.info.replace("file@delete:file", ""));
					} else if (Clientunique.info.contains("file@add:file服务器接收到文件：") == true) {
						Clientunique.friends_file.add(Clientunique.info.replace("file@add:file服务器接收到文件：", ""));
						Clientunique.friends_list.append("\n" + Clientunique.info.replace("file@add:file", ""));
					} else if (Clientunique.info.contains("out@id:message_0") == true) {// 加入禁言
						if (Clientunique.info.replace("out@id:message_0", "").contains(Clientunique.temp_ID) == true) {
							Clientunique.message.setEnabled(false);
							Clientunique.f_message.setEnabled(false);
							Clientunique.user_out.setVisible(true);
							Clientunique.friends_list.append("\n" + "你已被服务器禁言。");
						} else
							Clientunique.friends_list
									.append("\n“" + Clientunique.info.replace("out@id:message_0", "") + "”被服务器禁言。");

						for (int i = 0; i <= Clientunique.friends_sum.getItemCount(); i++) {
							if (Clientunique.info.replace("out@id:message_0", "")
									.contains(Clientunique.friends_sum.getItem(i)) == true) {
								Clientunique.friends_sum_out.add(Clientunique.friends_sum.getItem(i));
								Clientunique.friends_sum.remove(i);
								break;
							}
						}
					} else if (Clientunique.info.contains("@id:message_0") == true) {// 取消禁言
						if (Clientunique.info.replace("@id:message_0", "").contains(Clientunique.temp_ID) == true) {
							Clientunique.message.setEnabled(true);
							Clientunique.f_message.setEnabled(true);
							Clientunique.user_out.setVisible(false);
							Clientunique.friends_list.append("\n" + "你已被服务器解除了禁言。");
						} else
							Clientunique.friends_list
									.append("\n“" + Clientunique.info.replace("@id:message_0", "") + "”被服务器解除了禁言。");

						for (int i = 0; i <= Clientunique.friends_sum_out.getItemCount(); i++) {
							if (Clientunique.info.replace("@id:message_0", "")
									.contains(Clientunique.friends_sum_out.getItem(i)) == true) {
								Clientunique.friends_sum.add(Clientunique.friends_sum_out.getItem(i));
								Clientunique.friends_sum_out.remove(i);
								break;
							}
						}
					} else if (Clientunique.info.contains("delete@id:Server_exit_0") == true) {
						Clientunique.friends_list.append(
								"\n" + Clientunique.info.replace("delete@id:Server_exit_0", "") + "服务器已关闭了所有用户的聊天通道。");
						Clientunique.Client_main.setTitle("用户：" + Clientunique.temp_ID + "\t与服务器的连接被关闭！");
						Clientunique.friends_sum.removeAll();
						Clientunique.friends_sum_out.removeAll();
						Clientunique.f_message.setEnabled(false);
						Clientunique.message.setEnabled(false);
						Clientunique.user_out.setVisible(false);
					} else if (Clientunique.info.contains("@deleteallid") == true) {
						Clientunique.friends_sum.removeAll();
						Clientunique.friends_sum_out.removeAll();
					} else if (Clientunique.info.contains("new@id:") == true) {
						Clientunique.friends_sum.add(Clientunique.info.replace("new@id:", ""));
						if (Clientunique.info.contains("new@id:" + Clientunique.ID.getText())) {
							Clientunique.friends_list.setText("我已加入该聊天服务器。我的ID为：" + Clientunique.ID.getText());
						} else {
							Clientunique.friends_list
									.append("\n“" + Clientunique.info.replace("new@id:", "") + "”加入了该聊天室。");
						}
					} else if (Clientunique.info.contains("delete@id:") == true) {

						if (Clientunique.friends_sum.getItemCount() != 0)
							for (int i = 0; i < Clientunique.friends_sum.getItemCount(); i++) {
								if (Clientunique.info.contains(Clientunique.friends_sum.getItem(i)) == true) {
									Clientunique.friends_sum.remove(i);
									Clientunique.friends_list
											.append("\n“" + Clientunique.info.replace("delete@id:", "") + "”退出了聊天室。");
									break;
								}
							}
						if (Clientunique.friends_sum_out.getItemCount() != 0)
							for (int i = 0; i < Clientunique.friends_sum_out.getItemCount(); i++) {
								if (Clientunique.info.contains(Clientunique.friends_sum_out.getItem(i)) == true) {
									Clientunique.friends_sum_out.remove(i);
									Clientunique.friends_list
											.append("\n“" + Clientunique.info.replace("delete@id:", "") + "”退出了聊天室。");
									break;
								}
							}
					} else if (Clientunique.info.contains("new@_out_id:") == true) {
						Clientunique.friends_sum_out.add(Clientunique.info.replace("new@_out_id:", ""));
					} else
						Clientunique.friends_list.append("\n" + Clientunique.info);
				}
			}
		} catch (IOException e) {
		}
	}
}
