package SortCalc.SortClasses;

public class SelectionSort extends Sort
{
	
	public SelectionSort( int[] randoms ) {
		super( randoms );
	}

	public float sort()
	{
		int n = getArr().length;
		long time = System.currentTimeMillis();
		
		while( n > 1 )
		{
			int T = 0;
			int pos = 0;
			for( int i = 0; i < n; i++ )
				if( getArr()[i] > T )
				{
					T = getArr()[i];
					pos = i;
				}
			getArr()[ pos ] = getArr()[ n - 1 ];
			getArr()[ n - 1 ] = T;
			n--;
		}
		return (System.currentTimeMillis() - time)/1000f;
	}
}
