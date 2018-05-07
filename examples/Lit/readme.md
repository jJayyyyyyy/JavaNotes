##	简介

*	入口

	`[workspace]/src/lit/Lit.java`

*	之前跟着 Udacity 学 Android 的时候, 自己做了个 app --- Lit, 意为 Lite Info Toolkit, 项目地址在[这里](https://github.com/jJayyyyyyy/Lit/tree/master/app/src/main/java/io/github/jjayyyyyyy/lit)

	做这个 app 的时候, 没有系统地进行学习, 总结文档也没写 0.0, 结果好多东西都忘了, 真是惭愧... 

*	现在重拾 Java, 为了练手, 决定把以前的项目重写一遍.

	对于 Lit , 由于都是用 Java , 其实没有太多需要改动的地方, 只需要做一些微调.

*	JavaNotes --- note16 中总结的【摘要】, 在这个项目里得到了体现.

	比如, 对于 `流`

	在 src --- lit --- Request.java --- readFromStream() 中, 

	输入的是二进制流 `InputStream`

	先经过 `InputStreamReader` 进行初步包装与解码

	然后放到 `BufferedReader` 类型的 `bufReader` 中

	就可以 `String line = bufReader.readLine();` 写到字符串中了

	这里还参考了 Lit 原来的写法, 即使用 `StringBuilder sb` , 每次读到 line, 就进行 `sb.append(line);`

<br><br>

##	org.json

*	json是一种比较直观的数据格式, 存储和解析都比较方便

	但是 Java 自带的类库中, 没有直接用于存储和解析 json 的类

	还好有 org.json 这个第三方的开源包, 方便我们调用

*	地址	

	[GitHub --- https://github.com/stleary/JSON-java](https://github.com/stleary/JSON-java)

*	[文档 --- http://stleary.github.io/JSON-java/index.html](http://stleary.github.io/JSON-java/index.html)

*	食用方法

	在 [Github](https://github.com/stleary/JSON-java/releases) 下载最新的release, 解压到目录 `[workspace]/src/org/json/` 即可在 Eclipse 中调用, 和 python 用包差不多, 很方便.

<br><br>

##	思路与过程

*	Request.java

	首先实现 Request 类.

	```
	input: strURL

	return strResp

	inside: url.openConnection() --> InputStream --> InputStreamReader --> BufferedReader --> strResp
	```

*	其他类, 调用 

	创建一个 Request 对象, 通过 HTTP(s) 获取 response, 再对 response 进行解析

	```java
	Request req = new Request();
	req.https = true;	// 有些链接是 https 的, 有些不是
	
	String strURL = xxxURL;
	String resp = req.getResp(strURL);
	
	if( resp != null ) {
		// parse resp
	}
	```

<br><br>

##	分类

*	针对不同的信息源/api, 我们应采取不同的方式对 response 进行解析

*	如果目标网站提供了 json 接口, 那么就使用 org.json 这个包里面的类进行解析

	示例: NewsFlash.java

*	如果，没有规范的数据格式, 那么就分析 response, 找到目标字段的规律, 然后用 正则表达式 进行解析

	示例: Dictionary.java, HackerNews.java, Solidot.java

	对于天气模块 Weather.java, 由于其 html 比较杂乱, 而且白天和晚上的 html 代码会变化, 因此要考虑更复杂的情况

<br><br>

##	TODO
	
*	实现 GUI, 而不仅仅是 Console

*	多线程

*	研究 JSONObject 等文件的实现

<br><br>

##	结语

*	做了项目, 不要拖延, 马上写总结, 方便以后查阅
