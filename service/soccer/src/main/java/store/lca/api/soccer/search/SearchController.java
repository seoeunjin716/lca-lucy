package store.lca.api.soccer.search;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import store.lca.api.soccer.common.domain.Messenger;
import store.lca.api.soccer.player.PlayerService;

@Slf4j
@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchController {

    private final PlayerService playerService;

    /**
     * ?§Ïõå?úÎ°ú ?†Ïàò Í≤Ä??
     * GET /search/keyword?keyword=?çÍ∏∏??
     */
    @GetMapping("/keyword")
    public Messenger searchByKeyword(@RequestParam String keyword) {
        System.out.println("=================================");
        System.out.println("Í≤Ä?âÏñ¥: " + keyword);
        System.out.println("=================================");
        log.info("Í≤Ä?âÏñ¥ ?îÏ≤≠: {}", keyword);
        return playerService.searchByKeyword(keyword);
    }
}
