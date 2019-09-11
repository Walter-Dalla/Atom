package br.com.cotil.aton.campo.customisado;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.cotil.aton.HttpException.BadRequestException;
import br.com.cotil.aton.HttpException.ForbiddenException;
import br.com.cotil.aton.campo.padrao.CampoPadraoService;
import br.com.cotil.aton.usuario.usuario.UsuarioModel;
import br.com.cotil.aton.util.Utils;

@Service
public class CampoCustomizadoService {

	private CampoCustomizadoRepository campoCustomizadoRepository;
	private CampoPadraoService campoPadraoService;

	public CampoCustomizadoService(CampoCustomizadoRepository campoCustomizadoRepository,
			CampoPadraoService campoPadraoService) {
		super();
		this.campoCustomizadoRepository = campoCustomizadoRepository;
		this.campoPadraoService = campoPadraoService;
	}

	public Page<CampoCustomizadoModel> getCampoCustomizado(UsuarioModel usuario, Integer id, String nome, boolean ativo,
			Integer page, Integer size) throws BadRequestException {

		Pageable pageable = Utils.setPageRequestConfig(page, size);

		return campoCustomizadoRepository.findWithFilter(id, nome, ativo, usuario.getId(), pageable);
	}

	public CampoCustomizadoModel postCampoCustomisado(UsuarioModel usuario, CampoCustomizadoModel campoCustomizado)
			throws BadRequestException {

		campoCustomizado.setUsuario(usuario);
		campoCustomizado.setAtivo(true);
		campoCustomizado.setCampoPadrao(campoPadraoService.validaCampoPadrao(campoCustomizado.getCampoPadrao().getId()));
		campoCustomizado = CampoCustomizadoUtils.padronizar(campoCustomizado);

		return campoCustomizadoRepository.save(campoCustomizado);

	}

	public CampoCustomizadoModel atualizarCampoCustomizado(UsuarioModel usuario, CampoCustomizadoModel campoCustomizado)
			throws BadRequestException, ForbiddenException {

		boolean ativo = true;

		CampoCustomizadoModel campoDoBanco = validaSeCampoExiste(campoCustomizado.getId(), usuario.getId());
		validaAtivo(campoDoBanco, ativo);

		campoCustomizado = CampoCustomizadoUtils.padronizar(campoCustomizado);

		campoDoBanco.setNome(campoCustomizado.getNome());
		campoDoBanco.setPlaceHolder(campoCustomizado.getPlaceHolder());
		campoDoBanco.setToolTip(campoCustomizado.getToolTip());

		return campoCustomizadoRepository.save(campoDoBanco);
	}

	public CampoCustomizadoModel desativarCampoCustomizado(UsuarioModel usuario, Integer idCampo, boolean ativo)
			throws BadRequestException, ForbiddenException {

		CampoCustomizadoModel campoNoBanco = validaSeCampoExiste(idCampo, usuario.getId());

		validaAtivo(campoNoBanco, ativo);

		campoNoBanco.setAtivo(ativo);

		return campoCustomizadoRepository.save(campoNoBanco);
	}

	// ------ Validações e padronizações ------\\

	public CampoCustomizadoModel validaSeCampoExiste(Integer idCampo, Integer idUsuario) throws BadRequestException {
		Optional<CampoCustomizadoModel> campoCustomizadoOptional = campoCustomizadoRepository
				.findByIdAndUsuario(idCampo, idUsuario);
		if (!campoCustomizadoOptional.isPresent())
			throw new BadRequestException("Campo não encontrado");

		return campoCustomizadoOptional.get();
	}

	public void validaAtivo(CampoCustomizadoModel campo, boolean ativo) throws BadRequestException {
		if (campo.isAtivo() == ativo)
			throw new BadRequestException("campo está desativado des de " + campo.getDataAlteracao());
	}

	public CampoCustomizadoModel getCampoCustomizado(UsuarioModel usuario, Integer id) throws BadRequestException {

		Page<CampoCustomizadoModel> pageCampo = getCampoCustomizado(usuario, id, null, true, 0, 1);

		if (pageCampo.getContent().isEmpty())
			throw new BadRequestException("Campo não encontrado");

		return pageCampo.getContent().get(0);
	}
}
