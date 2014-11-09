package cn.shaolingweb.ch01_btrace;

import static com.sun.btrace.BTraceUtils.println;
import static com.sun.btrace.BTraceUtils.str;
import static com.sun.btrace.BTraceUtils.strcat;
import com.sun.btrace.BTraceUtils;
import com.sun.btrace.annotations.BTrace;
import com.sun.btrace.annotations.Kind;
import com.sun.btrace.annotations.Location;
import com.sun.btrace.annotations.OnMethod;
import com.sun.btrace.annotations.ProbeClassName;
import com.sun.btrace.annotations.ProbeMethodName;
import com.sun.btrace.annotations.TLS;

/**
 *匹配的是com.crm. components包下的所有的以Delegate结尾的类的所有的方法
 */
@BTrace
public class T4BtraceRegex {
	@TLS
	private static long startTime = 0;

	@OnMethod(clazz = "/com//.crm//.components//..*Delegate.*/", method = "/.*/")
	public static void startMethod() {
		startTime = BTraceUtils.timeMillis();
	}

	@OnMethod(clazz = "/com//.crm//.components//..*Delegate.*/", method = "/.*/", location = @Location(Kind.RETURN))
	public static void endMethod() {
		println(strcat("time taken=>", str(BTraceUtils.timeMillis() - startTime)));
		println("--------------------------------------");
	}

	@OnMethod(clazz = "/com//.crm//.components//..*Delegate.*/", method = "/.*/", location = @Location(Kind.RETURN))
	public static void print(@ProbeClassName String pcn, @ProbeMethodName String pmn) {
		println(pcn);
		println(pmn);
	}
}
