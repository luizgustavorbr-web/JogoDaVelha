
package DAO;
import VIEW.ConexaoBanco;
import VIEW.Jogador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JogadorDAO {

    public void salvarJogador(String nome) {
        String sql = "INSERT INTO jogadores (nome) VALUES (?)";

        try (Connection con = ConexaoBanco.conectar();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.executeUpdate();
            System.out.println("Jogador salvo!");

        } catch (SQLException e) {
            System.out.println("Erro ao salvar jogador: " + e.getMessage());
        }
    }

    public void salvarJogador(Jogador jogador1) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
