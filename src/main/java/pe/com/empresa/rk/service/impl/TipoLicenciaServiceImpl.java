package pe.com.empresa.rk.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.empresa.rk.domain.model.entities.TipoLicencia;
import pe.com.empresa.rk.domain.model.repository.jdbc.PeriodoEmpleadoTipoLicenciaRepository;
import pe.com.empresa.rk.domain.model.repository.jpa.TipoLicenciaJpaRepository;
import pe.com.empresa.rk.exception.GenericRestException;
import pe.com.empresa.rk.view.model.TipoLicenciaFilterViewModel;
import pe.com.empresa.rk.view.model.TipoLicenciaViewModel;
import pe.com.empresa.rk.domain.model.repository.jdbc.TipoLicenciaRepository;
import pe.com.empresa.rk.enums.SeverityStatusEnum;
import pe.com.empresa.rk.exception.BusinessException;
import pe.com.empresa.rk.service.TipoLicenciaService;
import pe.com.empresa.rk.view.model.LicenciaViewModel;
import pe.com.empresa.rk.view.model.NotificacionViewModel;
import pe.com.empresa.rk.view.model.TipoLicenciaResultViewModel;

@Service
public class TipoLicenciaServiceImpl implements TipoLicenciaService{

	final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	Mapper mapper;
	
	@Autowired
	TipoLicenciaRepository tipoLicenciaRepository;
	
	@Autowired
    TipoLicenciaJpaRepository tipoLicenciaJpaRepository;
	
	@Autowired
    PeriodoEmpleadoTipoLicenciaRepository periodoEmpleadoTipoLicenciaRepository;
	
	private NotificacionViewModel crearActualizarTipoLicencia(TipoLicenciaViewModel tipooLicenciaDto){
		NotificacionViewModel notificacionDto=new NotificacionViewModel();
		TipoLicencia tipoLicencia=new TipoLicencia();
		
		try{
			mapper.map(tipooLicenciaDto, tipoLicencia);
			
			tipoLicenciaJpaRepository.save(tipoLicencia);
			tipoLicenciaJpaRepository.flush();
			notificacionDto.setCodigo(1L);
			notificacionDto.setSeverity("success");
			notificacionDto.setSummary("Runakuna Success");
			notificacionDto.setDetail("Se registro correctamente");
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			notificacionDto.setCodigo(0L);
			notificacionDto.setDetail("No es posible registrar, "+e.getMessage());
		}
		return notificacionDto;
	}

	@Override
	public List<TipoLicenciaResultViewModel> search(TipoLicenciaFilterViewModel filterViewModel) {
		return tipoLicenciaRepository.obtenerTipoLicencias(filterViewModel);
	}

	@Override
	public TipoLicenciaViewModel findOne(Long idTipoLicencia) {
		TipoLicenciaViewModel dto=new TipoLicenciaViewModel();
		TipoLicencia tipoLicencia=tipoLicenciaJpaRepository.findOne(idTipoLicencia);
		mapper.map(tipoLicencia, dto);
		return dto;
	}

	@Override
	public NotificacionViewModel create(TipoLicenciaViewModel manteinanceViewModel) {
		NotificacionViewModel notificacionDto=new NotificacionViewModel();
		notificacionDto=crearActualizarTipoLicencia(manteinanceViewModel);
		return notificacionDto;
	}

	@Override
	public NotificacionViewModel update(TipoLicenciaViewModel manteinanceViewModel) {
		NotificacionViewModel notificacionDto=new NotificacionViewModel();
		notificacionDto=crearActualizarTipoLicencia(manteinanceViewModel);
		return notificacionDto;
	}

	@Override
	public NotificacionViewModel delete(Long idTipoLicencia) {
		NotificacionViewModel notificacion=new NotificacionViewModel();
		if (tipoLicenciaJpaRepository.findOne(idTipoLicencia) == null) {
			throw new GenericRestException("ERROR", "The record was changed by another user. Please re-enter the screen.");
		}
		try {
			List<LicenciaViewModel> listaTiposLicencias=tipoLicenciaRepository.obtenerLicenciaByTipoLicencia(idTipoLicencia);
			
			BigDecimal diasLicencia= periodoEmpleadoTipoLicenciaRepository.obtenerDiasLicenciaPorTipo(idTipoLicencia);
			if((listaTiposLicencias!=null && !listaTiposLicencias.isEmpty()) || diasLicencia.intValue()>0) {
				throw new BusinessException(SeverityStatusEnum.ERROR.getCode(),"Runakuna Error",  "No se puede eliminar el tipo de licencia porque existen licencias creadas para empleados");
				
			} else {
				
				tipoLicenciaRepository.eliminarTipoLicencia(idTipoLicencia);
				
			}
			notificacion.setCodigo(1L);
			notificacion.setSeverity("success");
			notificacion.setSummary("Runakuna Success");
			notificacion.setDetail("Se elimino el registro correctamente");
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			notificacion.setCodigo(0L);
			notificacion.setDetail("No se pudo eliminar el registro "+e.getMessage());
			throw new BusinessException(SeverityStatusEnum.ERROR.getCode(),"Runakuna Error",  e.getMessage());
		}
		return notificacion;
	}



	



	



	

	

}
