package store.lca.api.soccer.search;

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

    private String referenceId; // ê²€?‰ëœ ??ª©??ID

    private String searchDate; // ê²€??? ì§œ/?œê°„

}
