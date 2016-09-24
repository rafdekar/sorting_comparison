package SortCalc.SortClasses;

import java.util.Random;

public class Sorter
{
	private static final int size = 10000;
	private static final int MIN_RANDOM = (-100);
	private static final int MAX_RANDOM = 100;
	private int[] randoms;
	Random rand;
	
	public Sorter()
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
	
	public void drawArray( int size, int seed )
	{
		Random rand = new Random();
		rand.setSeed( seed );
		randoms = rand.ints( size ).toArray();
		for( int i = 0; i < randoms.length; i++ )
			randoms[i] = ( randoms[i] + MIN_RANDOM ) % ( MAX_RANDOM + 1 );
	}
	
	public int[] getRandoms()
	{
		return randoms;
	}
}
