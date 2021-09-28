import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class fcfs {
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) {

		// declaracao de variaveis
		Scanner sc = new Scanner(System.in);
		int qtdProcessos, tempoAtual;
		double tempoExecucao, tempoEspera;
		ArrayList processos, tempoChegada, tempoIngresso, tempoFinal, tempoInicial;
		int teste = 0;
		String formato, saida, strEntrada;
		DecimalFormat decimal = new DecimalFormat("0.00");

		qtdProcessos = sc.nextInt();
		sc.nextLine();

		while (qtdProcessos != 0) {
			teste++;
			processos = new ArrayList<>();
			tempoChegada = new ArrayList<>();
			tempoIngresso = new ArrayList<>();
			tempoFinal = new ArrayList<>();
			tempoInicial = new ArrayList<>();
			tempoEspera = 0;
			tempoExecucao = 0;

			for (int i = 1; i <= qtdProcessos; i++) {			 
				
				strEntrada = sc.nextLine();
				
				int indiceEspaco = strEntrada.indexOf(" ");
						
				tempoChegada.add(Integer.parseInt(strEntrada.substring(0, indiceEspaco)));
				tempoIngresso.add(Integer.parseInt(strEntrada.substring(indiceEspaco+1)));

				processos.add(i);
			}

			tempoAtual = (int) tempoChegada.get(0);

			for (int i = 0; i < qtdProcessos; i++) {
				if ((int) tempoChegada.get(i) > tempoAtual)
					tempoAtual = (int) tempoChegada.get(i);
				tempoInicial.add(tempoAtual);
				tempoAtual += (int) tempoIngresso.get(i);
				tempoFinal.add(tempoAtual);
			}

			for (int i = 0; i < qtdProcessos; i++) {
				tempoExecucao += (int) tempoFinal.get(i) - (int) tempoChegada.get(i);
				tempoEspera += (int) tempoInicial.get(i) - (int) tempoChegada.get(i);
			}

			System.out.println("\nTeste" + teste);

			// calcula o tempo medio de execução e tempo medio de espera
			tempoExecucao = tempoExecucao / qtdProcessos;
			tempoEspera = tempoEspera / qtdProcessos;

			formato = decimal.format(tempoExecucao);
			saida = "Tempo médio de execução: " + formato + "s";
			saida = saida.replace(".", ",");
			System.out.println(saida);

			formato = decimal.format(tempoEspera);
			saida = "Tempo médio de espera: " + formato + "s";
			saida = saida.replace(".", ",");
			System.out.println(saida);

			// gráfico de gantt
			for (int i = 0; i < qtdProcessos; i++) {
				System.out.print("P" + processos.get(i) + " ");
			}

			System.out.println("\n");
			
			strEntrada = sc.nextLine();
			qtdProcessos = Integer.parseInt(strEntrada);
		}

		sc.close();
	}
}