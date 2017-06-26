package webeng.filter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(filterName="/AccessLog", urlPatterns={"/*"} )
public class AccessLog implements Filter {

	private final DateTimeFormatter DATEFORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");
	private LocalDateTime now;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		now = LocalDateTime.now();
		System.out.print(DATEFORMAT.format(now));
		System.out.print(" "+ req.getRequestURL());
		if (req.getQueryString() != null){
			System.out.print(" Query: "+req.getQueryString() + " " );
		}
		
		System.out.print("\n");
		chain.doFilter(request, response);
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}//end class AccessLog
