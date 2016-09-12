package SortCalc;

import java.util.Arrays;
import java.util.Random;

public class Sorter
{
	private static final int size = 100;
	private int[] randoms;
	Sorter()
	{
		Random rand = new Random();
		randoms = rand.ints( size ).toArray();
	}
	
	public String sortTime( String sorttype )
	{
		if( sorttype == "BubbleSort" )
			return Float.toString( bubbleSort() );
		else if( sorttype == "InsertionSort" )
			return Float.toString( insertionSort() );
		else if( sorttype == "SelectionSort" )
			return Float.toString( selectionSort() );
		else return "";
	}
	
	public float bubbleSort()
	{
		int[] tempArr = Arrays.copyOf( randoms, randoms.length );
		boolean swap = true;
		long time = System.currentTimeMillis();
		
		while( swap = true )
		{
			swap = false;
			for( int i = 0; i < tempArr.length; i++ )
				if( tempArr[i] > tempArr[i+1] )
				{
					int temp = tempArr[i];
					tempArr[i] = tempArr[i+1];
					tempArr[i+1] = temp;
					swap = true;
				}
		}
		return (System.currentTimeMillis() - time)/1000f;
	}
	
	public int insertionSort()
	{
		return 0;
	}
	
	public int selectionSort()
	{
		return 0;
	}
}
