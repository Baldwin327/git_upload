package com.cathaybk.practice.nt50352.b;

public class Sales extends Employee {
	private int bonus;
	private int payments;
	
    public Sales() {
		
	}
	public Sales(String name,String department,int salary,int bonus) {
		super(name,department,salary);
		this.bonus = bonus;
	}

	public int getBonus() {
		return (int) (bonus*0.05);
	}

	public void setBonus(int bonus) {
		this.bonus = bonus;
	}

	public int getPayments() {
		return super.getSalary()+getBonus();
	}

	public void setPayments(int payments) {
		this.payments = payments;
	}
	
	public void printInfo() {
		super.printInfo();
		System.out.println("業績獎金:"+ getBonus() + "\n總計:" + getPayments());
	}
}
