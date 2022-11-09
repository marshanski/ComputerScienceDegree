package code;
public final class IntegrityStatement {
    public static String signature() {
        String names = "Ido Israeli and Raz Marshanski"; // <- Fill in your names here!
        if (names.length() == 0) {
            throw new UnsupportedOperationException("You should sign here");
        }
        return names;
    }
}
