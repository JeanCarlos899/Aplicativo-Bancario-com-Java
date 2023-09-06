import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // para ler as entradas do usuário
        List<Conta> contas = new ArrayList<>(); // lista de contas cadastradas
        List<Transferencia> transferencias = new ArrayList<>(); // lista de transferências realizadas
        Conta conta = null; // conta que está logada
        int entrada = 0; // entrada do usuário

        while (true) {

            if (conta == null) {
                System.out.println("--------------------------------------------------");
                System.out.println("Bem vindo ao banco TechFinance! Escolha uma opção:");
                System.out.println("1 - Criar conta");
                System.out.println("2 - Acessar conta");
                System.out.println("0 - Sair");
                System.out.println("--------------------------------------------------");

                try {
                    entrada = scanner.nextInt();
                } catch (Exception e) {
                    System.out.println("Entrada inválida");
                    scanner.nextLine();
                    continue;
                }
                if (entrada == 0) {
                    System.out.println("Obrigado por utilizar nossos serviços!");
                    break;
                } else if (entrada == 1) {
                    System.out.println("--------------------------------------------------");
                    System.out.println("Digite seu nome:");
                    String nome = scanner.next();
                    System.out.println("--------------------------------------------------");
                    System.out.println("Digite seu cpf:");
                    String cpf = scanner.next();
                    System.out.println("--------------------------------------------------");
                    System.out.println("Crie sua senha:");
                    String senha = scanner.next();
                    System.out.println("--------------------------------------------------");

                    Conta newConta = new Conta(nome, cpf, senha);
                    contas.add(newConta);
                    System.out.println("Conta criada com sucesso!");
                    System.out.println(newConta.getDadosConta());
                    continue;
                } else if (entrada == 2) {
                    System.out.println("--------------------------------------------------");
                    System.out.println("Bem vindo novamente!");
                    System.out.println("--------------------------------------------------");
                    System.out.println("Digite seu cpf ou número da conta:");
                    System.out.println("--------------------------------------------------");
                    String user = scanner.next();
                    System.out.println("--------------------------------------------------");
                    System.out.println("Digite sua senha:");
                    System.out.println("--------------------------------------------------");
                    String senha = scanner.next();
                    System.out.println("--------------------------------------------------");

                    for (Conta c : contas) {
                        if (c.login(user, senha)) {
                            conta = c;
                            break;
                        }
                    }

                    if (conta == null) {
                        System.out.println("Conta não encontrada");
                        continue;
                    } else {
                        System.out.println("Login realizado com sucesso!");
                        continue;
                    }
                } else {
                    System.out.println("--------------------------------------------------");
                    System.out.println("Opção inválida");
                    continue;
                }
            } else {
                while (conta != null) {
                    System.out.println("--------------------------------------------------");
                    System.out.println("Bem-vindo(a) " + conta.getNome() + "! Escolha uma opção:");
                    System.out.println(conta.getDadosConta());
                    System.out.println("--------------------------------------------------");
                    System.out.println("1 - Depositar");
                    System.out.println("2 - Pagar conta");
                    System.out.println("3 - Transferir (PIX)");
                    System.out.println("4 - Extrato");
                    System.out.println("0 - Sair");
                    System.out.println("--------------------------------------------------");

                    try {
                        entrada = scanner.nextInt();
                    } catch (Exception e) {
                        System.out.println("Entrada inválida");
                        scanner.nextLine();
                        continue;
                    }

                    switch (entrada) {
                        case 0:
                            System.out.println("Obrigado por utilizar nossos serviços!");
                            conta = null;
                            break;
                        case 1:
                            System.out.println("--------------------------------------------------");
                            System.out.println("Digite o valor a ser depositado:");
                            System.out.println("--------------------------------------------------");
                            double valor = scanner.nextDouble();
                            if (conta.deposito(valor)) {
                                System.out.println("--------------------------------------------------");
                                System.out.println("Depósito realizado com sucesso!");
                                continue;
                            } else {
                                System.out.println("--------------------------------------------------");
                                System.out.println("Valor inválido");
                                continue;
                            }
                        case 2:
                            System.out.println("--------------------------------------------------");
                            System.out.println("Digite o valor a ser pago:");
                            System.out.println("--------------------------------------------------");
                            valor = scanner.nextDouble();
                            if (conta.pagamento(valor)) {
                                System.out.println("--------------------------------------------------");
                                System.out.println("Pagamento realizado com sucesso!");
                                continue;
                            } else {
                                System.out.println("--------------------------------------------------");
                                System.out.println("Valor inválido");
                                continue;
                            }
                        case 3:
                            System.out.println("--------------------------------------------------");
                            System.out.println("Digite o cpf ou número da conta do destinatário:");
                            System.out.println("--------------------------------------------------");
                            String user = scanner.next();
                            System.out.println("--------------------------------------------------");
                            System.out.println("Digite o valor a ser transferido:");
                            System.out.println("--------------------------------------------------");
                            valor = scanner.nextDouble();
                            System.out.println("--------------------------------------------------");

                            Conta contaDestino = null;
                            for (Conta c : contas) {
                                if (c.pixCheck(user)) {
                                    contaDestino = c;
                                    break;
                                }
                            }

                            if (contaDestino == null) {
                                System.out.println("Conta não encontrada");
                                continue;
                            } else {
                                Transferencia transferencia = new Transferencia(conta, contaDestino, valor);
                                System.out.println(
                                        "Deseja realizar uma transferência para " + contaDestino.getNome() + "? (s/n)");
                                String confirmacao = scanner.next();
                                if (confirmacao.equals("s")) {
                                    transferencia.transferir();
                                    transferencias.add(transferencia);
                                    System.out.println("Transferência realizada com sucesso!");
                                    continue;
                                } else {
                                    System.out.println("Transferência cancelada");
                                    continue;
                                }
                            }
                        case 4:
                            System.out.println("--------------------------------------------------");
                            System.out.println("Extrato:");
                            for (Transferencia t : transferencias) {
                                if (t.getContaOrigem() == conta) {
                                    System.out.println("Transferência para " + t.getContaDestino().getNome() + " - "
                                            + "R$ " + t.getValor());
                                } else if (t.getContaDestino() == conta) {
                                    System.out.println(
                                            "Transferência recebida de " + t.getContaOrigem().getNome() + " - " + "R$" + t.getValor());
                                }
                            }
                            continue;
                        default:
                            System.out.println("Opção inválida");
                            continue;
                    }
                }
            }
        }

        scanner.close();
    }
}
