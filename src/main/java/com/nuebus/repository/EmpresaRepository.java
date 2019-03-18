package com.nuebus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nuebus.model.Empresa;

public interface EmpresaRepository extends JpaRepository< Empresa, String> {

}
