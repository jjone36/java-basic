import wk04.Service;

public class AppRunner {

    public static void main(String[] args) {
        final String OAuthToken = "e168049b3ba498150ccdfc513104a989549d6906";
        String repo = "whiteship/live-study";

        Service service = new Service(OAuthToken, repo);
        service.main();
    }
}
