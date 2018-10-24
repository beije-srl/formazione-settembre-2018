package grandepackage.controller;

import java.util.Random;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/controller")
public class ListaSpesaController {
	
	@GetMapping("/paginaCasuale")
	public String paginaCasuale() {
		
		//tira una moneta
		Random r = new Random();
		boolean casuale = r.nextBoolean();
		
		//se true, torna a una pagina, altrimenti l'altra.
		if(casuale) {
			return "paginaUno";
		}else {
			return "paginaDue";
		}
	}
}
