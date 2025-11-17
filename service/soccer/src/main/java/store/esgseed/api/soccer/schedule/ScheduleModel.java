package store.esgseed.api.soccer.schedule;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleModel {
    
    private String scheDate;

    private String stadiumUk;

    private String gubun;

    private String hometeamUk;

    private String awayteamUk;

    private String homeScore;

    private String awayScore;
    
}
