package store.lca.api.soccer.player;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    List<Player> findByPlayerNameContainingOrNicknameContaining(String playerName, String nickname);
}
