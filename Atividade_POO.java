/**
 * Atividade de revisão — Conceitos fundamentais de Orientação a Objetos
 * Solução completa das 5 partes do roteiro (GCVA).
 */

import java.util.ArrayList;
import java.util.List;

// ===================== PARTE 3 — Interface =====================
interface IConsole {
    void ligar();
    double calcularPreco();
    String getNome();
}

// ===================== PARTE 3 — Composição =====================
class DadosConsole {
    private final String nome;
    private final double precoBase;

    public DadosConsole(String nome, double precoBase) {
        this.nome = nome;
        this.precoBase = precoBase;
    }

    public String getNome() {
        return nome;
    }

    public double getPrecoBase() {
        return precoBase;
    }
}

// ===================== PARTE 3 — Nintendo =====================
class Nintendo implements IConsole {
    private final DadosConsole dados;

    public Nintendo(String nome, double precoBase) {
        this.dados = new DadosConsole(nome, precoBase);
    }

    @Override
    public void ligar() {
        System.out.println("Nintendo ligado.");
    }

    @Override
    public double calcularPreco() {
        return dados.getPrecoBase() * 1.10;
    }

    @Override
    public String getNome() {
        return dados.getNome();
    }
}

// ===================== PARTE 3 — Playstation =====================
// atributo "protected" (em vez de private) para permitir acesso da
// subclasse PlaystationPortatil (PARTE 4)
class Playstation implements IConsole {
    protected DadosConsole dados;

    public Playstation(String nome, double precoBase) {
        this.dados = new DadosConsole(nome, precoBase);
    }

    @Override
    public void ligar() {
        System.out.println("Playstation ligado.");
    }

    @Override
    public double calcularPreco() {
        return dados.getPrecoBase() * 1.20;
    }

    @Override
    public String getNome() {
        return dados.getNome();
    }
}

// ===================== PARTE 4 — Herança =====================
class PlaystationPortatil extends Playstation {

    public PlaystationPortatil(String nome, double precoBase) {
        super(nome, precoBase);
    }

    @Override
    public void ligar() {
        System.out.println("Playstation Portátil ligado.");
    }

    @Override
    public double calcularPreco() {
        return dados.getPrecoBase() * 1.15;
    }
}

// ===================== PARTE 5.4 — Extensibilidade (OCP) =====================
// Classe nova, criada sem tocar em nenhuma linha de Loja.
class Xbox implements IConsole {
    private final DadosConsole dados;

    public Xbox(String nome, double precoBase) {
        this.dados = new DadosConsole(nome, precoBase);
    }

    @Override
    public void ligar() {
        System.out.println("Xbox ligado.");
    }

    @Override
    public double calcularPreco() {
        return dados.getPrecoBase() * 1.18;
    }

    @Override
    public String getNome() {
        return dados.getNome();
    }
}

// ===================== PARTE 5 — Loja (Polimorfismo) =====================
class Loja {

    public void venderConsole(IConsole console) {
        console.ligar();
        System.out.println(console.getNome() + " -> Preço final: R$ " + console.calcularPreco());
    }

    public void venderVarios(List<IConsole> consoles) {
        for (IConsole console : consoles) {
            venderConsole(console);
        }
    }

    public double calcularFaturamentoTotal(List<IConsole> consoles) {
        double total = 0.0;
        for (IConsole console : consoles) {
            total += console.calcularPreco();
        }
        return total;
    }
}

public class Atividade_POO {

    public static void main(String[] args) {

        // PARTE 2 — construtor obrigatório, sem estados "pela metade"
        Nintendo nintendo = new Nintendo("Nintendo Switch", 2000);
        Playstation playstation = new Playstation("Playstation 5", 3000);
        PlaystationPortatil portatil = new PlaystationPortatil("Playstation Portátil", 2500);

        Loja loja = new Loja();

        List<IConsole> consoles = new ArrayList<>();
        consoles.add(nintendo);
        consoles.add(playstation);
        consoles.add(portatil);

        System.out.println("--- Venda inicial ---");
        loja.venderVarios(consoles);
        System.out.println("Faturamento total: R$ " + loja.calcularFaturamentoTotal(consoles));

        Xbox xbox = new Xbox("Xbox Series X", 2800);
        consoles.add(xbox);

        System.out.println("--- Venda com Xbox adicionado ---");
        loja.venderVarios(consoles);
        System.out.println("Faturamento total: R$ " + loja.calcularFaturamentoTotal(consoles));
    }
}
