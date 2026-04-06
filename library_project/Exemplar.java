import java.util.ArrayList;

public class Exemplar{
    private int codigoExemplar;
    private boolean disponivel;

    public Exemplar(int codigoExemplar, boolean disponivel) {
        this.codigoExemplar = codigoExemplar;
        this.disponivel = disponivel;
    }

    public void mostrarExemplar(){
        System.out.println("Código do exemplar: " + codigoExemplar);
        System.out.println("Disponível: " + disponivel);
    }
    
    public int getcodigoExemplar(){
        return codigoExemplar; 
    }
    public boolean getDisponivel(){
        return disponivel;
    }
    public void setDisponivel(boolean disponivel){
        this.disponivel = disponivel;
    }
}

class Livro{
    private int codigoLivro;
    private String titulo;
    private String autor;
    private int anoDePublicacao;

    private ArrayList <Exemplar> exemplares;

    public Livro(String titulo, String autor, int anoDePublicacao, int codigoLivro){
        this.codigoLivro = codigoLivro;
        this.titulo = titulo;
        this.autor = autor;
        this.anoDePublicacao = anoDePublicacao;
        this.exemplares = new ArrayList<>();

    }
    public int getCodigoLivro(){
        return codigoLivro;
    }
    public String getTitulo(){
        return titulo;
    }
    public String getAutor(){
        return autor;
    }
    public int getAnoDePublicacao(){
        return anoDePublicacao;
    }
    public void adicionarExemplar(Exemplar exemplar){
        exemplares.add(exemplar);
    }

    public ArrayList<Exemplar> getExemplares() {
        return exemplares;
    }

    public void mostrarLivro(){
        System.out.println("Código do Livro: " + codigoLivro);
        System.out.println("Título: " + titulo);
        System.out.println("Autor: " + autor);
        System.out.println("Ano de Publicação: " + anoDePublicacao);
        System.out.println(">>>>>");
        System.out.println("Exemplares:");
        for (Exemplar exemplar : exemplares) {
            System.out.println("- Código do Exemplar: " + exemplar.getcodigoExemplar() + ", Disponível: " + exemplar.getDisponivel());
        }
    }
}
