
public class Conta {
    
    private String nome;
    private int numeroConta;
    private double saldo;

    public Conta(String nome, int numeroConta, double saldo) {
        this.nome = nome;
        this.numeroConta = numeroConta;
        this.saldo = saldo;
    }

    public String getNome() {
        return nome;
    }

    public double getSaldo() {
        return saldo;
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean transferir(Conta conta, double valor) {
        if (valor > 0 && valor <= this.saldo) {
            this.saldo -= valor;
            conta.saldo += valor;
            return true;
        }
        return false;
    }
    
}
