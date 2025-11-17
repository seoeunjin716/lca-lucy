package store.esgseed.api.soccer.search;

import store.esgseed.api.soccer.common.domain.Messenger;

public interface SoccerSearchFacade {
    Messenger findByKeyword(String keyword);
}

