package runner;

import org.springframework.stereotype.Component;
import pages.BasePage;

@Component
public class TestContext {
    private static ThreadLocal<BasePage> page = new ThreadLocal<>();

    public BasePage getPage() {
        return page.get();
    }

    public void setPage(BasePage page) {
        this.page.set(page);
    }
}
