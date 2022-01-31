package com.selenium.assessment;

import org.openqa.selenium.By;


public class DeleteUser {
	public void DeleteSelectedUser() {
		SetUp.driver.findElement(By.name("chkSelectRow[]")).click();

		SetUp.driver.findElement(By.id("btnDelete")).click();

		SetUp.driver.findElement(By.id("dialogDeleteBtn")).click();

		}
		}
