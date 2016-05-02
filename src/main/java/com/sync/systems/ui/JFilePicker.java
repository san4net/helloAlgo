package com.sync.systems.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public  class JFilePicker extends JPanel  {
	private JLabel label;
	private JTextField textField;
	private JButton browseButton, addButton, removeButton;
	private Vector listData;
	private JFileChooser fileChooser;
	private JList listbox;
	private JScrollPane scrollPane;
	private List<ProfileListener> profileListener = new ArrayList<>();
	private int mode;
	public static final int MODE_OPEN = 1;
	public static final int MODE_SAVE = 2;

	public JFilePicker build(String buttonLabel) {
		setLayout(new BorderLayout());
		createDataEntryPanel(buttonLabel);
		add(scrollPane, BorderLayout.CENTER);
		return this;
	}

	public void createDataEntryPanel(String buttonLabel) {
		JLabel sync = new JLabel("Files under sync");
		fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		fileChooser.setAcceptAllFileFilterUsed(false);
		textField = new JTextField(10);
		browseButton = new JButton(buttonLabel);
		browseButton.addActionListener(e -> {
			buttonActionPerformed(e);
		});

		addButton = new JButton("add");
		addButton.addActionListener(e -> {
			listData.add(textField.getText());
			updateScrollPane();
			callBack(listData);
		});

		removeButton = new JButton("Delete");
		removeButton.addActionListener(e -> {
			int selection = listbox.getSelectedIndex();
			if (selection >= 0) {
				// Add this item to the list and refresh
				listData.removeElementAt(selection);
				updateScrollPane();
				// As a nice touch, select the next item
				if (selection >= listData.size())
					selection = listData.size() - 1;
				listbox.setSelectedIndex(selection);
			}
		});

		listData = new Vector();
		listbox = new JList(listData);
		scrollPane = new JScrollPane();
		scrollPane.getViewport().add(listbox);
		// Create a panel to hold all other components
		JPanel dataPanel = new JPanel();
		dataPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		dataPanel.add(removeButton);
		dataPanel.add(textField);
		dataPanel.add(browseButton);
		dataPanel.add(addButton);
		add(dataPanel, BorderLayout.SOUTH);
		add(sync, BorderLayout.NORTH);
	}
	
	public void updateData(Vector listData){
		this.listData = listData; 
	}
	
	public void updateScrollPane(){
		listbox.setListData(listData);
		scrollPane.revalidate();
		scrollPane.repaint();
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

	public void setMode(int mode) {
		this.mode = mode;
	}

	public String getSelectedFilePath() {
		return textField.getText();
	}

	public JFileChooser getFileChooser() {
		return this.fileChooser;
	}
	
	public void addProfileListener(ProfileListener profileListener){
		this.profileListener.add(profileListener);
	}
	
	private void callBack(Vector data){
		List l =  Arrays.asList(data.toArray());
		for(ProfileListener p : profileListener){
			try {
				p.onProfileUpdate(data);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

