package net.rossillo.cors.filter;

import org.springframework.mock.web.MockHttpServletRequest;

import junit.framework.TestCase;

/**
 * Provides simple CORS filter unit tests.
 * 
 * @author Scott Rossillo
 * 
 */
public final class SimpleCorsFilterTest extends TestCase {

	private SimpleCorsFilter filter;
	private MockHttpServletRequest request;

	protected void setUp() throws Exception {
		filter = new SimpleCorsFilter();
		request = new MockHttpServletRequest();
	}

	public void testAllowMethod() {
		assertTrue(filter.allowMethod("GET"));
		assertFalse(filter.allowMethod("POST"));
	}

	public void testAllowOrigin() {
		assertTrue(filter.allowOrigin("http://github.com"));
	}

	public void testAllowRequestFalse() {
		request.setMethod("PUT");
		assertFalse(filter.allowRequest(request));
	}

	public void testAllowRequestTrue() {
		request.setMethod("GET");
		assertTrue(filter.allowRequest(request));
	}

	public void testGetOrigin() {
		request.addHeader("Origin", "http://github.com");
		assertEquals("http://github.com", filter.getOrigin(request));
	}

	public void testIsCrossOriginRequestFalse() {
		assertFalse(filter.isCrossOriginRequest(request));
	}

	public void testIsCrossOriginRequestTrue() {
		request.addHeader("Origin", "http://github.com");
		assertTrue(filter.isCrossOriginRequest(request));
	}

}
