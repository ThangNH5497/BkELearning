package bk.elearning.utils;

import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;


public class OpenCvUtil {

	public OpenCvUtil() {

	}

	public void test(int number) {
		/// tesst opencv

		/*
		 * nu.pattern.OpenCV.loadShared(); Mat mat =
		 * Imgcodecs.imread("C:\\Users\\ThangNh\\Pictures\\bg.jpg");
		 * 
		 * Imgproc.rectangle(mat, new Point(10, 10), new Point(100, 100), new Scalar(0,
		 * 255, 0)); System.out.println("opencv Here");
		 * Imgcodecs.imwrite("D:\\result_test.jpg", mat);
		 */
		nu.pattern.OpenCV.loadShared();
		System.out.println("...started");

		Mat source = Imgcodecs.imread("D:\\sheet-"+String.valueOf(number)+".jpg");

		Scanner scanner = new Scanner(source, 20);
		scanner.setLogging(true);
		try {
			scanner.scan();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("...finished");
	}
}
