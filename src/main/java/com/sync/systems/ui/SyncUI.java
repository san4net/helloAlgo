package com.sync.systems.ui;

import static com.sync.systems.Utils.TITLE;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import com.sync.systems.BlockingClient;
import com.sync.systems.Profile.Location;
import com.sync.systems.message.ProfileUpdate;
import com.sync.systems.message.UserProfile;

public class SyncUI extends JFrame implements ProfileListener<String> {
	// client holder
	ExecutorService service = Executors.newFixedThreadPool(1);
	private BlockingClient clientHolder = null;
	private LoginDialog loginDialog = new LoginDialog(this);
	private JFilePicker filePicker;

	private void init() {
		setTitle(TITLE);
	}

	private void updateSize(int width, int height) {
		setSize(width, height);
		this.setLocationRelativeTo(null);
	}

	public void showUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		init();
		updateSize(300, 100);
		loginDialog.show(true);
	}

	public void afterLogin() throws IOException {
		dispose();
		createAndShowFilePicker();
		update((UserProfile) clientHolder.getProfile());
	}

	public void createAndShowFilePicker() {
		createFilePicker();
		showFilePicker();
	}
	
	private void createFilePicker() {
		filePicker = new JFilePicker().build("Browse...");
		filePicker.setMode(JFilePicker.MODE_SAVE);
		filePicker.addProfileListener(this);
	}

	public void showFilePicker() {
		loginDialog.setVisible(false);
		this.getContentPane().removeAll();
		add(filePicker);
		updateSize(400, 400);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new SyncUI().showUI();
	}

	public void update(UserProfile message) {
		Set<Location> locs = message.getLocations();
		Vector listData = new Vector<>(locs.size());
		for(Location l : locs){
			listData.addElement(l.getLocation());
		}
		filePicker.updateData(listData);
		filePicker.updateScrollPane();
	}

	class LoginDialog extends JDialog {
		private JTextField tfUsername;
		private JPasswordField pfPassword;
		private JLabel lbUsername;
		private JLabel lbPassword;
		private JButton btnLogin;
		private JButton btnCancel;
		private boolean succeeded;

		private ActionListener loginListener = new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					clientHolder = BlockingClient.create(tfUsername.getText().trim(),
							new String(pfPassword.getPassword()));
					if (clientHolder.validateLogin()) {
						JOptionPane.showMessageDialog(LoginDialog.this,
								"Hi " + getUsername() + "! You have successfully logged in.", "Login to " + TITLE,
								JOptionPane.INFORMATION_MESSAGE);
						succeeded = true;
						afterLogin();
					} else {
						JOptionPane.showMessageDialog(LoginDialog.this, "Invalid username or password", "Login",
								JOptionPane.ERROR_MESSAGE);
						// reset username and password
						tfUsername.setText("");
						pfPassword.setText("");
						succeeded = false;

					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		};

		public LoginDialog(Frame parent) {
			super(parent,  "Login to"+TITLE, true);
			JPanel panel = new JPanel(new GridBagLayout());
			GridBagConstraints cs = new GridBagConstraints();
			cs.fill = GridBagConstraints.HORIZONTAL;

			lbUsername = new JLabel("Username: ");
			cs.gridx = 0;
			cs.gridy = 0;
			cs.gridwidth = 1;
			panel.add(lbUsername, cs);

			tfUsername = new JTextField(20);
			cs.gridx = 1;
			cs.gridy = 0;
			cs.gridwidth = 2;
			panel.add(tfUsername, cs);

			lbPassword = new JLabel("Password: ");
			cs.gridx = 0;
			cs.gridy = 1;
			cs.gridwidth = 1;
			panel.add(lbPassword, cs);

			pfPassword = new JPasswordField(20);
			cs.gridx = 1;
			cs.gridy = 1;
			cs.gridwidth = 2;
			panel.add(pfPassword, cs);
			panel.setBorder(new LineBorder(Color.GRAY));

			btnLogin = new JButton("Login");
			btnLogin.addActionListener(loginListener);

			btnCancel = new JButton("Cancel");
			btnCancel.addActionListener(e -> dispose());

			JPanel bp = new JPanel();
			bp.add(btnLogin);
			bp.add(btnCancel);

			getContentPane().add(panel, BorderLayout.CENTER);
			getContentPane().add(bp, BorderLayout.PAGE_END);
			pack();
			setResizable(false);
			setLocationRelativeTo(parent);
		}

		public String getUsername() {
			return tfUsername.getText().trim();
		}

		public String getPassword() {
			return new String(pfPassword.getPassword());
		}

		public boolean isSucceeded() {
			return succeeded;
		}
	}

	@Override
	public void onProfileUpdate(List<String> locations) throws IOException {
		clientHolder.send(ProfileUpdate.create(clientHolder.getUserName(), locations));
	}
}
