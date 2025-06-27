package com.itsqmet.Mascotas.Repositorio;


import com.itsqmet.Mascotas.Entidad.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MascotaRepositorio extends JpaRepository<Mascota, Long> {
}