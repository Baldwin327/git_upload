package com.cathaybk.practice.nt50352.b;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class BufferWriter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Employee> employeeList = new ArrayList<>();
		employeeList.add(new Sales("張志城", "信用卡部", 35000, 6000));
		employeeList.add(new Sales("林大鈞", "保代部", 38000, 4000));
		employeeList.add(new Supervisor("李中白", "資訊部", 65000));
		employeeList.add(new Supervisor("林小中", "理財部", 80000));
		for (Employee employee : employeeList) {
			employee.printInfo();
		}

		// 路徑
		String filePath = "C:\\Users\\Admin\\Desktop\\Java 班\\git_upload\\output.csv";

		// BufferWriter
		try (BufferedWriter writer = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(filePath, true), "UTF-8"))) {
                 
			writer.write(new String(new byte[] {(byte) 0xEF, (byte) 0xBB,(byte) 0xBF}));//亂碼修正
			
			// 寫入Name、Payments
//			writer.write("Name,Payment");
//			writer.newLine();//換行

			// List裡的員工資料
			for (Employee employee : employeeList) {
				if(employee instanceof Sales) {
				writer.write(String.format("%s,%d", employee.getName(), ((Sales)employee).getPayments()));//強轉型 employee-Sales
				writer.newLine();
				}else if (employee instanceof Supervisor) {
					writer.write(String.format("%s,%d", employee.getName(), ((Supervisor)employee).getPayment()));//強轉型 employee-Supervisor
					writer.newLine();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
