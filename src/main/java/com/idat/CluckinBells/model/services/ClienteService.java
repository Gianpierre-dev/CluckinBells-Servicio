package com.idat.CluckinBells.model.services;

import com.idat.CluckinBells.model.entity.Cliente;
import com.idat.CluckinBells.model.entity.Usuario;

import java.util.List;

public interface ClienteService {


    Cliente buscarClienteId(Long id);
    Cliente guardarCliente(Cliente cliente);
    Cliente buscarUsername(String username);

    Cliente existeCorreoElectronico(String correoElectronico);

}
