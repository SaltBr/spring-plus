package org.example.expert.domain.todo.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.example.expert.domain.manager.entity.QManager;
import org.example.expert.domain.todo.entity.QTodo;
import org.example.expert.domain.todo.entity.Todo;
import org.example.expert.domain.user.entity.QUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import com.querydsl.core.BooleanBuilder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class TodoRepositoryQueryImpl implements TodoRepositoryQuery{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<Todo> findByIdWithUser(Long todoId){
        QTodo todo = QTodo.todo;
        QUser user = QUser.user;
        return Optional.ofNullable(jpaQueryFactory
                .selectFrom(todo)
                .leftJoin(todo.user, user).fetchJoin()
                .where(todo.id.eq(todoId))
                .fetchOne());
    }

    @Override
    public Page<Todo> findAllBySearch(String title, String nickname, LocalDateTime createdStart, LocalDateTime createdEnd, Pageable pageable){
        BooleanBuilder whereOption = new BooleanBuilder();
        QTodo todo = QTodo.todo;
        QManager manager = QManager.manager;
        QUser user = QUser.user;

        if (title != null) {
            whereOption.and(todo.title.contains(title));
        }

        if(nickname != null) {
            whereOption.and(todo.user.nickname.contains(nickname));
        }

        if(createdStart != null) {
            whereOption.and(todo.createdAt.goe(createdStart));
        }

        if(createdEnd != null) {
            whereOption.and(todo.createdAt.loe(createdEnd));
        }

        List<Todo> todos = jpaQueryFactory
                .selectFrom(todo)
                .leftJoin(todo.managers, manager).fetchJoin()
                .leftJoin(todo.user, user).fetchJoin()
                .where(whereOption)
                .orderBy(todo.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = jpaQueryFactory
                .select(todo.count())
                .from(todo)
                .leftJoin(todo.managers, manager)
                .leftJoin(todo.user, user)
                .where(whereOption)
                .fetchOne();

        if(count == null){
            count = 0L;
        }

        return new PageImpl<> (todos, pageable, count);
    }
}