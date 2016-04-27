package com.sync.systems.ui;

import static com.sync.systems.Utils.TITLE;
import static com.sync.systems.Utils.authenticate;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileFilter; 

public class SyncUI extends JFrame {
	private LoginDialog loginDialog = new LoginDialog(this);
	
	 private void init(){
		 setTitle(TITLE);
	 }
	 
	 private void setSize(){
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 setSize(300, 100);
	 }
	 
	 public void showUI(){
		 init();
		 setSize();
		 loginDialog.show(true);
	 }
	 
	 public void open(){
		 loginDialog.setVisible(false);
		 this.getContentPane().removeAll();
		  	setLayout(new FlowLayout())	;
	        // set up a file picker component
	        JFilePicker filePicker = new JFilePicker("Pick a file", "Browse...");
	        filePicker.setMode(JFilePicker.MODE_SAVE);
	        filePicker.addFileTypeFilter(".jpg", "JPEG Images");
	        filePicker.addFileTypeFilter(".mp4", "MPEG-4 Videos");
	        add(filePicker);
	        this.setVisible(true);
	 }
	 
	 public static void main(String[] args) {
		new SyncUI().showUI();
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
					if (authenticate(getUsername(), getPassword())) {
					    JOptionPane.showMessageDialog(LoginDialog.this,
					            "Hi " + getUsername() + "! You have successfully logged in.",
					            "Login",
					            JOptionPane.INFORMATION_MESSAGE);
					    succeeded = true;
					    dispose();
					    open();
					} else {
					    JOptionPane.showMessageDialog(LoginDialog.this,
					            "Invalid username or password",
					            "Login",
					            JOptionPane.ERROR_MESSAGE);
					    // reset username and password
					    tfUsername.setText("");
					    pfPassword.setText("");
					    succeeded = false;

					}
				} catch (HeadlessException | RemoteException | NotBoundException e1) {
					e1.printStackTrace();
				}
            }
        };

	    public LoginDialog(Frame parent) {
	        super(parent, parent.getTitle(), true);
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
	 
	 public static class JFilePicker extends JPanel {
		    private String textFieldLabel;
		    private String buttonLabel;
		     
		    private JLabel label;
		    private JTextField textField;
		    private JButton button;
		     
		    private JFileChooser fileChooser;
		     
		    private int mode;
		    public static final int MODE_OPEN = 1;
		    public static final int MODE_SAVE = 2;
		     
		    public JFilePicker(String textFieldLabel, String buttonLabel) {
//		    	super(parent, parent.getTitle(), true);
		        this.textFieldLabel = textFieldLabel;
		        this.buttonLabel = buttonLabel;
		         
		        fileChooser = new JFileChooser();
		         
		        setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		 
		        // creates the GUI
		        label = new JLabel(textFieldLabel);
		         
		        textField = new JTextField(30);
		        button = new JButton(buttonLabel);
		         
		        button.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent evt) {
		                buttonActionPerformed(evt);            
		            }
		        });
		         
		        add(label);
		        add(textField);
		        add(button);
		         
		    }
		     
		    private void buttonActionPerformed(ActionEvent evt) {
		        if (mode == MODE_OPEN) {
		            if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
		                textField.setText(fileChooser.getSelectedFile().getAbsolutePath());
		            }
		        } else if (mode == MODE_SAVE) {
		            if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
		                textField.setText(fileChooser.getSelectedFile().getAbsolutePath());
		            }
		        }
		    }
		 
		    public void addFileTypeFilter(String extension, String description) {
		        FileTypeFilter filter = new FileTypeFilter(extension, description);
		        fileChooser.addChoosableFileFilter(filter);
		    }
		     
		    public void setMode(int mode) {
		        this.mode = mode;
		    }
		     
		    public String getSelectedFilePath() {
		        return textField.getText();
		    }
		     
		    public JFileChooser getFileChooser() {
		        return this.fileChooser;
		    }
		}
	 
	 static class FileTypeFilter extends FileFilter {
		 
		    private String extension;
		    private String description;
		     
		    public FileTypeFilter(String extension, String description) {
		        this.extension = extension;
		        this.description = description;
		    }
		     
		    @Override
		    public boolean accept(File file) {
		        if (file.isDirectory()) {
		            return true;
		        }
		        return file.getName().toLowerCase().endsWith(extension);
		    }
		     
		    public String getDescription() {
		        return description + String.format(" (*%s)", extension);
		    }
		}
}
