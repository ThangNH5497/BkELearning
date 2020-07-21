package bk.elearning.utils;

import java.io.FileOutputStream;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.text.StringEscapeUtils;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bk.elearning.entity.Answer;
import bk.elearning.entity.Question;

@Component
public class QuestionMapperUtil implements IModelMapper<Question> {

	private final String ANSWER_FIELD = "answer";
	private final String IMAGE_FIELD = "image";
	private final String VIDEO_FIELD = "video";
	private final String AUDIO_FIELD = "audio";
	private final String CONTENT_FIELD = "content";
	private String rootUrl;
	@Autowired
	private EnvUtil envUtil;
	
	public String getRootUrl() {
		return rootUrl;
	}

	public void setRootUrl(String rootUrl) {
		this.rootUrl = rootUrl;
	}

	public List<Question> mapFields(Sheet sheet) {
		// TODO Auto-generated method stub
		List<Question> list = new ArrayList<Question>();
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
						Question question = new Question();
						List<Answer> answers=new ArrayList<Answer>();
						StringBuilder content=new StringBuilder();
						int checkQType=0;
						for (Cell cell : row) {
							String cellValue = dataFormatter.formatCellValue(cell);
							
							if (j < fieldNames.size()&&cellValue!=null&&(!(cellValue.equals("")))) {
								try {
									// BeanUtils.setProperty(t, fieldNames.get(j), cellValue);
									// read property question
									switch (fieldNames.get(j)) {
									
									case CONTENT_FIELD: {
										content.append("<p>"+cellValue+"</p>");
										break;
									}
									case IMAGE_FIELD: {
										break;
									}
									case ANSWER_FIELD: {
										Answer answer=new Answer();
										//get answer weight between '[' and ']'
										float answerWeight=Float.parseFloat(cellValue.substring(cellValue.indexOf('[')+1, cellValue.indexOf(']')));
										cellValue=cellValue.substring(cellValue.indexOf(']')+1);
										cellValue="<p>"+cellValue+"</p>";
										answer.setContent(StringEscapeUtils.escapeHtml4(cellValue));
										if(answerWeight>0f)
										{
											answer.setCorrect(true);
											checkQType++;
											
										}
										answer.setWeight(answerWeight);
										answer.setQuestion(question);
										answers.add(answer);
										break;
									}
									case VIDEO_FIELD: {
										content.append("<p style=\"text-align: center; \"><iframe frameborder=\"0\" width=\"640\" height=\"360\" class=\"note-video-clip\" src='"+cellValue+"'></iframe></p>");	
										break;
									}
									case AUDIO_FIELD: {
										content.append("<p style=\"text-align: center; \"><audio controls> <source  src='"+cellValue+"'></audio></p>");
										break;
									}
									default: {
										BeanUtils.setProperty(question, fieldNames.get(j), cellValue);
										break;
									}
									}
									
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							
							j++;
						}
						if(checkQType==1) question.setType("ONE_CHOICE");
						else if(checkQType>1) question.setType("MULTIPLE_CHOICE");
						else  question.setType("FILL_WORD");
						question.setAnswers(answers);
						question.setContent(StringEscapeUtils.escapeHtml4(content.toString()));
						list.add(question);

					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				i++;
			}

			mapImages(sheet, list, fieldNames);

		} catch (Exception e) {
			// TODO: handle exception
		}

		return list;
	}

	/**
	 * Map image to model question sheet -> sheet excel list -> list model question
	 */
	@Override
	public void mapImages(Sheet sheet, List<Question> list, List<String> fieldNames) {
		// TODO Auto-generated method stub
		try {
			XSSFDrawing dp = (XSSFDrawing) sheet.createDrawingPatriarch();
			List<XSSFShape> pics = dp.getShapes();
			for (XSSFShape xssfShape : pics) {
				XSSFPicture inpPic = (XSSFPicture) xssfShape;

				XSSFClientAnchor clientAnchor = inpPic.getClientAnchor();

				if (clientAnchor.getRow1() == clientAnchor.getRow2() && clientAnchor.getCol1() == clientAnchor.getCol2()
						&& fieldNames.get(clientAnchor.getCol1()).equals(IMAGE_FIELD)) {
					inpPic.getShapeName();
					PictureData pict = inpPic.getPictureData();
					int picType = pict.getPictureType();
					String ext = "jpg";
					Question question = list.get(clientAnchor.getRow1() - 1);
					boolean checkType = true;
					if (picType == Workbook.PICTURE_TYPE_JPEG) {
						ext = "jpg";
					} else if (picType == Workbook.PICTURE_TYPE_PNG) {
						ext = "png";
					} else
						checkType = false;
				//	String code = BeanUtils.getProperty(object, "code");
					if (checkType == true && question != null) {
						String rootPath = FileUpload.context.getRealPath("/");
						String filePath = "image." + ext;
						filePath = FileUpload.createUniqueFileName(Constant.UPLOAD_QESTION_DIR + filePath);
						FileOutputStream out = new FileOutputStream(rootPath + filePath);
						byte[] data = pict.getData();
						out.write(data);
						out.close();
						String hostname= InetAddress.getLocalHost().getHostName();
						String addr=InetAddress.getLocalHost().getHostAddress();
						
						String appendContent=StringEscapeUtils.escapeHtml4("<p style=\"text-align: center; \"><img src='"+rootUrl+"/"+filePath+"'></p>");
						question.setContent(question.getContent()+appendContent);
					}

				}

			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("exception : " + e.toString());
		}

	}

	@Override
	public List<Question> mapToModel(Sheet sheet) {
		// TODO Auto-generated method stub
		return mapFields(sheet);
	}

}
