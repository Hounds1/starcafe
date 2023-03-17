package kr.ac.kopo.starcafe.domain.user.model.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.ac.kopo.starcafe.domain.user.model.dto.SimpleUserResponse;
import kr.ac.kopo.starcafe.global.security.principal.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static kr.ac.kopo.starcafe.domain.user.model.QUser.*;

@Repository
@RequiredArgsConstructor
public class CustomUserRepositoryImpl implements CustomUserRepository{

    private final JPAQueryFactory queryFactory;
    @Override
    public Optional<CustomUserDetails> findCustomDetailsById(final Long id) {
        return Optional.ofNullable(
                queryFactory.select(Projections.constructor(CustomUserDetails.class,
                        user.id.as("id"),
                        user.email,
                        user.roleType))
                        .from(user)
                        .where(user.id.eq(id))
                        .fetchOne());
    }

    @Override
    public Optional<SimpleUserResponse> findUserByKeyword(final String keyword) {
        return Optional.ofNullable(
                queryFactory.select(Projections.constructor(SimpleUserResponse.class,
                        user.email,
                        user.firstName,
                        user.lastName,
                        user.roleType))
                        .from(user)
                        .where(user.id.eq(Long.parseLong(keyword)),
                                user.email.eq(keyword))
                        .fetchOne()
        );
    }
}
