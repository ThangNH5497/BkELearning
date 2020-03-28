package bk.elearning.utils;

import java.io.File;
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

	public static boolean saveFile(MultipartFile file, String filePath) {
		try {
			File fileSave = new File(context.getRealPath("/") + createUniqueFileName(filePath));
			file.transferTo(fileSave);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
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
			int i=1;
			while (new File(rootPath + filePath).exists()) {
				filePath = filePath.substring(0, filePath.lastIndexOf('.')) +String.valueOf(i)+ "." + ext;
				i++;
			}

			return filePath;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

}
