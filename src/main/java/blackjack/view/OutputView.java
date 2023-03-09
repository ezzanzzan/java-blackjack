package blackjack.view;

import java.util.List;
import java.util.Map;

public class OutputView {
    private static final int ZERO = 0;

    public void printStartMsg(List<String> names) {
        System.out.println();
        int lastIndex = names.size() - 1;
        System.out.print("딜러와 ");
        for (int i = 0; i < lastIndex; i++) {
            System.out.print(names.get(i) + ", ");
        }
        System.out.println(names.get(lastIndex) + "에게 2장을 나누었습니다.");
    }

    public void printPlayerCards(String name, List<String> cards, String end) {
        int lastIndex = cards.size() - 1;
        System.out.print(name + "카드: ");
        for (int i = 0; i < lastIndex; i++) {
            System.out.print(cards.get(i) + ", ");
        }
        System.out.print(cards.get(lastIndex) + end);
    }

    public void printDealerCards(List<String> cards, String end) {
        int lastIndex = cards.size() - 1;
        System.out.print("딜러: ");
        for (int i = 0; i < lastIndex; i++) {
            System.out.print(cards.get(i) + ", ");
        }
        System.out.print(cards.get(lastIndex) + end);
    }

    public void printDealerHit() {
        System.out.println();
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printDealerResult(List<String> cardNames, int calculateScore) {
        System.out.println();
        printDealerCards(cardNames, "");
        System.out.println(" - 결과: " + calculateScore);
    }

    public void printPlayerResult(String showName, List<String> cardNames, int calculateScore) {
        printPlayerCards(showName, cardNames, "");
        System.out.println(" - 결과: " + calculateScore);
    }

    public void printEndMsg() {
        System.out.println();
        System.out.println("## 최종 승패");
    }

    public void printPlayerWinningResult(Map<String, String> playerResult) {
        for (String playerName : playerResult.keySet()) {
            System.out.println(playerName + ": " + playerResult.get(playerName));
        }
    }

    public void printDealerWinningResult(Map<String, Integer> dealerResult) {
        StringBuilder dealerResultMsg = new StringBuilder("딜러: ");
        for (String result : dealerResult.keySet()) {
            if (dealerResult.get(result) != ZERO) {
                dealerResultMsg.append(dealerResult.get(result)).append(result).append(" ");
            }
        }
        System.out.println(dealerResultMsg);
    }
}
