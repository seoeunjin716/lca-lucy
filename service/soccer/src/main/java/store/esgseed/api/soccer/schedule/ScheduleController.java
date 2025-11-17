package store.esgseed.api.soccer.schedule;

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
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("")
    public Messenger save(@RequestBody ScheduleModel schedule) {
        return scheduleService.save(schedule);
    }

    @DeleteMapping("/{id}")
    public Messenger delete(@PathVariable Long id) {
        return scheduleService.delete(id);
    }

    @PutMapping("/{id}")
    public Messenger update(@RequestBody ScheduleModel schedule) {
        return scheduleService.update(schedule);
    }

    @GetMapping("/id/{id}")
    public Messenger findById(@PathVariable Long id) {
        return scheduleService.findById(id);
    }

    @GetMapping("/all")
    public Messenger findAll() {
        return scheduleService.findAll();
    }
}
