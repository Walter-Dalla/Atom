package br.com.cotil.aton.util;

import br.com.cotil.aton.HttpException.BadRequestException;
import br.com.cotil.aton.HttpException.BaseException;
import org.springframework.data.domain.Page;

import java.util.List;

public class PageUtils {

    public static List<?> validIfPageIsEmpty(Page<?> pageCampo, BaseException httpExeption){
        if (pageCampo.getContent().isEmpty())
            throw httpExeption;

        return pageCampo.getContent();
    }

}
