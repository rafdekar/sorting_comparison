package SortCalc;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import SortCalc.SortClasses.Sort;
import SortCalc.SortClasses.Sorter;

public class SortCalc
{
	public static void main(String[] args)
	{
		EventQueue.invokeLater( new Runnable()
		{
			public void run()
			{
				SortFrame frame = new SortFrame();
				frame.setTitle( "Sorting methods comparison");
				frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
				frame.setVisible( true );
			}
		});
	}
}

class SortFrame extends JFrame implements ItemListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_WIDTH = 800;
	private static final int DEFAULT_HEIGHT = 400;
	
	JList<String> results;
	DefaultListModel<String> resmodel;
	
	JCheckBox bubbleSort;
	JCheckBox insertionSort;
	JCheckBox selectionSort;
	
	Sorter sorter = new Sorter();
	
	JTextArea generatedArrayField;
	JTextArea sortedArrayField;
	
	JTextField seedField;
	JTextField quantityField;
	
	SortFrame()
	{
		setSize( DEFAULT_WIDTH, DEFAULT_HEIGHT );
		setLayout( new GridLayout(1,2) );
		
		//Left panel//
		
		//Making panel with choices
		JPanel choicePanel = new JPanel( new GridLayout(1, 3) );
		Border chPanEtched = BorderFactory.createEtchedBorder();
		Border chPanTitled = BorderFactory.createTitledBorder( chPanEtched, "Select sorting types" );
		choicePanel.setBorder( chPanTitled );
		
		//Making check boxes
		bubbleSort = new JCheckBox( "Bubble Sort" );
		bubbleSort.addItemListener( this );
		choicePanel.add( bubbleSort );
		//
		insertionSort = new JCheckBox( "Insertion Sort" );
		insertionSort.addItemListener( this );
		choicePanel.add( insertionSort );
		//
		selectionSort = new JCheckBox( "Selection Sort" );
		selectionSort.addItemListener( this );
		choicePanel.add( selectionSort );
		//
		
		//Making list with results
		resmodel = new DefaultListModel<String>();
		results = new JList<String>( resmodel );
		JScrollPane resultsSc = new JScrollPane( results );
		Border resListTitle = BorderFactory.createTitledBorder( chPanEtched, "Results" );
		resultsSc.setBorder( resListTitle );
		
		//Making "Sort" button
		JButton sortButton = new JButton( "Sort" );
		sortButton.addActionListener( new SortAction() );
		
		//Making panel with seed, quantity and "generate" button
		JPanel genPanel = new JPanel( new GridLayout(1, 3) );
		Border genPanTitle = BorderFactory.createTitledBorder( chPanEtched, "Generate array for sorting purposes" );
		genPanel.setBorder( genPanTitle );
		

		seedField = new JTextField();
		Border seedFieldTitle = BorderFactory.createTitledBorder( chPanEtched, "Enter seed" );
		seedField.setBorder( seedFieldTitle );
		
		quantityField = new JTextField();
		Border quantityFieldTitle = BorderFactory.createTitledBorder( chPanEtched, "Enter array size" );
		quantityField.setBorder( quantityFieldTitle );
		
		JButton generateButton = new JButton( "Generate" );
		generateButton.addActionListener( new GenerateAction() );

		genPanel.add( seedField );
		genPanel.add( quantityField );
		genPanel.add( generateButton );
		
		//Making left panel
		JPanel leftPanel = new JPanel( new GridLayout( 4, 1 ) );
		
		leftPanel.add(genPanel);
		leftPanel.add(choicePanel);
		leftPanel.add(sortButton);
		leftPanel.add(resultsSc);
		
		//Right panel//
		JPanel rightPanel = new JPanel( new GridLayout( 2, 1 ) );
		
		Border genArrTitle = BorderFactory.createTitledBorder( chPanEtched, "Generated array" );
		Border sortArrTitle = BorderFactory.createTitledBorder( chPanEtched, "Sorted array" );
		
		generatedArrayField = new JTextArea();
		generatedArrayField.setEditable(false);
		JScrollPane generatedArrayFieldSc = new JScrollPane( generatedArrayField );
		generatedArrayFieldSc.setBorder( genArrTitle );
		
		sortedArrayField = new JTextArea();
		sortedArrayField.setEditable(false);
		JScrollPane sortedArrayFieldSc = new JScrollPane( sortedArrayField );
		sortedArrayFieldSc.setBorder( sortArrTitle );
		
		rightPanel.add( generatedArrayFieldSc );
		rightPanel.add( sortedArrayFieldSc );
		
		add(leftPanel);
		add(rightPanel);
	}
	
	class GenerateAction implements ActionListener
	{
		public void actionPerformed( ActionEvent event )
		{
			String seed = seedField.getText();
			String size = quantityField.getText();
			try
			{
				int seedInt = Integer.parseInt( seed );
				int sizeInt = Integer.parseInt( size );
				if( seedInt > 0 && sizeInt > 0 )
				{
					sorter.drawArray( sizeInt, seedInt );
					writeGenArr();			
				} else JOptionPane.showMessageDialog(null, "Only numbers > 0" );
			}
			catch( NumberFormatException e )
			{
				JOptionPane.showMessageDialog(null, "Wrong input (max value = " + Integer.MAX_VALUE + ")" );
			}
		}
	}
	
	class SortAction implements ActionListener
	{
		public void actionPerformed( ActionEvent event )
		{
			for( int i = 0; i < resmodel.getSize(); i++ )
			{
				String temp = (String) resmodel.getElementAt(i);
				String result = sorter.sortTime( temp );
				resmodel.setElementAt( result, i );
			}
			writeSortArr();
		}
	}
	
	public void itemStateChanged(ItemEvent e)
	{
		Object source = e.getItemSelectable();
		
		if( e.getStateChange() == ItemEvent.SELECTED )
		{
			if( source == bubbleSort )
				resmodel.addElement( "Bubble Sort: " );
			else if( source == insertionSort )
				resmodel.addElement( "Insertion Sort: " );
			else if( source == selectionSort )
				resmodel.addElement( "Selection Sort: " );
		}
		
		
		if( e.getStateChange() == ItemEvent.DESELECTED )
		{
			if( source == bubbleSort )
				resmodel.remove(find("Bubble Sort: "));
			else if( source == insertionSort )
				resmodel.remove(find("Insertion Sort: "));
			else if( source == selectionSort )
				resmodel.remove(find("Selection Sort: "));
		}
	}
	
	public int find( String word )
	{
		for( int i = 0; i < resmodel.getSize(); i++ )
		{
			if( resmodel.elementAt( i ).charAt(0) == word.charAt(0) )
				return i;
		}
		return 0;
	}
	
	public void writeGenArr()
	{
		String temp = "";
		for( int i = 0; i < sorter.getRandoms().length; i++ )
			temp += sorter.getRandoms()[i] + "\n";
		generatedArrayField.setText( temp );
	}
	
	public void writeSortArr()
	{
		String temp = "";
		for( int i = 0; i < Sort.getArr().length; i++ )
			temp += Sort.getArr()[i] + "\n";
		sortedArrayField.setText( temp );
	}
}
