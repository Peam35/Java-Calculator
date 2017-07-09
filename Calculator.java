package gui;
import java.awt.*;
import java.awt.event.*;
import java.util.Iterator;

import javax.swing.*;

public class Calculator extends JFrame {
	private JButton one, two, three, four, five, six, seven, eight, nine, zero, add, subtract, multiply, divide, equal, CE, period;
	private JLabel resultLabel;
	private GridBagConstraints c;
	private int amountNums;
	private double lastNum, nextNum;
	private String operation = "";
	
	public Calculator() {
		setLayout(new GridBagLayout());
		c = new GridBagConstraints();
		c.insets = new Insets(5, 5, 5, 5);
		resultLabel = new JLabel("0.0");
		c.gridx = 2;
		c.gridy = 0;
		c.gridwidth = 5;
		c.fill = GridBagConstraints.HORIZONTAL;
		add(resultLabel, c);
		
		CE = new JButton("C/E");
		c.gridx = 3;
		c.gridy = 0;
		c.gridwidth = 1;
		add(CE, c);
		CE.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				resultLabel.setText("0.0");
				lastNum = 0;
				nextNum = 0;
				amountNums = 0;
			}
		});
		
		seven = new JButton("7");
		c.gridx = 0;
		c.gridy = 1;
		add(seven, c);
		seven.addActionListener(new number("7"));
		
		four = new JButton("4");
		c.gridy = 2;
		add(four, c);
		four.addActionListener(new number("4"));
		
		one = new JButton("1");
		c.gridy = 3;
		add(one, c);
		one.addActionListener(new number("1"));
		
		zero = new JButton("0");
		c.gridy = 4;
		add(zero, c);
		zero.addActionListener(new number("0"));
		
		eight = new JButton("8");
		c.gridx = 1;
		c.gridy = 1;
		add(eight, c);
		eight.addActionListener(new number("8"));
		
		five = new JButton("5");
		c.gridy = 2;
		add(five, c);
		five.addActionListener(new number("5"));
		
		two = new JButton("2");
		c.gridy = 3;
		add(two, c);
		two.addActionListener(new number("2"));
		
		period = new JButton(".");
		c.gridy = 4;
		add(period, c);
		period.addActionListener(new number("."));
		
		nine = new JButton("9");
		c.gridx = 2;
		c.gridy = 1;
		add(nine, c);
		nine.addActionListener(new number("9"));
		
		six = new JButton("6");
		c.gridy = 2;
		add(six, c);
		six.addActionListener(new number("6"));
		
		three = new JButton("3");
		c.gridy = 3;
		add(three, c);
		three.addActionListener(new number("3"));
		
		add = new JButton("+");
		c.gridy = 4;
		add(add, c);
		add.addActionListener(new operator("+"));
		
		divide = new JButton("/");
		c.gridx = 3;
		c.gridy = 1;
		add(divide, c);
		divide.addActionListener(new operator("/"));
		
		multiply = new JButton("*");
		c.gridy = 2;
		add(multiply, c);
		multiply.addActionListener(new operator("*"));
		
		subtract = new JButton("-");
		c.gridy = 3;
		add(subtract, c);
		subtract.addActionListener(new operator("-"));
		
		equal = new JButton("=");
		c.gridy = 4;
		add(equal, c);
		equal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (resultLabel.getText() == "") {
					resultLabel.setText("0.0");
					operation = "";
					lastNum = 0;
					return;
				} else if (operation == ""){
					return;
				} else {
					nextNum = Double.parseDouble(resultLabel.getText());
				}
				switch(operation) {
				case "*":
					resultLabel.setText(Double.toString(lastNum*nextNum));
					break;
				case "/":
					resultLabel.setText(Double.toString(lastNum/nextNum));
					break;
				case "+":
					resultLabel.setText(Double.toString(lastNum+nextNum));
					break;
				case "-":
					resultLabel.setText(Double.toString(lastNum-nextNum));
					break;
				}
				amountNums = 0;
				String newNum = "";
				for (String i : resultLabel.getText().split("")) {
					if (amountNums < 6) {
						newNum += i;
						amountNums++;
					} else {
						resultLabel.setText(newNum);
						break;
					}
				}
			}
		});
	}
	public class number implements ActionListener{
		String num;
		public number(String n) {
			num = n;
		}
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (amountNums < 6) {
				resultLabel.setText(resultLabel.getText() == "0.0" ? num : resultLabel.getText() + num);
				amountNums++;
			} 
		}
	}
	public class operator implements ActionListener {
		String operator;
		public operator(String o) {
			operator = o;
		}
		@Override
		public void actionPerformed(ActionEvent arg0) {
			lastNum = Double.parseDouble(resultLabel.getText());
			resultLabel.setText("");
			operation = operator;
			amountNums = 0;
		}
	}
	public static void main(String[] args) {
		Calculator gui = new Calculator();
		gui.setDefaultCloseOperation(EXIT_ON_CLOSE);
		gui.setSize(300, 300);
		gui.setVisible(true);
		gui.setTitle("Calculator");
	}
}

