package bk.elearning.utils;

import java.io.File;

import javax.servlet.ServletContext;

import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
@Component
public class FileUpload implements ServletContextAware {

	static ServletContext context;

	
	public static boolean saveFile(MultipartFile file,String filePath)
	{
		try {
			File fileSave = new File(context.getRealPath("/")+filePath);
			file.transferTo(fileSave); 
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}


	@Override
	public void setServletContext(ServletContext servletContext) {
		// TODO Auto-generated method stub
		this.context=servletContext;
	}
}
