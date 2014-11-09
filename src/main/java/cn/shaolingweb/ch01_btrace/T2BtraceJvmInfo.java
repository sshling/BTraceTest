package cn.shaolingweb.ch01_btrace;

import com.sun.btrace.annotations.BTrace;
import com.sun.btrace.annotations.OnTimer;
import static com.sun.btrace.BTraceUtils.*;

@BTrace
public class T2BtraceJvmInfo {
	//TraceMemory  打印堆/非堆内存信息，包括init、used、commit、max
	@OnTimer(4000)
	public static void printM(){  
		println("heap usage:["+heapUsage()+"],non heap usage["+nonHeapUsage()+"]");
	}
}
