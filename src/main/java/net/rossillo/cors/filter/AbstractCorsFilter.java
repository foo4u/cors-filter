package net.rossillo.cors.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Provides an abstract CORS filter implementation that handles communication
 * with the client browser and enforces access control while delegating
 * access decisions to its subclass. 
 *  
 * @author Scott Rossillo
 *
 */
public abstract class AbstractCorsFilter implements CorsFilter{

	protected static final String AC_ALLOW_HEADERS = "Access-Control-Allow-Headers";
	protected static final String AC_ALLOW_ORIGIN = "Access-Control-Allow-Origin";
	protected static final String AC_REQUEST_METHOD = "Access-Control-Request-Method";
	protected static final String REQUESTED_WITH_PARAM = "X-Requested-With";

	/**
	 * Creates a new abstract CORS filter.
	 */
	protected AbstractCorsFilter() {
		super();
	}

	/**
	 * Returns true if the request is permitted.
	 * 
	 * @param request the current <code>HttpServletRequest</code> to allow or deny
	 * 
	 * @return <code>true</code> if the request is permitted;
	 * <code>false</code> otherwise
	 */
	protected boolean allowRequest(final HttpServletRequest request) {

		if (this.isPreFlightRequest(request)) {
			return this.allowOrigin(this.getOrigin(request)) 
					&& this.allowMethod(request.getHeader(AC_ALLOW_HEADERS));
		}

		return this.allowOrigin(this.getOrigin(request)) 
				&& this.allowMethod(request.getMethod());
	}

	/**
	 * Returns true if the given request is a
	 * cross-origin resource sharing request; false otherwise.
	 */
	protected final boolean isCrossOriginRequest(final HttpServletRequest request) {
		return (this.getOrigin(request) != null);
	}

	/**
	 * Returns true if the given request is a pre-flight
	 * cross-origin resource sharing request; false otherwise.
	 */
	protected final boolean isPreFlightRequest(final HttpServletRequest request) {
		return this.isCrossOriginRequest(request) && "OPTIONS".equalsIgnoreCase(request.getMethod());
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public final void doFilter(
			final ServletRequest request, 
			final ServletResponse response, 
			final FilterChain chain)
					throws IOException, ServletException {
		this.doFilter((HttpServletRequest)request, (HttpServletResponse)response, chain);
	}

	private void doFilter(final HttpServletRequest request, final HttpServletResponse response, final FilterChain chain)
			throws IOException, ServletException { 

		if (this.isCrossOriginRequest(request) && !this.allowRequest(request)) {
			response.sendError(403);
			return;
		}

		if (this.isPreFlightRequest(request)) {
			this.handlePreFlightRequest(request, response);
		} else {
			this.handleCrossOriginRequest(request, response);
			chain.doFilter(request, response);
		}

	}

	/**
	 * Returns the origin of the given HTTP request.
	 */
	protected String getOrigin(final HttpServletRequest request) {
		return request.getHeader("Origin");
	}

	private final void handlePreFlightRequest(
			final HttpServletRequest request, 
			final HttpServletResponse response) throws IOException {

		if (REQUESTED_WITH_PARAM.equalsIgnoreCase( (String) request.getAttribute(AC_ALLOW_HEADERS))) {
			response.setHeader(AC_ALLOW_HEADERS, REQUESTED_WITH_PARAM);
		}

		response.addHeader("Access-Control-Allow-Methods", this.allowedMethods().toUpperCase());
		this.handleCrossOriginRequest(request, response);
	}

	private final void handleCrossOriginRequest(
			final HttpServletRequest request, 
			final HttpServletResponse response) throws IOException {
		response.addHeader(AC_ALLOW_ORIGIN, this.getOrigin(request));
	}

}