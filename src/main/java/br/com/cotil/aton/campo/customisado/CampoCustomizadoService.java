package br.com.cotil.aton.campo.customisado;

import br.com.cotil.aton.campo.padrao.CampoPadraoRepository;
import br.com.cotil.aton.campo.padrao.CampoPadraoUtils;
import br.com.cotil.aton.util.PageUtils;
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
	private CampoCustomizadoUtils campoCustomizadoUtils;
	private CampoPadraoUtils campoPadraoUtils;

	public CampoCustomizadoService(CampoCustomizadoRepository campoCustomizadoRepository,
		   CampoPadraoRepository campoPadraoRepository) {
		super();
		this.campoCustomizadoRepository = campoCustomizadoRepository;
		this.campoCustomizadoUtils = new CampoCustomizadoUtils(campoCustomizadoRepository);
		this.campoPadraoUtils = new CampoPadraoUtils(campoPadraoRepository);
	}

	public Page<CampoCustomizadoModel> getCampoCustomizado(UsuarioModel usuario, Integer id, String nome, boolean ativo,
			Boolean marcado, Integer page, Integer size) throws BadRequestException {

		Pageable pageable = Utils.setPageRequestConfig(page, size);

		return campoCustomizadoRepository.findWithFilter(id, nome, ativo, usuario.getId(), marcado, pageable);
	}

	public CampoCustomizadoModel postCampoCustomisado(UsuarioModel usuario, CampoCustomizadoModel campoCustomizado)
			throws BadRequestException {

		campoCustomizado.setUsuario(usuario);
		campoCustomizado.setAtivo(true);
		campoCustomizado
				.setCampoPadrao(campoPadraoUtils.validaCampoPadrao(campoCustomizado.getCampoPadrao().getId()));
		campoCustomizado = CampoCustomizadoUtils.padronizar(campoCustomizado);

		return campoCustomizadoRepository.save(campoCustomizado);

	}

	public CampoCustomizadoModel atualizarCampoCustomizado(UsuarioModel usuario, CampoCustomizadoModel campoCustomizado)
			throws BadRequestException, ForbiddenException {

		boolean ativo = true;

		CampoCustomizadoModel campoDoBanco = campoCustomizadoUtils.validaSeCampoExiste(campoCustomizado.getId(), usuario.getId());
		campoCustomizadoUtils.validaAtivo(campoDoBanco, ativo);

		campoCustomizado = CampoCustomizadoUtils.padronizar(campoCustomizado);

		campoDoBanco.setNome(campoCustomizado.getNome());
		campoDoBanco.setPlaceholder(campoCustomizado.getPlaceholder());
		campoDoBanco.setToolTip(campoCustomizado.getToolTip());

		return campoCustomizadoRepository.save(campoDoBanco);
	}

	public CampoCustomizadoModel desmarcarCampoCustomizado(UsuarioModel usuario, Integer idCampo) throws BadRequestException {

		CampoCustomizadoModel campoNoBanco = campoCustomizadoUtils.validaSeCampoExiste(idCampo, usuario.getId());

		campoCustomizadoUtils.validaAtivo(campoNoBanco, true);

		campoCustomizadoRepository.delete(campoNoBanco);

		return campoNoBanco;
	}

	public CampoCustomizadoModel desativarCampoCustomizado(UsuarioModel usuario, Integer idCampo, boolean ativo)
			throws BadRequestException, ForbiddenException {

		CampoCustomizadoModel campoNoBanco = campoCustomizadoUtils.validaSeCampoExiste(idCampo, usuario.getId());

		campoCustomizadoUtils.validaAtivo(campoNoBanco, ativo);

		if(campoNoBanco.isMarcado())
			throw new BadRequestException("O campo não pode ser deletado pois está marcado!");
		
		campoCustomizadoRepository.delete(campoNoBanco);
		
		return campoNoBanco;
	}

	public CampoCustomizadoModel marcarCampoCustomizado(UsuarioModel usuario, Integer idCampo)
			throws BadRequestException {

		boolean ativo = true;

		CampoCustomizadoModel campoDoBanco = campoCustomizadoUtils.validaSeCampoExiste(idCampo, usuario.getId());
		campoCustomizadoUtils.validaAtivo(campoDoBanco, ativo);

		CampoCustomizadoModel campo = new CampoCustomizadoModel(campoDoBanco);

		campo.setMarcado(true);
		campo.setId(0);
		campo.setDataAlteracao(null);
		campo.setDataCriacao(null);

		return campoCustomizadoRepository.save(campo);
	}

	//Sla o pq isso ta aqui
	public CampoCustomizadoModel getCampoCustomizado(UsuarioModel usuario, Integer id) throws BadRequestException {

		Page<CampoCustomizadoModel> pageCampo = getCampoCustomizado(usuario, id, null, true, null, 0, 1);

		return (CampoCustomizadoModel)
				PageUtils.validIfPageIsEmpty(pageCampo, new BadRequestException("Campo não encontrado")).get(0);
	}
}
