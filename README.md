# Board API 📝

Spring Boot와 MyBatis로 구현한 게시판 REST API 프로젝트입니다.  
게시글 목록 조회, 상세 조회, 등록, 수정, 삭제 기능을 제공하며 MySQL 데이터베이스를 사용합니다.

## 🚀 주요 기능

- 게시글 목록 조회
- 제목/내용 기준 키워드 검색
- 페이지네이션
- 게시글 상세 조회 시 조회수 증가
- 게시글 등록, 수정, 삭제
- 전역 예외 처리

## 🛠 기술 스택

- Java 21
- Spring Boot 3.5.16
- Gradle 9.5.1 Wrapper
- Spring Web
- MyBatis
- MySQL
- Lombok
- JUnit 5

## 📁 프로젝트 구조

```text
src
├── main
│   ├── java/com/example/board
│   │   ├── controller     # REST API 컨트롤러
│   │   ├── domain         # 도메인 객체
│   │   ├── dto            # 요청/응답 DTO
│   │   ├── exception      # 예외 및 전역 예외 처리
│   │   ├── mapper         # MyBatis Mapper 인터페이스
│   │   ├── service        # 비즈니스 로직
│   │   └── BoardApplication.java
│   └── resources
│       ├── mapper         # MyBatis XML Mapper
│       ├── application.yaml
│       ├── schema.sql
│       └── data.sql
└── test
```

## ⚙️ 실행 전 준비

### 1. MySQL 데이터베이스 생성

`application.yaml`의 기본 설정은 아래와 같습니다.

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/board?serverTimezone=Asia/Seoul
    username: root
    password: 1234
```

로컬 MySQL에 `board` 데이터베이스를 생성합니다.

```sql
CREATE DATABASE board DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

### 2. 테이블 생성

`src/main/resources/schema.sql` 내용을 MySQL에서 실행합니다.

```sql
CREATE TABLE board (
   id          BIGINT AUTO_INCREMENT PRIMARY KEY,
   title       VARCHAR(200)  NOT NULL,
   content     TEXT          NOT NULL,
   writer      VARCHAR(50)   NOT NULL,
   view_count  INT           DEFAULT 0,
   created_at  TIMESTAMP     DEFAULT CURRENT_TIMESTAMP,
   updated_at  TIMESTAMP     DEFAULT CURRENT_TIMESTAMP
);
```

현재 설정은 `spring.sql.init.mode: never`이므로 애플리케이션 실행 시 `schema.sql`과 `data.sql`이 자동 실행되지 않습니다.

## ▶️ 실행 방법

Windows PowerShell 기준:

```powershell
.\gradlew.bat bootRun
```

macOS/Linux 기준:

```bash
./gradlew bootRun
```

서버는 기본적으로 `http://localhost:8080`에서 실행됩니다.

## 📌 API 목록

| 기능 | Method | URL |
| --- | --- | --- |
| 게시글 목록 조회 | `GET` | `/api/boards` |
| 게시글 검색/페이징 | `GET` | `/api/boards?keyword=검색어&page=1&size=10` |
| 게시글 상세 조회 | `GET` | `/api/boards/{id}` |
| 게시글 등록 | `POST` | `/api/boards` |
| 게시글 수정 | `PUT` | `/api/boards/{id}` |
| 게시글 삭제 | `DELETE` | `/api/boards/{id}` |

## 💬 요청 예시

### 게시글 등록

```http
POST /api/boards
Content-Type: application/json

{
  "title": "첫 번째 게시글",
  "content": "게시글 내용입니다.",
  "writer": "홍길동"
}
```

### 게시글 수정

```http
PUT /api/boards/1
Content-Type: application/json

{
  "title": "수정된 제목",
  "content": "수정된 내용입니다."
}
```

### 목록 조회 응답 형태

```json
{
  "content": [
    {
      "id": 1,
      "title": "첫 번째 게시글",
      "content": "게시글 내용입니다.",
      "writer": "홍길동",
      "viewCount": 0,
      "createdAt": "2026-07-23T10:00:00",
      "updatedAt": "2026-07-23T10:00:00"
    }
  ],
  "page": 1,
  "size": 10,
  "totalElements": 1,
  "totalPages": 1
}
```

## 🧪 테스트

```powershell
.\gradlew.bat test
```

## ⚠️ 참고 사항

- `application.yaml`의 DB 계정 정보는 로컬 환경에 맞게 변경해야 합니다.
- `schema.sql`과 `data.sql`은 현재 자동 실행되지 않으므로 필요하면 직접 실행하거나 `spring.sql.init.mode` 설정을 변경하세요.
- `build.gradle`의 Validation, Swagger 관련 의존성 라인이 주석과 같은 줄에 있어 현재 비활성화된 상태로 보입니다. `@Valid` 검증이나 Swagger UI를 사용하려면 의존성 선언을 별도 줄로 정리해야 합니다.
