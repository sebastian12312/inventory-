package com.proyecto.web.restaurante.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.web.restaurante.clases.clientes;
import com.proyecto.web.restaurante.repository.ClienteRepository;
import com.proyecto.web.restaurante.service.ClienteService;

@Service
public class ClienteImplement  implements ClienteService{

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public  List<clientes> listaClientes() {
       List<clientes> listaClientes =  clienteRepository.findAll();
       return listaClientes;
    }
    
}
