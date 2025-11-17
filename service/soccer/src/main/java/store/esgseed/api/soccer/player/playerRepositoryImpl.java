package store.esgseed.api.soccer.player;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class playerRepositoryImpl implements PlayerRepositoryCustom {
    private final JPAQueryFactory queryFactory;
}
