package SortCalcTest;

import static org.junit.Assert.*;
import org.junit.Test;
import SortCalc.SortClasses.BubbleSort;
import SortCalc.SortClasses.Sort;

public class BubbleSortTest
{
	int[] input = new int[]{ 13, 21, 36, 16, 64, 3254, 565, 3, 84 };
	BubbleSort sort = new BubbleSort( input );
	int[] predictedOutput = new int[]{ 3, 13, 16, 21, 36, 64, 84, 565, 3254 };
	
	@Test
	public void testSort()
	{
		sort.sort();
		assertArrayEquals( predictedOutput, Sort.getArr() );
	}
}
