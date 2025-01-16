public enum RPS {
    ROCK, PAPER, SCISSORS;

    public int winner(RPS player1) {
        return switch (this) {
            case ROCK -> switch (player1) {
                case ROCK -> 0;
                case PAPER -> 1;
                case SCISSORS -> 2;
            };
            case PAPER -> switch (player1) {
                case ROCK -> 2;
                case PAPER -> 0;
                case SCISSORS -> 1;
            };
            case SCISSORS -> switch (player1) {
                case ROCK -> 1;
                case PAPER -> 2;
                case SCISSORS -> 0;
            };
        };
    }
}
