package com.asysbang.algorithm;

import java.awt.image.BufferedImage;

import com.asysbang.algorithm.base.Gaussian;
import com.asysbang.algorithm.base.ImageUtil;

public class Main {

	public static void main(String[] args) {
		System.out.println("====");
		Gaussian g = new Gaussian();
		try {
			g.getKernel(3, 3, 1.5, 1.5);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		BufferedImage image = ImageUtil.loadImage("a2.jpg");
		g.blur(image);
		
	}

}
