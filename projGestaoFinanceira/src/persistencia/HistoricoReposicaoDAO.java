/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import conexao.Conexao;
import dominio.HistoricoReposicao;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author diego
 */
public class HistoricoReposicaoDAO {
    
    private Conexao con = new Conexao("localhost", "5432", "Gestao Financeira", "postgres", "postgres");
    
    private final String BUSCAR = "SELECT * FROM \"HistoricoReposicao\" WHERE (\"codProd\"=? AND \"data\"=?)";
    private final String INSERIR = "INSERT INTO \"HistoricoReposicao\" (\"data\",\"codProd\",\"qtd\") VALUES (?,?,?) ";
    private final String ALTERARQTD = "UPDATE \"HistoricoReposicao\" SET \"qtd\"=? WHERE (\"codProd\"=? AND \"data\"=?)";
    private final String ALTERARCODPROD = "UPDATE \"HistoricoReposicao\" SET \"codProd\"=? WHERE (\"codProd\"=? AND \"data\"=?)";
    private final String ALTERARDATA = "UPDATE \"HistoricoReposicao\" SET \"data\"=? WHERE (\"codProd\"=? AND \"data\"=?)";
    private final String RELATORIO = "SELECT * FROM \"HistoricoReposicao\"";
    private final String EXCLUIR = "DELETE FROM \"HistoricoReposicao\" WHERE (\"codProd\"=? AND \"data\"=?)";

    public HistoricoReposicao buscar(int codProd, String data){
        HistoricoReposicao hr = null;
        try { 
            con.conectar();
            PreparedStatement buscar = con.getConexao().prepareStatement(BUSCAR);
            buscar.setInt(1, codProd);
            buscar.setString(2, data);
            ResultSet rsBuscar = buscar.executeQuery();
            while(rsBuscar.next()){
                hr = new HistoricoReposicao(rsBuscar.getString("data"),
                        rsBuscar.getInt("codProd"), rsBuscar.getInt("qtd"));
            }
            con.desconectar();
        } catch (Exception e) {
            System.out.println("Erro ao buscar Reposição no banco de dados! "+e.getMessage());
            e.printStackTrace();
        }
        return hr;
    }
    
    public void inserir(HistoricoReposicao hr){
        try {
            con.conectar();
            PreparedStatement inserir = con.getConexao().prepareStatement(INSERIR);
            inserir.setString(1, hr.getData());
            inserir.setInt(2, hr.getCodProd());
            inserir.setInt(3, hr.getQtd());
            inserir.execute();
            System.out.println("Reposição inserida com sucesso!");
            con.desconectar();
        } catch (Exception e) {
            System.out.println("Erro ao inserir Reposição no banco de dados! "+e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void alterarQtd(int qtd, String data, int codProd){
        try {
            con.conectar();
            PreparedStatement alterarQtd = con.getConexao().prepareStatement(ALTERARQTD);
            alterarQtd.setInt(1, qtd);
            alterarQtd.setInt(2, codProd);
            alterarQtd.setString(3, data);
            alterarQtd.execute();
            System.out.println("Quantidade alterada com sucesso!");
            con.desconectar();
        } catch (Exception e) {
            System.out.println("Erro ao alterar quantidade do produto reposto no banco de dados! "+e.getMessage());
            e.printStackTrace();
        }
    }
    public void alterarCodProd(int novoCodProd, String data, int codProd){
        try {
            con.conectar();
            PreparedStatement alterarQtd = con.getConexao().prepareStatement(ALTERARCODPROD);
            alterarQtd.setInt(1, novoCodProd);
            alterarQtd.setInt(2, codProd);
            alterarQtd.setString(3, data);
            alterarQtd.execute();
            System.out.println("Codigo do produto alterado com sucesso!");
            con.desconectar();
        } catch (Exception e) {
            System.out.println("Erro ao alterar código do produto reposto no banco de dados! "+e.getMessage());
            e.printStackTrace();
        }
    }
    public void alterarData(String novaData, String data, int codProd){
        try {
            con.conectar();
            PreparedStatement alterarQtd = con.getConexao().prepareStatement(ALTERARDATA);
            alterarQtd.setString(1, novaData);
            alterarQtd.setInt(2, codProd);
            alterarQtd.setString(3, data);
            alterarQtd.execute();
            System.out.println("Data alterada com sucesso!");
            con.desconectar();
        } catch (Exception e) {
            System.out.println("Erro ao alterar data da reposição no banco de dados! "+e.getMessage());
            e.printStackTrace();
        }
    }
    
    public ArrayList<HistoricoReposicao> relatorio(){
        ArrayList relHist = new ArrayList();
        try {
            con.conectar();
            PreparedStatement relatorio = con.getConexao().prepareStatement(RELATORIO);
            ResultSet rsRelatorio = relatorio.executeQuery();
            while (rsRelatorio.next()) {
                HistoricoReposicao hr = new HistoricoReposicao(rsRelatorio.getString("data"),
                        rsRelatorio.getInt("codProd"), rsRelatorio.getInt("qtd"));
		relHist.add(hr); 
            }
            con.desconectar();
        } catch (Exception e) {
            System.out.println("Erro ao emitir relatório de Reposição: "+e.getMessage());
            e.printStackTrace();
        }
        return relHist;
    }
    
    public void excluir(int codProd, String data){
        try {
            con.conectar();
            PreparedStatement excluir = con.getConexao().prepareStatement(EXCLUIR);
            excluir.setInt(1, codProd);
            excluir.setString(2, data);
            excluir.execute();
            System.out.println("Reposição excluida com sucesso!");
            con.desconectar();
        } catch (Exception e) {
            System.out.println("Erro ao excluir Reposição no banco de dados: "+e.getMessage());
            e.printStackTrace();
        }
    }
    
}
