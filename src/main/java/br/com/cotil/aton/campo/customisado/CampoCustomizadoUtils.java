package br.com.cotil.aton.campo.customisado;

import br.com.cotil.aton.HttpException.BadRequestException;
import br.com.cotil.aton.campo.padrao.CampoPadraoModel;
import br.com.cotil.aton.campo.padrao.CampoPadraoService;
import br.com.cotil.aton.usuario.usuario.UsuarioModel;
import br.com.cotil.aton.util.OptionalUtils;
import br.com.cotil.aton.util.PageUtils;
import br.com.cotil.aton.util.Utils;
import org.springframework.data.domain.Page;

import java.util.Optional;

public class CampoCustomizadoUtils {

	private CampoCustomizadoRepository campoCustomizadoRepository;

	public CampoCustomizadoUtils(CampoCustomizadoRepository campoCustomizadoRepository) {
		super();
		this.campoCustomizadoRepository = campoCustomizadoRepository;
	}

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
			campoCustomizado.setDefaultValue(CampoPadrao.getDefaultValue());

		if (Utils.isNullOrEmpty(campoCustomizado.getLabel()))
			campoCustomizado.setLabel(CampoPadrao.getLabel());

		if (Utils.isNullOrEmpty(campoCustomizado.getMaxlenght()) || (campoCustomizado.getMaxlenght() > 2147483646))
			campoCustomizado.setMaxlenght(CampoPadrao.getMaxlenght());

		if (Utils.isNullOrEmpty(campoCustomizado.getMinlenght()) || (campoCustomizado.getMinlenght() > -2147483648))
			campoCustomizado.setMinlenght(CampoPadrao.getMinlenght());

		return campoCustomizado;
	}




	// ------ Validações e padronizações ------\\

	public CampoCustomizadoModel validaSeCampoExiste(Integer idCampo, Integer idUsuario) {
		Optional<CampoCustomizadoModel> campoCustomizadoOptional = campoCustomizadoRepository
				.findByIdAndUsuario(idCampo, idUsuario);

		return (CampoCustomizadoModel) OptionalUtils.validIfIsNotPresent(campoCustomizadoOptional,
				new BadRequestException("Campo não encontrado"));
	}

	public CampoCustomizadoModel validaSeCampoExiste(Integer idCampo) throws BadRequestException {
		Optional<CampoCustomizadoModel> campoCustomizadoOptional = campoCustomizadoRepository
				.findById(idCampo);

		return (CampoCustomizadoModel) OptionalUtils.validIfIsNotPresent(campoCustomizadoOptional,
				new BadRequestException("Campo não encontrado"));
	}

	public void validaAtivo(CampoCustomizadoModel campo, boolean ativo) throws BadRequestException {
		if (campo.isAtivo() != ativo)
			throw new BadRequestException("campo está desativado des de " + campo.getDataAlteracao());
	}

	public CampoCustomizadoModel getCampoCustomizado(Integer usuarioId, Integer id) throws BadRequestException {

		Optional<CampoCustomizadoModel> optionalCampoCustomizado = campoCustomizadoRepository.findByIdAndUsuario(id, usuarioId);

		return (CampoCustomizadoModel) OptionalUtils.validIfIsNotPresent(optionalCampoCustomizado, new BadRequestException("Campo não encontrado"));
	}



}
