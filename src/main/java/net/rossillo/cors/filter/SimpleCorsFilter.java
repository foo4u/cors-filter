package net.rossillo.cors.filter;


import javax.servlet.FilterConfig;
import javax.servlet.ServletException;

/**
 * Provides a simple cross-origin resource sharing filter.
 * 
 * @author Scott Rossillo
 *
 */
public final class SimpleCorsFilter extends AbstractCorsFilter implements CorsFilter {

	public static final String DEFAULT_ALLOWED_MEHTODS = "GET";
	public static final String CONFIG_ALLOWED_MEHTODS = "methods";

	private String allowedMethods = DEFAULT_ALLOWED_MEHTODS;

	/**
	 * Creates a new simple CORS filter.
	 */
	public SimpleCorsFilter() {
		super();
	}

	public void init(final FilterConfig config) throws ServletException {

		final String methods = config.getInitParameter(CONFIG_ALLOWED_MEHTODS);

		if (methods != null) {
			allowedMethods = methods.toUpperCase();
		}
	}

	public void destroy() {
		// nothing to do here
	}

	public boolean allowOrigin(final String origin) {
		return true;
	}

	public boolean allowMethod(String method) {
		return allowedMethods.contains(method);
	}

	public String allowedMethods() {
		return allowedMethods;
	}

}
