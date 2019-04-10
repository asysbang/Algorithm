package com.asysbang.algorithm.base;

import java.awt.image.BufferedImage;

public class Gaussian {

	public void blur(BufferedImage image) {
		int width = image.getWidth();
		int height = image.getHeight();
		int type = image.getType();
		System.out.println("====type = " + type);
		BufferedImage resImage = new BufferedImage(width, height, type);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				int pixel = image.getRGB(x, y);
				if (type == BufferedImage.TYPE_3BYTE_BGR) {
					int blue = pixel & 0xFF;
					int green = (pixel >> 8) & 0xFF;
					int red = (pixel >> 16) & 0xFF;
					if(x == 36 && y ==200) {
						System.out.println("====bgr = " + blue +" , "+green+" , "+red);
					}

				}

			}
		}
	}

	/**
	 * 
	 * @param radius
	 * @param sigma
	 * @return
	 * @throws Exception
	 */
	public double[][] getKernel(int radius, double sigma) throws Exception {
		return getKernel(radius, radius, sigma, sigma);
	}

	/**
	 * 
	 * @param xKernelSize 卷积核的x方向大小
	 * @param yKernelSize 卷积核的y方向大小
	 * @param sigmaX      卷积核的x方向方差
	 * @param sigmaY      卷积核的y方向方差
	 * @return
	 * @throws Exception
	 */
	public double[][] getKernel(int xKernelSize, int yKernelSize, double sigmaX, double sigmaY) throws Exception {
		if (xKernelSize % 2 == 0 || yKernelSize % 2 == 0) {
			// 卷积核x，y方向大小必须是奇数，才能确定半径
			throw new Exception("kernel size must odd");
		}
		if (sigmaX < 0 || sigmaY < 0) {
			throw new Exception("sigma must > 0");
		}
		// ??? sigmaY>0 sigmaX =0 ????
		if (sigmaY == 0) {
			if (sigmaX > 0) {
				sigmaY = sigmaX;
			} else {
				sigmaY = sigmaX;
			}
		}
		double[][] kernel = new double[xKernelSize][yKernelSize];
		int xRadius = xKernelSize / 2;
		int yRadius = yKernelSize / 2;
		System.out.println("====xRadius = " + xRadius + ", yRadius = " + yRadius);
		double sum = 0;
		// y需要倒序，保证xy对应的点符合坐标系
		// 否则是x轴对称的排列方式
		for (int y = yKernelSize - 1; y >= 0; y--) {
			for (int x = 0; x < xKernelSize; x++) {
				// ?????? sigma 的平方在xy方向上怎么处理
				// -(x*x + y*y) / (2 sigma * sigma)
				double tem = (-(x - xRadius) * (x - xRadius) - (y - yRadius) * (y - yRadius)) / (2.0 * sigmaX * sigmaX);
//				System.out.println("====tem = "+tem);
				kernel[x][yKernelSize - 1 - y] = Math.exp(tem) / (2 * Math.PI * (sigmaX * sigmaX));
				System.out.print("    " + kernel[x][yKernelSize - y - 1]);
				sum += kernel[x][yKernelSize - y - 1];
			}
			System.out.println("");
		}
		System.out.println("====sum = " + sum);
		for (int y = 0; y < yKernelSize; y++) {
			for (int x = 0; x < xKernelSize; x++) {
				kernel[x][y] = kernel[x][y] / sum;
				System.out.print("    " + kernel[x][y]);
			}
			System.out.println("");
		}
		return kernel;
	}
}

//<p>
//*  \name Border processing modes
//*  \sa cv::BorderTypes
//*  \{ */
//public static final int CV_HAL_BORDER_CONSTANT = 0;
//public static final int CV_HAL_BORDER_REPLICATE = 1;
//public static final int CV_HAL_BORDER_REFLECT = 2;
//public static final int CV_HAL_BORDER_WRAP = 3;
//public static final int CV_HAL_BORDER_REFLECT_101 = 4;
//public static final int CV_HAL_BORDER_TRANSPARENT = 5;
//public static final int CV_HAL_BORDER_ISOLATED = 16;

///** \brief Blurs an image using a Gaussian filter.
//<p>
//The function convolves the source image with the specified Gaussian kernel. In-place filtering is
//supported.
//<p>
//@param src input image; the image can have any number of channels, which are processed
//independently, but the depth should be CV_8U, CV_16U, CV_16S, CV_32F or CV_64F.
//@param dst output image of the same size and type as src.
//@param ksize Gaussian kernel size. ksize.width and ksize.height can differ but they both must be
//positive and odd. Or, they can be zero's and then they are computed from sigma.
//@param sigmaX Gaussian kernel standard deviation in X direction.
//@param sigmaY Gaussian kernel standard deviation in Y direction; if sigmaY is zero, it is set to be
//equal to sigmaX, if both sigmas are zeros, they are computed from ksize.width and ksize.height,
//respectively (see #getGaussianKernel for details); to fully control the result regardless of
//possible future modifications of all this semantics, it is recommended to specify all of ksize,
//sigmaX, and sigmaY.
//@param borderType pixel extrapolation method, see #BorderTypes
//<p>
//\sa  sepFilter2D, filter2D, blur, boxFilter, bilateralFilter, medianBlur
// */
//@Namespace("cv") public static native void GaussianBlur( @ByVal Mat src, @ByVal Mat dst, @ByVal Size ksize,
//                                double sigmaX, double sigmaY/*=0*/,
//                                int borderType/*=cv::BORDER_DEFAULT*/ );
//@Namespace("cv") public static native void GaussianBlur( @ByVal Mat src, @ByVal Mat dst, @ByVal Size ksize,
//                                double sigmaX );
//@Namespace("cv") public static native void GaussianBlur( @ByVal UMat src, @ByVal UMat dst, @ByVal Size ksize,
//                                double sigmaX, double sigmaY/*=0*/,
//                                int borderType/*=cv::BORDER_DEFAULT*/ );
//@Namespace("cv") public static native void GaussianBlur( @ByVal UMat src, @ByVal UMat dst, @ByVal Size ksize,
//                                double sigmaX );
//@Namespace("cv") public static native void GaussianBlur( @ByVal GpuMat src, @ByVal GpuMat dst, @ByVal Size ksize,
//                                double sigmaX, double sigmaY/*=0*/,
//                                int borderType/*=cv::BORDER_DEFAULT*/ );
//@Namespace("cv") public static native void GaussianBlur( @ByVal GpuMat src, @ByVal GpuMat dst, @ByVal Size ksize,
//                                double sigmaX );
