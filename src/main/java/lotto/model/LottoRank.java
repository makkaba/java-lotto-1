package lotto.model;

import java.util.Arrays;

public enum LottoRank {

    BOOM(0, 0),
    FIFTH(3, 5_000),
    FOURTH(4, 50_000),
    THIRD(5, 1_500_000),
    SECOND(5, 1_500_000, true),
    FIRST(6, 2_000_000_000);

    private int matchCount;
    private int prizeMoney;
    private boolean isBonus;

    LottoRank(int matchCount, int prizeMoney) {
        this(matchCount, prizeMoney, false);
    }

    LottoRank(int matchCount, int prizeMoney, boolean isBonus) {
        this.matchCount = matchCount;
        this.prizeMoney = prizeMoney;
        this.isBonus = isBonus;
    }

    public static LottoRank match(int matchCount, boolean isBonus) {
        return Arrays.stream(LottoRank.values())
                .filter(rank -> rank.matchCount == matchCount)
                .filter(rank -> rank.isBonus == isBonus)
                .findFirst()
                .orElse(BOOM);
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }

    public boolean isBonus() {
        return isBonus;
    }
}