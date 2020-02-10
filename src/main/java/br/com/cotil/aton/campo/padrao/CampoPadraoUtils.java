package br.com.cotil.aton.campo.padrao;

import br.com.cotil.aton.HttpException.BadRequestException;
import br.com.cotil.aton.util.Utils;

import java.util.Optional;

public class CampoPadraoUtils {

    private CampoPadraoRepository campoPadraoRepository;

    public CampoPadraoUtils(CampoPadraoRepository campoPadraoRepository) {
        this.campoPadraoRepository = campoPadraoRepository;
    }

    public CampoPadraoModel validaCampoPadrao(Integer id) throws BadRequestException {

        if(Utils.isNullOrEmpty(id))
            throw new BadRequestException("Campo não encontrado");

        Optional<CampoPadraoModel> campoPadrao = campoPadraoRepository.findById(id);

        if (!campoPadrao.isPresent())
            throw new BadRequestException("Campo não encontrado");

        return campoPadrao.get();
    }
}
