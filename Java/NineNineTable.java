package com.cathaybk.practice.nt50352.b;

/**
 * 第1題答案
 */
public class NineNineTable {

	public static void main(String[] args) {
		for (int i = 1; i < 10; i++) {// 乘數放外面 因為被乘數先迴圈
			for (int j = 2; j < 10; j++) { // 被乘數
				System.out.printf("%d*%d =%2d ", j, i, i * j);

			}
			System.out.println();
		}

	}

}
