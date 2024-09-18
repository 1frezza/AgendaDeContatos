import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Agenda agenda = new Agenda();
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        do {
            System.out.println("1 - Importar Contatos");
            System.out.println("2 - Exportar Contatos");
            System.out.println("3 - Inserir Contato");
            System.out.println("4 - Remover Contato por Telefone");
            System.out.println("5 - Localizar Contato por Telefone");
            System.out.println("6 - Listar todos os Contatos");
            System.out.println("7 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    System.out.print("Informe o caminho do arquivo: ");
                    String caminhoImportar = scanner.nextLine();
                    try {
                        agenda.importarContatos(caminhoImportar);
                        System.out.println("Contatos importados com sucesso!");
                    } catch (Exception e) {
                        System.out.println("Erro ao importar contatos: " + e.getMessage());
                    }
                    break;
                case 2:
                    System.out.print("Informe o caminho para exportar: ");
                    String caminhoExportar = scanner.nextLine();
                    try {
                        agenda.exportarContatos(caminhoExportar);
                        System.out.println("Contatos exportados com sucesso!");
                    } catch (Exception e) {
                        System.out.println("Erro ao exportar contatos: " + e.getMessage());
                    }
                    break;
                case 3:
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Telefone: ");
                    String telefone = scanner.nextLine();
                    System.out.print("Endereço: ");
                    String endereco = scanner.nextLine();
                    agenda.inserirContato(nome, telefone, endereco);
                    System.out.println("Contato inserido com sucesso!");
                    break;
                case 4:
                    System.out.print("Informe o telefone do contato a ser removido: ");
                    String telefoneRemover = scanner.nextLine();
                    agenda.removerContatoPorTelefone(telefoneRemover);
                    System.out.println("Contato removido!");
                    break;
                case 5:
                    System.out.print("Informe o telefone do contato: ");
                    String telefoneBuscar = scanner.nextLine();
                    Contato contato = agenda.localizarContatoPorTelefone(telefoneBuscar);
                    if (contato != null) {
                        System.out.println("Contato encontrado: " + contato);
                    } else {
                        System.out.println("Contato não encontrado.");
                    }
                    break;
                case 6:
                    agenda.listarContatos();
                    break;
                case 7:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }

        } while (opcao != 7);

        scanner.close();
    }
}
