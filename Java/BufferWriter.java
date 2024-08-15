package com.cathaybk.practice.nt50352.b;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class BufferWriter {

	public static void main(String[] args) {
		List<Employee> employeeList = new ArrayList<>();
		employeeList.add(new Sales("張志城", "信用卡部", 35000, 6000));
		employeeList.add(new Sales("林大鈞", "保代部", 38000, 4000));
		employeeList.add(new Supervisor("李中白", "資訊部", 65000));
		employeeList.add(new Supervisor("林小中", "理財部", 80000));
		for (Employee employee : employeeList) {
			employee.printInfo();
		}

		String filePath = "C:\\Users\\Admin\\Desktop\\Java 班\\git_upload\\output.csv";

		try (BufferedWriter writer = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(filePath, true), "UTF-8"))) {

			writer.write(new String(new byte[] { (byte) 0xEF, (byte) 0xBB, (byte) 0xBF }));

			
			for (Employee employee : employeeList) {
				writer.write(employee.getName()+",");
				if (employee instanceof Sales) {
					writer.write(String.valueOf(((Sales) employee).getPayments()));// 強轉型
				} else if (employee instanceof Supervisor) {
					writer.write(String.valueOf(((Supervisor) employee).getPayment()));// 強轉型
				}
				writer.newLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
