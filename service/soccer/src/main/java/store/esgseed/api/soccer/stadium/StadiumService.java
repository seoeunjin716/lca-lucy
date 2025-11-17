package store.esgseed.api.soccer.stadium;

import store.esgseed.api.soccer.common.domain.Messenger;


public interface StadiumService {
    Messenger save(StadiumModel stadium);
    Messenger delete(Long id);
    Messenger update(StadiumModel stadium);
    Messenger findById(Long id);
    Messenger findAll();
}




