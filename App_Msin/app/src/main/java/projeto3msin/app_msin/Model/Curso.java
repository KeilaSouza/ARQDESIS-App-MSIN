package projeto3msin.app_msin.Model;

/**
 * Created by ASUS on 30/10/2016.
 */

import java.io.Serializable;


public class Curso implements Comparable<Curso>, Serializable {

    private double valor;
    private String imagem,tipo,codigo,nome,datainicio,datafim,horario,vagas,sala;
    public static final String NAO_EXISTE = "Não Existe.";

    public Curso(String imagem,String tipo,String codigo,String nome,String datainicio,String datafim,String horario,String vagas,String sala, double valor) {
 
        this.imagem = imagem;
        this.tipo = tipo;
        this.codigo = codigo;
        this.nome = nome;
        this.datainicio = datainicio;
        this.datafim = datafim;
        this.horario = horario;
        this.vagas = vagas;
        this.sala = sala;
        this.valor = valor;
    }

    public String getImagem() {
        return imagem;
    }
    public String getTipo() {
        return tipo;
    }
    public String getCodigo() {
        return codigo;
    }
    public String getNome() {
        return nome;
    }
    public String getDataInicio() {
        return datainicio;
    }
    public String getDataFim() {
        return datafim;
    }

    public String getHorario() {
        return horario;
    }
    public String getVagas() {
        return vagas;
    }
    public String getSala() {
        return sala;
    }

    public double getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return "projeto3msin.app_msin.Cursop1.Curso{" +
                "imagem='" + imagem + '\'' +
                ", tipo='" + tipo + '\'' +
                ", codigo='" + codigo + '\'' +
                ", nome='" + nome + '\'' +
                ", datainicio='" + datainicio + '\'' +
                ", datafim='" + datafim + '\'' +
                ", horario='" + horario + '\'' +
                ", vagas='" + vagas + '\'' +
                ", sala='" + sala + '\'' +
                ", valor='" + valor + '\'' +
                '}';
    }

    @Override
    public int compareTo(Curso Curso) {
        if (nome.equals(Curso.getNome())
                && tipo.equals(Curso.getTipo())){
            return 0;
        }
        return this.getNome().compareTo(Curso.getNome());
    }
}
