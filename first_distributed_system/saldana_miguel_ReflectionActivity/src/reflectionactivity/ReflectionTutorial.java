package reflectionactivity;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;

import edu.neumont.csc380.ReflectionActivity;
import static java.lang.System.out;

public class ReflectionTutorial {

	public ReflectionTutorial() {
	}

	public static void main(String[] args) {
		Class c;
		try {
			c = Class.forName("edu.neumont.csc380.ReflectionActivity");
			
			Constructor[] con = c.getConstructors();
			ReflectionActivity ra = (ReflectionActivity) con[1].newInstance("Hey", 10.0);
			
			printMembers(c.getConstructors(), "Class");
			printMembers(c.getDeclaredFields(), "Fields");
			printMembers(c.getDeclaredMethods(), "Methods");
			
			Method met = c.getMethod("addToNumber", double.class);
			System.out.println(met.invoke(ra, 10.0));
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Utilized from java docs
    private static void printMembers(Member[] members, String s) {
	out.format("%s:%n", s);
	for (Member member : members) {
	    if (member instanceof Field)
		out.format("  %s%n", ((Field)member).toGenericString());
	    else if (member instanceof Constructor)
		out.format("  %s%n", ((Constructor)member).toGenericString());
	    else if (member instanceof Method)
		out.format("  %s%n", ((Method)member).toGenericString());
	}
	if (members.length == 0)
	    out.format("  -- No %s --%n", s);
	out.format("%n");
    }
}
