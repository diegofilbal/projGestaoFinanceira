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
public class ProdVenda {
    private int codProd;
    private int codVenda;
    private int qtd;

    public ProdVenda(int codProd, int codVenda, int qtd) {
        this.codProd = codProd;
        this.codVenda = codVenda;
        this.qtd = qtd;
    }
    public ProdVenda(){
        
    }

    public int getCodProd() {
        return codProd;
    }

    public void setCodProd(int codProd) {
        this.codProd = codProd;
    }

    public int getCodVenda() {
        return codVenda;
    }

    public void setCodVenda(int codVenda) {
        this.codVenda = codVenda;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }
    
    
    
}
