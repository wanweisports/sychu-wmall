package Reader;// LocalTime.java
import com.sun.jna.*;
public class testmathfundll{
	public static void main (String [] args)   {
		mathfundll lib = (mathfundll) Native.loadLibrary ("mathfundll",mathfundll.class);
		//mathfundll.SYSTEMTIME time = new mathfundll.SYSTEMTIME ();
		//lib.GetLocalTime (time);
		double add = lib.Add(1,1);
		System.out.println ("add= "+add);
		System.out.println ("Month is "+lib.Subtract(5,1));
		System.out.println ("Day of Week is "+lib.Multiply(2,3));
		System.out.println ("Day is "+lib.Divide(8,2));
		
	}
}
