import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Conta> contas = new ArrayList<>();
        int entrada = 0;

        while (true) {

            System.out.println("Digite o número da operação que deseja realizar:");
            System.out.println("1 - Criar conta");
            System.out.println("2 - Depositar");
            System.out.println("3 - Sacar");
            System.out.println("4 - Transferir");
            System.out.println("5 - Ver saldo");
            System.out.println("6 - Ver número da conta");
            System.out.println("7 - Ver nome do titular");
            System.out.println("8 - Ver todas as contas");
            System.out.println("0 - Sair");

            try {
                entrada = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Entrada inválida");
                scanner.nextLine();
                continue;
            }

            if (entrada == 0) {
                break;
            }
            
            else if (entrada == 1) {
                System.out.println("Digite o nome do titular:");
                String nome = scanner.next();
                System.out.println("Digite o número da conta:");
                int numeroConta = scanner.nextInt();
                System.out.println("Digite o saldo inicial:");
                double saldo = scanner.nextDouble();

                contas.add(new Conta(nome, numeroConta, saldo));
                continue;
            }

            for (Conta conta : contas) {
                System.out.println("Nome: " + conta.getNome());
                System.out.println("Número da Conta: " + conta.getNumeroConta());
                System.out.println("Saldo: " + conta.getSaldo());
                System.out.println("--------");
            }
        }
        
        scanner.close();
    }
}
