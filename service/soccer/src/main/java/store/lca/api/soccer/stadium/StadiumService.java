package store.lca.api.soccer.stadium;

import store.lca.api.soccer.common.domain.Messenger;


public interface StadiumService {
    Messenger save(StadiumModel stadium);
    Messenger delete(Long id);
    Messenger update(StadiumModel stadium);
    Messenger findById(Long id);
    Messenger findAll();
}




