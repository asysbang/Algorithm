package com.asysbang.algorithm;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import com.asysbang.algorithm.base.Gaussian;
import com.asysbang.algorithm.base.ImageUtil;
import com.asysbang.algorithm.test.GaussianTest;

public class Main {

	public static void main(String[] args) throws Exception {
		System.out.println("====");
		Gaussian g = new Gaussian();
		try {
			g.getKernel(3, 3, 1.5, 1.5);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//先处理  正方形的卷积
		BufferedImage image = ImageUtil.loadImage("a2.jpg");
		BufferedImage blur = g.blur(image,11,1.5);
		ImageIO.write(blur, "png", new File("res11.jpg"));
		
		runTest();
		
	}
	
	
	public static void runTest() throws Exception {
		GaussianTest test = new GaussianTest();
		test.testGetWeightSum();
	}

}
