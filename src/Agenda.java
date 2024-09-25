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
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split("@");
                if (partes.length == 3) {
                    String nome = partes[0].trim();
                    String telefone = partes[1].trim();
                    String endereco = partes[2].trim();

                    contatos.put(telefone, new Contato(nome, telefone, endereco));
                    contatosImportados++;
                } else {
                    System.out.println("Formato inválido na linha: " + linha);
                }
            }
            System.out.println("Importação concluída. Contatos importados: " + contatosImportados);
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + caminhoArquivo);
        } catch (IOException e) {
            System.out.println("Erro ao importar contatos: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
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

    // insere contato
    public void inserirContato(String nome, String telefone, String endereco) {
        if (contatos.containsKey(telefone)) {
            JOptionPane.showMessageDialog(null, "Telefone já cadastrado.");
            return;
        }
    
        for (Contato contato : contatos.values()) {
            if (contato.getNome().equalsIgnoreCase(nome)) {
                JOptionPane.showMessageDialog(null, "Nome já cadastrado.");
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
            System.out.println("Todos os contatos foram excluídos com sucesso!");
        } else {
            System.out.println("Não há contatos para excluir."); 
        }
    }

    public void realizarChamada(String telefone) {
        Contato contato = buscarPorTelefone(telefone);
        if (contato != null) {
            System.out.println("Realizando chamada para: " + contato.getNome() + " - Telefone: " + contato.getTelefone());
        } else {
            System.out.println("Contato não encontrado.");
        }
    }

    private Contato buscarPorTelefone(String telefone) {
        return contatos.get(telefone); 
    }


   
}
