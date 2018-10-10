package Reader;// mathfundll.java
import com.sun.jna.*;
import com.sun.jna.win32.*;
import com.sun.jna.Structure;
import com.sun.jna.win32.StdCallLibrary;
import java.util.*;

public interface mathfundll extends StdCallLibrary{
	/*
	public static  class SYSTEMTIME extends Structure   {  //abstract
		public short wYear;
		public short wMonth;
		public short wDayOfWeek;
		public short wDay;
		public short wHour;
		public short wMinute;
		public short wSecond;
		public short wMilliseconds;
		
		@Override
		protected List getFieldOrder() {
			List a = new ArrayList();
			a.add("wYear");
			a.add("wMonth");
			a.add("wDayOfWeek");
			a.add("wDay");
			a.add("wHour");
			a.add("wMinute");
			a.add("wSecond");
			a.add("wMilliseconds");
			return a;
		}
	}
	*/
	//void GetLocalTime (SYSTEMTIME result);
	double Add(double a, double b);
	double Subtract(double a, double b);
	double Multiply(double a, double b);
	double Divide(double a, double b);
	
}