package com.goinside.jpa2.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.goinside.jpa2.dao.LivroDAO;
import com.goinside.jpa2.model.Livro;
import com.goinside.jpa2.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Livro.class)
public class LivroConverter implements Converter {

	private LivroDAO livroDAO;

	public LivroConverter() {
		this.livroDAO = CDIServiceLocator.getBean(LivroDAO.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Livro retorno = null;

		if (value != null) {
			retorno = this.livroDAO.buscarPeloId(new Long(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long id = ((Livro) value).getId();
			String retorno = (id == null ? null : id.toString());
			return retorno;
		}

		return "";
	}

}
