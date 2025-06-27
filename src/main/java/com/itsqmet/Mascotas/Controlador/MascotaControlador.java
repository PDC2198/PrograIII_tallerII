package com.itsqmet.Mascotas.Controlador;

import com.itsqmet.Mascotas.Entidad.Mascota;
import com.itsqmet.Mascotas.Servicio.ClienteServicio;
import com.itsqmet.Mascotas.Servicio.MascotaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;


@Controller
@RequestMapping("/mascotas")
public class MascotaControlador {

    @Autowired
    private MascotaServicio mascotaServicio;

    @Autowired
    private ClienteServicio clienteServicio;

    // Método para listar todas las mascotas
    @GetMapping
    public String listarMascotas(@RequestParam(required = false) String error, Model model) {
        model.addAttribute("mascotas", mascotaServicio.listarTodos());
        if ("notfound".equals(error)) {
            model.addAttribute("errorMensaje", "Mascota no encontrada.");
        }
        return "mascotas/listar";
    }

    // Método para mostrar formulario para agregar una nueva mascota
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevaMascota(Model model) {
        model.addAttribute("mascota", new Mascota());
        model.addAttribute("clientes", clienteServicio.listarTodos());
        return "mascotas/formulario";
    }

    // Método para guardar nueva mascota
    @PostMapping("/guardar")
    public String guardarMascota(@Valid @ModelAttribute Mascota mascota, BindingResult resultado, Model model) {
        if (resultado.hasErrors()) {
            model.addAttribute("clientes", clienteServicio.listarTodos());
            return "mascotas/formulario";
        }
        mascotaServicio.guardar(mascota);
        return "redirect:/mascotas";
    }

    // Método para mostrar formulario para editar una mascota
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarMascota(@PathVariable Long id, Model model) {
        Mascota mascota = mascotaServicio.obtenerPorId(id);
        model.addAttribute("mascota", mascota);
        model.addAttribute("clientes", clienteServicio.listarTodos());
        return "mascotas/formulario";
    }

    // Método para actualizar una mascota
    @PostMapping("/actualizar/{id}")
    public String actualizarMascota(@PathVariable Long id, @Valid @ModelAttribute Mascota mascota, BindingResult resultado, Model model) {
        if (resultado.hasErrors()) {
            model.addAttribute("clientes", clienteServicio.listarTodos());
            return "mascotas/formulario";
        }
        mascotaServicio.actualizar(id, mascota);
        return "redirect:/mascotas";
    }

    // Método para eliminar una mascota
    @GetMapping("/eliminar/{id}")
    public String eliminarMascota(@PathVariable Long id) {
        mascotaServicio.eliminar(id);
        return "redirect:/mascotas";
    }

    // Nuevo método para ver los detalles de una mascota
    @GetMapping("/detalle/{id}")
    public String verDetalleMascota(@PathVariable Long id, Model model) {
        Mascota mascota = mascotaServicio.obtenerPorId(id);
        if (mascota != null) {
            model.addAttribute("mascota", mascota);
            return "mascotas/detalle";  // Vista que mostrará los detalles de la mascota
        } else {
            return "redirect:/mascotas?error=notfound";  // Si no se encuentra la mascota, redirige al listado con un error
        }
    }
}

