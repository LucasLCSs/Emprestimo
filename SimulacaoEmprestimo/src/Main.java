import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner entrada = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("#, ##0.00");

        System.out.println("----Simulador de Emprestimos----");

        System.out.println("Digite o valor do empréstimo R$: ");
        double valorEmprestimo = entrada.nextDouble();

        System.out.println("Digite a taxa de juros mensal R$: ");
        double jurosMensal = entrada.nextDouble() / 100; // Converter para decimal

        System.out.println("Digite o número de parcelas: ");
        int numeroParcelas = entrada.nextInt();

        double parcelas = calculoParcelas(valorEmprestimo, jurosMensal, numeroParcelas);

        System.out.println("\n----Resultado----");
        System.out.println("Valor do Empréstimo: " + df.format(valorEmprestimo));
        System.out.println("Taxa de juros mensal: " + (jurosMensal * 100) + "%");
        System.out.println("Número de parcelas: " + numeroParcelas);
        System.out.println("Valor da parcela: " + df.format(parcelas));

        System.out.println("----Demonstrativo de Parcelas----");
        gerarDemonstrativo(valorEmprestimo, jurosMensal, numeroParcelas, parcelas, df);

        entrada.close();
    }

    // Metodo para calcular as parcelas no sistema price
    // Usei a formula de calcular parcelas que é Parcela = (valor do emprestimo * taxa do juros) / (1 - (1 + taxa do juros) - número de parcelas)

    public static double calculoParcelas(double valorEmprestimo, double jurosMensal, int numeroParcelas) {
        return (valorEmprestimo * jurosMensal) / (1 - Math.pow(1 + jurosMensal, - numeroParcelas));
    }
    // Metodo para gerar a demonstração do empréstimo
    public static void gerarDemonstrativo(double saldoDevedor, double juros, int meses, double parcelas, DecimalFormat df) {
        double jurosMensal;
        double amortizacao;

        for(int i = 1; i <= meses; i++) {
            jurosMensal = saldoDevedor * juros;
            amortizacao = parcelas - juros;
            saldoDevedor -= amortizacao;

            System.out.println("Parcela " + i +": R$ " + df.format(parcelas) +
                    " | Juros: " + df.format(jurosMensal) +
                    " | Amortização: " + df.format(amortizacao) +
                    " | Saldo devedor: " + (saldoDevedor > 0 ? df.format(saldoDevedor) : "0,00"));
        }
    }
}