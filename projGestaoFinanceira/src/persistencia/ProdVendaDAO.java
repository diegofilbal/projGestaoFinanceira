/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import conexao.Conexao;
import dominio.ProdVenda;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author diego
 */
public class ProdVendaDAO {
    
    private Conexao con = new Conexao("localhost", "5432", "Gestao Financeira", "postgres", "postgres");
    
    private final String BUSCARPRODUTO = "SELECT COUNT(*) FROM \"ProdVenda\" WHERE \"codProd\" =?";
    private final String BUSCARVENDA = "SELECT * FROM \"ProdVenda\" WHERE \"codVenda\" =?";
    private final String BUSCAR = "SELECT * FROM \"ProdVenda\" WHERE (\"codProd\" =? AND \"codVenda\" =?)";
    private final String INSERIR = "INSERT INTO \"ProdVenda\" (\"codVenda\", \"codProd\", \"qtd\") VALUES (?,?,?)";
    private final String EXCLUIR = "DELETE FROM \"ProdVenda\" WHERE (\"codProd\" =? AND \"codVenda\" =?)";
    private final String RELATORIO = "SELECT * FROM \"ProdVenda\"";
    private final String QTDPROD = "SELECT SUM(\"qtd\") FROM \"ProdVenda\" WHERE \"codProd\" =?"; // MULTIPLICAR PELO PRECO DESSE PROD
    private final String ALTERARCODVENDA = "UPDATE \"Venda\" SET \"codVenda\"=? WHERE \"codVenda\"=?";
    private final String SOMA = "SELECT SUM(\"qtd\") FROM \"ProdVenda\" WHERE \"codProd\"=?";
    
    public int buscarProduto(int codProd){
        int qtd = 0;
        try {
            con.conectar();
            PreparedStatement buscarProduto = con.getConexao().prepareStatement(BUSCARPRODUTO); 
            buscarProduto.setInt(1, codProd);
            ResultSet rs = buscarProduto.executeQuery(); 
            rs.next();
            qtd = rs.getInt("COUNT");
            con.desconectar();
        } catch (Exception e) {
            System.out.println("Erro ao buscar a Venda por codProd no banco de dados " + e.getMessage());
            e.printStackTrace();
        }
        return qtd;
    }
    
        public ProdVenda buscarCodVenda(int codVenda){
            ProdVenda pv = null;
        try {
            con.conectar();
            PreparedStatement buscarVenda = con.getConexao().prepareStatement(BUSCARVENDA); 
            buscarVenda.setInt(1, codVenda);
            ResultSet rs = buscarVenda.executeQuery(); 
            while(rs.next()){
                pv = new ProdVenda(rs.getInt("codProd"), rs.getInt("codVenda"), rs.getInt("qtd"));
            }
            con.desconectar();
        } catch (Exception e) {
            System.out.println("Erro ao buscar a Venda por codProd no banco de dados " + e.getMessage());
            e.printStackTrace();
        }
        return pv;
    }
    
    public ProdVenda buscar(int codVenda, int codProd){
        ProdVenda pv = null;
        try {
            con.conectar();
            PreparedStatement buscar = con.getConexao().prepareStatement(BUSCAR); 
            buscar.setInt(1, codProd);
            buscar.setInt(2, codVenda);
            ResultSet rsBuscar = buscar.executeQuery(); 
            while(rsBuscar.next()){
                pv = new ProdVenda(rsBuscar.getInt("codVenda"), rsBuscar.getInt("codProd"), rsBuscar.getInt("qtd"));
            }
            con.desconectar();
        } catch (Exception e) {
            System.out.println("Erro ao buscar a Venda por codProd no banco de dados " + e.getMessage());
            e.printStackTrace();
        }
        return pv;
    }
    
    public void inserir(ProdVenda pv){
        try {
            con.conectar();
            PreparedStatement inserir = con.getConexao().prepareStatement(INSERIR); 
            inserir.setInt(1, pv.getCodVenda());
            inserir.setInt(2, pv.getCodProd());
            inserir.setInt(3, pv.getQtd());
            inserir.execute();
            System.out.println("ProdVenda inserido com sucesso!");
            con.desconectar();
        } catch (Exception e) {
            System.out.println("Erro ao inserir Venda no banco de dados! " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void excluir(ProdVenda pv){
        try {
            con.conectar();
            PreparedStatement excluir = con.getConexao().prepareStatement(EXCLUIR);
            excluir.setInt(1, pv.getCodProd());
            excluir.setInt(2, pv.getCodVenda());
            excluir.execute();
            System.out.println("ProdVenda excluido com sucesso!");
            con.desconectar();
        } catch (Exception e) {
            System.out.println("Erro ao excluir Venda no banco de dados! " + e.getMessage());
            e.printStackTrace();
        }
    }
    
        public ArrayList<ProdVenda> relatorio(){
        ArrayList relProdVenda = new ArrayList();
        try {
            con.conectar();
            PreparedStatement relatorio = con.getConexao().prepareStatement(RELATORIO);
            ResultSet rsRelatorio = relatorio.executeQuery();
            while (rsRelatorio.next()) {
                ProdVenda pv = new ProdVenda(rsRelatorio.getInt("codVenda"), rsRelatorio.getInt("codProd"), rsRelatorio.getInt("qtd"));
		relProdVenda.add(pv); 
            }
            con.desconectar();
        } catch (Exception e) {
            System.out.println("Erro ao emitir relatório de Venda: "+e.getMessage());
            e.printStackTrace();
        }
        return relProdVenda;
    }
 
    public int qtdProd(int codProd){
        int qtd = 0;
        try {
            con.conectar();
            PreparedStatement qtdProd = con.getConexao().prepareStatement(QTDPROD);
            qtdProd.setInt(1, codProd);
            ResultSet rs = qtdProd.executeQuery();
            
            qtd = rs.getInt("SUM");
            con.desconectar();
        } catch (Exception e) {
             System.out.println("Erro ao calcular quantidade de vendas por produto: "+e.getMessage());
            e.printStackTrace();
        }
        return qtd;
    }
    
    public int soma(int codProd){
        int qtd = 0;
        try {
            con.conectar();
            PreparedStatement buscarProduto = con.getConexao().prepareStatement(SOMA); 
            buscarProduto.setInt(1, codProd);
            ResultSet rs = buscarProduto.executeQuery(); 
            rs.next();
                qtd = rs.getInt("sum");
            con.desconectar();
        } catch (Exception e) {
            System.out.println("Erro ao buscar a Venda por codProd no banco de dados " + e.getMessage());
            e.printStackTrace();
        }
        return qtd;
    }
        
}
