package Classes;

public class BlackOut {
    boolean lightMode = false;
    boolean isFrozen=false;
    String pathToImage = "Images/blackOut.png";
    int howScaledIs=0;

    public void lightOn() {
            pathToImage = "Images/lightOut.png";
            lightMode = true;
            howScaledIs=0;
    }
    public void lightOff() {
        pathToImage = "Images/blackOut.png";
        lightMode = false;
    }
    public void makeFreeze() {
        isFrozen=true;
    }
    public void unfreeze() {
        isFrozen=false;
    }

    public boolean isFrozen() {
        return isFrozen;
    }

    public boolean isLightMode() {
        return lightMode;
    }

    public int getHowScaledIs() {
        return howScaledIs;
    }

    public void setHowScaledIs(int howScaledIs) {
        this.howScaledIs = howScaledIs;
    }

    public String getPathToImage() {
        return pathToImage;
    }
}
