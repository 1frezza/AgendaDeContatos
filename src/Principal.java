import java.util.Scanner;
import javax.swing.JOptionPane;
// Não deixar cadastrar nomes e telefones iguais (mostrar um recado caso o usuário utilize o mesmo nome ou o mesmo telefone no cadastro)
// se não tiver contatos cadastrados, mostrar uma mensagem ao usuário
// fazer as funções de importar arquivos (ele não está achando o arquivo, portanto não conseguimos testar a função ainda)
// fazer a função de chamada

public class Principal {
    public static void main(String[] args) {
        Agenda agenda = new Agenda();
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        do {
            opcao = Integer.parseInt(JOptionPane.showInputDialog("1 - Importar Contatos\n2 - Exportar Contatos\n3 - Inserir Contato\n4 - Remover Contato por Telefone\n5 - Remover Contato por Nome\n6 - Localizar Contato por Telefone\n7 - Localizar Contato por Nome\n8- Listar todos os Contatos\n9- Limpar agenda\n10 - Sair\n\n\n\nEscolha uma opção: "));

            switch (opcao) {
                case 1:
                    String caminhoImportar = JOptionPane.showInputDialog("Informe o caminho do arquivo: \nEx: C:\\\\Users\\\\SeuUsuario\\\\Documents\\\\contatos.csv");
                    try {
                        agenda.importarContatos(caminhoImportar);
                        System.out.println("Contatos importados com sucesso!");
                    } catch (Exception e) {
                        System.out.println("Erro ao importar contatos: " + e.getMessage());
                    }
                    break;
                case 2:
                    String caminhoExportar = JOptionPane.showInputDialog("Informe o nome do arquivo: ");
                    try {
                        agenda.exportarContatos(caminhoExportar);
                        System.out.println("Contatos exportados com sucesso!");
                    } catch (Exception e) {
                        System.out.println("Erro ao exportar contatos: " + e.getMessage());
                    }
                    break;
                case 3:
                    String nome = JOptionPane.showInputDialog("Nome: ");
                    String telefone = JOptionPane.showInputDialog("Telefone: ");
                    String endereco = JOptionPane.showInputDialog("Endereço: ");
                    agenda.inserirContato(nome, telefone, endereco);
                    System.out.println("Contato inserido com sucesso!");
                    break;
                case 4:
                    String telefoneRemover = JOptionPane.showInputDialog("Informe o telefone do contato a ser removido: ");
                    agenda.removerContatoPorTelefone(telefoneRemover);
                    System.out.println("Contato removido!");
                    break;
                case 5:
                    String nomeRemover = JOptionPane.showInputDialog("Informe o nome do contato a ser removido: ");
                    agenda.removerContatoPorNome(nomeRemover);
                    System.out.println("Contato removido!");
                    break;
                case 6:
                    String telefoneBuscar = JOptionPane.showInputDialog("Informe o telefone do contato: ");
                    String contatoTelefone = agenda.localizarContatoPorTelefone(telefoneBuscar);
                    if (contatoTelefone != null) {
                        System.out.println("Contato encontrado: " + contatoTelefone);
                    } else {
                        System.out.println("Contato não encontrado.");
                    }
                    break;
                case 7:
                    String nomeBuscar = JOptionPane.showInputDialog("Informe o nome do contato: ");
                    String contatoNome = agenda.localizarContatoPorNome(nomeBuscar);
                    if (contatoNome != null) {
                        System.out.println("Contato encontrado: " + contatoNome);
                    } else {
                        System.out.println("Contato não encontrado.");
                    }
                    break;
                case 8:
                    agenda.listarContatos();
                    break;
                case 9:
                    agenda.excluirTodosContatos();
                case 10:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }

        } while (opcao != 10);

        scanner.close();
    }
}
