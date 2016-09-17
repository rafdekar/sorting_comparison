package SortCalc;

import java.util.Random;

public class Sorter
{
	private static final int size = 100010;
	private int[] randoms;
	Sorter()
	{
		Random rand = new Random();
		randoms = rand.ints( size ).toArray();
	}
	
	public String sortTime( String sorttype )
	{
		if( sorttype.charAt(0) == 'B' )
			return "Bubble Sort: " + Float.toString( new BubbleSort( randoms ).sort() ) + " seconds";
		else if( sorttype.charAt(0) == 'I' )
			return "Insertion Sort: " + Float.toString( new InsertionSort( randoms ).sort() ) + " seconds";
		else if( sorttype.charAt(0) == 'S' )
			return "Selection Sort: " + Float.toString( new SelectionSort( randoms ).sort() ) + " seconds";
		else return "";
	}
}
