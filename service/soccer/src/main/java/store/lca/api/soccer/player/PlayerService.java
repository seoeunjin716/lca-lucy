package store.lca.api.soccer.player;


import store.lca.api.soccer.common.domain.Messenger;


public interface PlayerService {
    Messenger save(PlayerModel player);
    Messenger delete(long id);
    Messenger update(PlayerModel player);
    Messenger findById(long id);
    Messenger findAll();
    Messenger searchByKeyword(String keyword);
}
