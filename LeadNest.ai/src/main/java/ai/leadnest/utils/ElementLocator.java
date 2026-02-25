package ai.leadnest.utils;

import org.openqa.selenium.By;

public enum ElementLocator {

    LOGIN_BTN(By.xpath("//a[normalize-space()='Log In']")),
    EMAIL_FIELD(By.xpath("//input[@name='username']")),
    PASSWORD_FIELD(By.xpath("//input[@name='password']")),
    SUBMIT_BTN(By.id("kc-login"));
	

    private final By locator;

    ElementLocator(By locator) {
        this.locator = locator;
    }

    public By getLocator() {
        return locator;
    }
}
