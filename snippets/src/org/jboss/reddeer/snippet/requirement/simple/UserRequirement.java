package org.jboss.reddeer.snippet.requirement.simple;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.jboss.reddeer.junit.requirement.PropertyConfiguration;
import org.jboss.reddeer.junit.requirement.Requirement;
import org.jboss.reddeer.snippet.requirement.simple.UserRequirement.User;

public class UserRequirement implements Requirement<User>,
		PropertyConfiguration {
	
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.TYPE)
	public @interface User {
	}

	private String name;
	private String ip;

	public boolean canFulfill() {
		// return true if it is possible to connect to database
		return true;
	}

	public void fulfill() {
		System.out.println("Fulfilling User requirement with\nName: " + name
				+ "\nIP: " + ip);
		// create a user in the database if user does not exists
	}

	public void setDeclaration(User user) {
		// annotation has no parameters so no need to store it
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getIp() {
		return ip;
	}
	
	public void cleanUp() {
		// perform clean up
	}
}