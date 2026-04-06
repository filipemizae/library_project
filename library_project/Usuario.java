import java.util.ArrayList;

public class Usuario extends Pessoa {

    private int id;
    private String email;
    private ArrayList<Exemplar> livrosEmprestados;

    public Usuario(String nome, String dataNascimento, String telefone, int id) {
        super(nome, dataNascimento, telefone);
        this.id = id;
        this.email = id + "@biblioteca.com";
        this.livrosEmprestados = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public ArrayList<Exemplar> getLivrosEmprestados() {
        return livrosEmprestados;
    }

    public void adicionarEmprestimo(Exemplar exemplar) {
        livrosEmprestados.add(exemplar);
    }

    public void removerEmprestimo(Exemplar exemplar) {
        livrosEmprestados.remove(exemplar);
    }

    @Override
    public String toString() {
        return super.toString() + " ID: " + id + " Email: " + email;
    }

}