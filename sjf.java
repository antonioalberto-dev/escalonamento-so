import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class sjf {

	@SuppressWarnings({ "unchecked", "rawtypes", "resource" })
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

//		 declara��o de vari�veis
		int qtdProcessos;
		ArrayList processos, ingressos, duracoes, cpIngressos = new ArrayList();
		int[] temposIniciais = new int[1], temposFinais = new int[1];
		int idProcessoAtual;
		String ordemExecucao = "", formato, saida, strEntrada;
		double tempoEspera, tempoExecucao;
		int teste = 0;
		DecimalFormat nf = new DecimalFormat("0.00");

		qtdProcessos = sc.nextInt();
		sc.nextLine();

		while (qtdProcessos != 0) {
			teste++; // conta o n�mero de testes

			ordemExecucao = "";
			processos = new ArrayList();
			ingressos = new ArrayList();
			duracoes = new ArrayList();

			temposFinais = new int[qtdProcessos];
			temposIniciais = new int[qtdProcessos];

			for (int i = 0; i < qtdProcessos; i++) {

				// l� e adiciona tempo de ingresso e o tempo de dura��o do processo
				strEntrada = sc.nextLine();

				int indiceEspaco = strEntrada.indexOf(" ");

				ingressos.add(Integer.parseInt(strEntrada.substring(0, indiceEspaco)));
				duracoes.add(Integer.parseInt(strEntrada.substring(indiceEspaco + 1)));
			}

			// cria copia da lista de tempos de ingressos devido a modificacoes
			cpIngressos = (ArrayList) ingressos.clone();

			int execucao;
			int qteprocessos = qtdProcessos;

			// tempo inicial = primeiro tempo da lista de ingressos
			int tempoAtual = (int) ingressos.get(0);
			while (qteprocessos > 0) {

				// percorre ingressos para achar processos que ingressam nesse tempo
				processos = new ArrayList();
				for (int i = 0; i < qtdProcessos; i++) {
					if ((int) ingressos.get(i) != -1 && (int) ingressos.get(i) <= tempoAtual) {
						// adicionar na lista de processos
						processos.add(i);
					}
				}

				// assumindo que o primeiro da lista eh o de menor duracao
				if (processos.isEmpty()) {
					tempoAtual++;
				} else {
					execucao = (int) processos.get(0);
					for (int i = 0; i < processos.size(); i++) {
						idProcessoAtual = (int) processos.get(i);
//						 se a dura��o do processo atual for menor do que a menor duracao j� encontrada
						if ((int) duracoes.get(idProcessoAtual) < (int) duracoes.get(execucao)) {
//							 ent�o alteramos o processo que vai executar
							execucao = (int) processos.get(i);
						}
					}

					temposIniciais[execucao] = tempoAtual;
					tempoAtual += (int) duracoes.get(execucao);
					temposFinais[execucao] = tempoAtual;
					ingressos.set(execucao, -1);

//					 define ordem de execu��o
					ordemExecucao += "P" + (execucao + 1) + " ";
					qteprocessos--;
				}
			}

//			 calcula o tempo m�dio de execu��o e tempo m�dio de espera
			tempoExecucao = 0;
			tempoEspera = 0;
			for (int i = 0; i < qtdProcessos; i++) {
				tempoExecucao += temposFinais[i] - (int) cpIngressos.get(i);
				tempoEspera += temposIniciais[i] - (int) cpIngressos.get(i);
			}
			tempoExecucao = tempoExecucao / qtdProcessos;
			tempoEspera = tempoEspera / qtdProcessos;

			System.out.println("\nTeste " + teste);

			formato = nf.format(tempoExecucao);
			saida = "Tempo medio de execucao: " + formato + "s";
			saida = saida.replace(".", ",");
			System.out.println(saida);

			formato = nf.format(tempoEspera);
			saida = "Tempo medio de espera: " + formato + "s";
			saida = saida.replace(".", ",");
			System.out.println(saida);

//			gr�fico de gantt
			System.out.println(ordemExecucao);
			System.out.println();

			strEntrada = sc.nextLine();
			qtdProcessos = Integer.parseInt(strEntrada);
		}
	}
}