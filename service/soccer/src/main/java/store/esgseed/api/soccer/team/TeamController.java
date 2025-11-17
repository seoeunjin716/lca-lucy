package store.esgseed.api.soccer.team;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import store.esgseed.api.soccer.common.domain.Messenger;


@RestController
@RequestMapping("/teams")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @PostMapping("")
    public Messenger save(@RequestBody TeamModel team) {
        return teamService.save(team);
    }

    @DeleteMapping("/{id}")
    public Messenger delete(@PathVariable Long teamId) {
        return teamService.delete(teamId);
    }

    @PutMapping("/{id}")
    public Messenger update(@RequestBody TeamModel team) {
        return teamService.update(team);
    }

    @GetMapping("/id/{id}")
    public Messenger findById(@PathVariable Long teamId) {
        return teamService.findById(teamId);
    }

    @GetMapping("/all")
    public Messenger findAll() {
        return teamService.findAll();
    }
}
