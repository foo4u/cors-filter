package net.rossillo.cors.filter;

import javax.servlet.Filter;

/**
 * Provides a cross-origin resource sharing filter.
 * 
 * @author Scott Rossillo
 *
 */
public interface CorsFilter extends Filter {

	/**
	 * Returns true if the given origin server is permitted by this filter.
	 * 
	 * @param origin the value of the request's HTTP "Origin" header
	 * 
	 * @return <code>true</code> if the given <code>origin</code> is permitted by
	 * this filter; <code>false</code> otherwise
	 */
	boolean allowOrigin(String origin);

	/**
	 * Returns true if the given HTTP method is permitted by this filter.
	 * 
	 * @param method the HTTP method being requested (e.g. GET, POST).
	 * 
	 * @return <code>true</code> if the given <code>method</code> is permitted by
	 * this filter; <code>false</code> otherwise
	 */
	boolean allowMethod(String method);

	/**
	 * Returns a comma delimited list of permitted HTTP methods.
	 */
	String allowedMethods();

}
