package store.lca.api.soccer.search;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import store.lca.api.soccer.common.domain.Messenger;
import store.lca.api.soccer.player.PlayerService;
import store.lca.api.soccer.schedule.ScheduleService;
import store.lca.api.soccer.stadium.StadiumService;
import store.lca.api.soccer.team.TeamService;





@Service
@RequiredArgsConstructor
public class SoccerSearchFacadeImpl implements SoccerSearchFacade {

    private final PlayerService playerService;
    private final ScheduleService scheduleService;
    private final StadiumService stadiumService;
    private final TeamService teamService;

    @Override
    public Messenger findByKeyword(String keyword) {
        System.out.println("=================================");
        System.out.println("검?�어: " + keyword);
        System.out.println("=================================");
        
        // TODO: 추후 요구사항에 따라 검색 결과를 반환
        // ?�제:
        // List<PlayerDTO> players = playerService.findByKeyword(keyword);
        // List<ScheduleDTO> schedules = scheduleService.findByKeyword(keyword);
        // List<StadiumDTO> stadiums = stadiumService.findByKeyword(keyword);
        // List<TeamDTO> teams = teamService.findByKeyword(keyword);
        
        return Messenger.builder()
                .message("검?�어 '" + keyword + "' ?�청 ?�료")
                .build();
    }
}

