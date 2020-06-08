package lotto.domain.lotto;

import lotto.domain.prize.WinningNumbers;
import lotto.domain.prize.WinningResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoTicket {

    private List<LottoNumbers> lottoTicket;

    private LottoTicket(int quantity) {
        checkQuantity(quantity);
        this.lottoTicket = addLottoNumbers(quantity);
    }

    public static LottoTicket create(int quantity) {
        return new LottoTicket(quantity);
    }

    public List<Integer> tellLottoNumbers(int i) {
        return lottoTicket.get(i).getLottoNumbers();
    }

    public WinningResult makeWinningResult(String enteredWinNumber) {
        WinningNumbers winningNumbers = WinningNumbers.create(enteredWinNumber);
        Map<Integer, Integer> winningCountMap = new HashMap<>();
        for (LottoNumbers lottoNumbers : this.lottoTicket) {
            int matchCount = winningNumbers.findMatchCount(lottoNumbers);
            putWinningCount(winningCountMap, matchCount);
        }
        return WinningResult.create(winningCountMap);
    }

    private void checkQuantity(int quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException("구매 장수가 없습니다.");
        }
    }

    private List<LottoNumbers> addLottoNumbers(int quantity) {
        return IntStream.range(0, quantity)
                .mapToObj(i -> LottoNumbers.create())
                .collect(Collectors.toList());
    }

    private void putWinningCount(Map<Integer, Integer> winningCountMap, int matchCount) {
        if (matchCount > 2) {
            winningCountMap.put(matchCount, winningCountMap.getOrDefault(matchCount, 0) + 1);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoTicket that = (LottoTicket) o;
        return Objects.equals(lottoTicket, that.lottoTicket);
    }

    @Override
    public String toString() {
        return String.valueOf(lottoTicket);
    }


}