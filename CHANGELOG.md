#### 유저
1. 유저 정보 입력 Controller
2. 유저 유효성 검증 Domain
   - 이름은 빈 값이 입력되면 안됨
3. 유저 저장 Repository

---
#### 팔로우
1. 팔로잉 유저, 팔로워 유저 ID 입력 Controller
2. 팔로우 여부 확인 Repository
   - 팔로우 상태라면 에러 반환
3. 자기 자신 팔로우 시 에러 반환 Domain
4. 팔로우 카운트 증감 Domain
5. 팔로우 저장 Repository
---
#### 게시글
1. 게시글 정보 입력 Controller 
2. 유저 유효성 검증 Domain
3. 게시글 유효성 검증 Domain
    - 5글자 이상 500자 이하
4. 게시글 저장 Repository
---
#### 댓글
1. 댓글 정보 입력 Controller
2. 유저 유효성 검증 Domain
3. 게시글 유효성 검증 Domain
4. 댓글 유효성 검증 Domain
   - 100자 이하
5. 댓글 저장 Repository
---
#### 게시글 상호작용
1. 유저 ID와 좋아요를 누를 게시글의 ID 입력 Controller
2. 게시글과 유저의 좋아요 존재 여부 확인 Repository
3. 유저 본인의 게시글인지 검증 Domain
4. 게시글 좋아요 수 증가 Domain 
5. DB 저장 및 결과 반환 Repository
---

#### 피드 조회 성능 최적화
1. DB 인덱스 추가
- PostQueue 테이블에 (UesrId, PostId) 인덱스 추가

2. 캐시 적용
- 문제점: 팔로워가 1억명인 유저가 게시글을 하나 작성할 때마다 1억개의 삽입/조회 발생