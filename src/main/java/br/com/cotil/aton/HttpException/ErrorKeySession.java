package br.com.cotil.aton.HttpException;

public class ErrorKeySession extends BaseException {

  private static final long serialVersionUID = 6166940709733250672L;

  public ErrorKeySession(String mensagem) {
    super(
        "Olá se você recebeu esse erro ao tentar executar os teste é porque não foi possível pegar a ks. Provavelmente vocÊ errou as credenciais digitadas, algum erro no auth ou seu horário de acesso expirou",
        mensagem, 406);
  }

}
