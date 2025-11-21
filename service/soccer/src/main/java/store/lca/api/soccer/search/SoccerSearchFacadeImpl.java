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
        System.out.println("Í≤Ä?âÏñ¥: " + keyword);
        System.out.println("=================================");
        
        // TODO: Ï∂îÌõÑ ?ÑÏöî???∞Îùº Í≤Ä??Í≤∞Í≥ºÎ•??©Ï≥ê??Î∞òÌôò
        // ?úÏ†ú:
        // List<PlayerDTO> players = playerService.findByKeyword(keyword);
        // List<ScheduleDTO> schedules = scheduleService.findByKeyword(keyword);
        // List<StadiumDTO> stadiums = stadiumService.findByKeyword(keyword);
        // List<TeamDTO> teams = teamService.findByKeyword(keyword);
        
        return Messenger.builder()
                .message("Í≤Ä?âÏñ¥ '" + keyword + "' ?†Ï≤≠ ?ÑÎ£å")
                .build();
    }
}

