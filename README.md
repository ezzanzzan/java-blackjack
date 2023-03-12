# java-blackjack

블랙잭 미션 저장소

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)

---

## 1단계 미션 기능 요구사항

### 도메인 로직

- [x] 블랙잭 게임은 딜러와 플레이어 중 카드의 합이 21 또는 21에 가장 가까운 숫자를 가지는 쪽이 이기는 게임이다.
    - [x] 카드의 합은 카드 숫자를 기준으로 더한다.
        - [x] Ace는 1또는 11로 계산할 수 있다.
        - [x] King,Queen,Jack은 각각 10으로 계산한다.
        - [x] 나머지 카드는 카드 숫자를 기준으로 계산한다.
    - [x] 딜러와 플레이어는 카드를 지급 받을 수 있다.
        - [x] 게임을 시작하면 딜러와 플레이어는 두 장의 카드를 지급 받는다.
        - [x] 플레이어는 카드의 합이 21을 초과한다면 카드를 지급 받을 수 없다.
        - [x] 플레이어가 A를 가지고 있을 때 플레이어의 점수 합이 11을 초과하면 A는 1점으로 간주한다.
        - [x] 플레이어가 A를 가지고 있을 때 플레이어의 점수 합이 11 이하면 A는 11점으로 간주한다.
        - [x] 플레이어의 수는 1~6 사이여야 한다.
        - [x] 플레이어의 이름은 양 끝 공백을 포함하지 않는다.
        - [x] 딜러는 카드의 합이 16을 초과한다면 카드를 지급 받을 수 없다.
        - [x] 딜러는 처음에 받은 2장의 합계가 16이하이면 반드시 1장의 카드를 추가로 받아야 한다.
    - [x] 게임이 끝나고 딜러와 각 플레이어의 승패를 구할 수 있다.

### 입력

- [x] 플레이어의 이름을 입력받는다.
    - [x] 플레이어의 이름은 쉼표를 기준으로 구분된다.
- [x] 플레이어는 카드를 더 받을 지 선택할 수 있다.
    - [x] 더 지급받으려면 y, 멈추고 싶다면 n을 입력한다.
    - [x] 그 외의 입력을 받으면 예외 메세지를 던지고 재입력을 받는다.

### 출력

- [x] 딜러와 플레이어가 각자 보유한 카드를 출력할 수 있다.
- [x] 딜러는 추가로 카드를 받았는 지에 대한 메세지를 출력할 수 있다.
- [x] 게임이 끝난 뒤 딜러와 각 플레이어가 보유한 카드와 카드의 합을 출력할 수 있다.
- [x] 게임의 최종 승패를 출력할 수 있다.

## 1단계 미션 피드백 후 리팩토링 목록

### 컨벤션

- [x] 메서드 순서 정렬
- [x] `static` 선언된 메소드 확인 후 제거
- [x] 축약해서 사용한 메소드, 필드 네이밍 변경
- [x] GameResult class getPlayerWinWhenDealerLessThan21 메소드 네이밍 변경
- [x] 상수와 필드 사이 개행 추가
- [x] show로 시작하는 메서드 네이밍 get으로 수정
- [x] 한 번만 사용되는 문장 -> 코드 내에서 바로 사용하도록 수정

### 검증

- [x] 이름에 공백이 들어왔을 경우 검증 추가
- [x] Deck이 비어있을 때 draw 메소드 사용하는 경우 검증 추가

### 테스트 코드

- [x] given when then 제거
- [x] assertDoesNotThrow를 사용한 테스트 메서드 -> 생성한 객체의 상태를 체크해주는 방식으로 변경
- [x] `@BeforeEach` 사용 부분 변경
- [x] GameResultTest 단위테스트 추가
- [x] Parameterized test 네이밍 출력 형식 변경

### 클래스 내부 로직 변경

- [x] Card 생성하는 부분 `for문` -> `stream`으로 변경
- [x] CardNumber enum class 필드 변경
- [x] Deck class 필드 변경
- [x] Dealer, Player class calculateScore 메소드 로직 변경
- [x] hasACE 메소드 위치 변경
- [x] GameResult class dealerResult.put(~) 올바른 결과가 출력되도록 변경
- [x] Person interface -> abstract class로 변경
- [x] 결과 타입에 대한 enum class 추가
- [x] view 호출하는 로직 단순화
- [x] List<Card>를 필드로 가지는 Hand 생성
- [x] Deck shuffle 메소드 전략 패턴을 통해 실행될 수 있도록 변경
- [x] for문 내부에서 size() 호출하는 로직 변경
- [x] 단순 패스를 해주는 로직 변경

--- 

## 1단계 미션 2차 피드백 후 리팩토링 목록

### 컨벤션

- [ ] playerHitOrStand 메소드 indent 요구사항에 맞춰 수정
- [x] 메서드 네이밍 get ~ 으로 통일
- [x] 스트림 연산자 기준으로 개행
- [x] OutputView printFinalVictoryOrDefeatDealer 내 depth 2인 부분 수정
- [ ] 테스트 클래스 given when then 패턴 대신 개행으로 구분
- [ ] 테스트 코드 내의 메서드 네이밍에 대해 질문하기
- [x] DisplayName 자세히 작성하기
- [x] 사용하지 않는 메서드, 출력문구 제거

### 클래스 내부 로직 변경

- [ ] BlackJackGameController에 dealer와 players를 가져와서 로직을 호출할 수 있도록 로직 변경
- [ ] enum class → enum 자체로 값을 비교하도록 수정
- [ ] enum 내부 메소드를 사용하도록 수정
- [ ] 디미터 법칙 위반된 코드 한 줄에 점을 하나만 찍도록 수정
- [ ] Game 내부에서 GameResult를 생성하는 메서드를 만들고, GamePlayer(Players와 Dealer)를 넘겨줄 수 있지 않나 ?
- [ ] Person class에서 hand를 생성자로 ..?
- [ ] calculateScore 메소드 hand로 위치 변경
- [x] 전체 코드에 사용되는 것이 아니면 @BeforeEach 제거
- [ ] `calculateVictoryOrDefeatWithDealerAndPlayer` 메서드 private로 수정
- [ ] GameResultTest에 `calculateVictoryOrDefeatWithDealerAndPlayer` 를 사용한 테스트 생성자를 통해 진행하도록 수정

--- 

## 2단계 미션 기능 요구사항

### 도메인 로직

- [ ] 플레이어는 게임을 시작할 때 배팅 금액을 정해야 한다.
- [ ] 플레이어는 카드를 추가로 뽑아 21을 초과할 경우 배팅 금액을 모두 잃게 된다.
- [ ] 플레이어는 처음 두 장의 카드 합이 21일 경우 블랙잭이 되면 베팅 금액의 1.5 배를 딜러에게 받는다.
- [ ] 딜러와 플레이어가 모두 동시에 블랙잭인 경우 플레이어는 베팅한 금액을 돌려받는다.
- [ ] 딜러가 21을 초과하면 그 시점까지 남아 있던 플레이어들은 가지고 있는 패에 상관 없이 승리해 베팅 금액을 받는다.
- [ ] 배당율은 건 금액만큼을 받는 게 기본이다. (100원을 걸어 이기면 200원을 받고 지면 건 금액인 100원을 잃는다.)

### 입력

- [ ] 플레이어의 배팅 금액을 입력 받는다.

### 출력

- [ ] 최종 승패를 출력하는 대신 최종 수익을 출력한다.
