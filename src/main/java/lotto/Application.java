package lotto;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.*;

public class Application {
    public static void main(String[] args) {
        int price = 0;

        while (true) {
            System.out.println("구입금액을 입력해 주세요.");
            String inputPrice = Console.readLine();

            try {
                price = Integer.parseInt(inputPrice);
                if (price < 1000 || price % 1000 != 0) {
                    throw new IllegalArgumentException("[ERROR] 구입 금액은 1,000원 단위로 입력해야 합니다.");
                }
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("[ERROR] 구입금액이 잘못되었습니다.");
            }
        }
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
        result.put("3개 일치 (5,000원)", 0);
        result.put("4개 일치 (50,000원)", 0);
        result.put("5개 일치 (1,500,000원)", 0);
        result.put("5개 일치, 보너스 볼 일치 (30,000,000원)", 0);
        result.put("6개 일치 (2,000,000,000원)", 0);

        for (List<Integer> lotto : allLotto) {
            int matchCount = 0;

            for (int n : lotto) {
                if (winNumbers.contains(n)) {
                    matchCount++;
                }
            }

            boolean bonusMatch = lotto.contains(bonus); // 보너스 볼 맞은 경우 true
            if (matchCount == 3) {
                result.put("3개 일치 (5,000원)", result.get("3개 일치 (5,000원)")+1);
            }
            if (matchCount == 4) {
                result.put("4개 일치 (50,000원)", result.get("4개 일치 (50,000원)")+1);
            }
            if (matchCount == 5 && !bonusMatch) {
                result.put("5개 일치 (1,500,000원)", result.get("5개 일치 (1,500,000원)")+1);
            }
            if (matchCount == 5 && bonusMatch) {
                result.put("5개 일치, 보너스 볼 일치 (30,000,000원)", result.get("5개 일치, 보너스 볼 일치 (30,000,000원)")+1);
            }
            if (matchCount == 6) {
                result.put("6개 일치 (2,000,000,000원)", result.get("6개 일치 (2,000,000,000원)")+1);
            }

        }

        // 4. 당첨 통계
        System.out.println("당첨 통계");
        System.out.println("---");

        for (Map.Entry<String, Integer> map : result.entrySet()) {
            System.out.println(map.getKey() + " - " + map.getValue() + "개");
        }

        // 5. 수익률 계산
        int total =
                result.get("3개 일치 (5,000원)") * 5000 +
                result.get("4개 일치 (50,000원)") * 50000 +
                result.get("5개 일치 (1,500,000원)") * 1500000 +
                result.get("5개 일치, 보너스 볼 일치 (30,000,000원)") * 30000000 +
                result.get ("6개 일치 (2,000,000,000원)") * 2000000000;

        double profit  = (double) total / price *100;
        System.out.println("총 수익률은 " + profit + "%입니다.");
    }
}
