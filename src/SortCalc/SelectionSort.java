package SortCalc;

import java.util.Arrays;

public class SelectionSort
{
	private int[] randoms;
	private int n;
	
	SelectionSort( int[] randoms )
	{
		this.randoms = Arrays.copyOf( randoms, randoms.length );
		n = randoms.length;
	}
	public float sort()
	{
		long time = System.currentTimeMillis();
		
		while( n > 1 )
		{
			int T = 0;
			int pos = 0;
			for( int i = 0; i < n; i++ )
				if( randoms[i] > T )
				{
					T = randoms[i];
					pos = i;
				}
			randoms[ pos ] = randoms[ n - 1 ];
			randoms[ n - 1 ] = T;
			n--;
		}
		return (System.currentTimeMillis() - time)/1000f;
	}
}
