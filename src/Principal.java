import java.util.Scanner;
import javax.swing.JOptionPane;
// fazer as funções de importar arquivos (ele não está achando o arquivo, portanto não conseguimos testar a função ainda) - a fazer
// fazer a função de chamada - check

public class Principal {
    public static void main(String[] args) {
        Agenda agenda = new Agenda();
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        do {
            opcao = Integer.parseInt(JOptionPane.showInputDialog("1 - Importar Contatos\n2 - Exportar Contatos\n3 - Inserir Contato\n4 - Remover Contato por Telefone\n5 - Remover Contato por Nome\n6 - Localizar Contato por Telefone\n7 - Localizar Contato por Nome\n8- Listar todos os Contatos\n9- Limpar agenda\n10- Chamar contato\n11 - Sair\n\n\n\nEscolha uma opção: "));

            switch (opcao) {
                case 1:
                String caminhoImportar = JOptionPane.showInputDialog("Informe o caminho do arquivo:\n\nEx: C:/Users/nomeUsuario/Documents/contatos.txt\n\nps. por favor siga o exemplo pois se as barrinhas estiverem ao contrário, ele não funciona");
                    if (caminhoImportar != null) {
                        agenda.importarContatos(caminhoImportar); 
                        JOptionPane.showMessageDialog(null, "Contatos importados com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Nenhum caminho foi informado!");
                    }
                    break;
                case 2:
                    String caminhoExportar = JOptionPane.showInputDialog("Informe o nome do arquivo: ");
                    try {
                        agenda.exportarContatos(caminhoExportar);
                        JOptionPane.showMessageDialog(null, "Contatos exportados com sucesso!");
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Erro ao exportar contatos: " + e.getMessage());
                    }
                    break;
                case 3:
                    String nome = JOptionPane.showInputDialog("Nome: ");
                    String telefone = JOptionPane.showInputDialog("Telefone: ");
                    String endereco = JOptionPane.showInputDialog("Endereço: ");
                    agenda.inserirContato(nome, telefone, endereco);
                    break;
                case 4:
                    String telefoneRemover = JOptionPane.showInputDialog("Informe o telefone do contato a ser removido: ");
                    agenda.removerContatoPorTelefone(telefoneRemover);
                    JOptionPane.showMessageDialog(null, "Contato removido!");
                    break;
                case 5:
                    String nomeRemover = JOptionPane.showInputDialog("Informe o nome do contato a ser removido: ");
                    agenda.removerContatoPorNome(nomeRemover);
                    JOptionPane.showMessageDialog(null, "Contato removido!");
                    break;
                case 6:
                    String telefoneBuscar = JOptionPane.showInputDialog("Informe o telefone do contato: ");
                    String contatoTelefone = agenda.localizarContatoPorTelefone(telefoneBuscar);
                    if (contatoTelefone != null) {
                        JOptionPane.showMessageDialog(null, "Contato encontrado: " + contatoTelefone);
                    } else {
                        JOptionPane.showMessageDialog(null, "Contato não encontrado.");
                    }
                    break;
                case 7:
                    String nomeBuscar = JOptionPane.showInputDialog("Informe o nome do contato: ");
                    String contatoNome = agenda.localizarContatoPorNome(nomeBuscar);
                    if (contatoNome != null) {
                        JOptionPane.showMessageDialog(null, "Contato encontrado: " + contatoNome);
                    } else {
                        JOptionPane.showMessageDialog(null, "Contato não encontrado.");
                    }
                    break;
                case 8:
                    agenda.listarContatos();
                    break;
                case 9:
                    agenda.excluirTodosContatos();
                    break;
                case 10:
                nomeBuscar = JOptionPane.showInputDialog("Informe o nome do contato: ");
                contatoTelefone = agenda.localizarContatoPorNome(nomeBuscar);
                agenda.realizarChamada(nomeBuscar);
                case 11:
                    JOptionPane.showMessageDialog(null, "Saindo...");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida.");
                    break;
            }

        } while (opcao != 11);

        scanner.close();
    }
}
