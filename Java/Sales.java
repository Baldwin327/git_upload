package com.cathaybk.practice.nt50352.b;

public class Sales extends Employee {
	private int bonus;
	private int payments;

	public Sales() {

	}

	public Sales(String name, String department, int salary, int performance) {
		super(name, department, salary);
		this.bonus = (int) (performance * 0.05);
		this.payments = salary + this.bonus;
	}

	public int getBonus() {
		return bonus;
	}

	public void setBonus(int bonus) {
		this.bonus = bonus;
	}

	public int getPayments() {
		return payments;
	}

	public void setPayments(int payments) {
		this.payments = payments;
	}

	@Override
	public void printInfo() {
		super.printInfo();
		System.out.println("業績獎金:" + getBonus() + "\n總計:" + getPayments());
	}
}
