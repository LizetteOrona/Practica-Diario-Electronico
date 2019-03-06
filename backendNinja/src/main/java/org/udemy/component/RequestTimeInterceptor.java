package org.udemy.component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component("requesttimeintenceptor")
public class RequestTimeInterceptor extends HandlerInterceptorAdapter{

	private static final Log LOGGER = LogFactory.getLog(RequestTimeInterceptor.class);
	 
	//PRIMERO
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		request.setAttribute("startTime", System.currentTimeMillis());
		return true;
	}

	//SEGUNDO
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		long startTime = (long) request.getAttribute("startTime");
		LOGGER.info("URL: to'"+ request.getRequestURI().toString() + "' --in: '"+ (System.currentTimeMillis() - startTime) + "' ms");
	}
	
}
