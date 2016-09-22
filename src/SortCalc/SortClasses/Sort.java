package SortCalc.SortClasses;

import java.util.Arrays;

public abstract class Sort
{
	private static int[] randoms;
	
	Sort( int[] randoms )
	{
		Sort.randoms = Arrays.copyOf( randoms, randoms.length );
	}
	
	public abstract float sort();
	
	public int[] getArr()
	{
		return randoms;
	}
	
}
