package com.itsqmet.Mascotas.Controlador;

import com.itsqmet.Mascotas.Entidad.Empleado;
import com.itsqmet.Mascotas.Servicio.EmpleadoServicio;
import com.itsqmet.Mascotas.Servicio.MascotaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@RequestMapping("/empleados")
public class EmpleadoControlador {

    @Autowired
    private EmpleadoServicio empleadoServicio;

    @Autowired
    private MascotaServicio mascotaServicio;

    // Mostrar lista de empleados
    @GetMapping
    public String listarEmpleados(Model model) {
        List<Empleado> empleados = empleadoServicio.listarTodos();
        model.addAttribute("empleados", empleados);
        return "empleados/listar";
    }

    // Mostrar el formulario para crear un nuevo empleado
    @GetMapping("/nueva")
    public String mostrarFormularioNuevoEmpleado(Model model) {
        model.addAttribute("empleado", new Empleado());
        model.addAttribute("mascotas", mascotaServicio.listarTodos());
        return "empleados/formulario";
    }

    // Guardar un nuevo empleado
    @PostMapping("/guardar")
    public String guardarEmpleado(@ModelAttribute Empleado empleado) {
        empleadoServicio.guardar(empleado);
        return "redirect:/empleados";
    }

    // Mostrar el formulario para editar un empleado
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarEmpleado(@PathVariable Long id, Model model) {
        Empleado empleado = empleadoServicio.obtenerPorId(id);
        if (empleado == null) {
            return "redirect:/empleados?error=notfound";
        }
        model.addAttribute("empleado", empleado);
        model.addAttribute("mascotas", mascotaServicio.listarTodos());
        return "empleados/formulario";
    }

    // Actualizar la información de un empleado
    @PostMapping("/actualizar/{id}")
    public String actualizarEmpleado(@PathVariable Long id, @ModelAttribute Empleado empleado) {
        empleado.setId(id);
        empleadoServicio.guardar(empleado);
        return "redirect:/empleados";
    }

    // Eliminar un empleado
    @GetMapping("/eliminar/{id}")
    public String eliminarEmpleado(@PathVariable Long id) {
        empleadoServicio.eliminar(id);
        return "redirect:/empleados";
    }
}



