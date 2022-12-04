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
public class Produto {
    private int codProd;
    private float preco;
    private String descricao;
    private int qtd;
    private float custoPart;
    
    public Produto(int codProd, float preco, String descricao, int qtd, float custoPart) {
        this.codProd = codProd;
        this.preco = preco;
        this.descricao = descricao;
        this.qtd = qtd;
        this.custoPart = custoPart;
    }
    
    public Produto() {
    
    }

    public int getCodProd() {
        return codProd;
    }

    public void setCodProd(int codProd) {
        this.codProd = codProd;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }
    
    public float getCustoPart() {
        return custoPart;
    }

    public void setCustoPart(float custoPart) {
        this.custoPart = custoPart;
    }
    
    
}
