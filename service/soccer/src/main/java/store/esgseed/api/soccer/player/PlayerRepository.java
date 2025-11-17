package store.esgseed.api.soccer.player;

import org.springframework.stereotype.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long>, PlayerRepositoryCustom {
    void delete(Long id);
    Optional findById(Long id);
}
