# 스프링 공통 응답 구조

## 공통 응답 DTO 구조

```java
@JsonRootName("result")
@Getter
public class ApiResponse<T> {

    private static final String SUCCESS_STATUS = "success";
    private static final String FAIL_STATUS = "fail";
    private static final String SUCCESS_RESPONSE_PROPERTY_NAME = "data";
    private static final String FAIL_RESPONSE_PROPERTY_NAME = "error";

    private final String status;
    private final Map<String, T> data;

    private ApiResponse(String status, String propertyName, T data) {
        this.status = status;
        this.data = Collections.singletonMap(propertyName, data);
    }

    public static ApiResponse<Void> success() {
        return new ApiResponse<>(SUCCESS_STATUS, SUCCESS_RESPONSE_PROPERTY_NAME, null);
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(SUCCESS_STATUS, SUCCESS_RESPONSE_PROPERTY_NAME, data);
    }

    public static ApiResponse<ErrorResponse> fail(ErrorResponse errorResponse) {
        return new ApiResponse<>(FAIL_STATUS, FAIL_RESPONSE_PROPERTY_NAME, errorResponse);
    }

    @JsonAnyGetter
    public Map<String, T> getData() {
        return data;
    }

}
```

## 응답 예시

### 1. 요청 성공

#### 1.1. 응답 데이터가 없는 경우

```json
{
  "result": {
    "status": "success"
  }
}
```

#### 1.2. 응답 데이터가 있는 경우

##### 1.2.1. 게시글 상세 조회

```json
{
  "result": {
    "status": "success",
    "data": {
      "postId": 1,
      "title": "제목",
      "writer": "작성자",
      "content": "내용",
      "createdAt": "2024-05-09T23:47:09.84513"
    }
  }
}
```

##### 1.2.2. 페이징 데이터를 포함한 게시글 목록 조회

```json
{
  "result": {
    "status": "success",
    "data": {
      "posts": [
        {
          "postId": 2,
          "title": "제목",
          "writer": "작성자",
          "createdAt": "2024-05-09T23:47:14.308914"
        },
        {
          "postId": 1,
          "title": "제목",
          "writer": "작성자",
          "createdAt": "2024-05-09T23:47:09.84513"
        }
      ],
      "page": 1,
      "totalPages": 1,
      "totalElements": 2,
      "prev": false,
      "next": false,
      "first": true,
      "last": true
    }
  }
}
```

### 2. 요청 실패

#### 2.1. 유효성 검증 예외가 없는 경우

```json
{
  "result": {
    "status": "fail",
    "error": {
      "code": "E404001",
      "message": "게시글을 찾을 수 없습니다.",
      "fields": []
    }
  }
}
```

#### 2.2. 유효성 검증 예외가 있는 경우

```json
{
  "result": {
    "status": "fail",
    "error": {
      "code": "E400001",
      "message": "입력값이 잘못되었습니다.",
      "fields": [
        {
          "field": "writer",
          "input": "",
          "message": "작성자를 입력해주세요."
        },
        {
          "field": "content",
          "input": "",
          "message": "본문을 입력해주세요."
        }
      ]
    }
  }
}
```