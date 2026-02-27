package AndrewWebServices;

/**
 * Stub for RecSys to use in tests. Returns a fixed recommendation instantly.
 */
public class StubRecSys extends RecSys {
    @Override
    public String getRecommendation(String accountName) {
        return "Animal House";
    }
}
