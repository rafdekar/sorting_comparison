package SortCalc;

import java.util.Arrays;

public class BubbleSort
{
	private int[] randoms;
	private boolean swap;
	private int n;
	
	BubbleSort( int[] randoms )
	{
		this.randoms = Arrays.copyOf( randoms, randoms.length );
		swap = true; 
		n = randoms.length;
	}
	public float sort()
	{
		long time = System.currentTimeMillis();
		
		while( swap == true )
		{
			swap = false;
			for( int i = 0; i < n-1; i++ )
				if( randoms[i] > randoms[i+1] )
				{
					int temp = randoms[i];
					randoms[i] = randoms[i+1];
					randoms[i+1] = temp;
					swap = true;
				}
		}
		return (System.currentTimeMillis() - time)/1000f;
	}
}
