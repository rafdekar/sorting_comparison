package SortCalc;

import java.util.Arrays;

public class InsertionSort
{
	private int[] randoms;
	private int n;
	
	InsertionSort( int[] randoms )
	{
		this.randoms = Arrays.copyOf( randoms, randoms.length );
		n = randoms.length;
	}
	public float sort()
	{
		int[] tempArr = Arrays.copyOf( randoms, randoms.length );
		long time = System.currentTimeMillis();
		
		for( int i = 0; i < n-1; i++ )
        {
            int pos = find( randoms, i, randoms[i+1] );
            move( randoms, pos, i+1, randoms[i+1] );
        }
		
		return (System.currentTimeMillis() - time)/1000f;
	}
	
    public void move( int[] randoms, int start, int end, int var )
    {
        for( int i = end; i > start; i-- )
        {
            randoms[i] = randoms[i-1];
        }
        randoms[start] = var;
    }
    
    public int find( int[] randoms, int end, int var )
    {
        int start = 0;
        
        if( var >= randoms[end] )
            return end + 1;
        else if( var <= randoms[start] )
            return 0;
            
        while(true)
        {
            int temp = start + (end-start)/2;
            if( randoms[temp] == var || ( randoms[temp] > var && randoms[temp - 1] < var ) )
                return temp;
            else if( randoms[temp] < var && randoms[temp + 1] > var )
                return temp + 1;
            else if( randoms[temp] < var )
                start = temp;
            else if( randoms[temp] > var )
                end = temp;
        }
    }
}
