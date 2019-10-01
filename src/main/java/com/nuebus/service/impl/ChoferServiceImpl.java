
package com.nuebus.service.impl;

import com.nuebus.builders.ChoferOcupacionBuilder;
import com.nuebus.dto.CarnetDTO;
import com.nuebus.dto.ChoferDTO;
import com.nuebus.dto.ChoferIncidenciaDTO;
import com.nuebus.dto.ChoferOcupacionDTO;
import com.nuebus.excepciones.ResourceNotFoundException;
import com.nuebus.mapper.ChoferMapper;
import com.nuebus.model.Carnet;
import com.nuebus.model.Chofer;
import com.nuebus.model.ChoferIncidencia;
import com.nuebus.model.ChoferPK;
import com.nuebus.model.ImagenChofer;
import com.nuebus.repository.ChoferRepository;
import com.nuebus.repository.ImagenChoferRepository;
import com.nuebus.repository.IncidenciaRepository;
import com.nuebus.service.ChoferService;
import com.nuebus.vistas.MapperVistas;
import com.nuebus.vistas.combos.ComboChoferes;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Valeria
 */


@Service
@Transactional(readOnly = true) 
public class ChoferServiceImpl implements ChoferService{
    
    final static Logger LOG = LoggerFactory.getLogger(ChoferServiceImpl.class);

    @Autowired
    ChoferRepository choferRepository;

    @Autowired
    IncidenciaRepository incidenciaRepository;

    @Autowired
    ChoferMapper choferMapper;

    @Autowired
    ImagenChoferRepository imagenChoferRepository;

    @Override
    public Page<ChoferDTO> findChoferes(Pageable pageable) {
        return choferRepository.findAll(pageable).map(chofer -> choferMapper.toDTO(chofer));
    }

    @Override
    public ChoferDTO getChofer(ChoferPK id) {

        Chofer chofer = choferRepository.getOne(id);
        if (chofer == null) {
            return null;
        } else {
            return choferMapper.toDTO(chofer);
        }
    }

    @Override
    @Transactional(readOnly = false)
    public ChoferDTO updateChofer(String empCodigo, Long codigo, ChoferDTO choferDTO) throws Exception {

        ChoferPK claveCho = new ChoferPK();
        claveCho.setEmpCodigo(empCodigo);
        claveCho.setCodigo(codigo);

        Chofer chofer = choferRepository.findById(claveCho).orElse(null);

        if (chofer == null) {
            throw new ResourceNotFoundException(codigo, "Chofer no encontrado");
        }

        choferMapper.mapToEntity(choferDTO, chofer);

        Chofer choferUpdated = choferRepository.save(chofer);

        return choferMapper.toDTO(choferUpdated);

    }

