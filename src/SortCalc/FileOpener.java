package SortCalc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileOpener
{
	private String code = new String("htfhfth");
	
	FileOpener( String sortType )
	{
		Path file = Paths.get( "D:/Java/SortCalc/src/SortCalc/InsertionSort.java" );
		try (InputStream in = Files.newInputStream(file);
		    BufferedReader reader =
		      new BufferedReader(new InputStreamReader(in))) {
		    String line = "";
		    while ((line = reader.readLine()) != null) {
		        code += ( line + "\n" );
		    }
		} catch (IOException x) {
		    System.err.println(x);
		}
	}
	
	public String getCode()
	{
		return code;
	}
}
