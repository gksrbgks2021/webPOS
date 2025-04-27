# 웹 으로 작성한 편의점 POS application.

![image](https://github.com/user-attachments/assets/e5d4b3aa-cd03-4985-ad7a-cc186ab8110f)


# 구성
| 구분 정보 | 항목 |
| --- | --- |
| 개발 언어 | JAVA 17버전, JavaScript |
| 프레임워크 | Spring-Boot-3.1.0 |
| 개발 환경 | STS, 인텔리제이 |
| 템플릿 엔진 | JSP |
| 버전 관리 시스템 | Git |
| 데이터베이스 | MySQL |
| 빌드 도구 | Maven |
| 웹 서버 | Tomcat |
| 서버 | Apache |
| 개발 일지 기록 | Notion |
| 패턴 | M-V-C 패턴 |
| 총 개발 소요 시간 | 25일 |
| 개발 인원 | 2명 | 

# 개발 목적
- 웹으로 편의점 POS 기 기능을 제공하는 어플리캐이션을 구축할 수 있다.
- Spring Jdbc 사용하여 DB 연결을 다루는 서버를 구축할 수 있다.


# 기능 개요
- 거래 관리 기능
  - 편의점 지점을 선택하고 해당 편의점의 주문 및 판매를 처리할 수 있다.
  - 특정 날짜의 거래 발생 정도를 정렬하여 조회할 수 있다.
- 회원 관리 기능 
  - 서버에 저장된 회원을 삭제할 수 있다. 
- 상품 관리 기능 
  - 상품을 등록하고 삭제할 수 있다.
 
# DB 개요
총 5개의 태이블을 구성하였음.
![image](https://github.com/gksrbgks2021/webPOS/assets/39733405/a48e1ef2-6a38-44b8-abf5-dab41bcee17c)

- member

| id | email | password | name | role | regdate |
|----|-------|----------|------|------|---------|
| 사용자id(PK) | 사용자email | 비밀번호 | 사용자이름 | 사용자 의 직급을 나타내며, staff 또는 manager | 아이디 생성 날짜. |


- product 

| productID | netprice | costprice | name |
|-----------|----------|-----------|------|
| 제품 코드(PK) | 정가 | 원가 | 제품 id |


- trade_log 

| id | productID | tradedate | quantitytraded | totalprice | state | location |
|----|-----------|-----------|----------------|------------|-------|----------|
| 테이블 인덱스. (PK) | 제품 id. | 거래가 발생한 날짜 | 제품 거래 수량 | 거래가 발생한 총 금액 | 제품이 편의점에 입고 또는 판매 상태를 기록 (saled, receive) | 편의점 위치 기록 |

- storeinfo 

| managerName | storename |
| --- | --- |
|편의점 점주 이름(PK,FK)| 편의점 이름(PK,FK)|

- inventory

| productid | quantity | storename |
|-----------|----------|-----------|
| 제품 코드(PK) | 수량 | 상점 이름 |


# 핵심 기능 코드
- 상품 관리
- 쿼리 구현부
- 기간 별 수익 통계
- 회원 관리
- AOP 모니터링




### 상품 관리
- 등록된 상품을 조회하고 수량을 선택해 주문할수 있다.
![image](https://github.com/user-attachments/assets/d66d364d-153f-4e5e-afdd-bb040da419cf)

```javascript
 // 선택한 상품을 테이블에 추가
        let row = table.insertRow();
        let nameCell = row.insertCell(0);
        let quantityCell = row.insertCell(1);
        let priceCell = row.insertCell(2);
        let deleteCell = row.insertCell(3); // 삭제 버튼을 위한 셀 추가
        nameCell.innerHTML = name;
        quantityCell.innerHTML = "<input type='number' name='quantity' min='1' value='1' onchange='updateTotalPrice()'>";
        priceCell.innerHTML = netPrice;
        // 삭제 버튼 추가
        deleteCell.innerHTML = "<button onclick='deleteRow(this, \"" + name + "\")'>X</button>";

        updateTotalPrice(); // 총 가격 업데이트
```

### 쿼리 구현부
- Data Access Obeject 는 [인터페이스 - 구현부 코드] 구조로 구성된다.



JdbcTemplate 을 사용하여 쿼리문을 구성하였다.
```java

public class MemberDaoImpl implements MemberDAO {
    private JdbcTemplate jdbcTemplate;

    //생성자로 bean 자동 주입
    @Autowired
    public MemberDaoImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //db 접속해서 loginCheck 하는 기능입니다.
    @Override
    public String loginCheck(Member member) {
        String query = "SELECT * FROM member WHERE email = ? AND password = ?";
        try {
            List<Member> members = jdbcTemplate.query(query,
                    (rs, rowNum) -> new Member(rs.getString("password"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("role"),
                            rs.getTimestamp("datetime").toLocalDateTime()
                    ),
                    member.getEmail(),
                    member.getPassword());
            if (!members.isEmpty()) {
                // 로그인 성공하면 success 리턴
                return "success";
            } else {
                // 로그인 실패
                return "failure";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "failure";
        }
    }

    @Override
    public void save(Member member) {
        KeyHolder keyHolder = new GeneratedKeyHolder(); //auto increment id 기능을 처리하는 객체.

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement pstmt = con.prepareStatement(
                        "insert into MEMBER (EMAIL, PASSWORD, NAME,ROLE, REGDATE) values (?, ?, ?, ?,?)",
                        new String[]{"ID"}); //생성된 id의 기본 키 값 반환
                pstmt.setString(1, member.getEmail());
                pstmt.setString(2, member.getPassword());
                pstmt.setString(3, member.getName());
                pstmt.setString(4, member.getRole());
                pstmt.setTimestamp(5, Timestamp.valueOf(member.getRegdate()));
                return pstmt;
            }
        }, keyHolder);
        Number keyValue = keyHolder.getKey();
        member.setId(keyValue.longValue());//id 삽입
    }
...
//이하 생략

```

### 기간 별 수익 통계

![image](https://github.com/user-attachments/assets/5a5cdbf7-07f1-4c44-ac8c-caedb42cb234)

- qeury 문을 사용하여 집계 하였습니다.
```java
/**
* src/main/java/com/example/webPOS/dao/impl/TradeLogDaoImpl.java
*/
@Override
    public Map<String, Integer> getProfit(String start, String end, String StoreName, String period) {
        if (period.equals("week")) { // 주 단위
            String query = "SELECT A.week AS w, B.s - A.o AS revenue "
                    + "FROM (SELECT WEEK(tradedate) AS week, SUM(totalprice) AS o " + "FROM tradelog "
                    + "WHERE storename = ? AND state = 'order' AND tradedate BETWEEN ? AND ? "
                    + "GROUP BY WEEK(tradedate)) AS A left join (SELECT WEEK(tradedate) AS week, SUM(totalprice) AS s "
                    + "FROM tradelog " + "WHERE storename = ? AND state = 'saled' AND tradedate BETWEEN ? AND ? "
                    + "GROUP BY WEEK(tradedate)) AS B on A.week = B.week ";

            return jdbcTemplate.query(query, (ResultSetExtractor<Map<String, Integer>>) rs -> {
                Map<String, Integer> weeklyTotalQuantities = new TreeMap<>();
                while (rs.next()) {
                    String week = rs.getString("w");
                    int revenue = rs.getInt("revenue");
                    weeklyTotalQuantities.put(week, revenue);
                }
                return weeklyTotalQuantities;
            }, StoreName, start, end, StoreName, start, end);
        } else if (period.equals("day")) { // 일 단위
            String query = "SELECT DATE_FORMAT(tradedate, '%Y-%m-%d') as Daily, SUM(CASE WHEN state = 'saled' THEN totalprice "
                    + "WHEN state = 'order' THEN -1 * totalprice " + "ELSE 0  " + "END) AS profit " + "FROM tradelog "
                    + "WHERE storename = ? AND tradedate BETWEEN ? AND ? "
                    + "GROUP BY DATE_FORMAT(tradedate, '%Y-%m-%d') ";

            return jdbcTemplate.query(query, (ResultSetExtractor<Map<String, Integer>>) rs -> {
                Map<String, Integer> dailyTotalQuantities = new TreeMap<>(); //TreeMap으로 데이터 삽입 시 정렬.
                while (rs.next()) {
                    String day = rs.getString("Daily");
                    int revenue = rs.getInt("profit");
                    dailyTotalQuantities.put(day, revenue);
                }
                return dailyTotalQuantities;
            }, StoreName, start, end);
        } else if (period.equals("month")) { // 월 단위
            String query = "SELECT A.month AS m, A.s - B.o AS revenue "
                    + "FROM (SELECT DATE_FORMAT(tradedate, '%Y-%m') AS month, SUM(totalprice) AS s" + "FROM tradelog "
                    + "WHERE storename = ? AND state = 'saled' AND tradedate BETWEEN ? AND ? "
                    + "GROUP BY DATE_FORMAT(tradedate, '%Y-%m')) AS A left join (SELECT DATE_FORMAT(tradedate, '%Y-%m') AS month, SUM(totalprice) AS o "
                    + "FROM tradelog " + "WHERE storename = ? AND state = 'order' AND tradedate BETWEEN ? AND ? "
                    + "GROUP BY DATE_FORMAT(tradedate, '%Y-%m'))  AS B on A.month = B.month ";

            return jdbcTemplate.query(query, (ResultSetExtractor<Map<String, Integer>>) rs -> {
                Map<String, Integer> monthlyTotalQuantities = new TreeMap<>();
                while (rs.next()) {
                    String month = rs.getString("m");
                    int revenue = rs.getInt("revenue");
                    monthlyTotalQuantities.put(month, revenue);
                }
                return monthlyTotalQuantities;
            }, StoreName, start, end, StoreName, start, end);
        } else {
            return null;
        }
    }

```

### 회원 관리
- 관리자로 로그인하여 현재 회원을 삭제하거나 정보를 수정할 수 있다 

![image](https://github.com/user-attachments/assets/48a6dfef-58d6-4141-a26b-3e331e92858a)


### AOP 사용한 모니터링

- 조인포인트를 컨트롤러 폴더 전체로 설정하여, 다음을 확인할 수 있다
  - 코드가 실행 메소드 이름, 리턴값은, url 요청값 등
- 로그를 남겨 개발할 때 어느 부분에서 이슈가 발생했는지 파악 할 수 있다

![image](https://github.com/gksrbgks2021/webPOS/assets/39733405/b16c33bf-5eac-41a0-8027-783347efea1c)


```java
@Component // 1
@Aspect // 2
public class RequestLoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(RequestLoggingAspect.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {//빈 객체 발견했을때 예외 발생
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    }
    private String paramMapToString(Map<String, String[]> paramMap) {
        return paramMap.entrySet().stream()
                .map(entry -> String.format("%s -> (%s)",
                        entry.getKey(), Joiner.on(",").join(entry.getValue())))
                .collect(Collectors.joining(", "));
    }

    //적용 범위를 잡는 pointcut 임으로 empty body로 남김
    @Pointcut("within(com.example.webPOS.controller..*)") // controller 패키지 하위의 모든 public 메서드와 매칭시킨다.
    public void onRequest() {}

    //조인포인트는, 컨트롤러 마다 다르게 설정해야함.

    @Around("com.example.webPOS.aop.RequestLoggingAspect.onRequest()") // 4
    public Object doLogging(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = // 5
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        Map<String, String[]> paramMap = request.getParameterMap();
        String params = paramMap.isEmpty() ? "" : " [" + paramMapToString(paramMap) + "]";

        Object result = null;
        long start = System.currentTimeMillis();
        try {
            result = joinPoint.proceed(joinPoint.getArgs()); // 6
            return result;
        } finally {

            long end = System.currentTimeMillis();
            String methodName = joinPoint.getSignature().getName();
            logger.debug("Request: {} {}{} < {} ({}ms)", request.getMethod(), request.getRequestURI(),
                    params, request.getRemoteHost(), end - start);
            logger.info("method Name :  [" + methodName+"]");

            logger.info("response: " + (result));
        }
    }
}
```
