package pgdp.adventuin;

import pgdp.color.RgbColor;

public class Adventuin {
    private final String name;
    private final int height;
    private final RgbColor color;
    private final HatType hat;
    private final Language language;

    public Adventuin(String name, int size, RgbColor color, HatType hat, Language language) {
        this.name = name;
        this.height = size;
        this.color = color;
        this.hat = hat;
        this.language = language;
    }

    public String getName() {
        return name;
    }

    public int getHeight() {
        return height;
    }

    public RgbColor getColor() {
        return color;
    }

    public HatType getHatType() {
        return hat;
    }

    public Language getLanguage() {
        return language;
    }

    @Override
    public String toString() {
        return "Adventuin{" +
                "name='" + name + '\'' +
                ", height=" + height +
                ", color=" + color +
                ", hatType=" + hat +
                ", language=" + language +
                '}';
    }
}
