package com.test.Funzionanti;

import java.util.Scanner;

public class Fib {

	public static void main(String[] args) {
		System.out.println("Inserire numero");
		Scanner n = new Scanner(System.in);
		int num = n.nextInt();
		n.close();

     	long n_1 = 0;
		long n_2 = 1;
		for (int i = 0; i < num; i++) {
		

			long nm = n_1 + n_2;
			n_2 = n_1;
			n_1 = nm;
			
			System.out.println(nm);
		}
	}
}
