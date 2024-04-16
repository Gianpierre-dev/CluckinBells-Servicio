package com.idat.CluckinBells.model.services.impl;

import com.idat.CluckinBells.model.entity.Cliente;
import com.idat.CluckinBells.model.repository.ClienteRepository;
import com.idat.CluckinBells.model.repository.UsuarioRepository;
import com.idat.CluckinBells.model.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements ClienteService {


    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public Cliente buscarClienteId(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    @Override
    public Cliente guardarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente buscarUsername(String username) {
        return clienteRepository.findByUsername(username).orElse(null);
    }

    @Override
    public Cliente existeCorreoElectronico(String correoElectronico) {
        return clienteRepository.findByCorreo(correoElectronico).orElse(null);
    }
}
