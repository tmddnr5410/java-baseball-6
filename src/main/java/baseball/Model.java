package baseball;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;

public class Model {
    private static String answerComputer;
    private static String answerPlayer;

    final int INPUT_LENGTH_MAX = 3;
    final int BALL_MIN = 1;
    final int BALL_MAX = 9;
    final int INPUT_RESTART_LENGTH_MAX = 1;
    final int INPUT_RESTART_MIN = 1;
    final int INPUT_RESTART_MAX = 2;


    final int STRIKE = 0;
    final int BALL = 1;

    public void initComputerAnswer() {
        //model
        List<Integer> computer = new ArrayList<>();
        StringBuilder answerBuilder = new StringBuilder();
        while (computer.size() < 3) {
            int randomNumber = Randoms.pickNumberInRange(1, 9);
            if (!computer.contains(randomNumber)) {
                computer.add(randomNumber);
                answerBuilder.append(Integer.toString(randomNumber));
            }
        }
        answerComputer = answerBuilder.toString();
    }

    public Integer[] countStrikeBallHits() {
        Integer[] score = new Integer[]{0, 0};

        char sourceChar, answerChar;
        for (int i = 0; i < answerPlayer.length(); i++) {
            sourceChar = answerPlayer.charAt(i);
            answerChar = answerComputer.charAt(i);
            if (sourceChar == answerChar) {
                score[STRIKE]++; // Increment strikes
                continue;
            }
            if (answerComputer.contains(String.valueOf(sourceChar))) {
                score[BALL]++; // Increment balls
            }
        }

        return score;
    }



    public boolean restartGame(String playerInput) {
        if (playerInput.equals("1")) {
            initComputerAnswer();
            return true;
        }
        if (playerInput.equals("2")) {
            return false;
        }
        return false;
    }



    public void validateCheckInputAnswer(String inputPlayer) {
        exceptLengthInvalid(inputPlayer, INPUT_LENGTH_MAX);
        exceptNotInteger(inputPlayer);
        exceptInvalidRange(inputPlayer, BALL_MIN, BALL_MAX);
        exceptInputSameNumber(inputPlayer);
        this.answerPlayer = inputPlayer;

    }

    public void validateCheckInputRestart(String inputPlayer) {
        exceptLengthInvalid(inputPlayer, INPUT_RESTART_LENGTH_MAX);
        exceptNotInteger(inputPlayer);
        exceptInvalidRange(inputPlayer, INPUT_RESTART_MIN, INPUT_RESTART_MAX);

    }

    public void exceptLengthInvalid(String source, int exclusive) {
        if (source.length() != exclusive) {
            throw new IllegalArgumentException("입력값의 길이는 " + Integer.toString(exclusive) + " 과(와) 같아야 합니다.");
        }
    }


    //model
    public void exceptNotInteger(String source) {
        for (int i = 0; i < source.length(); i++) {
            if (!Character.isDigit(source.charAt(i))) {
                throw new IllegalArgumentException("입력값은 반드시 정수여야 합니다.");
            }
        }
    }

    //model
    public void exceptInvalidRange(String source, final int startInclusive, final int endInclusive) {
        int eachDigit;
        for (int i = 0; i < source.length(); i++) {
            eachDigit = Integer.parseInt(String.valueOf(source.charAt(i)));
            if (eachDigit < startInclusive || endInclusive < eachDigit) {
                throw new IllegalArgumentException("입력값은 " + startInclusive + " 이상" + endInclusive + " 이하여야 합니다.");
            }
        }
    }

    //model
    public void exceptInputSameNumber(String source) {
        for (int i = 0; i < source.length(); i++) {
            for (int j = i + 1; j < source.length(); j++) {
                if (source.charAt(i) == source.charAt(j)) {
                    throw new IllegalArgumentException("입력값은 서로 다른 숫자로 이루어져야 합니다.");
                }
            }
        }
    }

    //utils
    public ArrayList<Integer> parseStringToInteger(String source) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < source.length(); i++) {
            list.add(Character.getNumericValue(source.charAt(i)));
        }
        return list;
    }
}
