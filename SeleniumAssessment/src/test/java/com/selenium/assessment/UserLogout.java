package com.selenium.assessment;

import org.openqa.selenium.By;
import org.testng.annotations.Test;


public class UserLogout {
@Test
public void LogOut() {
SetUp.driver.findElement(By.id("welcome")).click();
SetUp.driver.findElement(By.xpath("//*[@id=\"welcome-menu\"]/ul/li[3]/a")).click();
}
}