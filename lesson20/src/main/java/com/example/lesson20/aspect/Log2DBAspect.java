package com.example.lesson20.aspect;

import com.example.lesson20.annotation.Log2DB;
import com.example.lesson20.config.ApisScaner;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@Aspect
@Configuration
@Slf4j
public class Log2DBAspect {
	//拦截哪个包下的类
	//拦截哪个注解com.example.lesson20.annotation.Log2DB
	@Pointcut("execution(public * *(..)) && @annotation(com.example.lesson20.annotation.Log2DB)")
	public void logs(){
		log.info("@Pointcut");
	}
	
	@Around("logs()")
	public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
		log.info("@Around");
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Log2DB logAnnotation = signature.getMethod().getAnnotation(Log2DB.class);
        //如果不需要记录到数据库，继续业务调用并返回
        if(!logAnnotation.enabled()){
        	return joinPoint.proceed();
        }

        //定义需要从request和response中获取的信息 begin
		String name = "",//请求的服务名称
				url="",//请求服务地址
				method="",//请求方法 get post put
				clientIp="",//客户端ip
				browser="",//浏览器
				clazz_method="",//请求的类和方法名
				headers="", //head头信息
				request="";//请求body入参
		Object response=null;//返回参数
		Date bgn,end;//请求开始结束时间
		Boolean success = true;//是否成功
		long cost = 0;//耗时 毫秒
		//定义需要从request和response中获取的信息 end

		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		if(attributes==null){
			return joinPoint.proceed();
		}
		HttpServletRequest httpServletRequest = attributes.getRequest();

		url = httpServletRequest.getRequestURL().toString();
		method = httpServletRequest.getMethod();
		clientIp =getClientIp(httpServletRequest);
		clazz_method =joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName();

		name = logAnnotation.name();
		name = ApisScaner.APIS.get(clazz_method);


		//获取url后面的参数串
		StringBuffer params = new StringBuffer();
		for (String item : httpServletRequest.getParameterMap().keySet()) {
			params.append(item).append("=").append(httpServletRequest.getParameter(item)).append("&");
		}
		String tmp = params.toString();
		if(StringUtils.hasText(tmp)){
			tmp = tmp.substring(0,tmp.length()-1);
			url = url+"?"+tmp;
		}

		//获取header头信息
		List<Map<String,String>> headerList = new ArrayList<>();
		Enumeration<String> headerNames = httpServletRequest.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = headerNames.nextElement();
			String value = httpServletRequest.getHeader(key);
			Map<String,String> map = new HashMap<String,String>();
			map.put(key,value);
			headerList.add(map);

			if("user-agent".equals(key.toLowerCase())){
				browser = value;
			}
		}
		headers = new ObjectMapper().writeValueAsString(headerList);

		request = new ObjectMapper().writeValueAsString(joinPoint.getArgs());

		//记录起始时间
		bgn = new Date();
		/** 执行目标方法 */
		try{
			response= joinPoint.proceed();
			log.info("response={}",response==null?"": new ObjectMapper().writeValueAsString(response));
		}
		catch(Exception e){
			success=false;
			response = e.getMessage();
			log.error("errorMessage: {}", e.getMessage());
			e.printStackTrace();
			throw e;
		}
		finally{
			end = new Date();
			/** 记录操作时间 */
			cost = (end.getTime() - bgn.getTime());

			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
			log.info("name={}",name);
			log.info("url={}",url);
			log.info("method={}",method);
			log.info("ip={}",clientIp);
			log.info("browser={}",browser);
			log.info("class_method={}",clazz_method);
			log.info("header={}",headers);
			log.info("request={}",request);
			log.info("response={}",response);
			log.info("success={}",success);
			log.info("bgn={}",format.format(bgn));
			log.info("end={}",format.format(end));
			log.info("cost={}", cost);

			//TODO: 保存到数据库 这里大家伙自行sql保存到数据库，我就不参合了
			//不过我建议这个地方可以考虑先放到mq 消息队列中，然后慢慢消费，不要占用mysql的资源。
			// 或者日志就村存储到mongodb中也不失为一个好办法
		}
		return response;
	}

	@Before("logs()")
	public void doBefore(JoinPoint point){
		log.info("@Before");
	}

	@After("logs()")
	public void doAfter(){
		log.info("@After");
	}

	//在doAfter之后执行，主要用户记录程序执行后的返回值
	@AfterReturning(returning="object",pointcut="logs()")
	public void doAfterReturning(Object object){
		log.info("@AfterReturning");
	}

	//获取客户端ip
	private String getClientIp(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
}
