package com.fabian.tutorial.microservices.zuul.filters;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.netflix.zuul.IZuulFilter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.constants.ZuulConstants;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class PreTiempoTranscurridoFilter extends ZuulFilter {

	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		// TODO Auto-generated method stub

		RequestContext rtx = RequestContext.getCurrentContext();
		HttpServletRequest request = rtx.getRequest();
		Long tiempoDeInicio  = System.currentTimeMillis();
		request.setAttribute("tiempoDeInicio", tiempoDeInicio);
		
		return null;
	}

	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return "pre";
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 0;
	}

}
