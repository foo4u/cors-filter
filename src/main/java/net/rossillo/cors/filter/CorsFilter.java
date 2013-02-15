package net.rossillo.cors.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Provides cross-origin resource sharing filter.
 * 
 * @author Scott Rossillo
 *
 */
public final class CorsFilter implements Filter {
	
	/**
	 * Creates a new CORS filter.
	 */
	public CorsFilter() {
		
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}
	
	/**
	 * Returns true if the given request is a pre-flight
	 * cross-origin resource sharing request; false otherwise.
	 */
	protected boolean isPreFlightRequest(final HttpServletRequest request) {
		return "OPTIONS".equalsIgnoreCase(request.getMethod());
	}

	public void doFilter(
			final ServletRequest request, 
			final ServletResponse response,
			final FilterChain chain) throws IOException, ServletException {
		this.doFilter((HttpServletRequest)request, (HttpServletResponse)response, chain);
	}
	
	public void doFilter(
			final HttpServletRequest request, 
			final HttpServletResponse response,
			final FilterChain chain) throws IOException, ServletException { 
	
		System.err.println("CORS: " + request.getRequestURI());
		System.err.println("CORS: Origin = " + request.getHeader("Origin"));
		
		if (this.isPreFlightRequest(request)) {
			System.err.println("CORS: DOING PRE-FLIGHT");
			this.handlePreFlightRequest(request, response);
		} else {
			response.addHeader("Access-Control-Allow-Origin", "*");
			System.err.println("CORS: DOING CHAIN");
			chain.doFilter(request, response);
		}
		
	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

	private void handlePreFlightRequest(
			final HttpServletRequest request, 
			final HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Headers", "*");
		response.addHeader("Access-Control-Allow-Methods", "HEAD, GET, OPTIONS");
	}

}
