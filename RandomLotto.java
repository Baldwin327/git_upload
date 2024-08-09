package com.cathaybk.practice.nt50352.b;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomLotto {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		doRandomSet();
	}

	public static void doRandomSet() {
		List<Integer> results = new ArrayList<>();
		Random random = new Random();
		while (results.size() < 6) { // 6個數字
			int n = random.nextInt(49) + 1;// 產生1-49
			if (results.contains(n)) {
				continue;
			} else {
				results.add(n);
			}
		}
		System.out.print("排序前 ");
		for (int i = 0; i < results.size(); i++) {
			int element = results.get(i);
			System.out.print(element + " ");
		}
		
		Collections.sort(results);
		
		System.out.print("\n排序後 ");
		for (int j = 0; j < results.size(); j++) {
			int element2 = results.get(j);
			System.out.print(element2 + " ");
		}

	}
}
