package com.goinside.jpa2.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import com.goinside.jpa2.dao.VendedorDAO;
import com.goinside.jpa2.model.Vendedor;
import com.goinside.jpa2.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Vendedor.class)
public class VendedorConverter implements Converter {

	@Inject
	private VendedorDAO vendedorDAO;

	
	public VendedorConverter() {
		this.vendedorDAO = CDIServiceLocator.getBean(VendedorDAO.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Vendedor retorno = null;

		if (StringUtils.isNotBlank(value)) {
			retorno = this.vendedorDAO.buscarPeloId(new Long(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long codigo = ((Vendedor) value).getId();
			String retorno = (codigo == null ? null : codigo.toString());

			return retorno;
		}

		return "";
	}

}
