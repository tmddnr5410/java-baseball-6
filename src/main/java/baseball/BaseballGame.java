package baseball;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

public class BaseballGame {
    private String answerComputer;
    final int INPUT_LENGTH_MAX = 3;

    final int BALL_MIN = 1;
    final int BALL_MAX = 9;

    final int INPUT_RESTART_LENGTH_MAX = 1;
    final int INPUT_RESTART_MIN = 1;
    final int INPUT_RESTART_MAX = 2;


    public void startGame() {
        System.out.println("숫자 야구 게임을 시작합니다.");

        boolean isPlaying = true;

        initAnswer();

        String answerPlayer;

        String messageScore;

        System.out.print("생성된 숫자는 " + answerComputer + "\n");
        do {

            System.out.print("숫자를 입력해주세요 : ");
            answerPlayer = Console.readLine();
            exceptLengthInvalid(answerPlayer, INPUT_LENGTH_MAX);
            exceptNotInteger(answerPlayer);
            exceptInvalidRange(answerPlayer, BALL_MIN, BALL_MAX);

            messageScore = countStrikeBallHits(answerPlayer, answerComputer);

            System.out.println(messageScore);

            if (answerPlayer.equals(answerComputer)) {
                System.out.println("3개의 숫자 를 모두 맞히셨습니다! 게임 종료");
                isPlaying = restartGame();
            }
        } while (isPlaying);
    }


    public String countStrikeBallHits(String source, String answer) {
        int ballCnt = 0;
        int strikeCnt = 0;
        String messageScore = "";
        String messageBall = "";
        String messageStrike = "";

        for (int i = 0; i < source.length(); i++) {
            char sourceChar = source.charAt(i);
            if (sourceChar == answer.charAt(i)) {
                strikeCnt++;
            } else if (answer.contains(String.valueOf(sourceChar))) {
                ballCnt++;
            }
        }

        if (ballCnt > 0) {
            messageBall = String.valueOf(ballCnt) + "볼 ";
        }
        if (strikeCnt > 0) {
            messageStrike = String.valueOf(strikeCnt) + "스트라이크";
        }

        if (ballCnt + strikeCnt > 0) {
            messageScore = messageBall + messageStrike;
        } else if (ballCnt + strikeCnt == 0) {
            messageScore = "낫싱";
        }

        return messageScore;
    }


    public void initAnswer() {
        int randomNum = Randoms.pickNumberInRange(111, 999);
        this.answerComputer = Integer.toString(randomNum);
    }

    public boolean restartGame() {
        System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
        String playerInput = Console.readLine();
        exceptLengthInvalid(playerInput, INPUT_RESTART_LENGTH_MAX);
        exceptNotInteger(playerInput);
        exceptInvalidRange(playerInput, INPUT_RESTART_MIN, INPUT_RESTART_MAX);
        boolean isRestart = true;
        if (playerInput.equals("1")) {
            initAnswer();
        } else if (playerInput.equals("2")) {
            isRestart = false;
        }

        return isRestart;

    }

    public void exceptLengthInvalid(String source, int exclusive) {
        if (source.length() != exclusive) {
            throw new IllegalArgumentException("입력값의 길이는 " + Integer.toString(exclusive) + " 과(와) 같아야 합니다.");
        }
    }

    public void exceptNotInteger(String source) {
        for (int i = 0; i < source.length(); i++) {
            if (!Character.isDigit(source.charAt(i))) {
                throw new IllegalArgumentException("입력값은 반드시 정수여야 합니다.");
            }
        }
    }

    public void exceptInvalidRange(String source, final int startInclusive, final int endInclusive) {
        int eachDigit;
        for (int i = 0; i < source.length(); i++) {
            eachDigit = Integer.parseInt(String.valueOf(source.charAt(i)));
            if (eachDigit < startInclusive || endInclusive < eachDigit) {
                throw new IllegalArgumentException("입력값은 " + startInclusive + " 이상" + endInclusive + " 이하여야 합니다.");
            }
        }
    }

}
