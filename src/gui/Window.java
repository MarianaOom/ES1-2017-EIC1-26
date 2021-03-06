package gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import antiSpamFilter.Anti_Spam_Filter;
import antiSpamFilter.Rules;

public class Window extends JFrame {

	private JPanel contentPane;
	private JTextField rules_path;
	private JTextField ham_path;
	private JTextField spam_path;
	private JButton btnHam_1;
	private JButton btnSpam;
	private JLabel lblRulesPath;
	private JLabel lblHamPath;
	private JLabel lblSpamPath;
	private JComboBox dropDown;
	private JTextField textField_3;
	private JTextField manual_FN;
	private JTextField manual_FP;
	private JLabel lblFn;
	private JLabel lblFp;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JComboBox dropDown2;
	private JLabel label;
	private JLabel label_1;
	private JTextField automatic_FN;
	private JTextField automatic_FP;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private JLabel lblGeradorManual;
	private JLabel lblGeradorAutomtico;
	private Anti_Spam_Filter filter;
	private int FN;
	private int FP;

	/**
	 * @param fil
	 *            Create the frame.
	 */
	public Window(Anti_Spam_Filter fil) {
		filter = fil;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 785, 458);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		lblRulesPath = new JLabel("Rules Path");
		GridBagConstraints gbc_lblRulesPath = new GridBagConstraints();
		gbc_lblRulesPath.insets = new Insets(0, 0, 5, 5);
		gbc_lblRulesPath.gridx = 1;
		gbc_lblRulesPath.gridy = 1;
		contentPane.add(lblRulesPath, gbc_lblRulesPath);

