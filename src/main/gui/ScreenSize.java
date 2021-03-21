package gui;

/*
 * Represents the screen size of the window in which game is rendered.
 */
public class ScreenSize {
    private static final int SCREENWIDTH = 1000;
    private static final int SCREENHEIGHT = SCREENWIDTH / 2;

    //getters
    public int getScreenWidth() {
        return SCREENWIDTH;
    }

    public int getScreenHeight() {
        return SCREENHEIGHT;
    }
}
