package ranking;

public class Armazenamento implements ArmazemDePontos {
	private GerenciadorDeUsuario gerenciadorDeUsuario;

	public Armazenamento(GerenciadorDeUsuario gerenciadorDeUsuario) {
		this.gerenciadorDeUsuario = gerenciadorDeUsuario;
	}

	public void armazenarPontosDoUsuario(String usu, String tipoDePonto, int pontos) {
		Usuario usuario = armazenaUsuario(usu);
		usuario.incluirPontos(tipoDePonto, pontos);

		guardarUsuario(usuario);
	}

	public int quantidadeDePontosPorTipoDoUsuario(String usu, String tipodePonto) {
		return armazenaUsuario(usu).qtdDePontosPorTipo(tipodePonto);
	}

	public Set<Usuario> listaUsuariosPontuados() {
		return listaUsuarios().values().stream().filter(usuario -> usuario.comPontuacao()).collect(Collectors.toSet());
	}

	public Set<Ponto> tiposDepontosDeUmUsuario(String usu) {
		return armazenaUsuario(usu).getPontuacao();
	}

	private Usuario armazenaUsuario(String usu) {
		return gerenciadorDeUsuario.retornaUmNovoUsuario(usu);
	}

	private Map<String, Usuario> listaUsuarios() {
		return gerenciadorDeUsuario.listarUsuario();
	}

	private void guardarUsuario(Usuario usu) {
		gerenciadorDeUsuario.guardar(usu);
	}
}