		rules_path = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.anchor = GridBagConstraints.SOUTH;
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 1;
		contentPane.add(rules_path, gbc_textField);
		rules_path.setColumns(10);
		Window w = this;
		JButton btnHam = new JButton("Rules");
		btnHam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fc = new JFileChooser();
				fc.showOpenDialog(w);
				if (fc.getSelectedFile() != null) {
					rules_path.setText(fc.getSelectedFile().getName());
					fc.setVisible(true);
					fil.prepareRules(fc.getSelectedFile().getAbsolutePath());
					for (Rules rule : filter.getRules()) {
						dropDown.addItem(rule.getName());
						dropDown2.addItem(rule.getName());
					}
					rules_path.setBackground(Color.GREEN);

				} else
					rules_path.setBackground(Color.RED);
			}
		});
		GridBagConstraints gbc_btnHam = new GridBagConstraints();
		gbc_btnHam.insets = new Insets(0, 0, 5, 0);
		gbc_btnHam.gridx = 3;
		gbc_btnHam.gridy = 1;
		contentPane.add(btnHam, gbc_btnHam);

		lblHamPath = new JLabel("Ham Path");
		GridBagConstraints gbc_lblHamPath = new GridBagConstraints();
		gbc_lblHamPath.insets = new Insets(0, 0, 5, 5);
		gbc_lblHamPath.gridx = 1;
		gbc_lblHamPath.gridy = 2;
		contentPane.add(lblHamPath, gbc_lblHamPath);

		ham_path = new JTextField();
		ham_path.setColumns(10);
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 2;
		gbc_textField_1.gridy = 2;
		contentPane.add(ham_path, gbc_textField_1);

		btnHam_1 = new JButton("Ham");
		GridBagConstraints gbc_btnHam_1 = new GridBagConstraints();
		gbc_btnHam_1.insets = new Insets(0, 0, 5, 0);
		gbc_btnHam_1.gridx = 3;
		gbc_btnHam_1.gridy = 2;
		contentPane.add(btnHam_1, gbc_btnHam_1);
		btnHam_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				fc.showOpenDialog(w);
				if (fc.getSelectedFile() != null) {
					ham_path.setText(fc.getSelectedFile().getName());
					fc.setVisible(true);
					fil.readHam(fc.getSelectedFile().getAbsolutePath());
					ham_path.setBackground(Color.GREEN);
				} else
					ham_path.setBackground(Color.RED);

			}
		});

		lblSpamPath = new JLabel("Spam Path");
		GridBagConstraints gbc_lblSpamPath = new GridBagConstraints();
		gbc_lblSpamPath.insets = new Insets(0, 0, 5, 5);
		gbc_lblSpamPath.gridx = 1;
		gbc_lblSpamPath.gridy = 3;
		contentPane.add(lblSpamPath, gbc_lblSpamPath);

		spam_path = new JTextField();
		spam_path.setColumns(10);
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 2;
		gbc_textField_2.gridy = 3;
		contentPane.add(spam_path, gbc_textField_2);

		btnSpam = new JButton("Spam");
		GridBagConstraints gbc_btnSpam = new GridBagConstraints();
		gbc_btnSpam.insets = new Insets(0, 0, 5, 0);
		gbc_btnSpam.gridx = 3;
		gbc_btnSpam.gridy = 3;
		contentPane.add(btnSpam, gbc_btnSpam);
		btnSpam.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				fc.showOpenDialog(w);
				if (fc.getSelectedFile() != null) {
					spam_path.setText(fc.getSelectedFile().getName());
					fc.setVisible(true);
					fil.readSpam(fc.getSelectedFile().getAbsolutePath());
					spam_path.setBackground(Color.GREEN);
				} else
					spam_path.setBackground(Color.RED);

			}
		});

		lblGeradorManual = new JLabel("Gerador Manual");
		GridBagConstraints gbc_lblGeradorManual = new GridBagConstraints();
		gbc_lblGeradorManual.insets = new Insets(0, 0, 5, 5);
		gbc_lblGeradorManual.gridx = 1;
		gbc_lblGeradorManual.gridy = 5;
		contentPane.add(lblGeradorManual, gbc_lblGeradorManual);

		dropDown = new JComboBox<>();

		dropDown.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				textField_3.setText("" + filter.getRules().get(dropDown.getSelectedIndex()).getWeight());

			}
		});
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 6;
		contentPane.add(dropDown, gbc_comboBox);

		textField_3 = new JTextField();
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.insets = new Insets(0, 0, 5, 5);
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridx = 2;
		gbc_textField_3.gridy = 6;
		contentPane.add(textField_3, gbc_textField_3);
		textField_3.setColumns(10);
		textField_3.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				e.consume();
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				keyHandler(e);
			}
		});

		btnNewButton = new JButton("Evaluate");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filter.evaluate();
				setManualResults(filter.getFP(), filter.getFN());
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 3;
		gbc_btnNewButton.gridy = 6;
		contentPane.add(btnNewButton, gbc_btnNewButton);

		lblFn = new JLabel("FN:");
		GridBagConstraints gbc_lblFn = new GridBagConstraints();
		gbc_lblFn.insets = new Insets(0, 0, 5, 5);
		gbc_lblFn.anchor = GridBagConstraints.EAST;
		gbc_lblFn.gridx = 1;
		gbc_lblFn.gridy = 7;
		contentPane.add(lblFn, gbc_lblFn);

		manual_FN = new JTextField();
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.insets = new Insets(0, 0, 5, 5);
		gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_4.gridx = 2;
		gbc_textField_4.gridy = 7;
		contentPane.add(manual_FN, gbc_textField_4);
		manual_FN.setColumns(10);

		btnNewButton_1 = new JButton("Save");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fil.printResults();
			}
		});
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_1.gridx = 3;
		gbc_btnNewButton_1.gridy = 7;
		contentPane.add(btnNewButton_1, gbc_btnNewButton_1);

		lblFp = new JLabel("FP:");
		GridBagConstraints gbc_lblFp = new GridBagConstraints();
		gbc_lblFp.insets = new Insets(0, 0, 5, 5);
		gbc_lblFp.anchor = GridBagConstraints.EAST;
		gbc_lblFp.gridx = 1;
		gbc_lblFp.gridy = 8;
		contentPane.add(lblFp, gbc_lblFp);

		manual_FP = new JTextField();
		GridBagConstraints gbc_textField_5 = new GridBagConstraints();
		gbc_textField_5.insets = new Insets(0, 0, 5, 5);
		gbc_textField_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_5.gridx = 2;
		gbc_textField_5.gridy = 8;
		contentPane.add(manual_FP, gbc_textField_5);
		manual_FP.setColumns(10);

		lblGeradorAutomtico = new JLabel("Gerador Automatico");
		GridBagConstraints gbc_lblGeradorAutomtico = new GridBagConstraints();
		gbc_lblGeradorAutomtico.insets = new Insets(0, 0, 5, 5);
		gbc_lblGeradorAutomtico.gridx = 1;
		gbc_lblGeradorAutomtico.gridy = 10;
		contentPane.add(lblGeradorAutomtico, gbc_lblGeradorAutomtico);

		dropDown2 = new JComboBox();
		for (Rules rule : filter.getRules())
			dropDown2.addItem(rule.getName());
		dropDown2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				textField_3.setText("" + filter.getRules().get(dropDown2.getSelectedIndex()).getWeight());

			}
		});
		GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
		gbc_comboBox_1.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_1.gridx = 1;
		gbc_comboBox_1.gridy = 11;
		contentPane.add(dropDown2, gbc_comboBox_1);

		btnNewButton_3 = new JButton("Genarate");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fil.launcAuto();
				fil.automaticEvaluation();
			}
		});
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_3.gridx = 3;
		gbc_btnNewButton_3.gridy = 11;
		contentPane.add(btnNewButton_3, gbc_btnNewButton_3);

		label = new JLabel("FN:");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.EAST;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 1;
		gbc_label.gridy = 12;
		contentPane.add(label, gbc_label);

		automatic_FN = new JTextField();
		automatic_FN.setColumns(10);
		GridBagConstraints gbc_textField_6 = new GridBagConstraints();
		gbc_textField_6.insets = new Insets(0, 0, 5, 5);
		gbc_textField_6.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_6.gridx = 2;
		gbc_textField_6.gridy = 12;
		contentPane.add(automatic_FN, gbc_textField_6);

		lblGeradorAutomtico = new JLabel("Gerador Autom\u00E1tico");
		gbc_lblGeradorAutomtico = new GridBagConstraints();
		gbc_lblGeradorAutomtico.insets = new Insets(0, 0, 5, 5);
		gbc_lblGeradorAutomtico.gridx = 1;
		gbc_lblGeradorAutomtico.gridy = 10;
		contentPane.add(lblGeradorAutomtico, gbc_lblGeradorAutomtico);

		btnNewButton_3 = new JButton("Genarate");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fil.automaticEvaluation();
			}
		});
		gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_3.gridx = 3;
		gbc_btnNewButton_3.gridy = 11;
		contentPane.add(btnNewButton_3, gbc_btnNewButton_3);

		btnNewButton_2 = new JButton("Save");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fil.printResults();
			}
		});
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_2.gridx = 3;
		gbc_btnNewButton_2.gridy = 12;
		contentPane.add(btnNewButton_2, gbc_btnNewButton_2);

		label_1 = new JLabel("FP:");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.anchor = GridBagConstraints.EAST;
		gbc_label_1.insets = new Insets(0, 0, 0, 5);
		gbc_label_1.gridx = 1;
		gbc_label_1.gridy = 13;
		contentPane.add(label_1, gbc_label_1);

		automatic_FP = new JTextField();
		automatic_FP.setColumns(10);
		GridBagConstraints gbc_textField_7 = new GridBagConstraints();
		gbc_textField_7.insets = new Insets(0, 0, 0, 5);
		gbc_textField_7.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_7.gridx = 2;
		gbc_textField_7.gridy = 13;
		contentPane.add(automatic_FP, gbc_textField_7);
		rules_path.setEditable(false);
		ham_path.setEditable(false);
		spam_path.setEditable(false);
		manual_FN.setEditable(false);
		manual_FP.setEditable(false);
		automatic_FN.setEditable(false);
		automatic_FP.setEditable(false);
	}

	/**
	 * @param e
	 *            This method handles the manual insertion of weight, so it can
	 *            only be number or colon
	 */
	private void keyHandler(KeyEvent e) {
		if (e.getKeyCode() != KeyEvent.VK_ENTER) {
			if (Character.isDigit(e.getKeyChar()) || e.getKeyCode() == KeyEvent.VK_PERIOD
					|| e.getKeyCode() == KeyEvent.VK_COLON || e.getKeyCode() == KeyEvent.VK_MINUS
					|| e.getKeyCode() == KeyEvent.VK_SUBTRACT) {
				System.out.println(e.getKeyChar());
				textField_3.setText(textField_3.getText() + e.getKeyChar());
			} else
				textField_3.setBackground(Color.RED);
		} else {
			Double value = Double.parseDouble(textField_3.getText());
			if (value < -5)
				value = -5.0;
			if (value > 5)
				value = 5.0;
			textField_3.setBackground(Color.GREEN);
			filter.getRules().get(dropDown.getSelectedIndex()).setWeight(value);
			System.out.println(value);
		}
	}

	/**
	 * @param p
	 *            false positives
	 * @param n
	 *            false negatives
	 * 
	 *            This method places the manually generated false negatives and
	 *            positives in the frame
	 */
	public void setManualResults(int p, int n) {
		FP = p;
		FN = n;
		manual_FN.setText("" + FN);
		manual_FP.setText("" + FP);
	}

	/**
	 * @param p
	 *            false positives
	 * @param n
	 *            false negatives
	 * 
	 *            This method places the auomatically generated false negatives
	 *            and positives in the frame
	 */
	public void setAutomaticResults(int p, int n) {
		FP = p;
		FN = n;
		automatic_FN.setText("" + FN);
		automatic_FP.setText("" + FP);
	}

}
