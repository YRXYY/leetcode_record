package javalanguage.reflect;

import java.util.Map;

public class Batman {

    private long id;

    private String rune;

    private String armor;

    private String shovel;

    private Map<String,String> magicArts;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRune() {
        return rune;
    }

    public void setRune(String rune) {
        this.rune = rune;
    }

    public String getArmor() {
        return armor;
    }

    public void setArmor(String armor) {
        this.armor = armor;
    }

    public String getShovel() {
        return shovel;
    }

    public void setShovel(String shovel) {
        this.shovel = shovel;
    }

    public Map<String, String> getMagicArts() {
        return magicArts;
    }

    public void setMagicArts(Map<String, String> magicArts) {
        this.magicArts = magicArts;
    }
}
