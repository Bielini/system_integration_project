package pl.bie.model;

public enum VoivodeshipNames {

        LUBELSKIE("LUBELSKIE"),
        ŚWIĘTOKRZYSKIE("ŚWIĘTOKRZYSKIE"),
        PODKARPACKIE("PODKARPACKIE"),
        ŁÓDZKIE("ŁÓDZKIE"),
        POMORSKIE("POMORSKIE"),
        WARMIŃSKO_MAZURSKIE("WARMIŃSKO-MAZURSKIE"),
        ZACHODNIOPOMORSKIE("ZACHODNIOPOMORSKIE"),
        WIELKOPOLSKIE("WIELKOPOLSKIE"),
        LUBUSKIE("LUBUSKIE"),
        ŚLĄSKIE("ŚLĄSKIE"),
        DOLNOŚLĄSKIE("DOLNOŚLĄSKIE"),
        KUJAWSKO_POMORSKIE("KUJAWSKO-POMORSKIE"),
        OPOLSKIE("OPOLSKIE"),
        PODLASKIE("PODLASKIE"),
        MAZOWIECKIE("MAZOWIECKIE"),
        MAŁOPOLSKIE("MAŁOPOLSKIE");

        private final String value;

        VoivodeshipNames(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

