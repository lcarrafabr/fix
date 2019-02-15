/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fix;

import com.mysql.jdbc.PreparedStatement;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;


/**
 *
 * @author Aluno
 */

public class Contatos implements Serializable {
    private static final long serialVersionUID = 1L;
 
    private Integer cod;
    private String nome;
     private String tel;
     private String observacao;


       public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Contatos() {
    }

    public Contatos(Integer cod) {
        this.cod = cod;
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cod != null ? cod.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contatos)) {
            return false;
        }
        Contatos other = (Contatos) object;
        if ((this.cod == null && other.cod != null) || (this.cod != null && !this.cod.equals(other.cod))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fix.Contatos1[cod=" + cod + "]";
    }




            void setCadastrar(ClassConecta conexao){
  try{


        String comando = "INSERT INTO contatos  "+
" 	(cod,  "+
" 	nome,  "+
" 	tel,  "+
" 	observacao "+
" 	) "+
" 	VALUES "+
" 	(null,  "+
" 	?,  "+
" 	?,  "+
" 	?  "+
" 	); "
;

        System.out.println("Executando operação...");

        PreparedStatement stmt = (PreparedStatement) conexao.con.prepareStatement(comando);

        stmt.setString(1,getNome());
        stmt.setString(2,getTel());
        stmt.setString(3,getObservacao());

        stmt.execute();

        //System.out.println("Transação Concluída");
        //JOptionPane.showMessageDialog(null, "Transação Concluída", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
        }catch(Exception e){
            System.err.println("Erro na Transação\n"+e);
            JOptionPane.showMessageDialog(null, "Erro na Transação", "ATENÇÃO", JOptionPane.ERROR_MESSAGE);
        }
}



    public ResultSet getConsultar(ClassConecta conexao) throws SQLException
    {
        ResultSet resultSet = null;

        try
        {
            String comando;
            comando = "select * " +
                    "from contatos "+
                    "where cod = cod ";
                    

            int quantParam = 0;


           if (getNome() != null) {
                comando = comando + "AND nome like ?";
            }

            if (getCod() != null){
               comando = comando + " AND cod = ? ";
            }

            comando = comando + " order by cod ";
            //O parâmetro resultSetType define se o ResultSet irá ser navegável e posicionado ou não:
            //ResultSet.TYPE_FORWARD_ONLY: com este parâmetro o ResultSet não poderá ser navegável, ou seja, poderemos somente avançar no objeto ResultSet para poder buscar valores.
            //ResultSet.TYPE_SCROLL_INSENSITIVE: com este parâmetro o ResultSet poderá ser navegável em qualquer direção, para frente e para trás, e será insensível a mudanças feitas por outras transações ou por outros Statements da mesma transação.
            //ResultSet.TYPE_SCROLL_SENSITIVE: com este parâmetro o ResultSet poderá ser navegável para qualquer direção, e será sensível a mudanças feitas por outras transações ou por outros Statements da mesma transação.
            java.sql.PreparedStatement stmtQuery = conexao.con.prepareStatement(comando);


           if (getNome() != null){
               quantParam = quantParam +1;
               stmtQuery.setString(quantParam, getNome()+"%");

            }

            if (getCod() != null){
               quantParam = quantParam +1;
               stmtQuery.setInt(quantParam, getCod());

            }

        resultSet = stmtQuery.executeQuery();


        }
        catch (SQLException sqlex)
        {
             JOptionPane.showMessageDialog(null,"Não foi Possivél executar o comando sql" + sqlex);

        }

     return resultSet;
     }



    void setAlterar(ClassConecta conexao){

        try{
        //ClassConecta conexao = new ClassConecta();

        //conexao.conecta();

        String comando = "UPDATE contatos "+
" 	SET "+
" 	nome = ?, "+
" 	tel = ? , "+
" 	observacao = ? "+
" 	WHERE "+
" 	cod = ?";

        System.out.println("Executando operação...");

        PreparedStatement stmt = (PreparedStatement) conexao.con.prepareStatement(comando);

        stmt.setString(1,getNome());
        stmt.setString(2,getTel());
        stmt.setString(3,getObservacao());
        stmt.setInt(4,getCod());

        stmt.executeUpdate();

        System.out.println("Transação Concluída");
        JOptionPane.showMessageDialog(null, "O REGISTRO foi salvo com sucesso.", "ATENÇÃO", JOptionPane.INFORMATION_MESSAGE);
        }catch(Exception e){
            System.err.println("Erro na Transação\n"+e);
            JOptionPane.showMessageDialog(null, "Erro na Transação", "ATENÇÃO", JOptionPane.ERROR_MESSAGE);
        }


    }


     void setExcluir(ClassConecta conexao){

        try{
        //ClassConecta conexao = new ClassConecta();

        //conexao.conecta();

        String comando =

" DELETE FROM contatos  "+
" 	WHERE "+
" 	cod = ?  ";

        PreparedStatement stmt = (PreparedStatement) conexao.con.prepareStatement(comando);
        //Formatar data Prevista

        stmt.setInt(1,getCod());


        stmt.executeUpdate();

        //System.out.println("Transação Concluída");
        JOptionPane.showMessageDialog(null, "O REGISTRO foi excluído com sucesso.", "ATENÇÃO", JOptionPane.INFORMATION_MESSAGE);
        }catch(Exception e){
            System.err.println("Erro na Transação\n"+e);
            JOptionPane.showMessageDialog(null, "Erro na Transação", "ATENÇÃO", JOptionPane.ERROR_MESSAGE);
        }
    }
























}
