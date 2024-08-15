package com.cathaybk.practice.nt50352.b;

/**
 * 第6題答案
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class CarsCollection {

	public static void main(String[] args) {
		String filepath = "C:\\Users\\Admin\\Desktop\\cars.csv";

		try (FileReader fr = new FileReader(filepath); BufferedReader br = new BufferedReader(fr);) {// 讀資料
			List<Map<String, String>> dataList = new ArrayList<>();

			String firstLine = br.readLine();
			String[] columnNames = firstLine.split(",");

			String line = br.readLine();
			while ((line = br.readLine()) != null) {
				String[] values = line.split(",");
				Map<String, String> dataMap = new HashMap<>();
				for (int i = 0; i < columnNames.length; i++) {
					dataMap.put(columnNames[i], values[i]);
				}
				dataList.add(dataMap);
			}

			Collections.sort(dataList, new Comparator<Map<String, String>>() {

				@Override 
				public int compare(Map<String, String> car1, Map<String, String> car2) {
					BigDecimal price1 = new BigDecimal(car1.get("Price"));
					BigDecimal price2 = new BigDecimal(car2.get("Price"));
					return price2.compareTo(price1);
				}
			});
			System.out.println(dataList);

			String results = "C:\\Users\\Admin\\Desktop\\Java 班\\git_upload\\cars2.csv";
			try (BufferedWriter writer = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(results, true), "UTF-8"))) {

				writer.write(firstLine);// 欄位
				writer.newLine();
				for (Map<String, String> dataMap : dataList) {
					StringBuilder sb = new StringBuilder();
					for (String column : columnNames) {
						sb.append(dataMap.get(column));
					}
					writer.write(sb.toString());
					writer.newLine();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			// 6-2題
			Set<String> manufacturerSet = new TreeSet<>();
			for (Map<String, String> entry : dataList) {
				String manufacturer = entry.get("Manufacturer");
				manufacturerSet.add(manufacturer);
			}

			System.out.printf("%10s\t%5s\t%5s %2s", "MANUFACTURER", "TYPE", "MIN.PRICE", "PRICE");
			System.out.println();
			BigDecimal TotalPrice = BigDecimal.ZERO;
			BigDecimal TotalMinPrice = BigDecimal.ZERO;

			for (String brand : manufacturerSet) {
				BigDecimal SubTotalPrice = BigDecimal.ZERO;
				BigDecimal SubTotalMinPrice = BigDecimal.ZERO;

				for (Map<String, String> dataMap : dataList) {

					if (dataMap.get("Manufacturer").equals(brand)) {
						System.out.printf("%-10s\t%-5s\t%5s\t%5s", dataMap.get("Manufacturer"), dataMap.get("Type"),
								dataMap.get("Min.Price"), dataMap.get("Price"));
						System.out.println();
						SubTotalMinPrice = SubTotalMinPrice.add(new BigDecimal(dataMap.get("Min.Price")));
						SubTotalPrice = SubTotalPrice.add(new BigDecimal(dataMap.get("Price")));
						TotalMinPrice = TotalMinPrice.add(new BigDecimal(dataMap.get("Min.Price")));
						TotalPrice = TotalPrice.add(new BigDecimal(dataMap.get("Min.Price")));
					}
				}
				System.out.printf("小計\t\t\t%5s\t%5s", SubTotalMinPrice, SubTotalPrice);
				System.out.println();
			}
			System.out.printf("合計\t\t\t%5s\t%5s", TotalMinPrice, TotalPrice);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}