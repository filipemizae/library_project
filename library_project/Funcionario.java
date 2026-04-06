public class Funcionario extends Pessoa {

    private int id;
    private String email;

    public Funcionario(String nome, String dataNascimento, String telefone, int id) {
        super(nome, dataNascimento, telefone);
        this.id = id;
        this.email = id + "@biblioteca.com";
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return super.toString() + " ID funcionario: " + id + " Email: " + email;
    }

}