package _2_domainPackage;

public enum PieceOrder {
    NULL(null),
    ROOK1("Rook"),
    KNIGHT1("Knight"),
    BISHOP1("Bishop"),
    QUEEN("Queen"),
    KING("King"),
    BISHOP2("Bishop"),
    KNIGHT2("Knight"),
    ROOK2("Rook");

    public final String name;

    PieceOrder(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
