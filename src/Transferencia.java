public class Transferencia {
    private Conta contaOrigem;
    private Conta contaDestino;
    private double valor;

    public Transferencia(Conta contaOrigem, Conta contaDestino, double valor) {
        this.contaOrigem = contaOrigem;
        this.contaDestino = contaDestino;
        this.valor = valor;
    }

    public Conta getContaOrigem() {
        return this.contaOrigem;
    }

    public Conta getContaDestino() {
        return this.contaDestino;
    }

    public double getValor() {
        return this.valor;
    }

    public boolean transferir() {
        if (this.contaOrigem.pagamento(this.valor)) {
            this.contaDestino.deposito(this.valor);
            return true;
        }
        return false;
    }


}
