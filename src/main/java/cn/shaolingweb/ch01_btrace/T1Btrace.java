package cn.shaolingweb.ch01_btrace;
import static com.sun.btrace.BTraceUtils.println;  
import static com.sun.btrace.BTraceUtils.str;  
import static com.sun.btrace.BTraceUtils.strcat;  
import static com.sun.btrace.BTraceUtils.timeMillis;  

import com.sun.btrace.annotations.BTrace;
import com.sun.btrace.annotations.Kind;
import com.sun.btrace.annotations.Location;
import com.sun.btrace.annotations.OnMethod;
import com.sun.btrace.annotations.ProbeClassName;
import com.sun.btrace.annotations.ProbeMethodName;
import com.sun.btrace.annotations.TLS;

@BTrace //btrace script
public class T1Btrace {

	@TLS
	private static long startTime=0;
	
	@OnMethod(clazz="cn.shaolingweb.ch01_btrace.T1",method="execute")
	public static void startMethod(){  
	        startTime = timeMillis();  
	        println("-------------------------------------------");  
	}
	
	@OnMethod(clazz="cn.shaolingweb.ch01_btrace.T1",method="execute",location=@Location(Kind.RETURN))
	public static void endMethod(){  
		println(strcat("the class method execute time=>", str(timeMillis()-startTime)));  
        println("-------------------------------------------");  
	}
	
	
	@OnMethod(clazz="cn.shaolingweb.ch01_btrace.T1",method="execute",location=@Location(Kind.RETURN))
	 public static void traceExecute(
			 @ProbeClassName String name,
			 @ProbeMethodName String method,
			 int sleepTime){  
		 println(strcat("Class Name--->\t", name));
		 println(strcat("Method Name--->\t", method));
		 println(strcat("sleepTime--->\t", str(sleepTime)));
		 println();
	 }
	 
}
