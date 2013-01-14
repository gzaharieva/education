package uni.vt.education.history;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Blocks access to the application for not logged in users.
 * 
 * @author a.vitkinov
 */
@WebFilter("/application/*")
public class LoginFilter implements Filter {
	private static final String LOGIN_PAGE = "/index.jsf";

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest aRequset, ServletResponse aResponse,
			FilterChain aChain) throws IOException, ServletException {
		HttpServletRequest tRequest = (HttpServletRequest) aRequset;
		HttpSession tSession = tRequest.getSession();
//		if (tSession.getAttribute("SessionId") != null) {
			aChain.doFilter(aRequset, aResponse);
//		} else {
//			HttpServletResponse tResponse = (HttpServletResponse) aResponse;
//			tResponse.sendRedirect(tRequest.getContextPath() + LOGIN_PAGE);
//			return;
//		}
	}

	@Override
	public void init(FilterConfig aFilterConfig) throws ServletException {
	}
}