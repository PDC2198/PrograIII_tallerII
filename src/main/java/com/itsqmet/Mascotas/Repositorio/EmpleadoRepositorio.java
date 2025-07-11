package com.itsqmet.Mascotas.Repositorio;


import com.itsqmet.Mascotas.Entidad.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepositorio extends JpaRepository<Empleado, Long> {
}
