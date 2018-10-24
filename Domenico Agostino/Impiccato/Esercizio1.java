package Stringhe;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Esercizio1 {
	
	public static void main(String[] args) {
		String Indovinare = "";
		int vinto = 0;
		
		Scanner cin = new Scanner(System.in);
		
		System.out.println("Parola da indovinare: ");
		Indovinare = cin.next();
		Indovinare = Indovinare.toLowerCase();
		
		List<Character> l = new ArrayList<>();
				
		for(int i = 0; i < Indovinare.length();i++) {
			l.add('_');
		}
		
		System.out.println("Hai 6 tentativi!");
		
		
		for(int i = 1; i < 7 && vinto == 0; i++) {
			System.out.println(l);
			int risposta = 0;
			System.out.println("Tentativo numero " + i );
			do {
				System.out.println("Lettera o Soluzione? \n1:Lettera \n2:Soluzione");
				cin.nextLine();
				risposta = cin.nextInt();
			}while(risposta != 1 && risposta != 2);
			
			if(risposta == 1) {
				System.out.println("Inserisci lettera: ");
				cin.nextLine();
				Character lettera = cin.next().charAt(0);
				//lower case
					if(Indovinare.indexOf(lettera) != -1){
						System.out.println("Hai indovinato");
						l.set(Indovinare.indexOf(lettera), lettera);
						//creo indice
						int i2 = Indovinare.indexOf(lettera);
						//creo una sottostringa
						String temp = Indovinare.substring(i2 + 1);
						//vedo se ce ne sono altri
						while(true) {
							if(temp.indexOf(lettera) != -1) {
								l.set((temp.indexOf(lettera) + 1) + i2, lettera);
								i2 += temp.indexOf(lettera) + 1;
								if(i2 < Indovinare.length()) {
									temp = temp.substring(temp.indexOf(lettera) + 1);
								}
							}else {
								break;
							}
						}
					}else {
						System.out.println("Hai Sbagliato");
					}
			}
			else {
				String temp = " ";
				System.out.println("Inserisci Risposta:");
				cin.nextLine();
				temp = cin.next();
				if(temp.equals(Indovinare)) {
					vinto = 1;
				}else {
					vinto = 2;
				}
			}
			
			
		}
		
		if(vinto == 1) {
			System.out.println("Hai vinto");
		}else {
			System.out.println("Hai perso");
			System.out.println(""
					+ "      _______\r\n" + 
					"    |/      |\r\n" + 
					"    |      (_)\r\n" + 
					"    |      \\|/\r\n" + 
					"    |       |\r\n" + 
					"    |      / \\\r\n" + 
					"    |\r\n" + 
					"   _|___");
		}
		
	cin.close();
	
	}
}