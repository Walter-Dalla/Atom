package br.com.cotil.aton.usuario.token;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.cotil.aton.HttpException.BadRequestException;
import br.com.cotil.aton.HttpException.ForbiddenException;
import br.com.cotil.aton.usuario.conexao.ConexaoModel;

public class TokenUtils {


  public static void validaUsuario(ConexaoModel conexaoModel) throws ForbiddenException {
    if (!conexaoModel.getUsuario().isAtivo()) {
      DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
      throw new ForbiddenException("Este usuario está inativo des do dia: "
          + dateFormat.format(conexaoModel.getUsuario().getDataUltimaAtualizacao()));
    }
  }

  public static void validaToken(ConexaoModel conexao, List<String> dadosList, String ipReq)
      throws BadRequestException {

    List<String> erros = new ArrayList<String>();

    if (dadosList.isEmpty())
      erros.add("Erro: codigo do erro 1");

    if (!dadosList.contains(conexao.getNomeUsuario()))
      erros.add("Erro: codigo do erro 2");

    if (!dadosList.contains("Aton"))
      erros.add("Erro: codigo do erro 3");


    if (!validaIp(dadosList, ipReq))
      erros.add("Erro: codigo do erro 4, Ip foi alterado, é nessesario relogar no sistema");

    if (!erros.isEmpty()) {
      erros.add(0, "Token sem conexção ou expirado");
      throw new BadRequestException(erros);
    }
  }

  private static boolean validaIp(List<String> dadosList, String ipReq) {

    // Na AWS ele concatena o IP do usuario com o Ip interno deles e separa por ','
    // e isto estava causando mal funcionamento no codigo.
    List<String> ips = Arrays.asList(dadosList.get(dadosList.size() - 1).split(","));
    Boolean hasIp = false;

    for (String ip : Arrays.asList(ipReq.split(","))) {
      if (ips.contains(ip))
        hasIp = true;
    }

    return hasIp;
  }
}
