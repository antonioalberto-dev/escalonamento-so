import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class fcfs {
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) {

		// declaracao de variaveis
		Scanner sc = new Scanner(System.in);
		int qtdProcessos, entrada, tempoAtual;
		double tempoExecucao, tempoEspera, turnaround;
		ArrayList processos, tempoChegada, tempoIngresso, tempoFinal, tempoInicial;
		int teste = 0;
		String formato, saida;
		DecimalFormat decimal = new DecimalFormat("0.00");

		System.out.println("Quantos processos deseja armazenar? ");
		qtdProcessos = sc.nextInt();

		while (qtdProcessos != 0) {
			teste++;
			processos = new ArrayList<>();
			tempoChegada = new ArrayList<>();
			tempoIngresso = new ArrayList<>();
			tempoFinal = new ArrayList<>();
			tempoInicial = new ArrayList<>();
			tempoEspera = 0;
			tempoExecucao = 0;
			turnaround = 0.0;

			for (int i = 1; i <= qtdProcessos; i++) {
				System.out.println("Qual o tempo de chegada do processo P" + i);
				entrada = sc.nextInt();
				tempoChegada.add(entrada);
				System.out.println("Qual o burst do processo P" + i);
				entrada = sc.nextInt();
				tempoIngresso.add(entrada);
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

			System.out.println("Processamento - PART" + teste);
			System.out.println(".: ESCALONAMENTO FCFS :.");

			for (int i = 0; i < qtdProcessos; i++) {
				turnaround = (int) tempoFinal.get(i) - (int) tempoInicial.get(i);
				formato = decimal.format(turnaround);
				saida = "|Turnaround| P" + i + ": " + formato + "ms";
				saida = saida.replace(".", ",");
				System.out.println(saida);
			}

			System.out.println("-------------------------------------------------");
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

			System.out.println(" <-------- GRÁFICO DE GANTT ----------> ");
			for (int i = 0; i < qtdProcessos; i++) {
				System.out.print("P" + processos.get(i) + " ");
			}
			
			System.out.println("\n\nQuantos processos deseja armazenar?");
			qtdProcessos = sc.nextInt();
			
		}
		
		sc.close();
	}
}