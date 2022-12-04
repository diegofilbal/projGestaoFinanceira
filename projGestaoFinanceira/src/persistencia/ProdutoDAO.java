/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import conexao.Conexao;
import dominio.Produto;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author diego
 */
public class ProdutoDAO {
    
    private Conexao con = new Conexao("localhost", "5432", "Gestao Financeira", "postgres", "postgres");

    private final String BUSCAR = "SELECT * FROM \"Produto\" WHERE \"codProd\"= ?";
    private final String INSERIR = "INSERT INTO \"Produto\"(\"codProd\",\"preco\",\"descricao\",\"custoPart\",\"qtd\") VALUES (?,?,?,?,?)";
    private final String RELATORIO = "SELECT * FROM \"Produto\"";
    private final String ALTERARCODPROD = "UPDATE \"Produto\" SET \"codProd\"=? WHERE \"codProd\"=?";
    private final String ALTERARPRECO = "UPDATE \"Produto\" SET \"preco\"=? WHERE \"codProd\"=?";
    private final String ALTERARDESC = "UPDATE \"Produto\" SET \"descricao\"=? WHERE \"codProd\"=?";
    private final String ALTERARCUSTO = "UPDATE \"Produto\" SET \"custoPart\"=? WHERE \"codProd\"=?";
    private final String ALTERARQTD = "UPDATE \"Produto\" SET \"qtd\"=? WHERE \"codProd\"=?";
    private final String EXCLUIR = "DELETE FROM \"Produto\" WHERE \"codProd\"=?";
    
    public Produto buscar(int codProd){
        Produto p = null;
        try {
            con.conectar();
            PreparedStatement buscar = con.getConexao().prepareStatement(BUSCAR);
            buscar.setInt(1, codProd);
            ResultSet rsBuscar = buscar.executeQuery();
            while(rsBuscar.next()){
                p = new Produto(rsBuscar.getInt("codProd"),rsBuscar.getFloat("preco"),rsBuscar.getString("descricao"),
                        rsBuscar.getInt("qtd"), rsBuscar.getInt("custoPart"));
            }
            con.desconectar();
        } catch (Exception e) {
            System.out.println("Erro ao buscar Produto por codigo no banco de dados: "+e.getMessage());
            e.printStackTrace();
        }
        return p;
    }
     
    public void inserir(Produto p){
        try {
            con.conectar();
            PreparedStatement inserir = con.getConexao().prepareStatement(INSERIR);
            inserir.setInt(1, p.getCodProd());
            inserir.setFloat(2, p.getPreco());
            inserir.setString(3, p.getDescricao());
            inserir.setFloat(4, p.getCustoPart());
            inserir.setInt(5, p.getQtd());
            inserir.execute();
            System.out.println("Produto cadastrado com sucesso!");
            con.desconectar();
        } catch (Exception e) {
            System.out.println("Erro ao inserir Produto no banco de dados: " +e.getMessage());
            e.printStackTrace();
        }
    }
    
    public ArrayList<Produto> relatorio(){
        ArrayList relProd = new ArrayList();
        try {
            con.conectar();
            PreparedStatement relatorio = con.getConexao().prepareStatement(RELATORIO);
            ResultSet rsRelatorio = relatorio.executeQuery();
            while (rsRelatorio.next()) {
                Produto p = new Produto(rsRelatorio.getInt("codProd"),rsRelatorio.getFloat("preco"),
                        rsRelatorio.getString("descricao"),rsRelatorio.getInt("qtd"), rsRelatorio.getInt("custoPart"));
		relProd.add(p); 
            }
            con.desconectar();
        } catch (Exception e) {
            System.out.println("Erro ao emitir relatório de Vendedor: "+e.getMessage());
            e.printStackTrace();
        }
        return relProd;
    }
    
    public void alterarCodProd(int codProd, int novo){
        try {
            con.conectar();
            PreparedStatement alterarCodProd = con.getConexao().prepareStatement(ALTERARCODPROD);
            alterarCodProd.setInt(1, novo);
            alterarCodProd.setInt(2, codProd);
            alterarCodProd.execute();
            System.out.println("Código do Produto alterado com sucesso!");
            con.desconectar();
        } catch (Exception e) {
            System.out.println("Erro ao alterar o código do Produto no banco de dados: "+e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void alterarPreco(int codProd, Float novo){
        try {
            con.conectar();
            PreparedStatement alterarNome = con.getConexao().prepareStatement(ALTERARPRECO);
            alterarNome.setFloat(1, novo);
            alterarNome.setInt(2, codProd);
            alterarNome.execute();
            System.out.println("Preço alterado com sucesso!");
            con.desconectar();
        } catch (Exception e) {
            System.out.println("Erro ao alterar o preço do Produto no banco de dados: "+e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void alterarDesc(int codProd, String novo){
        try {
            con.conectar();
            PreparedStatement alterarDesc = con.getConexao().prepareStatement(ALTERARDESC);
            alterarDesc.setString(1, novo);
            alterarDesc.setInt(2, codProd);
            alterarDesc.execute();
            System.out.println("Descrição alterada com sucesso!");
            con.desconectar();
        } catch (Exception e) {
            System.out.println("Erro ao alterar desccrição do Produto no banco de dados: "+e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void alterarCusto(int codProd, float novo){
        try {
            con.conectar();
            PreparedStatement alterarCusto = con.getConexao().prepareStatement(ALTERARCUSTO);
            alterarCusto.setFloat(1, novo);
            alterarCusto.setInt(2, codProd);
            alterarCusto.execute();
            System.out.println("Custo particionado alterado com sucesso!");
            con.desconectar();
        } catch (Exception e) {
            System.out.println("Erro ao alterar custo particionado do Produto no banco de dados: "+e.getMessage());
            e.printStackTrace();
        }
    }  

    public void alterarQtd(int codProd, int qtd){
        try {
            con.conectar();
            PreparedStatement alterarQtd = con.getConexao().prepareStatement(ALTERARQTD);
            alterarQtd.setInt(1, qtd);
            alterarQtd.setInt(2, codProd);
            alterarQtd.execute();
            System.out.println("Quantidade  alterada com sucesso!");
            con.desconectar();
        } catch (Exception e) {
            System.out.println("Erro ao alterar quantidade do Produto no banco de dados: "+e.getMessage());
            e.printStackTrace();
        }
    }  
    
    public void excluir(int codProd){
        try {
            con.conectar();
            PreparedStatement excluir = con.getConexao().prepareStatement(EXCLUIR);
            excluir.setInt(1, codProd);
            excluir.execute();
            System.out.println("Produto excluido com sucesso!");
            con.desconectar();
        } catch (Exception e) {
            System.out.println("Erro ao excluir Produto no banco de dados: "+e.getMessage());
            e.printStackTrace();
        }
    }
  /*  
    public void atualizar(int qtd, int codProd){
        try {
            con.conectar();
            PreparedStatement repor = con.getConexao().prepareStatement(ATUALIZAR);
            repor.setInt(1, qtd);
            repor.setInt(2, codProd);
            repor.execute();
            con.desconectar();
        } catch (Exception e) {
            System.out.println("Erro ao repor produto no banco de dados: "+e.getMessage());
            e.printStackTrace();
        }
    }*/
    
}
