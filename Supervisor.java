package com.cathaybk.practice.nt50352.b;

public class Supervisor extends Employee {
	private int payment;

    public Supervisor() {
		
	}

	public Supervisor(String name,String department,int salary) {
		super(name,department,salary);		
	}

	public int getPayment() {
		return super.getSalary();
	}

	public void setPayment(int payment) {
		this.payment = payment;
	}
	
	public void printInfo() {
		super.printInfo();
		System.out.println("總計:" +  getPayment());
	}
}
