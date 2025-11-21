package store.lca.api.soccer.team;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import store.lca.api.soccer.player.Player;
import store.lca.api.soccer.stadium.Stadium;


@Data
@Entity
@Table(name = "teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String teamUk;

    private String regionName;

    private String teamName;

    private String eTeamName;

    private String origYyyy;

    private String zipCode1;

    private String zipCode2;

    private String address;

    private String ddd;

    private String tel;

    private String fax;

    private String homepage;

    private String owner;

    private String stadiumUk;


    @ManyToOne
    @JoinColumn(name = "stadium_id")
    private Stadium stadium;


    @OneToMany(mappedBy = "team")
    private List<Player> players;

    




}
