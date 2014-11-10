import org.junit.Test;

import com.atlauncher.reporter.GithubIssue;

public final class TestGithubIssue{
    @Test
    public void test(){
        GithubIssue issue = new GithubIssue("Test", "Test");
        System.out.println(issue);
    }
}