    @Override
    @Transactional(readOnly = false)
    public ChoferDTO saveChofer(ChoferDTO choferDTO) throws Exception {

        Chofer chofer = choferMapper.toEntity(choferDTO);
        int codigo = choferRepository.maxCodigoPersonalByEmpresa(choferDTO.getChoferPK().getEmpCodigo(),
                choferDTO.getTipoChofer());
        choferDTO.getChoferPK().setCodigo(codigo + 1);
        Chofer choferSaved = choferRepository.save(chofer);
        return choferMapper.toDTO(choferSaved);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteChofer(String empCodigo, Long codigo) {

        ChoferPK choferPK = new ChoferPK();
        choferPK.setEmpCodigo(empCodigo);
        choferPK.setCodigo(codigo);

        Chofer chofer = choferRepository.findById(choferPK).orElse(null);

        if (chofer == null) {
            throw new ResourceNotFoundException(codigo, "Chofer no encontrado");
        }

        choferRepository.delete(chofer);
    }

    @Override
    public Page<ChoferDTO> findPersonalByEmpresa(Pageable pageable, String empresa) {
        return choferRepository.findPersonalByEmpresa(empresa, pageable).map(chofer -> choferMapper.toDTO(chofer));
    }

    @Override
    @Transactional(readOnly = false)
    public void salvarIncidenciasByChofer(String empCodigo, Long codigo,
            Set<ChoferIncidenciaDTO> incidencias) throws Exception {

        ChoferPK idChofer = new ChoferPK();
        idChofer.setEmpCodigo(empCodigo);
        idChofer.setCodigo(codigo);

        Chofer unChofer = choferRepository.findById(idChofer).orElse(null);

        if (unChofer == null) {
            throw new ResourceNotFoundException(codigo, "Chofer no encontrado");
        }

        ChoferIncidencia choIncid;
        List<ChoferIncidencia> lista = new ArrayList<>();

        for (ChoferIncidenciaDTO chInc : incidencias) {

            choIncid = new ChoferIncidencia();
            choIncid.setId(chInc.getId());
            choIncid.setIncidencia(incidenciaRepository.findById(chInc.getIdIncidencia()).orElse(null));
            choIncid.setChofer(unChofer);
            choIncid.setInicio(chInc.getInicio());
            choIncid.setFin(chInc.getFin());
            lista.add(choIncid);

        }

        unChofer.getChoferIncidencias().clear();
        unChofer.getChoferIncidencias().addAll(lista);
        choferRepository.save(unChofer);
    }

    @Override
    public List<ChoferIncidenciaDTO> getIncidenciasByChofer(String empCodigo, long codigo) {

        ChoferPK idChofer = new ChoferPK();
        idChofer.setEmpCodigo(empCodigo);
        idChofer.setCodigo(codigo);

        Chofer unChofer = choferRepository.findById(idChofer).orElse(null);

        if (unChofer == null) {
            throw new ResourceNotFoundException(codigo, "Chofer no encontrado");
        }

        return unChofer.getChoferIncidencias().stream().
                map(choInc -> MapperVistas.toChoferIncidenciaDTO(choInc)).collect(Collectors.toList());

    }

    @Override
    @Transactional(readOnly = false)
    public void salvarCarnetsByChofer(String empCodigo, Long codigo, Set<CarnetDTO> carnetsDTO) {

        ChoferPK idChofer = new ChoferPK();
        idChofer.setEmpCodigo(empCodigo);
        idChofer.setCodigo(codigo);

        Chofer unChofer = choferRepository.findById(idChofer).orElse(null);

        if (unChofer == null) {
            throw new ResourceNotFoundException(codigo, "Chofer no encontrado");
        }

        Set<Carnet> carnets = choferMapper.carnetsDTOToCarnets(carnetsDTO);
        for (Carnet carnet : carnets) {
            carnet.setChofer(unChofer);
        }
        unChofer.getCarnets().clear();
        unChofer.getCarnets().addAll(carnets);
        choferRepository.save(unChofer);
    }

    @Override
    public List<CarnetDTO> getCarnetsByChofer(String empCodigo, long codigo) {

        ChoferPK idChofer = new ChoferPK();
        idChofer.setEmpCodigo(empCodigo);
        idChofer.setCodigo(codigo);

        Chofer unChofer = choferRepository.findById(idChofer).orElse(null);

        if (unChofer == null) {
            throw new ResourceNotFoundException(codigo, "Chofer no encontrado");
        }

        return unChofer.getCarnets().stream().
                map(carnet -> choferMapper.carnetToCarnetDTO(carnet)).collect(Collectors.toList());

    }

    @Override
    public List<ComboChoferes> getPersonal(String empCodigo, int estado, int funcion) {
        return choferRepository.finPersonalByEstado(empCodigo, estado, funcion);
    }

    @Override
    public List<ChoferOcupacionDTO> findPersonalOcupacionByEmpresa(String empresa, Date inicio, Date fin) {

        return new ChoferOcupacionBuilder(choferRepository.ocupacionChoferes(empresa, inicio, fin)).build();

    }

    @Override
    public List<Chofer> getChoferesConCarnetsVencidos(String empresa, int estadoChofer, Date fechaControl) {
        return choferRepository.getChoferesConCarnetsVencidos(empresa, estadoChofer, fechaControl);
    }

    @Override
    public List<ChoferDTO> findChoferesFromHorariosServicios(String empresa, String linea, Date inicio, Date fin) {
        List< ChoferDTO> choferes = new ArrayList<>();
        List<Object[]> chofs = choferRepository.findChoferesFromHorariosServicios(empresa, linea, inicio, fin);

        for (Object[] obj : chofs) {

            ChoferDTO chofer = new ChoferDTO();
            ChoferPK choPK = new ChoferPK();
            choPK.setEmpCodigo((String) obj[0]);
            choPK.setCodigo(((BigDecimal) obj[1]).intValue());
            chofer.setChoferPK(choPK);
            chofer.setNombre(((String) obj[2]));
            chofer.setTipoChofer(((BigDecimal) obj[3]).intValue());
            chofer.setEstado(((BigDecimal) obj[4]).intValue());
            chofer.setIdAux(((BigDecimal) obj[5]).intValue());

            choferes.add(chofer);
        }

        return choferes;
    }

    @Override
    public Chofer getChoferById(ChoferPK id) {
        Chofer chofer = choferRepository.getOne(id);
        if (chofer == null) {
            return null;
        } else {
            return chofer;
        }
    }

    @Override
    public Page<ChoferDTO> findPersonalByBusquedaAndEmpresa(String busqueda, String empresa, Pageable pageable) {

        if (busqueda != null) {
            busqueda = busqueda.toUpperCase();
        } else {
            busqueda = "";
        }

        Page<ChoferDTO> choferesDTO = choferRepository.findPersonalByBusquedaAndEmpresa(busqueda, empresa, pageable)
                .map(chofer -> choferMapper.toDTO(chofer));

        return choferesDTO;
    }

    @Override
    @Transactional(readOnly = false)
    public ChoferDTO updateImagenChofer(String empCodigo, long codigo, byte[] imagen) {

        Chofer chofer = getChoferById(new ChoferPK(empCodigo, codigo));

        if (chofer != null) {
            ImagenChofer imagenChofer = null;

            if (chofer.getFoto() != null) {

                imagenChofer = imagenChoferRepository.findByIdAndEmpresa(Long.valueOf(chofer.getFoto()),
                         chofer.getChoferPK().getEmpCodigo());

                //imagenChofer = imagenChoferRepository.findById( Long.valueOf( chofer.getFoto() ) ).orElse(null);
            }

            if (imagenChofer == null) {
                imagenChofer = new ImagenChofer();
                imagenChofer.setEmpresa(chofer.getChoferPK().getEmpCodigo());
            }

            imagenChofer.setImagen(imagen);

            ImagenChofer imagenChoferSaved = imagenChoferRepository.save(imagenChofer);
            
            Chofer choferUpdated = chofer;
            
            if (chofer.getFoto() == null
                    || !chofer.getFoto().equalsIgnoreCase(String.valueOf(imagenChoferSaved.getId()))) {
                chofer.setFoto(String.valueOf(imagenChoferSaved.getId()));
                choferUpdated = choferRepository.save(chofer);
            }

            return  choferMapper.toDTO(choferUpdated);
            
        } else {
            throw new ResourceNotFoundException(codigo, "Chofer no encontrado");
        }        
    }

    @Override
    public byte[] getImagenChofer(String empCodigo, long codigo) {
        ImagenChofer imagenChofer = null;
        Chofer chofer = getChoferById(new ChoferPK(empCodigo, codigo));
        if (chofer != null && chofer.getFoto() != null && chofer.getFoto().length() > 0) {

            imagenChofer = imagenChoferRepository.findByIdAndEmpresa(Long.valueOf(chofer.getFoto()),
                     chofer.getChoferPK().getEmpCodigo());

            if (imagenChofer != null && imagenChofer.getImagen() != null) {
                return imagenChofer.getImagen();
            }
        }

        throw new ResourceNotFoundException(codigo, "Chofer no encontrado");

    }
     
    
}
