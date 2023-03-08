package blackjack.controller;

import blackjack.domain.Card;
import blackjack.domain.CardNumber;
import blackjack.domain.CardSymbol;
import blackjack.domain.Dealer;
import blackjack.domain.Deck;
import blackjack.domain.Game;
import blackjack.domain.GamePlayer;
import blackjack.domain.GameResult;
import blackjack.domain.Name;
import blackjack.domain.Player;
import blackjack.domain.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class BlackJackGameController {
    private static final String LINE_SEPARATOR = System.lineSeparator();

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackGameController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    private Deck generateDeck() {
        List<Card> cards = Arrays.stream(CardNumber.values())
                .flatMap(cardNumber -> Arrays.stream(CardSymbol.values())
                        .map(cardSymbol -> new Card(cardNumber, cardSymbol)))
                .collect(Collectors.toList());

        return new Deck((Stack<Card>) cards);
    }

    private List<String> getCardNames(List<Card> cards) {
        return cards.stream()
                .map(card -> card.getCardNumberToString() + card.getCardSymbolToString()).collect(
                        Collectors.toList());
    }

    public void run() {
        Game game = start();
        play(game);
        finish(game);
    }

    private void finish(Game game) {
        outputView.printDealerResult(getCardNames(game.showDealerAllCards()), game.getDealerScore());

        for (int i = 0; i < game.getPlayersCount(); i++) {
            printOnePlayerResult(game, i);
        }

        GameResult gameResult = new GameResult(game);
        outputView.printEndMsg();
        outputView.printDealerWinningResult(gameResult.getDealerResult());
        outputView.printPlayerWinningResult(gameResult.getPlayerResult());
    }

    private void printOnePlayerResult(Game game, int i) {
        String playerName = game.showPlayerNameByIndex(i);
        List<String> cards = getCardNames(game.showPlayerCardsByIndex(i));
        int index = game.getPlayerScoreByIndex(i);
        outputView.printPlayerResult(playerName, cards, index);
    }

    private void play(Game game) {
        for (int i = 0; i < game.getPlayersCount(); i++) {
            playersPlay(game, i);
        }
        dealerPlay(game);
    }

    private Game start() {
        Game game = generateGame();
        printStartCards(game);
        return game;
    }

    private void dealerPlay(Game game) {
        while (game.isHitDealer()) {
            outputView.printDealerHit();
            game.giveCardToDealer();
        }
    }

    private void printStartCards(Game game) {
        outputView.printStartMsg(game.showPlayersName());
        outputView.printDealerCards(getCardNames(game.showDealerCards()), System.lineSeparator());
        for (int i = 0; i < game.getPlayersCount(); i++) {
            String playerName = game.showPlayerNameByIndex(i);
            List<String> cards = getCardNames(game.showPlayerCardsByIndex(i));
            outputView.printPlayerCards(playerName, cards, LINE_SEPARATOR);
        }
    }

    private Game generateGame() {
        List<Player> players = generatePlayers();
        Deck deck = generateDeck();
        GamePlayer gamePlayer = new GamePlayer(new Players(players), new Dealer());
        return new Game(deck, gamePlayer);
    }

    private List<Player> generatePlayers() {
        try {
            List<String> playersName = inputView.readPlayersName();
            return playersName.stream()
                    .map(name -> new Player(new Name(name)))
                    .collect(Collectors.toList());
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR]" + e.getMessage());
            return generatePlayers();
        }
    }

    private void playersPlay(Game game, int i) {
        while (isCheckPlayerCommand(game, i)) {
            game.giveCardToPlayerByIndex(i);
            outputView.printPlayerCards(game.showPlayerNameByIndex(i),
                    getCardNames(game.showPlayerCardsByIndex(i)), System.lineSeparator());
        }
    }

    private boolean isCheckPlayerCommand(Game game, int i) {
        if (game.isHitPlayerByIndex(i)) {
            return inputView.readTryCommand(game.showPlayerNameByIndex(i)).equals("y");
        }
        outputView.printPlayerCards(game.showPlayerNameByIndex(i),
                getCardNames(game.showPlayerCardsByIndex(i)), System.lineSeparator());
        return false;
    }
}
