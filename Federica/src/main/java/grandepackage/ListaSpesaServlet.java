package grandepackage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/listaSpesa")
public class ListaSpesaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 String risposta;

    
   

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getQueryString();
		String queryString = request.getQueryString();
		String[] arrayValoreParametri =
				queryString.split("=");
		String valoreParametro = arrayValoreParametri[1];
		
		
		risposta = "<html><body>";
		
		if(valoreParametro.equals("ciao")) {
			risposta+="<h1> Ciao anche a voi</h1>";
		}else {
			risposta+= "<p>Benvenuto nel notro sito</p>";
		}
		risposta+="</body></html>";
		
		
		//invia risposta
		response.getWriter().append("Served at: ").append("<hml><body></body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
