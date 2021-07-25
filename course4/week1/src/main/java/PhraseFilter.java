public class PhraseFilter implements Filter {
    private final String where;
    private final String phrase;

    public PhraseFilter(String where, String phrase) {
        this.where = where;
        this.phrase = phrase;
    }

    public boolean satisfies(QuakeEntry qe) {
        String info = qe.getInfo();
        if (where.equals("start")) {
            return (info.startsWith(phrase));
        }
        if (where.equals("end")) {
            return (info.endsWith(phrase));
        }
        if (where.equals("any")) {
            return (info.contains(phrase));
        }
        return false;
    }

    public String getName() {
        return "Phrase";
    }
}
