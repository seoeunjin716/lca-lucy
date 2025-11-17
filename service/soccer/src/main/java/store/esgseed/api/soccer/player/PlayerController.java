package store.esgseed.api.soccer.player;

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
@RequestMapping("/players")
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerService playerService;

    @PostMapping("")
    public Messenger save(@RequestBody PlayerModel player) {
        return playerService.save(player);
    }

    @DeleteMapping("/{id}")
    public Messenger delete(@PathVariable Long playerId) {
        return playerService.delete(playerId);
    }

    @PutMapping("/{id}")
    public Messenger update(@RequestBody PlayerModel player) {
        return playerService.update(player);
    }

    @GetMapping("/id/{playerId}")
    public Messenger findById(@PathVariable Long playerId) {
        return playerService.findById(playerId);
    }

    @GetMapping("/all")
    public Messenger findAll() {
        return playerService.findAll();
    }
}
