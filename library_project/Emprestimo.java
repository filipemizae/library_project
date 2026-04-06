import java.time.LocalDate;

public class Emprestimo {
    private Exemplar exemplar;
    private Usuario usuario;
    private LocalDate dataEmprestimo;
    private LocalDate dataPrevistaDevolucao;
    private LocalDate dataDevolvida;

    public Emprestimo(Exemplar exemplar, Usuario usuario, LocalDate dataEmprestimo) {
        this.exemplar = exemplar;
        this.usuario = usuario;
        this.dataEmprestimo = dataEmprestimo;
        this.dataPrevistaDevolucao = dataEmprestimo.plusDays(15);

        exemplar.setDisponivel(false);
        System.out.println("Exemplar emprestado.");
    }

    public void devolver() {
        this.dataDevolvida = LocalDate.now();
        exemplar.setDisponivel(true);

        System.out.println("Devolução realizada em: " + dataDevolvida);
        System.out.println("Exemplar disponível.");
    }

    public Exemplar getExemplar() {
        return exemplar;
    }

    public Usuario getUsuario(){
        return usuario;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataPrevistaDevolucao() {
        return dataPrevistaDevolucao;
    }

    public LocalDate prolongarDataDevEmprestimo(int dias) {
        this.dataPrevistaDevolucao = dataPrevistaDevolucao.plusDays(dias);
        return this.dataPrevistaDevolucao;
    }

    public LocalDate prolongarDataDevEmprestimo() {
        this.dataPrevistaDevolucao = dataPrevistaDevolucao.plusDays(7);
        return this.dataPrevistaDevolucao;
    }


}