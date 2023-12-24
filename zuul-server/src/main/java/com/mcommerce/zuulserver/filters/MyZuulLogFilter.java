package com.mcommerce.zuulserver.filters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import javax.servlet.http.HttpServletRequest;
@Component
public class MyZuulLogFilter {
	Logger log = LoggerFactory.getLogger(this.getClass());
	public String filterType() { 
		return "pre";

	}
	public int filterOrder() { return 1; }
	public boolean shouldFilter() { return true; }
	public Object run() throws ZuulException {
	 HttpServletRequest req = RequestContext.getCurrentContext().getRequest();
	 log.info("**** MyZuulLogFilter : Requête interceptée ! L'URL est : {} " , req.getRequestURL());
	 return null;
	}}

