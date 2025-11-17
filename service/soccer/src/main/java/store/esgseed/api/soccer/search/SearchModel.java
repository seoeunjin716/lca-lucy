package store.esgseed.api.soccer.search;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchModel {
    
    private String keyword;

    private String category;

    private String referenceId;

    private String searchDate;
    
}

