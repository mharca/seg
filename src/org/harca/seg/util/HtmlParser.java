package org.harca.seg.util;
import java.io.*;
import java.net.MalformedURLException;
import java.util.List;
/*
import org.jsoup.*;

import org.jsoup.Connection.Response;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;
*/
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.CookieManager;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
//import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
//import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.html.*;

public class HtmlParser {
		String site = "http://portalpetrobras.petrobras.com.br/PetrobrasPortal/appmanager/portal/desktop?_nfpb=true&_pageLabel=home_a_petrobras";
		Worker empregado = new Empregado();

		public HtmlParser(String matricula){
			WebClient webclient = new WebClient(BrowserVersion.FIREFOX_45);
		//	webclient.getOptions().setJavaScriptEnabled(true);
		//	webclient.setAjaxController(new NicelyResynchronizingAjaxController());
		    webclient.getOptions().setThrowExceptionOnFailingStatusCode(false);
/*
			CookieManager cookieManager = webclient.getCookieManager();
			cookieManager.setCookiesEnabled(true);
			webclient.getOptions().setRedirectEnabled(true);
	*/		
			try {
				final HtmlPage startPage = webclient.getPage(site);
							
				//List<HtmlForm> listaForm = startPage.getForms();
			//	HtmlForm form = listaForm.get(0);
				
						
				startPage.getElementByName("buscar").click();
				HtmlTextInput input = (HtmlTextInput) startPage.getElementById("txt-buscar");
				input.setValueAttribute(matricula);
				HtmlRadioButtonInput radio = (HtmlRadioButtonInput) startPage.getElementById("radio-lope");
				
				radio.setChecked(true);
				radio.click();
				
				HtmlSubmitInput submit = (HtmlSubmitInput) startPage.getElementByName("botao");
				
				HtmlPage site2 = submit.click();

				final List<FrameWindow> lfw = site2.getFrames();
				final HtmlPage p2 = (HtmlPage) lfw.get(1).getEnclosedPage();
				
				
				
			//	DomElement element = (DomElement) p2.getByXPath("//div[@class='span9']").get(0);
			//	empregado.setNome(element.asText());
				DomElement element;
				
				try{
					element = (DomElement) p2.getByXPath("//div[@class='span9']").get(1);
					empregado.setChave(element.asText());
				}catch(Exception e){
					empregado.setChave("Sem chave.");
				}
				element = (DomElement) p2.getByXPath("//div[@class='row-fluid']").get(4);
				empregado.setNome(element.asText());
				
			//	element = (DomElement) p2.getByXPath("//div[@class='row-fluid']").get(9);
				
				
			//	System.out.println("999999999999"+empregado.getNome());
				
				
				
				element = (DomElement) p2.getByXPath("//div[@class='span9']").get(2); // Genero
				empregado.setGenero(element.asText());
				
				element = (DomElement) p2.getByXPath("//div[@class='span9']").get(3); // Email
				empregado.setEmail(element.asText());
				
				element = (DomElement) p2.getByXPath("//div[@class='span9']").get(4); // Empresa
				empregado.setEmpresa(element.asText());
				
				element = (DomElement) p2.getByXPath("//div[@class='span9']").get(5); // Pais
				empregado.setPais(element.asText());
				
				element = (DomElement) p2.getByXPath("//div[@class='span9']").get(6); // Matricula
				empregado.setMatricula(element.asText());
				
				element = (DomElement) p2.getByXPath("//div[@class='span9']").get(7); // Cargo
				empregado.setCargo(element.asText());
				
				element = (DomElement) p2.getByXPath("//div[@class='span9']").get(8); // Imovel
				empregado.setImovel(element.asText());
				
				element = (DomElement) p2.getByXPath("//div[@class='span4']").get(1); // Ramal
				empregado.setRamal(element.asText());
				
		//		element = (DomElement) p2.getByXPath("//div[@class='span9']").get(10); // Endereco
		//		empregado.setEndereco(element.asText());
				
		//		element = (DomElement) p2.getByXPath("//div[@class='span9']").get(11); // Lotacao
		//		empregado.setLotacao(element.asText());
				
				/*
				for(int i=0;i< 9;i++){
					//element = (DomElement) p2.getByXPath("//div[@class='span9']").get(i);
					element = (DomElement) p2.getByXPath("//div[@class='row-fluid']").get(i);
					System.out.println(i+" - "+element.asText());
				}
				*/
			
				/*
				element = (DomElement) p2.getByXPath("//div[@class='row-fluid']").get(8);
				System.out.println("**********"+element.asText());
					*/			
				
			} catch (FailingHttpStatusCodeException e) {
				System.out.println("1");
				e.printStackTrace();
			} catch (MalformedURLException e) {
				System.out.println("2");
				//e.printStackTrace();
			} catch (IOException e) {
				System.out.println("3");
				//e.printStackTrace();
			}
			System.out.println("FIMFIMFIMFIMFIMFIMFIMFIMFIMFIMFIMFIMFIMFIMFIMFIMFIMFIMFIMFIMFIMFIMFIMFIM ");
			webclient.close();
		}
		
		public String getNome(){
			return empregado.getNome();
		}
		public String getRamal(){
			return empregado.getRamal();
		}
		public String getMatricula(){
			return empregado.getMatricula();
		}
		public String getEmpresa(){
			return empregado.getEmpresa();
		}
		
}
	

