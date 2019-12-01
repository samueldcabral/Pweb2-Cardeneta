package br.edu.ifpb.pweb2.cardeneta.controller;

import br.edu.ifpb.pweb2.cardeneta.entity.Disciplina;
import br.edu.ifpb.pweb2.cardeneta.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/disciplina")
public class DisciplinaController {

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @RequestMapping("/registrar")
    public String registrar(Model model, String nome, String curso) {
        Disciplina disciplina = new Disciplina();
        disciplina.setNome(nome);
        disciplina.setCurso(curso);

        disciplinaRepository.save(disciplina);
        model.addAttribute("disciplina", disciplina);
        return "aula/registrar-presenca";
    }
}
