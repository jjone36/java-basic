import dashboard.Service;

public class AppRunner {

    public static void main(String[] args) {
        final String OAuthToken = "";
        String repo = "whiteship/live-study";

        Service service = new Service(OAuthToken, repo);
        service.main();
    }
}
