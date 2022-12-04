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
public class HistoricoReposicao {
    private String data;
    private int codProd;
    private int qtd;

    public HistoricoReposicao(String data, int codProd, int qtd) {
        this.data = data;
        this.codProd = codProd;
        this.qtd = qtd;
    }
    
    public HistoricoReposicao(){
        
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getCodProd() {
        return codProd;
    }

    public void setCodProd(int codProd) {
        this.codProd = codProd;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }
       
}