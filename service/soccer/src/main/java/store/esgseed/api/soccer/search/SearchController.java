package store.esgseed.api.soccer.search;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import store.esgseed.api.soccer.common.domain.Messenger;


@RestController
@RequestMapping("/soccer/search")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class SearchController {

    private final SoccerSearchFacade searchFacade;

    /**
     * ?�합 검??API
     * Player, Schedule, Stadium, Team ?�체?�서 ?�워??검??
     * 
     * @param keyword 검?�어
     * @return ?�합 검??결과
     */
    @GetMapping("/keyword")
    public ResponseEntity<Messenger> searchByKeyword(@RequestParam String keyword) {
        System.out.println("========================================");
        System.out.println("전달받은 검색어: " + keyword);
        System.out.println("========================================");
        
        return ResponseEntity.ok(searchFacade.findByKeyword(keyword));
    }
}

