/**
 * 
 */
package bk.elearning.config;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * @author ThangNH
 *
 */
@Configuration
public class MultipartResolver extends CommonsMultipartResolver  {
	@Override
	public boolean isMultipart(HttpServletRequest request) {
	    return (request != null && isMultipartContent(request));
	}
	
	/**
	 * Extends ServletFileUpload.isMultipartContent() behavior to allow PUT requests as multipart.
	 * 
	 * @param request
	 *            The servlet request to be evaluated. Must be non-null.
	 *
	 * @return <code>true</code> if the request is multipart; <code>false</code> otherwise.
	 */
	public static final boolean isMultipartContent(HttpServletRequest request) {
	    HttpMethod httpMethod = HttpMethod.valueOf(request.getMethod());
	    if (HttpMethod.POST != httpMethod && HttpMethod.PUT != httpMethod) {
	        return false;
	    }
	    return FileUploadBase.isMultipartContent(new ServletRequestContext(request));
	}
}
