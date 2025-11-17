package store.esgseed.api.soccer.stadium;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StadiumModel {

    private String stadiumUk;

    private String stadiumName;

    private String hometeamUk;     // team.team_id ?��?

    private String seatCount;

    private String address;

    private String ddd;

    private String tel;

    
}
