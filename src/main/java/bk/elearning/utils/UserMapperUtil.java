package bk.elearning.utils;

import java.io.FileOutputStream;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFPicture;
import org.apache.poi.xssf.usermodel.XSSFShape;

public abstract class UserMapperUtil<T> implements IModelMapper<T> {

	private boolean loadImage;
	private final Class<T> clazz;

	public UserMapperUtil() {
		this.clazz = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	public Class<T> getClazz() {
		return clazz;
	}

	public UserMapperUtil(boolean loadImage) {
		super();
		this.loadImage = loadImage;
		this.clazz = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	public List<T> mapFields(Sheet sheet) {
		// TODO Auto-generated method stub
		List<T> list = new ArrayList<T>();
		try {
			DataFormatter dataFormatter = new DataFormatter();
			List<String> fieldNames = new ArrayList<String>();
			int i = 0;
			// read first row
			for (Row row : sheet) {
				// next first row
				if (i == 0) {
					for (Cell cell : row) {
						String cellValue = dataFormatter.formatCellValue(cell);
						fieldNames.add(cellValue);
					}
				} else {
					int j = 0;
					// create object instance
					try {
						T t = clazz.newInstance();
						for (Cell cell : row) {
							String cellValue = dataFormatter.formatCellValue(cell);
							if (j < fieldNames.size()) {
								try {
									BeanUtils.setProperty(t, fieldNames.get(j), cellValue);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							j++;
						}
						list.add(t);

					} catch (InstantiationException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IllegalAccessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				i++;
			}
			if(loadImage==true)
			{
				mapImages(sheet, list, fieldNames);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}

		return list;
	}

	/**
	 * Map image to model user sheet -> sheet exel list -> list model user
	 */
	@Override
	public void mapImages(Sheet sheet, List<T> list, List<String> fieldNames) {
		// TODO Auto-generated method stub
		try {
			XSSFDrawing dp = (XSSFDrawing) sheet.createDrawingPatriarch();
			List<XSSFShape> pics = dp.getShapes();
			for (XSSFShape xssfShape : pics) {
				XSSFPicture inpPic = (XSSFPicture) xssfShape;

				XSSFClientAnchor clientAnchor = inpPic.getClientAnchor();

				if (clientAnchor.getRow1() == clientAnchor.getRow2() && clientAnchor.getCol1() == clientAnchor.getCol2()
						&& fieldNames.get(clientAnchor.getCol1()).equals("image")) {
					inpPic.getShapeName();
					PictureData pict = inpPic.getPictureData();
					System.out.println("mime type : " + pict.getMimeType());
					int picType = pict.getPictureType();
					String ext = "jpg";
					T object = list.get(clientAnchor.getRow1() - 1);
					boolean checkType = true;
					if (picType == Workbook.PICTURE_TYPE_JPEG) {
						ext = "jpg";
					} else if (picType == Workbook.PICTURE_TYPE_PNG) {
						ext = "png";
					} else
						checkType = false;
					String code=BeanUtils.getProperty(object, "code");
					if (checkType == true&&object!=null&&code!=null&&code!="") {
						String rootPath = FileUpload.context.getRealPath("/");
						String filePath = "image."+ ext;
						filePath = FileUpload.createUniqueFileName(Constant.UPLOAD_USER_DIR+filePath);
						FileOutputStream out = new FileOutputStream(rootPath + filePath);
						byte[] data = pict.getData(); 
						out.write(data);

						BeanUtils.setProperty(object, "image", filePath);
						out.close();
					}

				}

			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("exception : "+e.toString());
		}

	}


}
