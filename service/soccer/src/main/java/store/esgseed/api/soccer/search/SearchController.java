package store.esgseed.api.soccer.search;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import store.esgseed.api.soccer.common.domain.Messenger;
import store.esgseed.api.soccer.player.PlayerService;

@Slf4j
@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchController {

    private final PlayerService playerService;

    /**
     * 키워드로 선수 검색
     * GET /search/keyword?keyword=홍길동
     */
    @GetMapping("/keyword")
    public Messenger searchByKeyword(@RequestParam String keyword) {
        System.out.println("=================================");
        System.out.println("검색어: " + keyword);
        System.out.println("=================================");
        log.info("검색어 요청: {}", keyword);
        return playerService.searchByKeyword(keyword);
    }
}
