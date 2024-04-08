package FP05;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Aluno implements Serializable {
	private int numero;
    private String nome;
    private String curso;
    private String contacto;

    public Aluno(int numero, String nome, String curso, String contacto) {
        this.numero = numero;
        this.nome = nome;
        this.curso = curso;
        this.contacto = contacto;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }
    
    public int registarAluno(Aluno aluno) {
    	ArrayList<Aluno> alunosRegistados = carregarDados();
		if (alunosRegistados.contains(aluno)) {
			return -1; // aluno já está registado
		}

		alunosRegistados.add(aluno);
		escreverDados();

		return alunosRegistados.size();
	}

	public ArrayList<Aluno> pesquisaAluno(String nomealuno) {
		ArrayList<Aluno> alunosRegistados = carregarDados();
		ArrayList<Aluno> alunosEncontrados = new ArrayList<Aluno>();
		for (int i = 0; i < alunosRegistados.size(); i++) {
			if (alunosRegistados.get(i).getNome().equals(nomealuno)) {
				alunosEncontrados.add(alunosRegistados.get(i));
			}
		}
		return alunosEncontrados;
	}

	public void escreverDados() {
		ArrayList<Aluno> alunosRegistados = carregarDados();
		try {
			FileWriter writer = new FileWriter(
					"C:/Users/tigol/Documents/GitHub/DistributedSystems/src/FP02/alunosregistados.txt");
			for (Aluno aluno : alunosRegistados) {
				writer.write(aluno.getNumero() + "," + aluno.getNome() + "," + aluno.getCurso() + ","
						+ aluno.getContacto() + "\n");
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Aluno> carregarDados() {
		ArrayList<Aluno> alunosRegistados = new ArrayList<>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(
					"C:/Users/tigol/Documents/GitHub/DistributedSystems/src/FP02/alunosregistados.txt"));
			String linha;
			while ((linha = br.readLine()) != null) {
				String[] campos = linha.split(",");
				int numero = Integer.parseInt(campos[0]);
				String nome = campos[1];
				String curso = campos[2];
				String contacto = campos[3];
				Aluno aluno = new Aluno(numero, nome, curso, contacto);
				alunosRegistados.add(aluno);
			}
			br.close();
		} catch (IOException e) {
			System.out.println("Erro ao ler arquivo de alunos: " + e.getMessage());
		}
		return alunosRegistados;
	}

    @Override
    public String toString() {
        return "Aluno [numero=" + numero + ", nome=" + nome + ", curso=" + curso + ", contacto=" + contacto + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Aluno other = (Aluno) obj;
        if (numero != other.numero)
            return false;
        if (contacto == null) {
            if (other.contacto != null)
                return false;
        } else if (!contacto.equals(other.contacto))
            return false;
        if (curso == null) {
            if (other.curso != null)
                return false;
        } else if (!curso.equals(other.curso))
            return false;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        return true;
    }
}