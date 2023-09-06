
public class Conta {

    private String nome;
    private String cpf;
    private String senha;
    private int numeroConta;
    private double saldo;

    public Conta(String nome, String cpf, String senha) {
        this.nome = nome;
        this.numeroConta = gerarNumeroConta();
        this.saldo = 0;
        this.cpf = cpf;
        this.senha = senha;
    }

    private int gerarNumeroConta() {
        return (int) (Math.random() * 100000000);
    }

    public String getDadosConta() {
        return "Número da conta: " + this.numeroConta + "\nSaldo: R$ " + this.saldo;
    }

    public String getNome() {
        return this.nome;
    }

    public boolean setSenha(String senha) {
        if (senha.length() >= 6) {
            this.senha = senha;
            return true;
        }
        return false;
    }

    public boolean deposito(double valor) {
        if (valor > 0) {
            this.saldo += valor;
            return true;
        }
        return false;
    }

    public boolean pagamento(double valor) {
        if (valor > 0 && valor <= this.saldo) {
            this.saldo -= valor;
            return true;
        }
        return false;
    }

    public boolean pixCheck(String user) {
        if (this.cpf.equals(user) || Integer.toString(this.numeroConta).equals(user)) {
            return true;
        }
        return false;
    }

    public boolean login(String user, String senha) {
        if (this.cpf.equals(user) && this.senha.equals(senha)) {
            return true;
        } else if (Integer.toString(this.numeroConta).equals(user) && this.senha.equals(senha)) {
            return true;
        }
        return false;
    }
}
