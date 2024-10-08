package com.cathaybk.practice.nt50352.b;

/**
 * 第7題答案
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Db {

	private static final String DB_CONNECTION_URL = "jdbc:oracle:thin:@//localhost:1521/XE";

	private static final String QUERY_CARSALL_SQL = "select * from STUDENT.CARS";

	private static final String QUERY_CARS_SQL = "select * from STUDENT.CARS where MANUFACTURER = ? and TYPE = ?";

	private static final String INSERT_CARS_SQL = "insert into STUDENT.CARS (MANUFACTURER, TYPE, MIN_PRICE, PRICE) values (?, ?, ?, ?)";

	private static final String UPDATE_CARS_SQL = "update STUDENT.CARS set PRICE = ?, MIN_PRICE = ? where MANUFACTURER = ? and TYPE = ?";

	private static final String DELETE_CARS_SQL = "delete from STUDENT.CARS where MANUFACTURER = ? and TYPE = ?";

	private static final String ACCOUNT_NUMBER = "student";

	private static final String PASSWORD = "student123456";

	public static void main(String[] args) {
		doAllQuery();
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.print("請選擇以下指令輸入:select、insert、update、delete ");
			String enter = scanner.next();

			switch (enter) {
			case "select":
				doQuery();
				break;
			case "insert":
				doInsert();
				break;
			case "update":
				doUpdate();
				break;
			case "delete":
				doDelete();
				break;
			default:
				System.out.println("輸入錯誤");
				break;
			}
		}
	}

	private static void doAllQuery() {
		try (Connection conn = DriverManager.getConnection(DB_CONNECTION_URL, ACCOUNT_NUMBER, PASSWORD);) {
			PreparedStatement pstmt = conn.prepareStatement(QUERY_CARSALL_SQL);
			ResultSet rs = pstmt.executeQuery();

			List<Map<String, String>> maplist = new ArrayList<>();

			while (rs.next()) {
				Map<String, String> map = new HashMap<>();
				map.put("MANUFACTURER", rs.getString("MANUFACTURER"));
				map.put("TYPE", rs.getString("TYPE"));
				map.put("MIN_PRICE", rs.getString("MIN_PRICE"));
				map.put("PRICE", rs.getString("PRICE"));
				maplist.add(map);
			}
			System.out.println(maplist);
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void doQuery() {

		try (Connection conn = DriverManager.getConnection(DB_CONNECTION_URL, ACCOUNT_NUMBER, PASSWORD);
				Scanner scanner = new Scanner(System.in);) {

			PreparedStatement pstmt = conn.prepareStatement(QUERY_CARS_SQL);
			System.out.print("請輸入製造商 : ");
			String manufacturer = scanner.next();
			System.out.print("請輸入類別 : ");
			String type = scanner.next();

			pstmt.setString(1, manufacturer);
			pstmt.setString(2, type);

			ResultSet rs = pstmt.executeQuery();
			List<Map<String, String>> maplist = new ArrayList<>();

			while (rs.next()) {
				Map<String, String> map = new HashMap<>();
				map.put("MANUFACTURER", rs.getString("MANUFACTURER"));
				map.put("TYPE", rs.getString("TYPE"));
				map.put("MIN_PRICE", rs.getString("MIN_PRICE"));
				map.put("PRICE", rs.getString("PRICE"));
				maplist.add(map);
			}
			System.out.println(maplist);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void doInsert() {
		try (Connection conn = DriverManager.getConnection(DB_CONNECTION_URL, ACCOUNT_NUMBER, PASSWORD);
				Scanner scanner = new Scanner(System.in);) {// 第1層TRY-連線成功
			try {
				conn.setAutoCommit(false);
				PreparedStatement pstmt = conn.prepareStatement(INSERT_CARS_SQL);

				System.out.print("請輸入製造商 : ");
				String text = scanner.next();

				System.out.print("請輸入類別 : ");
				String text2 = scanner.next();

				System.out.print("請輸入底價 : ");
				int int1 = scanner.nextInt();

				System.out.print("請輸入售價 : ");
				int int2 = scanner.nextInt();

				pstmt.setString(1, text);
				pstmt.setString(2, text2);
				pstmt.setInt(3, int1);
				pstmt.setInt(4, int2);
				int k = pstmt.executeUpdate();
				if (k < 1) {
					System.out.println("新增失敗");
				} else {
					conn.commit();
					System.out.println("新增成功"); // 第2層TRY-新增成功
				}
			} catch (Exception e) {
				System.out.println("新增失敗，原因：" + e.getMessage());
				try {// 第3層TRY-rollback
					conn.rollback();
				} catch (SQLException sqle) {
					System.out.println("rollback 失敗，原因：" + sqle.getMessage());
				}
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	private static void doUpdate() {
		try (Connection conn = DriverManager.getConnection(DB_CONNECTION_URL, ACCOUNT_NUMBER, PASSWORD);
				Scanner scanner = new Scanner(System.in);) {
			try {
				conn.setAutoCommit(false);
				PreparedStatement pstmt = conn.prepareStatement(UPDATE_CARS_SQL);

				System.out.print("請輸入製造商 : ");
				String text = scanner.next();

				System.out.print("請輸入類別 : ");
				String text2 = scanner.next();

				System.out.print("請更新底價 : ");
				int int1 = scanner.nextInt();

				System.out.print("請更新售價 : ");
				int int2 = scanner.nextInt();

				pstmt.setString(3, text);
				pstmt.setString(4, text2);
				pstmt.setInt(2, int1);
				pstmt.setInt(1, int2);

				int k = pstmt.executeUpdate();
				if (k < 1) {
					System.out.println("更新失敗 查無此項");
				} else {
					conn.commit();
					System.out.println("更新成功"); 
				}
			} catch (Exception e) {
				System.out.println("更新失敗，原因：" + e.getMessage());
				try {
					conn.rollback();
				} catch (SQLException sqle) {
					System.out.println("rollback 失敗，原因：" + sqle.getMessage());
				}
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	private static void doDelete() {
		try (Connection conn = DriverManager.getConnection(DB_CONNECTION_URL, ACCOUNT_NUMBER, PASSWORD);
				Scanner scanner = new Scanner(System.in);) {
			try {
				conn.setAutoCommit(false);
				PreparedStatement pstmt = conn.prepareStatement(DELETE_CARS_SQL);

				System.out.print("請輸入製造商 : ");
				String text = scanner.next();

				System.out.print("請輸入類別 : ");
				String text2 = scanner.next();

				pstmt.setString(1, text);
				pstmt.setString(2, text2);
				int k = pstmt.executeUpdate();
				if (k < 1) {
					System.out.println("刪除失敗 查無此項");
				} else {
					conn.commit();
					System.out.println("刪除成功"); 
				}
			} catch (Exception e) {
				System.out.println("刪除失敗，原因：" + e.getMessage());
				try {
					conn.rollback();
				} catch (SQLException sqle) {
					System.out.println("rollback 失敗，原因：" + sqle.getMessage());
				}
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}
}
