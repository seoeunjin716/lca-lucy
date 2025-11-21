package store.lca.api.soccer.team;

import store.lca.api.soccer.common.domain.Messenger;


public interface TeamService {
    Messenger save(TeamModel team);
    Messenger delete(Long id);
    Messenger update(TeamModel team);
    Messenger findById(Long id);
    Messenger findAll();
}
