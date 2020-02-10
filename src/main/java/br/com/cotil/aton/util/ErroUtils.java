package br.com.cotil.aton.util;

import br.com.cotil.aton.HttpException.BadRequestException;

import java.util.List;

public class ErroUtils {
    public static void validaErroList(List<String> erroList){
        if(!erroList.isEmpty()){
            throw new BadRequestException(erroList);
        }
    }
}
