package br.edu.ifpb.pweb2.cardeneta.controller;

import java.util.Optional;

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
import br.edu.ifpb.pweb2.cardeneta.entity.Usuario;
import br.edu.ifpb.pweb2.cardeneta.repository.AlunoRepository;
import br.edu.ifpb.pweb2.cardeneta.repository.ProfessorRepository;
import br.edu.ifpb.pweb2.cardeneta.repository.UsuarioRepository;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private ProfessorRepository professorRepository;
	
	@Autowired
	private AlunoRepository alunoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@RequestMapping(method = RequestMethod.GET)
	public String loginForm(Model model, @CookieValue(value = "clogin", defaultValue = "") String clogin) {
		Usuario user = new Usuario();
		user.setLogin(clogin);
        model.addAttribute(user);	
		return "login/login";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String validadeLogin(Usuario user, String lembrar, HttpSession session, HttpServletRequest request, HttpServletResponse response, RedirectAttributes flash) {
		String proxPagina = null;
		Usuario usuarioBanco = usuarioRepository.findByLogin(user.getLogin());
		if (lembrar != null && lembrar.equalsIgnoreCase("sim")) {
			Cookie cookie = new Cookie("clogin", user.getLogin());
			response.addCookie(cookie);
		} else {
			if (request.getCookies() != null) {	
				for (Cookie cookie : request.getCookies()) {
					if (cookie.getName().equals("clogin")) {
						cookie.setValue(null);
						cookie.setMaxAge(0);
						response.addCookie(cookie);
					}
				}
			}
		}
		if (usuarioBanco != null) {		
			if (user.getLogin().equals(usuarioBanco.getLogin()) && user.getSenha().equals(usuarioBanco.getSenha())) {
				
				Professor prof = professorRepository.findByUsuarioId(Long.parseLong(usuarioBanco.getId().toString()));
				Aluno alu = alunoRepository.findByUsuarioId(Long.parseLong(usuarioBanco.getId().toString()));
				if (prof != null && alu == null) {
					session.setAttribute("professor", prof);
					System.out.println("login professor is " + prof.getNome() + " " + prof.getId());
					if(prof.IsCoordenador()) {
						proxPagina = "redirect:coordenador";
					}else {
						proxPagina = "redirect:turmas";
					}
				} else if (alu != null && prof == null) {
					session.setAttribute("aluno", alu);
					proxPagina = "redirect:disciplinas";
				} else {
					proxPagina = "redirect:login";
				}
			} else {
				flash.addFlashAttribute("mensagem", "Senha inválida.");
				flash.addFlashAttribute("usuario", user);
				proxPagina = "redirect:login";
			}
		} else {
			flash.addFlashAttribute("mensagem", "Login e/ou senha inválidos.");
			flash.addFlashAttribute("usuario", user);
			proxPagina = "redirect:login";
		}
		
		return proxPagina;
	}
	
//	@RequestMapping(method = RequestMethod.POST)
//	public String validadeLogin(String login, String senha, String lembrar, HttpSession session, HttpServletRequest request, HttpServletResponse response, RedirectAttributes flash) {
//		String proxPagina = null;
//        String letra = login.substring(0, 1);
//        if (letra.equalsIgnoreCase("a")) {
//        	Aluno alunoBanco = alunoRepository.findByLogin(login);
//        	if (lembrar != null && lembrar.equalsIgnoreCase("sim")) {
//        		Cookie cookie = new Cookie("clogin", login);
//        		response.addCookie(cookie);
//        	} else {
//        		for (Cookie cookie: request.getCookies()) {
//        			if (cookie.getName().equals("clogin")) {
//        				cookie.setValue(null);
//        				cookie.setMaxAge(0);
//        				response.addCookie(cookie);
//        			}
//        		}
//        	}
//        	if (login.equalsIgnoreCase(alunoBanco.getLogin()) && senha.equals(alunoBanco.getSenha())) {
//        		session.setAttribute("aluno", alunoBanco);
//        		proxPagina = "redirect:disciplinas";
//        	} else {
//        		flash.addFlashAttribute("mensagem", "Login e/ou senha inválidos");
//        		flash.addFlashAttribute("aluno", login);
//        		proxPagina = "redirect:login";
//        	}
//        } else if (letra.equalsIgnoreCase("p")) {
//        	Professor professorBanco = professorRepository.findByLogin(login);
//        	if (lembrar != null && lembrar.equalsIgnoreCase("sim")) {
//        		Cookie cookie = new Cookie("clogin", login);
//        		response.addCookie(cookie);
//        	} else {
//        		for (Cookie cookie: request.getCookies()) {
//        			if (cookie.getName().equals("clogin")) {
//        				cookie.setValue(null);
//        				cookie.setMaxAge(0);
//        				response.addCookie(cookie);
//        			}
//        		}
//        	}
//        	if (login.equalsIgnoreCase(professorBanco.getLogin()) && senha.equals(professorBanco.getSenha())) {
//        		session.setAttribute("professor", professorBanco);
//        		proxPagina = "redirect:turmas";
//        	} else {
//        		flash.addFlashAttribute("mensagem", "Login e/ou senha inválidos");
//        		flash.addFlashAttribute("professor", login);
//        		proxPagina = "redirect:login";
//        	}
//        }
//        return proxPagina;
//
//	}
	
}
