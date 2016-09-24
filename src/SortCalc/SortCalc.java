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
	
	JCheckBox BubbleSort;
	JCheckBox InsertionSort;
	JCheckBox SelectionSort;
	
	Sorter sorter = new Sorter();
	
	JTextArea generatedArrayField;
	JTextArea sortedArrayField;
	
	JTextField SeedField;
	JTextField QuantityField;
	
	SortFrame()
	{
		setSize( DEFAULT_WIDTH, DEFAULT_HEIGHT );
		setLayout( new GridLayout(1,2) );
		
		//Left panel//
		
		//Making panel with choices
		JPanel ChoicePanel = new JPanel( new GridLayout(1, 3) );
		Border ChPanEtched = BorderFactory.createEtchedBorder();
		Border ChPanTitled = BorderFactory.createTitledBorder( ChPanEtched, "Select sorting types" );
		ChoicePanel.setBorder( ChPanTitled );
		
		//Making check boxes
		BubbleSort = new JCheckBox( "Bubble Sort" );
		BubbleSort.addItemListener( this );
		ChoicePanel.add( BubbleSort );
		//
		InsertionSort = new JCheckBox( "Insertion Sort" );
		InsertionSort.addItemListener( this );
		ChoicePanel.add( InsertionSort );
		//
		SelectionSort = new JCheckBox( "Selection Sort" );
		SelectionSort.addItemListener( this );
		ChoicePanel.add( SelectionSort );
		//
		
		//Making list with results
		resmodel = new DefaultListModel<String>();
		results = new JList<String>( resmodel );
		Border ResListTitle = BorderFactory.createTitledBorder( ChPanEtched, "Results" );
		results.setBorder( ResListTitle );
		
		//Making "Sort" button
		JButton SortButton = new JButton( "Sort" );
		SortButton.addActionListener( new SortAction() );
		
		//Making panel with seed, quantity and "generate" button
		JPanel GenPanel = new JPanel( new GridLayout(1, 3) );
		Border GenPanTitle = BorderFactory.createTitledBorder( ChPanEtched, "Generate array for sorting purposes" );
		GenPanel.setBorder( GenPanTitle );
		

		SeedField = new JTextField();
		Border SeedFieldTitle = BorderFactory.createTitledBorder( ChPanEtched, "Enter seed" );
		SeedField.setBorder( SeedFieldTitle );
		
		QuantityField = new JTextField();
		Border QuantityFieldTitle = BorderFactory.createTitledBorder( ChPanEtched, "Enter array size" );
		QuantityField.setBorder( QuantityFieldTitle );
		
		JButton GenerateButton = new JButton( "Generate" );
		GenerateButton.addActionListener( new GenerateAction() );

		GenPanel.add( SeedField );
		GenPanel.add( QuantityField );
		GenPanel.add( GenerateButton );
		
		//Making left panel
		JPanel leftPanel = new JPanel( new GridLayout( 4, 1 ) );
		
		leftPanel.add(GenPanel);
		leftPanel.add(ChoicePanel);
		leftPanel.add(SortButton);
		leftPanel.add(results);
		
		//Right panel//
		JPanel rightPanel = new JPanel( new GridLayout( 2, 1 ) );
		
		Border GenArrTitle = BorderFactory.createTitledBorder( ChPanEtched, "Generated array" );
		Border SortArrTitle = BorderFactory.createTitledBorder( ChPanEtched, "Sorted array" );
		
		generatedArrayField = new JTextArea();
		generatedArrayField.setEditable(false);
		JScrollPane generatedArrayFieldSc = new JScrollPane( generatedArrayField );
		generatedArrayFieldSc.setBorder( GenArrTitle );
		
		sortedArrayField = new JTextArea();
		sortedArrayField.setEditable(false);
		JScrollPane sortedArrayFieldSc = new JScrollPane( sortedArrayField );
		sortedArrayFieldSc.setBorder( SortArrTitle );
		
		rightPanel.add( generatedArrayFieldSc );
		rightPanel.add( sortedArrayFieldSc );
		
		add(leftPanel);
		add(rightPanel);
	}
	
	class GenerateAction implements ActionListener
	{
		public void actionPerformed( ActionEvent event )
		{
			String seed = SeedField.getText();
			String size = QuantityField.getText();
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
			if( source == BubbleSort )
				resmodel.addElement( "Bubble Sort: " );
			else if( source == InsertionSort )
				resmodel.addElement( "Insertion Sort: " );
			else if( source == SelectionSort )
				resmodel.addElement( "Selection Sort: " );
		}
		
		
		if( e.getStateChange() == ItemEvent.DESELECTED )
		{
			if( source == BubbleSort )
				resmodel.remove(find("Bubble Sort: "));
			else if( source == InsertionSort )
				resmodel.remove(find("Insertion Sort: "));
			else if( source == SelectionSort )
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
