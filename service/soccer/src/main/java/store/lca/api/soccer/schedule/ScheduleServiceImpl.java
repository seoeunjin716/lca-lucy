package store.lca.api.soccer.schedule;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import store.lca.api.soccer.common.domain.Messenger;


@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Override
    public Messenger save(ScheduleModel schedule) {
        Schedule entity = new Schedule();
        entity.setScheDate(schedule.getScheDate());
        entity.setStadiumUk(schedule.getStadiumUk());
        entity.setGubun(schedule.getGubun());
        entity.setHometeamUk(schedule.getHometeamUk());
        entity.setAwayteamUk(schedule.getAwayteamUk());
        entity.setHomeScore(schedule.getHomeScore());
        entity.setAwayScore(schedule.getAwayScore());
        scheduleRepository.save(entity);
        return Messenger.builder().code(200).message("?标车").build();
    }

    @Override
    public Messenger delete(Long id) {
        scheduleRepository.deleteById(id);
        return Messenger.builder().code(200).message("?标车").build();
    }

    @Override
    public Messenger update(ScheduleModel schedule) {
        Schedule entity = new Schedule();
        entity.setScheDate(schedule.getScheDate());
        entity.setStadiumUk(schedule.getStadiumUk());
        entity.setGubun(schedule.getGubun());
        entity.setHometeamUk(schedule.getHometeamUk());
        entity.setAwayteamUk(schedule.getAwayteamUk());
        entity.setHomeScore(schedule.getHomeScore());
        entity.setAwayScore(schedule.getAwayScore());
        scheduleRepository.save(entity);
        return Messenger.builder().code(200).message("?标车").build();
    }

    @Override
    public Messenger findById(Long id) {
        scheduleRepository.findById(id);
        return Messenger.builder().code(200).message("?标车").build();
    }

    @Override
    public Messenger findAll() {
        scheduleRepository.findAll();
        return Messenger.builder().code(200).message("?标车").build();
    }
}
