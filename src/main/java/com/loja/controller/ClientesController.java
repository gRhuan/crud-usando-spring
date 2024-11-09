package com.loja.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.loja.dto.RequisicaoNovoCliente;
import com.loja.model.Clientes;
import com.loja.repository.ClientesRepository;
import com.loja.service.ClientesService;

@Controller
public class ClientesController {

    @Autowired
    private ClientesService clientesService;

    @Autowired
    private ClientesRepository clientesRepository;

    @GetMapping("/api/clientes")
    public List<Clientes> clientes() {
        return clientesService.getAllClientes();
    }

    @GetMapping("/clientes")
    public ModelAndView index() {
        List<Clientes> clientes = clientesService.getAllClientes();

        ModelAndView mv = new ModelAndView("clientes/index");

        mv.addObject("clientes", clientes);

        return mv;
    }

    @PostMapping("/clientes/adicionar")
    public String criar(RequisicaoNovoCliente requisicao) {
        Clientes cliente = requisicao.toClientes();
        this.clientesRepository.save(cliente);
        return "redirect:/clientes";
    }

}
