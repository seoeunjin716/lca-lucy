package store.lca.api.soccer.search;

import store.lca.api.soccer.common.domain.Messenger;

public interface SoccerSearchFacade {
    Messenger findByKeyword(String keyword);
}

