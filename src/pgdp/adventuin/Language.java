package pgdp.adventuin;

public enum Language {
    ENGLISH, GERMAN;

    public String getLocalizedChristmasGreeting(String greeterName) {
        if (this == GERMAN){
            return "Fröhliche Weihnachten wünscht dir " + greeterName +"!";
        }
        else{
            return greeterName + " wishes you a Merry Christmas!";
        }
    }
}
