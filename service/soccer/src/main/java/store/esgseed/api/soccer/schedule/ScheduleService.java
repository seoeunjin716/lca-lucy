package store.esgseed.api.soccer.schedule;

import store.esgseed.api.soccer.common.domain.Messenger;

public interface ScheduleService {
    Messenger save(ScheduleModel schedule);
    Messenger delete(Long id);
    Messenger update(ScheduleModel schedule);
    Messenger findById(Long id);
    Messenger findAll();
}
