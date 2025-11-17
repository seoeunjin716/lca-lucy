package store.esgseed.api.soccer.team;

import store.esgseed.api.soccer.common.domain.Messenger;


public interface TeamService {
    Messenger save(TeamModel team);
    Messenger delete(Long id);
    Messenger update(TeamModel team);
    Messenger findById(Long id);
    Messenger findAll();
}
