package SortCalc.SortClasses;

public class BubbleSort extends Sort
{
	public BubbleSort(int[] randoms) {
		super(randoms);
	}	

	public float sort()
	{
		boolean swap = true;
		int n = getArr().length;
		long time = System.currentTimeMillis();
		
		while( swap == true )
		{
			swap = false;
			for( int i = 0; i < n-1; i++ )
				if( getArr()[i] > getArr()[i+1] )
				{
					int temp = getArr()[i];
					getArr()[i] = getArr()[i+1];
					getArr()[i+1] = temp;
					swap = true;
				}
		}
		return (System.currentTimeMillis() - time)/1000f;
	}
	
	
}
