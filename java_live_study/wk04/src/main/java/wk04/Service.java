package wk04;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.kohsuke.github.*;

public class Service {

    private GitHub github;
    private GHRepository githubRepo;
    private Map<String, Double> percentage = new HashMap<>();

    public Service(String oAuthToken, String repo) {
        GitHubBuilder builder = new GitHubBuilder();
        try {
            github = builder.withOAuthToken(oAuthToken).build();
            githubRepo = github.getRepository(repo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void main() {
        try {
            List<GHIssue> issueList = getAllIssues();
            for (int i = 18; i > 0; i--) {
                updateCommenters(issueList.get(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("======= Participants =======");
        Object[] participants = percentage.keySet().toArray();
        for (Object participant: participants) {
            String account = participant.toString();
            Double completancy = percentage.get(account);
            System.out.println("> " + account + ": " + String.format("%.2f", completancy*100.0) + "%");
        }
        System.out.println("======= THE END =======");
    }

    private void updateCommenters(GHIssue issue) throws IOException {
        List<GHIssueComment> comments = issue.getComments();
        for (GHIssueComment comment: comments) {
            if (hasAssignmentLink(comment)) {
                String id = comment.getUser().getLogin();
                updateCounts(id);
            }
        }
    }

    private void updateCounts(String id) {
        double num = 1/18f;
        if (percentage.containsKey(id)) {
            num += percentage.get(id);
        }
        percentage.put(id, num);
    }

    private boolean hasAssignmentLink(GHIssueComment comment) {
        return comment.getBody().contains("https://");
    }

    private List<GHIssue> getAllIssues() throws IOException {
        return githubRepo.getIssues(GHIssueState.ALL);
    }
}
