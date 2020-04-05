package bk.elearning.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUpload implements ServletContextAware {

	static ServletContext context;

	@Override
	public void setServletContext(ServletContext servletContext) {
		// TODO Auto-generated method stub
		this.context = servletContext;
	}

	public static ServletContext getContext() {
		return context;
	}

	public static String saveFile(MultipartFile file, String filePath) {
		try {
			filePath=createUniqueFileName(filePath);
			File fileSave = new File(context.getRealPath("/") +filePath );
			file.transferTo(fileSave);
			return filePath;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return Util.DEFAULT_USER_IMAGE;
	}

	public static <T> List<T> processFileExel(MultipartFile file, IModelMapper<T> mapper) {
		List<T> list = new ArrayList<T>();

		try {
			Workbook workbook;
			String lowerCaseFileName = file.getOriginalFilename().toLowerCase();
			if (lowerCaseFileName.endsWith(".xlsx")) {
				workbook = new XSSFWorkbook(file.getInputStream());
			} else {
				workbook = new HSSFWorkbook(file.getInputStream());
			}

			Sheet sheet = workbook.getSheetAt(0);
			workbook.close();
			list = mapper.mapToModel(sheet);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * Kiểm tra file tổn tại
	 * Nếu tồn tại thêm số vào cuối file
	 * return new filePath
	 */
	public static String createUniqueFileName(String filePath) {
		try {
			String rootPath = context.getRealPath("/");
			String ext = filePath.substring(filePath.lastIndexOf('.') + 1);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
			// Output "Wed Sep 26 14:23:28 EST 2012"
			String time = format.format(Util.getDate());
			filePath = filePath.substring(0, filePath.lastIndexOf('.'))+"-" +time+ "." + ext;
			return filePath;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

}
