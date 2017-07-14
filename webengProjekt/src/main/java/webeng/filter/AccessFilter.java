package webeng.filter;

import java.io.IOException;

import javax.faces.context.FacesContext;
import javax.servlet.DispatcherType;
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
 * Servlet Filter implementation class UserAdminFilter
 */
@WebFilter(filterName="/AccessFilter", urlPatterns={"/pages/warenkorb.xhtml", "/pages/myAccount.xhtml"}, dispatcherTypes={DispatcherType.REQUEST, DispatcherType.FORWARD})
public class AccessFilter implements Filter {


    /**
     * Default constructor. 
     */
    public  AccessFilter() {
    	
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		
		if( session.getAttribute("loggedUser") == null){	
			HttpServletResponse resp = (HttpServletResponse)response;
			resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Nur eingeloggte User k�nnen auf diese Seite zugreifen!");
		}else{
			chain.doFilter(request, response);
		}
			
		
		
	}
	
	
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}//end class UserAdminFilter
