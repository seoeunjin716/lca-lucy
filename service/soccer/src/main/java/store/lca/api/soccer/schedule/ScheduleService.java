package store.lca.api.soccer.schedule;

import store.lca.api.soccer.common.domain.Messenger;

public interface ScheduleService {
    Messenger save(ScheduleModel schedule);
    Messenger delete(Long id);
    Messenger update(ScheduleModel schedule);
    Messenger findById(Long id);
    Messenger findAll();
}
