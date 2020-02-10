package br.com.cotil.aton.util;

import br.com.cotil.aton.HttpException.BaseException;

import java.util.Optional;

public class OptionalUtils {

    public static Object validIfIsNotPresent(Optional<?> objOptional, BaseException exception) throws BaseException {
        if(!objOptional.isPresent())
            throw exception;
        return objOptional.get();
    }

    public static void validIfIsPresent(Optional<?> objOptional, BaseException exception) throws BaseException {
        if(objOptional.isPresent())
            throw exception;
    }

}
