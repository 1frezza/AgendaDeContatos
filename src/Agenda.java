import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JOptionPane;

import java.io.*;

public class Agenda {
    private HashMap<String, Contato> contatos;

    public Agenda() {
        this.contatos = new HashMap<>();
    }

    // importa arquivo TXT
    public void importarContatos(String caminhoArquivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            int contatosImportados = 0;
            int duplicatas = 0;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split("@");
                if (partes.length == 3) {
                    String nome = partes[0].trim();
                    String telefone = partes[1].trim();
                    String endereco = partes[2].trim();

                    if (contatos.containsKey(telefone)) {
                        JOptionPane.showMessageDialog(null, "Telefone duplicado: " + telefone);
                        duplicatas++;
                        continue; 
                    }
                    boolean nomeDuplicado = false;
                    for (Contato contato : contatos.values()) {
                        if (contato.getNome().equalsIgnoreCase(nome)) {
                            JOptionPane.showMessageDialog(null, "Nome duplicado: " + nome);
                            duplicatas++;
                            nomeDuplicado = true;
                            break;
                        }
                    }
    
                    if (!nomeDuplicado) {
                        contatos.put(telefone, new Contato(nome, telefone, endereco));
                        contatosImportados++;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Formato inválido na linha: " + linha);
                }
            }
            JOptionPane.showMessageDialog(null, "Importação concluída. \n\nContatos importados: " + contatosImportados);
            JOptionPane.showMessageDialog(null, "Contatos duplicados não importados: " + duplicatas);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Arquivo não encontrado: " + caminhoArquivo);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao importar contatos: " + e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro inesperado: " + e.getMessage());
        }
    }
    

    // exporta contatos pro arquivo TXT
    public void exportarContatos(String nomeArquivo) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo + ".txt"));
        for (Map.Entry<String, Contato> entry : contatos.entrySet()) {
            Contato contato = entry.getValue();
            writer.write(contato.getNome() + "@" + contato.getTelefone() + "@" + contato.getEndereco());
            writer.newLine();
        }
        writer.close();
    }

    public void inserirContato(String nome, String telefone, String endereco) {
        if (contatos.containsKey(telefone)) {
            JOptionPane.showMessageDialog(null, "Erro: Telefone já cadastrado.");
            return;
        }
        for (Contato contato : contatos.values()) {
            if (contato.getNome().equalsIgnoreCase(nome)) {
                JOptionPane.showMessageDialog(null, "Erro: Nome já cadastrado.");
                return;
            }
        }
        Contato contato = new Contato(nome, telefone, endereco);
        contatos.put(telefone, contato);
        JOptionPane.showMessageDialog(null, "Contato cadastrado com sucesso!");
    }
    


    public boolean removerContatoPorTelefone(String telefone) {
        Iterator<Map.Entry<String, Contato>> iterator = contatos.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Contato> entry = iterator.next();
            Contato contato = entry.getValue();
            if (contato.getTelefone().equals(telefone)) {
                iterator.remove(); 
                return true;
            }
        }
        return false; 
    }

    public boolean removerContatoPorNome(String nome) {
        Iterator<Map.Entry<String, Contato>> iterator = contatos.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Contato> entry = iterator.next();
            Contato contato = entry.getValue();
            if (contato.getNome().equals(nome)) {
                iterator.remove();
                return true; 
            }
        }
        return false; 
    }

    public String localizarContatoPorTelefone(String telefone) {
        Iterator<Map.Entry<String, Contato>> iterator = contatos.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Contato> entry = iterator.next();
            Contato contato = entry.getValue();
            if (contato.getTelefone().equals(telefone)) {
                return "nome: " + contato.getNome() + ", telefone: " + contato.getTelefone() + ", endereço: " + contato.getEndereco(); 
            }
        }
        return null;
    }

    public String localizarContatoPorNome(String nome) {
        Iterator<Map.Entry<String, Contato>> iterator = contatos.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Contato> entry = iterator.next();
            Contato contato = entry.getValue();
            if (contato.getNome().equals(nome)) {
                return "nome: " + contato.getNome() + ", telefone: " + contato.getTelefone() + ", endereço: " + contato.getEndereco(); 
            }
        }
        return null;
    }

    // lista todos os contatos
    public void listarContatos() {
        if (contatos.size() > 0) {
            StringBuilder sb = new StringBuilder();
            for (Contato contato : contatos.values()) {
                sb.append(contato.toString()).append("\n"); 
            }
            JOptionPane.showMessageDialog(null, sb.toString()); 
        } else {
            JOptionPane.showMessageDialog(null, "Não há contatos cadastrados.");
        }
    }
    
    
    

    public void excluirTodosContatos() {
        if (contatos.size() > 0) { 
            contatos.clear(); 
            JOptionPane.showMessageDialog(null, "Todos os contatos foram excluídos com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Não há contatos para excluir."); 
        }
    }

    public void realizarChamada(String nome) {
        String contato = localizarContatoPorNome(nome);
        if (contato != null) {
            JOptionPane.showMessageDialog(null, "Realizando chamada para: " + contato);
        } else {
            JOptionPane.showMessageDialog(null, "Contato não encontrado.");
        }
    }
}
