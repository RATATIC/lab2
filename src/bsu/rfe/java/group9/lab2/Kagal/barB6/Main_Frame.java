package bsu.rfe.java.group9.lab2.Kagal.barB6;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

@SuppressWarnings("serial")

public class Main_Frame  extends JFrame
{
	
	private static final int WIDTH = 400;
	private static final int HEIGHT = 400;
	
	private Double mem1 = new Double(0);
	private Double mem2 = new Double(0);
	private Double mem3 = new Double(0);

	
	private JTextField textFieldX;
	private JTextField textFieldY;
	private JTextField textFieldZ;
	
	private JTextField textFieldResult;
	private JTextField memoryTextField;
	
	
	private ButtonGroup radioButtons = new ButtonGroup();
	private ButtonGroup radioMemoryButtons = new ButtonGroup();
	
	private Box hBoxMemoryType = Box.createHorizontalBox();
	private Box hboxFormulaType = Box.createHorizontalBox();
	

	private int formulaId = 1;
	private int memoryId= 1;
	
	public double calculate1(double x, double y, double z)
	{
		return Math.pow( Math.log((1+z)*(1+z))+Math.cos(3.14*y*y*y), 0.25) / Math.pow(Math.cos(Math.pow(2.71,x))+Math.sqrt(1*x)+Math.pow( 2.71,x*x),Math.sin(y));
	}
	
	public double calculate2(double x, double y, double z) 
	{
		return (Math.tan(x*x)+Math.sqrt(y))/(z*Math.log(x+y));
	}
	
	private void addRadioButton(String buttonName, final int formulaId) 
	{
		
		JRadioButton button = new  JRadioButton(buttonName);
		
		button.addActionListener(new ActionListener()
		{
			
			@Override
			
			public void actionPerformed(ActionEvent ev)
			{
				Main_Frame.this.formulaId = formulaId;
			}
		});
		
		radioButtons.add(button); 
		hboxFormulaType.add(button);
	}
	
