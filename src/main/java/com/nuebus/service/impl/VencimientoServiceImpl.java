package com.nuebus.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nuebus.dto.ChoferConCarnetsDTO;
import com.nuebus.dto.VencimientosChoferDTO;
import com.nuebus.excepciones.ResourceNotFoundException;
import com.nuebus.model.TipoVencimiento;
import com.nuebus.model.Vencimiento;
import com.nuebus.repository.TipoVencimientoRepository;
import com.nuebus.repository.VencimientoRepository;
import com.nuebus.service.VencimientoService;
import com.nuebus.vencimientos.VencimientosChofer;
import com.nuebus.vencimientos.VencimientosChoferCarnet;

@Service
@Transactional(readOnly = true)
public class VencimientoServiceImpl implements VencimientoService {

	public final boolean VENCIMIENTO_ACTIVO = true;

	@Autowired
	VencimientoRepository vencimientoRepository;
	@Autowired
	TipoVencimientoRepository tipoVencimientoRepository;
	@Autowired
	VencimientosChoferCarnet vencimientosChoferCarnet;
	@Autowired
	VencimientosChofer vencimientosChofer;

	@Override
	public List<Vencimiento> getVencimientos(String empresa) {
		return vencimientoRepository.findByEmpresa(empresa);
	}

	@Override
	public List<TipoVencimiento> getTiposVencimientos() {
		return tipoVencimientoRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false)
	public Vencimiento saveVencimiento(Vencimiento vencimiento) {

		Vencimiento v = new Vencimiento();
		v.setActivo(vencimiento.getActivo());
		v.setCantidadAnticipacion(vencimiento.getCantidadAnticipacion());
		v.setEmpresa(vencimiento.getEmpresa());
		v.setTipoVencimiento(vencimiento.getTipoVencimiento());

		vencimientoRepository.save(v);
		return v;
	}

	@Override
	public Vencimiento getVencimiento(Long id) {
		Vencimiento vencimiento = vencimientoRepository.getOne(id);
		if (vencimiento == null) {
			throw new ResourceNotFoundException(id, "Vencimiento no encontrado");
		}
		return vencimiento;
	}

	@Override
	@Transactional(readOnly = false)
	public Vencimiento deleteVencimiento(Long id) {

		Vencimiento vencimiento = getVencimiento(id);
		vencimientoRepository.delete( vencimiento );
		return vencimiento;
	}

	@Override
	@Transactional(readOnly = false)
	public Vencimiento updateVencimiento(Long id, Vencimiento vencimiento) {

		TipoVencimiento tipoVencimiento = getTipoVencimiento(vencimiento.getTipoVencimiento().getId());

		Vencimiento vencimientoUpdate = vencimientoRepository.getOne(id);
		// vencimientoUpdate.setEmpresa( vencimiento.getEmpresa());
		vencimientoUpdate.setActivo(vencimiento.getActivo());
		vencimientoUpdate.setCantidadAnticipacion(vencimiento.getCantidadAnticipacion());
		vencimientoUpdate.setTipoVencimiento(tipoVencimiento);

		return vencimientoUpdate;
	}

	TipoVencimiento getTipoVencimiento(Short id) {
		TipoVencimiento tipoVencimiento = tipoVencimientoRepository.getOne(id);
		if (tipoVencimiento == null) {
			throw new ResourceNotFoundException((long) id, "Tipo Vencimiento no encontrado");
		}
		return tipoVencimiento;
	}

	@Override
	public List<Vencimiento> getVencimientosByEmpresaAndNombreEntidad(String empresa, String nombreEntidad,
			boolean activo) {
		return vencimientoRepository.findByEmpresaAndNombreEntidad(empresa, nombreEntidad, activo);
	}

	@Override
	public List<VencimientosChoferDTO> calcularVencimientosChoferes(String empresa, int estadoChofer) {

		List<VencimientosChoferDTO> vencimientosChofer = new ArrayList<VencimientosChoferDTO>();
		List<Vencimiento> vencimientos = getVencimientosByEmpresaAndNombreEntidad(empresa,
				VencimientosChofer.ENTIDAD_CARNET, VENCIMIENTO_ACTIVO);
		for (Vencimiento venc : vencimientos) {

			if (venc.getTipoVencimiento() != null && venc.getTipoVencimiento().getNombreCampo()
					.equals(VencimientosChoferCarnet.FECHA_VENC_CAMPO_CARNET)) {

				VencimientosChoferDTO vencimientoChoDTO = vencimientosChoferCarnet.calcularVencimietosChofer(empresa,
						estadoChofer, venc);

				vencimientosChofer.add(vencimientoChoDTO);
			}

		}
		return vencimientosChofer;

	}

	@Override
	public void calcularVencimietosChofer(String empresa, List<ChoferConCarnetsDTO> choferesDTO) {
		List<Vencimiento> vencimientos = getVencimientosByEmpresaAndNombreEntidad(empresa,
				VencimientosChofer.ENTIDAD_CARNET, VENCIMIENTO_ACTIVO);

		for (Vencimiento venc : vencimientos) {
			if (venc.getTipoVencimiento() != null && venc.getTipoVencimiento().getNombreCampo()
					.equals(VencimientosChoferCarnet.FECHA_VENC_CAMPO_CARNET)) {
				vencimientosChoferCarnet.calcularVencimietosChofer(venc, choferesDTO);
			}
		}

	}

}
