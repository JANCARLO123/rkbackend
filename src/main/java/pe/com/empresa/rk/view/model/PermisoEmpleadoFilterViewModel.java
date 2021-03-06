package pe.com.empresa.rk.view.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import pe.com.empresa.rk.json.JsonDateSimpleDeserializer;
import pe.com.empresa.rk.json.JsonDateSimpleSerializer;

import java.util.Date;

/**
 * Created by josediaz on 8/11/2016.
 */
public class PermisoEmpleadoFilterViewModel extends FilterViewModel {

    private Long idEmpleado;
    private String nombreEmpleado;

    @JsonSerialize(using=JsonDateSimpleSerializer.class)
    @JsonDeserialize(using=JsonDateSimpleDeserializer.class)
    private Date desde;

    @JsonSerialize(using=JsonDateSimpleSerializer.class)
    @JsonDeserialize(using=JsonDateSimpleDeserializer.class)
    private Date hasta;

    private Long unidadNegocio;
    private Long departamento;
    private Long proyecto;

    private String codigoPermiso;
    private String jefeInmediato;

    private String estado;

    private Long idJefeInmediato;

	private Integer mes;
	private Integer anio;
	
	private Long idJefe;
    
    public String getCodigoPermiso() {
        return codigoPermiso;
    }

    public void setCodigoPermiso(String codigoPermiso) {
        this.codigoPermiso = codigoPermiso;
    }

    public Long getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Long idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }


    public Date getDesde() {
        return desde;
    }

    public void setDesde(Date desde) {
        this.desde = desde;
    }

    public Date getHasta() {
        return hasta;
    }

    public void setHasta(Date hasta) {
        this.hasta = hasta;
    }

    public Long getUnidadNegocio() {
        return unidadNegocio;
    }

    public void setUnidadNegocio(Long unidadNegocio) {
        this.unidadNegocio = unidadNegocio;
    }

    public Long getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Long departamento) {
        this.departamento = departamento;
    }

    public Long getProyecto() {
        return proyecto;
    }

    public void setProyecto(Long proyecto) {
        this.proyecto = proyecto;
    }

    public String getJefeInmediato() {
        return jefeInmediato;
    }

    public void setJefeInmediato(String jefeInmediato) {
        this.jefeInmediato = jefeInmediato;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

	public Long getIdJefeInmediato() {
		return idJefeInmediato;
	}

	public void setIdJefeInmediato(Long idJefeInmediato) {
		this.idJefeInmediato = idJefeInmediato;
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	public Long getIdJefe() {
		return idJefe;
	}

	public void setIdJefe(Long idJefe) {
		this.idJefe = idJefe;
	}
    
}
