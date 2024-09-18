import java.util.HashMap;
import java.util.Map;
import java.io.*;

public class Agenda {
    private HashMap<String, Contato> contatos;

    public Agenda() {
        this.contatos = new HashMap<>();
    }

    // importa arquivo TXT
    public void importarContatos(String caminhoArquivo) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo));
        String linha;
        while ((linha = reader.readLine()) != null) {
            String[] dados = linha.split("@");
            if (dados.length == 3) {
                Contato contato = new Contato(dados[0], dados[1], dados[2]);
                contatos.put(dados[1], contato);
            }
        }
        reader.close();
    }

    // exporta contatos pro arquivo TXT
    public void exportarContatos(String caminhoArquivo) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo));
        for (Map.Entry<String, Contato> entry : contatos.entrySet()) {
            Contato contato = entry.getValue();
            writer.write(contato.getNome() + "@" + contato.getTelefone() + "@" + contato.getEndereco());
            writer.newLine();
        }
        writer.close();
    }

    // insere contato
    public void inserirContato(String nome, String telefone, String endereco) {
        Contato contato = new Contato(nome, telefone, endereco);
        contatos.put(telefone, contato);
    }

    // remove contato pelo telefone
    public void removerContatoPorTelefone(String telefone) {
        contatos.remove(telefone);
    }

    // localiza contato pelo telefone
    public Contato localizarContatoPorTelefone(String telefone) {
        return contatos.get(telefone);
    }

    // lista todos os contatos
    public void listarContatos() {
        for (Contato contato : contatos.values()) {
            System.out.println(contato);
        }
    }

   
}
