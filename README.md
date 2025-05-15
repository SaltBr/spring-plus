# SPRING PLUS
Java를 사용하여 주어진 코드를 수정/개선한 과제입니다.

Lvl 1 부터 Lvl 3까지 별도의 브랜치에 나뉘어 있으며, 커밋으로 과제 단계를 분리하였습니다.

## [Level 1](https://github.com/SaltBr/spring-plus/tree/level1)
### 1. 코드 개선 퀴즈 - @Transactional의 이해
- TodoService의 오류 수정
### 2. 코드 추가 퀴즈 - JWT의 이해
- User 정보와 JWT에 `nickname` 추가
### 3. 코드 개선 퀴즈 -  JPA의 이해
- 할 일 검색 시 `weather` 와 수정일의 시작/끝 필터링 추가
  
- JPQL 사용
### 4. 테스트 코드 퀴즈 - 컨트롤러 테스트의 이해
- Todo 컨트롤러 테스트에서 오류가 발생하지 않도록 수정

## [Level 2](https://github.com/SaltBr/spring-plus/tree/level2)
### 6. JPA Cascade
- 할 일을 생성한 유저가 매니저로 등록되도록 수정
  
- JPA의 cascade 기능 활용
### 7. N+1
- CommentController 클래스의 getComments() API의 N+1문제 해결
### 8. QueryDSL
- JPQL로 작성된 findByIdWithUser 를 QueryDSL로 변경
### 9. Spring Security
-  Filter와 Argument Resolver를 사용하던 코드들을 Spring Security로 변경
  
-  JWT 사용
  
-  ManagerService -> `saveManager` 메서드의 `AuthUser`가 `CustomUserPrincipal`로 수정되어 있지 않습니다. 이 부분은 Level3 브랜치의 **11.Transaction 심화** 커밋에서 수정됩니다.

## [Level 3](https://github.com/SaltBr/spring-plus/tree/level3)
### 10. QueryDSL 을 사용하여 검색 기능 만들기
- 검색 키워드, 생성일 범위, 닉네임으로 검색하는 기능
  
- 반환 정보: 제목, 담당자 수, 댓글 개수
  
### 11. Transaction 심화
- 매니저 등록 요청을 기록하도록 로그 기능 추가
