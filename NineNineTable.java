package com.cathaybk.practice.nt50352.b;

public class NineNineTable {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(int i=1 ; i<10 ; i++) {// 乘數放外面 因為被乘數先迴圈
			for(int j=2 ; j<10 ; j++) { //被乘數
				System.out.printf("%d*%d =%2d ",j,i,i*j);
				
			}System.out.println();
		}

	}

}
