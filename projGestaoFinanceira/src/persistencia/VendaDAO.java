/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import conexao.Conexao;
import dominio.Venda;
import java.util.ArrayList;

/**
 *
 * @author diego
 */
public class VendaDAO {
    
    private Conexao con = new Conexao("localhost", "5432", "Gestao Financeira", "postgres", "postgres");
    
    private final String BUSCARVENDEDOR = "SELECT COUNT(*) FROM \"Venda\" WHERE \"CPF\" =?";
    private final String BUSCARVENDEDOR2 = "SELECT * FROM \"Venda\" WHERE \"CPF\" =?";
    private final String BUSCARCODVENDA = "SELECT * FROM \"Venda\" WHERE \"codVenda\" =?";
    private final String RELATORIO = "SELECT * FROM \"Venda\"";
    private final String INSERIR = "INSERT INTO \"Venda\"(\"codVenda\",\"CPF\",\"data\",\"hora\") VALUES (?,?,?,?)";
    private final String EXCLUIR = "DELETE FROM \"Venda\" WHERE \"codVenda\" =?";
    private final String ALTERARCPF = "UPDATE \"Venda\" SET \"CPF\"=? WHERE \"codVenda\"=?";
    private final String ALTERARDATA = "UPDATE \"Venda\" SET \"data\"=? WHERE \"codVenda\"=?";
    private final String ALTERARHORA = "UPDATE \"Venda\" SET \"hora\"=? WHERE \"codVenda\"=?";
    private final String ALTERARCODVENDA = "UPDATE \"Venda\" SET \"codVenda\"=? WHERE \"codVenda\"=?";

    public int buscarVendedor(String CPF){
        int qtd = 0;
        try {
            con.conectar();
            PreparedStatement buscarVendedor = con.getConexao().prepareStatement(BUSCARVENDEDOR); 
            buscarVendedor.setString(1, CPF);
            ResultSet rs = buscarVendedor.executeQuery(); 
            rs.next();
            qtd = rs.getInt("COUNT");
            con.desconectar();
        } catch (Exception e) {
            System.out.println("Erro ao buscar a Venda por CPF no banco de dados " + e.getMessage());
            e.printStackTrace();
        }
        return qtd;
    }
    
    public ArrayList<Venda> buscarVendedor2(String CPF){
        ArrayList<Venda> relVenda = new ArrayList();
        try {
            con.conectar();
            PreparedStatement buscarVendedor = con.getConexao().prepareStatement(BUSCARVENDEDOR2); 
            buscarVendedor.setString(1, CPF);
            ResultSet rs = buscarVendedor.executeQuery(); 
            while(rs.next()){
                Venda ve = new Venda(rs.getInt("codVenda"), rs.getString("CPF"), rs.getString("data"), rs.getString("hora"));
                relVenda.add(ve);
            }
            con.desconectar();
        } catch (Exception e) {
            System.out.println("Erro ao buscar a Venda por CPF no banco de dados " + e.getMessage());
            e.printStackTrace();
        }
        return relVenda;
    }
    
    public Venda buscarCodVenda(int codVenda){
        Venda ve = null;
        try {
            con.conectar();
            PreparedStatement buscarVenda = con.getConexao().prepareStatement(BUSCARCODVENDA); 
            buscarVenda.setInt(1, codVenda);
            ResultSet rs = buscarVenda.executeQuery(); 
            while(rs.next()){
                ve = new Venda(rs.getInt("codVenda"), rs.getString("CPF"), rs.getString("data"), rs.getString("hora"));
            }
            con.desconectar();
        } catch (Exception e) {
            System.out.println("Erro ao buscar a Venda por codVenda no banco de dados " + e.getMessage());
            e.printStackTrace();
        }
        return ve;
    }       
        
    
    
    public ArrayList<Venda> relatorio(){
        ArrayList relVenda = new ArrayList();
        try {
            con.conectar();
            PreparedStatement relatorio = con.getConexao().prepareStatement(RELATORIO);
            ResultSet rsRelatorio = relatorio.executeQuery();
            while (rsRelatorio.next()) {
                Venda ve = new Venda(rsRelatorio.getInt("codVenda"), rsRelatorio.getString("CPF"),
                        rsRelatorio.getString("data"), rsRelatorio.getString("hora"));
		relVenda.add(ve); 
            }
            con.desconectar();
        } catch (Exception e) {
            System.out.println("Erro ao emitir relatório de Venda: "+e.getMessage());
            e.printStackTrace();
        }
        return relVenda;
    }
    
    public void inserir(Venda ve){
        try {
            con.conectar();
            PreparedStatement inserir = con.getConexao().prepareStatement(INSERIR);
            inserir.setInt(1, ve.getCodVenda());
            inserir.setString(2, ve.getCPF());
            inserir.setString(3, ve.getData());
            inserir.setString(4, ve.getHora());
            inserir.execute();
            con.desconectar();
        } catch (Exception e) {
            System.out.println("Erro ao inserir Venda no banco de dados: " +e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void excluir(Venda ve){
        try {
            con.conectar();
            PreparedStatement excluir = con.getConexao().prepareStatement(EXCLUIR);
            excluir.setInt(1, ve.getCodVenda());
            excluir.execute();
            System.out.println("Venda excluido com sucesso!");
            con.desconectar();
        } catch (Exception e) {
            System.out.println("Erro ao excluir Venda no banco de dados! " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void alterarCPF(String CPF, int codVenda){
        try {
            con.conectar();
            PreparedStatement alterar = con.getConexao().prepareStatement(ALTERARCPF);
            alterar.setString(1, CPF);
            alterar.setInt(2, codVenda);
            alterar.execute();
            System.out.println("CPF alterado com sucesso!");
            con.desconectar();
        } catch (Exception e) {
            System.out.println("Erro ao alterar CPF da venda no banco de dados! " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void alterarData(String data, int codVenda){
        try {
            con.conectar();
            PreparedStatement alterar = con.getConexao().prepareStatement(ALTERARDATA);
            alterar.setString(1, data);
            alterar.setInt(2, codVenda);
            alterar.execute();
            System.out.println("Data alterada com sucesso!");
            con.desconectar();
        } catch (Exception e) {
            System.out.println("Erro ao alterar data da venda no banco de dados! " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void alterarHora(String hora, int codVenda){
        try {
            con.conectar();
            PreparedStatement alterar = con.getConexao().prepareStatement(ALTERARHORA);
            alterar.setString(1, hora);
            alterar.setInt(2, codVenda);
            alterar.execute();
            System.out.println("Hora alterada com sucesso!");
            con.desconectar();
        } catch (Exception e) {
            System.out.println("Erro ao alterar hora da venda no banco de dados! " + e.getMessage());
            e.printStackTrace();
        }
    }
  
    public void alterarCodVenda(int novoCod, int codVenda){
        try {
            con.conectar();
            PreparedStatement alterar = con.getConexao().prepareStatement(ALTERARCODVENDA);
            alterar.setInt(1, novoCod);
            alterar.setInt(2, codVenda);
            alterar.execute();
            System.out.println("Código da venda alterado com sucesso!");
            con.desconectar();
        } catch (Exception e) {
            System.out.println("Erro ao alterar código da venda no banco de dados! " + e.getMessage());
            e.printStackTrace();
        }
    }
    
}
