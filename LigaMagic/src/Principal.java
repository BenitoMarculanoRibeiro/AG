import java.util.ArrayList;

public class Principal {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		ArrayList<Print> list = new ArrayList<>();
		int contador = 0;
		Print p = new Print();
		contador++;
		final String path = "armazenar.txt";
		final String urlPreco = "ligamagicPreco2.txt";
		final String urlQtd = "ligamagicQtd2.txt";
		final String urlPedido = "ligamagicPedido1.txt";
		final String urlLojas = "ligamagicFrete.txt";
		final int tipoCruzamento = 1;
		final int tipoInsercao = 2;
		final int tamPop = 1000;
		final int tamPopTop = 10;
		final int ciclos = 1000;
		final int tipoCromossomo = 1;// 1 = randomico, 2 = menor pre�o carta, 3 = menor frete
		Card[] vetCard = ControlArq.carregaCards(urlQtd, urlPreco);
		Loja[] vetLoja = ControlArq.carregaLojas(urlLojas);
		Pedido ped = ControlArq.carregaPedido(urlPedido);
		Populacao pop = new Populacao(tamPop, vetCard, vetLoja, ped, tamPopTop, tipoCromossomo);
		int cont = 0;
		Cromossomo[] pais, filhos;
		boolean condMut, condInsert;
		long t = System.currentTimeMillis();
		Cromossomo top1 = pop.getTop1();
		int geracoes =0;
		do {
			pais = Selecao.S(pop, 1, 2);
			filhos = Cruzamento.S(pais, vetCard, vetLoja, tipoCruzamento);
			condMut = Mutacao.S(filhos, 1, 1, 85, 2, null, vetCard, vetLoja);
			condInsert = Insercao.S(pop, pais, filhos, tipoInsercao, t, vetCard, vetLoja, null);
			if(top1.getFitness() > pop.getTop1().getFitness()) {
		        cont = 0;
		        top1 = pop.getTop1();
			}
		    cont ++;
		    geracoes ++;
		} while (cont < ciclos);
		Util.p(pop.exibe(vetCard, vetLoja));
		Util.p("top1: " + pop.getTop1().exibe3(vetCard, vetLoja));

		// Extra
		p = new Print("" + t, "" + 1, "" + tamPop, pop.getTop1().exibe3(vetCard, vetLoja));
		list.add(p);
		//ControlArq.escrever(path, p.toString());
		System.out.println(contador);

		System.out.println("\n\n" + contador + "\n\n" + list.size() + "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

		System.out.println(list.toString());

	}
}
