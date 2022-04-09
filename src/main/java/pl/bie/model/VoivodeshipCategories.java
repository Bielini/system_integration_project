package pl.bie.model;

public enum VoivodeshipCategories {

    ŚREDNIE_ZAROBKI_BRUTTO("averageSalary.xml"),
    LICZBA_WYPADKÓW_SAMOCHODOWYCH("carAccidents.xml"),
    LICZBA_ŚMIERCI("deathsNumber.xml"),
    PRZYCHODY_RESTAURACJI("restaurantsIncome.xml"),
    POJEMNOŚCI_IMPREZ_MASOWYCH("massiveParties.xml");

    private final String value;

    VoivodeshipCategories(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }


}
