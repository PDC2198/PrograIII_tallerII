package com.itsqmet.Mascotas.Controlador;

import com.itsqmet.Mascotas.Entidad.Cliente;
import com.itsqmet.Mascotas.Servicio.ClienteServicio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/clientes")
public class ClienteControlador {

    @Autowired
    private ClienteServicio clienteServicio;

    // Mostrar lista de clientes
    @GetMapping
    public String listarClientes(Model model) {
        model.addAttribute("clientes", clienteServicio.listarTodos());
        return "clientes/listar"; // archivo: templates/clientes/listar.html
    }

    // Mostrar formulario para crear cliente
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevoCliente(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "clientes/formulario"; // archivo: templates/clientes/formulario.html
    }

    // Procesar formulario de nuevo cliente
    @PostMapping("/guardar")
    public String guardarCliente(@Valid @ModelAttribute Cliente cliente, BindingResult resultado) {
        if (resultado.hasErrors()) {
            return "clientes/formulario";
        }
        clienteServicio.guardar(cliente);
        return "redirect:/clientes";
    }

    // Mostrar formulario para editar cliente
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarCliente(@PathVariable Long id, Model model) {
        Cliente cliente = clienteServicio.obtenerPorId(id);
        model.addAttribute("cliente", cliente);
        return "clientes/formulario";
    }

    // Procesar formulario para actualizar cliente
    @PostMapping("/actualizar/{id}")
    public String actualizarCliente(@PathVariable Long id, @Valid @ModelAttribute Cliente cliente, BindingResult resultado) {
        if (resultado.hasErrors()) {
            return "clientes/formulario";
        }
        clienteServicio.actualizar(id, cliente);
        return "redirect:/clientes";
    }

    // Eliminar cliente
    @GetMapping("/eliminar/{id}")
    public String eliminarCliente(@PathVariable Long id) {
        clienteServicio.eliminar(id);
        return "redirect:/clientes";
    }
}
