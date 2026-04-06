import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static ArrayList<Usuario> usuarios = new ArrayList<>();
    public static ArrayList<Funcionario> funcionarios = new ArrayList<>();
    public static ArrayList<Livro> livros = new ArrayList<>();
    public static ArrayList<Emprestimo> emprestimos = new ArrayList<>();
    // public static ArrayList<Exemplar> exemplares = new ArrayList<>();
    public static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        inicio();

        int opcao = -1;

        do {

            System.out.println("-----MENU-----");
            System.out.println("1 - Cadastrar usuário");
            System.out.println("2 - Cadastrar funcionário");
            System.out.println("3 - Cadastrar Livro");
            System.out.println("4 - Cadastrar Exemplar");
            System.out.println("5 - Buscar Livro");
            System.out.println("6 - Buscar Exemplar");
            System.out.println("7 - Listar todos os usuários");
            System.out.println("8 - Listar todos os livros");
            System.out.println("9 - Listar todos os exemplares disponíveis de um livro");
            System.out.println("10 - Registrar um empréstimo de livro");
            System.out.println("11 - Registrar uma devolução de livro");
            System.out.println("12 - Prolongar um empréstimo");
            System.out.println("-----DIGITE 0 PARA SAIR-----");

            try {
                opcao = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Digite apenas os valores númericos sugeridos! [1] [2] .. [12]");
                input.nextInt();
            }

            switch (opcao) {
                case 1:

                    cadastrarUsuario();

                    break;

                case 2:

                    cadastrarFuncionario();

                    break;

                case 3:

                    cadastrarLivro();

                    break;

                case 4:

                    cadastrarExemplar();

                    break;

                case 5:

                    buscarLivro();

                    break;

                case 6:

                    buscarExemplar();

                    break;

                case 7:

                    listarUsuarios();

                    break;

                case 8:

                    listarLivros();

                    break;

                case 9:

                    listarExemplaresLivro();

                    break;

                case 10:

                    registrarEmprestimo();

                    break;

                case 11:

                    registrarDevolucaoLivro();

                    break;

                case 12:

                    prolongarEmprestimo();

                    break;

                default:
                    break;
            }
        } while (opcao != 0);

        input.close();

    }

    // Seção de buscas

    // Buscar livro

    public static void buscarLivro() {
        System.out.println("\nBuscar livro (pelo código): ");

        System.out.print("Digite o código do livro que deseja buscar: ");
        int cdg_livro = input.nextInt();

        boolean encontrado = false;

        for (Livro l : livros) {
            if (l.getCodigoLivro() == cdg_livro) {
                encontrado = true;
                System.out.println("\n----Informações do livro----\n");
                l.mostrarLivro();
            }
        }

        System.out.println("\n");

        if (!encontrado) {
            System.out.println("Livro respectivo não encontrado!");
        }

        input.nextLine();

    }

    // Buscar exemplar

    public static void buscarExemplar() {

        System.out.println("\nBuscar exemplar (pelo código): ");

        System.out.print("Digite o código do exemplar que deseja buscar: ");
        int cdg_exemplar = input.nextInt();

        boolean encontrado = false;

        for (Livro l : livros) {
            for (Exemplar e : l.getExemplares()) {
                if (e.getcodigoExemplar() == cdg_exemplar) {
                    encontrado = true;
                    System.out.println("\n----Informações do exemplar----\n");
                    e.mostrarExemplar();
                    System.out.println("\n");
                }
            }
        }

        if (encontrado == false) {
            System.out.println("Exemplar não encontrado!");
        }

        input.nextLine();

    }

    // Listar usuários

    public static void listarUsuarios() {

        for (Usuario u : usuarios) {
            System.out.println("\n----Informações do usuário----\n");
            System.out.println(u.toString());
        }

    }

    // Listar usuários

    public static void listarLivros() {

        for (Livro l : livros) {
            System.out.println("\n----Informações do livro----");
            l.mostrarLivro();
        }

    }

    // Listar exemplares de um livro

    public static void listarExemplaresLivro() {

        System.out.println("Deseja listar os exemplares de qual livro? (Digite o código): ");
        int cdg_livro = input.nextInt();

        boolean encontrado = false;

        for (Livro l : livros) {
            if (l.getCodigoLivro() == cdg_livro) {
                encontrado = true;

                System.out.println("\n----Livro----");
                l.mostrarLivro();

            }
        }

        System.out.println("\n");

        if (encontrado == false) {
            System.out.println("Exemplar não encontrado!");
        }

    }

    // Registrar emprestimo de Livro

    public static void registrarEmprestimo() {

        // Selecionar exemplar do empréstimo
        Exemplar exemplar = null;

        System.out.println("\nRegistrar novo empréstimo: ");
        System.out.print("\n");
        System.out.print("---Deseja registrar um empréstimo de qual exemplar:---");
        System.out.print("\n");
        System.out.print("\n");
        do {
            for (int i = 0; i < livros.size(); i++) {
                System.out.println(i + " - " + livros.get(i).getTitulo());
                if (i == (livros.size() - 1)) {
                    System.out.print("\n");
                }
            }

            System.out.print("Livro: ");
            int escolhaL = input.nextInt();
            Livro emp_livro = livros.get(escolhaL);

            boolean lDisponivelencontrado = false;

            for (Exemplar e : emp_livro.getExemplares()) {
                if (e.getDisponivel()) {
                    exemplar = e;
                    lDisponivelencontrado = true;

                    // Selecionar usuário do empréstimo

                    System.out.print("---O empréstimo será para qual usuário:---");
                    System.out.print("\n");
                    System.out.print("\n");
                    for (int i = 0; i < usuarios.size(); i++) {
                        System.out.println(i + " - " + usuarios.get(i).getNome());
                        if (i == (usuarios.size() - 1)) {
                            System.out.print("\n");
                        }
                    }

                    Usuario emp_user = null;
                    while (emp_user == null) {
                        try {
                            System.out.print("Usuário: ");
                            int escolhaU = input.nextInt();
                            emp_user = usuarios.get(escolhaU);
                        } catch (IndexOutOfBoundsException ex) {
                            System.out.println("Opção inválida! Escolha um número (usuário) da lista.");
                        } catch (InputMismatchException ex) {
                            System.out.println("Digite apenas valores numéricos! [1],[2], etc...");
                            input.nextLine();
                        }
                    }

                    // Selecionar data do empréstimo

                    input.nextLine();

                    System.out.print("Digite a data do empréstimo: ");
                    String data_emp_string = input.nextLine();
                    DateTimeFormatter formatador_data = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    try {
                        LocalDate dataEmprestimo = LocalDate.parse(data_emp_string, formatador_data);
                        Emprestimo emprestimo = new Emprestimo(exemplar, emp_user, dataEmprestimo);
                        emprestimos.add(emprestimo);
                    } catch (DateTimeParseException ex) {
                        System.out.println("Formato inserido inválido! O correto é dd/MM/yyyy");
                        return;
                    }

                }

            }

            if (!lDisponivelencontrado) {
                System.out.println("Este livro não possui exemplares disponíveis!");
                break;
            }

        } while (exemplar == null);

    }

    // Registrar devolução de livro

    public static void registrarDevolucaoLivro() {

        System.out.print("---Deseja realizar e devolução de qual empréstimo?:---");
        System.out.print("\n");
        System.out.print("\n");
        for (int i = 0; i < emprestimos.size(); i++) {

            Exemplar exemplarEmprestimo = emprestimos.get(i).getExemplar();
            String tituloLivroEmp = null;

            for (Livro l : livros) {
                for (Exemplar e : l.getExemplares()) {
                    if (e == exemplarEmprestimo) {
                        tituloLivroEmp = l.getTitulo();
                        break;
                    }
                }
            }

            Usuario usuario_emprestimo = emprestimos.get(i).getUsuario();
            LocalDate data_do_emprestimo = emprestimos.get(i).getDataEmprestimo();
            LocalDate data_p_devolucao = emprestimos.get(i).getDataPrevistaDevolucao();

            System.out.println(i + " - Livro: " + tituloLivroEmp + " / " + usuario_emprestimo
                    + " / Data do empréstimo: " + data_do_emprestimo + " / Data da devolução: " + data_p_devolucao);

            if (i == (emprestimos.size() - 1)) {
                System.out.print("\n");
            }
        }
        System.out.print("Emprestimo: ");
        int escolhaE = input.nextInt();
        Emprestimo emp_escolhido = emprestimos.get(escolhaE);

        emp_escolhido.devolver();
        System.out.println("Devolução realizada!");

    }

    // Prolongar empréstimo

    public static void prolongarEmprestimo() {

        System.out.print("---Deseja prolongar a data de devolução de qual empréstimo?:---");
        System.out.print("\n");
        System.out.print("\n");
        for (int i = 0; i < emprestimos.size(); i++) {

            Exemplar exemplarEmprestimo = emprestimos.get(i).getExemplar();
            String tituloLivroEmp = null;

            for (Livro l : livros) {
                for (Exemplar e : l.getExemplares()) {
                    if (e == exemplarEmprestimo) {
                        tituloLivroEmp = l.getTitulo();
                        break;
                    }
                }
            }

            Usuario usuario_emprestimo = emprestimos.get(i).getUsuario();
            LocalDate data_do_emprestimo = emprestimos.get(i).getDataEmprestimo();
            LocalDate data_p_devolucao = emprestimos.get(i).getDataPrevistaDevolucao();

            System.out.println(i + " - Livro: " + tituloLivroEmp + " / " + usuario_emprestimo
                    + " / Data do empréstimo: " + data_do_emprestimo + " / Data da devolução: " + data_p_devolucao);

            if (i == (emprestimos.size() - 1)) {
                System.out.print("\n");
            }
        }
        System.out.print("Emprestimo: ");
        int escolhaE = input.nextInt();
        Emprestimo emp_escolhido = emprestimos.get(escolhaE);

        System.out.println("Deseja prolongar o empréstimo em 1 semana ou escolher em quantos dias?: ");
        System.out.println("1 - Prolongar em 1 semana");
        System.out.println("2 - Quero escolher a quantidade de dias para prolongar");
        int escolha_prlng = input.nextInt();

        if (escolha_prlng == 1) {
            emp_escolhido.prolongarDataDevEmprestimo();
            System.out.println("Data de devolução do empréstimo " + escolhaE + " alterada para (+1 semana): "
                    + emp_escolhido.getDataPrevistaDevolucao());
        } else if (escolha_prlng == 2) {
            System.out.println("Em quantos dias?: ");
            int diasAProlongar = input.nextInt();

            emp_escolhido.prolongarDataDevEmprestimo(diasAProlongar);
            System.out.println("Data de devolução do empréstimo " + escolhaE + " alterada para: "
                    + emp_escolhido.getDataPrevistaDevolucao());

        } else {
            System.out.println("Digite uma opção válida!");
        }

    }

    // Seção de cadastros

    // Usuario

    public static void cadastrarUsuario() {

        System.out.println("\nCadastro de Usuário: ");

        System.out.print("Nome completo: ");
        String nome = input.nextLine();
        System.out.print("Data de nascimento: ");
        String data_nasc = input.nextLine();
        System.out.print("Telefone: ");
        String telefone = input.nextLine();
        try {
            System.out.print("Registro de usuário (ID único): ");
            int id = input.nextInt();
            for (Usuario u : usuarios) {
                if (u.getId() == id) {
                    System.out.println("Erro: já existe um usuário com o ID " + id + "!");
                    return;
                }

            }

            Usuario usuario = new Usuario(nome, data_nasc, telefone, id);
            usuarios.add(usuario);
            System.out.println("Usuário cadastrado com sucesso!");
        } catch (InputMismatchException e) {
            System.out.println("O ID do usuário deve ser um número inteiro! [1],[2], etc...");
        }
    }

    // Funcionario
    public static void cadastrarFuncionario() {
        System.out.println("\nCadastro de Funcionário: ");

        System.out.print("Nome completo: ");
        String nome = input.nextLine();
        System.out.print("Data de nascimento: ");
        String data_nasc = input.nextLine();
        System.out.print("Telefone: ");
        String telefone = input.nextLine();
        try {
            System.out.print("Registro de Funcionário (ID único): ");
            int id = input.nextInt();
            for (Funcionario f : funcionarios) {
                if (f.getId() == id) {
                    System.out.println("Erro: já existe um funcionário com o ID " + id + "!");
                    return;
                }

            }

            Funcionario funcionario = new Funcionario(nome, data_nasc, telefone, id);
            funcionarios.add(funcionario);
            System.out.println("Funcionário cadastrado com sucesso!");
        } catch (InputMismatchException e) {
            System.out.println("O ID do usuário deve ser um número inteiro! [1],[2], etc...");
        }

    }

    // Livro
    public static void cadastrarLivro() {

        System.out.println("\nCadastro de Livro:\n");

        System.out.print("Título: ");
        String titulo = input.nextLine();
        System.out.print("Autor: ");
        String autor = input.nextLine();
        System.out.print("Ano de publicação: ");
        int ano_publicacao = input.nextInt();
        try {
            System.out.print("Código do livro: ");
            int cdg_livro = input.nextInt();
            for (Livro l : livros) {
                if (l.getCodigoLivro() == cdg_livro) {
                    System.out.println("Erro: já existe um livro com o código " + cdg_livro + "!");
                    return;
                }
            }

            Livro livro = new Livro(titulo, autor, ano_publicacao, cdg_livro);
            livros.add(livro);
            System.out.println("\nLivro cadastrado com sucesso!\n");
        } catch (InputMismatchException e) {
            System.out.println("O ID do usuário deve ser um número inteiro! [1],[2], etc...");
        }
    }

    // Exemplar
    public static void cadastrarExemplar() {

        System.out.println("\n");

        for (Livro l : livros) {
            System.out.println(l.getCodigoLivro() + " - " + l.getTitulo());
        }

        System.out.println("\nCadastro de Exemplar:\n");

        System.out.print("Código do livro: ");
        int cdg_livro = input.nextInt();

        boolean encontrado = false;

        for (Livro l : livros) {
            if (l.getCodigoLivro() == cdg_livro) {
                encontrado = true;
                try {
                    System.out.println("Livro encontrado! Digite o código do exemplar: ");
                    int cdg_exemplar = input.nextInt();
                    for (Exemplar e : l.getExemplares()) {
                        if (e.getcodigoExemplar() == cdg_exemplar) {
                            System.out.println("Erro: já existe um exemplar com o código " + cdg_exemplar + "!");
                            return;
                        }
                    }

                    Exemplar exemplar = new Exemplar(cdg_exemplar, true);
                    l.adicionarExemplar(exemplar);
                    System.out.println("\nExemplar do livro cadastrado com sucesso!\n");
                } catch (InputMismatchException e) {
                    System.out.println("O ID do usuário deve ser um número inteiro! [1],[2], etc...");
                }
            }
        }

        if (!encontrado) {
            System.out.println("Livro respectivo não encontrado!");
        }

        input.nextLine();

    }

    // Dados iniciais

    public static void inicio() {

        Usuario usuario1 = new Usuario("Alex", "1995", "11912345678", 1);
        Usuario usuario2 = new Usuario("Arthur", "2001", "11912345678", 2);
        Usuario usuario3 = new Usuario("Camila", "1997", "11912345678", 3);
        Funcionario funcionario = new Funcionario("Fernanda", "1998", "11912345678", 2);
        Livro livro1 = new Livro("Dom Casmurro", "Machado de Assis", 1899, 1);
        Livro livro2 = new Livro("O Pequeno Príncipe", "Antoine de Saint-Exupéry", 1943, 2);
        Livro livro3 = new Livro("Dom Quixote", "Miguel de Cervantes", 1605, 3);
        Exemplar exemplar1 = new Exemplar(1, true);
        Exemplar exemplar2 = new Exemplar(2, true);
        Exemplar exemplar3 = new Exemplar(3, true);
        Exemplar exemplar4 = new Exemplar(4, true);

        livro1.adicionarExemplar(exemplar1);
        livro1.adicionarExemplar(exemplar2);
        livro2.adicionarExemplar(exemplar3);
        livro2.adicionarExemplar(exemplar4);

        Emprestimo emprestimo1 = new Emprestimo(exemplar1, usuario2, LocalDate.now());
        Emprestimo emprestimo2 = new Emprestimo(exemplar2, usuario1, LocalDate.now());
        Emprestimo emprestimo3 = new Emprestimo(exemplar3, usuario3, LocalDate.now());

        usuarios.add(usuario1);
        usuarios.add(usuario2);
        usuarios.add(usuario3);
        funcionarios.add(funcionario);

        livros.add(livro1);
        livros.add(livro2);
        livros.add(livro3);

        emprestimos.add(emprestimo1);
        emprestimos.add(emprestimo2);
        emprestimos.add(emprestimo3);

    }

}