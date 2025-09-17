package br.com.alura.comex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.comex.model.Cliente;
import br.com.alura.comex.repository.ClienteRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    @Transactional
    public Cliente cadastrar(Cliente cliente) {
        if (clienteRepository.findByCpf(cliente.getCpf()).isPresent()) {
            throw new IllegalStateException("CPF já cadastrado.");
        }
        if (clienteRepository.findByEmail(cliente.getEmail()).isPresent()) {
            throw new IllegalStateException("E-mail já cadastrado.");
        }
        return clienteRepository.save(cliente);
    }
}
