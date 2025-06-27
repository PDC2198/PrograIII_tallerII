package com.itsqmet.Mascotas.Servicio;


import com.itsqmet.Mascotas.Entidad.Empleado;
import com.itsqmet.Mascotas.Repositorio.EmpleadoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoServicio {

    @Autowired
    private EmpleadoRepositorio empleadoRepositorio;

    public List<Empleado> listarTodos() {
        return empleadoRepositorio.findAll();
    }

    public Empleado obtenerPorId(Long id) {
        return empleadoRepositorio.findById(id).orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
    }

    public Empleado guardar(Empleado empleado) {
        return empleadoRepositorio.save(empleado);
    }

    public Empleado actualizar(Long id, Empleado datos) {
        Empleado empleado = obtenerPorId(id);
        empleado.setNombre(datos.getNombre());
        empleado.setApellido(datos.getApellido());
        empleado.setEspecialidad(datos.getEspecialidad());
        empleado.setFechaContratacion(datos.getFechaContratacion());
        return empleadoRepositorio.save(empleado);
    }

    public void eliminar(Long id) {
        empleadoRepositorio.deleteById(id);
    }
}
