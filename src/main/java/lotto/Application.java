package lotto;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.*;

public class Application {
    public static void main(String[] args) {
        System.out.println("구입금액을 입력해 주세요.");
        int price = Integer.parseInt(Console.readLine());
        int count = price / 1000;

        System.out.println(count + "개를 구매했습니다.");

        // 1. 로또 생성
        List<List<Integer>> allLotto = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            List<Integer> lotto = Randoms.pickUniqueNumbersInRange(1, 45, 6);
            allLotto.add(lotto);
            System.out.println(lotto);
        }

        // 2. 당첨 번호 입력
        System.out.println("당첨 번호를 입력해 주세요.");
        String[] input = Console.readLine().split(",");
        Set<Integer> winNumbers = new HashSet<>();

        for (String s : input) {
            winNumbers.add(Integer.parseInt(s));
        }

        System.out.println("보너스 번호를 입력해 주세요.");
        int bonus = Integer.parseInt(Console.readLine());

        // 3. 결과 계산
        Map<String, Integer> result = new HashMap<>();


        // 4. 당첨 통계
        System.out.println("당첨 통계");
        System.out.println("---");


    }
}
