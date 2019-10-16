package br.com.cotil.aton.campo.customisado;

import br.com.cotil.aton.HttpException.BadRequestException;
import br.com.cotil.aton.campo.padrao.CampoPadraoModel;
import br.com.cotil.aton.util.Utils;

public class CampoCustomizadoUtils {

	public static CampoCustomizadoModel padronizar(CampoCustomizadoModel campoCustomizado) throws BadRequestException {

		CampoPadraoModel CampoPadrao = campoCustomizado.getCampoPadrao();

		campoCustomizado.setCampoPadrao(CampoPadrao);

		if (Utils.isNullOrEmpty(campoCustomizado.getNome()))
			campoCustomizado.setNome(CampoPadrao.getNome());

		if (Utils.isNullOrEmpty(campoCustomizado.getPlaceholder()))
			campoCustomizado.setPlaceholder(CampoPadrao.getPlaceHolder());

		if (Utils.isNullOrEmpty(campoCustomizado.getToolTip()))
			campoCustomizado.setToolTip(CampoPadrao.getToolTip());

		if (Utils.isNullOrEmpty(campoCustomizado.getDefaultValue()))
			campoCustomizado.setToolTip(CampoPadrao.getDefaultValue());

		if (Utils.isNullOrEmpty(campoCustomizado.getLabel()))
			campoCustomizado.setToolTip(CampoPadrao.getLabel());

		if (Utils.isNullOrEmpty(campoCustomizado.getMaxlenght()) || (campoCustomizado.getMaxlenght() > 2147483646))
			campoCustomizado.setToolTip(CampoPadrao.getMaxlenght());

		if (Utils.isNullOrEmpty(campoCustomizado.getMinlenght()) || (campoCustomizado.getMinlenght() > -2147483648))
			campoCustomizado.setToolTip(CampoPadrao.getMinlenght());

		return campoCustomizado;
	}

}
