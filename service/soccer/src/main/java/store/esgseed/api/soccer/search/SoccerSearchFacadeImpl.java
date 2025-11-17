package store.esgseed.api.soccer.search;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import store.esgseed.api.soccer.common.domain.Messenger;
import store.esgseed.api.soccer.player.PlayerService;
import store.esgseed.api.soccer.schedule.ScheduleService;
import store.esgseed.api.soccer.stadium.StadiumService;
import store.esgseed.api.soccer.team.TeamService;





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
        System.out.println("검색어: " + keyword);
        System.out.println("=================================");
        
        // TODO: 추후 필요에 따라 검색 결과를 합쳐서 반환
        // 시제:
        // List<PlayerDTO> players = playerService.findByKeyword(keyword);
        // List<ScheduleDTO> schedules = scheduleService.findByKeyword(keyword);
        // List<StadiumDTO> stadiums = stadiumService.findByKeyword(keyword);
        // List<TeamDTO> teams = teamService.findByKeyword(keyword);
        
        return Messenger.builder()
                .message("검색어 '" + keyword + "' 신청 완료")
                .build();
    }
}

