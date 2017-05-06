package br.univel.meustestes;

import java.math.BigDecimal;
import java.util.List;

import br.univel.minhaarvore.UniArvore;
import br.univel.minhaarvore.UniArvoreImpl;
import br.univel.minhaarvore.UniNode;
import br.univel.minhaarvore.UniNodeImpl;

		public class Principal {
			public Principal() {
				Conta contaAgua = new Conta(1, ">Água", new BigDecimal(80));
				UniNode<Conta> nodeAgua = new UniNodeImpl<>(contaAgua);

				Conta contaAluguel = new Conta(2, ">>Aluguel", new BigDecimal(1200));
				UniNode<Conta> nodeAluguel = new UniNodeImpl<>(contaAluguel);

				Conta contaInterTele = new Conta(3, ">>>Internet Telefone", new BigDecimal(250));
				UniNode<Conta> nodeInterTele = new UniNodeImpl<>(contaInterTele);

				Conta contaEnergia = new Conta(4, ">>>>Energia Eletrica", new BigDecimal(375));
				UniNode<Conta> nodeEnergia = new UniNodeImpl<>(contaEnergia);

				Conta despesasAdm = new Conta(1, "#Despesas Administrativas#", new BigDecimal(0));
				UniNode<Conta> nodeAdm = new UniNodeImpl<>(despesasAdm);

				nodeAdm.addFilho(nodeAgua);
				nodeAdm.addFilho(nodeAluguel);
				nodeAdm.addFilho(nodeInterTele);
				nodeAdm.addFilho(nodeEnergia);

				Conta lazerpisci = new Conta(1, ">Piscina", new BigDecimal(300.00));
				UniNode<Conta> nodeLazerPisci = new UniNodeImpl<>(lazerpisci);

				Conta lazerchurras = new Conta(2, ">>Churrasqueira", new BigDecimal(700.00));
				UniNode<Conta> nodeLazerChurras = new UniNodeImpl<>(lazerchurras);
				
				Conta lazerfutebol = new Conta(1, ">>>Futebol", new BigDecimal(100.00));
				UniNode<Conta> nodeLazerfute = new UniNodeImpl<>(lazerfutebol);

				Conta lazer = new Conta(2, "#Lazer#", new BigDecimal(0));
				UniNode<Conta> nodeLazer = new UniNodeImpl<>(lazer);
				nodeLazer.addFilho(nodeLazerPisci);
				nodeLazer.addFilho(nodeLazerChurras);
				nodeLazer.addFilho(nodeLazerfute);

				Conta contaJardim = new Conta(1, ">Jardineiro", new BigDecimal(1200.00));
				UniNode<Conta> nodeJardineiro = new UniNodeImpl<>(contaJardim);

				Conta contaSegur = new Conta(2, ">>Segurança", new BigDecimal(1500.00));
				UniNode<Conta> nodeSeguranca = new UniNodeImpl<>(contaSegur);

				Conta contaPort = new Conta(3, ">>>Porteiro", new BigDecimal(1300.00));
				UniNode<Conta> nodePorteiro = new UniNodeImpl<>(contaPort);

				Conta gastosFuncionarios = new Conta(3, "#Funcionarios#", new BigDecimal(0));
				UniNode<Conta> nodeGastosfunc = new UniNodeImpl<>(gastosFuncionarios);
				nodeGastosfunc.addFilho(nodeJardineiro);
				nodeGastosfunc.addFilho(nodeSeguranca);
				nodeGastosfunc.addFilho(nodePorteiro);

				Conta taxacond = new Conta(1, ">Taxa de Condominio", new BigDecimal(800.00));
				UniNode<Conta> nodeCond = new UniNodeImpl<>(taxacond);

				Conta taxareser = new Conta(2, ">>>Taxa de Reserva", new BigDecimal(430));
				UniNode<Conta> nodeReserv = new UniNodeImpl<>(taxareser);

				Conta materiais = new Conta(4, "#Taxas#", new BigDecimal(0));
				UniNode<Conta> nodeTaxas = new UniNodeImpl<>(materiais);
				nodeTaxas.addFilho(nodeCond);
				nodeTaxas.addFilho(nodeReserv);

				Conta despesasOper = new Conta(1, "Despesas Operacionais", new BigDecimal(0));
				UniNode<Conta> nodeOper = new UniNodeImpl<>(despesasOper);
				nodeOper.addFilho(nodeAdm);
				nodeOper.addFilho(nodeLazer);
				nodeOper.addFilho(nodeGastosfunc);
				nodeOper.addFilho(nodeTaxas);

				UniArvore<UniNode<Conta>> planoContas = new UniArvoreImpl(nodeOper);
				mostrarTodosConsole(planoContas.getRaiz(), " , ");
				somarFilhos(planoContas.getRaiz()); 
				System.out.println("\n ## Total de Gastos ##  R$: " + planoContas.getRaiz().getConteudo().getValor());

			}
			
			public void somarFilhos(UniNode<Conta> noRaiz) {
				List<UniNode<Conta>> arvore = noRaiz.getFilhos();

				for (int i = 0; i < arvore.size(); i++) {
					
					if (arvore.get(i).isLeaf()) {
						
						noRaiz.getConteudo().setValor(noRaiz.getConteudo().getValor().add(arvore.get(i)
								.getConteudo().getValor()));
					} 
					else {
						
						somarFilhos(arvore.get(i));
						noRaiz.getConteudo().setValor(noRaiz.getConteudo().getValor()
								.add(arvore.get(i).getConteudo().getValor()));
					}
				}

			}

			public void mostrarTodosConsole(UniNode<Conta> noRaiz, String operacao) {
				List<UniNode<Conta>> filhos = noRaiz.getFilhos();

				if(noRaiz.isLeaf()){
					System.out.println(operacao.split(",")[0] + operacao.split(",")[1] +String.format("%02d", noRaiz.getConteudo().getId()) + "\t\t\t" + 
							operacao.split(",")[0] + noRaiz.getConteudo().getNome() +
							": R$" + noRaiz.getConteudo().getValor());
				}
				else{
					System.out.println(operacao.split(",")[0] + operacao.split(",")[1] +String.format("%02d", noRaiz.getConteudo().getId()) + "\t\t\t" + 
							operacao.split(",")[0] + noRaiz.getConteudo().getNome());
					operacao = " " + operacao + String.format("%02d", noRaiz.getConteudo().getId()) + ".";
					for(int i = 0; i < filhos.size(); i++){	
						mostrarTodosConsole(filhos.get(i), operacao);
						
					}
				}

			}


	public static void main(String[] args) {
		
		new Principal();
		
	}
	
}
