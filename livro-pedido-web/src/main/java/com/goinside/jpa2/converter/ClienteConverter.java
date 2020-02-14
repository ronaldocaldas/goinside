package com.goinside.jpa2.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import com.goinside.jpa2.dao.ClienteDAO;
import com.goinside.jpa2.model.Cliente;
import com.goinside.jpa2.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=Cliente.class)
public class ClienteConverter implements Converter {

	@Inject
	private ClienteDAO clienteDAO;
	
	public ClienteConverter() {
		this.clienteDAO = CDIServiceLocator.getBean(ClienteDAO.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Cliente retorno = null;

		if (StringUtils.isNotBlank(value)) {
			retorno = this.clienteDAO.buscarPeloId(new Long(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long codigo = ((Cliente) value).getId();
			String retorno = (codigo == null ? null : codigo.toString());
			
			return retorno;
		}
		
		return "";
	}
}