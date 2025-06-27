package com.itsqmet.Mascotas.Servicio;


import com.itsqmet.Mascotas.Entidad.Mascota;
import com.itsqmet.Mascotas.Repositorio.MascotaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MascotaServicio {

    @Autowired
    private MascotaRepositorio mascotaRepositorio;

    public List<Mascota> listarTodos() {
        return mascotaRepositorio.findAll();
    }

    public Mascota obtenerPorId(Long id) {
        return mascotaRepositorio.findById(id).orElseThrow(() -> new RuntimeException("Mascota no encontrada"));
    }

    public Mascota guardar(Mascota mascota) {
        return mascotaRepositorio.save(mascota);
    }

    public Mascota actualizar(Long id, Mascota datos) {
        Mascota mascota = obtenerPorId(id);
        mascota.setNombre(datos.getNombre());
        mascota.setTipo(datos.getTipo());
        mascota.setRaza(datos.getRaza());
        mascota.setFechaNacimiento(datos.getFechaNacimiento());
        return mascotaRepositorio.save(mascota);
    }

    public void eliminar(Long id) {
        mascotaRepositorio.deleteById(id);
    }
}
