package techcourse.fp.chess.domain;

import java.util.Arrays;

public enum Rank {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8);

    private final int order;

    Rank(final int order) {
        this.order = order;
    }

    public static Rank of(final int order) {
        return Arrays.stream(values())
                .filter(rank -> rank.order == order)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 rank값을 입력하셨습니다."));
    }

    public static Rank createByName(final String name) {
        return Arrays.stream(values())
                .filter(rank -> rank.name().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 rank값을 입력하셨습니다."));
    }

    public int getOrder() {
        return order;
    }
}
