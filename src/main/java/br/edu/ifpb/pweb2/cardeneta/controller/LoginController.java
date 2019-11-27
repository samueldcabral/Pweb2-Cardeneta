package br.edu.ifpb.pweb2.cardeneta.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifpb.pweb2.cardeneta.entity.Aluno;
import br.edu.ifpb.pweb2.cardeneta.entity.Professor;
import br.edu.ifpb.pweb2.cardeneta.repository.AlunoRepository;
import br.edu.ifpb.pweb2.cardeneta.repository.ProfessorRepository;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private ProfessorRepository professorRepository;
	
	@Autowired
	private AlunoRepository alunoRepository;
	
	@RequestMapping(method = RequestMethod.GET)
	public String loginForm(Model model, @CookieValue(value = "clogin", defaultValue = "") String clogin) {
        model.addAttribute("login", clogin);	
		return "login/login";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String validadeLogin(String login, String senha, String lembrar, HttpSession session, HttpServletRequest request, HttpServletResponse response, RedirectAttributes flash, Model model) {
		String proxPagina = null;
        String letra = login.substring(0, 1);
        if (letra.equalsIgnoreCase("a")) {
        	Aluno alunoBanco = alunoRepository.findByLogin(login);
          
          if (alunoBanco != null) {
            
            if (lembrar != null && lembrar.equalsIgnoreCase("sim")) {
              Cookie cookie = new Cookie("clogin", login);
              response.addCookie(cookie);
            } else {
        		for (Cookie cookie: request.getCookies()) {
        			if (cookie.getName().equals("clogin")) {
        				cookie.setValue(null);
        				cookie.setMaxAge(0);
        				response.addCookie(cookie);
        			}
        		}            		
            }

            if (login.equalsIgnoreCase(alunoBanco.getLogin()) && senha.equals(alunoBanco.getSenha())) {
        		session.setAttribute("aluno", alunoBanco);
        		model.addAttribute("turmas-aluno", alunoBanco.getTurmas());
        		proxPagina = "redirect:disciplinas";
        	  } else {
	            flash.addFlashAttribute("mensagem", "Login e/ou senha inválidos");
        		flash.addFlashAttribute("aluno", login);
        		proxPagina = "redirect:login";
          }
          } else {
	          flash.addFlashAttribute("mensagem", "Login e/ou senha inválidos");
	    	  flash.addFlashAttribute("aluno", login);
	    	  proxPagina = "redirect:login";
          }

        } else if (letra.equalsIgnoreCase("p")) {
        	Professor professorBanco = professorRepository.findByLogin(login);

          if (professorBanco != null) {

            if (lembrar != null && lembrar.equalsIgnoreCase("sim")) {
              Cookie cookie = new Cookie("clogin", login);
              response.addCookie(cookie);
            } else if (request.getCookies() != null) {   	
              for (Cookie cookie: request.getCookies()) {
                if (cookie.getName().equals("clogin")) {
                  cookie.setValue(null);
                  cookie.setMaxAge(0);
                  response.addCookie(cookie);
                }
              }	
            }
            
            if (login.equalsIgnoreCase(professorBanco.getLogin()) && senha.equals(professorBanco.getSenha())) {
              session.setAttribute("professor", professorBanco);
              model.addAttribute("turmas", professorBanco.getTurmas());
              proxPagina = "redirect:turmas";
            } else {
              flash.addFlashAttribute("mensagem", "Login e/ou senha inválidos");
              flash.addFlashAttribute("professor", login);
              proxPagina = "redirect:login";
            }
          } else {
              flash.addFlashAttribute("mensagem", "Login e/ou senha inválidos");
              flash.addFlashAttribute("professor", login);
              proxPagina = "redirect:login";
          }
        } else {
            flash.addFlashAttribute("mensagem", "Login e/ou senha inválidos");
            proxPagina = "redirect:/login";
        }
        return proxPagina;

	}
	
}
