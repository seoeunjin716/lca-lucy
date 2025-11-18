package store.esgseed.api.soccer.player;


import store.esgseed.api.soccer.common.domain.Messenger;


public interface PlayerService {
    Messenger save(PlayerModel player);
    Messenger delete(long id);
    Messenger update(PlayerModel player);
    Messenger findById(long id);
    Messenger findAll();
    Messenger searchByKeyword(String keyword);
}
