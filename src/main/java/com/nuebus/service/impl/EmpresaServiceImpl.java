package com.nuebus.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nuebus.model.Empresa;
import com.nuebus.repository.EmpresaRepository;
import com.nuebus.service.EmpresaService;


@Service()
@Transactional(readOnly=true)
public class EmpresaServiceImpl implements EmpresaService {
	
	@Autowired 
	EmpresaRepository empresaRepository;

	@Override
	public List<Empresa> getEmpresas() { 
		return empresaRepository.findAll();
	}
	

}
