package com.asysbang.algorithm.test;

import com.asysbang.algorithm.base.Gaussian;

public class GaussianTest {

	public void testGetWeightSum() throws Exception {
		Gaussian g = new Gaussian();
		int[][] data = { { 14, 15, 16 }, { 24, 25, 26 }, { 34, 35, 36 } };
		double[][] kernel = g.getKernel(1, 1.5);
		double weightSum = g.getWeightSum(data, kernel);
		System.out.println("====weightSum = " + weightSum);//should  = 25.0
	}

}
