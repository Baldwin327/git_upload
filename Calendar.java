package com.cathaybk.practice.nt50352.b;

/**
 * 第5題答案
 */

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Scanner;

public class Calendar {

	public static void main(String[] args) {
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.print("輸入介於1-12的整數m : ");
			int year = LocalDate.now().getYear();
			int month = scanner.nextInt();
			System.out.printf("\t\t%d年%d月", year, month);
			System.out.print("\n---------------------------------------------------");

			LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);// 取得月份第一天
			int numberOfDaysInMonth = firstDayOfMonth.lengthOfMonth();// 當月長度
			DayOfWeek firstDayOfWeek = firstDayOfMonth.getDayOfWeek();
			int dayOfWeekValue = firstDayOfWeek.getValue();// 拿到值 1=禮拜一 7=禮拜日

			System.out.println("\n日\t一\t二\t三\t四\t五\t六");
			System.out.println("===================================================");
			int offset = (dayOfWeekValue % 7);// 如果是0就是禮拜日 有0-6
			for (int i = 1; i <= offset; i++) {
				System.out.print(" \t");// 印第一排空格
			}

			for (int day = 1; day <= numberOfDaysInMonth; day++) {
				System.out.printf("%2d\t", day);
				if ((offset + day) % 7 == 0) {
					System.out.println();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
