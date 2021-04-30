public class Loading {

    private static boolean isLoading = false;
    
    /**
     * Sets whether or not the game is loading
     * @param bool is the game loading
     */
    public static void setLoading(boolean bool) {
        isLoading = bool;
    }

    /**
     * gets the state of loading
     * @return is the game loading
     */
    public static boolean getState() {
        return isLoading;
    }
}
