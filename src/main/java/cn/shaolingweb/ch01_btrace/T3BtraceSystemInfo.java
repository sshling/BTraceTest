package cn.shaolingweb.ch01_btrace;
import com.sun.btrace.annotations.BTrace;
import static com.sun.btrace.BTraceUtils.*;
@BTrace
public class T3BtraceSystemInfo {
	//打印系统信息
	static{  
        println("java vm properties:===>");  
        printVmArguments();  
        println("System properties:===>");  
        printProperties();  
        println("OS properties:===>");  
        printEnv();  
        exit();  
    } 
}
