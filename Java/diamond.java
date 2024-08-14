package com.cathaybk.javaedu.lesson1.practice;

public class diamond {

	public static void main(String[] args) {

		// 上半部
		/**
		 * i = 1 2 3 4 5 
		 * j = 4 3 2 1 0  i + j = 5 
		 * k = 1 3 5 7 9  2*i - 1 = k
		 */
		for (int i = 1; i <= 5; i++) {
			// 空格
			for (int j = 1; j <= 5 - i; j++) {
				System.out.print(" ");
			}
			// *
			for (int k = 1; k <= 2 * i - 1; k++) {
				System.out.print("*");
			}
			System.out.println();
		}

		// 下半部
		/**
		 * i = 1 2 3 4 
		 * j = 1 2 3 4 
		 * k = 7 5 3 1 2*i + k = 9
		 */
		for (int i = 1; i <= 4; i++) {
			// 空格
			for (int j = 1; j <= i; j++) {
				System.out.print(" ");
			}
			// *
			for (int k = 1; k <= 9 - 2 * i; k++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}

}
