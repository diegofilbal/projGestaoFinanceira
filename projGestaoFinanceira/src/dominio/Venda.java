/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

/**
 *
 * @author diego
 */
public class Venda {
    private int codVenda;
    private String CPF;
    private String data;
    private String hora;

    public Venda(int codVenda, String CPF, String data, String hora) {
        this.codVenda = codVenda;
        this.CPF = CPF;
        this.data = data;
        this.hora = hora;
    }
    
    public Venda() {
      
    }

    public int getCodVenda() {
        return codVenda;
    }

    public void setCodVenda(int codVenda) {
        this.codVenda = codVenda;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
    
}
