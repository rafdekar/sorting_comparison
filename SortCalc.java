package SortCalc;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListModel;
import javax.swing.border.Border;

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
	private static final int DEFAULT_WIDTH = 400;
	private static final int DEFAULT_HEIGHT = 400;
	
	DefaultListModel resmodel;
	
	JCheckBox BubbleSort;
	JCheckBox InsertionSort;
	JCheckBox SelectionSort;
	
	SortFrame()
	{
		setSize( DEFAULT_WIDTH, DEFAULT_HEIGHT );
		setLayout( new GridLayout(3,1) );
		// Making panel with choices
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
		resmodel = new DefaultListModel();
		JList results = new JList( resmodel );
		
		//Making "Sort" button
		JButton SortButton = new JButton( "Sort" );
		SortButton.addActionListener( new SortAction() );
		
		
		
		
		add(ChoicePanel);
		add(results);
		add(SortButton);
	}
	
	class SortAction implements ActionListener
	{
		public void actionPerformed( ActionEvent event )
		{
			
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
			if( resmodel.elementAt( i ) == word )
				return i;
		}
		return 0;
	}
}

