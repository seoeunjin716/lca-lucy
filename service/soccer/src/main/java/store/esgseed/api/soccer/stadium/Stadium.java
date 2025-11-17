package store.esgseed.api.soccer.stadium;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import store.esgseed.api.soccer.schedule.Schedule;
import store.esgseed.api.soccer.team.Team;

@Data
@Entity
@Table(name = "stadiums")
public class Stadium {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String stadiumUk;

    private String stadiumName;

    private String hometeamUk;     // team.team_id ?��?

    private String seatCount;

    private String address;

    private String ddd;

    private String tel;

    @OneToMany(mappedBy = "stadium")
    private List<Schedule> schedules;

    @OneToMany(mappedBy = "stadium")
    private List<Team> teams;
}
