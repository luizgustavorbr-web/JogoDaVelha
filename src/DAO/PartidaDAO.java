package DAO;
import VIEW.ConexaoBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PartidaDAO {

    public void salvarPartida(int jogador1Id, int jogador2Id, int vencedorId) {
        String sql = "INSERT INTO partidas (jogador1_id, jogador2_id, vencedor_id) VALUES (?, ?, ?)";

        try (Connection con = ConexaoBanco.conectar();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, jogador1Id);
            stmt.setInt(2, jogador2Id);
            stmt.setInt(3, vencedorId);

            stmt.executeUpdate();
            System.out.println("✅ Partida registrada com sucesso!");

        } catch (SQLException e) {
            System.err.println("❌ Erro ao salvar partida: " + e.getMessage());
        }
    }
}

