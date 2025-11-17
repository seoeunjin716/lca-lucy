package store.esgseed.api.soccer.search;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "searches")
public class Search {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String keyword;

    private String category; // player, team, stadium, schedule

    private String referenceId; // 검색된 항목의 ID

    private String searchDate; // 검색 날짜/시간

}
