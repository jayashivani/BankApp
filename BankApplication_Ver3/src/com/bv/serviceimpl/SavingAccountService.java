package com.bv.serviceimpl;

import java.util.Scanner;

import com.bv.model.SavingAccount;
import com.bv.service.ITransaction;
import com.exceptions.InsufficientBalanceException;


public class SavingAccountService implements ITransaction {

	SavingAccount savingAccount;

		public SavingAccountService(SavingAccount savingAccount) {
		super();
		this.savingAccount = savingAccount;
		if (!savingAccount.isSalaryAccount())
		{
			savingAccount.setAcc_type("Saving");
			set();
		}
		else
		{
			savingAccount.setAcc_type("Salary");
		}
	}
		private void set()
		{
			savingAccount.setLimit_charges(100);
			savingAccount.setMin_balance(1000);
		}
	@Override
	public void withdraw(double amount) {
		System.out.println("Before withdraw:" + savingAccount.getBalance());
		if (amount < 0) {
			System.out.println("\n" + "------------------------SMS-------------------------------");
			try {
				throw new InsufficientBalanceException("Invalid Transaction amount cannot be negative");
			} catch (InsufficientBalanceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (savingAccount.getBalance() < amount) {
			System.out.println("\n" + "------------------------SMS-------------------------------");
			try {
				throw new InsufficientBalanceException("Insufficient fund balance");
			} catch (InsufficientBalanceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			savingAccount.setBalance(savingAccount.getBalance() - amount);
			System.out.println("\n" + "------------------------SMS-------------------------------");
			System.out.println("Your account XXXXXXX" + savingAccount.getAcc_no()%10 + " has been debited with Rs." + amount);
		if(savingAccount.getBalance() - savingAccount.getLimit_charges() > 0 && savingAccount.isSalaryAccount()==false)
		{
			if (savingAccount.getBalance() < savingAccount.getMin_balance()) {
				savingAccount.setBalance(savingAccount.getBalance() - savingAccount.getLimit_charges());
				System.out.println("Chargers applied for not maintining min balance: " + savingAccount.getLimit_charges());
			}
		}
		else if ( savingAccount.isSalaryAccount()==false)
		{
			System.out.println("Limit charges of "+savingAccount.getLimit_charges()+" rs will be debited in next transaction");
			savingAccount.setBalance(savingAccount.getBalance()-savingAccount.getLimit_charges());
		}

		if(savingAccount.getBalance() < 0)
			System.out.println("and Your account balance is " + 0.0);
		else
			System.out.println("and Your account balance is " + savingAccount.getBalance());
			
		}
	}
	@Override
	public void deposite(double amount) {

		System.out.println("Before Depoite: " + savingAccount.getBalance());
		savingAccount.setBalance(savingAccount.getBalance() + amount);
		System.out.println(amount + " Rs. Amount credited");
		System.out.println("Total balance after deposite " + savingAccount.getBalance());
	}
	public void chooseOperation() {
		Scanner sc = null;
		int choice;
		boolean flag = true;
			while (flag) {

				System.out.println("\n" + "Select one of the options below");
				System.out.println("1. Withdraw" + "\n" + "2. Deposite" + "\n" + "3. Display Details" + "\n" + "4. Exit");
				sc = new Scanner(System.in);
				choice = sc.nextInt();
				switch (choice) {
				case 1: {
					System.out.println("Enter amount to be withdraw\n");
					withdraw(sc.nextDouble());
					break;
				}

				case 2: {
					System.out.println("Enter amount to be deposite\n");
					deposite(sc.nextDouble());
					break;
				}
				case 3:
					System.out.println(savingAccount);
					break;

				case 4: {
					System.out.println("Transaction complete\n");
					flag = false;
					 break;
				}
				default: {
					System.out.println("Enter Valid operation\n");
					break;
				}
				}

			}
	
	}
}
