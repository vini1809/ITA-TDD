package banco;

public class CaixaEletronico {
	
	private String logContaCorrente;
IHardware hardware;
	
	public String logar(String numCC) throws ProblemaEncontradoCCException, HardwareException{
		hardware.pegarNumeroDaContaCartao(numCC);
		setLogContaCorrente(numCC);
		return "Usuário Autenticado";
	}
	
	public String sacar(BigDecimal vlrSacar) throws ProblemaEncontradoCCException, SemSaldoException, HardwareException{
		ContaCorrente contaCorrente = hardware.getServicoRemoto().recuperarConta(getLogContaCorrente());
		BigDecimal saldo = contaCorrente.getSaldo();
		if(saldo.compareTo(vlrSacar) > 0)
		{
			contaCorrente.setSaldo(saldo.subtract(vlrSacar));
			hardware.entregarDinheiro();
			hardware.getServicoRemoto().persistirConta(contaCorrente);
			return "Retire seu dinheiro";
		}
		else
		{
			throw new SemSaldoException("Saldo insuficiente");
		}
	}
	
	public String depositar(BigDecimal vlrDeposito) throws ProblemaEncontradoCCException, HardwareException {
		ContaCorrente contaCorrente = hardware.getServicoRemoto().recuperarConta(getLogContaCorrente());
		BigDecimal saldo = contaCorrente.getSaldo();
		hardware.lerEnvelope();
		contaCorrente.setSaldo(saldo.add(vlrDeposito));
		hardware.getServicoRemoto().persistirConta(contaCorrente);
		return "Depósito recebido com sucesso";
	}
	
	public String saldo() throws ProblemaEncontradoCCException, HardwareException{
		ContaCorrente contaCorrente = hardware.getServicoRemoto().recuperarConta(getLogContaCorrente());
		BigDecimal saldo = contaCorrente.getSaldo();
		
		return "O saldo é R$" + saldo.toString();
	}

	public String getLogContaCorrente() {
		return logContaCorrente;
	}

	public void setLogContaCorrente(String logContaCorrente) {
		this.logContaCorrente = logContaCorrente;
	}
	
	public CaixaEletronico(MockHardware mockHardware){
		hardware = mockHardware;
	}

}

}
