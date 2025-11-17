package store.esgseed.api.soccer.player;


import store.esgseed.api.soccer.common.domain.Messenger;


public interface PlayerService {
    Messenger save(PlayerModel player);
    Messenger delete(Long id);
    Messenger update(PlayerModel player);
    Messenger findById(Long id);
    Messenger findAll();
}
