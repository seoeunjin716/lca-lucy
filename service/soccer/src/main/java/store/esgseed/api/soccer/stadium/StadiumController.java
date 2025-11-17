package store.esgseed.api.soccer.stadium;

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
@RequestMapping("/stadiums")
@RequiredArgsConstructor
public class StadiumController {

    private final StadiumService stadiumService;

    @PostMapping("")
    public Messenger save(@RequestBody StadiumModel stadium) {
        return stadiumService.save(stadium);
    }

    @DeleteMapping("/{id}")
    public Messenger delete(@PathVariable Long stadiumId) {
        return stadiumService.delete(stadiumId);
    }

    @PutMapping("/{id}")
    public Messenger update(@RequestBody StadiumModel stadium) {
        return stadiumService.update(stadium);
    }

    @GetMapping("/id/{id}")
    public Messenger findById(@PathVariable Long stadiumId) {
        return stadiumService.findById(stadiumId);
    }

    @GetMapping("/all")
    public Messenger findAll() {
        return stadiumService.findAll();
    }
}