	private void addMemoryRadioButton (String buttonName, final int memoryId)	
	{
		JRadioButton button = new JRadioButton(buttonName);
		
		button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent event)
			{
				Main_Frame.this.memoryId = memoryId;
				if (memoryId == 1)	memoryTextField.setText(mem1.toString());
				if (memoryId == 2)	memoryTextField.setText(mem2.toString());
				if (memoryId == 3)	memoryTextField.setText(mem3.toString());
			}
		});
		
		radioMemoryButtons.add(button);
		hBoxMemoryType.add(button);
	}
	
	
	public Main_Frame() 
	{
		super("Подсчёт значений из формулы");
		
		setSize(WIDTH,HEIGHT);
		
		Toolkit kit = Toolkit.getDefaultToolkit();
		
		setLocation((kit.getScreenSize().width - WIDTH)/2,
				(kit.getScreenSize().height - HEIGHT)/2);
		hboxFormulaType.add(Box.createHorizontalGlue());
		
		addRadioButton("Формула 1", 1);
		addRadioButton("Формула 2 ", 2);
		
		radioButtons.setSelected(radioButtons.getElements().nextElement().getModel(), true);
		
		hboxFormulaType.add(Box.createHorizontalGlue());
		hboxFormulaType.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
		
		JLabel labelForX = new JLabel("X: ");
		textFieldX = new JTextField("0", 10);
		textFieldX.setMaximumSize(textFieldX.getPreferredSize());
		
		JLabel labelForY = new JLabel("Y: ");
		textFieldY = new JTextField("0", 10);
		textFieldY.setMaximumSize(textFieldY.getPreferredSize());
		
		JLabel labelForZ = new JLabel("Z: ");
		textFieldZ  = new JTextField("0", 10);
		textFieldZ.setMaximumSize(textFieldZ.getPreferredSize());
		
		Box hboxVariables = Box.createHorizontalBox();
		hboxVariables.setBorder(BorderFactory.createLineBorder(Color.RED));
		
		hboxVariables.add(Box.createHorizontalGlue());
		hboxVariables.add(labelForX);
		hboxVariables.add(Box.createHorizontalStrut(10));
		hboxVariables.add(textFieldX);
		
		hboxVariables.add(Box.createHorizontalStrut(100));
		hboxVariables.add(labelForY);
		hboxVariables.add(Box.createHorizontalStrut(10));
		hboxVariables.add(textFieldY);
		hboxVariables.add(Box.createHorizontalGlue());
		
		hboxVariables.add(Box.createHorizontalStrut(100));
		hboxVariables.add(Box.createHorizontalGlue());
		hboxVariables.add(labelForZ);
		hboxVariables.add(Box.createHorizontalStrut(10));
		hboxVariables.add(textFieldZ);
		
		JLabel labelForResult = new JLabel("Результат: ");
		textFieldResult = new JTextField("0", 10);
		textFieldResult.setMaximumSize(textFieldResult.getPreferredSize());
		
		Box hboxResult = Box.createHorizontalBox();
		hboxResult.add(Box.createHorizontalGlue());
		hboxResult.add(labelForResult);
		hboxResult.add(Box.createHorizontalStrut(10));
		hboxResult.add(textFieldResult);
		hboxResult.add(Box.createHorizontalGlue());
		hboxResult.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		
		JButton buttonCalc = new JButton ("Вычислить");
		
		buttonCalc.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				try 
				{
					double x = Double.parseDouble(textFieldX.getText());
					double y = Double.parseDouble(textFieldY.getText());
					double z = Double.parseDouble(textFieldZ.getText());
					double result;
					
					if (formulaId == 1)
						result = calculate1(x,y,z);
					else 
						result = calculate2(x,y,z);
					
					textFieldResult.setText(String.valueOf(result));
					
				} 
				catch (NumberFormatException ex) 
				{
					JOptionPane.showMessageDialog(Main_Frame.this, 
							"Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		JButton buttonReset = new JButton ("Очистить поля");
		
		buttonReset.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent ev)
			{
				textFieldX.setText("0");
				textFieldY.setText("0");
				textFieldZ.setText("0");
				textFieldResult.setText("0");
			}
		});
		
		Box hboxButtons = Box.createHorizontalBox();
		hboxButtons.add(Box.createHorizontalGlue());
		hboxButtons.add(buttonCalc);
		hboxButtons.add(Box.createHorizontalStrut(30));
		hboxButtons.add(buttonReset); // RESET BUTTON
		hboxButtons.add(Box.createHorizontalGlue());
		hboxButtons.setBorder(BorderFactory.createLineBorder(Color.GREEN));
		
		
		hBoxMemoryType.add(Box.createHorizontalGlue());
		
		addMemoryRadioButton("Память 1", 1);
		addMemoryRadioButton("Память 2", 2);
		addMemoryRadioButton("Память 3", 3);
		
		radioMemoryButtons.setSelected(radioMemoryButtons.getElements().nextElement().getModel(), true); 
		hBoxMemoryType.add(Box.createHorizontalGlue()); 
		hBoxMemoryType.setBorder(BorderFactory.createLineBorder(Color.YELLOW)); 

		
		JButton buttonMC = new JButton("MC");
		buttonMC.addActionListener(new ActionListener()	
		{
			public void actionPerformed(ActionEvent event)
			{
				
				if (memoryId == 1)	mem1 = (double) 0;
				if (memoryId == 2)	mem2 = (double) 0;
				if (memoryId == 3)	mem3 = (double) 0;
				memoryTextField.setText("0.0");
			}
		});
		
		
		memoryTextField = new JTextField("0.0", 15);
		memoryTextField.setMaximumSize(memoryTextField.getPreferredSize());
		
		Box hBoxMemoryField = Box.createHorizontalBox();
		hBoxMemoryField.add(Box.createHorizontalGlue());
		hBoxMemoryField.add(memoryTextField);
		hBoxMemoryField.add(Box.createHorizontalGlue());
		
		JButton buttonMp = new JButton("M+");
		buttonMp.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				try
				{
				Double result = Double.parseDouble(textFieldResult.getText());
				
				if (memoryId == 1) 	{mem1 += result;memoryTextField.setText(mem1.toString());}
				if (memoryId == 2)	{mem2 += result;memoryTextField.setText(mem2.toString());}
				if (memoryId == 3)	{mem3 += result;memoryTextField.setText(mem3.toString());}
			
				}catch (NumberFormatException ex) 
					{ 
					JOptionPane.showMessageDialog(Main_Frame.this, 
						"Ошибка в формате записи числа с плавающей точкой", "" +
								"Ошибочный формат числа", JOptionPane.WARNING_MESSAGE);
					}
			}
		});
		
		
		Box hBoxControlButtons = Box.createHorizontalBox();
		hBoxControlButtons.add(Box.createHorizontalGlue());
		hBoxControlButtons.add(buttonMC);
		hBoxControlButtons.add(Box.createHorizontalStrut(30));
		hBoxControlButtons.add(buttonMp);
		hBoxControlButtons.add(Box.createHorizontalGlue());

		
		Box contentBox = Box.createVerticalBox();
		contentBox.add(hboxFormulaType);
		contentBox.add(hboxVariables);
		contentBox.add(hboxResult);
		contentBox.add(hboxButtons);
		contentBox.add(hBoxMemoryType); 
		contentBox.add(hBoxControlButtons);
		contentBox.add(hBoxMemoryField); 
		contentBox.add(Box.createVerticalGlue());
		getContentPane().add(contentBox, BorderLayout.CENTER);		
	}
}