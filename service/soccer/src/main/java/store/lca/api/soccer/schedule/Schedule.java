package store.lca.api.soccer.schedule;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import store.lca.api.soccer.stadium.Stadium;


@Data
@Entity
@Table(name = "schedules")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String scheDate;

    private String stadiumUk;

    private String gubun;

    private String hometeamUk;

    private String awayteamUk;

    private String homeScore;

    private String awayScore;

    @ManyToOne
    @JoinColumn(name = "stadium_id")
    private Stadium stadium;


}
