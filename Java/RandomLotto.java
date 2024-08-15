package com.cathaybk.practice.nt50352.b;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

/**
 * 第二題答案
 */

public class RandomLotto {

	public static void main(String[] args) {
		doRandomSet();
	}

	public static void doRandomSet() {
		Set<Integer> results = new HashSet<>();
		Random random = new Random();

		while (results.size() < 6) { 
			int n = random.nextInt(49) + 1;
			results.add(n);
		}

		System.out.print("排序前 ");
		for (Integer number : results) {
			System.out.print(" " + number);
		}

		Set<Integer> results2 = new TreeSet<>(results);

		System.out.print("\n排序後 ");
		for (Integer number : results2) {
			System.out.print(" " + number);
		}
	}
}
