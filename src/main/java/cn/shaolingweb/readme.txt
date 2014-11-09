https://kenai.com/projects/btrace/pages/UserGuide
http://blog.csdn.net/qyongkang/article/details/6090497


01 安装
1.首先到网上下个Btrace包，官方网址：http://kenai.com/projects/btrace
	解压即用。
2 写btrace和一般java差不多，只是用了一些annotation来标识某个类是跟踪脚本。
	btrace用到的jar包基本都在下载的/btrace-bin/build文件下
	，将这三个包导进工程就可以使用了。
	【btrace脚本写好后可以不用编译，直接执行.java文件就可以】

3 使用(jps)
	>btrace 4056 D:\ws_02\T1Btrace.java
3 Method Annotations
	1. @com.sun.btrace.annotations.OnMethod
	    定位目标类，方法，位置，且可定位多个。target class(es), target method(s) and "location(s)" within the method(s）
	    查找clazz中的类和方法。类可以是全路径定义或者正则。正则为//中间的。 /java 
	.awt 
	..+/
	    可以用annotation来确定定位范围。@javax.jws.WebService所有被WebService annotation的类。
	    用+表示继承。+java.lang.Runnable可以定位所有继承自runnable接口的类。
	2. @com.sun.btrace.annotations.OnTimer
	    监控需要周期运行的方法。参数 N milliseconds。
	3. @com.sun.btrace.annotations.OnError
	    当被监控的btrace的任何方法抛异常的时候被触发。
	4. @com.sun.btrace.annotations.OnExit
	    当BTrace调用方法exit(int)推出监控session时被触发。
	5. @com.sun.btrace.annotations.OnEvent
	    当BTrace客户端从外部发出事件请求时被触发。可以用string表示事件名
	6. @com.sun.btrace.annotations.OnLowMemory
	    监控内存超过临界点的方法。
	7. @com.sun.btrace.annotations.OnProbe
	    用来避免吧需要监控的类或方法写死在脚本里。可以用xml文件配置来定义需要监控的方法等。运行的时候需要把这个xml文件拷到JVM的运行路径下，或者修改btracer.bat文件的probeDescPath来指定xml文件的位置。
	Argument Annotations
	8. @com.sun.btrace.annotations.Self
	    标记一个参数拥有this或（self）的值。
	9. @com.sun.btrace.annotations.Return
	    标记一个参数持有方法return的值。
	10. @com.sun.btrace.annotations.ProbeClassName (since 1.1)
	    标记一个参数持有被监控类的类名
	11. @com.sun.btrace.annotations.ProbeMethodName (since 1.1)
	    标记一个参数持有被监控方法的方法名。
	    1.2版本开始可以用Boolean参数fqn表是是否需要全路径名。
	12. @com.sun.btrace.annotations.TargetInstance (since 1.1)
	    标记一个参数持有调用实例的值。
	13. @com.sun.btrace.annotations.TargetMethodOrField (since 1.1)
	    标记一个参数持有调用方法名。
	    1.2版本开始可以用Boolean参数fqn表是是否需要全路径名。
	Unannotated arguments
	    Unannotated arguments是用来做特征匹配的，因此必须按照被监控方法的定义顺序出现。但他们可以和标记参数交叉出现。它们的精确意义取决于使用它们的location:
			    Kind.ENTRY, Kind.RETURN- the probed method arguments
		    Kind.THROW - the thrown exception
		    Kind.ARRAY_SET, Kind.ARRAY_GET - the array index
		    Kind.CATCH - the caught exception
		    Kind.FIELD_SET - the field value
		    Kind.LINE - the line number
		    Kind.NEW - the class name
		    Kind.ERROR - the thrown exception
	Field Annotations
	14. @com.sun.btrace.annotations.Export
	    使用这个关联一个BTrace成员变量来指定这个成员变量和jvmstat的counter相关联。这样就可以暴露出外部jvmstat clients的监控次数。
	15. @com.sun.btrace.annotations.Property
	    用来标记一个指定的成员变量作为MBean属性。如果一个BTrace类至少定义了一个用@Property注解的静态成员变量，那么MBean就会被创建并且注册到MBean服务器上。
	16. @com.sun.btrace.annotations.TLS
	    定义一个BTrace的静态成员变量为线程局部变量（thread local）。因此为了正常工作这个变量必须是immutable或cloneable的。这个可以让我们用来监控是不是同一个线程会进入到多个被监控action。
	Class Annotations
	17. @com.sun.btrace.annotations.DTrace
	    监控DTrace的
	18. @com.sun.btrace.annotations.DTraceRef
	    监控DTrace的
	19. @com.sun.btrace.annotations.BTrace
	    必选annotation。用以指定java类。 
	文档地址为http://kenai.com/projects/btrace/pages/UserGuide
    annotation的API补充：
    1. AnyType
	        用来匹配任何引用类型。AnyType[]。用来对方法进行特征匹配。
	2. Location
	        定义了需要监控的位置。可以选择不同的位置进行监控。配合Kind和Where来使用。
	      Kind，枚举类型
				ARRAY_GET         array element load
				ARRAY_SET         array element store
				CALL         method call
				CATCH         exception catch
				CHECKCAST         checkcast
				ENTRY         method entry
				ERROR         "return" because of no-catch
				FIELD_GET         getting a field value
				FIELD_SET         setting a field value
				INSTANCEOF        instanceof check
				LINE         source line number
				NEW         new object created
				NEWARRAY         new array created
				RETURN         return from method
				SYNC_ENTRY         entry into a synchronized block
				SYNC_EXIT         exit from a synchronized block
				THROW         throwing an exception
				 
				      Where
				AFTER         after the location of interest
				BEFORE         before the location of interest






