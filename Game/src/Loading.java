public class Loading {
    private static boolean isLoading = false;
    
    public static void setLoading(boolean bool) {
        isLoading = bool;
    }

    public static boolean getState() {
        return isLoading;
    }
}
