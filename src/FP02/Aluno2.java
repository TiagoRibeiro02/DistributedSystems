package FP02;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import Ler.Ler;


public class Aluno2 implements Serializable {


    private Integer numero;
    private String nome;
    private String curso;
    private String contacto;

    Aluno2(Integer numero, String nome, String curso, String contacto) throws IOException, ClassNotFoundException {
        Socket sc = null;

        this.numero = numero;
        this.nome = nome;
        this.curso = curso;
        this.contacto = contacto;

        int escolha = -1;

        sc = new Socket("127.0.0.1",2222);
        ObjectOutputStream os = new ObjectOutputStream(sc.getOutputStream());
        ObjectInputStream is = new ObjectInputStream(sc.getInputStream());
        while(escolha != 0){
            System.out.println("O que pretende fazer?\n0:Sair\n1:Registar-se\n2:Consultar quais os alunos registados\n3:Consultar o número de acessos\n4:Pesquisar o contacto e número de um aluno");
            escolha = Ler.umInt();
            os.writeObject(escolha);
            os.flush();
            switch (escolha){
                case 0 : break;
                case 1 :

                    os.writeObject( this);
                    os.flush();
                    System.out.println((int) is.readObject());
                    //registar
                    break;
                case 2: //completar
                    //não precisamos de nada
                    break;
                case 3: //completar

                    break;
                    case 4://completar
                        break;
            }

        }
        os.close();
        is.close();
    }
    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aluno2 aluno = (Aluno2) o;
        return getNumero().equals(aluno.getNumero()) && getNome().equals(aluno.getNome()) && getCurso().equals(aluno.getCurso()) && getContacto().equals(aluno.getContacto());
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "numero=" + numero +
                ", nome='" + nome + '\'' +
                ", curso='" + curso + '\'' +
                ", contacto='" + contacto + '\'' +
                '}';
    }

    public static void main(String args[]) throws IOException, ClassNotFoundException {
        System.out.println("Insira os dados do aluno: numero, nome, curso e contacto");
        Aluno2 c = new Aluno2(Ler.umInt(),Ler.umaString(),Ler.umaString(),Ler.umaString());
    }

}

