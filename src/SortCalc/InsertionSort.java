package SortCalc;

import java.util.Arrays;

public class InsertionSort
{
	int[] randoms;
	
	InsertionSort( int[] randoms )
	{
		this.randoms = Arrays.copyOf( randoms, randoms.length );
	}
	public float sort()
	{
		int[] tempArr = Arrays.copyOf( randoms, randoms.length );
		boolean swap = true;
		long time = System.currentTimeMillis();
		
		while( swap == true )
		{
			swap = false;
			for( int i = 0; i < tempArr.length-1; i++ )
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
}
